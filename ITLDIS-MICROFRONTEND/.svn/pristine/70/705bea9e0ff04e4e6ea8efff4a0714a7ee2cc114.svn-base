import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { QaReportPagePagePresenter } from '../qa-report-page/qa-report-page.presenter';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { DateService } from 'src/app/root-service/date.service';
import { QaReportPageStore } from '../qa-report-page/qa-report-page.store';
import { BehaviorSubject } from 'rxjs';
import { JobCardSearchWebService } from 'src/app/service/customer-service/job-card/component/job-card-search/job-card-search-web.service';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { SurveySummaryReportService } from 'src/app/service/crm/survey-summary-report/survey-summary-report.service';

@Component({
  selector: 'app-qa-report-table',
  templateUrl: './qa-report-table.component.html',
  styleUrls: ['./qa-report-table.component.css'],
  providers:[QaReportPageStore,QaReportPagePagePresenter,JobCardSearchWebService, SurveySummaryReportService]
})
export class QaReportTableComponent implements OnInit {

  reportForm:FormGroup

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
  key = "survey";
  clearSearchRow = new BehaviorSubject<string>("");


  constructor(private presenter:QaReportPagePagePresenter,
    private toastr: ToastrService,
    private dateService: DateService,
    private tableDataService: NgswSearchTableService,
    private surveySummaryReportService: SurveySummaryReportService,) { }

  ngOnInit() {
    this.reportForm = this.presenter.reportForm
  }





searchSurveySummaryQAReport() {
    const searchFormValues = this.reportForm.getRawValue()  
    
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.reportForm.value))
    if (this.dataTable != undefined) {
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
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      searchFormValues['page'] == this.page;
      searchFormValues['size'] = this.size;
    }
    else {
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    const temp = this.reportForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
      searchFormValues.fromDate = searchFormValues.fromDate ? this.dateService.getDateIntoYYYYMMDD(searchFormValues.fromDate):null
      searchFormValues.toDate = searchFormValues.toDate ? this.dateService.getDateIntoYYYYMMDD(searchFormValues.toDate):null
      searchFormValues.question = searchFormValues.question ? searchFormValues.question.quesId :null

     
      this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
      if (this.checkValidDatesInput()) {
        console.log('vinay-------',this.reportForm.get('product').value);
        if (this.reportForm.get('fromDate').value ||this.reportForm.get('surveyType').value || this.reportForm.get('variant').value ||
        this.reportForm.get('product').value || this.reportForm.get('dealer').value ||
        this.reportForm.get('question').value || this.reportForm.get('model').value ||
        this.reportForm.get('subModel').value) {
          this.surveySummaryReportService.searchSurveySummaryQAReport(searchFormValues).subscribe( res => {
              this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
              this.totalTableElements = res['count'];
            }
          );
         }else {
          this.toastr.error("Please fill atleast one input field.");
        }
      } else {
        this.toastr.error("Please Select Date Range.");
      }

  }

  checkValidDatesInput() {
    const fltEnqObj = this.reportForm.getRawValue()
    fltEnqObj.fromDate = this.reportForm.getRawValue() ? this.reportForm.value.fromDate : null
    fltEnqObj.toDate = this.reportForm.getRawValue() ? this.reportForm.value.toDate : null
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

  
  initialQueryParams(event) {
    this.reportForm.patchValue(event)
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchSurveySummaryQAReport()
  }

  clearSurveySummaryQAReportForm() {
    this.clearSearchRow.next("");
    this.reportForm.reset()
    this.dataTable=null
    localStorage.removeItem(this.key);
    this.surveySummaryReportService.clearFormSubject.next('clear');
  }

  qaExcelReport(event) {
    const searchFormValues = this.reportForm.getRawValue();
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    //console.log('formValues', searchFormValues)
     this.downloadExcel(searchFormValues)
  }

  downloadExcel(data){
    this.surveySummaryReportService.downloadsurveyQAExcelReport(data).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
       
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }


  


}
