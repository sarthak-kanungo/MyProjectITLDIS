import { Component, OnInit, Input } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { LogSheetWebService } from "../log-sheet/log-sheet-web.service";
import { AutoCompleteResult, LogsheetType } from "../../domain/log-sheet.domain";
import { LogSheetSearchService } from "./log-sheet-search.service";
import { PcrSearchWebService } from "../../../product-concern-report/component/pcr-search/pcr-search-web.service";
import { PcrWebService } from "../../../product-concern-report/component/pcr/pcr-web.service";
import {
  SearchPCRAutoComplete,
  SearchByModel,
  FailureType
} from "../../../product-concern-report/domain/product-concern-report.domain";
import { MatDatepickerInput } from '@angular/material';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';
@Component({
  selector: "app-log-sheet-search",
  templateUrl: "./log-sheet-search.component.html",
  styleUrls: ["./log-sheet-search.component.scss"],
  providers: [
    PcrWebService,
    PcrPageWebService,
    PcrSearchWebService,
    LogSheetWebService,
    LogSheetSearchService
  ]
})
export class LogSheetSearchComponent implements OnInit {
  @Input() searchForm: FormGroup;
  isAdvanceSearch: boolean;
  status: any[] = [];
  logSheetData: AutoCompleteResult;
  chassisData: AutoCompleteResult;
  jobCodeData: AutoCompleteResult;
  mobileData: AutoCompleteResult;
  logsheetType: LogsheetType[];
  failureType: FailureType[];
  toDate: Date;
  toDate1: Date;
  fromDate: Date;
  fromDate1: Date;
  newdate= new Date()
  constructor(
    private pcrSearchWebService: PcrSearchWebService,
    private pcrPageWebService: PcrPageWebService,
    private logSheetService: LogSheetWebService,
    private pcrService: PcrWebService,
    private logSheetSearchService: LogSheetSearchService,
  ) {}

  ngOnInit() {
    this.formValueChanges();
    this.dropDownStatusModel();
    this.dropDownLogsheetType();
    this.dropDownFailureType();
    this.getSystemDate();
  }
  ngAfterViewInit() {
    this.toDate= this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth()-1);
    this.fromDate = backDate;
    this.fromDate1 = backDate;
    this.searchForm.get('logsheetFromDate').patchValue(backDate);
    this.searchForm.get('logsheetToDate').patchValue(new Date());
    this.searchForm.get('jobCardFromDate').reset()
    this.searchForm.get('jobCardToDate').reset()

  }
  formValueChanges() {
    this.searchForm.get("chassisNo").valueChanges.subscribe(val => {
      if (val && typeof val == 'string') {
        this.autoCompleteSearchChassisNo(val);
      }
    });

    this.searchForm.get("jobCardNo").valueChanges.subscribe(val => {
      if (val && typeof val == 'string') {
        this.searchAutoCompleteJobCode(val);
      }
    });
    this.searchForm.get("logsheetNo").valueChanges.subscribe(val => {
      if (val && typeof val == 'string') {
        this.searchLogsheetNo(val);
      }
    });
    this.searchForm.get("mobileNo").valueChanges.subscribe(val => {
      if (val && typeof val == 'string') {
        this.searchCustomerMobileNo(val);
      }
    });
  }

  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch;
  }

  private searchLogsheetNo(txt: string) {
    this.logSheetSearchService.searchLogsheetNo(txt).subscribe(
      res => {
        this.logSheetData = res;
      },
      err => {
        console.log("err", err);
      }
    );
  }
  private dropDownLogsheetType() {
    this.logSheetService.dropDownLogsheetType().subscribe(res => {
      this.logsheetType = res;
    });
  }

  private autoCompleteSearchChassisNo(txt: string) {
    this.logSheetService.autoCompleteChassisNoInJobCard(txt).subscribe(res => {
      this.chassisData = res;
    });
  }
  private searchAutoCompleteJobCode(txt: string) {
    this.logSheetService.searchAutoCompleteJobCode(txt).subscribe(res => {
      this.jobCodeData = res;
    });
  }
  private dropDownStatusModel() {
    this.logSheetSearchService.getLookupByCode('Logsheet_Status').subscribe(res => {
      this.status = res['result'];
    });
  }
  private dropDownFailureType() {
    this.pcrService.dropDownFailureType('0').subscribe(
      result => {
        this.failureType = result;
      },
      err => {
        console.log("err", err);
      }
    );
  }

  private searchCustomerMobileNo(txt: string) {
    this.logSheetSearchService.searchCustomerMobileNo(txt).subscribe(
      res => {
        this.mobileData = res;
      },
      err => {
        console.log("err", err);
      }
    );
  }

  private getSystemDate() {
    this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
      this.toDate = new Date(res);
    });
  }

  displayCodeFn(obj: SearchPCRAutoComplete): string | number | undefined {
    return obj ? obj.code : undefined;
  }

  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    fieldName == 'logsheet' ? this.toDate = event.value : this.toDate1 = event.value;
    if (event && event['value']) {
      if(fieldName != 'logsheet' ){
        this.fromDate1 = new Date(event['value']);
        let maxDate = new Date(event['value']);
        maxDate.setMonth(maxDate.getMonth() + 1);
        if (maxDate > this.newdate)
          this.toDate1 = this.newdate;
        else
          this.toDate1 = maxDate;
        if (this.searchForm.get('jobCardToDate').value > this.toDate1)
          this.searchForm.get('jobCardToDate').patchValue(this.toDate1);
      }else{
        this.fromDate = new Date(event['value']);
        let maxDate = new Date(event['value']);
        maxDate.setMonth(maxDate.getMonth() + 1);
        if (maxDate > this.newdate)
          this.toDate = this.newdate;
        else
          this.toDate = maxDate;
        if (this.searchForm.get('logsheetToDate').value > this.toDate)
          this.searchForm.get('logsheetToDate').patchValue(this.toDate);
      }
    }
  }
}
