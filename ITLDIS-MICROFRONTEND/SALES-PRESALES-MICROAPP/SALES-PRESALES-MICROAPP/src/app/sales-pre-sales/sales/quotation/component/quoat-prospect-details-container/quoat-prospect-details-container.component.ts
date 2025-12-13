import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { QuotationSearchDomain } from 'quotation-dto';

@Component({
  selector: 'app-quoat-prospect-details-container',
  templateUrl: './quoat-prospect-details-container.component.html',
  styleUrls: ['./quoat-prospect-details-container.component.scss']
})
export class QuoatProspectDetailsContainerComponent implements OnInit {

  @Input() dataAutoPopulatebyEnquiryNo: QuotationSearchDomain;
  @Input() isValidateForm: boolean;
  @Output() validatedFormData = new EventEmitter<object>();
  constructor() { }

  ngOnInit() {
  }
  validatedFormDataFn(formData) {
    if (formData) {
      this.validatedFormData.emit(formData);
    }
  }
}
