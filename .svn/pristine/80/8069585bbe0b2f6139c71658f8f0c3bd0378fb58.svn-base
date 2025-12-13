import { Component, OnInit } from '@angular/core';
import { TrainingProgrammeCalenderSearchPageWebService } from '../training-prog-calender-search-page/training-prog-calender-search-page-web.service';
import { TrainingProgrammeCalenderSearchPagePresenter } from '../training-prog-calender-search-page/training-prog-calender-search-page.presenter';
import { TrainingProgrammeCalenderSearchPageStore } from '../training-prog-calender-search-page/training-prog-calender-search-page.store';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { TrainingProgCalenderService } from '../../service/training-prog-calender.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DateService } from 'src/app/root-service/date.service';
import { FormGroup } from '@angular/forms';
import { TpcHeader, TpcSearchHeader } from '../../domain/tpc-domain';
import { ObjectUtil } from 'src/app/utils/object-util';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';

@Component({
  selector: 'app-training-programme-calender-search-table',
  templateUrl: './training-programme-calender-search-table.component.html',
  styleUrls: ['./training-programme-calender-search-table.component.css'],

  providers: [TrainingProgrammeCalenderSearchPageWebService, TrainingProgrammeCalenderSearchPageStore, TrainingProgrammeCalenderSearchPagePresenter]
})
export class TrainingProgrammeCalenderSearchTableComponent implements OnInit {

  tpcSearchForm: FormGroup

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
  toDate: Date;
  toDate1: Date;
  fromDate: Date;
  newdate = new Date();
  key = 'tpc';
  clearSearchRow = new BehaviorSubject<string>("");
  programNumberNgModel
  programDateNgModel;
  trainingLocDescNgModel
  trainingModuleDescNgModel
  locationNgModel
  startDateNgModel
  endDateNgModel
  updateNomineeNgModel
  lastNominationDateNgModel
  maxNoOfNomineesNgModel
  noOfAllowedNomineesNgModel
  createdDateNgModel
  nomineeNgModel
  approvalNgModel
  hoOrDealerView:boolean
  constructor(private presenter: TrainingProgrammeCalenderSearchPagePresenter,
    private service: TrainingProgCalenderService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dateservice: DateService,
    private toastr: ToastrService,
    private tableDataService: NgswSearchTableService,
    private loginService: LocalStorageService,) { }

  ngOnInit() {
    if (this.loginService.getLoginUser().dealerCode == null) {
      this.hoOrDealerView = true
    }
    
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key)
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.tpcSearchForm = this.presenter.tpcSearchHeaderForm
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.tpcSearchForm.patchValue(this.searchFilterValues)
    }
    if (this.tpcSearchForm.get('programNumber').value == null && this.tpcSearchForm.get('trainingLocation').value == null && this.tpcSearchForm.get('trainingModule').value == null && this.tpcSearchForm.get('fromDate').value == null && this.tpcSearchForm.get('toDate').value == null) {
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      let newToDate = new Date();
      newToDate.setMonth(newToDate.getMonth() + 1);
      this.tpcSearchForm.get('fromDate').patchValue(backDate);
      this.tpcSearchForm.get('toDate').patchValue(newToDate);
      this.getTpcsearch()
    } else {
      localStorage.getItem(this.key)
      this.getTpcsearch()
    }
  }

  getTpcsearch() {
    // this.tableClear()
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
    const filterObj = this.tpcSearchForm.getRawValue()
    let searchData = {} as TpcSearchHeader
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(filterObj))

    searchData.page = this.page
    searchData.size = this.size
    searchData.departmentName = this.loginService.getLoginUser().departmentName
     searchData.programNo = filterObj.programNumber ? filterObj.programNumber.programNumber:null
    
    //  console.log( searchData.programNo,'dataa')
    searchData.trainingLocationId = filterObj.trainingLocation ? filterObj.trainingLocation['Training_loc_id'] : null
    searchData.trainingModuleId = filterObj.trainingModule ? filterObj.trainingModule['Training_Module_id'] : null
    searchData.fromDate = filterObj.fromDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.fromDate) : null
    searchData.toDate = filterObj.toDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.toDate) : null
    this.searchFilter = ObjectUtil.removeNulls(searchData);
    if (this.tpcSearchForm.valid) {
      if (this.checkValidDatesInput()) {
        if (this.searchFlag == true && this.tpcSearchForm.get('programNumber').value || this.tpcSearchForm.get('trainingLocation').value || this.tpcSearchForm.get('fromDate').value || this.tpcSearchForm.get('toDate').value || this.tpcSearchForm.get('typeofTraining').value) {
          //this.searchWarrantyPcr(filterObj);
          this.service.tpcSearch(searchData).subscribe(res => {
            let result = res['result'];
          //  result.forEach(result=>result.edit="Edit")
            this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
            this.totalTableElements = res.count;
          })
        }
        else if (this.tpcSearchForm.get('fromDate').value == null && this.tpcSearchForm.get('toDate').value == null) {
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
    const fltEnqObj = this.tpcSearchForm.value

    fltEnqObj.fromDate = this.tpcSearchForm.getRawValue() ? this.tpcSearchForm.value.fromDate : null
    fltEnqObj.toDate = this.tpcSearchForm.getRawValue() ? this.tpcSearchForm.value.toDate : null
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
  this.programDateNgModel=''
  this.trainingLocDescNgModel=''
  this.trainingModuleDescNgModel=''
  this.locationNgModel=''
  this.startDateNgModel=''
  this.endDateNgModel=''
  this.updateNomineeNgModel=''
  this.lastNominationDateNgModel=''
  this.maxNoOfNomineesNgModel=''
  this.noOfAllowedNomineesNgModel=''
  this.createdDateNgModel=''
  this.nomineeNgModel=''
  this.approvalNgModel=''
  this.locationNgModel=''
}
  clear() {
    this.tpcSearchForm.reset()
    this.dataTable = null
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
    
  }

  tableAction(event: object) {
    // console.log('event---', event)
    if (event['btnAction'] === 'edit') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], questionCode: event['record']['programNumber'] }
      })
    }
    if (event['btnAction'] === 'approval') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], questionCode: event['record']['programNumber'], approval:'approval' }
      })
    }
    if (event['btnAction'] === 'programNumber') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], questionCode: event['record']['programNumber'] }
      })
    }
    if (event['btnAction'] === 'updateNominee') {
      this.router.navigate(['../../training-nomination/create'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], programNo: event['record']['programNumber'] }
      })
    }

  }
  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.getTpcsearch()
  }


}
