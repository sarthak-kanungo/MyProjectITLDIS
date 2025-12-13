import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchDesignationService } from '../../../designation-master/component/search-designation/search-designation.service';
import { AddDesignationLevelService } from './add-designation-level.service';
import { BaseDto } from 'BaseDto';
import { SaveDesignationLevel } from 'Designation Level';
import { DepartmentName } from '../../../designation-master/component/search-designation/search-designation';

@Component({
  selector: 'app-add-designation-level',
  templateUrl: './add-designation-level.component.html',
  styleUrls: ['./add-designation-level.component.css']
})
export class AddDesignationLevelComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object;
  departmentId: number
  newDesignationLevelForm: FormGroup;
  departmentNameList: BaseDto<Array<DepartmentName>>

  constructor(private fb: FormBuilder,
    private searchDesignationService: SearchDesignationService,
    private addDesignationLevelService: AddDesignationLevelService,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,) { }

  ngOnInit() {
    this.createnewdesignationLevelForm();
    this.getDepartmentNameDropDown();
  }

  createnewdesignationLevelForm() {
    this.newDesignationLevelForm = this.fb.group({
      designationlevelcode: [''],
      designationlevel: [''],
      departmentName: [''],
    })
  }

  getDepartmentNameDropDown() {
    this.searchDesignationService.getDepartmentName().subscribe(response => {
      console.log("response ", response);
      this.departmentNameList = response
    })
  }

  validateForm() {
    this.openConfirmDialog();
  }

  departmentNameSelectionChanged(event) {
    console.log("event ", event);
    this.departmentId = event.value.id
    console.log("this.departmentId ", this.departmentId);
  }

  submitData() {
    let formData = {} as SaveDesignationLevel
    formData = this.newDesignationLevelForm.value
    formData.departmentId =  this.departmentId 
    console.log("formData ", formData);
    this.addDesignationLevelService.submitDesignationLevel(formData).subscribe(formData => {
      console.log(formData);
      if (formData) {
        this.toastr.success('Designation Level Master Submited Successfully', 'Success')
      }
      this.router.navigate(['../'], { relativeTo: this.activatedRoute })
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Designation Level Master?';
    if (this.isEdit) {
      message = 'Do you want to update Designation Level Master?';
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
    this.newDesignationLevelForm.reset();
  }


}
