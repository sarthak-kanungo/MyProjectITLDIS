import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { WarrentyClaimRequestSearchWebService } from '../../../warrenty-claim-request/component/warrenty-claim-request-search/warrenty-claim-request-search-web.service';
import { WcrDropdown } from '../../../warrenty-claim-request/domain/warrenty-claim-request.domain';
import { PcrSearchWebService } from '../../../product-concern-report/component/pcr-search/pcr-search-web.service';
import { SearchPCRAutoComplete, SearchByModel } from '../../../product-concern-report/domain/product-concern-report.domain';
import { LogSheetFailureWebService } from '../../../log-sheet/component/log-sheet-failure-parts/log-sheet-failure-web.service';
import { AutoCompleteResult } from '../../../log-sheet/domain/log-sheet.domain';
import { MatDatepickerInput } from '@angular/material/datepicker';
import { WcrReportCreatePageWebService } from '../wcr-report-create-page/wcr-report-create-page-web.service';
import { WcrReportAutoComplete } from '../../domain/wcr-report.domain';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';

@Component({
  selector: 'app-wcr-report',
  templateUrl: './wcr-report.component.html',
  styleUrls: ['./wcr-report.component.scss'],
  providers: [WarrentyClaimRequestSearchWebService, PcrPageWebService, PcrSearchWebService, LogSheetFailureWebService, WcrReportCreatePageWebService]
})
export class WcrReportComponent implements OnInit {
  @Input() wcrReportForm: FormGroup;
  warrantyClaim: Array<string> = ['Claim 1', 'Claim 2', 'Claim 3', 'Claim 4'];
  wcrType: WcrDropdown;
  chassisData: SearchPCRAutoComplete;
  model: SearchByModel;
  autoCompleteData: AutoCompleteResult;
  endDate: Date;
  dealerCode: WcrReportAutoComplete;
  newdate = new Date()
  fromDate: Date
  toDate: Date

  constructor(
    private warrentyClaimRequestSearchWebService: WarrentyClaimRequestSearchWebService,
    private pcrPageWebService: PcrPageWebService,
    private pcrSearchWebService: PcrSearchWebService,
    private logSheetFailureService: LogSheetFailureWebService,
    private wcrReportCreatePageWebService: WcrReportCreatePageWebService
  ) { }

  ngOnInit() {
    this.formValueChanges();
    this.searchWcrType();
    this.dropDownStatusModel();
    this.getSystemDate();
   
  }
  ngAfterViewInit() {
  

  }
  private formValueChanges() {
    this.wcrReportForm.get('chassisNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompleteSearchChassisNo(val);
      }
    });
    this.wcrReportForm.get('partNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompletePartNumber(val);
      }
    });
    this.wcrReportForm.get('dealerCode').valueChanges.subscribe(val => {
      if (val) {
        this.getDealerCodeAutocomplete(val);
      }
    });
  }

  private searchWcrType() {
    this.warrentyClaimRequestSearchWebService.searchWcrType().subscribe(res => {
      this.wcrType = res;
    });
  }
  private autoCompleteSearchChassisNo(txt: string) {
    this.pcrSearchWebService.autoCompleteSearchChassisNo(txt).subscribe(res => {
      this.chassisData = res;
    });
  }
  private dropDownStatusModel() {
    this.pcrSearchWebService.dropDownStatusModel().subscribe(res => {
      this.model = res;
    });
  }
  private autoCompletePartNumber(txt: string) {
    this.logSheetFailureService.autoCompletePartNumber(txt).subscribe(res => {
      this.autoCompleteData = res;
    })
  }
  private getDealerCodeAutocomplete(txt: string) {
    this.wcrReportCreatePageWebService.getDealerCodeAutocomplete(txt).subscribe(res => {
      this.dealerCode = res;
      console.log('this.dealerCode', this.dealerCode);
    });
  }

  private getSystemDate() {
    this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
      this.toDate = new Date(res);
    });
  }
  selectedDealer(event: MatAutocompleteSelectedEvent) {
    this.wcrReportForm.get('dealerName').patchValue(event.option.value.dealerName);
  }
  displayCodeFn(obj: WcrReportAutoComplete): string | number | undefined {
    return obj ? obj.code : undefined;
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.fromDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.toDate = this.newdate;
      else
        this.toDate = maxDate;
      if (this.wcrReportForm.get('toDate').value > this.toDate)
        this.wcrReportForm.get('toDate').patchValue(this.toDate);
    }
  }
}
