import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { bankDetailStore } from './bank-detail.store';

@Injectable()
export class bankDetailPresenter {

    constructor(
        private store: bankDetailStore
    ) { }

    public get getcreateBankDetailForm(): FormGroup {
        return this.store.getcreateBankDetailForm;
    }
}    
