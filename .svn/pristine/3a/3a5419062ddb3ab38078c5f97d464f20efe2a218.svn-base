import { Injectable } from "@angular/core";
import { PCRSearchPageStore } from './pcr-search-page.store';
import { FormGroup } from '@angular/forms';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';

@Injectable()
export class PCRSearchPagePresenter {
    searchFilter: any;

    constructor(
        private pcrSearchPageStore: PCRSearchPageStore,
        private localStorageService: LocalStorageService

    ) {}

    get PCRSearchForm(): FormGroup {
        return this.pcrSearchPageStore.pcrSearchFormAll;
    }

    get loginUser() {
        return this.localStorageService.getLoginUser();
    }

    searchFilterForm (filter) {
        this.searchFilter = filter;
        
    }

}