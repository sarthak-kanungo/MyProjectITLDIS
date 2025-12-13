import { Injectable } from '@angular/core';
import { PartRequisitionPageStore } from './part-requisition-page.store';
import { SparePartRequisitionItemById } from '../../domain/part-requisition.domain';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

export interface PartRequisitionPresenter {
    readonly partRequisitionForm: FormGroup
}
export interface SparePartRequisitionItemPresenter {
    readonly sparePartRequisitionItemControl: FormArray;
}
@Injectable()
export class PartRequisitionPagePresenter implements PartRequisitionPresenter, SparePartRequisitionItemPresenter {
    private partRequisitionPageStore: PartRequisitionPageStore
    public itemDetailsGroup: BehaviorSubject<FormArray> = new BehaviorSubject<FormArray>(null);
    constructor(
        fb: FormBuilder
    ) {
        this.partRequisitionPageStore = new PartRequisitionPageStore(fb);
    }
    public get partRequisitionPageForm(): FormGroup {
        return this.partRequisitionPageStore.partRequisitionPageForm;
    }
    public get partRequisitionForm(): FormGroup {
        return this.partRequisitionPageStore.partRequisitionPageForm.get('partRequisition') as FormGroup;
    }
    public get sparePartRequisitionItemControl(): FormArray {
        return this.partRequisitionPageStore.sparePartRequisitionItemControl;
    }
    public addNewRowInItemDetails(dataForPatch?: SparePartRequisitionItemById):FormGroup{
        let itemControls =  this.partRequisitionPageStore.sparePartRequisitionItemControl;
        if(this.partRequisitionPageStore.partRequisitionPageForm.get('sparePartRequisitionItem').valid){
            let fg:FormGroup = this.partRequisitionPageStore.itemDetail();

            if(dataForPatch){
                fg.patchValue(dataForPatch);
                fg.controls.itemNo.patchValue({'itemNo':dataForPatch.itemNo,'value':dataForPatch.itemNo,'id':dataForPatch.sparePartId})
                fg.controls.itemNo.disable();
            }
        
            itemControls.push(fg); 
            this.itemDetailsGroup.next(itemControls);
            return fg;
        }
    }
    public deleteRowItemDetail(){
        let selectedToDelete = [];
        selectedToDelete = this.partRequisitionPageStore.sparePartRequisitionItemControl.value.filter(val => val.isSelected === true);
        //if ((this.partRequisitionPageStore.sparePartRequisitionItemControl.length - selectedToDelete.length) >= 1) {
            let machineDetails = this.partRequisitionPageStore.sparePartRequisitionItemControl;
            let nonSelected = machineDetails.controls.filter(machinery => !machinery.value.isSelected)
            machineDetails.clear();
            nonSelected.forEach(el => machineDetails.push(el));
        //}
    }
    validate(){
        this.partRequisitionPageForm.markAllAsTouched();
        return this.partRequisitionForm.valid && 
            this.sparePartRequisitionItemControl.value && 
            this.sparePartRequisitionItemControl.value.length > 0 &&
            this.sparePartRequisitionItemControl.valid;
    }
    patchValue(obj){
        this.partRequisitionForm.patchValue(obj);
    }
    reset(){
        const {requestedBy,requisitionDate} = this.partRequisitionForm.getRawValue();
        this.partRequisitionPageForm.reset();
        this.itemDetailsGroup.next(null);
        this.partRequisitionForm.patchValue({requestedBy,requisitionDate});

    }
    
}
