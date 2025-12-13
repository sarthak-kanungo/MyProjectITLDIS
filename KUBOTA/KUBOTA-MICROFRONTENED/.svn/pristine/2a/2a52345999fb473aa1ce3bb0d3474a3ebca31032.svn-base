import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { PartRequisitionSearchApiService } from './part-requisition-search-api.service';
import { SparePartRequisitionSearchFilter } from '../../domain/part-requisition.domain';
import { Router } from '@angular/router';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-part-requisition-search',
  templateUrl: './part-requisition-search.component.html',
  styleUrls: ['./part-requisition-search.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    PartRequisitionSearchApiService
  ]
})
export class PartRequisitionSearchComponent implements OnInit {
  @Input() partRequisitionSearchForm: FormGroup;
  requisitionNoList$: Observable<SelectList[]>;
  requisitionPurposeList$: Observable<SelectList[]>;
  jobCardNoList$: Observable<SelectList[]>;
  @Output() searchChange = new EventEmitter<SparePartRequisitionSearchFilter>();
  @Output() clearData = new EventEmitter<any>();
  searchFilter: any;
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private partRequisitionSearchApiService: PartRequisitionSearchApiService,
    private iFrameService: IFrameService,
    private autocompleteService: AutocompleteService, private toastr: ToastrService) { }

  ngOnInit() {
    this.getRequisitionPurpose();
    this.requisitionNoValueChanges();
    this.jobCardNoValueChanges();
    this.pageValueChanges();
    this.sizeValueChanges();
  }
  ngAfterViewInit() {

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
      if (this.partRequisitionSearchForm.get('toDate').value > this.maxDate)
        this.partRequisitionSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }
  private requisitionNoValueChanges() {
    this.partRequisitionSearchForm.get('requisitionNo').valueChanges.subscribe(val => {
      if (val && typeof val === 'string') {
        this.requisitionNoList$ = this.partRequisitionSearchApiService.searchPartRequisitionNo(val);
      }
      this.autocompleteService.validateSelectedFromList(val, this.requisitionNoList$, this.partRequisitionSearchForm.get('requisitionNo'));
    })
  }
  private jobCardNoValueChanges() {
    this.partRequisitionSearchForm.get('jobCardNo').valueChanges.subscribe(val => {
      if (val && typeof val === 'string') {
        this.jobCardNoList$ = this.partRequisitionSearchApiService.searchJobCardNo(val);
      }
      this.autocompleteService.validateSelectedFromList(val, this.jobCardNoList$, this.partRequisitionSearchForm.get('jobCardNo'));
    })
  }
  private getRequisitionPurpose() {
    this.requisitionPurposeList$ = this.partRequisitionSearchApiService.getRequisitionPurpose();
  }
  requisitionNoOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.autocompleteService.removeSelectedFromListError(this.partRequisitionSearchForm.get('requisitionNo'));
  }
  jobCardNoOptionSelected(event: MatAutocompleteSelectedEvent) {
    //this.autocompleteService.removeSelectedFromListError(this.partRequisitionSearchForm.get('jobCardNo'));
  }
  private pageValueChanges() {
    this.partRequisitionSearchForm.get('page').valueChanges.subscribe(val => {
      this.setQueryParams();
    })
  }
  private sizeValueChanges() {
    this.partRequisitionSearchForm.get('size').valueChanges.subscribe(val => {
      this.setQueryParams();
    })
  }
  searchResult() {
    if (this.checkValidDatesInput()) {
      if (this.partRequisitionSearchForm.get('jobCardNo').value || this.partRequisitionSearchForm.get('requisitionNo').value || this.partRequisitionSearchForm.get('requisitionPurpose').value || this.partRequisitionSearchForm.get('fromDate').value || this.partRequisitionSearchForm.get('toDate').value) {
        this.setQueryParams();
        this.searchChange.emit(this.partRequisitionSearchForm.getRawValue());
      }
      else if (this.partRequisitionSearchForm.get('fromDate').value == null && this.partRequisitionSearchForm.get('toDate').value == null) {
        this.toastr.error("Please fill atleast one input field.");
      }
    }
    else {
      this.toastr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.partRequisitionSearchForm.value

    fltEnqObj.fromDate = this.partRequisitionSearchForm.getRawValue() ? this.partRequisitionSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.partRequisitionSearchForm.getRawValue() ? this.partRequisitionSearchForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate', 'nextfollowUpFromDate', 'tentativePurchaseFromDate'];
    let toDates = ['toDate', 'nextFollowUpToDate', 'tentativePurchaseToDate'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }

    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }

  }
  setQueryParams(params?: { [key: string]: any }) {
    const searchFilter = this.partRequisitionSearchForm.getRawValue();
    ObjectUtil.removeNulls(searchFilter);
    this.searchFilter = { ...searchFilter, ...params };
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    this.partRequisitionSearchForm.patchValue(event);
    this.partRequisitionSearchForm.patchValue({
      requisitionFromDate: event.requisitionFromDate ? new Date(event.requisitionFromDate) : null,
      requisitionToDate: event.requisitionToDate ? new Date(event.requisitionToDate) : null,
    });
    if (event.fromDate) {
      this.partRequisitionSearchForm.get('fromDate').patchValue(new Date(event.fromDate));
    }
    if (event.toDate) {
      this.partRequisitionSearchForm.get('toDate').patchValue(new Date(event.toDate))
    }
    this.searchChange.emit(this.partRequisitionSearchForm.getRawValue());
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url } as UrlSegment);
  }
  resetSearchForm() {
    const { page, size } = this.partRequisitionSearchForm.getRawValue();
    this.partRequisitionSearchForm.reset();
    this.partRequisitionSearchForm.patchValue({ page, size });
    const searchFilter = this.partRequisitionSearchForm.getRawValue();
    ObjectUtil.removeNulls(searchFilter);
    this.searchFilter = searchFilter;
    this.clearData.emit(this.partRequisitionSearchForm.getRawValue());
  }
  getQueryParams(url: string) {
    const queryString = url.split('?')[1];
    const queryList = queryString.split('&');
    let queryParams: { [key: string]: any } = {};
    queryList.forEach((val: string, index: number) => {
      if (val) {
        const queryParam = val.split('=');
        const key = queryParam[0];
        const value = queryParam[1].split('%20').join(' ');
        queryParams[key] = value;
      }
    });
    if (Object.keys(queryParams).length) {
      return queryParams;
    }
    return null;
  }
}
