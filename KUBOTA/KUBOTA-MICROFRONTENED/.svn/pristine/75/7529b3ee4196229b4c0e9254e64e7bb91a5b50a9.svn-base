import { Component, OnInit } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { DealerDepartmentMasterPageStore } from './dealer-department-master-page.store';
import { DealerDepartmentMasterPagePresenter } from './dealer-department-master-page.presenter';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { OperationsUtil, Operation } from 'src/app/utils/operation-util';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { SubmitDto } from '../../../party-master/domain/party-master-domain';
import { DealerDepartmentMasterPageService } from './dealer-department-master-page.service';


@Component({
  selector: 'app-dealer-department-master-page',
  templateUrl: './dealer-department-master-page.component.html',
  styleUrls: ['./dealer-department-master-page.component.scss'],
  providers: [DealerDepartmentMasterPagePresenter, DealerDepartmentMasterPageStore, DealerDepartmentMasterPageService]
})
export class DealerDepartmentMasterPageComponent implements OnInit {

  createForm: FormGroup
  dealerDetails: FormGroup
  isView: boolean;
  isEdit: boolean;
  isCreate: boolean;

  id:any

  constructor(
    private dealerDepartmentMasterPagePresenter: DealerDepartmentMasterPagePresenter,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private dealerDepartmentMasterPageService: DealerDepartmentMasterPageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.dealerDepartmentMasterPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.createForm = this.dealerDepartmentMasterPagePresenter.createDealerDepartmentMasterForm
    this.dealerDetails = this.dealerDepartmentMasterPagePresenter.dealerMasterCreateForm
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.dealerDepartmentMasterPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    } else if (this.dealerDepartmentMasterPagePresenter.operation === Operation.EDIT) {
      this.isEdit=true
      this.toGetDepartmentId()
    } else {
      this.dealerDepartmentMasterPagePresenter.operation === Operation.CREATE
      this.isCreate=true
    }
  }

  submitCustomerCreateForm() {

    if (this.createForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.dealerDepartmentMasterPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  clearCustomerCreateForm() {
    this.createForm.reset()
  }

  toGetDepartmentId(){
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
    // else if (this.dealerDetails.get('departmentCode').value===null || this.dealerDetails.get('departmentName').value===null) {
      
    // }
    else{
      this.openConfirmDialog();
    }
  }

  submitData() {
    
    let departmentCode= this.dealerDetails.get('departmentCode').value
    let departmentname= this.dealerDetails.get('departmentName').value

    if (this.isEdit==true) {
      this.dealerDepartmentMasterPageService.updateDepartment(departmentCode,departmentname,this.id).subscribe(res=>{

      })
    }

    else{
      let submitData = {} as any
      submitData.departmentName=departmentname
      submitData.departmentCode=departmentCode
      this.dealerDepartmentMasterPageService.submitDealerDepartmentMaster(submitData).subscribe(res=>{
      })
    }
    
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Dealer Department Master?';
    if (this.isEdit) {
      message = 'Do you want to update Dealer Department Master?';
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
        this.toastr.success(`Dealer Department Master Created Successfully`, 'Success')
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
