import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { QuotationCreateStoreService } from '../../pages/quotation-create/quotation-create-store.service';

@Injectable()
export class QuotationService {

  quotationFrom: FormGroup;
  implementsDetailsForm: FormGroup;

  constructor(
    private httpClient: HttpClient,
    private fb: FormBuilder,
    private quotationCreateStoreService: QuotationCreateStoreService

  ) { }

  createQuotation() {
    this.quotationFrom = this.fb.group({
      enquiryNo: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
      quotationDate: [{ value: null, disabled: true }, Validators.compose([])],
      tentativePurchaseDate: [{ value: null, disabled: true }, Validators.compose([])],
      enquiryStatus: [{ value: null, disabled: true }, Validators.compose([])],
      salesPerson: [{ value: null, disabled: true }, Validators.compose([])],
      source: [{ value: null, disabled: true }, Validators.compose([])],
      purposeOfPurchase: [{ value: null, disabled: true }, Validators.compose([])],
      enquiryDate: [{ value: null, disabled: true }, Validators.compose([])],
      quotationNumber: [{ value: null, disabled: true }, Validators.compose([])],
    })
    this.subscribeResetForm();
    return this.quotationFrom;
  }
  private subscribeResetForm() {
    this.quotationCreateStoreService.resetAll().subscribe(res => {
      if (res) {
        this.quotationFrom.reset();
        this.quotationFrom.controls.enquiryNo.enable();  
      }
    })
  }
  implementsdetailsForm() {
    this.implementsDetailsForm = this.fb.group({
      implementsDetails: this.fb.array([])
    });
    return this.implementsDetailsForm;
  }
  getQuotationDataForView() {
    this.quotationCreateStoreService.getQuotionaRecordForView().subscribe(res => {
      let enquiry = res['enquiry'] ? res['enquiry'] : {};
      let modifiedData = { quotationDate: res.createdDate, quotationNumber: { quotationNumber: res.quotationNumber } }
      this.quotationFrom.patchValue({ ...res, ...res['enquiry'], ...modifiedData }, { onlySelf: true, emitEvent: false });
      console.log('res[enquiry]', res['enquiry'], { enquiryNumber: res['enquiry'].enquiryNumber, id: res['enquiry'].id });

      this.quotationFrom.disable({ emitEvent: false });
      if (res['enquiry']) {
        this.quotationFrom.get('enquiryNo').patchValue({ enquiryNumber: res['enquiry'].enquiryNumber, id: res['enquiry'].id }, { onlySelf: true, emitEvent: false });
      }
    });
  }
}
