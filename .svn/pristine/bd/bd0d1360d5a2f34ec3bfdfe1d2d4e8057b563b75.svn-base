import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup } from "@angular/forms";
import { ItemDetail } from "../domain/pl.domain";
import { PickListPageStore } from "./pickList-page.store";
import { Observable, BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
@Injectable()
export class PLPagePresenter {
     readonly plForm: FormGroup;
     public saleOrderNumberSubject: BehaviorSubject<string> = new BehaviorSubject<string>(null);
     public itemDetailsGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
     constructor( private pickListPageStore : PickListPageStore,
             private toasterService: ToastrService,){
        this.plForm = this.pickListPageStore.pickListFormGroup
     }
      
      get pickListForm() {
        return this.plForm.get('pickListForm') as FormGroup;
      }
      
      public get getItemDetailsFormObservable(): Observable<Array<FormGroup>> {
          return this.itemDetailsGroup.asObservable();
      }
      public get getItemDetailsForm(): FormArray {
          return this.pickListPageStore.pickListFormGroup.get('itemDetails') as FormArray;
      }
      customerOrdeNoSetValidation() {
        this.pickListForm.get('saleOrderNumber').setErrors({
          selectFromList: 'Please select from list',
        });
      }
      
      public addNewRowInItemDetails(dataForPatch?: ItemDetail, currentRowIndex?:number) {
          
          if (this.pickListPageStore.pickListFormGroup.get('itemDetails').valid) {

              let itemControls = <FormArray>this.pickListPageStore.pickListFormGroup.get('itemDetails');
              
              let newForm = this.pickListPageStore.createItemDetailsTableRow(dataForPatch);
              if(currentRowIndex){
                  itemControls.insert(currentRowIndex, newForm);
              }else{
                  itemControls.push(newForm);
              }
              this.itemDetailsGroup.next(itemControls.controls as FormGroup[]);
              
              return newForm.controls.uuid.value;
          }
      }
      
      public deleteRowFromItemDetail(currentRowIndex?:number) {
          if(currentRowIndex){
              let machineDetails = this.pickListPageStore.pickListFormGroup.get('itemDetails') as FormArray;
              machineDetails.removeAt(currentRowIndex);
          }else{
              let selectedToDelete = [];
              selectedToDelete = this.pickListPageStore.pickListFormGroup.get('itemDetails').value.filter(val => val.isSelected === true);
              if ((this.pickListPageStore.pickListFormGroup.get('itemDetails').value.length - selectedToDelete.length) >= 1) {
                  let machineDetails = this.pickListPageStore.pickListFormGroup.get('itemDetails') as FormArray;
                  let nonSelected = machineDetails.controls.filter(machinery => !machinery.value.isSelected)
                  machineDetails.clear();
                  nonSelected.forEach(el => machineDetails.push(el));
              }else{
                  this.toasterService.warning("All Rows cann't be delete","Warning");
              }
          }
      }
}