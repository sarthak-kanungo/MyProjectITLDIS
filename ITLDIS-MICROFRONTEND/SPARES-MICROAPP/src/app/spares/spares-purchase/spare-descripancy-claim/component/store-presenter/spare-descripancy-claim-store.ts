import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { LocalStorageService } from "src/app/root-service/local-storage.service";
import { LoginFormService } from "src/app/root-service/login-form.service";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class SpareDescripancyClaimStore {
    spareDescripancyClaimForm:FormGroup

    constructor(
        private fb: FormBuilder,
       private localStorage:LocalStorageService,
       private loginService:LoginFormService
      
    ) {
       this.spareDescripancyClaimForm=this.fb.group({
          searchForm:this.searchSpareDescripancyClaimSearch(),
          createForm:this.createSpareDescripancyClaim(),
          attachementForm:this.createattachmentForm(),
          partDetailsForm:this.fb.array([]),
          approvalForm:this.approvalSpareDescripancyForm()
       })
    }
    createSpareDescripancyClaim(){
        const createForm=this.fb.group({
            claimType:[{value:null,disabled:false},Validators.required],
            claimNo:[{value:null,disabled:true}],
            claimDate:[{value:null,disabled:true}],
            status:[{value:null,disabled:true}],
            grnNo:[{value:null,disabled:false},Validators.compose([Validators.required, CustomValidators.selectFromLists()])],
            grnDate:[{value:null,disabled:true}],
            dealerCode:[{value:null,disabled:true}],
            claimRaisedBy:[{value:null,disabled:true}],
            noOfBoxSent:[{value:null,disabled:true}],
            kaiInvoiceNo:[{value:null,disabled:true}],
            invoiceDate:[{value:null,disabled:true}],
            mobileNo:[{value:null,disabled:true}],
            noofBoxesRecieved:[{value:null,disabled:true}],
            mode:[{value:null,disabled:true}],
            transporter:[{value:null,disabled:true}],
            lrNo:[{value:null,disabled:true}]
        });
        return createForm;
    }
    createattachmentForm(){
        const attachemtForm=this.fb.group({
         photo1:[{value:null,disabled:false}],
         photo2:[{value:null,disabled:false}],
        });
        return attachemtForm;
    }
     searchSpareDescripancyClaimSearch() {
        const searchForm = this.fb.group({
           claimNo:[{value:null,disabled:false}],
           grnNo:[{value:null,disabled:false}],
           mode:[{value:null,disabled:false}],
           claimStatus:[{value:null,disabled:false}],
           dealerCode:[{value:null,disabled:false}],
           kaiInvoiceNo:[{value:null,disabled:false}],
           fromDate:[{value:null,disabled:false}],
           toDate:[{value:null,disabled:false}],
        });
        return searchForm;
    }

    approvalSpareDescripancyForm(){
        const userType=this.localStorage.getLoginUser();
        const isitldisUser = userType['userType'] == 'itldis';
        const approvalForm=this.fb.group({
            remarkForDealer:[{value:null,disabled:false},isitldisUser ? Validators.required : null],
            internalKaiRemark:[{value:null,disabled:false},isitldisUser ? Validators.required : null],
            creditNoteNo:[{value:null,disabled:false},isitldisUser ? Validators.required : null],
            creditNoteDate:[{value:null,disabled:false},isitldisUser ? Validators.required : null],
            creditNoteAmount:[{value:null,disabled:false},isitldisUser ? Validators.required : null],
            kaiAcceptanceDate:[{value:null,disabled:true}],
            kaiSettlementDate:[{value:null,disabled:true}]
        })
        return approvalForm;
       
    }

      partDetails(materialDetail?:any) {
        const userType=this.localStorage.getLoginUser();
         const isitldisUser = userType['userType'] == 'itldis';
        const partDetailsForm = this.fb.group({
          isSelected:[{value:null,disabled:false}],
          partMaster:[{value:null,disabled:false},Validators.required],
          partId:[{value:null,disabled:true}],
          itemDesc:[{value:null,disabled:true}],
          invoiceQty:[{value:null,disabled:true}],
          receiptQty:[{value:null,disabled:true}],
          shortQty:[{value:null,disabled:true}],
          damageQty:[{value:null,disabled:true}],
          excessQty:[{value:null,disabled:false},CustomValidators.numberOnly],
          returnQty:[{value:null,disabled:false},CustomValidators.numberOnly],
          sellingUnitePrice:[{value:null,disabled:true}],
          value:[{value:null,disabled:true},CustomValidators.numberOnly],
          dealerRemarkReasons:[{value:null,disabled:false},isitldisUser ? null : Validators.required],
          kaiAcceptedQty:[{value:null,disabled:false},isitldisUser ? Validators.required : null],
          kaiRemarkReason:[{value:null,disabled:false},isitldisUser ? Validators.required : null],
          id:[{value:null,disabled:false}]
        });
        return partDetailsForm;
      }

      initializePartDetailForm(materialDetail?: any) {
        return this.partDetails(materialDetail);
      }



}