import { Component, OnInit, Input } from '@angular/core';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { PartIssueSearchApiService } from './part-issue-search-api.service';
import { DateAdapter, MAT_DATE_FORMATS, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, AbstractControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import { SelectList } from '../../../../../core/model/select-list.model';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';

@Component({
  selector: 'app-part-issue-search',
  templateUrl: './part-issue-search.component.html',
  styleUrls: ['./part-issue-search.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    PartIssueSearchApiService
  ]
})
export class PartIssueSearchComponent implements OnInit {
  requisitionNoList$: Observable<SelectList[]>;
  requisitionPurposeList$: Observable<SelectList[]>;
  jobCardNoList$: Observable<SelectList[]>;
  partIssueNoList$: Observable<SelectList[]>;
  @Input() partIssueSearchForm: FormGroup;
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private partIssueSearchApiService: PartIssueSearchApiService,
    private autocompleteService: AutocompleteService
  ) { }

  ngOnInit() {
    this.getRequisitionPurpose();
    this.requisitionNoValueChanges();
    this.jobCardNoValueChanges();
    this.partIssueNoValueChanges();
  }
  ngAfterViewInit(){
  
  }
  fromDateSelected(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.partIssueSearchForm.get('requisitionToDate').value > this.maxDate)
        this.partIssueSearchForm.get('requisitionToDate').patchValue(this.maxDate);
    }
  }
  fromDateSelected1(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.partIssueSearchForm.get('issueToDate').value > this.maxDate)
        this.partIssueSearchForm.get('issueToDate').patchValue(this.maxDate);
    }
  }
  private requisitionNoValueChanges() {
    this.partIssueSearchForm.get('requisitionNo').valueChanges.subscribe(val => {
      if (val && typeof val === 'string') {
        this.requisitionNoList$ = this.partIssueSearchApiService.searchPartRequisitionNo(val);
      }
      this.autocompleteService.validateSelectedFromList(val, this.requisitionNoList$, this.partIssueSearchForm.get('requisitionNo'));
    })
  }
  private jobCardNoValueChanges() {
    this.partIssueSearchForm.get('jobCardNo').valueChanges.subscribe(val => {
      if (val && typeof val === 'string') {
        this.jobCardNoList$ = this.partIssueSearchApiService.searchJobCardNo(val);
      }
      this.autocompleteService.validateSelectedFromList(val, this.jobCardNoList$, this.partIssueSearchForm.get('jobCardNo'));
    })
  }
  private partIssueNoValueChanges() {
    this.partIssueSearchForm.get('partIssueNo').valueChanges.subscribe(val => {
      if (val && typeof val === 'string') {
        this.partIssueNoList$ = this.partIssueSearchApiService.searchApiNo(val);
      }
      this.autocompleteService.validateSelectedFromList(val, this.partIssueNoList$, this.partIssueSearchForm.get('partIssueNo'));
    })
  }
  private getRequisitionPurpose() {
    this.requisitionPurposeList$ = this.partIssueSearchApiService.getRequisitionPurpose();
  }
  optionSelected(event: MatAutocompleteSelectedEvent, formControl: AbstractControl) {
    this.autocompleteService.removeSelectedFromListError(formControl);
  }
}
