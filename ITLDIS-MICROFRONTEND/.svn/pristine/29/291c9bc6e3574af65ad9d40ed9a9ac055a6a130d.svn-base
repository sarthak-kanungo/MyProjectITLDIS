import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, FormArray, AbstractControl,FormControl } from '@angular/forms';
import { PartIssuePageStore } from './part-issue-page-store';
import { TypeOf } from '../../../../../utils/check-typeof';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class PartIssuePagePresenter {
    public itemDetailsGroup: BehaviorSubject<Array<FormGroup>> = new BehaviorSubject<Array<FormGroup>>(null);
    public readonly partIssuePageForm: FormGroup;
    public readonly partIssueForm: FormGroup;
    public readonly partIssueItemFormArray: FormArray;

    constructor(
            private partIssuePageStore : PartIssuePageStore,
            private toastr: ToastrService
    ) {
        this.partIssuePageForm = this.partIssuePageStore.partIssuePageForm;
        this.partIssueForm = this.partIssuePageStore.partIssueForm;
        this.partIssueItemFormArray = this.partIssuePageStore.partIssueItemFormArray;
    }
    isAdvancedSparePartIssue(){
        if(this.partIssueForm.controls.issueAgainst.value=='API'){
            return true
        }else{
            return false;
        }
    }
    validate() {
        this.partIssuePageForm.markAllAsTouched();
        let flag:boolean = true;
        if(this.partIssueForm.valid){
        
            if(this.partIssueItemFormArray.getRawValue() && this.partIssueItemFormArray.getRawValue().length > 0){
                
               (this.partIssueItemFormArray.controls as FormGroup[]).forEach(fg => {
                   if(fg.controls.issuedQuantity.value==null || fg.controls.issuedQuantity.value==0){
                       flag= false;
                       fg.controls.issuedQuantity.setErrors({ minValue: 'Qty Required' });
                       this.toastr.error("Issue Qty Required OR Delete Part");
                       return false;
                   }else{
                       fg.controls.issuedQuantity.setErrors(null);
                   } 
                });
            }else{
                this.toastr.error("Part Details not found");
                flag= false;
            }
        }else{
            flag= false;
        }
        return flag;
    }
    patchValue(obj) {
        this.partIssueForm.patchValue(obj);
    }
    addRow(data,currentRowIndex?:number) {
        let fg:FormGroup = this.partIssuePageStore.createItemDetailTableRow(data);
        
        if(currentRowIndex){
            this.partIssueItemFormArray.insert(currentRowIndex, fg);
        }else{
            this.partIssueItemFormArray.push(fg)
        }
        this.itemDetailsGroup.next(this.partIssueItemFormArray.controls as FormGroup[]);
    }
    deleteRow() {
        let nonSelected = this.partIssueItemFormArray.controls.filter(element => !element.value.isSelected)
        this.partIssueItemFormArray.clear()
        nonSelected.forEach(el => this.partIssueItemFormArray.push(el))
    }
    resetWhenIssueTypeChange() {
        this._forEachChild(this.partIssueForm.controls, (formContol, formContolName) => {
            switch (formContolName) {
                case 'issueType':
                    break;
                case 'partsIssuedBy':
                    break;
                case 'issueDate':
                    break;
                case 'id':
                    break;
                default:
                    formContol.reset();
                    break;
            }
        });
        // this.partIssueItemFormArray.patchValue({resetForm:true});
        this.partIssueItemFormArray.clear();
    }
    resetWhenSparePartRequisitionChange() {
        console.log('resetWhenSparePartRequisitionChange: ');
        this._forEachChild(this.partIssueForm.controls, (formContol, formContolName) => {
            switch (formContolName) {
                case 'issueType':
                    break;
                case 'partsIssuedBy':
                    break;
                case 'issueDate':
                    break;
                case 'id':
                    break;
                case 'sparePartRequisition':
                    break;
                default:
                    formContol.reset();
                    break;
            }
        });
        this.partIssueItemFormArray.clear();
    }
    resetWhenJobCardNoChange() {
        console.log('resetWhenJobCardNoChange: ');
        this._forEachChild(this.partIssueForm.controls, (formContol, formContolName) => {
            switch (formContolName) {
                case 'issueType':
                    break;
                case 'partsIssuedBy':
                    break;
                case 'issueDate':
                    break;
                case 'id':
                    break;
                case 'jobCardNo':
                    break;
                // case 'sparePartRequisition':
                //     break;
                default:
                    formContol.reset();
                    break;
            }
        });
        this.partIssueItemFormArray.clear();
    }
    resetWhenIssueAgainstChange(){
        this.partIssueItemFormArray.clear();
    }
    reset() {
        const { partIssuedBy, partIssueDate } = this.partIssueForm.getRawValue();
        this.partIssuePageForm.reset();
        // this.partIssueItemFormArray.patchValue({resetForm:true});
        this.partIssueItemFormArray.clear();
        this.partIssueForm.patchValue({ partIssuedBy, partIssueDate });

    }
    private _forEachChild(controls: {
        [key: string]: AbstractControl;
    }, cb: (v: AbstractControl, k: string) => void): void {
        Object.keys(controls).forEach(k => cb(controls[k], k));
    }
    patchValueToPartIssue(value: { [key: string]: any; }, options?: { onlySelf?: boolean; emitEvent?: boolean; }) {

        this._forEachChild(this.partIssueForm.controls, (control: AbstractControl, formContolName: string) => {
            if (formContolName === 'sparePartRequisition') {
                if (value && typeof value['sparePartRequisition'] === 'string') {
                    control.patchValue({ id: value.requisitionId, value: value.sparePartRequisition }, options);
                    return;
                }
            }
            if (value[formContolName]) {

                control.patchValue(value[formContolName], options);

            }
        });
        
        if(value['advancedSparePartIssueNo']){
            this.partIssueForm.addControl('advancedSparePartIssue', new FormControl({value:{id:value['advancedSparePartIssueId'],value: value['advancedSparePartIssueNo']},disabled:true}));
        }
        // new TypeOf(this).isString();

    }
}