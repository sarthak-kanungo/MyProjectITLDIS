import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { QuotationCreateStoreService } from '../../pages/quotation-create/quotation-create-store.service';
import { EnquiryAtViewQuotationDTO } from '../../pages/quotation-create/create-quotation';

@Injectable()
export class QuoatProspectDetailsService {

  prospectdetailsForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private quotationCreateStoreService: QuotationCreateStoreService
  ) { }

  createprospectdetails() {
    this.prospectdetailsForm = this.fb.group({
      prospectCode: [{ value: '', disabled: true }, Validators.compose([])],
      prospectType: [{ value: '', disabled: true }, Validators.compose([])],
      companyName: [{ value: '', disabled: true }, Validators.compose([])],
      firstName: [{ value: '', disabled: true }, Validators.compose([])],
      middleName: [{ value: '', disabled: true }, Validators.compose([])],
      lastName: [{ value: '', disabled: true }, Validators.compose([])],
      fatherName: [{ value: '', disabled: true }, Validators.compose([])],
      mobileNumber: [{ value: '', disabled: true }, Validators.compose([])],
      whatsAppNumber: [{ value: '', disabled: true }, Validators.compose([])],
      alternateMobileNumber: [{ value: '', disabled: true }, Validators.compose([])],
      std: [{ value: '', disabled: true }, Validators.compose([])],
      telephoneNo: [{ value: '', disabled: true }, Validators.compose([])],
      emailId: [{ value: '', disabled: true }, Validators.compose([])],
      address1: [{ value: '', disabled: true }, Validators.compose([])],
      address2: [{ value: '', disabled: true }, Validators.compose([])],
      address3: [{ value: '', disabled: true }, Validators.compose([])],
      pinCode: [{ value: '', disabled: true }, Validators.compose([])],
      postOffice: [{ value: '', disabled: true }, Validators.compose([])],
      // village: [{ value: '', disabled: true }, Validators.compose([])],
      tehsil: [{ value: '', disabled: true }, Validators.compose([])],
      district: [{ value: '', disabled: true }, Validators.compose([])],
      city: [{ value: '', disabled: true }, Validators.compose([])],
      state: [{ value: '', disabled: true }, Validators.compose([])],
      country: [{ value: '', disabled: true }, Validators.compose([])],
      language: [{ value: '', disabled: true }, Validators.compose([])],
      dob: [{ value: '', disabled: true }, Validators.compose([])],
      aniversarydate: [{ value: '', disabled: true }, Validators.compose([])],
      gstno: [{ value: '', disabled: true }, Validators.compose([])],
      panNo: [{ value: '', disabled: true }, Validators.compose([])],
      product: [{ value: '', disabled: true }, Validators.compose([])],
      series: [{ value: '', disabled: true }, Validators.compose([])],
      model: [{ value: '', disabled: true }, Validators.compose([])],
      variant: [{ value: '', disabled: true }, Validators.compose([])],
    });
    this.subscribeResetForm();
    return this.prospectdetailsForm;
  }
  private subscribeResetForm() {
    this.quotationCreateStoreService.resetAll().subscribe(res => {
      if (res) {
        this.prospectdetailsForm.reset();
      }
    })
  }
  getQuotationDataForView() {
    this.quotationCreateStoreService.getQuotionaRecordForView().subscribe(res => {
      console.log('getQuotationDataForView ------->', res['enquiry']);
      let enquiry = res.enquiry ? res.enquiry : {} as null;
      //enquiry.prospectCode = enquiry.customerMaster.customerCode;
      //enquiry.prospectType = enquiry.customerMaster.customerType;
      this.prospectdetailsForm.patchValue({ ...enquiry, ...{ panNo: enquiry.panNumber, telephoneNo: enquiry.telephoneNumber, prospectCode :enquiry.customerMaster.customerCode, prospectType:enquiry.customerMaster.customerType} });
      this.prospectdetailsForm.disable();
    });
  }
}
