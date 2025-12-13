import { Component, OnInit } from '@angular/core';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { DropDownDepartmentNames, SubmitDepartment } from 'SearchDepartment';
import { SearchDepartmentService } from '../search-department/search-department.service';
import { AddDepartmenetService } from './add-departmenet.service';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.scss']
})
export class AddDepartmentComponent implements OnInit {

  isEdit: boolean;
  data: Object;

  newdepartmentForm: FormGroup;

  departmentNamesList: BaseDto<Array<DropDownDepartmentNames>>
  // dealersList: BaseDto<Array<DropDownDealers>>
  // codeList: BaseDto<Array<DropDownCode>>

  constructor(private fb: FormBuilder, public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private searchDepartmentService: SearchDepartmentService,
    private addDepartmenetService: AddDepartmenetService) { }

  ngOnInit() {
    this.createnewdepartmentForm();
    // this.loadAllDropDownData().subscribe(dt => {
    //   this.dealersList = dt[0] as BaseDto<Array<DropDownDealers>>
    //   this.codeList = dt[1] as BaseDto<Array<DropDownCode>>

    //   // this.patchOrCreate()
    // })
  }

  createnewdepartmentForm() {
    this.newdepartmentForm = this.fb.group({

      departmentCode: [null,Validators.required],
      departmentName: [null,Validators.required],
      // linkedtoDealer: [''],
      // dealerCode: [''],
      // remarks: [''],
    })
  }

  // private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
  //   let dropDownTask = [];
  //   dropDownTask.push(this.searchDepartmentService.getDealersList())
  //   dropDownTask.push(this.searchDepartmentService.getCodeList())

  //   return forkJoin(...dropDownTask)
  // }

  checkDuplicateKaiDepartmentCode(event){
    let value=event.toUpperCase()
    this.newdepartmentForm.get('departmentCode').setValue(event.toUpperCase());
    this.addDepartmenetService.checkKaiDepartmentCode(value).subscribe(res=>{
      if (res===undefined || res===null) {}
      else if (res.toUpperCase()===value.toUpperCase()) {
          this.newdepartmentForm.get('departmentCode').setErrors({
            departmentCodeErroe:'departmentCodeErroe'
          })
        }
    })
  }

  checkDuplicateKaiDepartmentName(event:string){
    this.addDepartmenetService.checkKaiDepartmentName(event).subscribe(res=>{
      if (res===undefined || res===null) {}
      else if (res.toUpperCase()===event.toUpperCase()) {
          this.newdepartmentForm.get('departmentName').setErrors({
            departmentNameErroe:'departmentNameErroe'
          })
        }
    })
  }

  validateForm() {
    if (this.newdepartmentForm.status==='INVALID' ||this.newdepartmentForm.get('departmentCode').value===null || this.newdepartmentForm.get('departmentName').value===null) {
      this.toastr.error('Please check Mandatory Fields')
      this.newdepartmentForm.markAllAsTouched()
    }
    else{
      this.openConfirmDialog();
    }
  }

  submitData() {
    let formData = {} as SubmitDepartment

    formData.departmentCode = this.newdepartmentForm.controls.departmentCode.value;
    formData.departmentName = this.newdepartmentForm.controls.departmentName.value;
    // formData.remarks = this.newdepartmentForm.controls.remarks.value;
    // if (this.newdepartmentForm.controls.linkedtoDealer.value == 'Yes') {
    //   formData.linkedToDealer = 'Y'
    // } else if (this.newdepartmentForm.controls.linkedtoDealer.value == 'No') {
    //   formData.linkedToDealer = 'N'
    // }
    // if (this.newdepartmentForm.controls.dealerCode.value == 'Yes') {
    //   formData.dealerCode = 'Y'
    // } else if (this.newdepartmentForm.controls.dealerCode.value == 'No') {
    //   formData.dealerCode = 'N'
    // }
    console.log("formData ", formData);
    this.addDepartmenetService.submitDepartmenet(formData).subscribe(formData => {
      let response = formData as BaseDto<SubmitDepartment>
      console.log(response)
      if (response.status == "200") {
        this.toastr.success('Department Master Submited Successfully', 'Success')
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.toastr.error(response.message, 'Failed')
      }
      
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Department Master?';
    if (this.isEdit) {
      message = 'Do you want to update Department Master?';
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
        this.submitData();
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
    this.newdepartmentForm.reset();
  }

}
