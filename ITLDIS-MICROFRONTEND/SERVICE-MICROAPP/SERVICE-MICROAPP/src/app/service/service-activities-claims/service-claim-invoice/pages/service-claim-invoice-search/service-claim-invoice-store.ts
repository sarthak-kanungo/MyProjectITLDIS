import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class claimInvoiceStore {
    private readonly invoiceVerifyForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.invoiceVerifyForm = this.fb.group({
          
                invoiceNo:[null,Validators.required],
                invoiceDate:[null,Validators.required]
          
        });
    }

   
    get claimSearchForm() {
        // console.log(this.invoiceVerifyForm,'dofifjnf')
        if(this.invoiceVerifyForm) {
            return this.invoiceVerifyForm as FormGroup;
        }
        
    }
}