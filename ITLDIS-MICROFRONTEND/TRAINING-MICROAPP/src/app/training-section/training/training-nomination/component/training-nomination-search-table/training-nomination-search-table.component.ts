import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import {  NominationSearchHeader } from '../../domain/training-nomination.domain';
import { TrainingnominationService } from '../../service/training-nomination.service';
import { TrainingnominationSearchPageWebService } from '../training-nomination-search-page/training-nomination-search-page-web.service';
import { TrainingnominationSearchPagePresenter } from '../training-nomination-search-page/training-nomination-search-page.presenter';
import { TrainingnominationSearchPageStore } from '../training-nomination-search-page/training-nomination-search-page.store';

@Component({
  selector: 'app-training-nomination-search-table',
  templateUrl: './training-nomination-search-table.component.html',
  styleUrls: ['./training-nomination-search-table.component.css'],
  providers: [TrainingnominationSearchPageWebService, TrainingnominationSearchPageStore, TrainingnominationSearchPagePresenter]
})
export class TrainingNominationSearchTableComponent implements OnInit {
  nominationSearchForm: FormGroup

  searchFilter;
  public dataTable: DataTable;
  public totalTableElements: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  searchFilterValues: any;
  recordData: any
  page = 0;
  size = 10
  newResult: any = {}
  newResultFinal: any = []
  fromDate: Date;
  newdate = new Date();
  key = 'tns';
  clearSearchRow = new BehaviorSubject<string>("");
  programNumberNgModel:string
  nominatinNumberNgModel: string;
  employeeCodeNgModel: string;
  employeeNameNgModel: string;
  departmentNameNgModel: string;
  dealerCodeNgModel: string;
  dealerNameNgModel: string;
  createdDateNgModel: string;

  constructor(private presenter:TrainingnominationSearchPagePresenter,
    private service: TrainingnominationService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dateservice: DateService,
    private toastr: ToastrService,
    private tableDataService: NgswSearchTableService,) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key)
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.nominationSearchForm = this.presenter.tpcSearchHeaderForm
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.nominationSearchForm.patchValue(this.searchFilterValues)
    }
    if (this.nominationSearchForm.get('programNumber').value == null && this.nominationSearchForm.get('nominatinoNumber').value == null && this.nominationSearchForm.get('fromDate').value == null && this.nominationSearchForm.get('toDate').value == null) {
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      this.nominationSearchForm.get('fromDate').patchValue(backDate);
      this.nominationSearchForm.get('toDate').patchValue(new Date());
      this.getNominationSearchFormsearch()
    } else {
      localStorage.getItem(this.key)
      this.getNominationSearchFormsearch()
    }
  }

  getNominationSearchFormsearch(){
    this.tableClear()
    if (this.dataTable != undefined || this.dataTable != null) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }
    else {
      this.page = 0;
      this.size = 10;
    }
    this.searchFlag = true;
    const filterObj = this.nominationSearchForm.getRawValue()
    console.log(filterObj,'obj')
    let searchData = {} as NominationSearchHeader
    console.log(searchData,'data')
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(filterObj))

    searchData.page = this.page
    searchData.size = this.size
    searchData.programId = filterObj.programNumber ? filterObj.programNumber.id : null
    console.log(searchData.programId ,'searchData.programId ')
    searchData.nomineeId = filterObj.nominatinoNumber ? filterObj.nominatinoNumber.programNominationHDRId : null
    console.log( searchData.nomineeId ,'searchData.programId ')
    searchData.fromDate = filterObj.fromDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.fromDate) : null
    searchData.toDate = filterObj.toDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.toDate) : null
    this.searchFilter = ObjectUtil.removeNulls(searchData);
    if (this.nominationSearchForm.valid) {
      if (this.checkValidDatesInput()) {
        if (this.searchFlag == true && this.nominationSearchForm.get('programNumber').value || this.nominationSearchForm.get('nominatinoNumber').value || this.nominationSearchForm.get('fromDate').value || this.nominationSearchForm.get('toDate').value) {
          this.service.tncSearch(searchData).subscribe(res => {
            this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
            this.totalTableElements = res.count;
          })
        }
        else if (this.nominationSearchForm.get('fromDate').value == null && this.nominationSearchForm.get('toDate').value == null) {
          this.toastr.error("Please fill atleast one input field.");
        }
      } else {
        this.toastr.error("Please Select Date Range.");
      }
    }
    else {
      this.toastr.error('Plese select value from list', 'Error');
    }
  }

  checkValidDatesInput() {
    const fltEnqObj = this.nominationSearchForm.getRawValue()

    fltEnqObj.fromDate = this.nominationSearchForm.getRawValue() ? this.nominationSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.nominationSearchForm.getRawValue() ? this.nominationSearchForm.value.toDate : null
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
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
  tableClear(){
    this.programNumberNgModel=''
    this.nominatinNumberNgModel=''
    this.employeeCodeNgModel=''
    this.employeeNameNgModel=''
    this.departmentNameNgModel=''
    this.dealerCodeNgModel=''
    this.dealerNameNgModel=''
    this.createdDateNgModel=''
  }
  clear(){
    this.nominationSearchForm.reset()
    this.dataTable=null
    localStorage.removeItem(this.key)
  }

  tableAction(event: object) {
    console.log('event---', event)
    if (event['btnAction'].toLowerCase() === 'edit') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'],programNo: event['record']['programNumber']}
      })
    }
    if (event['btnAction'] === 'nominationNumber') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'],programNo: event['record']['programNumber']}
      })
    }
  }
  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.getNominationSearchFormsearch()
  }
}
