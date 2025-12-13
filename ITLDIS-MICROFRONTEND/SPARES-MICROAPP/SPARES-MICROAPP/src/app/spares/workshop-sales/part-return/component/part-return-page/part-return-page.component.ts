import { Component, OnInit } from '@angular/core';
import { PartReturnPageApiService } from './part-return-page-api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { FormArray, FormGroup, AbstractControl } from '@angular/forms';
import { SelectList } from '../../../../../core/model/select-list.model';
import { PartReturnPagePresenter } from './part-return-page-presenter';
import { PartReturnForm, PartReturnItem, PartReturn } from '../../domain/part-return.domain';
import { ObjectUtil } from '../../../../../utils/object-util';

@Component({
  selector: 'app-part-return-page',
  templateUrl: './part-return-page.component.html',
  styles: [``],
  providers:[PartReturnPagePresenter, PartReturnPageApiService]
})
export class PartReturnPageComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  partReturnStatus: string;
  itemDetailsTable: AbstractControl;
  partReturnForm:FormGroup;
  itemDetailList: any;
  // itemDetailTableConfig: ItemDetailTableConfig;
  advancedSparePartReturn: SelectList;
  isSubmitDisable:boolean = false;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute,
    private partReturnPagePresenter:PartReturnPagePresenter,
    private partReturnPageApiService:PartReturnPageApiService
  ) { }

  ngOnInit() {
    this.checkIsGrnForEditOrView();
    this.itemDetailsTable = this.partReturnPagePresenter.partReturnItemFormArray;
    this.partReturnForm = this.partReturnPagePresenter.partReturnForm;
    // this.itemDetailTableConfig = new ItemDetailTableConfig(this.partReturnPagePresenter.createItemDetailsTableRowFn);
  }
  private checkIsGrnForEditOrView() {
    this.activatedRoute.paramMap.subscribe(param => {
      if (param.has('viewId')) {
        this.isView = true;
        // this.partReturnPagePresenter.partReturnForm.get('id').patchValue(param.get('viewId'));
        this.getPartReturnById(parseInt(atob(param.get('viewId'))));
      }
      if (param.has('updateId')) {
        this.isEdit = true;
        // this.partReturnPagePresenter.partReturnForm.get('id').patchValue(param.get('updateId'));
        this.getPartReturnById(parseInt(atob(param.get('updateId'))));
      }
    })
  }
  getPartReturnById(partReturnById: number) {
     this.partReturnPageApiService.getPartReturnById(partReturnById).subscribe(partReturnRes=>{
       
       this.partReturnPagePresenter.partReturnForm.patchValue(partReturnRes['result']['headerData']);
       if(this.partReturnPagePresenter.partReturnForm.controls.serviceJobCard.value){
           this.partReturnPagePresenter.partReturnForm.controls.requisitionType.patchValue('Job Card');
           
       }else{
           this.partReturnPagePresenter.partReturnForm.controls.requisitionType.patchValue('APR');
       }
       
       partReturnRes['result']['lineItems'].forEach(issueItem => {
         if (typeof issueItem.itemNo === 'string') {
           issueItem['sparePartMaster'] = {id:issueItem.sparePartId, itemNo:issueItem.itemNo}
         }
         this.partReturnPagePresenter.addRow(issueItem);
       });
       if (this.isView) {
         this.partReturnPagePresenter.partReturnPageForm.disable();
       }
     })
  }
  submitPartReturn() {
    this.partReturnPagePresenter.partReturnForm.get('partReturnStatus').patchValue('Submitted');
    this.validateForm();
  }
  savePartReturn() {
    this.partReturnPagePresenter.partReturnForm.get('partReturnStatus').patchValue('Draft');
    this.validateForm();
  }
  validateForm() {
    const valid = this.partReturnPagePresenter.validate();
    if (valid) {
      this.openConfirmDialog();
    }
  }

  submitData() {
    let partReturnRow = this.partReturnPagePresenter.partReturnPageForm.getRawValue() as PartReturnForm;
    partReturnRow.partReturn['dealerEmployeeMaster']={'id':partReturnRow.partReturn['partReceivedById']};
    const partReturn:PartReturn = {
      ...partReturnRow.partReturn,
      sparePartReturnItems: partReturnRow.partReturnItem as PartReturnItem[]
    };
    // if (typeof partReturnRow.partReturn.issuePurpose ==='object') {
    //   partReturn.issuePurpose = partReturnRow.partReturn.issuePurpose.value as string;
    // }
    ObjectUtil.removeNulls(partReturn);
    
    // return
    this.partReturnPageApiService.saveSparePartReturn((partReturn as any)).subscribe(res => {
      if(res){
        if (this.isEdit) {
          this.toastr.success('Part issue Updated Successfully', 'Success');
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
          return;
        }
        this.toastr.success('Part issue Saved Successfully', 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed')
      }
    }, err => {
      this.isSubmitDisable = false;
      if (this.isEdit) {
        this.toastr.error('Part issue Update has Error', 'Error');
        return;
      }
      this.toastr.error('Part issue Save has Error', 'Error');
    });
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Part Return?';
    if (this.isEdit) {
      message = 'Do you want to update Part Return?';
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
    this.partReturnPagePresenter.reset()
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
