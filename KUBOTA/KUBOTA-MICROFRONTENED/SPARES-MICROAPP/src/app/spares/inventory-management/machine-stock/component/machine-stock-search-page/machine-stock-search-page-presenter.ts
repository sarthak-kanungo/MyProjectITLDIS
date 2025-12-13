import { FormGroup } from '@angular/forms';
import { Injectable } from '@angular/core';
import { MachineStockSearchPageStore } from './machine-stock-search-page-store';

@Injectable()
export class MachineStockSearchPagePersenter{
    readonly machineStockSearchFormGroup: FormGroup;
    
    constructor(private pageStore : MachineStockSearchPageStore){
        this.machineStockSearchFormGroup = this.pageStore.machineStockSearchForm;
    }
    
    public get machineStockSearchForm(){
        return this.machineStockSearchFormGroup.get('machineStockSearchForm') as FormGroup;
    }
    
}