import { Component, Input, OnInit } from '@angular/core';
import { SearchPartReturnApiService } from './search-part-return-api.service';
import { Observable } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { PartReturnSearchPagePresenter } from '../part-return-search-page/part-return-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import {PartRequisitionSearchApiService} from '../../../part-requisition/component/part-requisition-search/part-requisition-search-api.service';

@Component({
  selector: 'app-part-return-search',
  templateUrl: './part-return-search.component.html',
  styles: [``],
  providers: [SearchPartReturnApiService,PartRequisitionSearchApiService]
})
export class PartReturnSearchComponent implements OnInit {

  @Input() partReturnSearchForm: FormGroup;
  reasonForReturnList$: Observable<SelectList[]>;
  requisitionPurposeList$: Observable<SelectList[]>;
  requisitionTypeList$: Observable<SelectList[]>;
  requisitionNoList$: Observable<SelectList[]>;
  jobCardNoList$: Observable<SelectList[]>;
  partReturnNoList$: Observable<SelectList[]>;
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private searchPartsReturnService: SearchPartReturnApiService,
    private partReturnSearchPagePresenter: PartReturnSearchPagePresenter,
    private autocompleteService: AutocompleteService,
    private partRequisitionSearchApiService : PartRequisitionSearchApiService
  ) {
    this.partReturnSearchForm = this.partReturnSearchPagePresenter.partReturnSearchForm;
  }

  ngOnInit() {
    this.getRequisitionPurpose();
    this.getReasonForReturn();
    //this.getRequisitionType();
    this.partReturnNoValueChanges();
    this.requisitionNoValueChanges();
    this.serviceJobCardValueChanges();
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
      if (this.partReturnSearchForm.get('requisitionToDate').value > this.maxDate)
        this.partReturnSearchForm.get('requisitionToDate').patchValue(this.maxDate);
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
      if (this.partReturnSearchForm.get('issueToDate').value > this.maxDate)
        this.partReturnSearchForm.get('issueToDate').patchValue(this.maxDate);
    }
  }
  
  private getRequisitionPurpose() {
    this.requisitionPurposeList$ = this.partRequisitionSearchApiService.getRequisitionPurpose();
  }
  private getReasonForReturn() {
    this.reasonForReturnList$ = this.searchPartsReturnService.getReasonsForReturn();

  }
  /*private getRequisitionType() {
    this.requisitionTypeList$ = this.partRequisitionSearchApiService.getRequisitionPurpose();
  }*/
  private requisitionNoValueChanges() {
    this.partReturnSearchForm.get('requisitionNo').valueChanges.subscribe(val => {
      if (val && typeof val === 'string') {
        this.requisitionNoList$ = this.searchPartsReturnService.searchPartRequisitionNo(val);
      }
      this.autocompleteService.validateSelectedFromList(val, this.requisitionNoList$, this.partReturnSearchForm.get('requisitionNo'));
    })
  }
  requisitionNoOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.autocompleteService.removeSelectedFromListError(this.partReturnSearchForm.get('requisitionNo'));
  }
  private partReturnNoValueChanges() {
    this.partReturnSearchForm.get('partReturnNo').valueChanges.subscribe(val => {
      if (val && typeof val === 'string') {
         this.partReturnNoList$ = this.searchPartsReturnService.searchByReturnNo(val);
      }
      this.autocompleteService.validateSelectedFromList(val, this.partReturnNoList$, this.partReturnSearchForm.get('partReturnNo'));
    })
  }
  partReturnNoOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.autocompleteService.removeSelectedFromListError(this.partReturnSearchForm.get('partReturnNo'));
  }
  private jobCardNoValueChanges() {
    this.partReturnSearchForm.get('jobCardNo').valueChanges.subscribe(val => {
      // if (val && typeof val === 'string') {
      //   this.requisitionNoList$ = this.searchPartsReturnService.searchPartRequisitionNo(val);
      // }
      this.autocompleteService.validateSelectedFromList(val, this.requisitionNoList$, this.partReturnSearchForm.get('jobCardNo'));
    })
  }
  jobCardNoOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.autocompleteService.removeSelectedFromListError(this.partReturnSearchForm.get('jobCardNo'));
  }

  private serviceJobCardValueChanges() {
    if (this.partReturnSearchForm) {
      this.partReturnSearchForm.get('jobCardNo').valueChanges.subscribe((aprSearchVal: string | SelectList) => {
        if (typeof aprSearchVal === 'string') {
          this.jobCardNoList$ = this.searchPartsReturnService.searchByJobCardNoForPartReturn(aprSearchVal);
        }
      })
    }
  }
}
