import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { AutoEmployeeCode, AutoEmployeeName, AutoEmpMobile } from '../../domain/dealer-employee-domain';
import { SearchDealerEmployeeService } from './search-dealer-employee.service';
import { SearchDealerDepartmentService } from '../../../dealer-department-master/component/search-dealer-department/search-dealer-department.service';
import { CreateNewAssignUserToBranchService } from '../../../assign-user-to-branch/component/create-new-assign-user-to-branch/create-new-assign-user-to-branch.service';

@Component({
  selector: 'app-search-dealer-employee',
  templateUrl: './search-dealer-employee.component.html',
  styleUrls: ['./search-dealer-employee.component.scss'],
  providers: [SearchDealerEmployeeService,SearchDealerDepartmentService]
})
export class SearchDealerEmployeeComponent implements OnInit,OnChanges {

  @Input() employeeForm: FormGroup;
  employeeCodeList: AutoEmployeeCode[]=[]
  employeeNameList:AutoEmployeeName[]=[]
  departmentsCodeAndName:any;
  employeeMobileList: AutoEmpMobile[]=[]
  stateList:any
  dealerCodeList:any;
  stateNameList:any
  constructor( private fb: FormBuilder,
    private searchDealerEmployeeService:SearchDealerEmployeeService,
    private login:LoginFormService,
    private departMentService:SearchDealerDepartmentService,
    private assignUserToBranchService:CreateNewAssignUserToBranchService,
    // private trainingService:TrainingProgCalenderService
    ) { }

  ngOnChanges() {
    this.getEmployeeCode();
    this.getDepartmentName();
    this.getState();
  }

  ngOnInit() {
  }

  onkeyDealerCode(event:KeyboardEvent){
    this.onFocusDealerCode(event);
  }

onFocusDealerCode(value){
 
  if (value == null || value == undefined)
  
  value = '';

if(typeof value !== 'object'){
 
//  this.getEmployeeCode(value);
}
else{

this.dealerCodeList= null;
}
}
  onKeyEmployeeName(event:KeyboardEvent){
    this.onfocusEmployeeName(event);
  }

  onKeyState(event:KeyboardEvent){
    this.onfocusState(event);
  }
  onfocusState(value){
    if (value == null || value == undefined)
    value = '';

if(typeof value !== 'object'){
  //  this.getEmployeeCode(value);
}
else{
  this.stateList = null;
}
  }

  onfocusEmployeeName(value){
    if (value == null || value == undefined)
        value = '';
  
    if(typeof value !== 'object'){
      //  this.getEmployeeCode(value);
    }
    else{
      this.employeeNameList = null;
    }
  }
  
  onKeyEmployeeeCode(event: KeyboardEvent) {
    this.onFocusGetEmployeeCodeList(event);
  }

  onFocusGetEmployeeCodeList(value){
    if (value == null || value == undefined)
        value = '';
  
    if(typeof value !== 'object'){
      //  this.getEmployeeCode(value);
    }
    else{
      this.employeeCodeList = null;
    }
  }


  // valueChangesForAutoComplete() {
  //   this.employeeForm.get('employeeCode').valueChanges.subscribe(value => {
  //     this.onFocusGetEmployeeCodeList(value);
  //   })

  //   this.employeeForm.get('mobileNo').valueChanges.subscribe(value => {
  //     this.onFocusGetEmployeeCodeList(value);
  //   })
  // }

  getEmployeeCode(){
      this.employeeForm.get('employeeCode').valueChanges.subscribe(code => {
        if(code){
          this.searchDealerEmployeeService.getEmployeeCodeAuto(null,code).subscribe(res=>{
            if(res){
            this.employeeCodeList=res
            }
            })
        }
        
      })
      this.employeeForm.get('mobileNo').valueChanges.subscribe(mobile => {
        if(mobile){
        this.searchDealerEmployeeService.getEmployeeCodeAuto(mobile,null).subscribe(res=>{
          if(res){
          this.employeeMobileList=res
          }
          })

        }
      })
      this.employeeForm.get('employeeName').valueChanges.subscribe(name=>{
        if(name){
       this.searchDealerEmployeeService.getEmployeeNameAuto(name).subscribe(res=>{
        if(res){
          this.employeeNameList=res;
        }
       })
      }
      })
    
      this.employeeForm.get('dealerCode').valueChanges.subscribe(dealerCode=>{
       if(dealerCode){
        this.assignUserToBranchService.dealerToAssignBranch(dealerCode).subscribe(dealerNameCode=>{
          if(dealerNameCode){
            this.dealerCodeList=dealerNameCode;
          }
        })
       }
      })




    // let empMobileNo=null
    // this.searchDealerEmployeeService.getEmployeeCodeAuto(empMobileNo,employeeCode).subscribe(res=>{
    // this.employeeCodeList=res
    // })
  }

  displayFnEmployeeCode(employeeCode: AutoEmployeeCode) {
    return employeeCode ? employeeCode.employeeCode : undefined;
  }

  displayFnEmpMobile(employeeCode: AutoEmployeeCode) {
    return employeeCode ? employeeCode.employeeCode : undefined;
  }

  getDepartmentName(){
    this.departMentService.searchDepartmentCode('').subscribe(res=>{
      this.departmentsCodeAndName=res
    })
  }
  getState(){
    this.searchDealerEmployeeService.getStates('1','').subscribe(stateName=>{
      // console.log(stateName,'stateName')
       this.stateList=stateName.result;
    })
  }
  displayDealerCode(dealerCode:any){

    return dealerCode?dealerCode.displayString:undefined
  }
}
