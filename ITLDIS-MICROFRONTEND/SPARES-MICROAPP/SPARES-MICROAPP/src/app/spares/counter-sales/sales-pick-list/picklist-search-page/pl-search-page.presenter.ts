import { Injectable } from "@angular/core"
import { FormGroup } from "@angular/forms"
import { PlSearchPageStore } from "./pl-search-page.store"

@Injectable()
export class PlSearchPagePresenter {
    readonly plSearchForm: FormGroup

    constructor(private store: PlSearchPageStore) {
        this.plSearchForm = this.store.plSearchFormGroup
    }

    get plSearch() {
        console.log('in_ppresenter',this.plSearchForm.get('pLSearch') as FormGroup)
        //return this.plSearchForm.get('plSearch') as FormGroup
        return this.plSearchForm.get('pLSearch') as FormGroup
    }
}