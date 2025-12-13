import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { PurchaseOrderCreatePageStore } from './purchase-order-create-page.store';
import { SparesPOPartDetails } from '../../domain/spares-purchase-order.domain';

@Injectable()
export class PurchaseOrderCreatePagePresenter {
    public itemNumberValueChanges: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    public sendTotalPOAmountToKaiOutStanding: BehaviorSubject<number> = new BehaviorSubject<number>(0);
    public itemNumbeGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
    public orderTypeChangeEvent : BehaviorSubject<string> = new BehaviorSubject<string>("");
    constructor(
        private purchaseOrderCreatePageStore: PurchaseOrderCreatePageStore
    ) { }

    public getCreatePOForm(): FormGroup {
        return this.purchaseOrderCreatePageStore.getCreatePoForm;
    }
    public get getPartOrderingForm(): FormGroup {
        return this.purchaseOrderCreatePageStore.getCreatePoForm.get('partsOrdering') as FormGroup;
    }
    public get getItemDetailsFormObservable(): Observable<Array<FormGroup>> {
        return this.itemNumbeGroup.asObservable();
        // return this.purchaseOrderCreateStore.getCreatePoForm.get('itemDetails') as FormGroup;
    }
    public get getItemDetailsForm(): FormArray {
        return this.purchaseOrderCreatePageStore.getCreatePoForm.get('itemDetails') as FormArray;
    }
    public get getPoTotalForm(): FormGroup {
        return this.purchaseOrderCreatePageStore.getCreatePoForm.get('poTotal') as FormGroup;
    }
    public addNewRowInItemDetails(dataForPatch?: SparesPOPartDetails, excelUpload?:boolean) {
        if (this.purchaseOrderCreatePageStore.getCreatePoForm.get('itemDetails').valid || excelUpload) {

            let machineControl = <FormArray>this.purchaseOrderCreatePageStore.getCreatePoForm.get('itemDetails');
            
            if(excelUpload){
                let  j:number = machineControl.value.length;
                if(machineControl.value[j-1].sparePartMaster == null || machineControl.value[j-1].sparePartMaster==''){
                    machineControl.removeAt(j-1);
                }
            }
            let newForm = this.purchaseOrderCreatePageStore.initializeItemDetailsForm(dataForPatch);  
            machineControl.push(newForm);
            this.itemNumbeGroup.next(machineControl.controls as FormGroup[])
            return newForm.controls.uuid.value;
        }
    }
    public deleteRowFromItemDetail() {
        let selectedToDelete = [];
        selectedToDelete = this.purchaseOrderCreatePageStore.getCreatePoForm.get('itemDetails').value.filter(val => val.isSelected === true);
        if ((this.purchaseOrderCreatePageStore.getCreatePoForm.get('itemDetails').value.length - selectedToDelete.length) >= 1) {
            let machineDetails = this.purchaseOrderCreatePageStore.getCreatePoForm.get('itemDetails') as FormArray;
            let nonSelected = machineDetails.controls.filter(machinery => !machinery.value.isSelected)
            machineDetails.clear();
            nonSelected.forEach(el => machineDetails.push(el));
        };
    }
    public disableAllForm() {
        this.getCreatePOForm().disable();
        this.getPartOrderingForm.disable();
    }
    public resetPartsOrderingForm() {
        this.getPartOrderingForm.get('supplierType').reset();
        this.getPartOrderingForm.get('supplierType').enable();
        this.getPartOrderingForm.get('supplierName').reset();
        this.getPartOrderingForm.get('orderPlanningNo').reset();
        this.getPartOrderingForm.get('serviceJobCard').reset();
        this.getPartOrderingForm.get('orderType').enable();
        this.getPartOrderingForm.get('orderType').reset();
        this.getPartOrderingForm.get('transportMode').reset();
        this.getPartOrderingForm.get('transporter').reset();
        this.getPartOrderingForm.get('coDealerMaster').reset();
        this.getPartOrderingForm.get('coDealerName').reset();
        this.getPartOrderingForm.get('priceType').reset();
        this.getPartOrderingForm.get('gstNumber').reset();
        this.getPartOrderingForm.get('model').reset();
        this.getPartOrderingForm.get('mobileNumber').reset();
        this.getPartOrderingForm.get('totalHour').reset();
        this.getPartOrderingForm.get('chassisNumber').reset();
        this.getPartOrderingForm.get('customerName').reset();
        this.getPartOrderingForm.get('engineNumber').reset();
        this.getPartOrderingForm.get('freightBorneBy').reset();
        this.getPartOrderingForm.get('remarks').reset();
        this.getPartOrderingForm.get('purchaseOrderDate').reset();
    }
    public setValueToPartOrderForm(patchObj: any) {
        // console.log(patchObj,'presenter@@@@@@@@@@')
        this.getPartOrderingForm.patchValue(patchObj);
        
        this.getPartOrderingForm.get('orderPlanningNo').patchValue(patchObj.opsNo)
        if(patchObj['supplierName']!=null && patchObj['supplierName']['gstNumber']!=null){
            this.getPartOrderingForm.get('gstNumber').patchValue(patchObj['supplierName']['gstNumber']);
        }
        
        let totalBaseAmount:number = this.getPoTotalForm.controls.totalBasicAmount.value;
        let totalGSTAmount:number = this.getPoTotalForm.controls.totalGstAmount.value;
        let totalAmount:number = this.getPoTotalForm.controls.totalPOAmount.value;
        if(patchObj.baseAmount && patchObj.baseAmount != null){
            totalBaseAmount = (totalBaseAmount==null?0:totalBaseAmount) + patchObj['baseAmount'];
            totalGSTAmount = (totalGSTAmount==null?0:totalGSTAmount) + patchObj['gstAmount'];
            totalAmount = totalBaseAmount + totalGSTAmount;
    
            this.getPoTotalForm.controls.totalBasicAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalBaseAmount));
            this.getPoTotalForm.controls.totalGstAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalGSTAmount));
            
            if(this.getPoTotalForm.get('tcsPerc').value){
                totalAmount = totalAmount + (totalAmount * this.getPoTotalForm.get('tcsPerc').value)/100;
                this.getPoTotalForm.controls.totalPOAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalAmount));
            }else{
                this.getPoTotalForm.controls.totalPOAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalAmount));
            }
        }
    }
    
    // private convertToTwoDigitsAfterDecimal(val: number): number {
    //     return val!=null && parseFloat(val.toFixed(2));
    //   }

      private convertToTwoDigitsAfterDecimal(val: any): any {
        return val!=null &&  parseFloat(val).toFixed(2);
      }
    
    public setAndRemoveValidatorsBasedOnOrderType(controlNamesArray: Array<string>) {
 
        let controlsToHideAndShow = ['orderPlanningNo', 'serviceJobCard', 'transportMode', 'transporter', 'coDealerMaster', 'supplierName', 'priceType'];

        controlsToHideAndShow.forEach(controlName => {
            if (controlNamesArray.includes(controlName)) {
                this.getPartOrderingForm.controls[controlName].reset();
                this.getPartOrderingForm.controls[controlName].setValidators([Validators.required]);
            } else {
                this.getPartOrderingForm.controls[controlName].reset();
                this.getPartOrderingForm.controls[controlName].setValidators([Validators.nullValidator])
            }
        })
    }
}
