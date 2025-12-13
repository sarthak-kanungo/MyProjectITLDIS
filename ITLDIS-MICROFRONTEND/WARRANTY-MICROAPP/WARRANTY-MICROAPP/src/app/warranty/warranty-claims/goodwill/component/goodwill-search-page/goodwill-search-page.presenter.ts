import { Injectable } from "@angular/core";
import { GoodwillSearchPageStore } from './goodwill-search-page.store';
import { FormGroup } from '@angular/forms';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';

@Injectable()
export class GoodwillSearchPagePresenter {

    constructor(
        private goodwillSearchPageStore: GoodwillSearchPageStore,
        private localStorageService: LocalStorageService
    ) {}

    get goodwillSearchForm(): FormGroup {
        return this.goodwillSearchPageStore.goodwillSearchForm;
    }
    get loginUser() {
        return this.localStorageService.getLoginUser();
    }
}