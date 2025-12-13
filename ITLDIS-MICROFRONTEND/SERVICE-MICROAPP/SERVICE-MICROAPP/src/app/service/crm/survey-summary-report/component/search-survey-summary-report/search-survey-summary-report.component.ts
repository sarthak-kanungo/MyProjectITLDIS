import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute, UrlSegment } from '@angular/router';
import { ObjectUtil } from 'src/app/utils/object-util';
import { SurveySummaryReportSearchPagePresenter } from './survey-summary-report-search-page.presenter';

import { SurveySummaryReportSearchPageStore } from './survey-summary-report-search-page.store';
import { SurveySummaryReportService } from '../../survey-summary-report.service';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { IFrameMessageSource, IFrameService } from 'src/app/root-service/iFrame.service';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { DateService } from 'src/app/root-service/date.service';
import { BaseDto } from 'BaseDto';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ModalFileUploadComponent } from 'src/app/service/Utility/modal-file-upload/modal-file-upload.component';
import { DirectSurveyService } from '../../../direct-survey/direct-survey.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';


@Component({
  selector: 'app-search-survey-summary-report',
  templateUrl: './search-survey-summary-report.component.html',
  styleUrls: ['./search-survey-summary-report.component.scss'],
  providers: [SurveySummaryReportSearchPagePresenter, SurveySummaryReportSearchPageStore, DirectSurveyService]
})
export class SearchSurveySummaryReportComponent implements OnInit {

  surveySummaryReportSearchForm: FormGroup
  surveySummaryReportForm: FormGroup
  searchFilter;
  public dataTable: DataTable;
  public totalTableElements: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  searchFilterValues: any;
  recordData: any
  reportType:string=null
  page = 0;
  size = 10
  newResult: any = {}
  newResultFinal: any = []
  key = "survey";
  clearSearchRow = new BehaviorSubject<string>("");
  surveyType:string
  surveyStatusList: BaseDto<Array<any>>
  surveyDateList: BaseDto<Array<any>>
  setStatus:string
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private surveySummaryReportSearchPagePresenter: SurveySummaryReportSearchPagePresenter,
    private surveySummaryReportService: SurveySummaryReportService,
    private tableDataService: NgswSearchTableService,
    private tostr: ToastrService,
    private dateService: DateService,
    private matDialog : MatDialog,
    private service:DirectSurveyService

  ) { }

  ngOnInit() {
    const val:any = JSON.parse(localStorage.getItem(this.key))
    let arrs = this.router.url.split('/')[2].split('-');
    this.surveyType = (arrs[arrs.length-1]).toUpperCase()
    
    this.surveySummaryReportForm = this.surveySummaryReportSearchPagePresenter.surveySummaryReportForm
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.surveySummaryReportForm.patchValue(this.searchFilterValues)
    }
   
    this.getSurveyDateDropDown();
    this.getSurveyStatusDropDown();

    this.surveySummaryReportForm.get('surveyName').patchValue(this.surveyType.toUpperCase())
    if (this.surveyType =='DIRECT') {
      this.surveySummaryReportForm.get('asOnDate').patchValue('DATE RANGE')
    }
    else if (this.surveyType =='TELEPHONIC') {
      this.surveySummaryReportForm.get('asOnDate').patchValue('AS ON DATE')
    }
  }
  // ngAfterViewInit() {
  //   if (this.surveyDateList.result.length>0) {
  //     this.searchSurveySummaryReportForTable();
  //   }
  // }

  getSurveyDateDropDown() {
    this.surveySummaryReportService.getSurveyDate().subscribe(response => {
      this.surveyDateList = response
      this.surveySummaryReportForm.get('asOnDate').patchValue(this.surveyDateList['result'][0].lookupval)
    })
  }
  
  getSurveyStatusDropDown() {
    this.surveySummaryReportService.getSurveyStatus().subscribe(response => {
      this.surveyStatusList = response
      if (this.surveySummaryReportForm.get('surveyStatus').value ==undefined) {
        // this.surveySummaryReportForm.get('surveyStatus').patchValue(this.surveyStatusList['result'][1].lookupval)
      }
    })
  }

  searchSurveySummaryReportForTable() {
    let searchFormValues = this.surveySummaryReportForm.getRawValue()
    this.reportType = searchFormValues.reportType;
    
    localStorage.setItem(this.key, JSON.stringify(this.surveySummaryReportForm.value))
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
    const temp = this.surveySummaryReportForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);

    if (searchFormValues.asOnDate == 'AS ON DATE') {
      searchFormValues.asOnDate = 'Y'
    }
    else {
      searchFormValues.asOnDate = 'N'
    }
    
    if (searchFormValues.fromDate != undefined && searchFormValues.fromDate != null)
      searchFormValues.fromDate = this.dateService.getDateIntoYYYYMMDD(searchFormValues.fromDate);

    if (searchFormValues.toDate != undefined && searchFormValues.toDate != null)
      searchFormValues.toDate = this.dateService.getDateIntoYYYYMMDD(searchFormValues.toDate);

    if (searchFormValues.dealer != undefined && searchFormValues.dealer != null)
      searchFormValues.dealer = searchFormValues.dealer.id;

    if (searchFormValues.model != undefined && searchFormValues.model != null)
      searchFormValues.model = searchFormValues.model

    if (searchFormValues.subModel != undefined && searchFormValues.subModel != null)
      searchFormValues.subModel = searchFormValues.subModel

      if(searchFormValues.fromDcDate != undefined && searchFormValues.fromDcDate != null)
      searchFormValues.fromDcDate = this.dateService.getDateIntoYYYYMMDD(searchFormValues.fromDcDate);

      if(searchFormValues.toDcDate != undefined && searchFormValues.toDcDate != null)
      searchFormValues.toDcDate = this.dateService.getDateIntoYYYYMMDD(searchFormValues.toDcDate);

    // if (searchFormValues.ZONE != undefined && searchFormValues.ZONE != null)
    //   searchFormValues.zone = searchFormValues.ZONE.org_hierarchy_id

    // if (searchFormValues.REGION != undefined && searchFormValues.REGION != null)
    //   searchFormValues.region = searchFormValues.REGION.org_hierarchy_id

    // if (searchFormValues.AREA != undefined && searchFormValues.AREA != null)
    //   searchFormValues.area = searchFormValues.AREA.org_hierarchy_id

    // if (searchFormValues.TERRITORY != undefined && searchFormValues.TERRITORY != null)
    //   searchFormValues.territory = searchFormValues.TERRITORY.org_hierarchy_id

    if (searchFormValues.customerSatisfaction != undefined && searchFormValues.customerSatisfaction != null)
      searchFormValues.customerSatisfaction = searchFormValues.customerSatisfaction.lookupval

      searchFormValues.question = searchFormValues.question ? searchFormValues.question.quesId :null

      searchFormValues.surveyNo = searchFormValues.surveyNo ? searchFormValues.surveyNo.Survey_No :null
      searchFormValues['hierId'] = searchFormValues.TERRITORY ? searchFormValues.TERRITORY['org_hierarchy_id'] : (searchFormValues.AREA ? searchFormValues.AREA['org_hierarchy_id'] : (searchFormValues.REGION ? searchFormValues.REGION['org_hierarchy_id'] : searchFormValues.ZONE ? searchFormValues.ZONE['org_hierarchy_id'] : 0))

      delete searchFormValues.page;
      delete searchFormValues.size;
    
      this.searchFlag = true;
      searchFormValues = ObjectUtil.removeNulls(searchFormValues);
    
      if (Object.keys(searchFormValues).length>0) {
        searchFormValues.page = this.page
        searchFormValues.size = this.size
        
        if (!this.dateService.checkValidDatesInput(searchFormValues.fromDate, searchFormValues.toDate)) {
            this.tostr.error("Please Select Date Range.");
            return false;
        }  
        this.surveySummaryReportService.searchSurveySummaryReport(searchFormValues).subscribe( res => {
            this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
            this.totalTableElements = res['count'];
          }
        );
      }else {
        this.tostr.error("Please fill atleast one input field.");
      }
    // this.surveySummaryReportService.searchSurveySummaryReport(searchFormValues).subscribe(
    //   res => {
    //     this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
    //     this.totalTableElements = res['count'];
    //     res.result.map(((res: any) => {
    //     })
    //     )
    //   },
    //   err => {
    //     console.log('err', err);
    //   }
    // );

  }
  
  checkValidDatesInput() {
    const fltEnqObj = this.surveySummaryReportForm.value

    fltEnqObj.fromDate = this.surveySummaryReportForm.getRawValue() ? this.surveySummaryReportForm.value.fromDate : null
    fltEnqObj.toDate = this.surveySummaryReportForm.getRawValue() ? this.surveySummaryReportForm.value.toDate : null

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


  tableAction(event: object) {
    if (event['record']['action'] === 'Start Survey') {
      if(this.surveyType.toLowerCase()=='telephonic')
        this.router.navigate(['../../survey-telephonic/create'], {
          relativeTo: this.activatedRoute, queryParams: { mobileNo: event['record']['customerNumber'], survetType: this.surveyType, status: event['record']['surveyStatus'], reminderId: event['record']['id'], chassisNo: event['record']['chassisNo'],custMstId: event['record']['secondId']  }
        })
      if(this.surveyType.toLowerCase()=='direct')
        this.router.navigate(['../../survey-direct/create'], {
          relativeTo: this.activatedRoute, queryParams: { mobileNo: event['record']['customerNumber'], survetType: this.surveyType, status: event['record']['surveyStatus'], reminderId: event['record']['id'], chassisNo: event['record']['chassisNo'],custMstId: event['record']['secondId']  }
        })
    }
    if (((event['record']['action']).replace(/\s/g, "") === 'UploadCallRecording') && (event['btnAction'] != 'surveyNumber')) {
      this.uploadFile(event['record']['callAttemptID'],null)
    }
    if ((((event['record']['action']).replace(/\s/g, "") === 'UploadSurveyRecording')) && (event['btnAction'] != 'surveyNumber')) {
      this.uploadFile(null,event['record']['survey_Hdr_Id'])
    }
    if (event['btnAction'] ===  'surveyNumber') {
      if(this.surveyType.toLowerCase()=='telephonic'){
        this.router.navigate(['../../survey-telephonic/view'], {
          relativeTo: this.activatedRoute, queryParams: {mobileNo: event['record']['customerNumber'], surveyNo: event['record']['surveyNumber'], survetType: this.surveyType, status: event['record']['surveyStatus'], chassisNo: event['record']['chassisNo'],custMstId: event['record']['secondId'], reminderId: event['record']['id'],satisFactoryLevel: event['record']['customerSatisfied'] }
        })
      }if(this.surveyType.toLowerCase()=='direct'){
        this.router.navigate(['../../survey-direct/view'], {
          relativeTo: this.activatedRoute, queryParams: {mobileNo: event['record']['customerNumber'], surveyNo: event['record']['surveyNumber'], survetType: this.surveyType, status: event['record']['surveyStatus'], chassisNo: event['record']['chassisNo'],custMstId: event['record']['secondId'], reminderId: event['record']['id'],satisFactoryLevel: event['record']['customerSatisfied'] }
        })
      }
    }
  }

  initialQueryParams(event) {
    // this.surveySummaryReportSearchForm.get('surveySummaryReportForm').patchValue(event);
    this.surveySummaryReportForm.patchValue(event)
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    // Comment By Ankit fix seach issue
    // if (this.surveySummaryReportForm.get('surveyStatus').value ==undefined) {
    //   this.surveySummaryReportForm.get('surveyStatus').patchValue('Due')
    // }
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchSurveySummaryReportForTable()
  }

  clearSurveySummaryReportForm() {
    // this.surveySummaryReportSearchForm.reset()
    //this.searchSurveySummaryReportForTable()
    this.clearSearchRow.next("");
    this.surveySummaryReportForm.reset()
    this.dataTable=null
    localStorage.removeItem(this.key)
    this.surveySummaryReportForm.get('surveyName').patchValue(this.surveyType)
  }

  public uploadFile(callAttemptId?:number, surveyHdrId?:number){
    const dialogConfig = new MatDialogConfig();
    // if (callAttemptId == undefined) {
    //   callAttemptId = 0
    // }
    // if (surveyRecordingId == undefined) {
    //   surveyRecordingId = 0
    // }
    // The user can't close the dialog by clicking outside its body
    let updatedata ={'callAttemptId':callAttemptId, 'surveyHdrId':surveyHdrId}
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    dialogConfig.data = {}
    const modalDialog = this.matDialog.open(ModalFileUploadComponent, dialogConfig);
    modalDialog.afterClosed().subscribe(result => {
      if(result.event === 'upload'){
        let file:File = result.data;
        this.service.updateRecordingFile(updatedata,file).subscribe(res=>{
          if (res.status == 200) {
            this.tostr.success(res['message']);
            this.searchSurveySummaryReportForTable()
          }else {
            this.tostr.error(res.message)
          }
        })
      }
    })
  }


  surveyDetailsExcelReport(event) {
    let formValues ={} as any
    let searchFormValues = this.surveySummaryReportForm.getRawValue();
    this.reportType = searchFormValues.reportType;
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    if (searchFormValues.asOnDate == 'AS ON DATE') {
      searchFormValues.asOnDate = 'Y'
    }
    else {
      searchFormValues.asOnDate = 'N'
    }
    
    if (searchFormValues.fromDate != undefined && searchFormValues.fromDate != null)
      searchFormValues.fromDate = this.dateService.getDateIntoYYYYMMDD(searchFormValues.fromDate);

    if (searchFormValues.toDate != undefined && searchFormValues.toDate != null)
      searchFormValues.toDate = this.dateService.getDateIntoYYYYMMDD(searchFormValues.toDate);

    if (searchFormValues.dealer != undefined && searchFormValues.dealer != null)
      searchFormValues.dealer = searchFormValues.dealer.id;

    if (searchFormValues.model != undefined && searchFormValues.model != null)
      searchFormValues.model = searchFormValues.model

    if (searchFormValues.subModel != undefined && searchFormValues.subModel != null)
      searchFormValues.subModel = searchFormValues.subModel

    if (searchFormValues.customerSatisfaction != undefined && searchFormValues.customerSatisfaction != null)
      searchFormValues.customerSatisfaction = searchFormValues.customerSatisfaction.lookupval

      searchFormValues.question = searchFormValues.question ? searchFormValues.question.quesId :null

      searchFormValues.surveyNo = searchFormValues.surveyNo ? searchFormValues.surveyNo.Survey_No :null
      searchFormValues['hierId'] = searchFormValues.TERRITORY ? searchFormValues.TERRITORY['org_hierarchy_id'] : (searchFormValues.AREA ? searchFormValues.AREA['org_hierarchy_id'] : (searchFormValues.REGION ? searchFormValues.REGION['org_hierarchy_id'] : searchFormValues.ZONE ? searchFormValues.ZONE['org_hierarchy_id'] : 0))

      delete searchFormValues.page;
      delete searchFormValues.size;
    
      this.searchFlag = true;
      searchFormValues = ObjectUtil.removeNulls(searchFormValues);
    
      if (Object.keys(searchFormValues).length>0) {
        searchFormValues.page = this.page
        searchFormValues.size = this.size

        if (!this.dateService.checkValidDatesInput(searchFormValues.fromDate, searchFormValues.toDate)) {
            this.tostr.error("Please Select Date Range.");
            return false;
        }  
          
        this.downloadExcel(searchFormValues)
      }else {
        this.tostr.error("Please fill atleast one input field.");
      }
      
    }

  downloadExcel(data){
    this.surveySummaryReportService.downloadsurveyDetailsExcelReport(data).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
       
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }



}
