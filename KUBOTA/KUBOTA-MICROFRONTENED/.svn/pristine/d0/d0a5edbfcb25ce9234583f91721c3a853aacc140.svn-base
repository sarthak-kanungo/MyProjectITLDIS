import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder } from '@angular/forms';
import { KubotaUserMasterCreatePagePresenter } from '../kubota-user-create-page/kubota-user-create-page.presenter';
import { LoginIdStatusDropDown, EmployeeIdAutoCreate, KubotaUserMasterDomain } from '../../domain/kubota-user-domain';
import { CreateNewUserService } from './create-new-user.service';
import { BaseDto } from 'BaseDto';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-create-new-user',
  templateUrl: './create-new-user.component.html',
  styleUrls: ['./create-new-user.component.scss'],
  providers: [CreateNewUserService, KubotaUserMasterCreatePagePresenter]
})
export class CreateNewUserComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object; 
  disable = true;
  reportingEmployeeCodeList: BaseDto<Array<EmployeeIdAutoCreate>>;
  functionDetails: Array<KubotaUserMasterDomain> = [];
  @Output() getFunctionData = new EventEmitter();

  status: Array<LoginIdStatusDropDown> = [
    'ACTIVE', 'INACTIVE', 
  ];
   createNewUserForm: FormGroup;
  constructor(
    public dialog: MatDialog,
    private kubotaUserMasterCreatePagePresenter:KubotaUserMasterCreatePagePresenter,
    private createNewUserService: CreateNewUserService,
    private activityRoute: ActivatedRoute,  
    ) { }

  ngOnInit() {
    this.kubotaUserMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.createNewUserForm=this.kubotaUserMasterCreatePagePresenter.createNewUserForm;
        
    this.createNewUserForm.controls.employeeId.valueChanges.subscribe(val => {
      let forCreate:string
      if (this.kubotaUserMasterCreatePagePresenter.operation === Operation.CREATE) {
        forCreate='Y'
      }
      else{
        forCreate='N'
      }
      this.createNewUserService.getEmployeeCodeDropDown(val,forCreate).subscribe(res => {
        this.reportingEmployeeCodeList = res;      
      })
    })

    this.createNewUserForm.controls.confirmPassword.valueChanges.subscribe(val => {
      let pass = this.createNewUserForm.controls.password.value;
      if (pass != val) {
        this.createNewUserForm.controls.confirmPassword.setErrors({
          passwordMismatch : "Password Doesn't Match."
        })
      }
    })    
  }

  selectReportingEmployeeCode(event) {
    console.log("event----->", event);
    this.createNewUserForm.controls.employeeName.patchValue(event.option.value.employee_name);
    this.createNewUserForm.controls.employeeStatus.patchValue("ACTIVE");
    
    this.getFunctionData.emit(event ? true:false);
  }

  displayFnReportingEmployeeCode(employeeCode: EmployeeIdAutoCreate) {
    return employeeCode ? employeeCode.employeecode : undefined
  }
 
}
