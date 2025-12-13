import { Component, OnInit } from '@angular/core';
import { PartRequisitionPagePresenter } from './part-requisition-page.presenter';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { PartRequisitionPageApiService } from './part-requisition-page-api.service';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { PartRequisitionForm, SparePartRequisitionItem, PartRequisition } from '../../domain/part-requisition.domain';
import { ObjectUtil } from '../../../../../utils/object-util';


@Component({
  selector: 'app-part-requisition-page',
  templateUrl: './part-requisition-page.component.html',
  styleUrls: ['./part-requisition-page.component.scss'],
  providers: [PartRequisitionPagePresenter, PartRequisitionPageApiService]
})
export class PartRequisitionPageComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  partRequisitionStatus: string;
  isSubmitDisable: boolean = false;
  constructor(
    public dialog: MatDialog,
    public partRequisitionPagePresenter: PartRequisitionPagePresenter,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private partRequisitionPageApiService: PartRequisitionPageApiService
  ) { }

  ngOnInit() {
    this.checkIsGrnForEditOrView();
  }
  private checkIsGrnForEditOrView() {
    this.activatedRoute.paramMap.subscribe(param => {
      if (param.has('viewId')) {
        this.isView = true;
        this.partRequisitionPagePresenter.partRequisitionForm.get('id').patchValue(atob(param.get('viewId')));
        this.getPartRequisitionById(parseInt(atob(param.get('viewId'))));
        // this.sparesGrnPagePresenter.disable();
      }
      if (param.has('updateId')) {
        this.isEdit = true;
        this.partRequisitionPagePresenter.partRequisitionForm.get('id').patchValue(atob(param.get('updateId')));
        this.getPartRequisitionById(parseInt(atob(param.get('updateId'))));
      }
    })
  }
  getPartRequisitionById(partRequisitionById:number){
    this.partRequisitionPageApiService.getPartRequisitionById(partRequisitionById).subscribe(partRequisitionRes=>{
      this.partRequisitionPagePresenter.partRequisitionForm.patchValue(partRequisitionRes.sparePartRequisition);
      partRequisitionRes.sparePartRequisitionItem.forEach(requisitionItem => {
        if (typeof requisitionItem.itemNo === 'string') {
          requisitionItem['sparePartMaster'] = {id:requisitionItem.sparePartId, itemNo:requisitionItem.itemNo}
        }
        this.partRequisitionPagePresenter.addNewRowInItemDetails(requisitionItem);
      });
      //this.partRequisitionPagePresenter.sparePartRequisitionItemControl.patchValue(partRequisitionRes.sparePartRequisitionItem);
      
      if (this.isView) {
        this.partRequisitionPagePresenter.partRequisitionPageForm.disable();
      }
    })
  }
  submitPartRequisition(){
    this.partRequisitionStatus = 'Submitted';
    this.validateForm();
  }
  savePartRequisition(){
    this.partRequisitionStatus = 'Draft';
    this.validateForm();
  }
  validateForm() {
    const valid = this.partRequisitionPagePresenter.validate();
    if (valid) {
      this.openConfirmDialog();
    }
  }

  submitData() {
    const partRequisitionRow = this.partRequisitionPagePresenter.partRequisitionPageForm.getRawValue() as PartRequisitionForm;
    let items = partRequisitionRow.sparePartRequisitionItem as SparePartRequisitionItem[];
    
    items.forEach(item => {
        item.sparePartMaster = {'id':item['itemNo']['id'], 'itemNo':item['itemNo']['itemNo']},
        item.reqQuantity = item['requisitionQty'];
        delete item['requisitionQty'];
        delete item['itemNo'];
        delete item['itemDescription'];
        delete item['isSelected'];
    });
    
    const partRequisition = {
      ...partRequisitionRow.partRequisition, 
      sparePartRequisitionItem: items,
      partRequisitionStatus:this.partRequisitionStatus,
      type:'APR'
    }
    if (typeof partRequisitionRow.partRequisition.requisitionPurpose ==='object') {
      partRequisition.requisitionPurpose = partRequisitionRow.partRequisition.requisitionPurpose.value as string;
    }
    ObjectUtil.removeNulls(partRequisition);
    this.partRequisitionPageApiService.savePartRequisition(partRequisition).subscribe(res=>{
      if(res){
        if (this.isEdit) {
          this.toastr.success('Part requisition Updated Successfully', 'Success');
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
          return;
        }
        this.toastr.success('Part requisition Saved Successfully', 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction failed');
      }
    }, err => {
      this.isSubmitDisable = false;
      if (this.isEdit) {
        this.toastr.error('Part requisition Update has Error', 'Error');
        return;
      }
      this.toastr.error('Part requisition Save has Error', 'Error');
    });
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Part Requisition?';
    if (this.isEdit) {
      message = 'Do you want to update Part Requisition?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  clearAllSubForm() {
    this.partRequisitionPagePresenter.reset()
    // this.grnForm.updateValueAndValidity();
  }
  back() {
    if (this.isEdit || this.isView) {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.router.navigate(['../'], { relativeTo: this.activatedRoute });
  }

}
