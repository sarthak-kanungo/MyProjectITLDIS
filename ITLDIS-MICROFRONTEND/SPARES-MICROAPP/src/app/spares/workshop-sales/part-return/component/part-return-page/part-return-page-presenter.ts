import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, FormArray, AbstractControl } from '@angular/forms';
import { PartReturnPageStore } from './part-return-page-store';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class PartReturnPagePresenter {
    private readonly partReturnPageStore: PartReturnPageStore;
    public readonly partReturnPageForm: FormGroup;
    public readonly partReturnForm: FormGroup;
    public readonly partReturnItemFormArray: FormArray;
    public itemDetailsGroup: BehaviorSubject<FormGroup[]> = new BehaviorSubject<FormGroup[]>(null);
    constructor(
        fb: FormBuilder,
        private toastr: ToastrService,
    ) {
        this.partReturnPageStore = new PartReturnPageStore(fb);
        this.partReturnPageForm = this.partReturnPageStore.partReturnPageForm;
        this.partReturnForm = this.partReturnPageForm.get('partReturn') as FormGroup;
        this.partReturnItemFormArray = this.partReturnPageStore.partReturnTableControl;
    }

    get createItemDetailsTableRowFn() {
        return this.partReturnPageStore.createItemDetailTableRow;
    }
    validate() {
        this.partReturnPageForm.markAllAsTouched();
        //return (this.partReturnForm.valid && this.partReturnItemFormArray.value && this.partReturnItemFormArray.value.length > 0);
        
        let flag:boolean = true;
        if(this.partReturnForm.valid && this.partReturnItemFormArray.valid){
        
            if(this.partReturnItemFormArray.getRawValue() && this.partReturnItemFormArray.getRawValue().length > 0){
                
               (this.partReturnItemFormArray.controls as FormGroup[]).forEach(fg => {
                   if(fg.controls.returnQuantity.value > fg.controls.issuedQuantity.value){
                       flag= false;
                       fg.controls.returnQuantity.setErrors({ maxValue: "Return Qty can't exceed Issued Qty" });
                       return false;
                   }else{
                       fg.controls.issuedQuantity.setErrors(null);
                   } 
                });
            }else{
                this.toastr.error('Part Details not found');
                flag= false;
            }
        }else{
            flag= false;
        }
        return flag;
        
    }
    patchValue(obj) {
        this.partReturnForm.patchValue(obj);
    }
     addRow(data) {
         let fg:FormGroup =  this.partReturnPageStore.createItemDetailTableRow(data)
         if(fg!=null){
             this.partReturnItemFormArray.push(fg)
             this.itemDetailsGroup.next(this.partReturnItemFormArray.controls as FormGroup[]);
         }
     }
     deleteRow() {
         let nonSelected = this.partReturnItemFormArray.controls.filter(element => !element.value.isSelected)
         this.partReturnItemFormArray.clear()
         nonSelected.forEach(el => this.partReturnItemFormArray.push(el))
    }
    resetWhenReturnTypeChange() {
        this.forEachControl(this.partReturnForm.controls, (formContol, formContolName) => {
            switch (formContolName) {
                case 'issueType':
                    break;
                case 'partReturndBy':
                    break;
                case 'partReturnDate':
                    break;
                default:
                    formContol.reset();
                    break;
            }
        });
         //this.partReturnItemFormArray.patchValue({resetForm:true});
         //this.partReturnItemFormArray.clear();
        this.itemDetailsGroup.next(null);
        this.partReturnForm.controls.sparePartRequisition.enable();
    }
    reset() {
        const { partReturndBy, partReturnDate } = this.partReturnForm.getRawValue();
        this.partReturnPageForm.reset();
        //this.partReturnItemFormArray.patchValue({resetForm:true});
        //this.partReturnItemFormArray.clear();
        this.itemDetailsGroup.next(null);
        this.partReturnForm.controls.sparePartRequisition.enable();
        this.partReturnForm.patchValue({ partReturndBy, partReturnDate });

    }
    forEachControl(controls: {
        [key: string]: AbstractControl;
    }, cb: (v: any, k: string) => void): void {
        Object.keys(controls).forEach(k => cb(controls[k], k));
    }
}