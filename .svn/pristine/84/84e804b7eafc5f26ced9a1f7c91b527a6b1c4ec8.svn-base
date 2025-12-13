import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { PcrSearchWebService } from '../../../product-concern-report/component/pcr-search/pcr-search-web.service';
import { SearchByModel } from '../../../product-concern-report/domain/product-concern-report.domain';
import { WpdcSearchPageWebService } from '../wpdc-search-page/wpdc-search-page-web.service';
import { AutoCompleteResult } from '../../../retro-fitment-campaign/domain/retro-fitment-campaign.domain';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';

@Component({
  selector: 'app-wpdc-search',
  templateUrl: './wpdc-search.component.html',
  styleUrls: ['./wpdc-search.component.scss'],
  providers: [PcrSearchWebService, WpdcSearchPageWebService, PcrPageWebService]
})
export class WpdcSearchComponent implements OnInit {
  @Input() searchForm: FormGroup;
  isAdvanceSearch: boolean;
  toDate: Date;
  toDate1: Date;
  model: SearchByModel;
  autoCompleteData: AutoCompleteResult;
  fromDate: Date;
  newdate= new Date()
  constructor(
    private pcrSearchWebService: PcrSearchWebService,
    private pcrPageWebService: PcrPageWebService,
    private wpdcSearchPageWebService: WpdcSearchPageWebService
  ) {}

  ngOnInit() {
    this.dropDownStatusModel();
    this.formValueChange();
    this.getSystemDate();
  }
  ngAfterViewInit() {
    
  }
  private formValueChange() {
    this.searchForm.get('wcrNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchAutoCompleteWcrNo(val);
      }
    })
  }

  private searchAutoCompleteWcrNo(txt: string) {
    this.wpdcSearchPageWebService.searchAutoCompleteWcrNo(txt).subscribe(res => {
      this.autoCompleteData = res;
    });
  }

  private dropDownStatusModel() {
    this.pcrSearchWebService.dropDownStatusModel().subscribe(res => {
      this.model = res;
    });
  }

  private getSystemDate() {
    this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
      this.toDate = new Date(res);
    });
  }

  onClickAdvanceSearch() {
    this.isAdvanceSearch = !this.isAdvanceSearch;
  }
  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    if (fieldName == 'dc') {
      this.toDate = event.value;
    } else {
      this.toDate1 = event.value;
    }
    if (event && event['value']) {
      this.fromDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.toDate = this.newdate;
      else
        this.toDate = maxDate;
      if (this.searchForm.get('dcToDate').value > this.toDate)
        this.searchForm.get('dcToDate').patchValue(this.toDate);
    }
  }

  displayCodeFn(obj): string | number | undefined {
    return obj ? obj.code : undefined;
  }

}
