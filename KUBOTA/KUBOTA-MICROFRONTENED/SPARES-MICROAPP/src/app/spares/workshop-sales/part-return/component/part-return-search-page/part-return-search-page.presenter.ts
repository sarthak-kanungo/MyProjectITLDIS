import { Injectable } from "@angular/core";
import { PartReturnSearchPageStore } from './part-return-search-page.store';
import { FormBuilder, FormGroup } from '@angular/forms';

@Injectable()
export class PartReturnSearchPagePresenter {
    private readonly partReturnSearchPageStore: PartReturnSearchPageStore;
    constructor(fb: FormBuilder){
        this.partReturnSearchPageStore = new PartReturnSearchPageStore(fb);
    }
    
    public get partReturnSearchForm() : FormGroup {
        return this.partReturnSearchPageStore.partReturnPageForm;
    }
    
}