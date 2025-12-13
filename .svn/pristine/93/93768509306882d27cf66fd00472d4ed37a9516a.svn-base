import { Injectable } from "@angular/core";
import { TrainingnominationSearchPageStore } from './training-nomination-search-page.store';
import { FormGroup } from '@angular/forms';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';

@Injectable()
export class TrainingnominationSearchPagePresenter {

    readonly nominationSearchForm: FormGroup


    constructor(private store:TrainingnominationSearchPageStore){
        this.nominationSearchForm = store.nominationSearchForm
    }

    public get tpcSearchHeaderForm(){
        return this.nominationSearchForm.get('tpcSearchHeader') as FormGroup
    }

   
}