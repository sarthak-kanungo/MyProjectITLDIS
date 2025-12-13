
import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class bankDetailStore {
    private readonly createBankDetailForm: FormGroup;
    ifsc = "^[A-Za-z]{4}0[A-Z0-9]{6}$";
    bankAccount="^[0-9]{6,22}$";
    bankName="^[a-zA-Z ]*$"
    constructor(
        private fb: FormBuilder,
    ) {
       
        this.createBankDetailForm = this.fb.group({
            employeeCode:[{value:null,disabled:false}],
            mobileNo:[{value:null,disabled:false}],
            firstName:[{value:null,disabled:true}],
            middleName:[{value:null,disabled:true}],
            lastName:[{value:null,disabled:true}],
            oldbankAccountNo:[{value:null,disabled:true}],
            oldbankName:[{value:null,disabled:true}],
            oldbankBranch:[{value:null,disabled:true}],
            oldifsCode:[{value:null,disabled:true}],
            Desigation:[{value:null,disabled:true}],
            bankAccountNo:[{value:null,disabled:false}, Validators.compose([Validators.required,Validators.pattern(this.bankAccount)])],
           bankName:[{value:null,disabled:false},Validators.compose([Validators.required,Validators.pattern(this.bankName)])],
            bankBranch:[{value:null,disabled:false},Validators.required],
            ifsCode:[{value:null,disabled:false},Validators.compose([Validators.required,Validators.pattern(this.ifsc)])]
        })
    }

    get getcreateBankDetailForm(): FormGroup {
        if (this.createBankDetailForm) {
            return this.createBankDetailForm as FormGroup;
        }
        /* this.createForm();
        return this.searchLogsheetForm as FormGroup; */
    }
}
