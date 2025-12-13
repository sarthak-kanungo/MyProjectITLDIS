import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { QuotationSearchDomain } from 'quotation-dto';
import { QuoatProspectDetailsService } from './quoat-prospect-details.service';

@Component({
  selector: 'app-quoat-prospect-details',
  templateUrl: './quoat-prospect-details.component.html',
  styleUrls: ['./quoat-prospect-details.component.scss'],
  providers: [
    QuoatProspectDetailsService
  ]
})
export class QuoatProspectDetailsComponent implements OnInit {

  disable = true;
  prospects
  postoffices
  villages

  prospectdetails: FormGroup;
  @Input() set dataAutoPopulatebyEnquiryNo(value: QuotationSearchDomain) {
    if (this.prospectdetails) {
      this.prospectdetails.patchValue(value)
      this.prospectdetails.controls.dob.patchValue(new Date(value.dob))
      this.prospectdetails.controls.aniversarydate.patchValue(new Date(value.anniversaryDate))
    }
  }
  @Output() validatedFormData = new EventEmitter<object>();
  @Input()
  public set isValidateForm(v: string) {
    if (!!v) {
      this.prospectdetails.markAllAsTouched();
      if (this.prospectdetails.valid || this.prospectdetails.status === 'DISABLED') {
        this.validatedFormData.emit({ value: this.prospectdetails.getRawValue(), isValid: true });
      } else {
        this.validatedFormData.emit({ isValid: false });
      }
    }
  }

  constructor(private fb: FormBuilder,
    private quoatProspectDetailsService: QuoatProspectDetailsService) { }

  ngOnInit() {
    this.prospectdetails = this.quoatProspectDetailsService.createprospectdetails()
  }
  ngAfterViewInit() {
    this.quoatProspectDetailsService.getQuotationDataForView();
  }
}
