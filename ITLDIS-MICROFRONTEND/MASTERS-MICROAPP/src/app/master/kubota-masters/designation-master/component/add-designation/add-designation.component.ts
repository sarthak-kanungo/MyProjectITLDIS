import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SearchDesignationService } from '../search-designation/search-designation.service';
import { BaseDto } from 'BaseDto';
import { AddDesignationService } from './add-designation.service';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { DepartmentName, SaveDesignation } from '../search-designation/search-designation';

@Component({
  selector: 'app-add-designation',
  templateUrl: './add-designation.component.html',
  styleUrls: ['./add-designation.component.scss']
})
export class AddDesignationComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object;

  newdesignationForm: FormGroup;
  departmentNameList: BaseDto<Array<DepartmentName>>
  departmentId: number
  constructor(private fb: FormBuilder,
    private searchDesignationService: SearchDesignationService,
    private addDesignationService: AddDesignationService,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.createnewdesignationForm();
    this.getDepartmentNameDropDown();
  }

  createnewdesignationForm() {
    this.newdesignationForm = this.fb.group({
      designationcode: [null , Validators.required],
      //designationcode: ['',Validators.pattern('/^\S*$/')],
      designation: [null,Validators.required],
      departmentName: [null, Validators.required],
     // remarks: [''],
     
    })
  }

  getDepartmentNameDropDown() {
    this.searchDesignationService.getDepartmentName().subscribe(response => {
      console.log("response ", response);
      this.departmentNameList = response
    })
  }

  validateForm() {

    if (this.newdesignationForm.status==='INVALID' ||this.newdesignationForm.get('designationcode').value===null ||this.newdesignationForm.get('designation').value===null ||this.newdesignationForm.get('departmentName').value===null) {
      this.toastr.error('Please check Mandatory Fields')
      this.newdesignationForm.markAllAsTouched()
    }
    else{
      this.openConfirmDialog();
    }
  }
  
  departmentNameSelectionChanged(event) {
    console.log("event ", event);
    this.departmentId = event.value.id
    console.log("this.departmentId ", this.departmentId);
  }
  
  submitData() {
    let formData = {} as SaveDesignation
    formData = this.newdesignationForm.value
    formData.departmentId =  this.departmentId 
    console.log("formData ", formData);
    this.addDesignationService.submitDesignation(formData).subscribe(formData => {
      console.log(formData);
      if (formData) {
        this.toastr.success('Designation Master Submited Successfully', 'Success')
      }
      this.router.navigate(['../'], { relativeTo: this.activatedRoute })
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Designation Master?';
    if (this.isEdit) {
      message = 'Do you want to update Designation Master?';
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
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }

  clearForm() {
    this.newdesignationForm.reset();
  }

  checkDuplicateKaiDesignation(event:string){
    this.addDesignationService.checkKaiDesignation(event).subscribe(res=>{
      if (res===undefined || res===null) {}
      else if (res.toUpperCase()===event.toUpperCase()) {
          this.newdesignationForm.get('designation').setErrors({
            designationError:'designationError'
          })
        }
    })
  }
  checkDuplicateKaiDesignationCode(event){
    let value=event.toUpperCase()
    this.newdesignationForm.get('designationcode').setValue(event.toUpperCase().replace(" ", ""));
    this.addDesignationService.checkKaiDesignationCode(value).subscribe(res=>{
     if (res===undefined || res===null) {}
     else if (res.toUpperCase()===value.toUpperCase()) {
        this.newdesignationForm.get('designationcode').setErrors({
          designationCodeErroe:'designationCodeErroe'
        })
      }
    })
  }

}
