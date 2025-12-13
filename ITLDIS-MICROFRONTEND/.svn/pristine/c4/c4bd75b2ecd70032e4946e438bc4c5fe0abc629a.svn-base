import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { Observable, BehaviorSubject } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { ToastrService } from 'ngx-toastr';
import { ScrollToFormError } from '../../../../../utils/scroll-to-error';
import { PurchaseOrderCreatePageStore } from './purchase-order-create-page.store';
import { PurchaseOrderCreatePagePresenter } from './purchase-order-create-page.presenter';
import { PurchaseOrderCreatePageWebService } from './purchase-order-create-page-web.service';
import { SaveSparesPurchaseOrder, SparesPOPartDetails, SparePartMaster } from '../../domain/spares-purchase-order.domain';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-purchase-order-create-page',
  templateUrl: './purchase-order-create-page.component.html',
  styleUrls: ['./purchase-order-create-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [PurchaseOrderCreatePagePresenter, PurchaseOrderCreatePageStore, PurchaseOrderCreatePageWebService],
})
export class PurchaseOrderCreatePageComponent implements OnInit {
  public markAllAsTouchedObserv: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public itemDetailsForm: Observable<Array<FormGroup>>;
  private dialogMessage: string = 'save';
  public partOrderingForm: FormGroup;
  public isDraft: boolean = true;
  public poTotalForm: FormGroup;
  public editRecordId: number;
  private searchFilter: string;
  public isEdit: boolean;
  public isApprove:boolean;
  public isView: boolean;
  public tcsPerc:number;
  public orderType:string;
  public showApproval:boolean = false;
  public approvalHierDetails : any;
  isSubmitDisable:boolean = false;
  hideAddrowandUploadExcel:boolean=true;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private toasterService: ToastrService,
    private activatedRoute: ActivatedRoute,
    private purchaseOrderCreatePagePresenter: PurchaseOrderCreatePagePresenter,
    private purchaseOrderCreatePageWebService: PurchaseOrderCreatePageWebService,
  ) {
    this.poTotalForm = this.purchaseOrderCreatePagePresenter.getPoTotalForm;
    this.partOrderingForm = this.purchaseOrderCreatePagePresenter.getPartOrderingForm;
    this.itemDetailsForm = this.purchaseOrderCreatePagePresenter.getItemDetailsFormObservable;
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(paramsMap => {
      
      // console.log('Kanhaiya--',atob(paramsMap.get('approveId')))
      if (paramsMap && Object.keys(paramsMap['params']).length > 0) {
        
        this.searchFilter = paramsMap.get('searchFilter');
        if (paramsMap && paramsMap.get('viewId') && paramsMap.get('orderType')) {
          this.isView = true;
          this.editRecordId = parseInt(atob(paramsMap.get('viewId')));
          // console.log("editRecordId--->", this.editRecordId);
          this.getPoDetailsFromId(atob(paramsMap.get('viewId')), atob(paramsMap.get('orderType')));
          return
        }
        if (paramsMap && paramsMap.get('updateId') && paramsMap.get('orderType')) {
          
          this.isEdit = true;
          this.editRecordId = parseInt(atob(paramsMap.get('updateId')));
          // console.log("isEdit--->", this.editRecordId);
          this.getPoDetailsFromId(atob(paramsMap.get('updateId')), atob(paramsMap.get('orderType')));
        }
        if (paramsMap && paramsMap.get('approveId') && paramsMap.get('orderType')) {
            this.isApprove = true;
            this.editRecordId = parseInt(atob(paramsMap.get('approveId')));
            // console.log("isApprove--->", this.editRecordId);
            this.getPoDetailsFromId(atob(paramsMap.get('approveId')), atob(paramsMap.get('orderType')));
          }
        }
    })
  }
  private getPoDetailsFromId(poId: string, orderType: string) {
    this.purchaseOrderCreatePageWebService.getPoDetailsFromId(poId, orderType).subscribe(res => {
      if (res) {
        const poDetailsHeaders = res['result']['headerResponse'];
        this.isDraft = res['result']['headerResponse']['headerDetails']['draftFlag'];
        this.partOrderingForm.get('supplierType').disable();
        this.partOrderingForm.get('orderType').disable();
         this.partOrderingForm.get('orderPlanningNo').disable();
        this.orderType = poDetailsHeaders.orderType.orderType;
      
        
        this.purchaseOrderCreatePagePresenter.setValueToPartOrderForm({
          supplierType: poDetailsHeaders.headerDetails.supplierType,
          ...{ orderType: poDetailsHeaders.orderType },
        });

        this.poTotalForm.patchValue(poDetailsHeaders.headerDetails);
       
        this.partOrderingForm.get('orderPlanningNo').patchValue(poDetailsHeaders.headerDetails.opsNo);
        this.partOrderingForm.get('opSheetNoId').patchValue(poDetailsHeaders.headerDetails.opsId);
        // console.log(this.partOrderingForm,'dndn')
        this.tcsPerc = poDetailsHeaders.headerDetails['tcsPerc'];
        if(res['result']){
          res['result']['partDetails'].forEach((element: SparesPOPartDetails) => {
            // console.log(element,'element++')
            element.sparePartMaster = {} as SparePartMaster;
             if (element['sparePartMasterId']) {
               element.sparePartMaster.id = element['sparePartMasterId'];
                element.sparePartMaster.itemNo = element['itemNo'];
               } else {
                element.sparePartMaster.id = element['localPartMasterId'];
                element.sparePartMaster.itemNo = element['itemNo'];
            }
            // var twoPlacedFloat = parseFloat(yourString).toFixed(2)
            // console.log(parseFloat((element['gstAmount'])))
               element['gstAmount'] = parseFloat((element['gstAmount'])).toFixed(2)
               element['baseAmount'] = parseFloat((element['baseAmount'])).toFixed(2)
              element['totalAmount'] = parseFloat((element['totalAmount'])).toFixed(2)
              
              this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails(element);
          })
        }
        // if(res['result']){
        // res['result']['partDetails'].forEach((element: SparesPOPartDetails) => {
        
        //   element.sparePartMaster = {} as SparePartMaster;
        //   if (element['sparePartMasterId']) {
        //     element.sparePartMaster.id = element['sparePartMasterId'];
        //     element.sparePartMaster.itemNo = element['itemNo'];
        //   } else {
        //     element.sparePartMaster.id = element['localPartMasterId'];
        //     element.sparePartMaster.itemNo = element['itemNo'];
        //   }
        //   element['gstAmount'] = parseFloat(element['gstAmount'].toFixed(2))
        //   element['baseAmount'] = parseFloat(element['baseAmount'].toFixed(2))
        //   element['totalAmount'] = parseFloat(element['totalAmount'].toFixed(2))
          
        //   this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails(element);
        // });
      // }
      // code commented by ANkit Rai 21-03-204
      const gstTotalAmount = res['result']['partDetails'].reduce((initialValue, element) => {
        initialValue = initialValue +(element.gstAmount); // Convert to number
        return initialValue;
    }, 0);
    //     // console.log(gstTotalAmount,'gstToTalAmount')
    //     this.poTotalForm.get('totalGstAmount').patchValue(gstTotalAmount);
    //   const gstTotalAmount = res['result']['partDetails'].reduce((initialValue, element) => {
    //     initialValue = initialValue + parseFloat(element.gstAmount); // Convert to number
    //     return initialValue;
    // }, 0);
    //     // console.log(gstTotalAmount,'gstToTalAmount')
    //     this.poTotalForm.get('totalGstAmount').patchValue(gstTotalAmount);
        if (this.isView || this.isApprove) {
          this.purchaseOrderCreatePagePresenter.getCreatePOForm().disable();
          this.partOrderingForm.get('freightBorneBy').disable();
        }
        poDetailsHeaders.coDealerMaster = {'id':poDetailsHeaders.headerDetails['dealerId'],'dealerCode':poDetailsHeaders.headerDetails['dealerCode']}
        // console.log('poDetailsHeaders.coDealerMaster', poDetailsHeaders.coDealerMaster);
        
        this.purchaseOrderCreatePagePresenter.setValueToPartOrderForm({
          ...poDetailsHeaders.headerDetails,
          ...{ supplierName: res['result']['supplierName'] },
          ...{ coDealerMaster: poDetailsHeaders.coDealerMaster },
          ...{serviceJobCard : {jobcardNo:poDetailsHeaders.headerDetails['serviceJobCard'],id:poDetailsHeaders.headerDetails['jobCardId']}},
          ...poDetailsHeaders.coDealerMaster
        });

        if(this.orderType === 'KAI Machine Down Order' && !this.isDraft){
            this.showApproval = true;
            this.approvalHierDetails = res['result']['approvalResponse'];
        }else{
            this.showApproval = false;
        }
      }
    })
  }

  public validateForm(draftFlag: boolean) {
  
    ScrollToFormError.scrollToError();
    if (typeof this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('serviceJobCard').value=='string') {
      this.toasterService.error('Please enter Valid Job Card Number')
      this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('serviceJobCard').setErrors({
        serviceJobCard:''
      })
      return
    }
    this.purchaseOrderCreatePagePresenter.getCreatePOForm().markAllAsTouched();
    this.markAllAsTouchedObserv.next(true);
    if (this.purchaseOrderCreatePagePresenter.getCreatePOForm().valid) {
      this.openConfirmationModal(draftFlag);
    }else{
      this.toasterService.error('Please fill mandatory fields')
    }
  }
  private saveEditPo(finalData: SaveSparesPurchaseOrder) {
    this.purchaseOrderCreatePageWebService.saveAndEditPo(finalData).subscribe(res => {
     if (res) {
        this.toasterService.success('Spares PO saved successfully !', 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.isSubmitDisable = false;
        this.toasterService.error('Error genearated while saving','Transaction failed')
      }
    },error => {
        this.isSubmitDisable = false;
        this.toasterService.error('Error genearated while saving','Transaction failed')
    })
  }
  private openConfirmationModal(draft: boolean) {
    this.dialogMessage = draft ? 'save' : 'submit';
    const message = `Are you sure you want to ${this.dialogMessage}?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
        this.isDraft = draft as boolean;
        const formValues = this.purchaseOrderCreatePagePresenter.getCreatePOForm().getRawValue();
        // console.log(formValues,'formValues')
        const finalObject = this.purchaseOrderCreatePageWebService.createSavePoObject(formValues, this.isDraft, this.editRecordId);
        // console.log(finalObject,'finalObject@@@@@')
        this.saveEditPo(finalObject);
      }
    })
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  public clearForm() {
    this.purchaseOrderCreatePagePresenter.resetPartsOrderingForm();
    this.poTotalForm.reset();
    this.purchaseOrderCreatePagePresenter.getItemDetailsForm.clear();
  }
  public goBack() {
    this.router.navigate(['../'], { relativeTo: this.activatedRoute, queryParams: { searchFilter: this.searchFilter } })
  }
  
  public approvalForm(flag){
      this.openConfirmDialog(flag);
  }
  
  approve(remark, flag) {
      this.purchaseOrderCreatePageWebService.approveSpareOrder(remark, this.editRecordId+"", flag).subscribe(res => {
        if(res){
          this.toasterService.success(res['message'], 'Success');
          this.router.navigate(['../'], { relativeTo: this.activatedRoute })
        }else {
          this.isSubmitDisable = false;
          this.toasterService.error('Error genearated while saving','Transaction failed')
        }
      }, err => {
        this.isSubmitDisable = false;
        this.toasterService.error('Error genearated while saving','Transaction failed')
      })
    }

    private openConfirmDialog(flag): void | any {
      let message = `Do you want to ${flag} PO`;
      const confirmationData = this.setConfirmationDialogData(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });

      dialogRef.afterClosed().subscribe((result:DialogResult) => {
          if (result.button === 'Confirm') {
              this.isSubmitDisable = true;
              this.approve(result.remarkText, flag);
           }
      });
    }
    private setConfirmationDialogData(message: string) {
      const confirmationData = {} as ConfirmDialogData;
      confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Cancel', 'Confirm'];
      confirmationData.remarkConfig = {
              source: Source.APPROVE
          }
      return confirmationData;
    }
    
    viewPrint(printStatus:string){
        this.purchaseOrderCreatePageWebService.printPO(this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('purchaseOrderNumber').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
            if (resp) {
                let headerContentDispostion = resp.headers.get('content-disposition');
                let fileName = headerContentDispostion.split("=")[1].trim();
                const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
                saveAs(file);
              }
         })
     }

     showaddRowandupload(event:any){
      // console.log(event,'event')
      if(event===false){
        this.hideAddrowandUploadExcel=true;

      }else{
        this.hideAddrowandUploadExcel=false;
      }
     }
    
}
