import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { AutoEmployeeCode, AutoEmployeeName, DepartmentCodeAndName, DepartmentCodeAuto, submitData } from '../model/employee';
import { EmployeServiceService } from '../service/employe-service.service';
import { bankDetailPresenter } from './bank-detail-presenter';
import { bankDetailStore } from './bank-detail.store';
import { debounceTime, distinctUntilChanged, filter, switchMap, tap } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-employee-bank-detail-create',
  templateUrl: './employee-bank-detail-create.component.html',
  styleUrls: ['./employee-bank-detail-create.component.css'],
  providers:[bankDetailStore,bankDetailPresenter]
})
export class EmployeeBankDetailCreateComponent implements OnInit {

  departmentsCode: DepartmentCodeAuto;
  departmentsCodeAndName: DepartmentCodeAndName;
  employeeMobileList=[]
  employeeCodeList=[]
  createBankDetailForm: FormGroup
  bank: any;
  bankData: any;
  mobileData: any;
  status:any;
  private searchSubject = new BehaviorSubject<string>('');
  searchwithMobileNo:boolean=false;
  constructor(private service:EmployeServiceService,private store:bankDetailStore,private presenter:bankDetailPresenter,public dialog: MatDialog,private toastr: ToastrService,private router:Router) { }

  ngOnInit() {
    this.createBankDetailForm = this.presenter.getcreateBankDetailForm;
     this.getEmployeeCode()
  }

  
  onKeyEmployeeeCode(event: KeyboardEvent) {
    this.onFocusGetEmployeeCodeList(event);
  }
  // onKeyEmployeeNumber(event: KeyboardEvent) {
  //   this.onFocusGetEmployeeNumberList(event);
  // }

  // onFocusGetEmployeeNumberList(value){
  //   if (value == null || value == undefined)
  //       value = '';
  
  //   if(typeof value !== 'object'){
  //   }
  //   else{
  //     this.employeeMobileList = null;
  //   }
  // }

  onFocusGetEmployeeCodeList(value){
    if (value == null || value == undefined)
        value = '';
  
    if(typeof value !== 'object'){
    }
    else{
      this.employeeCodeList = null;
    }
  }


  getempBankDetails(event:any){
    // let enquiryData = event.option.value
   
    // this.BankDetails(event.option.value)
    // this.bankData = null
    // this.mobileData = null
  }
  // BankDetails(event:any){
  //   this.  createBankDetailForm
  //   .get('employeeCode').valueChanges.subscribe(code => {
  //     if(code){
      
  //     this.service.getCodeMobile(null,code).subscribe(res=>{
  //       this.bankData=res
  //       this.createBankDetailForm.patchValue({
  //         firstName: res.firstName,
  //         middleName:  res.middleName,
  //         lastName:res.lastName,
  //         Desigation:res.designation,
  //         oldbankAccountNo:res.bankAccountNo,
  //         oldbankName:res.bankName,
  //         oldbankBranch:res.bankBranch,
  //         oldifsCode:res.ifsCode,
  //       });
  //       })
  //     }
  //   })
  //   this.createBankDetailForm.get('mobileNo').valueChanges.subscribe(mobile => {
  //     if(mobile){
      
  //     this.service.getCodeMobile(mobile,null).subscribe(res=>{
  //       console.log(res,'res')
  //       this.mobileData=res
  //       // this.createBankDetailForm.patchValue({
  //       //   firstName: res.firstName,
  //       //   middleName:  res.middleName,
  //       //   lastName:res.lastName,
  //       //   Desigation:res.designation,
  //       //   oldbankAccountNo:res.bankAccountNo,
  //       //   oldbankName:res.bankName,
  //       //   oldbankBranch:res.bankBranch,
  //       //   oldifsCode:res.ifsCode,
  //       // })
  //       })
  //     }
  //   })
  // }

  getNumberList(event:any){
     this.createBankDetailForm.patchValue({
          firstName: this.mobileData.firstName,
          middleName:  this.mobileData.middleName,
          lastName:this.mobileData.lastName,
          Desigation:this.mobileData.designation,
          oldbankAccountNo:this.mobileData.bankAccountNo,
          oldbankName:this.mobileData.bankName,
          oldbankBranch:this.mobileData.bankBranch,
          oldifsCode:this.mobileData.ifsCode,
        })
  }
 
  getEmployeeCode(){
    //  this.BankDetails(event)
      this.  createBankDetailForm
      .get('employeeCode').valueChanges.subscribe(code => {
        if(code){
        
        this.service.getEmployeeCodeAuto(null,code).subscribe(res=>{
          this.employeeCodeList=res
          })
        }
      })
      // this.createBankDetailForm.get('mobileNo').valueChanges.subscribe(mobile => {
      //   if(mobile){
        
      //   this.service.getEmployeeCodeAuto(mobile,null).subscribe(res=>{
      //     this.employeeMobileList=res
      //     })
      //   }
      // })

  }
  clearForm(){
   this.createBankDetailForm.reset();
  }
  exitForm() {
    this.router.navigate(['../dealermasters/employeebankdetail/'])
  }
submitForm(){
  if (this.createBankDetailForm.invalid) {
    this.toastr.error("Please fill all the mandatory fields")
    this.createBankDetailForm.markAllAsTouched()
  }
  else{
    this.openConfirmDialog();
  }
 
    
}
private openConfirmDialog(): void | any {
  let message = 'Do you want to submit Bank Details';
  
  const confirmationData = this.setConfirmationModalData(message);
  const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
    width: '500px',
    panelClass: 'confirmation_modal',
    data: confirmationData
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);
    if (result === 'Confirm') {
      this.submitData();
    }else{
      this.toastr.error(`Error while Creating submiting`, 'Error')
    }
   
  });
}
buildJsonForEmployee() {
  const employeeDetails = this.presenter.getcreateBankDetailForm.getRawValue()
  let submitData = {} as submitData  
  submitData.id= employeeDetails.employeeCode ? employeeDetails.id : null
  submitData.employeeCode= employeeDetails.employeeCode ? employeeDetails.employeeCode : null
  submitData.mobileNo= employeeDetails.mobileNo ? employeeDetails.mobileNo : null
  submitData.firstName= employeeDetails.firstName ? employeeDetails.firstName : null
  submitData.middleName= employeeDetails.middleName ? employeeDetails.middleName : null
  submitData.lastName= employeeDetails.lastName ? employeeDetails.lastName : null
  submitData.bankAccountNo= employeeDetails.bankAccountNo ? employeeDetails.bankAccountNo : null
  submitData.bankName= employeeDetails.bankName ? employeeDetails.bankName : null
    submitData.bankBranch= employeeDetails.bankBranch ? employeeDetails.bankBranch : null
  submitData.ifsCode= employeeDetails.ifsCode ? employeeDetails.ifsCode : null
  return submitData
}
submitData() { 
  
  this.service.updateEmployeeBankDetails(this.buildJsonForEmployee()).subscribe(res => {
    if(res.status=='200'){
      this.createBankDetailForm.reset()
      this.toastr.success(res['message'], 'Success');
      this.router.navigate(['../dealermasters/employeebankdetail/'])
      // this.router.navigate(['..'], { relativeTo: this.activatedRoute });
    }else{
      this.toastr.error(res['message'], 'Error');
    }

  }, 
  );

}
private setConfirmationModalData(message: string) {
  const confirmationData = {} as ConfirmDialogData;
  confirmationData.buttonAction = [] as Array<ButtonAction>;
  confirmationData.title = 'Confirmation';
  confirmationData.message = message;
  confirmationData.buttonName = ['Confirm', 'Cancel'];
  return confirmationData;
}

  displayFnEmployeeCode(employeeCode: AutoEmployeeCode) {
    return employeeCode ? employeeCode.employeeCode : undefined;
  }

  displayFnEmployeeName(employeeName:AutoEmployeeName){
    return employeeName?employeeName.employeeName:undefined
  }

  checkMobileNo(event:any){
    // console.log(event,'event')
    if(event.checked===true){
      this.searchwithMobileNo=true;
      console.log(this.createBankDetailForm)
      this.createBankDetailForm.get('mobileNo').setValidators([Validators.required]);
      this.createBankDetailForm.get('employeeCode').updateValueAndValidity();

    }else{
      this.createBankDetailForm.get('mobileNo').updateValueAndValidity();
      this.createBankDetailForm.get('employeeCode').setValidators([Validators.required]);
      this.searchwithMobileNo=false;
    }
  }
// modified Code
// this.service.getEmployeeCodeAuto(mobile,null).subscribe(res=>{
//   this.employeeMobileList=res
//   })
  onSearchMobileNo(event:any){
   const mobileNo= this.createBankDetailForm.get('mobileNo');
   mobileNo.valueChanges.pipe(
    debounceTime(1000),
    // Use switchMap to switch to a new observable when the value changes
    switchMap(mobileNo => {
      // Your service call here using the value 'mobileNo'
      return this.service.getEmployeeCodeAuto(mobileNo, null);
    })
  ).subscribe(res => {
    // Handle the result here
    if (res) {
      this.employeeMobileList = res;
    }
  });
  }

}
