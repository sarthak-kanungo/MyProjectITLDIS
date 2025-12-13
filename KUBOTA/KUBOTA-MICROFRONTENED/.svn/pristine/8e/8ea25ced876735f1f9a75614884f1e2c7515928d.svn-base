import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { KubotaUserCreatePageStore } from './kubota-user-create-page.store';
import { KubotaUserMasterCreatePagePresenter } from './kubota-user-create-page.presenter';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { EmployeeIdAutoCreate, KubotaUserMasterDomain, LoginUser, Mapping, SubmitDto } from '../../domain/kubota-user-domain';
import { KubotaUserCreatePageService } from './kubota-user-create-page.service';
import { BaseDto } from 'BaseDto';
import { CreateNewUserService } from '../create-new-user/create-new-user.service';

@Component({
  selector: 'app-kubota-user-create-page',
  templateUrl: './kubota-user-create-page.component.html',
  styleUrls: ['./kubota-user-create-page.component.scss'],
  providers:[KubotaUserMasterCreatePagePresenter , KubotaUserCreatePageStore, KubotaUserCreatePageService, CreateNewUserService]
})
export class KubotaUserCreatePageComponent implements OnInit {
  isEdit: boolean = false;
  isView: boolean = false;
  isCreate: boolean = false;
  createUserMasterMainForm:FormGroup;
  createNewUserForm: FormGroup;
  functionTableForm:FormGroup
  getFunctionData: boolean 
  viewId: any;

  constructor(
    private kubotaUserMasterCreatePagePresenter:KubotaUserMasterCreatePagePresenter,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService,
    private kubotaUserCreatePageService: KubotaUserCreatePageService,
    private router: Router,
    private createNewUserService: CreateNewUserService
  ) { }

  ngOnInit() {
    this.kubotaUserMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.createUserMasterMainForm = this.kubotaUserMasterCreatePagePresenter.createUserMasterMainForm
    this.createNewUserForm = this.kubotaUserMasterCreatePagePresenter.createNewUserForm
    this.functionTableForm = this.kubotaUserMasterCreatePagePresenter.functionTableForm
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.kubotaUserMasterCreatePagePresenter.operation === Operation.VIEW) {      
      this.isView=true
      this.activityRoute.params.subscribe(prms =>{
        this.viewUserDeatails(prms);
      })
      this.createUserMasterMainForm.disable()
      this.functionTableForm.disable()

    } else if (this.kubotaUserMasterCreatePagePresenter.operation === Operation.EDIT) {
      console.log("edit");
      this.isEdit=true
      this.activityRoute.params.subscribe(prms =>{
        this.viewUserDeatails(prms);
      })
    }
    else if (this.kubotaUserMasterCreatePagePresenter.operation === Operation.CREATE) {
      console.log("create");
      this.isCreate = true;
    }
  }
  
  viewUserDeatails(prms) {  
    this.kubotaUserCreatePageService.viewKubotaUserDetails(prms.id).subscribe(res => {
      console.log("viewUser---->",res);
      this.createNewUserForm.patchValue(
        {employeeId: res.result['userName'],
          employeeStatus: "ACTIVE",
          loginIdStatus:res.result['loginIdStatus']}
      );
    this.createNewUserForm.get('password').patchValue(res['result'].password)
    this.createNewUserForm.get('confirmPassword').patchValue(res['result'].password)
    this.viewId = res.result['id'];
    this.getFunctionData = true;
    })

    this.createNewUserService.getEmployeeCodeDropDown(prms.id,'N').subscribe(res => {
      console.log("UserName---->",res);
      this.createNewUserForm.controls.employeeId.disable();
      let empId:EmployeeIdAutoCreate = {
        employeecode: res.result[0]['employeecode'],
        employee_name: res.result[0]['employee_name'],
        displayValue: res.result[0]['displayValue']
      };
      this.createNewUserForm.controls.employeeName.patchValue(res.result[0]['employee_name']);  
      this.createNewUserForm.get('employeeId').setValue(empId);           
    })
  }

  validateForm() {
    if (this.createUserMasterMainForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.kubotaUserMasterCreatePagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  resetForm() {
    this.createUserMasterMainForm.reset();   
  }

  exitForm(){
    if (this.isCreate) {
      this.router.navigate(['../'], { relativeTo: this.activityRoute })
    } else{
      this.router.navigate(['../../'], { relativeTo: this.activityRoute })
    }
  }

  submitData() {
    let submitData = {} as SubmitDto
    let userObj = this.createUserMasterMainForm.getRawValue();
    console.log("userObj---->", userObj);
    let user = {} as LoginUser
    user.userName = userObj.createNewUserForm.employeeId.employeecode;
    user.password = userObj.createNewUserForm.password;
    user.loginIdStatus = userObj.createNewUserForm.loginIdStatus;
    user.kubotaEmployeeId = userObj.createNewUserForm.employeeId.id;
    
    let assignFunctions = [] as Mapping[];
    userObj.functionTableForm.tableData.forEach(functn => {  
      
    //   if (!functn.assignFunction) {
    //     let functions = {} as Mapping
    //     functions.loginUserId = this.viewId
    //     functions.roleId = functn.roleid
    //     assignFunctions.push(functions);
    //   }
    // });
         
      let functions = {} as Mapping
      functions.loginUserId = this.viewId
      functions.roleId = functn.roleid
      if (functn.assignFunction===true) {
       functions.isactive='Y'
      }
      if (functn.assignFunction===false) {
        functions.isactive='N'
       }
      assignFunctions.push(functions);
  });

    submitData.loginUser = user;
    submitData.mapping = assignFunctions;    
    
    if (this.isCreate) {
      this.kubotaUserCreatePageService.postSubmitDto(submitData).subscribe((result:BaseDto<object>) => {
        debugger
        if (result.status == "200" ) {
          this.router.navigate(['../'], { relativeTo: this.activityRoute })
        } else {
          this.toastr.error("Error")
        }
      })
    } else {
      user.id = this.viewId;
      submitData.loginUser = user;
      console.log("submitData--->", submitData)
      this.kubotaUserCreatePageService.updateUser(submitData).subscribe((result:BaseDto<object>) => {
        
        if (result.status == "200") {
          this.router.navigate(['../../'], { relativeTo: this.activityRoute })
        } else {
          this.toastr.error("Error")
        }
      })
    }

  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to Submit Kubota Empolyee?';
    if (this.isEdit) {
      message = 'Do you want to Update Kubota Empolyee Master?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
        this.toastr.success(`Form submitted successfully`, 'Success')
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitData();
        this.toastr.success(`Form updated successfully`, 'Success')
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }

  
  functionDetail(data){      
    this.getFunctionData = data;
  }

}
