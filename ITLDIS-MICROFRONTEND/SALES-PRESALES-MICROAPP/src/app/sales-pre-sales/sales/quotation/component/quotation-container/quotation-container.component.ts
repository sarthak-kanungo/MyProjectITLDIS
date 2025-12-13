import { Component, OnInit, Output, EventEmitter, Input, OnChanges } from '@angular/core';
import { QuotationContainerService } from './quotation-container.service';
import { BaseDto } from 'BaseDto';
import { EnquiryCodeDomain, QuotationSearchDomain } from 'quotation-dto';
import { Router, ActivatedRoute } from '@angular/router';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { Subject } from 'rxjs';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';

@Component({
  selector: 'app-quotation-container',
  templateUrl: './quotation-container.component.html',
  styleUrls: ['./quotation-container.component.scss'],
  providers: [QuotationContainerService,EnquiryCommonService]
})
export class QuotationContainerComponent implements OnInit, OnChanges {
  enquiryCodeDomain: BaseDto<Array<EnquiryCodeDomain>>
  quotationSearchDomain: BaseDto<QuotationSearchDomain>
  @Output() autoPopulateDatabyEnquiryNo = new EventEmitter<QuotationSearchDomain>();
  @Input() isValidateForm: boolean;
  @Output() validatedFormData = new EventEmitter<object>();
  private _isViewOnly: boolean;
  _quotationCodeList: Object[] = [];
  test: any;
  loginUser: import("LoginDto").StorageLoginUser;
  @Input()
  public set isViewOnly(v: boolean) {
    if (typeof v === 'boolean') {
      this._isViewOnly = v;
    }
  }

  public get isViewOnly(): boolean {
    return this._isViewOnly;
  }
  constructor(
    private quotationContainerService: QuotationContainerService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private localStorageService: LocalStorageService,
    private enquiryCommonService: EnquiryCommonService
  ) { }

  ngOnInit() {
    this.loginUser = this.localStorageService.getLoginUser();
  }
  ngOnChanges() {
    // console.log('this.test', this.test);
  }
  // test(arg0: string, test: any) {
  //   throw new Error("Method not implemented.");
  // }
  autoEnquiryNo(event) {
    this.enquiryCommonService.searchEnquiryCode(event, "QUOTATION").subscribe(response => {
      this.enquiryCodeDomain = response as BaseDto<Array<EnquiryCodeDomain>>
    })
  }

  autoPopulateBydataenquiryNo(event) {
    this.quotationContainerService.searchByEnquiryNo(event).subscribe(response => {
      this.quotationSearchDomain = response as BaseDto<QuotationSearchDomain>
      console.log(this.quotationSearchDomain.result)
      this.autoPopulateDatabyEnquiryNo.emit(this.quotationSearchDomain.result)
    })
  }
  validatedFormDataFn(formData) {
    console.log('formData', formData);
    if (formData) {
      this.validatedFormData.emit(formData);
    }
  }

  public set quotationNumberList(searchValue: any) {
    this.quotationContainerService.getQuotationCodeList(searchValue).subscribe(list => {
      this._quotationCodeList = list['result'] as Array<Object>;
    });
  }
  public get quotationNumberList(): any {
    return this._quotationCodeList
  }
  routeToViewQuoatation(quotation: string) {
    console.log('quotationNumber', quotation);

    this.router.navigate(['../view', quotation['quotationNumber']], { relativeTo: this.activatedRoute });
  }

}
