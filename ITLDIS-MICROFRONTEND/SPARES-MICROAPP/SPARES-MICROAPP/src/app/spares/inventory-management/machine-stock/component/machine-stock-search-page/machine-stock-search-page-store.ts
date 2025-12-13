import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";

@Injectable()
export class MachineStockSearchPageStore {
    
    private readonly _machineStockSearchFormGroup: FormGroup;
    

    constructor( private fb: FormBuilder){
        
        this._machineStockSearchFormGroup = fb.group({
            machineStockSearchForm: this.buildingMachineStocProductSearchForm(),
        })
    }
    public get machineStockSearchForm(){
        return this._machineStockSearchFormGroup;
    }

    private buildingMachineStocProductSearchForm(): FormGroup {
        return this.fb.group({
            invoiceFromDate:[''],
            invoiceToDate:[''],
            product: [''],
            series: [''],
            model: [''],
            subModel: [''],
            variant: [''],
            itemNo: [''],
            chassisNo:[''],
            engineNo:[''],
            status:[''],
            grnDoneFlag:[''],
            dealerCode : [null],
            dealerName : [null],
            orgHierLevel1 : [],
            orgHierLevel2 : [],
            orgHierLevel3 : [],
            orgHierLevel4 : [],
            orgHierLevel5 : [],
        })
    }
}