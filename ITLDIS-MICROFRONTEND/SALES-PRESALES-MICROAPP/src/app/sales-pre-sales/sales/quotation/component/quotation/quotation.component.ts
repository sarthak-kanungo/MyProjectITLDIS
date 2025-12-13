import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { QuotationService } from './quotation.service';
import { BaseDto } from 'BaseDto';
import { EnquiryCodeDomain } from 'quotation-dto';

@Component({
  selector: 'app-quotation',
  templateUrl: './quotation.component.html',
  styleUrls: ['./quotation.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    QuotationService
  ]
})
export class QuotationComponent implements OnInit, AfterViewInit {
  quotationFrom: FormGroup;
  disable = true;
  purchase: string[] = [
    'Own Use', 'Hire',
  ];

  @Output() autoEnquiryNo: EventEmitter<string> = new EventEmitter<string>()
  @Output() autoPopulateByenquiryNo = new EventEmitter<BaseDto<Array<EnquiryCodeDomain>>>()
  @Input() enquiryCodeDomain: BaseDto<Array<EnquiryCodeDomain>>
  @Input() isViewOnly: boolean;
  @Input() set quotationSearchDomain(list) {
    if (this.quotationFrom !== undefined)
      this.quotationFrom.patchValue(list.result)
  }
  @Input()
  public set isValidateForm(v: string) {
    if (!!v) {
      this.quotationFrom.markAllAsTouched();
      if (this.quotationFrom.valid) {
        this.validatedFormData.emit({ value: this.quotationFrom.getRawValue(), isValid: true });
      } else {
        this.validatedFormData.emit({ isValid: false });
      }
    }
  }
  @Input() quotationNumberList;
  @Output() validatedFormData = new EventEmitter<object>();
  @Output() quotationNumberListChange = new EventEmitter<any>();

  @Output() quotationNumberOptionSelected = new EventEmitter<string>();
  constructor(
    private quotationService: QuotationService
  ) { }

  ngOnInit() {
    this.quotationFrom = this.quotationService.createQuotation();
    this.quotationFrom.controls.enquiryNo.valueChanges.subscribe(value => {
      console.log('quotationFrom.controls.enquiryNo value', value);
      if (!!value && typeof value === 'object') {
        this.autoEnquiryNo.emit(value.enquiryCode)
        return;
      }
      this.autoEnquiryNo.emit(value)
    });
    this.quotationFrom.get('quotationNumber').valueChanges.subscribe(value => {

      this.quotationNumberListChange.emit(value);
    });
  }
  ngAfterViewInit(): void {
    if (this.isViewOnly) {
      this.quotationService.getQuotationDataForView();
    }
  }
  optionSelectedEnquiryNo(event) {
    //this.quotationFrom.controls.enquiryNo.disable();  
    this.autoPopulateByenquiryNo.emit(event.option.value.enquiryNumber)
  }
  enquiryNoDisplayFn(enquiry): string | undefined {
    return enquiry ? enquiry.enquiryNumber : undefined;
  }
  quotationNumberDisplayFn(quotation): string | undefined {
    return quotation ? quotation.quotationNumber : undefined;
  }
  quotationNumberSelected(event) {
    this.quotationNumberOptionSelected.emit(event.option.value);
  }
}
