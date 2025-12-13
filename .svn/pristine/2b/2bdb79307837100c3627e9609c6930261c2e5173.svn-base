import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { VehicleRegistrationDetailsService } from './vehicle-registration-details.service';
import { BaseDto } from 'BaseDto';
import { AutoCompleteChassisNo, AutoPopulateChassisNo } from 'VehicleRegistration';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-vehicle-registration-details-update',
  templateUrl: './vehicle-registration-details-update.component.html',
  styleUrls: ['./vehicle-registration-details-update.component.scss'],
  providers:[VehicleRegistrationDetailsService]
})
export class VehicleRegistrationDetailsUpdateComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  data: Object;
  vehicleRegistrationDetailsForm: FormGroup;
  autoCompleteChassisNo:BaseDto<Array<AutoCompleteChassisNo>>
  autoPopulateChassisNumber:BaseDto<AutoPopulateChassisNo>

  constructor( private fb: FormBuilder,public dialog: MatDialog,
    private vehicleRegistrationDetailsService:VehicleRegistrationDetailsService, private toastr: ToastrService) { }

  ngOnInit() {
    this.createvehicleRegistrationDetailsForm();
  }

  createvehicleRegistrationDetailsForm() {
    this.vehicleRegistrationDetailsForm = this.fb.group({
      
      chassisNo: ['', Validators.compose([])],
      vehicleRegistrationNo: ['', Validators.compose([])],
     
    })
    this.vehicleRegistrationDetailsForm.controls.chassisNo.valueChanges.subscribe(
      value =>{
        this.autoChassisNumber(value)
      }
    )
  }
  autoChassisNumber(value){
    this.vehicleRegistrationDetailsService.getChassisNo(value).subscribe(
      res=>{
        console.log('vehicleRegistrationDetailsService',res);
        this.autoCompleteChassisNo=res as BaseDto<Array<AutoCompleteChassisNo>>
      }
    )
  }
  optionSelectedChassisNo(event){
    console.log(event.option.value)
    this.vehicleRegistrationDetailsService.getChassisAutopopulate(event.option.value).subscribe(
      response=>{
        console.log('===>>',response);
        this.autoPopulateChassisNumber=response as BaseDto<AutoPopulateChassisNo>
      }
    )
  }

  displayFn(chassis:AutoCompleteChassisNo){
    console.log("=====",chassis);
    return chassis ? chassis.chassisNo:undefined;
    
  }

  validateForm() {
   
    this.openConfirmDialog();
  }
  
  submitData() {
    this.vehicleRegistrationDetailsService.submitVehicleRegistration.vehicleRegistrationNo=this.vehicleRegistrationDetailsForm.controls.vehicleRegistrationNo.value
    this.vehicleRegistrationDetailsService.submitVehicleRegistration.customerMaster={
      id:this.vehicleRegistrationDetailsForm.controls.chassisNo.value.id
    }
    console.log("submitData",this.vehicleRegistrationDetailsService.submitVehicleRegistration);
    this.vehicleRegistrationDetailsService.postSubmitData(this.vehicleRegistrationDetailsService.submitVehicleRegistration).subscribe(res=>{
      console.log("+++++++",res);
      if(res){
        this.toastr.success('Activity Saved Successfully', 'Success')
      }
    })
  }
  
  private openConfirmDialog(): void | any {
  let message = 'Do you want to submit Vehicle Registration No.?';
  if (this.isEdit) {
    message = 'Do you want to update Vehicle Registration No.?';
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

  clearData(){
    this.vehicleRegistrationDetailsForm.reset(this.vehicleRegistrationDetailsForm.controls.value);
  }
  

}
