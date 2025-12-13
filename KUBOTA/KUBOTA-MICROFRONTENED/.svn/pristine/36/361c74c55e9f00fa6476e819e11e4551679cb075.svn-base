import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SearchDealerEmployeeService } from '../../../dealer-employee-master/component/search-dealer-employee/search-dealer-employee.service';
import { AutoEmployeeCode } from '../../../dealer-employee-master/domain/dealer-employee-domain';
import { EmployeeName } from '../../domain/create-dealer-user-domain';

@Component({
  selector: 'app-dealer-user-search-result',
  templateUrl: './dealer-user-search-result.component.html',
  styleUrls: ['./dealer-user-search-result.component.scss'],
  providers:[SearchDealerEmployeeService]
})
export class DealerUserSearchResultComponent implements OnInit {

  @Input() dealerForm: FormGroup;
  employeeCodeList: AutoEmployeeCode[]=[]
  employeeNameList: AutoEmployeeCode[]=[]

  constructor(
    private  searchDealerEmployeeService:SearchDealerEmployeeService
  ) { }

  ngOnInit() {
  }
  
  onKeyEmployeeeCode(event: KeyboardEvent) {
    
    this.onFocusGetEmployeeCodeList(event);
  }

  onFocusGetEmployeeCodeList(value){
    if (value == null || value == undefined)
        value = '';
  
    if(typeof value !== 'object'){
        this.getEmployeeCode(value);
    }
    else{
      this.employeeCodeList = null;
    }
  }


  valueChangesForAutoComplete() {
    this.dealerForm.get('employeeCode').valueChanges.subscribe(value => {
      this.onFocusGetEmployeeCodeList(value);
    })
  }

  getEmployeeCode(employeeCode: string){
    let empMobileNo=null
    this.searchDealerEmployeeService.getEmployeeCodeAuto(empMobileNo,employeeCode).subscribe(res=>{
    this.employeeCodeList=res
    })
  }

  displayFnEmployeeCode(employeeCode: AutoEmployeeCode) {
    return employeeCode ? employeeCode.employeeCode : undefined;
  }

  getEmployeename(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.searchDealerEmployeeService.getEmployeeNameAuto(value).subscribe(res=>{
        this.employeeNameList=res
      })
    }
  }

  displayFnEmpName(employeeName: EmployeeName) {
    return employeeName ? employeeName.employeeName : undefined;
  }


}
