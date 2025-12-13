import { Component, OnInit } from '@angular/core';
import { TrainingattendanceSheetPageStore } from './attendance-sheet-create-page.store';
import { TrainingattendanceSheetPagePresenter } from './attendance-sheet-create-page.presenter';
import { FormGroup, FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { MatAutocompleteSelectedEvent, MatDatepickerInput, MatDialog } from '@angular/material';
import { TrainingattendanceSheetService } from '../../service/attendance-sheet.service';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { DateService } from 'src/app/root-service/date.service';
import { AttendanceDtl, AttendanceHeader, AttendanceHeaders, GrowthIndex } from '../../domain/attendance-sheet.domain';
import { TrainingProgCalenderService } from '../../../training-programme-calender/service/training-prog-calender.service';
import { ProgramNumber } from '../../../training-programme-calender/domain/tpc-domain';
import { TrainingnominationService } from '../../../training-nomination/service/training-nomination.service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { TrainingApi } from '../../url-utils/attendance-sheet-url';
import { DatePipe } from '@angular/common';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { count } from 'console';

@Component({
  selector: 'app-attendance-sheet-create-page',
  templateUrl: './attendance-sheet-create-page.component.html',
  styleUrls: ['./attendance-sheet-create-page.component.scss'],
  providers: [TrainingattendanceSheetPageStore,DatePipe, TrainingattendanceSheetPagePresenter, TrainingattendanceSheetService, FileUploadService]
})
export class TrainingattendanceSheetCreatePageComponent implements OnInit {
  trainingattendanceSheetForm: FormGroup;
  allForm: FormGroup
  implementForm: FormArray;
  attandenceSheetDays: FormArray

  dummyDateForm:FormGroup
  controlsArr:string[]=[]
  files: Array<UploadableFile> = new Array()
  isEdit: boolean
  isView: boolean=false
  isCreate: boolean
  type: string
  todayDate = new Date();
  designationList: any
  implementHeading: any = []
  departmentList: any;
  programNoList$: ProgramNumber[] = []
  headerDeatils: any
  trainers: any;
  holidays: any;
  days: any
  fileStaticPath: string = TrainingApi.staticPath
  trainingDateList: Array<any> = []
  submitButtonView: boolean = true
  startDate: Date;
  endDate: Date;
  a: any = []
  b: any
  newDateRange: any = []
  dateRangeData: any = []
  checked = false;
  newDays:any=[]
  showLocation:boolean=false
  holidayDate:any
  createHolidayDate: any;
  pretestValue:number=0;
  postTestValue:number=0;
  imageList: any;
  totolAverageIndex:number=0
  editAverageIndex:any;
  programId:number
  editAverageIndexValue:any;
  buttonDisabled:boolean=false;
  constructor(
    private trainingattendanceSheetPagePresenter: TrainingattendanceSheetPagePresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    public dialog: MatDialog,
    private trainingAttendanceService: TrainingattendanceSheetService,
    private fileUploadService: FileUploadService,
    private dateService: DateService,
    private service: TrainingProgCalenderService,
    private nominationService: TrainingnominationService,
    private fb: FormBuilder,
    private loginService: LocalStorageService,
    private datePipe: DatePipe,
    private store:TrainingattendanceSheetPageStore
  ) { }

  ngOnInit() {
    // this.getAttendanceTrainers(this.loginService.getLoginUser().departmentName)
    this.trainingattendanceSheetPagePresenter.operation = OperationsUtil.operation(this.activatedRoute)
    // console.log(this.trainingattendanceSheetPagePresenter.operation,'this.trainingattendanceSheetPagePresenter.operation')
    this.trainingattendanceSheetForm = this.trainingattendanceSheetPagePresenter.trainingattendanceSheetForm;
    this.implementForm = this.trainingattendanceSheetPagePresenter.attendanceDtl;
    this.attandenceSheetDays = this.trainingattendanceSheetPagePresenter.attendanceSheetDays
    this.allForm = this.trainingattendanceSheetPagePresenter.allForm

    this.viewOrEditOrCreate()
    // this.trainingattendanceSheetPagePresenter.patchAttance(null)

    // this.getAttendanceTrainers()

  }

  fileSelctionChanges(fileInput: Event) {
    
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput);
      this.files = this.fileUploadService.listUploadableFiles();
      this.trainingattendanceSheetPagePresenter.collectPCRFiles(this.files);
    }
  }
  deleteImage(id: string) {
    // console.log(this.files);
    this.fileUploadService.deleteFile(id);
    this.files = this.fileUploadService.listUploadableFiles();
    this.trainingattendanceSheetPagePresenter.collectPCRFiles(this.files);
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5;
  }
  // fileSelctionChanges(fileInput: any) {
  //   debugger
  //   //   this.fileUploadService.onFileSelect(fileInput)
  //   //   this.files = this.fileUploadService.listUploadableFiles()
  //   // console.log('fileInput--', this.files)
  //   //   this.trainingattendanceSheetPagePresenter.fileSelectionChanges(this.files)
  //   //   this.implementForm.get('photos').patchValue(this.files)


  //   const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0])

  //   if (msg != 'OK') {
  //     this.tostr.warning(msg);
  //     this.trainingattendanceSheetPagePresenter.attendanceDtl.get('photos').reset()
  //     return false;
  //   }
  //   if (!this.isFilesCount()) {
  //     this.fileUploadService.onFileSelect(fileInput)
  //     this.files = this.fileUploadService.listUploadableFiles()
     
  //   //  this.trainingattendanceSheetPagePresenter.fileSelectionChanges(this.files)
      
  //   }
  // }
  // isFilesCount() {
  //   return this.fileUploadService.fileCount() == 1
  // }
  private viewOrEditOrCreate() {
    if (this.trainingattendanceSheetPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      // this.trainingattendanceSheetForm.get('programDate').patchValue(this.todayDate)
      this.getAttendanceTrainers(this.loginService.getLoginUser().departmentName)
      this.getNomineePersonDesignation()
    }
    if (this.trainingattendanceSheetPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
      this.getDataFromSearchPage()
    }
    if (this.trainingattendanceSheetPagePresenter.operation === Operation.VIEW) {
    
      this.isView = true
      this.getDataFromSearchPage()
      this.implementForm.disable()
    
    }
  }
  deleteImageHour(id: string) {
    // if (this.trainingattendanceSheetPagePresenter.attendanceDtl.get('photos').value) {
    //   this.trainingattendanceSheetPagePresenter.attendanceDtl.get('photos').reset()
    // }
    this.trainingAttendanceService.deleteFile(id)
    this.files = this.trainingAttendanceService.listUploadableFileslist()
  }
  getDataFromSearchPage() {
    this.activatedRoute.queryParamMap.subscribe(param => {
      if (param.has('id')) {
        this.patchDataViewEdit(parseInt(param.get('id')))
        this.programId=parseInt(param.get('id'))
      }
    })
  }
  patchDataViewEdit(programId: number) {
    let response: any
    this.trainingAttendanceService.getattendanceDataforViewEdit(programId).subscribe(res => {
     this.imageList=res.result.docsDtl;
      response = res['result'].TcpHeaderDtl;
      this.holidayDate=response.holiday_date;
      const array = this.holidayDate.split(',');
      this.headerDeatils = res['result']
      if (this.isEdit || this.isView) {
        var getDateArray = function (start, end) {
          var arr = new Array();
          var dt = new Date(start);
          while (dt <= end) {
            arr.push(new Date(dt));
            dt.setDate(dt.getDate() + 1);
          }
          return arr;
        }
      var dateArr = getDateArray(this.dateService.stringToDateIntoDDMMYYYY(response.startDate), this.dateService.stringToDateIntoDDMMYYYY(response.endDate));
      if (res['result'].AttendanceDtl != null && res['result'].nominEmpIndex != null) {
        res['result'].AttendanceDtl.forEach((value, i) => {
          const empNominIndex = res['result'].nominEmpIndex[i] ; // Get the corresponding element from the other list
        
          // Create a FormGroup for each element in AttendanceDtl
          const mergedList = {...value, ...empNominIndex};
          let fg: FormGroup = this.trainingattendanceSheetPagePresenter.patchAttance(mergedList);
          let count: number = 0;
          dateArr.forEach(element => {
            let control_name = "D_" + count;
            fg.addControl(control_name, new FormControl(null));
            if (this.controlsArr.length < dateArr.length) {
              this.controlsArr.push(control_name);
            }
            // Compare each date to the holidayDates and disable the control if it's a holiday.
            const formattedDate = this.datePipe.transform(element, 'dd-MM-yyyy');
            if (array.includes(formattedDate)) {
              fg.get(control_name).disable();
            }
            count++;
          });
      
          if (value != null) {
            this.controlsArr.forEach(key => {
              if (fg.get(key)) {
                fg.get(key).patchValue(value[key]);
              }
            });
          }
      
          
        });
      }
      
      const count = res.result.nominEmpIndex.length;
      let editAverageIndex = 0;
      if(res.result.nominEmpIndex!=null || res.result.nominEmpIndex>0){
        // let amount=0
        res.result.nominEmpIndex.forEach((ele:any)=>{
          if(ele.growthIndex!==null && ele.growthIndex!==''){
          editAverageIndex += parseFloat(ele.growthIndex);
            // console.log(editAverageIndex,'editAverageIndex')
          }
          

            
        })
        const finalValue=editAverageIndex/count;
        this.editAverageIndex=finalValue.toFixed(2)
      }
      
      // this.trainingattendanceSheetForm.get('dealerId').patchValue(res.result.AttendanceDtl.dealerId)
      this.implementHeading = dateArr; 
      this.trainingattendanceSheetForm.get('programNumber').patchValue(response.programNumber)
      this.trainingattendanceSheetForm.get('programDate').patchValue(response.programDate)
      this.trainingattendanceSheetForm.get('trainingLocation').patchValue(response.trainingLocation)
      this.trainingattendanceSheetForm.get('typeofTraining').patchValue(response.training_type_desc)
      this.trainingattendanceSheetForm.get('trainingModule').patchValue(response.trainingModuleDesc)
      this.trainingAttendanceService.getAttendanceTrainers(this.loginService.getLoginUser().departmentName).subscribe(trRes => {
        this.trainers = trRes['result']
        this.trainingattendanceSheetForm.get('trainer1').patchValue(this.trainers.filter(tr1 => tr1.id == res['result'].Trainers.trainer1)[0])
        this.trainingattendanceSheetForm.get('trainer2').patchValue(this.trainers.filter(tr1 => tr1.id == res['result'].Trainers.trainer2)[0])
      })
      this.trainingattendanceSheetForm.get('trainingLocation').patchValue(response.trainingLocDesc)
      this.trainingattendanceSheetForm.get('dealerLocation').patchValue(response.location)
      this.trainingattendanceSheetForm.get('chassisNo').patchValue(response.chassisNo)
      this.trainingattendanceSheetForm.get('startDate').patchValue(response.startDate)
      this.trainingattendanceSheetForm.get('endDate').patchValue(response.endDate)
      this.trainingattendanceSheetForm.get('remarks').patchValue(response.remarks)
      }

    })

   

    
    if (this.isView) {
      this.trainingattendanceSheetForm.disable()
    }
  }
  buildJsonForSubmit() {
    const attendance = this.trainingattendanceSheetForm.getRawValue()
    const attendance1 = this.implementForm.getRawValue()
    let attendancDetails: Array<AttendanceDtl> = []
    let growthIndexs:Array<GrowthIndex>=[]
    //  let attendanceHeaderForm = {} as AttendanceHeader
     let attendanceHeaderForm = {} as AttendanceHeaders;
    //console.log('vinay----',attendance1);
    let details: Array<AttendanceDtl> = []

    this.isEdit == true ? attendanceHeaderForm.programId = attendance.programNumber : null
    attendanceHeaderForm.programId = this.headerDeatils.TcpHeaderDtl.Id
    attendanceHeaderForm.programNominationHdrId = this.headerDeatils.TcpHeaderDtl.Id
    attendanceHeaderForm.trainer1 = attendance.trainer1['id'] ? attendance.trainer1['id'] : null
    attendanceHeaderForm.trainer2 = attendance.trainer2['id'] ? attendance.trainer2['id'] : null
    attendanceHeaderForm.avgGrowthIndex=this.totolAverageIndex;
    attendanceHeaderForm.attendanceDtl = attendancDetails,
    attendanceHeaderForm.nominationEmpIndexs=growthIndexs,
    attendanceHeaderForm.multipartFileList = this.trainingattendanceSheetPagePresenter.collectFiles;

    this.implementForm.controls.forEach(list =>{

      this.controlsArr.forEach(control=>{
             
        if (list.get(control) && list.get(control).value!=null) {
          attendancDetails.push({
            programNominationDtlId: list.get('programNominationDTLId').value,
            trainingDate: (typeof list.get(control).value == 'string') == true ? list.get(control).value:null,
            attendance: (typeof list.get(control).value === 'string')== true ? 'P' : '',
          })
        }
      })
    })

    this.implementForm.controls.forEach(list =>{
      growthIndexs.push({
              programNominationDtlId: list.get('programNominationDTLId').value,
              preTest:list.get('preTest').value,
              postTest:list.get('postTest').value,
              growthIndex:list.get('growthIndex').value,
              
            })
    })
    return attendanceHeaderForm
  }
  buildJsonForFileSubmit() {
    const attendance1 = this.implementForm.getRawValue()

    let attendanceHeaderForm = {} as AttendanceHeader

    // attendanceHeaderForm.designation = attendance1.designation
    // attendanceHeaderForm.photos = attendance1.photos
    return attendanceHeaderForm
  }
  validateForm() {
if (this.trainingattendanceSheetForm.status == 'INVALID') {
  this.tostr.error('Please check Mandatory Fields', "Mandatory Field");
  this.trainingattendanceSheetForm.markAllAsTouched();
} else {
  let postTestGreaterThanPreTest = false; // Initialize a flag
  this.implementForm.controls.forEach(ele => {
    const preTestValue = parseInt(ele.get('preTest').value);
    const postTestValue = parseInt(ele.get('postTest').value);
    if (postTestValue < preTestValue) {
  
      postTestGreaterThanPreTest = true; // Set the flag if postTest is greater
      return false; // Exit the loop
    }
  });

  if (postTestGreaterThanPreTest) {
    
    this.implementForm.controls.forEach(ele => {
      const preTestValue = parseInt(ele.get('preTest').value);
      const postTestValue = parseInt(ele.get('postTest').value);
      this.tostr.error("Post Test Value is Not Less Than Pre test Value");
      if (preTestValue !== null  && (postTestValue === null)) {
        ele.get('postTest').setErrors({ required: 'Post Value is Required' });
        ele.get('postTest').setValidators(Validators.required);
        ele.get('postTest').updateValueAndValidity();
        // Add the CSS class to the input element
        ele.get('postTest').setErrors({ 'cssClass': 'postTest-error' });
      }
    });
    
  
  
  } else {
    this.openConfirmDialog();
  }
}

  }


  submitForm() {
    this.trainingAttendanceService.submitNomineAttendanceSheet(this.buildJsonForSubmit(), this.buildJsonForFileSubmit()).subscribe(res => {
      if (res['status']=="200") {
        this.tostr.success(res['message']);
        this.buttonDisabled=true;
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      } else {
        this.tostr.error(res['message'])
      }
    })
  }

  updateForm() {
    this.trainingAttendanceService.updateNomineAttendanceSheet(this.buildJsonForSubmit(), this.buildJsonForFileSubmit()).subscribe(res => {
      if (res.status == 200) {
        this.tostr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      } else {
        this.tostr.error(res.message)
      }
    })
  }

  reset() {
    this.trainingattendanceSheetForm.reset()
  }
  private openWarningDialog(): void | any {
    const message = `Data Not Available !`;
    const warning = this.setWarningModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '300px',
      panelClass: 'confirmation_modal',
      data: warning
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'OK') {
        this.implementForm.reset()
        this.attandenceSheetDays.clear()
        this.trainingattendanceSheetForm.reset()
        this.implementHeading = []
      }
    })
  }
  private setWarningModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Warning';
    confirmationData.message = message;
    confirmationData.buttonName = ['OK'];
    return confirmationData;
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

  setToDate(event: MatDatepickerInput<Date>, fieldName: string) {
    // if (event && event['value']) {
    //   this.toDate1 = new Date(event['value']);
    //   let maxDate = new Date(event['value']);
    //   maxDate.setMonth(maxDate.getMonth() + 1);
    //   if (maxDate > this.newdate)
    //     this.toDate2 = this.newdate;
    //   else
    //     this.toDate2 = maxDate;
    //   if (this.searchForm.get('goodwillToDate').value > this.toDate2)
    //     this.searchForm.get('goodwillToDate').patchValue(this.toDate2);
    // }
  }


  getProgramNumber(programNo: string) {
    if (programNo) {
      this.trainingAttendanceService.getNomineeAutoProgramNo(programNo).subscribe(res => {
        this.programNoList$ = res['result']
      })
    }
  }

  checkProgNo(prog: any) {
    if (typeof this.trainingattendanceSheetForm.get('programNumber').value != 'object') {
      this.trainingattendanceSheetForm.get('programNumber').setErrors({
        progError: 'Please select from list'
      })
    }
  }

  selectProgramNo(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.trainingattendanceSheetForm.get('programNumber').setErrors(null);
    }
    this.getProgramNomineeProgDtl(event.option.value.id)

  }
  getProgramNomineeProgDtl(programId: number) {
  
    this.trainingattendanceSheetForm.get('programNumber').disable();
    this.trainingattendanceSheetForm.reset()
    this.trainingAttendanceService.getProgramNomineeProgDtl(programId).subscribe(res => {
      this.headerDeatils = res['result']
      // console.log(this.headerDeatils)
      if(this.headerDeatils['TcpHeaderDtl']==='Dealer Location'){
        this.showLocation=true
      }else{
        this.showLocation=false
      }
      if ((this.dateService.stringToDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.startDate)) <= new Date()) {
        this.submitButtonView = true
      } else if ((this.dateService.stringToDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.startDate)) <= new Date()) {
        this.submitButtonView = false
      }

      if ((this.dateService.stringToDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.startDate)) <= new Date()) {
        this.trainingattendanceSheetForm.patchValue({
          programNumber: this.headerDeatils.TcpHeaderDtl.programNumber,
          programDate:this.headerDeatils.TcpHeaderDtl.programDate,
          // programDate: this.dateService.getDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.programDate),
          typeofTraining: this.headerDeatils.TcpHeaderDtl.training_type_desc,
          trainingModule: this.headerDeatils.TcpHeaderDtl.trainingModuleDesc,
          trainingLocation: this.headerDeatils.TcpHeaderDtl.trainingLocDesc,
          dealerLocation: this.headerDeatils.TcpHeaderDtl.location,
          startDate: this.headerDeatils.TcpHeaderDtl.startDate,
          endDate: this.headerDeatils.TcpHeaderDtl.endDate,
          remarks: this.headerDeatils.TcpHeaderDtl.remarks,

        })
      const holidayDate=this.headerDeatils.TcpHeaderDtl.holiday_date;
      // console.log(holidayDate,'holidateDate')
      if(holidayDate==null){

      }else{
      this.createHolidayDate = holidayDate.split(',');
      }
      

      }
      else {
        this.tostr.warning('Allow Only After ' + this.headerDeatils.TcpHeaderDtl.startDate)
      }
      if (this.headerDeatils.AttendanceDtl.length != 0) {
       this.getTableData()
      } else {
        this.implementForm.reset()
        this.attandenceSheetDays.clear()
        this.implementHeading = null
        this.openWarningDialog()
      }
    })

  }
  // getTableData() {
  //   if (this.dateService.stringToDateIntoDDMMYYYY( this.headerDeatils.TcpHeaderDtl.endDate)<= new Date()) {
  //     var dateArr = this.getDateArray(this.dateService.stringToDateIntoDDMMYYYY( this.headerDeatils.TcpHeaderDtl.startDate), this.dateService.stringToDateIntoDDMMYYYY( this.headerDeatils.TcpHeaderDtl.endDate));
  //   }else{
  //     var dateArr = this.getDateArray(this.dateService.stringToDateIntoDDMMYYYY( this.headerDeatils.TcpHeaderDtl.startDate), new Date());
  //   }

  //   this.headerDeatils.AttendanceDtl.forEach(value => {
  //     let i=0;
      
  //   let fg:FormGroup = this.trainingattendanceSheetPagePresenter.patchAttance(value);
  //   console.log(fg,'fg')
  //     let count:number = 0;
  //     dateArr.forEach(element => {
  //         console.log(element,'elementforCreate')
  //       // const Date = this.datePipe.transform(element,'dd-MM-yyyy');

  //       for (let i = 0; i < this.createHolidayDate.length; i++) {
  //         const dateString = this.createHolidayDate[i];
  //         const formattedCurrentDate = this.datePipe.transform(element, 'dd-MM-yyyy');
  //         if (dateString==formattedCurrentDate) { 
  //           let control_name = "D_" + count;
  //           fg.addControl(control_name, new FormControl({ value: null, disabled: true }));
  //         } else {
  //           let control_name = "D_" + count;
  //           fg.addControl(control_name, new FormControl(null));
  //         }
  //       }
  //       let control_name = "D_"+count;
  //       // console.log(control_name,'controlName')
  //        fg.addControl(control_name, new FormControl(null));
        
  //       if (this.controlsArr.length<dateArr.length) {
          
  //         this.controlsArr.push(control_name);
  //       }
  //       count++;
  //     })
  //   })
  //   console.log(dateArr,'DateArray')
  //   this.implementHeading = dateArr


  // }

  getTableData() {
    if (this.dateService.stringToDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.endDate) <= new Date()) {
      var dateArr = this.getDateArray(this.dateService.stringToDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.startDate), this.dateService.stringToDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.endDate));
    } else {
      var dateArr = this.getDateArray(this.dateService.stringToDateIntoDDMMYYYY(this.headerDeatils.TcpHeaderDtl.startDate), new Date());
    }
  
    const holidayDates = this.createHolidayDate.reduce((obj, date) => {
      obj[date] = true;
       // Mark holiday dates
      return obj;
    }, {});
  
    this.headerDeatils.AttendanceDtl.forEach(value => {
      let fg: FormGroup = this.trainingattendanceSheetPagePresenter.patchAttance(value);
      let count: number = 0;
  
      dateArr.forEach(element => {
        let formattedCurrentDate = this.datePipe.transform(element, 'dd-MM-yyyy');
  
        let control_name = "D_" + count;
        fg.addControl(control_name, new FormControl({ value: null, disabled: holidayDates[formattedCurrentDate] === true }));
        
        if (this.controlsArr.length < dateArr.length) {
          this.controlsArr.push(control_name);
        }
        count++;
      });
    });
  
    this.implementHeading = dateArr;
  }
  
 getDateArray(start, end) {
    var arr = new Array();
    var dt = new Date(start);
    while (dt <= end) {
      arr.push(new Date(dt));
      dt.setDate(dt.getDate() + 1);
    }
    return arr;
  }

  check(fg:FormGroup, event: any) {
      this.controlsArr.forEach(control=>{
        if (fg.get(control)) {
          // console.log('vinay----',fg.get(control).value );
          if (fg.get(control).value == true ) {
            fg.get(control).patchValue(this.dateService.getDateIntoDDMMYYYY(event))
          }else if(fg.get(control).value == false) {
            fg.get(control).patchValue(false)
            fg.get(control).reset()
          }
        }
      }) 
      // console.log('vinay----',this.implementForm);
  }


  getAttendanceTrainers(departmentName: string) {
    this.trainingAttendanceService.getAttendanceTrainers(departmentName).subscribe(res => {
      this.trainers = res['result']
    })
  }
  displayFnForProgramNo(selectedOption: ProgramNumber) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['programNumber'] : undefined;
  }

  getNomineePersonDesignation() {
    this.nominationService.nomineeDesignation('').subscribe(res => {
      this.designationList = res['result']
    })
  }

  checkTrainer1(event) {
    if (event.id === this.trainingattendanceSheetForm.get('trainer1').value.id) {
      this.trainingattendanceSheetForm.get('trainer2').reset()
    }
  }
  checkTrainer2(event) {
    if (event.id === this.trainingattendanceSheetForm.get('trainer2').value.id) {
      this.trainingattendanceSheetForm.get('trainer1').reset()
    }
  }

  createGroup(dateArray:Array<any>) {
    let aar:any[]=[]
    dateArray.forEach(res=>{
      aar.push(this.dateService.getDateIntoDDMMYYYY(res))
    }) 
    aar.forEach(control => this.dummyDateForm.addControl(control, this.fb.control('')));
    return this.dummyDateForm;
  }


  onExit(){
    // debugger
    this.router.navigate(['../'], { relativeTo: this.activatedRoute });
  }

  enterPreTest(event:any,fg:any){
      this.pretestValue=event.target.value;
      if(event.key=="Backspace" || event.key=="Delete"){
        this.calculateAverageIndex(fg)
        if(fg.get('preTest').value==null || fg.get('preTest').value==''){
          fg.get('postTest').reset();
        }
      }else{
       
      }
     if(event.target.value>100){
      this.tostr.error("You Are Not Enter More Than 100");
      fg.get('preTest').reset();
      
     }
  }

 
  enterPostTest(event: any, fg: any) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
     
      if (fg.get('preTest').value == null) {
        fg.get('postTest').reset();
        fg.get('growthIndex').reset();
      } else {
        const postTestValue = parseFloat(fg.get('postTest').value);
        const preTestValue = parseFloat(fg.get('preTest').value);
  
        if (!isNaN(postTestValue) && event.key === 'Backspace' && postTestValue < preTestValue) {
          this.tostr.error('Post Test Value is not Less than Pre Test Value');
          fg.get('growthIndex').reset();
          this.trainingattendanceSheetPagePresenter.calculateAverageIndex();
          this.totolAverageIndex=this.trainingattendanceSheetPagePresenter.aomunt.toFixed(2);
        }
        if(fg.get('postTest').value=='' ||fg.get('postTest').value==null){
           fg.get('growthIndex').reset();
        }else{
        
        }
        
      }
      
     
    } else {
      const postTestValue = parseFloat(fg.get('postTest').value);
      const preTestValue = parseFloat(fg.get('preTest').value);
      if (!isNaN(postTestValue) && postTestValue < preTestValue) {
       
        fg.get('postTest').setErrors(
          { 
          qtyError: 'Post Value is not Less than Pre Test Value',
        }
        );
      }
      fg.get('growthIndex').reset();
      this.calculateAverageIndex(fg)
       
      if(fg.get('postTest').value>100){
        this.tostr.error("You Are Not Enter More Than 100");
        fg.get('postTest').reset();
        fg.get('growthIndex').reset();
        
       }
    }
  }
  
  

  calculateAverageIndex(fg:any){
 const calculateAverageIndex=((fg.get('postTest').value - fg.get('preTest').value) / (100 - fg.get('preTest').value)) * 100;
   if(calculateAverageIndex<0){
    fg.get('growthIndex').reset();
   }else{
    fg.get('growthIndex').patchValue(calculateAverageIndex.toFixed(2)?calculateAverageIndex.toFixed(2):0)
    this.trainingattendanceSheetPagePresenter.calculateAverageIndex();
    this.totolAverageIndex=this.trainingattendanceSheetPagePresenter.aomunt.toFixed(2);
   }
  // console.log(calculateAverageIndex.toFixed(2),'calculateAverageIndex')
  
  }

  validInput(event: any) {
    const inputChar = String.fromCharCode(event.charCode);
    if (inputChar === "0" && event.target.value.trim() === "") {
      event.preventDefault();
    }
    const pattern = /^\d*\.?\d{0,2}$/; // Allow only digits and dot
    if (!pattern.test(inputChar)) {
      event.preventDefault(); // Prevent the invalid character from being entered
    }
  }


  downloadCertificate(fg:any){
    const postData={
      programId:this.programId,
      employeeId:fg.get('employeeID').value,
      dealerId:fg.get('dealerId').value,
      printStatus:true,
    }

    this.trainingAttendanceService.downloadCertificate(postData).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
        else{
          this.tostr.error("Server Side Error! Pdf Not Download");
        }
   })
  }
    

}