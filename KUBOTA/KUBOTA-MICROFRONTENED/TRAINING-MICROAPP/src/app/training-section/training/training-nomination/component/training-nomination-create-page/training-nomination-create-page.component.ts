import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { TrainingnominationPageStore } from './training-nomination-create-page.store';
import { TrainingnominationPagePresenter } from './training-nomination-create-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';

import { TrainingnominationService } from '../../service/training-nomination.service';
import { DesignationJson, employeeName, NominationHeader, Nominee, NomineeDetails, NomineeDetailsPatch } from '../../domain/training-nomination.domain';
import { DateService } from 'src/app/root-service/date.service';
import { TrainingProgCalenderService } from '../../../training-programme-calender/service/training-prog-calender.service';
import { ProgramNumber } from '../../../training-programme-calender/domain/tpc-domain';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';


@Component({
  selector: 'app-training-nomination-create-page',
  templateUrl: './training-nomination-create-page.component.html',
  styleUrls: ['./training-nomination-create-page.component.scss'],
  providers: [TrainingnominationPageStore, TrainingnominationPagePresenter, TrainingnominationService],
  changeDetection : ChangeDetectionStrategy.OnPush
})
export class TrainingnominationCreatePageComponent implements OnInit {
  implementHeading = [
    { name: 'Select' },
    { name: 'Designation' },
    { name: 'Name' },
    { name: 'Emp Code' },
    { name: 'Status' },
    { name: 'Attended' },
    { name: 'T-Shirt Size' }
  ]
  trainingnominationForm: FormGroup;
  nomineeDetailsForm: FormArray;
  // @tpcForm: FormGroup;
  departmentList: any;
  isEdit: boolean
  isView: boolean
  isCreate: boolean
  type: string
  todayDate:Date 
  programNoList$: ProgramNumber[] = []
  headerDeatils:any
  designationList:Array<any>
  nomineeNameList:Array<any>
  designationId:string
  nomineeDetails:any
  proNo:string
  buttonView:boolean
  tshirtSizeChart:string[]=['S','M','L','XL','XXL','XXXL']
   disableTshirt:boolean=false;
  programId: any;
  constructor(
    private trainingnominationPagePresenter: TrainingnominationPagePresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    public   dialog: MatDialog,
    private nominationService: TrainingnominationService,
    private toastr: ToastrService,
    private dateService: DateService,
    private service: TrainingProgCalenderService,
    private loginService: LocalStorageService,
    private trainingNominationStore: TrainingnominationPageStore
  ) { 
    
  }

  ngOnInit() {
    this.trainingnominationPagePresenter.operation = OperationsUtil.operation(this.activatedRoute)
    this.type = this.trainingnominationPagePresenter.operation
    this.trainingnominationForm = this.trainingnominationPagePresenter.trainingnominationForm;
    this.nomineeDetailsForm = this.trainingnominationPagePresenter.nominationDetailForm;
   
    this.viewOrEditOrCreate()
    
   
  }
  ngAfterViewInit(){
    // this.trainingnominationPagePresenter.patchRow(null)
    console.log(this.trainingnominationForm.controls['typeofTraining'].value,'valuesss')
    if(this.trainingnominationForm.controls['typeofTraining'].value==="Dealer CCE Training"){
      console.log('aa')
      this.disableTshirt=true
    }else{
      console.log('else')
      this.disableTshirt=false
    }
  }

    

  private viewOrEditOrCreate() {
    
    if (this.trainingnominationPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.todayDate = new Date();
      this.getNomineeDesignation()
      console.log(this.trainingnominationForm.controls['typeofTraining'].value,'valuesss')
      
      this.getDataFromSearchPage()
      this.trainingnominationForm.get('nominationDate').patchValue(this.dateService.getDateIntoDDMMYYYY(this.todayDate))
    }
    if (this.trainingnominationPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
      this.getDataFromSearchPage()
    }
    if (this.trainingnominationPagePresenter.operation === Operation.VIEW) {
      this.isView = true
      this.getDataFromSearchPage()
      this.nomineeDetailsForm.disable()
    }
  }



  getDataFromSearchPage() {
    
    this.activatedRoute.queryParamMap.subscribe(param => {
      if (param.has('programNo') && this.isCreate) {
        
        //this.getProgramNumber(param.get('programNo'))
        this.ProgHdr(param.get('programNo'))
       
        
        this.proNo = param.get('programNo')
        this.progDtl(param.get('programNo'))
      }
      else{
      // this.progDtl(param.get('programNo'))
      this.ProgHdr(param.get('programNo'))
        this.patchDataViewEdit(parseInt(param.get('id')))
        
        //this.getProgramNumber(param.get('programNo'))
      }
    })
  }

  patchDataViewEdit(nominationId: number) {
    console.log(this.trainingnominationForm.controls['typeofTraining'].value,'valuesss')
    let response: any
    let desgId: Array<any> =[]
    this.nominationService.nomineeDesignation('').subscribe(response => {
      this.designationList = response['result']
    })
    let nomineeDtlList = {} as NomineeDetailsPatch
    this.nominationService.getDataforViewEdit(nominationId).subscribe(res => {
      response = res['result']
      this.trainingnominationForm.get('nominationNumber').patchValue(response.nominationNumber)
      this.trainingnominationForm.get('nominationNumber').disable()
      this.trainingnominationForm.get('nominationId').patchValue(response.nominationId)
      this.trainingnominationForm.get('nominationDate').patchValue(response.nominationDate)
      // this.trainingnominationForm.get('programNumber').patchValue({
      //   id:response.programId,
      //   programNumber:this.proNo
      // })

      response.nominationDetails.forEach(nomineeDtl => {
        
        // desgId.push({
        //   id:nomineeDtl.designationId
        // })
        let empDtl:Nominee = {
          employeeCode: '',
          employeeName:nomineeDtl. employeeName,
          id: nomineeDtl.employeeId
        }
        nomineeDtlList.nominationDtlId = nomineeDtl.nominationDtlId
        // nomineeDtlList.designation = this.patchDesignation(nomineeDtl.designationId).disable()
        nomineeDtlList.designation = this.patchDesignation(nomineeDtl.designationId)
        nomineeDtlList.status= nomineeDtl.status
        // nomineeDtlList.status= ''
        nomineeDtlList.name = empDtl
        nomineeDtlList.employeeCode = nomineeDtl.employeeCode
        nomineeDtlList.attended=nomineeDtl.attended == 'Y' ? true:false
        nomineeDtlList.tShirtSize=nomineeDtl.tShirtSize
        nomineeDtlList.createdBy=nomineeDtl.createdBy
        nomineeDtlList.createdDate=nomineeDtl.createdDate


        let fg = this.trainingnominationPagePresenter.patchRow(nomineeDtlList)
        fg.disable();

        // if (nomineeDtl.designationId) {
        //   this.nominationService.getAutoEmpName(nomineeDtl.designationId, '').subscribe(res =>{
        //     this.nomineeNameList = res.result
        //   })
        // }
        //console.log('nomineeDetailsForm--', this.nomineeDetailsForm);
      });
      // this.nomineeDetailsForm.controls.forEach(fg=>{
      //   fg.get('name').disable()
      //   fg.get('attended').disable()
      //   fg.get('tShirtSize').disable()
      // })
      
    })

    
  }

  patchDesignation(data){
    // let selectedState = [];
    //   if (this.designationList) {
    //    // data.forEach(type => {
    //       selectedState.push(this.designationList[this.designationList.findIndex(res => res['id'] === data)]);
    //     //})
    //   }
    // return selectedState
    // this.dealerList.filter(dealer=>dealer.id == res['result']['survey'].surveyDoneBy)[0]
    if(this.designationList)
      return this.designationList.filter(desg=>desg.id == data)[0]
    else 
      return null;  
  }

  buildJsonForSubmit() {
    const nomination = this.trainingnominationForm.getRawValue()
    console.log('nomination--', nomination);
    // console.log('nomineeDetailsForm--', this.nomineeDetailsForm);
    

    let nominationHeaderForm = {} as NominationHeader
    let nomineeDtlList: Array<NomineeDetails>=[]
    
    nominationHeaderForm.nominationId = nomination.nominationId?nomination.nominationId:null
    this.isEdit == true ? nominationHeaderForm.nominationNumber ='' :null
    //this.isEdit == true ? nominationHeaderForm.nominationNumber = null:null
    this.isCreate == true ?nominationHeaderForm.nominationNumber = '':null
    nominationHeaderForm.nominationDate = nomination.nominationDate
    if (this.headerDeatils) {
      nominationHeaderForm.programId = this.headerDeatils.programId
      nominationHeaderForm.dealerId = this.headerDeatils.dealerId
    }
    nominationHeaderForm.status = null
    nominationHeaderForm.nominationDetails = nomineeDtlList

    this.nomineeDetailsForm.getRawValue().forEach(element => {
      console.log('vinay--', element);
      nomineeDtlList.push({
        nominationDtlId:element.nominationDtlId,
        nominationId:null,
        employeeId:element ? element.name.id:null,
        status:'',
        // attended:element.attended == true ? 'Y':'N',
        attended:element.attended = null,
        active:element.active == true ? 'Y':'N',
        tShirtSize:element.tShirtSize ? element.tShirtSize:element.tShirtSize="Not Applicable",
        createdBy:element.createdBy,
        createdDate:element.createdDate
      })
    });

    console.log('submitForm----', nominationHeaderForm);

    return nominationHeaderForm
  }
  validateForm() {
    console.log('nomineeDetailsForm--', this.nomineeDetailsForm);
    if (this.trainingnominationForm.status == 'INVALID' || this.nomineeDetailsForm.status == 'INVALID') {
      this.toastr.error('Please check Mandatory Fields', "Mandatory Field")
      this.trainingnominationForm.markAllAsTouched()
      this.nomineeDetailsForm.markAllAsTouched()
    } else {
      this.openConfirmDialog()
    }
  }


  submitForm() {
    this.buildJsonForSubmit()
    this.nominationService.saveNominationForm(this.buildJsonForSubmit()).subscribe(res =>{
      if (res.status == 200) {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.toastr.error(res.message)
      }
    })
  }

  updateForm() {
    this.nominationService.updateNominationForm(this.buildJsonForSubmit()).subscribe(res =>{
      if (res.status == 200) {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.toastr.error(res.message)
      }
    })
  }

  reset() {
    this.trainingnominationForm.reset()
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
  addRow(data?: object) {
    
    return this.trainingnominationPagePresenter.patchRow(data);
    
  }
  deleteRow() {
    this.trainingnominationPagePresenter.deleteRow()
  }

  getProgramNumber(programId:string) {
    // if (programId) {
    //   this.service.getAutoProgramNo(programId).subscribe(res => {
    //     this.programNoList$ = res['result']
    //     //this.trainingnominationForm.get('programNumber').patchValue(this.programNoList$.filter(prog=>prog.programNumber == programNo)[0])
    //   })
    // }
  }

  checkProgNo(prog:any){
    if (typeof this.trainingnominationForm.get('programNumber').value != 'object') {
      this.trainingnominationForm.get('programNumber').setErrors({
        progError:'Please select from list'
      })
    }
    if (this.trainingnominationForm.get('programNumber').status == 'INVALID') {
      this.nomineeDetailsForm.controls.forEach(fg =>{
        fg.get('name').disable()
        fg.get('tShirtSize').disable()
      })
    }
  }

  // selectProgramNo(event: MatAutocompleteSelectedEvent) {
  //   if (event instanceof MatAutocompleteSelectedEvent) {
  //     this.trainingnominationForm.get('programNumber').setErrors(null);
  //   }

  //   if (this.trainingnominationForm.get('programNumber').status == 'VALID') {
  //     this.nomineeDetailsForm.controls.forEach(fg =>{
  //       fg.get('name').enable()
  //       fg.get('tShirtSize').enable()
  //     })
  //   }
  //     this.progDtl(event.option.value.programNumber)
  // }
  ProgHdr(progNo:string){
    this.nominationService.getProgramNomineeProgHdr(progNo).subscribe(response => {
      this.trainingnominationPagePresenter.patchNomineeHeader(response['result'])
      this.headerDeatils = response['result']
      console.log(this.headerDeatils,'header-details')
      var data=this.headerDeatils.trainingTypeDesc
      console.log(data,'ssssss')
      if(data==="Dealer CCE Training"){
        this.disableTshirt=true
      }
      this.programId=this.headerDeatils.programId
     
      this.trainingnominationForm.patchValue({
        programNumber:  response['result'].programNumber,
        typeofTraining:  response['result'].trainingTypeDesc,
        trainingModule: response['result'].trainingModuleDesc,
        trainingLocation: response['result'].trainingLocDesc,
        dealerCode_Name: response['result'].dealerName,
        startDate: this.dateService.stringToDateIntoDDMMYYYY(response['result'].startDate),
        endDate: this.dateService.stringToDateIntoDDMMYYYY(response['result'].endDate),
        remarks: response['result'].remarks,
        
        // typeofTraining :response['result'].startDate,
        
      });
      
     
      // if(this.isCreate){
      //   let rowAdded : boolean = false;
      //   response['result'].filter(d=>d.nominationDtlId!=null).forEach(res => {
      //     rowAdded=true;
      //     res['designation'] = this.patchDesignation(res.designation)
      //     res['name'] = {id:res['employeeId'],employeeName:res['name']}
      //     let fg:FormGroup = this.addRow(res)
      //     fg.get('designation').disable();
      //     fg.get('tShirtSize').disable();
      //     fg.get('name').disable();
      //     fg.get('isSelect').enable();
      //   })
      //   if(!rowAdded){
      //     this.trainingnominationPagePresenter.patchRow(null)
      //   }
      // }
      if (this.dateService.stringToDateIntoDDMMYYYY(response['result'].endDate) >= new Date()) {
        this.buttonView  = true
      }else{
        this.buttonView  = true
      }
    })
  }

  progDtl(progNo:string){
  
    this.nominationService.getProgramNomineeProgDtl(progNo).subscribe(response => {
      this.trainingnominationPagePresenter.patchNomineeHeader(response['result'][0])

      this.headerDeatils = response['result']
      this.trainingnominationForm.patchValue({
        programNumber:  response['result'][0].programNumber,
        typeofTraining:  response['result'][0].trainingTypeDesc,
        trainingModule: response['result'][0].trainingModuleDesc,
        trainingLocation: response['result'][0].trainingLocDesc,
        dealerCode_Name: response['result'][0].dealerName,
        startDate:this.dateService.stringToDateIntoDDMMYYYY(response['result'][0].startDate),
        endDate: this.dateService.stringToDateIntoDDMMYYYY(response['result'][0].endDate),
        remarksremarksremarks: response['result'][0].remarks,
        // typeofTraining :response['result'].startDate,
        
      });
      
      if(this.isCreate){
        let rowAdded : boolean = false;  
        response['result'].filter(d=>d.nominationDtlId!=null).forEach(res => {
          
          rowAdded=true;
          // res['designation']={id:res['designation'],designation:res['designationName']}
           res['designation'] = this.patchDesignation(res.designation)
          res['name'] = {id:res['employeeId'],employeeName:res['name']}
          let fg:FormGroup = this.addRow(res)
         
          fg.get('designation').disable();
          fg.get('tShirtSize').disable();
          fg.get('name').disable();
          fg.get('isSelect').enable();
        })
      
      // this.trainingnominationPagePresenter.patchNomineeHeader(response['result'][0])
      // this.headerDeatils = response['result'][0]
      // if(this.isCreate){
      //   let rowAdded : boolean = false;
      //   response['result'].filter(d=>d.nominationDtlId!=null).forEach(res => {
      //     rowAdded=true;
      //     res['designation'] = this.patchDesignation(res.designation)
      //     res['name'] = {id:res['employeeId'],employeeName:res['name']}
      //     let fg:FormGroup = this.addRow(res)
      //     fg.get('designation').disable();
      //     fg.get('tShirtSize').disable();
      //     fg.get('name').disable();
      //     fg.get('isSelect').enable();
      //   })
        if(!rowAdded){
          this.trainingnominationPagePresenter.patchRow(null)
        }
      }
      
      this.buttonView  = true
      // if (this.dateService.stringToDateIntoDDMMYYYY(response['result'][0].endDate) >= new Date()) {
      //   this.buttonView  = true
      // }else{
      //   this.buttonView  = false
      // }
    })
  }
 


  displayFnForProgramNo(selectedOption: ProgramNumber) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['programNumber'] : undefined;
  }

  getNomineeDesignation(){
    this.nominationService.nomineeDesignation('').subscribe(res =>{
      this.designationList = res['result']
    })
  }

  // getDesignationId(desgArray:Array<any>){
  //   let id:string =''
  //   desgArray.forEach(desgId =>{
  //     id += desgId.id+','
  //   })
  //   this.designationId = id
  // }
  getDesignationId(desgArray){
    this.designationId = desgArray.id
  }


  getNomineeList(searchName:string, fg1:FormGroup){
    if(!fg1.disabled){
      this.nominationService.getAutoEmpName(this.designationId,
          searchName, this.trainingnominationForm.get('typeofTraining').value,
          this.trainingnominationForm.get('trainingModule').value).subscribe(res =>{
        this.nomineeNameList = res.result;
        console.log(this.nomineeNameList,'nomi')
        // this.nomineeDetailsForm.controls.forEach(fg => {
        //   if(fg!=fg1){
            
        //     this.nomineeNameList = this.nomineeNameList.filter(nm => 
        //       {
        //         console.log(nm['designationId'],fg.get('designation').value.id , nm['employeeName'],fg.get('name').value.employeeName);
        //        return (nm['designationId']!=fg.get('designation').value.id && nm['employeeName']!=fg.get('name').value.employeeName)
        //       }  
        //       );
        //   }
        // });
      })
    }
  }
  
  
  displayFnForNominee(selectedOption: employeeName) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['employeeName'] : undefined;
  }
  // displayFnForNominee(selectedOption: any) {
  //   if (!!selectedOption && typeof selectedOption === 'string') {
  //     return selectedOption;
  //   }
  //   return selectedOption ? selectedOption['employeeName'] : undefined;
  // }

  checkEmployeeName(fg:FormGroup){
    if (typeof fg.get('name').value != 'object') {
      fg.get('designation').markAsTouched()
      fg.get('name').setErrors({
        empError:'Please select from list'
      })
    }
  }

  getNomineeEmpDetails(event: MatAutocompleteSelectedEvent,fg:FormGroup) {
    this.nominationService.getNomineeEmpDetails(this.programId,event.option.value.id).subscribe(response => {
        let res = response['result']
          fg.get('status').patchValue(res.status)
          fg.get('employeeCode').patchValue(res.employeeCode)
          fg.get('attended').patchValue(res.attended == 'Y' ? true:false)
    })
  }
}

