import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatAutocompleteSelectedEvent, MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, FormArray} from '@angular/forms';
import { CreateNewAssignUserToBranchPagePresenter } from './create-new-assign-user-to-branch-page.presenter';
import { CreateNewAssignUserToBranchPageStore } from './create-new-assign-user-to-branch-page.store';
import { AutoDealer, SaveAbtuObject, UserBranch, UserId, UserToBranch } from '../../domain/assign-user-to-branch-domain';
import { CreateNewAssignUserToBranchService } from './create-new-assign-user-to-branch.service';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-new-assign-user-to-branch',
  templateUrl: './create-new-assign-user-to-branch.component.html',
  styleUrls: ['./create-new-assign-user-to-branch.component.scss'],
  providers:[CreateNewAssignUserToBranchPagePresenter,CreateNewAssignUserToBranchPageStore]
})
export class CreateNewAssignUserToBranchComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  data: Object;
  disable = true
  newAssignUserToBranchForm: FormGroup;
  branchAccessible: FormArray
  dealershipList:AutoDealer
  userIdList:Array<UserId>
  userIdListView:Array<UserId>
  dealerId:any
  dealerIdForView:any
  dealerName:string
  userId:string
  isShowPcrButton: boolean
  isactiveFlag:any
  dealerEmployeeId:any
  userBranchListView:Array<UserBranch>
  id:number
  branchId:number
  constructor( private fb: FormBuilder,public dialog: MatDialog,
    private pagePresenter:CreateNewAssignUserToBranchPagePresenter,
    private assignUserToBranchService:CreateNewAssignUserToBranchService,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,) { }

  ngOnInit() {
    this.newAssignUserToBranchForm = this.pagePresenter.userToBranchForm
    this.branchAccessible = this.pagePresenter.branchAccessible
    this.pagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.viewOrEditOrCreate()
    this.checkRouterParamsForBrancgToUser()
  }

  
  private viewOrEditOrCreate() {
    if (this.pagePresenter.operation === Operation.VIEW) {
      this.isView=true
      this.newAssignUserToBranchForm.disable()

    } else if (this.pagePresenter.operation === Operation.EDIT) {
      this.isEdit = true
    }
    else if (this.pagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
    }
  }

  checkRouterParamsForBrancgToUser() {
    this.activityRoute.queryParamMap.subscribe(param => {
      console.log('param--',param);
      if (param.has('id')) {
				if (this.isEdit== true) {
          this.id= parseInt(param.get('id'))  
					this.viewdealerDetails(param.get('dealerEmployeeId'))
				}
				if (this.isView) {
					this.viewdealerDetails(param.get('dealerEmployeeId'))
				}
			}
      
    })
  }
  
  viewdealerDetails(id:any){
    this.assignUserToBranchService.getDealerIdAndName(id).subscribe(res=>{
      this.dealerIdForView=res
      let empId:UserId = {
        id: 1,
        employeeName: res[0].firstName,
        usercode: '',
        value:''
      };
     this.newAssignUserToBranchForm.get('userId').patchValue(empId)
      // this.assignUserToBranchService.userToAssignBranch('',res[0].dealerId).subscribe(res=>{
      //   this.userIdListView=res
      //   this.userIdListView.forEach(element => {
      //     if (element.id==id) {
      //       let empId:UserId = {
      //         id: element.id,
      //         employeeName: element.employeeName,
      //         usercode: element.usercode,
      //         value:element.value
      //       };
      //      this.newAssignUserToBranchForm.get('userId').patchValue(empId)
      //     }
      //   });
      // })  
        this.assignUserToBranchService.getUserBranch(res[0].dealerId).subscribe(res=>{
          this.userBranchListView=res
        })
    })
    
  }
  sendBranchId(id){
    this.branchId = id;
  }

  getAutodealership(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.assignUserToBranchService.dealerToAssignBranch(value).subscribe(res=>{
        this.dealershipList=res
      })
    }
  }

  displayFndealership(userIdDetails: AutoDealer) {
    return userIdDetails ? userIdDetails.displayString : undefined;
  }

  dealershipSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.newAssignUserToBranchForm.get('dealership').setErrors(null);
    }
    this.dealerId= this.newAssignUserToBranchForm.get('dealership').value.id
    this.dealerName=this.newAssignUserToBranchForm.get('dealership').value.cod
    this.newAssignUserToBranchForm.get('userId').enable();
  }





  getAutoUserId(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.assignUserToBranchService.userToAssignBranch(value,this.dealerId,'ASSIGNUSER').subscribe(res=>{
        this.userIdList=res
      })
    }
  }

  displayFnUserIdDetailes(userIdDetails: UserId) {
    return userIdDetails ? userIdDetails.employeeName : undefined;
  }

  userIdSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.newAssignUserToBranchForm.get('userId').setErrors(null);
    }
    this.userId= this.newAssignUserToBranchForm.get('userId').value.employeeName
    this.dealerEmployeeId=this.newAssignUserToBranchForm.get('userId').value.id
  }

  // private buildJsonForAssignBranchToUser() {
  //   const saveAssignedBranchToUser={
  //     ...this. buildJsonForAssignUserToBranchForm()
  //   }
  //   return saveAssignedBranchToUser
  // }

  
  buildJsonForAssignUserToBranchForm() {
    const branchAccessibleForm = this.branchAccessible.getRawValue()
    console.log('branchAccessibleForm--',branchAccessibleForm);
    const abtu = {} as SaveAbtuObject
    let userToBranch:Array<any> = [];
    let status:string
    if (branchAccessibleForm[0].Active_status===true) {
      status='Y'
    }else{
      status='N'
    }
    
    if (this.isEdit===true) {
      abtu.id=this.id
      userToBranch.push({
        dealerEmployeeId:this.dealerIdForView[0].employeeId,
        branchId:this.userBranchListView[0].id,
        isactive:status
      })
    }else{
      userToBranch.push({
        dealerEmployeeId:this.dealerEmployeeId,
        branchId:this.branchId,
        isactive:status
      })}
    abtu.userToBranch = userToBranch;

    return abtu
  }

  
  validateForm() {
   
    this.openConfirmDialog();
  }
  
  submitData() {
    this.assignUserToBranchService.submitBranchToUser(this.buildJsonForAssignUserToBranchForm()).subscribe(res=>{
      if (res.status == "200") {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activityRoute })
      } else {
        this.toastr.error(res.message)
      }
    })
  }
  updateUserBranchAssignment(){
    this.assignUserToBranchService.updateUserBranchAssignment(this.buildJsonForAssignUserToBranchForm()).subscribe(res=>{
      if (res.status == "200") {
        this.toastr.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activityRoute })
      } else {
        this.toastr.error(res.message)
      }
    })
  }
  
  private openConfirmDialog(): void | any {
  let message = 'Do you want to submit Assign User To Branch?';
  if (this.isEdit) {
    message = 'Do you want to update Assign User To Branch?';
  }
  const confirmationData = this.setConfirmationModalData(message);
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
      this.updateUserBranchAssignment();
    }
  });
  }
  private setConfirmationModalData(message: string) {
  const confirmationData = {} as ConfirmDialogData;
  confirmationData.buttonAction = [] as Array<ButtonAction>;
  confirmationData.title = 'Confirmation';
  confirmationData.message = message;
  confirmationData.buttonName = ['Confirm', 'Cancel'];
  return confirmationData;
  }
  clearForm() {
    this.newAssignUserToBranchForm.reset();
  }

  exit() {
    this.router.navigate(['../'], { relativeTo: this.activityRoute })
  }

}
