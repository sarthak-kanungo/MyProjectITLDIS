import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class TrainingnominationSearchPageStore {
    private searchForm: FormGroup;

    constructor(private fb:FormBuilder){
        this.searchForm = fb.group({
            tpcSearchHeader: this.tpcSearchHeaderForm(),
        })
    }


    public get nominationSearchForm(){
        return this.searchForm
    }

    tpcSearchHeaderForm(){
        return this.fb.group({
            programNumber:[],
            nominatinoNumber:[],
            fromDate:[],
            toDate:[],
        })
    }
}