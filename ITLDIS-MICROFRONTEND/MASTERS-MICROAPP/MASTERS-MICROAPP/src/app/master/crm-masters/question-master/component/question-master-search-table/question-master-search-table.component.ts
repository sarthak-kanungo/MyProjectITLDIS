import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { QuestionMaster } from '../../domain/question-master-domain';
import { QuestionMasterService } from '../../question-master-service/question-master.service';
import { QuestionMasterSearchPagePresenter } from '../question-master-search-page/question-master-search-page.presenter';
import { QuestionMasterSearchPageStore } from '../question-master-search-page/question-master-search-page.store';

@Component({
  selector: 'app-question-master-search-table',
  templateUrl: './question-master-search-table.component.html',
  styleUrls: ['./question-master-search-table.component.css'],
  providers:[QuestionMasterSearchPageStore,QuestionMasterSearchPagePresenter]
})
export class QuestionMasterSearchTableComponent implements OnInit {
  questionMasterSearchHeaderForm: FormGroup
  
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
  key = "survey";
  clearSearchRow = new BehaviorSubject<string>("");

  questionCodeNgModel: any
  questionTypeNgModel: any
  questionNgModel: any
  mainAnsweNgModel: any
  subAnsweNgModel: any
  questionCreationDateNgModel: any

  constructor(private presenter:QuestionMasterSearchPagePresenter,
              private service: QuestionMasterService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private dateservice: DateService,
              private toastr: ToastrService,
              private tableDataService: NgswSearchTableService,) { }

  ngOnInit() {
    this.questionMasterSearchHeaderForm = this.presenter.questionMasterSearchHeaderForm

  }

  searchQuestionMaster(){
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
    const filterObj = this.questionMasterSearchHeaderForm.getRawValue()
    let searchData = {} as QuestionMaster
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(filterObj))

    searchData.page = this.page
    searchData.size = this.size

    searchData.questionType = filterObj.questionType ? filterObj.questionType.Survey_type_desc:null
    searchData.questionId = filterObj.questionCode ? filterObj.questionCode.ques_ID:null
    searchData.fromDate = filterObj.fromDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.fromDate) : null
    searchData.toDate = filterObj.toDate ? this.dateservice.getDateIntoYYYYMMDD(filterObj.toDate) : null
    this.searchFilter = ObjectUtil.removeNulls(searchData);
    if (this.questionMasterSearchHeaderForm.valid) {
      if (this.checkValidDatesInput()) {
        if (this.searchFlag == true && this.questionMasterSearchHeaderForm.get('questionType').value || this.questionMasterSearchHeaderForm.get('questionCode').value || this.questionMasterSearchHeaderForm.get('fromDate').value || this.questionMasterSearchHeaderForm.get('toDate').value) {
          //this.searchWarrantyPcr(filterObj);
          this.service.searchQuestionMaster(searchData).subscribe(res => {
            res['result'].forEach(row => {
              row.edit= 'edit';
            });
            this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
            this.totalTableElements = res.count;
          })
        }
        else if (this.questionMasterSearchHeaderForm.get('fromDate').value == null && this.questionMasterSearchHeaderForm.get('toDate').value == null) {
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
    const fltEnqObj = this.questionMasterSearchHeaderForm.value

    fltEnqObj.fromDate = this.questionMasterSearchHeaderForm.getRawValue() ? this.questionMasterSearchHeaderForm.value.pcrFromDate : null
    fltEnqObj.toDate = this.questionMasterSearchHeaderForm.getRawValue() ? this.questionMasterSearchHeaderForm.value.pcrToDate : null
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



  clearForm(){
    this.questionMasterSearchHeaderForm.reset()
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
    this.questionCodeNgModel= ''
    this.questionTypeNgModel= ''
    this.questionNgModel= ''
    this.mainAnsweNgModel= ''
    this.subAnsweNgModel= ''
    this.questionCreationDateNgModel= ''
  }

  tableAction(event: object) {
    console.log('event---', event)
    if (event['btnAction'].toLowerCase() === 'edit') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'],questionCode: event['record']['questionCode']}
      })
    }
    if (event['btnAction'] === 'questionCode') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'],questionCode: event['record']['questionCode']}
      })
    }
  }
  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchQuestionMaster()
  }


}
