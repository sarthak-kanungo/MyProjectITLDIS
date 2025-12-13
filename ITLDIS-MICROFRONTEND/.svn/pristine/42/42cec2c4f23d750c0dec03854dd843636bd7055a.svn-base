import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CreateNewDealerUserService } from './create-new-dealer-user.service';
import { DealerDetailsForNewUser, DealerEmployeeCodeForNewUser, DealerFunction, SubmitNewDealerDto } from '../../domain/create-dealer-user-domain';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FunctionComponent } from '../function/function.component';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';

@Component({
  selector: 'app-create-new-dealer-user',
  templateUrl: './create-new-dealer-user.component.html',
  styleUrls: ['./create-new-dealer-user.component.scss'],
  providers:[CreateNewDealerUserService,FunctionComponent]
})
export class CreateNewDealerUserComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  data: Object;
  dealerId:any
  userDetails:any
  newDealerList: DealerDetailsForNewUser[]=[]
  employeeCodeForDealerList: DealerEmployeeCodeForNewUser[]=[]
  // labelPosition = 'before';
  disable = true;
  assignRoleval:any
  functionVal:any
  isShowPcrButton: boolean
  dealers: string[] = [
    'D1', 'D2', 'D3',
  ];

  roles: string[] = [
    'R1', 'R2', 'R3',
  ];
  createNewUserForm: FormGroup;
  dealerFunctionList: DealerFunction[]=[]
  private _operation: string
  employeeCode:number
  roleArray:any[]



  constructor(private fb: FormBuilder,
    public dialog: MatDialog,
    private createNewDealerUserService:CreateNewDealerUserService,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    ) { }

  ngOnInit() {
    this.operation = OperationsUtil.operation(this.activityRoute)
    this.createcreateNewUserForm();
    this.viewOrEditOrCreate()
    this.checkRouterParamsForDealer()
    
  }

  set operation(type: string) {
      this._operation = type
  }
  get operation() {
      return this._operation
  }

  private viewOrEditOrCreate() {
    if (this.operation === Operation.VIEW) {
      console.log("view");
      this.isView=true
      this.createNewUserForm.disable()
      console.log(" this.employeeMaster ", this.createNewUserForm);

    } else if (this.operation === Operation.EDIT) {
      this.isEdit= true
    }
    else if (this.operation === Operation.CREATE) {
      this.isCreate=true
      this.createNewUserForm.get('isactive').disable()
    }
  }

  checkRouterParamsForDealer() {
    this.activityRoute.queryParamMap.subscribe(param => {
      if (param.has('id')) {
				if (this.isEdit== true) {
          this.employeeCode=parseInt(param.get('id'))
					this.viewdealerDetails(param.get('id'))
				}
				if (this.isView) {
					this.viewdealerDetails(param.get('id'))
					if (param.get('hasButton') == 'false') {
					}
				}
			}
      
    })
  }
  
  
  viewdealerDetails(id:any){
    this.createNewDealerUserService.viewdealerDetails(id).subscribe(res=>{
      this.patchDataFromView(res[0]);
      this.autoDealerFunction(id)
      })
  }
  patchDataFromView(viewData: any) {
    console.log('viewData--', viewData)
    let empCode:DealerEmployeeCodeForNewUser = {
      id: viewData,
      employeeName: viewData,
      usercode:viewData.employeeCode,
      value:viewData
    }
    this.createNewUserForm.get('employeeCode').patchValue(empCode)
    this.createNewUserForm.patchValue({
      dealer: viewData.dealer_code,
      employeeName: viewData.employeeName,
      userId:viewData.loginIdStatus,
      password: viewData.password,
      confirmPassword:viewData.password,
      isactive:(viewData.isactive==='Y'?true:false)
    })
    // if (viewData.login_id_status==='ACTIVE') {
    //   this.createNewUserForm.get('isactive').patchValue(true)
    // }
    // else{
    //   this.createNewUserForm.get('isactive').patchValue(false)
    // }
    
  }

  createcreateNewUserForm() {
    this.createNewUserForm = this.fb.group({

      dealer: ['', Validators.compose([])],
      employeeCode: [{ value: '', disabled: true }, Validators.compose([])],
      employeeName: [{ value: '', disabled: true }, Validators.compose([])],
      userId: ['', Validators.compose([])],
      password: ['', Validators.compose([])],
      confirmPassword: ['', Validators.compose([])],
      //assignRole: [{ value: '', disabled: true }, Validators.compose([])],
      isactive : [{ value: false}],
    },
      {
        validator: this.confirmPasswordValidator("password", "confirmPassword")
      }
    )
  }

  confirmPasswordValidator(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      let control = formGroup.controls[controlName];
      let matchingControl = formGroup.controls[matchingControlName]
      if (matchingControl.errors && !matchingControl.errors.confirmPasswordValidator) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ confirmPasswordValidator: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  autoCreateDealer(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.createNewDealerUserService.dealerAuto (value).subscribe(res=>{
        this.newDealerList=res
      })
    }
  }

  displayFnDealer(dealer: DealerDetailsForNewUser) {
    return dealer ? dealer.displayString : undefined;
  }

  newDealerSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.createNewUserForm.get('dealer').setErrors(null);
    }
    this.dealerId= this.createNewUserForm.get('dealer').value.id
    this.createNewUserForm.get('employeeCode').enable()
  }

  autoEmployeeCodeForNewDealer(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.createNewDealerUserService.autoEmployeeCode(value,this.dealerId).subscribe(res=>{
        this.employeeCodeForDealerList=res
      })
    }
  }

  displayFnEmployeeCode(employeeCode: DealerEmployeeCodeForNewUser) {
    return employeeCode ? employeeCode.usercode : undefined;
  }

  employeeCodeSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.createNewUserForm.get('employeeCode').setErrors(null);
    }
    // if (this.dealerId!=null || this.dealerId!=null) {
    //   this.createNewUserForm.get('isactive').patchValue(true)
    // }
    this.userDetails= this.createNewUserForm.get('employeeCode').value
    this.createNewUserForm.get('userId').patchValue(this.userDetails.usercode)
    this.autoDealerFunction(0);
    this.createNewUserForm.patchValue({
      employeeName:this.userDetails.employeeName,
      //userId:this.userDetails.id
    })
  }

  autoDealerFunction(dealerId:number){
    let value=''
    let applicableFor
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.createNewDealerUserService.dealerFunction(dealerId,'Y',).subscribe(res=>{
        this.dealerFunctionList=res
      })
    }
  }

  checkDuplicateUser(){
    let userName=this.createNewUserForm.get('userId').value
    console.log('userName--',userName);
    this.createNewDealerUserService.tocheckDuplicateUser(userName).subscribe(res=>{
      console.log('duplicate_user--',res['']);
      let resValue=res
      if (resValue===userName) {
        this.createNewUserForm.get('userId').setErrors({
          select:'User Name Is already Exist'
        })
      }
      
    })
    
  }

  functionForm(event) {
    let role= event.value
    role.forEach(val=> {
      if (val.assignRole===true) {
        val.assignRole='Y'
      }
      else if (val.assignRole===false){
        val.assignRole='N'
      }
    });
    this.roleArray=role
  }


  validateForm() {
    if (this.createNewUserForm.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.createNewUserForm.markAllAsTouched();
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }
  resetForm(){
    this.createNewUserForm.reset();
  }

  private buildJsonForSaveNewDealer() {
    const saveDealerEmployee={
      ...this.buildJsonForCreateNewUser(),
      ...this.buildJsonForFunctionForm()
    }
    return saveDealerEmployee;
  }


  buildJsonForCreateNewUser() {
    let createNewUser = this.createNewUserForm.getRawValue()
    let SubmitNewDealer = {} as SubmitNewDealerDto
    let daveData = {} as any
    if (this.isEdit== true) {
      SubmitNewDealer.id=this.employeeCode
    }
    //SubmitNewDealer.userName= createNewUser.dealer ? createNewUser.dealer.code : null
    if (this.isCreate== true) {
      SubmitNewDealer.dealerEmployeeId= createNewUser.employeeCode ? createNewUser.employeeCode.id : null
    }
    SubmitNewDealer.employeeName= createNewUser.employeeName ? createNewUser.employeeName : null
    SubmitNewDealer.userName= createNewUser.userId ? createNewUser.userId : null
    SubmitNewDealer.password= createNewUser.password ? createNewUser.password : null
    SubmitNewDealer.confirmPassword= createNewUser.confirmPassword ? createNewUser.confirmPassword : null
    SubmitNewDealer.assignRole= createNewUser.assignRole ? createNewUser.assignRole : null
    SubmitNewDealer.isactive = (createNewUser.isactive===true?'Y':'N')
    daveData.loginUser=SubmitNewDealer
    return daveData;
  }

  buildJsonForFunctionForm() {
    let userRole:Array<any> = [];
    let SubmitNewDealer = {} as any
    this.roleArray.forEach(list=>{
      userRole.push({
      isactive:list.assignRole,
      roleId:list.roleid
      })
    })
    SubmitNewDealer.userRoleManu=userRole
    return SubmitNewDealer;
  }

  submitData() {
    this.createNewDealerUserService.submitCreateNewUser(this.buildJsonForSaveNewDealer()).subscribe(res=>{
      if (res.status == "200") {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activityRoute })
      } else {
        this.toastr.error(res.message)
      }
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Dealer User?';
    if (this.isEdit) {
      message = 'Do you want to update Dealer User?';
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
      }
      if (result === 'Confirm' && this.isEdit) {
        this.updateDealerUser()
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
  
  updateDealerUser(){
      this.createNewDealerUserService.updateDealerUser(this.buildJsonForSaveNewDealer()).subscribe(res=>{
        if (res.status == "200") {
          this.toastr.success(res.message, 'Success');
          this.router.navigate(['../'], { relativeTo: this.activityRoute })
        } else {
          this.toastr.error(res.message)
        }
    })
  }


  exit() {
    this.router.navigate(['../'], { relativeTo: this.activityRoute })
  }

}
