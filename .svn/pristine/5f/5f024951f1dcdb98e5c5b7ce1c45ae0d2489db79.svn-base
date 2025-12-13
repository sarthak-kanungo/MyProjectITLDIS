import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Injectable()
export class SearchEnquiryV2Presenter {

    searchEnquiryFormGroup: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ){
        this.searchEnquiryFormGroup = this.formBuilder.group({
            searchEnquiry: this.buildsearchEnquiryFormGroup(),
        })
    }

    private buildsearchEnquiryFormGroup(): FormGroup {
        return this.formBuilder.group({
            enquiryNumber: [''],
            enquiryType: [''],
            model: [''],
            salesPerson: [''],
            fromDate: [''],
            toDate: [''],
            source: [''],
            enquiryStatus: [''],
            retailConversionActivity: [''],
            product: [''],
            series: [''],
            subModel: [''],
            variant: [''],
            itemNo: [''],
            finance: [''],
            autoClose: [''],
            subsidy: [''],
            exchange: [''],
            nextfollowUpFromDate: [''],
            nextFollowUpToDate: [''],
            tentativePurchaseFromDate: [''],
            tentativePurchaseToDate: [''],
            dealerCode : [null],
            dealerName : [{value:'', disabled:true}],
            orgHierLevel1 : [],
            orgHierLevel2 : [],
            orgHierLevel3 : [],
            orgHierLevel4 : [],
            orgHierLevel5 : [],
            district : [null],
            village : [null],
            state : [null,[Validators.required]]
        })
    }

    resetAll(){
        let searchEnquiry = this.searchEnquiryFormGroup.get('searchEnquiry') as FormGroup
        searchEnquiry.reset({
            dealerCode: searchEnquiry.get('dealerCode').value
        })
    }

    disabledForItemNo(){
        let searchEnquiry = this.searchEnquiryFormGroup.get('searchEnquiry') as FormGroup
        searchEnquiry.get('product').disable()
        searchEnquiry.get('series').disable()
        searchEnquiry.get('subModel').disable()
        searchEnquiry.get('variant').disable()
        searchEnquiry.get('model').disable()
    }
    enableForItemNo(){
        let searchEnquiry = this.searchEnquiryFormGroup.get('searchEnquiry') as FormGroup
        searchEnquiry.get('product').enable()
        searchEnquiry.get('series').enable()
        searchEnquiry.get('subModel').enable()
        searchEnquiry.get('variant').enable()
        searchEnquiry.get('model').enable()
    }

    resetForItemNo(){
        let searchEnquiry = this.searchEnquiryFormGroup.get('searchEnquiry') as FormGroup
        searchEnquiry.get('product').reset()
        searchEnquiry.get('series').reset()
        searchEnquiry.get('subModel').reset()
        searchEnquiry.get('variant').reset()
        searchEnquiry.get('model').reset()
    }

}