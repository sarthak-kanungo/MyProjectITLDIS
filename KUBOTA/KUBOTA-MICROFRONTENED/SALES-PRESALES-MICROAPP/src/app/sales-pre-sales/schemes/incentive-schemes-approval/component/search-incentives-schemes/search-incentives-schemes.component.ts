import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { DropDownSchemeTypeDomain } from 'IncentiveSchemeMasterModule';
import { SchemesCommonService } from '../../../schemes-common-service/schemes-common.service';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-search-incentives-schemes',
  templateUrl: './search-incentives-schemes.component.html',
  styleUrls: ['./search-incentives-schemes.component.scss'],
  providers: [SchemesCommonService]
})
export class SearchIncentivesSchemesComponent implements OnInit, AfterViewInit {

  status: string[] = [
    'ACTIVE', 'INACTIVE',
  ];
  schemetypes: string[] = [
    'Wholesale', 'Retail', 'Exchange', 'DSE', 'TM/BM/SM', 'GM', 'Loyalty', 'Referal', 'Booking Scheme'
  ];
  dropdownSchemeTypeDomain: BaseDto<Array<DropDownSchemeTypeDomain>>
  SearchIncentiveSchemes: FormGroup
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(private fb: FormBuilder,
    public dialog: MatDialog,
    private schemesCommonService: SchemesCommonService) { }

  ngOnInit() {
    this.createSearchIncentiveSchemes()
    this.dropDownSchemeType()
  }
  ngAfterViewInit() {
    this.maxDate = this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth() - 1);
    this.minDate = backDate;
    this.SearchIncentiveSchemes.get('schemefromdate').patchValue(backDate);
    this.SearchIncentiveSchemes.get('schemetodate').patchValue(new Date());
  }
  fromDateChange(event) {

    // console.log('event', event)
    // this.changedInDate = event.target.value
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.SearchIncentiveSchemes.get('schemetodate').value > this.maxDate)
        this.SearchIncentiveSchemes.get('schemetodate').patchValue(this.maxDate);
    }

  }
  createSearchIncentiveSchemes() {
    this.SearchIncentiveSchemes = this.fb.group({
      schemeno: ['', Validators.compose([Validators.required])],
      status: ['', Validators.compose([Validators.required])],
      schemeType: ['', Validators.compose([Validators.required])],
      schemefromdate: ['', Validators.compose([Validators.required])],
      schemetodate: ['', Validators.compose([Validators.required])],
    })
  }
  dropDownSchemeType() {
    this.schemesCommonService.dropdownSchemeType().subscribe(response => {
      console.log('SchemeType', response);
      this.dropdownSchemeTypeDomain = response as BaseDto<Array<DropDownSchemeTypeDomain>>
    })
  }
}
