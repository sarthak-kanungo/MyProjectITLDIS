import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { LogSheetSearchPageStore } from './log-sheet-search-page.store';

@Injectable()
export class LogSheetSearchPagePresenter {

    constructor(
        private logSheetSearchPageStore: LogSheetSearchPageStore
    ) { }

    public get logsheetSearchForm(): FormGroup {
        return this.logSheetSearchPageStore.logsheetSearchForm;
    }
}    
