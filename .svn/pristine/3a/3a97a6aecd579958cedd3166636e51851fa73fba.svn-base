import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import {BehaviorSubject} from 'rxjs';
import { PartQuotationPageStore } from './part-quotation-page.store';
import { CustomerDetails, ViewPartQuotation, RetailerOrMachinePatchJson } from '../../domain/part-quotation-domain';



@Injectable()
export class PartQuotationPagePresenter {

    onClickClearBehaviorCall:BehaviorSubject<string> = new BehaviorSubject<string>('');

    readonly partQuotationForm: FormGroup
    private _operation: string
    ids:any;
    totalAmt: number;

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private partQuotationPageStore: PartQuotationPageStore
    ) {
        this.partQuotationForm = this.partQuotationPageStore.partQuotationForm
    }

    get detailsForm() {
        return this.partQuotationForm.get('partQuotationDetailsForm') as FormGroup
    }

    get partQuotationTableRow() {
        return this.partQuotationForm.get('itemDetails') as FormGroup
    }
  
    createPartQuotationTableRow() {
        return this.partQuotationPageStore.createPartQuotationTableRow()
    }

    get partQuotationTotalForm() {
        return this.partQuotationForm.get('partQuotationTotal') as FormGroup
    }

    patchForCustomerMaster(value: CustomerDetails) {
        this.detailsForm.patchValue(value)
        this.detailsForm.get('customerType').patchValue("New/Existing" )
        this.detailsForm.get('mobileNumber').patchValue({ mobileNumber: value.mobileNumber })
        this.detailsForm.get('state').patchValue({id: value.stateId, state: value.state })
        this.detailsForm.get('district').patchValue({id: value.districtId, district: value.district })
        this.detailsForm.get('tehsil').patchValue({id: value.tehsilId, tehsil: value.tehsil })
        this.detailsForm.get('city').patchValue({id: value.cityId, city: value.city })
        this.detailsForm.get('pinCode').patchValue({id: value.pinCodeId,pinCode: value.pinCode})
        this.detailsForm.get('postOffice').patchValue({postOffice: value.postOffice})
    }

    patchValueForStateToCity(res: RetailerOrMachinePatchJson) {
        this.detailsForm.patchValue(res)
        this.detailsForm.get('mechanicName').patchValue(res.retailerName);
        this.detailsForm.get('address1').patchValue(res.customerAddress1)
        this.detailsForm.get('address2').patchValue(res.customerAddress2)
        this.detailsForm.get('mobileNumber').patchValue({ mobileNumber: res.contactNumber });
        this.detailsForm.get('state').patchValue({ state: res.state })
        this.detailsForm.get('district').patchValue({ district: res.district })
        this.detailsForm.get('tehsil').patchValue({ tehsil: res.tehsil })
        this.detailsForm.get('city').patchValue({ city: res.village })
        this.detailsForm.get('pinCode').patchValue({ pinCode: res.pinCode, id:res.pinCodeId })
        this.detailsForm.get('postOffice').patchValue({ postOffice: res.postOffice })
      }

    disabledFieldForCustomerName() {
        this.detailsForm.get('customerName').disable()
        this.detailsForm.get('address1').disable()
        this.detailsForm.get('address2').disable()
        this.detailsForm.get('state').disable()
        this.detailsForm.get('district').disable()
        this.detailsForm.get('tehsil').disable()
        this.detailsForm.get('city').disable()
        this.detailsForm.get('pinCode').disable()
        this.detailsForm.get('postOffice').disable()
    }
    enabledFieldForCustomerName() {
        this.detailsForm.get('mechanicName').enable()
        this.detailsForm.get('retailerName').enable()
        this.detailsForm.get('customerName').enable()
        this.detailsForm.get('mobileNumber').enable() 
        this.detailsForm.get('address1').enable()
        this.detailsForm.get('address2').enable()
        this.detailsForm.get('state').enable()
        this.detailsForm.get('district').enable()
        this.detailsForm.get('tehsil').enable()
        this.detailsForm.get('city').enable()
        this.detailsForm.get('pinCode').enable()
        this.detailsForm.get('postOffice').enable()
    }
    resetFieldForCustomerName() {
        this.detailsForm.get('customerName').reset()
        this.detailsForm.get('address1').reset()
        this.detailsForm.get('address2').reset()
        this.detailsForm.get('state').reset()
        this.detailsForm.get('district').reset()
        this.detailsForm.get('tehsil').reset()
        this.detailsForm.get('city').reset()
        this.detailsForm.get('pinCode').reset()
        this.detailsForm.get('postOffice').reset()
    }
    disabledFieldForMechanicNRetailer() {
        this.detailsForm.get('mechanicName').disable()
        this.detailsForm.get('retailerName').disable()
        this.detailsForm.get('mobileNumber').disable()
        this.detailsForm.get('address1').disable()
        this.detailsForm.get('address2').disable()
        this.detailsForm.get('state').disable()
        this.detailsForm.get('district').disable()
        this.detailsForm.get('tehsil').disable()
        this.detailsForm.get('city').disable()
        this.detailsForm.get('pinCode').disable()
        this.detailsForm.get('postOffice').disable()
    }
    enableFieldForMechanicNRetailer() {
        this.detailsForm.get('mechanicName').enable()
        this.detailsForm.get('mobileNumber').enable() 
        this.detailsForm.get('retailerName').enable()
        this.detailsForm.get('address1').enable()
        this.detailsForm.get('address2').enable()
        this.detailsForm.get('state').enable()
        this.detailsForm.get('district').enable()
        this.detailsForm.get('tehsil').enable()
        this.detailsForm.get('city').enable()
        this.detailsForm.get('pinCode').enable()
        this.detailsForm.get('postOffice').enable()
    }
    resetFieldForMechanicNRetailer() {
        this.detailsForm.get('mechanicName').reset()
        this.detailsForm.get('mechanic').reset()
        this.detailsForm.get('retailer').reset()
        this.detailsForm.get('mobileNumber').reset()
        this.detailsForm.get('retailerName').reset()
        this.detailsForm.get('address1').reset()
        this.detailsForm.get('address2').reset()
        this.detailsForm.get('state').reset()
        this.detailsForm.get('district').reset()
        this.detailsForm.get('tehsil').reset()
        this.detailsForm.get('city').reset()
        this.detailsForm.get('pinCode').reset()
        this.detailsForm.get('postOffice').reset()
    }
    resetFieldForCustomerType() {
        this.detailsForm.get('mobileNumber').reset()
        this.detailsForm.get('customerName').reset()
        this.detailsForm.get('mechanicName').reset()
        this.detailsForm.get('retailerName').reset()
        this.detailsForm.get('mechanic').reset()
        this.detailsForm.get('retailer').reset()
        this.detailsForm.get('mechanic').clearValidators()
        this.detailsForm.get('retailer').clearValidators()
        this.detailsForm.get('mechanic').setErrors(null);
        this.detailsForm.get('retailer').setErrors(null);
        this.detailsForm.get('mechanic').updateValueAndValidity()
        this.detailsForm.get('retailer').updateValueAndValidity()
        this.detailsForm.get('address1').reset()
        this.detailsForm.get('address2').reset()
        this.detailsForm.get('state').reset()
        this.detailsForm.get('district').reset()
        this.detailsForm.get('tehsil').reset()
        this.detailsForm.get('city').reset()
        this.detailsForm.get('pinCode').reset()
        this.detailsForm.get('postOffice').reset()
    }
    resetFieldForItemDetails() {
        this.detailsForm.get('mechanicName').reset()
        this.detailsForm.get('mechanic').reset()
        this.detailsForm.get('retailer').reset()
        this.detailsForm.get('mobileNumber').reset()
        this.detailsForm.get('retailerName').reset()
        this.detailsForm.get('address1').reset()
        this.detailsForm.get('address2').reset()
        this.detailsForm.get('state').reset()
        this.detailsForm.get('district').reset()
        this.detailsForm.get('tehsil').reset()
        this.detailsForm.get('city').reset()
        this.detailsForm.get('pinCode').reset()
        this.detailsForm.get('postOffice').reset()
    }
    patchValueForViewQuotation(value: ViewPartQuotation){
        this.detailsForm.patchValue(value.headerResponse)
        this.detailsForm.get('customerType').patchValue(value.headerResponse.customerType )
        this.detailsForm.get('quotationDate').patchValue(new Date(value.headerResponse.quotationDate))
        this.detailsForm.get('mobileNumber').patchValue({ mobileNumber: value.headerResponse.mobileNumber })
        this.detailsForm.get('retailer').patchValue({ value: value.headerResponse.retailer })
        this.detailsForm.get('mechanic').patchValue({ value: value.headerResponse.retailer })
        this.detailsForm.get('mechanicName').patchValue(value.headerResponse.customerName)
        this.detailsForm.get('retailerName').patchValue(value.headerResponse.customerName)
        this.detailsForm.get('address1').patchValue(value.headerResponse.customerAddress1)
        this.detailsForm.get('address2').patchValue(value.headerResponse.customerAddress2)
        this.detailsForm.get('state').patchValue({id: value.headerResponse.stateId, state: value.headerResponse.state })
        this.detailsForm.get('district').patchValue({id: value.headerResponse.districtId, district: value.headerResponse.district })
        this.detailsForm.get('tehsil').patchValue({id: value.headerResponse.tehsilId, tehsil: value.headerResponse.tehsil })
        this.detailsForm.get('city').patchValue({id: value.headerResponse.cityId, city: value.headerResponse.city })
        this.detailsForm.get('pinCode').patchValue({id: value.headerResponse.pinCodeId, pinCode: value.headerResponse.pinCode})
        this.detailsForm.get('postOffice').patchValue({postOffice: value.headerResponse.postOffice})
        console.log("partDetails--->", value.partDetails)
        this.partQuotationTableRow.patchValue(value.partDetails)
        this.partQuotationTotalForm.patchValue(value.partDetails[0])
        this.totalAmt = value.headerResponse.totalQuotationAmount;
    }

    markFormAsTouched() {
        this.partQuotationForm.markAllAsTouched();
    }
}