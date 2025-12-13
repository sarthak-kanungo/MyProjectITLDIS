import { Injectable } from "@angular/core";
import { SpareDescripancyClaimStore } from "./spare-descripancy-claim-store";
import { FormArray, FormGroup } from "@angular/forms";
import { Operation } from "src/app/utils/operation-util";
import { UploadableFile } from "itldis-file-upload";
import { Toast, ToastrService } from "ngx-toastr";
import { LocalStorageService } from "src/app/root-service/local-storage.service";
import { element } from "protractor";

@Injectable()
export class SpareDescripancyClaimPresenter {
    private _operation: string;
    photo1: UploadableFile[];
    photo2:UploadableFile[];
    operation: any; 
  constructor(
    private store:SpareDescripancyClaimStore,
    private toaster:ToastrService,
    private localStorageService:LocalStorageService
  ){
    
  } 
    get searchSpareDesClaimForm() {
        return this.store.spareDescripancyClaimForm.get('searchForm') as FormGroup;
    }

    get createSpareDesClaimForm(){
        return this.store.spareDescripancyClaimForm.get('createForm') as FormGroup;
    }

    get createAttachmentForm(){
        return this.store.spareDescripancyClaimForm.get('attachementForm') as FormGroup;
    }

    get partDetailForm(): FormArray {
        return this.store.spareDescripancyClaimForm.get('partDetailsForm') as FormArray;
      }

      get approvalForm() {
        return this.store.spareDescripancyClaimForm.get('approvalForm') as FormGroup;
      }


      addRowInMaterialDetail(materialDetail?:any ) {
        const control = this.partDetailForm;
        const newForm = this.store.initializePartDetailForm(materialDetail);
        control.push(newForm);
        if (this._operation == Operation.VIEW) {
          newForm.disable();
        }
        if (this._operation == Operation.EDIT) {
          newForm.disable();
        }
        if(this._operation==Operation.APPROVE){
            newForm.disable();
        }
      }

      collectPhoto1(files: UploadableFile[]) {
        this.photo1 = files;
           }

    collectPhoto2(File:UploadableFile[]){
        this.photo2=File;
    }

    deleteMaterialDetail(form: FormArray){
        const deleteRow = form.value.filter(val => val.isSelected);
        if (deleteRow.length === form.value.length) {
            this.toaster.warning("You Can't Delete All Details ")
            return;
          }
        if ((form.value.length - deleteRow.length) > 0) {
          const control = form;
          const notSelected = control.controls.filter(ctrl => !ctrl.value.isSelected);
          control.clear();
          notSelected.forEach(elt => { control.push(elt) });
        }
    }


    checkQuantity(){
          const partDetails =this.store.spareDescripancyClaimForm.get('partDetailsForm') as FormArray;
          partDetails.controls.forEach(element=>{
              if(element.get('returnQty').value!=null){
                const returnQty=element.get('returnQty').value;
                const kaiAcceptedQty=element.get('kaiAcceptedQty').value;
                if (kaiAcceptedQty > returnQty) {
                    this.toaster.warning("kaiAcceptedQty should not be greater than returnQty");
                    element.get('kaiAcceptedQty').reset();
                    element.get('kaiRemarkReason').reset();
                  } else {
                    // console.log('Quantity check passed for this element');
                  }
                
              }
            //   if(element.get('kaiAcceptedQty').value===0){
            //     this.toaster.warning("kaiAcceptedQty should not be zero");
            //     element.get('kaiAcceptedQty').reset();
            //   }
          })
          
    }

    checkValidation(){
      const partDetails =this.store.spareDescripancyClaimForm.get('partDetailsForm') as FormArray;
          partDetails.controls.forEach(element=>{
            if(element.get('returnQty').value!=null ){
                  const damageQty=element.get('damageQty').value;
                  const returnQty=element.get('returnQty').value;
                   if(returnQty>damageQty){
                    this.toaster.warning("Return Qty should be Less than Damage Qty");
                    element.get('returnQty').reset();
                    element.get('value').reset();
                    return false;
                    
                   }
               }
           
          })
    }

    calCulateValueSellingPrice(){
      const partDetails =this.store.spareDescripancyClaimForm.get('partDetailsForm') as FormArray;
          partDetails.controls.forEach(element=>{
            if(element.get('returnQty').value!=null ){
                  const sellingPrice=element.get('sellingUnitePrice').value.toFixed(2);
                  let returnQty=element.get('returnQty').value;
                  let finalValue=(sellingPrice*returnQty).toFixed(2);
                  element.get('value').patchValue(finalValue);
               }
           
          })
    }

   

}