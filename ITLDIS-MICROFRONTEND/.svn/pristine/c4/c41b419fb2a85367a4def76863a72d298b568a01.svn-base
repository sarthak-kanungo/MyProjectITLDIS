import { Component, OnInit } from '@angular/core';
import { TrainingattendanceTrainingReportPageStore } from './attendance-training-report-create-page.store';
import { TrainingattendanceTrainingReportPagePresenter } from './attendance-training-report-create-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';

import { HttpResponse } from '@angular/common/http';
import { errorObject } from 'rxjs/internal-compatibility';
import { TrainingattendanceTrainingReportService } from '../../service/attendance-training-report.service';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { TrainingProgCalenderService } from '../../../training-programme-calender/service/training-prog-calender.service';
import { DateService } from 'src/app/root-service/date.service';
import { AttendancReporteHeader, FieldLevel, FirstTimeBuyer, LevelHierarchy } from '../../domain/attendance-training-report.domain';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { saveAs } from 'file-saver';
import { BaseDto } from 'BaseDto';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ObjectUtil } from 'src/app/utils/object-util';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-attendance-training-report-create-page',
  templateUrl: './attendance-training-report-create-page.component.html',
  styleUrls: ['./attendance-training-report-create-page.component.scss'],
  providers: [TrainingattendanceTrainingReportPageStore, TrainingattendanceTrainingReportPagePresenter, TrainingattendanceTrainingReportService, FileUploadService]
})
export class TrainingattendanceTrainingReportCreatePageComponent implements OnInit {
  TrainingattendanceTrainingReportForm: FormGroup;
  isEdit: boolean
  isView: boolean
  isCreate: boolean
  type: string
  todayDate = new Date();
  trainingList: Array<any>
  trainingModuleList:Array<any>
  holidayDateMin: Date
  holidayDateMax: Date
  empStatus: FirstTimeBuyer[]=[{value: 'Y', viewValue: 'Yes'},{value: 'N', viewValue: 'No'},];
  clearSearchRow = new BehaviorSubject<string>("");
  departmentList: any;
  zoneList:Array<any>
  regionList:Array<any>
  statesList:Array<any>
  tsmName:Array<any>
  dealerDesignationList:Array<any>
  public dataTable: DataTable;
  public totalTableElements: number;
  page = 0;
  size = 10
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  searchFilterValues: any;
  key='atr'
  searchFilter;
  public todaysDate = new Date();
  minDate: Date;
  maxDate: Date
  dealerNameNgModel: string;
  employeeNameNgModel: string;
  isactiveNgModel: string;
  level_idNgModel: string;
  org_hierarchy_idNgModel: string;
  parent_org_hierarchy_idNgModel: string;
  constructor(
    private TrainingattendanceTrainingReportPagePresenter: TrainingattendanceTrainingReportPagePresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private trainingattaendanceReportService: TrainingattendanceTrainingReportService,
    private fileUploadService: FileUploadService,
    private dateService: DateService,
    private service: TrainingProgCalenderService,
    private tableDataService: NgswSearchTableService,
    private loginService: LocalStorageService,
  ) { }

  ngOnInit() {
  
    this.getZoneForTraining()
    this.getState()
    this.getTrainingType(this.loginService.getLoginUser().departmentName,this.type)
    this.getTSMName()
    
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key)
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.TrainingattendanceTrainingReportForm = this.TrainingattendanceTrainingReportPagePresenter.TrainingattendanceTrainingReportForm;
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.TrainingattendanceTrainingReportForm.patchValue(this.searchFilterValues)
    }
    if (this.TrainingattendanceTrainingReportForm.get('zone').value==null && this.TrainingattendanceTrainingReportForm.get('region').value==null && this.TrainingattendanceTrainingReportForm.get('state').value==null && this.TrainingattendanceTrainingReportForm.get('tsmName').value==null && this.TrainingattendanceTrainingReportForm.get('employeeStatus').value==null && this.TrainingattendanceTrainingReportForm.get('typeofTraining').value==null && this.TrainingattendanceTrainingReportForm.get('trainingModule').value==null && this.TrainingattendanceTrainingReportForm.get('delearEmpDesignation').value==null && this.TrainingattendanceTrainingReportForm.get('trainingstartDate').value==null && this.TrainingattendanceTrainingReportForm.get('trainingendDate').value==null) {
      this.maxDate = this.todaysDate
    let backDate = new Date();
    backDate.setMonth(this.todaysDate.getMonth() - 1);
      this.TrainingattendanceTrainingReportForm.get('trainingstartDate').patchValue(backDate);
      this.TrainingattendanceTrainingReportForm.get('trainingendDate').patchValue(new Date());
      this.getTrainingReport()
    } else {
      localStorage.getItem(this.key)
      this.getTrainingReport()
    }
  }
  private viewOrEditOrCreate() {
    if (this.TrainingattendanceTrainingReportPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
    }
    if (this.TrainingattendanceTrainingReportPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    }
    if (this.TrainingattendanceTrainingReportPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    }
  }


  getTrainingType(departmentName:string,actionType:string){
    this.service.getTrainingType(departmentName,actionType).subscribe(res =>{
      this.trainingList = res.result
    })
  }

  getTrainingModule(trainingTypeId:any){
    this.service.getTrainingModule(trainingTypeId.Training_Type_id,this.type).subscribe(res =>{
      this.trainingModuleList = res.result
    })
  }

  getDataFromSearchPage() {
    this.activatedRoute.queryParamMap.subscribe(param => {
      if (param.has('id')) {
        this.patchDataViewEdit(parseInt(param.get('id')))
      }
    })
  }

  patchDataViewEdit(data: number) {
    let response: any
    this.trainingattaendanceReportService.getattendanceDataforViewEdit().subscribe(res => {
      response = res['result'][0]
      this.TrainingattendanceTrainingReportForm.get('region').patchValue(response.region)
      this.TrainingattendanceTrainingReportForm.get('state').patchValue(response.state)
      this.TrainingattendanceTrainingReportForm.get('typeofTraining').patchValue(this.trainingList.filter(tt => tt.Training_Type_id == response.training_type_id)[0])
      this.TrainingattendanceTrainingReportForm.get('trainingModule').patchValue(this.trainingModuleList.filter(tm => tm.Training_Module_id == response.module_id)[0])
      this.TrainingattendanceTrainingReportForm.get('tsmName').patchValue(response.tsmName)
      this.TrainingattendanceTrainingReportForm.get('delearName').patchValue(response.delearName)
      this.TrainingattendanceTrainingReportForm.get('delearEmpDescription').patchValue(response.delearEmpDescription)
      this.TrainingattendanceTrainingReportForm.get('empState').patchValue(response.empState)
      this.TrainingattendanceTrainingReportForm.get('trainingstartDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(response.trainingstartDate))
      this.TrainingattendanceTrainingReportForm.get('trainingendDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(response.trainingendDate))
    })
  }
  buildJsonForSubmit() {
    const attendance = this.TrainingattendanceTrainingReportForm.getRawValue()
    console.log('attendanceReport--', attendance);

    let attendanceHeaderForm = {} as AttendancReporteHeader

    this.isEdit == true ? attendanceHeaderForm.programNumber = attendance.programNumber : null
    attendanceHeaderForm.programNumber = ''
    attendanceHeaderForm.region = attendance.region ? attendance.region: null
    attendanceHeaderForm.state = attendance.state ? attendance.trainer1.state : null
    attendanceHeaderForm.tsmName = attendance.tsmName
    attendanceHeaderForm.trainingTypeId = attendance.trainingTypeId ? attendance.trainingTypeId.Training_Type_id : null
    attendanceHeaderForm.trainingModuleId = attendance.trainingModule ? attendance.trainingModule.Training_Module_id : null
    attendanceHeaderForm.delearName = attendance.delearName ? attendance.delearName : null
    attendanceHeaderForm.startDate = attendance.trainingstartDate ? this.dateService.getDateIntoDDMMYYYY(attendance.trainingstartDate) : null
    attendanceHeaderForm.endDate = attendance.trainingendDate ? this.dateService.getDateIntoDDMMYYYY(attendance.trainingendDate) : null
    attendanceHeaderForm.empStatus = attendance.empState
    attendanceHeaderForm.delearEmpDesignation = attendance.delearEmpDescription

    console.log('submitForm----', attendanceHeaderForm);

    return attendanceHeaderForm
  }
  validateForm() {
    if (this.TrainingattendanceTrainingReportForm.status == 'INVALID') {
      this.toastr.error('Please check Mandatory Fields', "Mandatory Field")
      this.TrainingattendanceTrainingReportForm.markAllAsTouched()
    } else {
      this.openConfirmDialog()
    }
  }


  submitForm() {
    // this.service.saveTrainingAttendancesheet(this.buildJsonForSubmit()).subscribe(res =>{
    //   if (res.status == 200) {
    //     this.tostr.success(res.message, 'Success');
    //     this.router.navigate(['../'], { relativeTo: this.activatedRoute })
    //   }else{
    //     this.tostr.error(res.message)
    //   }
    // })
  }

  updateForm() {
    // this.service.updateTrainingAttendancesheet(this.buildJsonForSubmit()).subscribe(res =>{
    //   if (res.status == 200) {
    //     this.tostr.success(res.message, 'Success');
    //     this.router.navigate(['../'], { relativeTo: this.activatedRoute })
    //   }else{
    //     this.tostr.error(res.message)
    //   }
    // })
  }

  reset() {
    this.TrainingattendanceTrainingReportForm.reset()
  }

  private openConfirmDialog(): void | any {
    const message = `Are you sure you want to submit?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && this.isCreate) {
        this.submitForm()
      } else if (result === 'Confirm' && this.isEdit) {
        this.updateForm()
      }
    })
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }


  
  getZoneForTraining(){
    this.trainingattaendanceReportService.getTrainingZoneAndRegion(1,1).subscribe(res=>{
      this.zoneList = res['result']
    })
  }
  getRegionFortraining(event){
    this.trainingattaendanceReportService.getTrainingZoneAndRegion(event.level_id,event.org_hierarchy_id).subscribe(res=>{
      this.regionList = res['result']
      this.TrainingattendanceTrainingReportForm.get('region').enable()
    })
  }

  getState(){
    this.service.getStates(1,'').subscribe(res =>{
      this.statesList = res.result
    })
  }

  getStartDate(startDate){
    this.holidayDateMin = startDate
  }

  getEndDate(endDate){
    this.holidayDateMax = endDate
  }

  getTSMName(){
    this.trainingattaendanceReportService.getTsmNameList('').subscribe(res=>{
      this.tsmName = res['result']
    })
  }

  getDealerDesignation(){
    this.trainingattaendanceReportService.getDesignationList('').subscribe(res=>{
      this.dealerDesignationList = res['result']
    })
  }

  searchTrainingReport(searchData){
    this.trainingattaendanceReportService.reportSearchSearch(searchData).subscribe(res=>{
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    })
  }

  getTrainingReport() {
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
    const formValues = this.TrainingattendanceTrainingReportForm.getRawValue();
    let reportFilter = {} as AttendancReporteHeader
    localStorage.setItem(this.key, JSON.stringify(formValues))
    //this.filterData = this.purchaseOrderSearchService.removeNullFromObjectAndFormatDate(formValues);
    reportFilter.region =  formValues.region ?  formValues.region.org_hierarchy_id:null
    reportFilter.state =  formValues.state ?  formValues.state.id:null
    reportFilter.tsmName =  formValues.tsmName ?  formValues.tsmName.id:null
    reportFilter.empStatus =  formValues.employeeStatus ?  formValues.employeeStatus:null
    reportFilter.trainingTypeId =  formValues.typeofTraining ?  formValues.typeofTraining.Training_Type_id:null
    reportFilter.trainingModuleId =  formValues.trainingModule ?  formValues.trainingModule.Training_Module_id:null
    reportFilter.delearEmpDesignation =  formValues.delearEmpDesignation ?  formValues.delearEmpDesignation.id:null
    reportFilter.startDate =  formValues.trainingstartDate ?  this.dateService.getDateIntoDDMMYYYY(formValues.trainingstartDate):null
    reportFilter.endDate =  formValues.trainingendDate ?  this.dateService.getDateIntoDDMMYYYY(formValues.trainingendDate):null
    this.searchFilter = ObjectUtil.removeNulls(reportFilter);
    if (this.TrainingattendanceTrainingReportForm.valid) {
      if (this.checkValidDatesInput()) {
        if (this.searchFlag == true && this.TrainingattendanceTrainingReportForm.get('zone').value || this.TrainingattendanceTrainingReportForm.get('region').value || this.TrainingattendanceTrainingReportForm.get('state').value || this.TrainingattendanceTrainingReportForm.get('tsmName').value || this.TrainingattendanceTrainingReportForm.get('employeeStatus').value || this.TrainingattendanceTrainingReportForm.get('typeofTraining').value || this.TrainingattendanceTrainingReportForm.get('trainingModule').value || this.TrainingattendanceTrainingReportForm.get('delearEmpDesignation').value || this.TrainingattendanceTrainingReportForm.get('trainingstartDate').value || this.TrainingattendanceTrainingReportForm.get('trainingendDate').value) {
          this.searchTrainingReport(reportFilter)
        }
        else if (this.TrainingattendanceTrainingReportForm.get('trainingstartDate').value==null && this.TrainingattendanceTrainingReportForm.get('trainingendDate').value==null) {
          this.toastr.error("Please fill atleast one input field.");
        }
      } else {
        this.toastr.error("Please Select Date Range.");
      }
    }
    else {
      this.toastr.error('Plese select value from list', 'Error');
    }
    // this.downloadExcelReport(reportFilter);
  }
  checkValidDatesInput() {
    const fltEnqObj = this.TrainingattendanceTrainingReportForm.value

    fltEnqObj.fromDate = this.TrainingattendanceTrainingReportForm.getRawValue() ? this.TrainingattendanceTrainingReportForm.value.trainingstartDate : null
    fltEnqObj.toDate = this.TrainingattendanceTrainingReportForm.getRawValue() ? this.TrainingattendanceTrainingReportForm.value.trainingendDate : null
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
  downloadExcelReport(event) {
    let searchObject= this.TrainingattendanceTrainingReportForm.getRawValue();
    this.trainingattaendanceReportService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
       
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  tableClear(){
    this.dealerNameNgModel=''
    this.dealerNameNgModel=''
    this.isactiveNgModel=''
    this.level_idNgModel=''
    this.org_hierarchy_idNgModel=''
    this.parent_org_hierarchy_idNgModel=''
  }
  clear() {
    this.TrainingattendanceTrainingReportForm.reset()
    this.dataTable = null
    localStorage.removeItem(this.key)
    this.clearSearchRow.next("");
  }
  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.getTrainingReport()
  }
  tableAction(event: object) {
    console.log('event---', event)
    if (event['btnAction'] === 'edit') {
     
    }
   

  }
}
