import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { DealerDesignationMasterPagePresenter } from './dealer-designation-master-page.presenter';
import { DealerDesignationMasterPageStore } from './dealer-designation-master-page.store';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { DealerDesignationMasterPageService } from './dealer-designation-master-page.service';


@Component({
  selector: 'app-dealer-designation-master-page',
  templateUrl: './dealer-designation-master-page.component.html',
  styleUrls: ['./dealer-designation-master-page.component.scss'],
  providers: [DealerDesignationMasterPagePresenter, DealerDesignationMasterPageStore, DealerDesignationMasterPageService]
})
export class DealerDesignationMasterPageComponent implements OnInit {

  createForm: FormGroup
  dealerDetails: FormGroup
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;

  id:any

  constructor(
    private dealerDesignationMasterPagePresenter: DealerDesignationMasterPagePresenter,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private dealerDesignationMasterPageService: DealerDesignationMasterPageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.dealerDesignationMasterPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.createForm = this.dealerDesignationMasterPagePresenter.createDealerDesignationMasterForm
    this.dealerDetails = this.dealerDesignationMasterPagePresenter.dealerMasterTableForm
    this.viewOrEditOrCreate()

  }

  private viewOrEditOrCreate() {
    if (this.dealerDesignationMasterPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.dealerDesignationMasterPagePresenter.operation === Operation.EDIT) {
        this.isEdit=true
        this. toGetDesignationId()
    } else {
      this.dealerDesignationMasterPagePresenter.operation === Operation.CREATE
      this.isCreate=true
    }
  }

  submitCustomerCreateForm() {

    if (this.createForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.dealerDesignationMasterPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  clearCustomerCreateForm() {
    this.createForm.reset()

  }

  toGetDesignationId(){
    this.activityRoute.queryParamMap.subscribe(param => {
      console.log('toGetDesignationId',param.get('id'));
      this.id=param.get('id')
    })
  }

  validateForm() {
    if (this.dealerDetails.status==='INVALID') {
      this.toastr.error('Please check Mandatory Fields')
      this.dealerDetails.markAllAsTouched()
    }
    // else if (this.dealerDetails.get('departmentCode').value===null || this.dealerDetails.get('departmentName').value===null || this.dealerDetails.get('department').value===null) {
      
    // }
    else{
      this.openConfirmDialog();
    }
  }


  submitData() {

    let submitData = {} as any
    let designationcode=this.dealerDetails.get('designationCode').value
    let designationName=this.dealerDetails.get('designationName').value
    let department=this.dealerDetails.get('department').value.id
    if (this.isEdit==true) {
      this.dealerDesignationMasterPageService.updateDesignation(department,designationcode,designationName,this.id).subscribe(res=>{
  
      })
    }else{
      submitData.designationcode=designationcode
      submitData.departmentId=department
      submitData.designation=designationName
  
      this.dealerDesignationMasterPageService.submitDealerDesignationMaster(submitData).subscribe(res=>{
  
      })
    }

  

  }


  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Dealer Designation Master?';
    if (this.isEdit) {
      message = 'Do you want to update Dealer Designation Master?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
        this.toastr.success(`Dealer Designation Master Created Successfully`, 'Success')
        this.router.navigate(['../'], { relativeTo: this.activityRoute })
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitData();
      }
    });
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  exit() {
     this.router.navigate(['../'], { relativeTo: this.activityRoute })
  }

}

