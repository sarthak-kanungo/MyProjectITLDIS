import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class DealerSearchPageStore {


    private readonly _searchDealerForm: FormGroup;
    constructor(private formBuilder: FormBuilder) {
        this._searchDealerForm = this.formBuilder.group({
            dealerForm:this.buildDealerSearchForm(),
            
        });
    }

    get searchDealerForm() {
        return this._searchDealerForm;
    }

   private buildDealerSearchForm(){
        return this.formBuilder.group({
            employeeCode: [null, Validators.compose([])],
            employeeName: [null, Validators.compose([])],
        })
    }
    
}