import { Component, OnInit, Input } from '@angular/core';
import { EmployeeMasterCreatePageStore } from './EmployeeMasterCreatePageStore';
import { EmployeeMasterCreatePagePresenter } from './employee-master-page.presenter';
import { FormGroup, FormArray } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SubmitDto } from '../../domain/dealer-employee-domain';
import { EmployeeMasterCreatePageService } from './employee-master-create-page.service';
import { DateService } from 'src/app/root-service/date.service';


@Component({
  selector: 'app-employee-master-create-page',
  templateUrl: './employee-master-create-page.component.html',
  styleUrls: ['./employee-master-create-page.component.scss'],
  providers: [EmployeeMasterCreatePageStore, EmployeeMasterCreatePagePresenter,EmployeeMasterCreatePageService]
})
export class EmployeeMasterCreatePageComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;

  employeeMaster: FormGroup
  employeeMasterDetails: FormGroup
  employeeForm: FormGroup
  employmentForm: FormGroup
  employeeAddressForm: FormGroup
  employeeFamilyDetailsForm: FormGroup
  employeeWorkExperienceForm: FormGroup
  employeeId:number

  constructor(
    private employeeMasterCreatePagePresenter: EmployeeMasterCreatePagePresenter,
    public dialog: MatDialog,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService,
    private toasterService: ToastrService,
    private router: Router,
    private activateRoute: ActivatedRoute,
    private employeeMasterCreatePageService:EmployeeMasterCreatePageService,
    private dateService: DateService,
  ) { }

  ngOnInit() {
    this.employeeMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activityRoute)

    this.employeeMaster = this.employeeMasterCreatePagePresenter.employeemasterForm
    this.employeeMasterDetails = this.employeeMasterCreatePagePresenter.detailsForm
    this.employeeForm = this.employeeMasterCreatePagePresenter.employeeForm
    this.employmentForm = this.employeeMasterCreatePagePresenter.employmentForm
    this.employeeAddressForm = this.employeeMasterCreatePagePresenter.employeeAddressForm
    this.employeeFamilyDetailsForm = this.employeeMasterCreatePagePresenter.employeeFamilyDetailsForm
    this.employeeWorkExperienceForm = this.employeeMasterCreatePagePresenter.employeeWorkExperienceForm
    this.viewOrEditOrCreate()
    this.getIdForEdit()
  }
  getIdForEdit() {
    this.activateRoute.queryParamMap.subscribe(param => {
      console.log('param--',param);
      this.employeeId=parseInt(param.get('id'))
    })
  }

  private viewOrEditOrCreate() {
    if (this.employeeMasterCreatePagePresenter.operation === Operation.VIEW) {
      this.isView=true
      // this.addRow()
      this.employeeMaster.disable()

    } else if (this.employeeMasterCreatePagePresenter.operation === Operation.EDIT) {
      this.isEdit=true
    }
    else if (this.employeeMasterCreatePagePresenter.operation === Operation.CREATE) {
      this.isCreate=true
    }
  }

  // addRow() {
  //   this.employeeMasterCreatePagePresenter.addRows()
  // }

  validateForm() {
    if (this.employeeMaster.status === 'VALID') {
      this.openConfirmDialog();
    } else {
      this.employeeMasterCreatePagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }


  }

  resetForm() {
    this.employeeMaster.reset();
    //clears row
    const control = this.employeeMasterCreatePagePresenter.employeeFamilyDetailsForm.get('dataTable') as FormArray
    control.clear()
    this.employeeMasterCreatePagePresenter.addRow()

    const controls =this.employeeMasterCreatePagePresenter.employeeWorkExperienceForm.get('dataTables') as FormArray
    controls.clear()
    this.employeeMasterCreatePagePresenter.addRows()

  }
  private buildJsonForSaveEmployee() {
    const saveDealerEmployee={
      ...this.buildJsonForEmployeeMasterDetails(),
      ...this.buildJsonForEmployee(),
      ...this.buildJsonForEmployment(),
      ...this.buildJsonForEmployment(),
      ...this.buildJsonForEmployeeAddress(),
      ...this.buildJsonForEmployeeFamilyDetails(),
      ...this.buildJsonForEmployeeWorkExperience()

    }
    // let formData: any = new FormData();
    // formData.append('saveDealerEmployee', JSON.stringify(saveDealerEmployee))
    return saveDealerEmployee
  }

  buildJsonForEmployeeMasterDetails() {
    const employeeMasterDetails = this.employeeMasterCreatePagePresenter.detailsForm.getRawValue()
    let submitData = {} as SubmitDto
    submitData.dealerId= employeeMasterDetails.dealerDetails ? employeeMasterDetails.dealerDetails.id: null
    console.log('submitData.dealerId--<',submitData);
    if ( this.isEdit===true) {
      submitData.id=this.employeeId
      if (employeeMasterDetails.status==='ACTIVE') {
        submitData.activeStatus= 'Y'
      }
      else{
        submitData.activeStatus= 'N'
      }
    }
    
    submitData.employeeCode= employeeMasterDetails.employeeCode ? employeeMasterDetails.employeeCode.employeeCode : null
    submitData.title= employeeMasterDetails.title ? employeeMasterDetails.title : null
    submitData.firstName= employeeMasterDetails.firstName ? employeeMasterDetails.firstName : null
    submitData.middleName= employeeMasterDetails.middleName ? employeeMasterDetails.middleName : null
    submitData.lastName= employeeMasterDetails.lastName ? employeeMasterDetails.lastName : null
    
    //submitData.status= employeeMasterDetails.status ? employeeMasterDetails.status : null
    submitData.emailId= employeeMasterDetails.email ? employeeMasterDetails.email : null
    submitData.mobileNo= employeeMasterDetails.mobileNo ? employeeMasterDetails.mobileNo : null
    submitData.alternateMobileNo= employeeMasterDetails.alternateMobileNo ? employeeMasterDetails.alternateMobileNo : null
    submitData.division= employeeMasterDetails.division ? employeeMasterDetails.division : null
    submitData.departmentId= employeeMasterDetails.department ? employeeMasterDetails.department : null
    submitData.designationId= employeeMasterDetails.desidnation ? employeeMasterDetails.desidnation : null
    submitData.licenceType= employeeMasterDetails.licenceType ? employeeMasterDetails.licenceType : null
    submitData.drivingLicenceNo= employeeMasterDetails.drivingLicenceNo ? employeeMasterDetails.drivingLicenceNo : null
    submitData.drivingLicenceExpiryDate= this.dateService.getDateIntoDDMMYYYY(employeeMasterDetails.expiryDate)
    submitData.reportingEmployeeId= employeeMasterDetails.reportingEmployeeCode ? employeeMasterDetails.reportingEmployeeCode.reportingEmpID : null
    submitData.reportingEmployeeName= employeeMasterDetails.reportingEmployeeName ? employeeMasterDetails.reportingEmployeeName : null
    
    return submitData
  }

  buildJsonForEmployee() {
    const employeeDetails = this.employeeMasterCreatePagePresenter.employeeForm.getRawValue()
    let submitData = {} as SubmitDto
    //submitData=employeeDetails
    submitData.birthDate= this.dateService.getDateIntoDDMMYYYY(employeeDetails.birthday )
    submitData.anniversaryDate= this.dateService.getDateIntoDDMMYYYY(employeeDetails.anniversaryDate)
    submitData.highestQualification= employeeDetails.highestQualification ? employeeDetails.highestQualification : null
    submitData.maritalStatus= employeeDetails.maritalStatus ? employeeDetails.maritalStatus : null
    submitData.bloodGroup= employeeDetails.bloodGroup ? employeeDetails.bloodGroup : null
    submitData.sex= employeeDetails.sex ? employeeDetails.sex : null
    submitData.emergencyContactName= employeeDetails.emergencyContactName ? employeeDetails.emergencyContactName : null
    submitData.emergencyContactNo= employeeDetails.emergencyContactNo ? employeeDetails.emergencyContactNo : null
    return submitData
  }

  buildJsonForEmployment() {
    const employmentForm = this.employeeMasterCreatePagePresenter.employmentForm.getRawValue()
    let submitData = {} as SubmitDto
    submitData.joiningDate= this.dateService.getDateIntoDDMMYYYY(employmentForm.joiningDate)
    console.log('joiningDate--',submitData.joiningDate);
    submitData.preFromDate=employmentForm.preFromDate?this.dateService.getDateIntoDDMMYYYY(employmentForm.preFromDate):null
   submitData.preToDate=employmentForm.preToDate?this.dateService.getDateIntoDDMMYYYY(employmentForm.preToDate):null,
    submitData.totalExperience=employmentForm.totalExperience?employmentForm.totalExperience:null,
    submitData.latestSalary= employmentForm.latestSalary ? employmentForm.latestSalary : null
    submitData.leavingDate= employmentForm.leavingDate ? this.dateService.getDateIntoDDMMYYYY(employmentForm.leavingDate):null
    submitData.pfNo= employmentForm.pfNo ? employmentForm.pfNo : null
    submitData.panNo= employmentForm.panNo ? employmentForm.panNo : null
    submitData.aadharNo=employmentForm.aadharNo?employmentForm.aadharNo:null,
    submitData.esiNo= employmentForm.esiNo ? employmentForm.esiNo : null
    submitData.bankAccountNo= employmentForm.bankAccountNo ? employmentForm.bankAccountNo : null
    submitData.bankName= employmentForm.bankName ? employmentForm.bankName : null
    submitData.bankBranch= employmentForm.bankBranch ? employmentForm.bankBranch : null
    submitData.ifsCode=employmentForm.ifsCode?employmentForm.ifsCode:null,
    submitData.authorizedDiscount= employmentForm.authorizedDiscount ? employmentForm.authorizedDiscount : null
    return submitData
  }

  buildJsonForEmployeeAddress() {
    const employeeAddress = this.employeeMasterCreatePagePresenter.employeeAddressForm.getRawValue()
    let submitData = {} as SubmitDto
    submitData.address1= employeeAddress.address1 ? employeeAddress.address1 : null
    submitData.address2= employeeAddress.address2 ? employeeAddress.address2 : null
    submitData.address3= employeeAddress.address3 ? employeeAddress.address3 : null
    submitData.pinCode= employeeAddress.pinCode ? employeeAddress.pinCode.pinCode : null
    submitData.locality= employeeAddress.postOffice ? employeeAddress.postOffice.postOffice : null
    submitData.tehsil= employeeAddress.tehsil ? employeeAddress.tehsil.tehsil : null
    submitData.district= employeeAddress.district ? employeeAddress.district.district : null
    submitData.city= employeeAddress.city ? employeeAddress.city.city : null
    submitData.state= employeeAddress.state ? employeeAddress.state.state : null
    submitData.country= employeeAddress.country ? employeeAddress.country : null
    return submitData
  }

  buildJsonForEmployeeFamilyDetails() {
    const employeeFamilyDetails = this.employeeMasterCreatePagePresenter.employeeFamilyDetailsForm.getRawValue()
    console.log('vinay--',employeeFamilyDetails.dataTable);
    let familyDetails:[]=[]
    // let submitData = {} as SubmitDto
    let submitData = {} as any
    // submitData.isSelected= employeeFamilyDetails.isSelected ? employeeFamilyDetails.isSelected : null
    // submitData.name= employeeFamilyDetails.name ? employeeFamilyDetails.name : null
    // submitData.relationship= employeeFamilyDetails.relationship ? employeeFamilyDetails.relationship : null
    return submitData.familyDetails=employeeFamilyDetails.dataTable
  }

  buildJsonForEmployeeWorkExperience() {
    const employeeWorkExperience = this.employeeMasterCreatePagePresenter.employeeWorkExperienceForm.getRawValue()
    console.log('vinay1--',employeeWorkExperience.dataTables);
    let submitData = {} as SubmitDto
    submitData.isSelected= employeeWorkExperience.isSelected ? employeeWorkExperience.isSelected : null
    submitData.companyName= employeeWorkExperience.companyName ? employeeWorkExperience.companyName : null
    submitData.fromDate= this.dateService.getDateIntoDDMMYYYY(employeeWorkExperience.fromDate)
    submitData.toDate= this.dateService.getDateIntoDDMMYYYY(employeeWorkExperience.toDate)
    submitData.designation= employeeWorkExperience.designation ? employeeWorkExperience.designation : null
    submitData.role= employeeWorkExperience.isSelected ? employeeWorkExperience.role : null
    return submitData
  }


  submitData() { 
    
    this.employeeMasterCreatePageService.postSubmitDto(this.buildJsonForSaveEmployee()).subscribe(res => {
      if (res.status == "200") {
        this.toasterService.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activateRoute })
      } else {
        this.toastr.error(res.message)
      }
    })

  }
  updateEmployeeMaster(){
    this.employeeMasterCreatePageService.updateEmployeeMaster(this.buildJsonForSaveEmployee()).subscribe(res => {
      if (res.status == "200") {
        this.toasterService.success(res.message, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activateRoute })
      } else {
        this.toastr.error(res.message)
      }
    })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Dealer Empolyee Master?';
    if (this.isEdit) {
      message = 'Do you want to update Dealer Empolyee Master?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.updateEmployeeMaster();
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

  exit() {
    this.router.navigate(['../'], { relativeTo: this.activateRoute })
  }

}
