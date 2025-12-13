import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class MachineMasterReportPageStore {


    private readonly _machineMasterReportForm: FormGroup;
    constructor(private formBuilder: FormBuilder) {
        this._machineMasterReportForm = this.formBuilder.group({
            machineMasterReportForm:this.buildMMReportForm(),
            
        });
    }

    get machineMasterReportForm() {
        return this._machineMasterReportForm;
    }

   private buildMMReportForm(){
        return this.formBuilder.group({
            productGroup:[],
            product:[],
            series:[],
            model: [null, Validators.compose([])],
            subModel: [null, Validators.compose([])],
            variant:[],
            itemcode:[],
        })
    }
    
}