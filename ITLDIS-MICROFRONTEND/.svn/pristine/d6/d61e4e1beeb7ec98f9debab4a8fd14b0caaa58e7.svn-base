import { FormGroup } from '@angular/forms';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { Component, OnInit, OnChanges, ChangeDetectionStrategy, Input, ChangeDetectorRef } from '@angular/core';
import { PurchaseOrderCreatePagePresenter } from '../purchase-order-create-page/purchase-order-create-page.presenter';
import { ItemDetailsWebService } from './item-details-web.service';
import { SearchAutocomplete } from 'CommonSupportDto';
import { ToastrService } from 'ngx-toastr';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { MatDialog, MatDialogConfig  } from '@angular/material';
import { ModalFileUploadComponent } from '../modal-file-upload/modal-file-upload.component';
import { ItemErrorReportComponent } from '../item-error-report/item-error-report.component';
import { UploadableFile } from 'kubota-file-upload';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { SparesPOPartDetails } from '../../domain/spares-purchase-order.domain'
import { PurchaseOrderCreatePageStore } from '../purchase-order-create-page/purchase-order-create-page.store';
import { CustomValidators, ValidateList } from '../../../../../utils/custom-validators';
@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [ItemDetailsWebService]
})
export class ItemDetailsComponent implements OnInit, OnChanges {
  @Input() public poItemDetailForm: Observable<Array<FormGroup>>;
  @Input() markAllAsTouchedObserv: BehaviorSubject<boolean>;
  @Input() public poTotalForm: FormGroup;
  @Input() public isView: boolean;
  @Input() public isApprove: boolean;
  @Input() public isEdit: boolean;
  @Input() public tcsPerc:number;
  @Input() public hideAddrowandUploadExcel:any;
  public excelDownload : boolean = true;
  hideAddButton:boolean

  public filteredItemNumberList: Observable<(string | object)[]>;
  private itemDetailsArray: Array<FormGroup> = [];
  public tableTitle: string[] = [];
  @Input() public tcsPercList = [];
  
  constructor(
    private purchaseOrderCreatePagePresenter: PurchaseOrderCreatePagePresenter,
    private itemDetailsWebService: ItemDetailsWebService,
    private _changeDetectorRef: ChangeDetectorRef,
    private toasterService: ToastrService,
    private matDialog : MatDialog
  ) { }

  ngOnChanges(){
     /* if ((this.isEdit || this.isView) && this.tcsPerc) {
          this.poTotalForm.controls.tcsPerc.patchValue(this.tcsPerc);
          
      }*/

      // commented by AU
      // this.poTotalForm.controls.tcsPerc.patchValue(this.tcsPerc+"");
      this.disabledQtyOnMDO()
      // console.log(this.hideAddrowandUploadExcel,'@@@@@@@@@@formChild')
  }
  ngOnInit() {
    this.tableTitle = ['Select', 'Item No.', 'Item Description', 'Qty', 'Current Stock',
      'Back Order at KAI', 'Transit From KAI', 'Unit Price', 'Base Amount', 'GST %',
      'GST Amount', 'Total Amount', 'ACCPAC order No.']
    
      // Commented by AU
    // this.itemDetailsWebService.tcsPercList().subscribe(response => {
    //     this.tcsPercList = response['result'];
    //     if(this.tcsPerc){
    //         this.poTotalForm.controls.tcsPerc.patchValue(this.tcsPerc+"");  
    //     }
    // })

    // Implemented by AU
    this.itemDetailsWebService.tcsPercentageList().subscribe(response => {
      // this.tcsAmount = response['result'].tcsPercent
      this.poTotalForm.controls.tcsPerc.setValue(response['result'].tcsPercent)
    })
    
    if (!this.isEdit && !this.isView && !this.isApprove) {
      this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails();      //Adding one default row while creating the form
    }
    
    this.totalPOAmountValueChanges();
    this.markAllAsTouchedObserv.subscribe((value: boolean) => {
      if (value) {
        this._changeDetectorRef.markForCheck();
      }
    })
    this.poItemDetailForm.subscribe((res: Array<FormGroup>) => {
      this.itemDetailsArray = res as Array<FormGroup>
      
      if (res) {
        this.itemDetailsArray.forEach( fg => {
          
          if(!this.isView){
            fg.get('quantity').setValidators(CustomValidators.moq(fg.get('moq'), "Quantity should be multiple of MOQ :"+fg.get('moq').value));
            
          }
          if(this.isView){
            fg.get('quantity').clearValidators();
            fg.get('quantity').updateValueAndValidity();
          }
           /*  added by vinay to remove error in case of view and quantity is Zero*/
        });  
        const newlyAddedFormGroup = this.itemDetailsArray[this.itemDetailsArray.length - 1]
        this.itemNumberValueChanges(newlyAddedFormGroup);
        this.quantityValueChanges(newlyAddedFormGroup);
      }
    })
    
    this.purchaseOrderCreatePagePresenter.orderTypeChangeEvent.subscribe(orderType =>{
       if(orderType && orderType.toLowerCase() === 'kai machine down order'){
           this.excelDownload = false;
           this.hideAddButton=true
       } else{
           this.excelDownload = true;
           this.hideAddButton=false
       }
    });
  }

  disabledQtyOnMDO(){
    this.purchaseOrderCreatePagePresenter.orderTypeChangeEvent.subscribe(orderType =>{
      if(orderType && orderType.toLowerCase() === 'kai machine down order'){
          this.hideAddButton=true
          this.poItemDetailForm.forEach(element=>{
            element.forEach(val=>{
             val.get('quantity').disable()
            })
          })
          
      } else if (orderType && orderType.toLowerCase() !== 'kai machine down order'){
          this.hideAddButton=false
          this.poItemDetailForm.forEach(element=>{
           element.forEach(val=>{
            val.get('quantity').enable()
            val.get('quantity').clearValidators();
            val.get('quantity').updateValueAndValidity();
           })
         })
      }
   });
  }

  private totalPOAmountValueChanges() {
    this.poTotalForm.get('totalPOAmount').valueChanges.subscribe((poTotal: number) => {
      if (poTotal) {
        this.purchaseOrderCreatePagePresenter.sendTotalPOAmountToKaiOutStanding.next(poTotal);
      }
    })
  }
  private itemNumberValueChanges(newFormGroup: FormGroup) {
    newFormGroup.get('sparePartMaster').valueChanges.subscribe(value => {
    //newFormGroup.get('quantity').patchValue('0');  
      if(!this.isView){  
          if(this.purchaseOrderCreatePagePresenter.getPartOrderingForm.controls.orderType.value === null){
              this.toasterService.warning('Please select Order Type');
              return false;
          }else if(this.purchaseOrderCreatePagePresenter.getPartOrderingForm.controls.orderType.value.orderType === 'Co-Dealer Order' && this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('priceType').value === null){
              this.toasterService.warning('Please select Price Type');
              return false;
          }   
      }
      if (value && value!='') {
          const itemDetailsIdArray = [];
          this.itemDetailsArray.forEach((element: FormGroup) => {
            if (typeof element.get('sparePartMaster').value === 'object' && element.get('sparePartMaster').value) {
              itemDetailsIdArray.push(element.get('sparePartMaster').value.id);
            }
          });
          if (typeof value !== 'object') {
            const orderType = this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('orderType').value.orderType;
            this.filteredItemNumberList = this.itemDetailsWebService.searchByItemNumberAuto(value, itemDetailsIdArray.join(), orderType);
          }
        }   
       
    })
  }
  displayItemNumberFn(itemNoObj: SearchAutocomplete) {
    return itemNoObj ? itemNoObj['itemNo'] : undefined
  }
  private quantityValueChanges(newFormGroup: FormGroup) {
    newFormGroup.get('quantity').valueChanges.subscribe(value => {
      /*let moq:number = newFormGroup.getRawValue().moq;
      if(moq ==null || moq ==0){
          moq = 1;
      }  */
      if (value && value !== null) {
          /*let qty:number = parseInt(value);
          if(qty%moq != 0){
              this.toasterService.warning("Quantity should be multiple of MOQ i.e. "+moq);
              if(qty<moq)
                qty = moq;
              else       
                qty = moq * parseInt((qty/moq)+"");
              newFormGroup.get('quantity').patchValue(qty);
          }*/
          this.calculateRowAndTotalPoValue(newFormGroup);
          return
      }
      newFormGroup.get('baseAmount').patchValue(typeof newFormGroup.getRawValue().unitPrice === 'string' ? parseInt(newFormGroup.getRawValue().unitPrice) : newFormGroup.getRawValue().unitPrice)
      return;
    })
  }
  public moqCheck(targetevent){
      // console.log(targetevent)
  }
  private calculateRowAndTotalPoValue(newFormGroup: FormGroup) {
    const unitPrice = typeof newFormGroup.getRawValue().unitPrice === 'string' ? parseInt(newFormGroup.getRawValue().unitPrice) : newFormGroup.getRawValue().unitPrice;
    this.calculateBaseAmount(newFormGroup, unitPrice);
    this.calculateGstAmount(newFormGroup);
    this.calculateTotalPoAmountWithGstAndBase();
  }
  private calculateBaseAmount(newForm: FormGroup, unitPrice: number) {
    // newForm.get('baseAmount').patchValue(((unitPrice) * parseInt(newForm.getRawValue().quantity)))     /* comment and toFixed(2) added by vinay */
    newForm.get('baseAmount').patchValue(((unitPrice) * parseInt(newForm.getRawValue().quantity)).toFixed(2))
  }

  private calculateGstAmount(newForm: FormGroup) {
    const gstPercent = typeof newForm.getRawValue().gstPercent === 'string' ? parseFloat(newForm.getRawValue().gstPercent) : newForm.getRawValue().gstPercent;
    const baseAmount = typeof newForm.getRawValue().baseAmount === 'string' ? parseFloat(newForm.getRawValue().baseAmount) : newForm.getRawValue().baseAmount;
    newForm.get('gstAmount').patchValue(((gstPercent / 100) * baseAmount).toFixed(2));
    newForm.get('totalAmount').patchValue(((gstPercent / 100) * baseAmount + baseAmount).toFixed(2));
  }
  private calculateTotalPoAmountWithGstAndBase() {
    const totalBase = this.calculateTotalBaseAmount();
    const totalGst = this.calculateTotalGstAmount();
    this.calculateTotalPOAmount(totalBase, totalGst);
  }

  private calculateTotalBaseAmount() {
    let initialValue = 0;
    this.purchaseOrderCreatePagePresenter.getItemDetailsForm.getRawValue().forEach(arrayValue => {
      initialValue = parseFloat(initialValue+"") + parseFloat(arrayValue.baseAmount);
    });
    return this.convertToTwoDigitsAfterDecimal(initialValue);
  }
  private calculateTotalGstAmount() {
    let initialValue = 0;
    this.purchaseOrderCreatePagePresenter.getItemDetailsForm.getRawValue().forEach(arrayValue => {
      initialValue = parseFloat(initialValue+"") + parseFloat(arrayValue.gstAmount);
    });
    return this.convertToTwoDigitsAfterDecimal(initialValue);
  }
  private convertToTwoDigitsAfterDecimal(val: any): any {
    // return val!=null && parseFloat(val.toFixed(2));
    return val!=null &&  parseFloat(val).toFixed(2);
  }
  private calculateTotalPOAmount(totalBaseAmount: any, totalGstAmount: any) {
    
    // if (typeof totalBaseAmount === 'number' && typeof totalGstAmount === 'number') {
      this.poTotalForm.get('totalBasicAmount').patchValue(this.convertToTwoDigitsAfterDecimal(totalBaseAmount));
      this.poTotalForm.get('totalGstAmount').patchValue(this.convertToTwoDigitsAfterDecimal(totalGstAmount));
      
      if(this.poTotalForm.get('tcsPerc').value){
          let total:number = parseFloat(totalGstAmount) + parseFloat(totalBaseAmount);
          total = total + (total * parseFloat(this.poTotalForm.get('tcsPerc').value))/100; 
          
          this.poTotalForm.get('totalPOAmount').patchValue(this.convertToTwoDigitsAfterDecimal(total));
          
      }else{
          this.poTotalForm.get('totalPOAmount').patchValue(this.convertToTwoDigitsAfterDecimal((parseFloat(totalGstAmount) + parseFloat(totalBaseAmount))));    
      }
   // }
  }
  public calculateTcsSelection(){
      if(this.poTotalForm.get('totalBasicAmount').value!=null){
          this.calculateTotalPOAmount(this.poTotalForm.get('totalBasicAmount').value, this.poTotalForm.get('totalGstAmount').value);
      }else{
          this.toasterService.warning("Base Amount not found");
          this.poTotalForm.get('tcsPerc').reset()
      }
  }
  public itemNumberSelection(event: MatAutocompleteSelectedEvent, index: number) {
    if (event && event.option.value) {
      const selectedControlUuidValue = this.itemDetailsArray[index].get('uuid').value;
      const groupToPatch = this.itemDetailsArray.filter((formGroup: FormGroup) => {
        if (formGroup.controls.uuid.value === selectedControlUuidValue) {
          this.getItemDetailsFromItemNumberId(event.option.value.id, formGroup);
          this.filteredItemNumberList = of([]);
        }
      });
    }
  }
  private getItemDetailsFromItemNumberId(itemNumberId: string, fgToPatch: FormGroup) {
    const orderType = this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('orderType').value.orderType;
    let priceType = '';
    if(this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('priceType'))
        priceType = this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('priceType').value;
    if(priceType===undefined)
        priceType='';
    // console.log(itemNumberId, orderType, priceType)
    this.itemDetailsWebService.getItemDetailsFromItemNumberId(itemNumberId, orderType, priceType).subscribe(res => {
      if (res) {
        const result = res['result'];
        delete result.itemNumber;
        fgToPatch.patchValue(result);

        if(!this.isView){
          fgToPatch.get('quantity').setValidators(CustomValidators.moq(fgToPatch.get('moq'), "Quantity should be multiple of MOQ :"+fgToPatch.get('moq').value));
          
        }
        if (fgToPatch.get('totalAmount').value !== null) {
          this.calculateRowAndTotalPoValue(fgToPatch); //calcualte all values if item number changes
        }
      }
    })
  }
  public downloadTemplate() {
    this.itemDetailsWebService.downloadTemplateForPoItemDetail("PurchaseOrder.xlsx").subscribe((resp: HttpResponse<Blob>) => {
        if (resp) {
            let headerContentDispostion = resp.headers.get('content-disposition');
            let fileName = headerContentDispostion.split("=")[1].trim();
            const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
           saveAs(file);
          }

    })
  }
  public addRow() {
    this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails();
    this.filteredItemNumberList = of([]);
  }
  public deleteRow() {
    this.purchaseOrderCreatePagePresenter.deleteRowFromItemDetail();
    this.calculateTotalPoAmountWithGstAndBase();
  }
  
  public uploadExcel(){
      if(this.purchaseOrderCreatePagePresenter.getPartOrderingForm.controls.orderType.value === null){
          this.toasterService.warning('Please select Order Type');
          return false;
      }else if(this.purchaseOrderCreatePagePresenter.getPartOrderingForm.controls.orderType.value.orderType === 'Co-Dealer Order' && this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('priceType').value === null){
          this.toasterService.warning('Please select Price Type');
          return false;
      }
      
      const orderType = this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('orderType').value.orderType;
      let priceType = '';
      if(this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('priceType'))
          priceType = this.purchaseOrderCreatePagePresenter.getPartOrderingForm.get('priceType').value;
      if(priceType===undefined)
          priceType='';
      
      const dialogConfig = new MatDialogConfig();
      // The user can't close the dialog by clicking outside its body
      dialogConfig.disableClose = true;
      dialogConfig.id = "modal-component";
      dialogConfig.width = "500px";
      
      /*confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Cancel', 'Confirm'];*/
      const modalDialog = this.matDialog.open(ModalFileUploadComponent, dialogConfig);
     
      modalDialog.afterClosed().subscribe(result => {
           if(result.event === 'upload'){
               let file:File = result.data;
           
               const itemDetailsIdArray = [];
               const errorItemDetailsArray:Array<SparesPOPartDetails> = [];
               
               this.itemDetailsArray.forEach((element: FormGroup) => {
                 if (typeof element.get('sparePartMaster').value === 'object' && element.get('sparePartMaster').value) {
                   itemDetailsIdArray.push(element.get('sparePartMaster').value.id);
                 }
               });
           
               let uploadableFile = { file: file, orderType: orderType, priceType: priceType, existingItems : itemDetailsIdArray.join() }
               
               this.itemDetailsWebService.uploadExcelPoItemDetail(uploadableFile).subscribe(response => {
                   let items:Array<SparesPOPartDetails> = response['result'];
               
                   let totalBaseAmount:number = this.poTotalForm.controls.totalBasicAmount.value;
                   let totalGSTAmount:number = this.poTotalForm.controls.totalGstAmount.value;
                   let totalAmount:number = this.poTotalForm.controls.totalPOAmount.value;
               
                   if(items!=null && items.length > 0){
                       items.forEach(data => {
                           if(data.isValidItem === 'Y'){
                               this.purchaseOrderCreatePagePresenter.addNewRowInItemDetails(data,true);
                               totalBaseAmount = (totalBaseAmount==null?0:totalBaseAmount) + data.baseAmount;
                               totalGSTAmount = (totalGSTAmount==null?0:totalGSTAmount) + data.gstAmount;
                               totalAmount = totalBaseAmount + totalGSTAmount;
                           }else{
                               errorItemDetailsArray.push(data);
                           }
                       })
                   }
                   this.poTotalForm.controls.totalBasicAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalBaseAmount));
                   this.poTotalForm.controls.totalGstAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalGSTAmount));
                   if(this.poTotalForm.get('tcsPerc').value){
                       totalAmount = totalAmount + (totalAmount * this.poTotalForm.get('tcsPerc').value)/100;
                       this.poTotalForm.controls.totalPOAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalAmount));
                   }else{
                       this.poTotalForm.controls.totalPOAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalAmount));
                   }
                   this.toasterService.success(response['message']);
                   if(errorItemDetailsArray.length>0){
                       
                       const dialogConfig1 = new MatDialogConfig();
                       dialogConfig1.disableClose = true;
                       dialogConfig1.id = "item-error-component";
                       dialogConfig1.width = "500px";
                       dialogConfig1.height = "350px";
                       dialogConfig1.data = errorItemDetailsArray;
                       const modalDialog = this.matDialog.open(ItemErrorReportComponent, dialogConfig1);
                       
                   }
               })
           }
        })
  }
}