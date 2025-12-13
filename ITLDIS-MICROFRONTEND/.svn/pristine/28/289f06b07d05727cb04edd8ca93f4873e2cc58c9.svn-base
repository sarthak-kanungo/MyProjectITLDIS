import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormArray } from '@angular/forms';
import { SparesGrnPagePresenter } from './spares-grn-page.presenter';
import { Observable, Subject, BehaviorSubject, of } from 'rxjs';
import { SpareGrnPageAdapter } from './spare-grn-page.adapter';
import { SpareGrnPageApiService } from './spare-grn-page-api.service';
import { SaveSpareGrn ,sparePartGrnItem} from '../../domain/spare-grn.domain';
import { DateService } from '../../../../../root-service/date.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SparesGrnApiService } from '../spares-grn/spares-grn-api.service';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { OperationsUtil } from 'src/app/utils/operation-util';

@Component({
  selector: 'app-spares-grn-page',
  templateUrl: './spares-grn-page.component.html',
  styleUrls: ['./spares-grn-page.component.scss'],
  viewProviders: [SparesGrnPagePresenter],
  providers: [SpareGrnPageAdapter, SpareGrnPageApiService, SparesGrnApiService],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SparesGrnPageComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  goodsReceiptNoteForm: FormGroup;
  grnForm: FormGroup;
  itemDetail$: Observable<FormArray>;
  itemDetailTotalForm: FormGroup;
  private grnStatus: string;
  binLocationValid: boolean;
  isSubmitDisable: boolean = false;
  private markForCheck$ = new BehaviorSubject<boolean>(undefined);
  public get getMarkForCheck$(): Observable<boolean> {
    return this.markForCheck$.asObservable();
  }
  private grnSubmitType$ = new BehaviorSubject<string>(undefined);
  public get getGrnSubmitType$(): Observable<string> {
    return this.grnSubmitType$.asObservable();
  }

  constructor(
    public dialog: MatDialog,
    public sparesGrnPagePresenter: SparesGrnPagePresenter,
    private spareGrnPageAdapter: SpareGrnPageAdapter,
    private spareGrnPageApiService: SpareGrnPageApiService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.grnForm = this.sparesGrnPagePresenter.grnForm;
    this.itemDetail$ = this.sparesGrnPagePresenter.getItemDetail$;
    this.itemDetailTotalForm = this.sparesGrnPagePresenter.itemDeatailTotalForm;
    // this.spareGrnPageApiService.getServerDate().subscribe(sevrDate => {
    //   this.grnForm.get('grnDate').patchValue(sevrDate.result);
    //   this.grnForm.get('goodsReceiptDate').patchValue(sevrDate.result);
    // });
    this.sparesGrnPagePresenter.operation = OperationsUtil.operation(this.activatedRoute)
    this.checkIsGrnForEditOrView()
  }
  checkIsGrnForEditOrView() {
    this.activatedRoute.paramMap.subscribe(param => {
      if (param.has('viewId')) {
        this.isView = true;
        this.getSpareGrnByGrnId(parseInt(atob(param.get('viewId'))));
        this.sparesGrnPagePresenter.disable();
      }
      if (param.has('updateId')) {
        this.isEdit = true;
        this.grnForm.get('id').patchValue(atob(param.get('updateId')));
        this.getSpareGrnByGrnId(parseInt(atob(param.get('updateId'))));
      }
    })
  }
  private getSpareGrnByGrnId(grnId: number) {  
    this.spareGrnPageApiService.getSpareGrnByGrnId(grnId).subscribe(grnData => {
      this.sparesGrnPagePresenter.patchValue(grnData, { isEdit: true });
      if (this.isView) {
        this.sparesGrnPagePresenter.disable();
      }
    });
  }
  saveGrnAsDraft() {
    // this.sparesGrnPagePresenter.setBinLocationRequired = false;
    this.grnSubmitType$.next('Draft')
    this.validateForm(false)
  }
  checkForGrnSubmit() {
    this.grnSubmitType$.next('Submitted');
    this.saveGrnAsSubmitted(true);
  }
  saveGrnAsSubmitted(isValidate?: boolean) {
    
    if (isValidate) {
      this.validateForm(true)
    }
    // this.sparesGrnPagePresenter.setBinLocationRequired = true;
  }
  private validateForm(isBinLocationRequired = false) {
    
    const isvalid = this.sparesGrnPagePresenter.validateGrn(isBinLocationRequired)/* .subscribe(res => {
      
      if (res !== 'INVALID' && res !== 'PENDING') {
        this.openConfirmDialog();
        subscription.unsubscribe();
      }
    }, reject => subscription.unsubscribe()
    ); */
    this.markForCheck$.next(true);
    // 
    // this.sparesGrnPagePresenter.goodsReceiptNoteForm.statusChanges.subscribe(res => {
    //   
    // })
    const saveGrn = this.spareGrnPageAdapter.saveAdapt<SaveSpareGrn>(this.sparesGrnPagePresenter.getRowValueOfGoodsReceiptNoteForm);
    let flag:boolean =  true;
    saveGrn.sparePartGrnItems.forEach(item => {
      console.log("item--->", item)
      console.log("item.netReceivedQty--->", item.netReceivedQty)
      if(item.netReceivedQty == null || item.netReceivedQty == undefined){
          this.toastr.warning("Enter Received Qty");
          flag = false;
          return;
      } else  if(item.binLocation == null || item.binLocation.binLocation ==''){ 
        // if (this.binLocationValid) {
        //   if (isvalid) {
        //     this.openConfirmDialog();
        //   }
        // }else{
        //   this.toastr.warning("Invalid Bin Location.")
        // }
        /* commented by Vinay bcoz binLocationValid is declear bt never user so it is Undefined */

         
          this.toastr.warning("Invalid Bin Location.")
          flag = false;
          return;
          
      }
    });
    if(isvalid && flag){
      this.openConfirmDialog();
    }
    
  }

  // private submitGrn() {
  //   const saveGrn = this.spareGrnPageAdapter.saveAdapt<SaveSpareGrn>(this.sparesGrnPagePresenter.getRowValueOfGoodsReceiptNoteForm);
  //   saveGrn.grnStatus = this.grnStatus;
  //   saveGrn.draftFlag = false;
  //   this.spareGrnPageApiService.saveGrn(saveGrn).subscribe(res => {
  //   });
  // }
  private saveGrn() {
    const saveGrn = this.spareGrnPageAdapter.saveAdapt<SaveSpareGrn>(this.sparesGrnPagePresenter.getRowValueOfGoodsReceiptNoteForm);
    saveGrn.grnStatus = this.grnSubmitType$.value;
    saveGrn.draftFlag = this.grnSubmitType$.value === 'Draft' ? true : false;
    saveGrn.sparePartGrnItems.forEach(item => {
        if(item.binLocation!=null && typeof item.binLocation !== 'object' && item.binLocation!=''){
            item.binLocation = {id:null, binLocation:item.binLocation};
        }
        if(saveGrn.sparePurchaseOrder){
            item.sparePurchaseOrder = {id:saveGrn.sparePurchaseOrder['id']}
        }
    });
    
    this.spareGrnPageApiService.saveGrn(saveGrn).subscribe(res => {
      if(res){
        if (this.isEdit) {
          this.toastr.success('GRN Updated Successfully', 'Success');
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
          return;
        }
        this.toastr.success('GRN Saved Successfully', 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, err => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving','Transaction failed');
    });
 
    
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Goods Receipt Note?';
    if (this.isEdit) {
      message = 'Do you want to update Goods Receipt Note?';
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
        this.saveGrn();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.saveGrn();
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
    this.sparesGrnPagePresenter.reset();
  }
  back() {
    if (this.isEdit || this.isView) {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.router.navigate(['../'], { relativeTo: this.activatedRoute });
  }
  
  viewPrint(printStatus:string){
      this.spareGrnPageApiService.printGrn(this.grnForm.get('grnNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
  
  viewBinningSlip(printStatus:string){
      this.spareGrnPageApiService.printBinningSlip(this.grnForm.get('grnNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
}
