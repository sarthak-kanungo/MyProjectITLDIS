import { AfterViewInit, Component, OnChanges, OnInit, SimpleChanges, ViewChild, ViewEncapsulation } from '@angular/core';
import { TrainingProgrammeCalenderPageStore } from './training-prog-calender-create-page.store';
import { TrainingProgrammeCalenderPagePresenter } from './training-prog-calender-create-page.presenter';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MatDatepicker, MatDatepickerInputEvent, MatDialog, MatOption } from '@angular/material';

import { TrainingProgCalenderService } from '../../service/training-prog-calender.service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { Abc, ApeoveDetails, TpcDealerDetails, TpcHeader, TpcHolidayDetails } from '../../domain/tpc-domain';
import { DateService } from 'src/app/root-service/date.service';
import { runInThisContext } from 'vm';
export interface Task {
  name: string;
  completed: boolean;
  subtasks?: Task[];
}

@Component({
  selector: 'app-training-prog-calender-create-page',
  templateUrl: './training-prog-calender-create-page.component.html',
  styleUrls: ['./training-prog-calender-create-page.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers: [TrainingProgrammeCalenderPageStore, TrainingProgrammeCalenderPagePresenter, TrainingProgCalenderService]
})
export class TrainingProgrammeCalenderCreatePageComponent implements OnInit,OnChanges,AfterViewInit {
  tpcForm: FormGroup;
  implementForm: FormArray;
  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.
    // return day !== 0 && day !== 6;
    // Prevent  Sunday from being selected
    return day !== 0
  };
  @ViewChild('allSelected',{static:false})
  private allSelected:MatOption
  task: Task = {
    name: 'Indeterminate',
    completed: false,
  };
  implementHeading = [
    { name: 'S.No' },
    { name: 'Confirm Nomination' },
    { name: 'Attended' },
    { name: 'Dealer Name' },
    { name: 'Nomination No.' },
    { name: 'Nomination Date' },
    { name: 'State' },
    { name: 'Dealer Location' },
    { name: 'Designation' },
    { name: 'Emp Code' },
    { name: 'Name' },
    { name: 'Emp Contact Number' },
    { name: 'T-Shirt Size' },
  ]
  isEdit: boolean
  isView: boolean
  isCreate: boolean
  type:string
  todayDate:Date;
  locationList: Array<any>;
  trainingList: Array<any>
  trainingModuleList:Array<any>
  statesList:Array<any>
  allComplete: boolean = false;
  dealerNameList:Array<any>
  holidayDateMin: Date
  holidayDateMax: Date
  holidaydaysSelected: any[] |any = [];
  public CLOSE_ON_SELECTED = false;
  public init = new Date();
  public resetModel = new Date(0);
  public model = [];
  @ViewChild('picker', { static: true }) _picker: MatDatepicker<Date>;
  programId:number
  programDlrDtlId:number
  programHolidDtlId:number
  isApprove:boolean
  confirmationList=['Accepted',	'Rejected']
  disabled = false;
  isViewNomineeTable:boolean;
  tshirtSizeChart:string[]=['S','M','L','XL','XXL','XXXL']
  tsize: boolean;
  showLocation:boolean=false
  constructor(
    private presenter: TrainingProgrammeCalenderPagePresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private service: TrainingProgCalenderService,
    private loginService: LocalStorageService,
    private dateService: DateService,

  ) {
    
   }


  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute)
    this.type = this.presenter.operation
    this.tpcForm = this.presenter.tpcForm;
    this.implementForm = this.presenter.nominationForm;
    this.presenter.patchRow(null)
    this.tpcForm.get('department').patchValue(this.loginService.getLoginUser().departmentName)
    this.getProgramLocation()
    this.viewOrEditOrCreate()
    if(this.tpcForm.get('endDate').value==null || this.isApprove==true || this.isView==true){
      this.disabled = true
    }

  }
  ngOnChanges(){
    // this.tpcForm
  }
 
  ngAfterViewInit() {
    if (!this.isCreate) {
    this.patchDataViewEdit(this.programId)
    this.getNominationDetails(this.programId)
    }
  }

  toggleAllSelection(){
    if (this.allSelected.selected) {
      // this.pinCodeListFiltered.forEach(ele => {
      //   ele['isSelected'] = true;
      //   this.pinCodeList.find(ele => ele.id)['isSelected'] = true;               //  Require if select all is from filter
      // })    [...this.pinCodeList.filter(ele => ele.isSelected), 'all']
      // this.pinCodeListFiltered.push('all')
     
      this.tpcForm.controls.dealerName.patchValue([...this.dealerNameList,'all']);
      // console.log("this.centerPinMappingForm.controls.pinCode ", this.centerPinMappingForm.controls.pinCode.value);
    } else {
      // this.pinCodeListFiltered.forEach(ele => {
      //   ele['isSelected'] = false;
      //   this.pinCodeList.find(ele => ele.id)['isSelected'] = false;             //  Require if select all is from filter
      // })
      // if (this.isEdit && this.pinDataForUpdate['pin'].length > 0) {
      //   this.pinDataForUpdate['pin'].forEach(element => {
      //     element.activeStatus = 'N';
      //     this.pinCodeListInActive.push(element);
      //   });
      // }
      this.tpcForm.controls.dealerName.patchValue([]);
    }
  }

  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.todayDate = new Date();
      this.tpcForm.get('programDate').patchValue(this.todayDate)
      // this.getTrainingType(this.loginService.getLoginUser().departmentId,this.type)
      this.getTrainingType(this.loginService.getLoginUser().departmentName,this.type)
      this.getState()
    }
    if (this.presenter.operation === Operation.EDIT) {
      this.isEdit = true
      this.getDataFromSearchPage()
    }
    if (this.presenter.operation === Operation.VIEW) {
      this.isView = true
      this.getDataFromSearchPage()
    }
  }

  getDataFromSearchPage(){
    this.activatedRoute.queryParamMap.subscribe(param => {
      if (param.has('approval')) {
        this.isApprove = true
      }
      if (param.has('id')) {
        this.programId = parseInt( param.get('id'))
      }
    })
  }

  patchDataViewEdit(programId:number){
    let response:any
    let dealerIdList : Array<TpcDealerDetails> = []
    let holidaysList : Array<TpcHolidayDetails> = []
    this.service.getDataforViewEdit(programId).subscribe(res =>{
      response = res['result']
      this.enableDisbleFields(response)
      this.tpcForm.get('programNumber').patchValue(response.programNumber)
      this.tpcForm.get('programDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(response.createdDate))
      // this.tpcForm.get('department').patchValue(response.programNumber)
      // console.log("hello")
      if (response.trainingLocDesc) {
        this.service.getProgramLocation(this.type).subscribe(res => {
          this.locationList = res.result
          this.tpcForm.get('programLocation').patchValue(this.locationList.filter(pl => pl.Training_loc_desc == response.trainingLocDesc)[0])
        })
      }

      this.tpcForm.get('location').patchValue(response.location)

      if (response.training_type_id) {
        this.service.getTrainingType(this.loginService.getLoginUser().departmentName,this.type).subscribe(res =>{
        // this.service.getTrainingType(this.loginService.getLoginUser().departmentId,this.type).subscribe(res =>{
          this.trainingList = res.result
          this.tpcForm.get('typeofTraining').patchValue(this.trainingList.filter(tt => tt.Training_Type_id == response.training_type_id)[0])
        })
      }


      if (response.training_type_id) {
        this.service.getTrainingModule(response.training_type_id,this.type).subscribe(res =>{
          this.trainingModuleList = res.result
          // console.log(this.trainingModuleList,'liss')
          if(this.trainingModuleList[0].Training_Module_desc==='Dealer CCE Training'){
            this.tsize=true
          }else{
            this.tsize=false
          }
          this.tpcForm.get('trainingModule').patchValue(this.trainingModuleList.filter(tm => tm.Training_Module_id == response.module_id)[0])
        })
      }

      this.tpcForm.get('lastNominationDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(response.lastNominationDate))
      this.tpcForm.get('startDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(response.startDate))
      this.tpcForm.get('endDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(response.endDate))
      this.holidayDateMin = this.dateService.stringToDateIntoDDMMYYYY(response.startDate)
      this.holidayDateMax = this.dateService.stringToDateIntoDDMMYYYY(response.endDate)
      this.tpcForm.get('noNominees').patchValue(response.noOfAllowedNominees)
      this.tpcForm.get('maxNoOfNominees').patchValue(response.maxNoOfNominees)
      this.tpcForm.get('remarks').patchValue(response.remarks)

      

      if ((this.isCreate || this.isEdit) && !this.isApprove) {
        
      let stateArray:any[]=[]
      if (response.dealer_state !=null) {
        let dealerState:Array<any> = response.dealer_state.split(',')
        dealerState.forEach(state =>{
          stateArray.push({
            id: '',
            state: state
          })
        })
      }

      this.patchStateList(stateArray)
      this.programDlrDtlId = response.programDlrDtlId
      
        let dealerArray:any[]=[]
        if (response.dealer_id !=null) {
        let dealerName:Array<any> = response.dealer_id.split(',')
        dealerName.forEach(dealerId =>{
          dealerArray.push({
            id: parseInt(dealerId),
            dealer: ''
          })
        })
        }
  
        if (response.dealer_state !=null) {
          this.service.getDealerNames(response.dealer_state).subscribe(response =>{
            this.dealerNameList = response.result
            console.log(this.dealerNameList,'dealerNameList')
            if (dealerArray && this.dealerNameList) {
              let selectedDealer = [];
              dealerArray.forEach(type => {
                console.log(type,'typeessss')
                selectedDealer.push(this.dealerNameList[this.dealerNameList.findIndex(res => res['id'] === type.id)]);
              })
              this.tpcForm.get('dealerName').setValue(selectedDealer)
            }
          })
        }
      }
      else if (this.isView || this.isApprove) {
        this.tpcForm.get('dealerName').setValue(response.dealer_name)

        this.tpcForm.get('applicableStates').setValue(response.dealer_state)
      }

      this.programHolidDtlId = response.programHolidDtlId
      if (response.holiday_date !=null) {
        let holi:Array<Date> = response.holiday_date.split(',')
        holi.forEach(element => {
          this.model.push(this.dateService.stringToDateIntoDDMMYYYY(element));
        });
        this.tpcForm.get('holidayDate').patchValue({
          holidayDate: this.model
        })
      }

    })

  }

  patchStateList(data){
    this.service.getStates(1,'').subscribe(response => {
      this.statesList = response['result']
      if (data && this.statesList) {
        let selectedState = [];
        data.forEach(type => {
          selectedState.push(this.statesList[this.statesList.findIndex(res => res['state'] === type.state)]);
        })
        this.tpcForm.get('applicableStates').setValue(selectedState)
      }
    })
  }

  enableDisbleFields(data){
    if (this.isApprove) {
      this.tpcForm.disable()
      this.implementForm.markAllAsTouched()
    }
    if(this.isView){
  
      this.tpcForm.disable()
    }
    if (this.dateService.stringToDateIntoDDMMYYYY(data.startDate)<=new Date()) {
          this.tpcForm.get('programNumber').disable()
          this.tpcForm.get('programDate').disable()
          this.tpcForm.get('programLocation').disable()
          this.tpcForm.get('location').disable()
          this.tpcForm.get('typeofTraining').disable()
          this.tpcForm.get('trainingModule').disable()
          this.tpcForm.get('lastNominationDate').disable()
          this.tpcForm.get('startDate').disable()
          // this.tpcForm.get('endDate').disable()
          this.tpcForm.get('noNominees').disable()
          this.tpcForm.get('maxNoOfNominees').disable()
          this.tpcForm.get('remarks').disable()
          this.tpcForm.get('holidayDate').disable()
          this.tpcForm.get('applicableStates').disable()
          this.tpcForm.get('dealerName').disable()
      
    }
  }

  
  getNominationDetails(programId:number){
    this.implementForm.clear()
    this.service.getNominationDetails(programId).subscribe(res =>{
      if (res['result'].length>0) {
        this.isViewNomineeTable = true
      }
      res['result'].forEach(element => {
        let fg:FormGroup = this.presenter.patchRow(element);
        fg.get('nominationDate').patchValue(this.dateService.getDateIntoDDMMYYYY(fg.get('nominationDate').value));
        fg.get('attended').patchValue(element['attended']=='Y'?true:false);
      });
    })
  }

  
  buildDataForNominiesApproval(){
    let approveListAs= {} as Abc
    let approveList:Array<ApeoveDetails>=[]
    this.implementForm.controls.forEach(fg => {
      approveList.push({
        nominationStatus:fg.get('confirmNomination').value,
        attendedStatus:fg.get('attended').value == true ? 'Y':'N',
        programNominationDtlId:fg.get('programNominationDTLId').value
      })
    });
    approveListAs.nomineesApproval = approveList
    return approveListAs;
  }

  approveNominies(){
    this.service.nomineesApproval(this.buildDataForNominiesApproval()).subscribe(res =>{
      if (res.status == 200) {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.toastr.error(res.message)
      }
    })
  }


  getProgramLocation() {
    this.service.getProgramLocation(this.type).subscribe(res => {
      this.locationList = res.result
      console.log(this.locationList,'locationList')
      if(this.locationList.forEach){

      }
    })
  }

  enableLocationField(locationId){
    if(locationId.Training_loc_desc == 'Dealer Location'){ 
      this.showLocation=true;
       this.tpcForm.get('location').enable();
       this.tpcForm.get('location').setValidators(Validators.compose([Validators.required]));
       this.tpcForm.get('location').updateValueAndValidity();
    }else{
      this.showLocation=false
      this.tpcForm.get('location').reset()
       this.tpcForm.get('location').disable();
       this.tpcForm.get('location').setValidators(null);
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

  getState(){
    this.service.getStates(1,'').subscribe(res =>{
      this.statesList = res.result
    })
  }

  getDealerName(stateArray:Array<any>){
    let stateNames:string = ''
    stateArray.forEach(state =>{
      stateNames += state.state+','
    })
    this.service.getDealerNames(stateNames).subscribe(res =>{
      this.dealerNameList = res.result
    })
  }

  getStartDate(startDate){
    this.holidayDateMin = startDate
  }

  getEndDate(endDate){
    this.holidayDateMax = endDate
    if (this.tpcForm.get('lastNominationDate').value >= this.tpcForm.get('endDate').value) {
      this.toastr.error('Last Nomination Date Should not Gratter than End Date')
      this.tpcForm.get('lastNominationDate').setErrors({
        lndError:'Last Nomination Date Should not Gratter than End Date'
      })
    }
  }

  // holidayDateValidation(){
  //   this.tpcForm.get('startDate').value
  //   this.tpcForm.get('endDate').value
  //   this.tpcForm.get('holidayDate').value
  //   console.log('vinay1--',this.tpcForm.get('startDate').value);
  //   console.log('vinay2--',this.tpcForm.get('endDate').value);
  //   console.log('vinay3--',this.tpcForm.get('holidayDate').value);
  //   if (this.tpcForm.get('startDate').value == null ||  this.tpcForm.get('endDate').value == null) {
  //     this.tpcForm.get('holidayDate').setErrors({
  //       startEndError:'Please Select Start and End Date First'
  //     })
  //   }
    
  // }

  buildJsonForSubmit() {
    const tpc = this.tpcForm.getRawValue()
    // console.log('tpc--',tpc);
    
    let tpcHeaderForm = {} as TpcHeader
    let dealerIdList : Array<TpcDealerDetails> = []

    let holidaysList : Array<TpcHolidayDetails> = []
    this.isEdit == true ? tpcHeaderForm.programId = this.programId:null
    this.isEdit == true ? tpcHeaderForm.programNumber =  tpc.programNumber:null
    tpcHeaderForm.programNumber =''
    tpcHeaderForm.programDate = tpc.programDate ? this.dateService.getDateIntoDDMMYYYY(tpc.programDate):null
    tpcHeaderForm.programLocationId = tpc.programLocation ? tpc.programLocation.Training_loc_id:null
    tpcHeaderForm.location = tpc.location
    tpcHeaderForm.trainingTypeId = tpc.typeofTraining ? tpc.typeofTraining.Training_Type_id:null
    // console.log
    tpcHeaderForm.trainingModuleId = tpc.trainingModule ? tpc.trainingModule.Training_Module_id:null
    tpcHeaderForm.lastNominationDate = tpc.lastNominationDate ? this.dateService.getDateIntoDDMMYYYY(tpc.lastNominationDate):null
    tpcHeaderForm.startDate = tpc.startDate ? this.dateService.getDateIntoDDMMYYYY(tpc.startDate):null
    tpcHeaderForm.endDate = tpc.endDate ? this.dateService.getDateIntoDDMMYYYY(tpc.endDate):null
    tpcHeaderForm.noNominees = tpc.noNominees
    tpcHeaderForm.maxNoOfNominees = tpc.maxNoOfNominees
    tpcHeaderForm.remarks = tpc.remarks

    tpcHeaderForm.tpcDealerDetails = dealerIdList
    tpcHeaderForm.tpcHolidayDetails = holidaysList
    tpc.dealerName != null ? tpc.dealerName.forEach(element => {
      if(element.id!=null){
      dealerIdList.push({
        dealerId:element.id,
      })
    }
    }):null
    tpc.holidayDate != null ? tpc.holidayDate.holidayDate.forEach(element => {
      holidaysList.push({
        holidayDate : this.dateService.getDateIntoDDMMYYYY(element),
      })
    }):null

    return tpcHeaderForm
   
  }





  public dateClass = (date: Date) => {
    if (this._findDate(date) !== -1) {
      return [ 'selected' ];
    }
    return [ ];
  }


  public dateChanged(event: MatDatepickerInputEvent<Date>): void {
    if (event.value) {
      const date = event.value;
      const index = this._findDate(date);
      if (index === -1) {
        this.model.push(date);
        this.tpcForm.get('holidayDate').patchValue({
          holidayDate: this.model
        })
        // console.log('model--', this.model)
        // console.log('holidayDate--',  this.tpcForm.get('holidayDate').value)
      } else {
        this.model.splice(index, 1)
      }
      this.resetModel = new Date(0);
      if (!this.CLOSE_ON_SELECTED) {
        const closeFn = this._picker.close;
        this._picker.close = () => { };
        this._picker['_popupComponentRef'].instance._calendar.monthView._createWeekCells()
        setTimeout(() => {
          this._picker.close = closeFn;
        });
      }
    }
  }
    public remove(date: Date): void {
      const index = this._findDate(date);
      this.model.splice(index, 1)
    }

    private _findDate(date: Date): number {
      return this.model.map((m) => +m).indexOf(+date);
    }


  validateForm() {
    this.tpcForm.get('holidayDate').setErrors(null)
    if ((this.isCreate || this.isEdit) && !this.isApprove) {
      if (this.tpcForm.status == 'INVALID') {
        this.toastr.error('Please check Mandatory Fields', "Mandatory Field")
        this.tpcForm.markAllAsTouched()
      }else {
        this.openConfirmDialog()
      }
    }
    if (this.isApprove) {
      let flag:boolean= false
      this.implementForm.controls.forEach(fg => {
          if ( fg.get('confirmNomination').value == null ||  fg.get('confirmNomination').value == "") {
            // fg.get('confirmNomination').setValidators(Validators.required)
            // fg.get('confirmNomination').updateValueAndValidity({emitEvent: false})
            // fg.get('attended').setValidators(Validators.required),
            // fg.get('attended').updateValueAndValidity({emitEvent: false})
            fg.markAllAsTouched()
          }else {
            flag=true
            return;
          }
        })
        if(flag){
          this.openConfirmDialog()
        }
    }
   }


  submitForm() {
    this.service.saveTrainingProgramCalendar(this.buildJsonForSubmit()).subscribe(res =>{
      if (res.status == 200) {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.toastr.error(res.message)
      }
    })
  }

  updateForm() {
    this.service.updateTrainingProgramCalendar(this.buildJsonForSubmit()).subscribe(res =>{
      if (res.status == 200) {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.toastr.error(res.message)
      }
    })
  }

  reset(){
    this.tpcForm.reset()
    this.model =null
  }

  exitForm(){
    this.router.navigate(['../'], { relativeTo: this.activatedRoute })
  }

  private openConfirmDialog(): void | any {
    let message:string;
    if (this.isCreate) {
      message = `Are you sure you want to submit?`;
    }
    if (this.isEdit) {
      message = `Are you sure you want to Update?`;
    }
    if (this.isApprove) {
      message = `Are you sure you want to submit Approval?`;
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && this.isCreate) {
        this.submitForm()
      }
      if (result === 'Confirm' && (this.isEdit && !this.isApprove)) {
        this.updateForm()
      }
      if (result === 'Confirm' && this.isApprove) {
        this.approveNominies()
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

  checkMaxNominee(value){
    this.tpcForm.get('noNominees').reset()
  }

  checkNominee(value){
    if (value > this.tpcForm.get('maxNoOfNominees').value) {
      this.tpcForm.get('noNominees').setErrors({
        nomineeError:'Value Should be less than or Equal to '+this.tpcForm.get('maxNoOfNominees').value
      })
    }
  }

}
