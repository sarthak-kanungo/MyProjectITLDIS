import { Component, OnInit } from '@angular/core';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EmplyeeDetailsService } from './emplyee-details.service';
import { AutoDesiganationLevel, SaveEmployee, DropDesiganationLevel, DropDoenDepartments, DepartmentMasters } from '../empolyee-details/employee-details';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { SearchkubotaEmployeeService } from '../search-kubota-empolyee/searchkubota-employee.service';
import { EmployeeDetail } from '../empolyee-details/employee-details';

@Component({
  selector: 'app-empolyee-details',
  templateUrl: './empolyee-details.component.html',
  styleUrls: ['./empolyee-details.component.scss']
})
export class EmpolyeeDetailsComponent implements OnInit {

  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  departmentId: number;
  designationLevelId : number;
  designationId : number;
  reportingId : number;

  data: Object;
  disable = true;
  buttons = "Submit";
  status: string[] = [
    'ACTIVE', 'INACTIVE',
  ];
  employeeDetailsForm: FormGroup;
  departmentsList: Array<DropDoenDepartments> = [];
 // kaibranchList: BaseDto<Array<DropDoenKaibranch>>
  designationLeveList: Array<AutoDesiganationLevel> = [];
  designationNameList: Array<DropDesiganationLevel> = [];
  constructor(private fb: FormBuilder,
    private emplyeeDetailsService: EmplyeeDetailsService,
    private searchkubotaEmployeeService: SearchkubotaEmployeeService,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute, ) { }

  ngOnInit() {
    
    this.checkOperationType();
    this.patchOrCreate();    
    this.checkEmployeeCode();
    this.selectDesignationLevel();

   // this.dropDownKaiBranch();
  }

  checkEmployeeCode(){    
    if (this.isCreate) {
      this.employeeDetailsForm.controls.employeeCode.valueChanges.subscribe(empCode => {
        this.searchkubotaEmployeeService.searchEmployeeCode(empCode).subscribe(response => {      
          response.result.forEach(value =>{
             if (value.employeeCode == empCode ) {
               this.toastr.error("Employee Code Already Exists.");
               this.employeeDetailsForm.controls.employeeCode.patchValue('');
             }
          });        
        });
      });
    }
  }

  createemployeeDetailsForm() {
    this.employeeDetailsForm = this.fb.group({
      employeeCode: [null, Validators.required],
      employeeName: [null, Validators.required],
      departmentName: [null, Validators.required],
     // branchDepotMasters: [null, Validators.required],
      designationLevel: [null, Validators.compose([Validators.required, CustomValidators.selectValueFromList()])],
      designation: [null, Validators.required],
      contactNo: [null, Validators.required],
      emailId: [null, Validators.compose([Validators.required, CustomValidators.validateEmail])],
      status: [null, Validators.required],
      managementAccess: [false],
    });

    this.dropDownDepartment();

    if (this.departmentId) {
      this.employeeDetailsForm.controls.designationLevel.valueChanges.subscribe(value => {
        this.autoDesignationLevel(value, this.departmentId)
      });
    }

    this.emplyeeDetailsService.directRepotees = [];
    this.emplyeeDetailsService.reportingEmployee = {reportingEmployeeCode: '', reportingEmployeeName:''};
  }

  autoDesignationLevel(searchText, value) {
    this.emplyeeDetailsService.searchDesiganationLevel(searchText, value).subscribe(response => {
      this.designationLeveList = response.result
    })
  }

  displayFnDesignationLevel(designationLevel: AutoDesiganationLevel) {
    return designationLevel ? designationLevel.designationLevel : undefined
  }

  // dropDownKaiBranch() {
  //   this.emplyeeDetailsService.getKaiBranchDrop().subscribe(res => {
  //     this.kaibranchList = res
  //     console.log('this.kaibranchList', this.kaibranchList)
  //   })
  // }

  dropDownDepartment() {
    this.emplyeeDetailsService.getDeprtmentDrop().subscribe(res => {
      console.log(res)
      this.departmentsList = res.result
    })
  }
  validateForm() {
    if(this.employeeDetailsForm.valid && this.emplyeeDetailsService.reportingEmployeeForm.valid) this.openConfirmDialog();
    else this.markAsTouched();
  }

  markAsTouched() {
    this.employeeDetailsForm.markAllAsTouched();
    this.emplyeeDetailsService.reportingEmployeeForm.markAllAsTouched();
  }

  onlyNumberValidation(event: any) {
    const pattern = /[0-9]/;
    const inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
  submitData() {
    const formData = {} as SaveEmployee
    formData.contactNo = this.employeeDetailsForm.controls.contactNo.value;
    formData.departmentMasters = [] as DepartmentMasters[]
    //this.employeeDetailsForm.value.departmentName.forEach(element => {
      const departmentValue = {} as DepartmentMasters
      departmentValue.id = this.employeeDetailsForm.value.departmentName.id
      formData.departmentMasters.push(departmentValue)
    //});
    // formData.branchDepotMasters = [] as KaiBranch[]
    // this.employeeDetailsForm.value.branchDepotMasters.forEach(element => {
    //   const kaiBranchValue = {} as KaiBranch
    //   kaiBranchValue.id = element.id
    //   formData.branchDepotMasters.push(kaiBranchValue)
    // });
    formData.employeeCode = this.employeeDetailsForm.controls.employeeCode.value;
    formData.designationHierarchy = { id: this.employeeDetailsForm.controls.designationLevel.value.id };
    formData.emailId = this.employeeDetailsForm.controls.emailId.value;
    formData.employeeName = this.employeeDetailsForm.controls.employeeName.value;
    formData.managementAccess = this.employeeDetailsForm.controls.managementAccess.value;
    formData.reportingUser = {id: this.emplyeeDetailsService.reportingEmployeeDetail.id};
    formData.hoDepartmentId = this.employeeDetailsForm.value.departmentName.id;
    formData.hoDesignationLevelId = this.employeeDetailsForm.value.designationLevel.id;
    formData.hoDesignationId = this.employeeDetailsForm.controls.designation.value.id;
    formData.reportingUserId = this.emplyeeDetailsService.reportingEmployeeDetail.id;
    formData.activeStatus = this.employeeDetailsForm.controls.status.value == "ACTIVE"? 'Y':'N';

    if (formData.hoDepartmentId == null || formData.hoDepartmentId == undefined) {
      formData.hoDepartmentId = this.departmentId;
    } 
    if (formData.hoDesignationLevelId == null || formData.hoDesignationLevelId == undefined) {
      formData.hoDesignationLevelId = this.designationLevelId;
    } 
    if (formData.hoDesignationId == null || formData.hoDesignationId == undefined) { 
      formData.hoDesignationId = this.designationId;
    } 
    if (formData.reportingUserId == null || formData.reportingUserId == undefined) {
      formData.reportingUserId = this.reportingId;
    }

    if (this.isEdit) {
      this.emplyeeDetailsService.updateEmployee(formData).subscribe(formData => {
        if (formData) {
          this.toastr.success('Employee Master Updated Successfully', 'Success')
        }
        this.router.navigate(['../../'], { relativeTo: this.activatedRoute })
      })
    } else {
      this.emplyeeDetailsService.submitEmployee(formData).subscribe(formData => {
        console.log(formData);
        if (formData) {
          this.toastr.success('Employee Master Submited Successfully', 'Success')
        }
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      })
    }

  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Kubota Employee Master?';
    if (this.isEdit) {
       message = 'Do you want to update Kubota Employee Master?';
    } 
    
    const confirmationData = this.setConfirmationModalData(message);
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
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  clearForm() {
    this.employeeDetailsForm.reset();
    this.emplyeeDetailsService.reportingEmployeeForm.reset();
    this.emplyeeDetailsService.directRepotees = [];
    this.emplyeeDetailsService.reportingEmployee = {reportingEmployeeCode: '', reportingEmployeeName:''};
  }
  selectDesignationLevel() {
    this.emplyeeDetailsService.getDesiganationLevel().subscribe(res => {
      this.designationNameList = res.result
    })
  }

  selectDepartment(event){
    this.departmentId = event.value.id;
    this.autoDesignationLevel('', event.value.id);    
    if (!this.isView) {
      this.employeeDetailsForm.controls.designationLevel.patchValue('');
      this.employeeDetailsForm.controls.designation.patchValue('');
    }
  }

  
  private checkOperationType() {
    console.log(this.activatedRoute.snapshot.routeConfig.path)
    this.isView = this.activatedRoute.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.activatedRoute.snapshot.routeConfig.path.split('/')[0] == 'edit'
  }

  private patchOrCreate() {
    if (this.isView) {
      this.createemployeeDetailsForm();
      this.isView = true;
      this.activatedRoute.params.subscribe(prms =>{
        this.pathchData(prms.viewId);
      })
    }
    else if (this.isEdit) {
      this.createemployeeDetailsForm();
      this.isEdit = true;
      this.activatedRoute.params.subscribe(prms =>{
        this.pathchData(prms.editId);
      })
      this.buttons = "Update";
    }
    else {
      this.createemployeeDetailsForm();
      this.isCreate = true;   
    }
  }

  pathchData(empId){
    let index = 0;
    this.emplyeeDetailsService.getKubotaEmployeeById(empId).subscribe(emp => {
        emp.result.forEach(dR => {
          index++;
          this.emplyeeDetailsService.directRepotees.push({id:index, directRepotee:dR.directRepotee});
        });        
        this.emplyeeDetailsService.reportingEmployee = {reportingEmployeeCode : emp.result[0].reportingEmployeeCode, 
                                                        reportingEmployeeName : emp.result[0].reportingEmployeeName}
        this.employeeDetailsForm.patchValue(emp.result[0]);
        if (emp.result[0].activeStatus == "Y") {
          this.employeeDetailsForm.controls.status.patchValue('ACTIVE');
        } else {
          this.employeeDetailsForm.controls.status.patchValue('INACTIVE') ;
        }             
        
    //     this.employeeDetailsForm.get('departmentName').patchValue(this.departmentsList[this.departmentsList
    //                                                   .findIndex(ele => ele['departmentName'] === emp.result[0].departmentName)]);
    //     this.employeeDetailsForm.get('designationLevel').patchValue(this.designationLeveList[this.designationLeveList
    //                                                     .findIndex(ele => ele['designationLevel'] === emp.result[0].designationLevel)]);
    //     this.employeeDetailsForm.get('designation').patchValue(this.designationNameList[this.designationNameList
    //                                                .findIndex(ele => ele['designation'] === emp.result[0].designation)]);
        this.departmentId = emp.result[0].departmentId;
        this.designationLevelId = emp.result[0].designationLevelId;
        this.designationId = emp.result[0].designationId;
        this.reportingId = emp.result[0].reportingId;
        

        let dL:AutoDesiganationLevel = {designationLevel:emp.result[0].designationLevel};
        this.employeeDetailsForm.get('designationLevel').patchValue(dL);                
        
        let dsgnL:DropDesiganationLevel = {designation:emp.result[0].designation, id:emp.result[0].designationId}; 
            
        this.employeeDetailsForm.get('designation').patchValue(dsgnL.designation);                

        this.autoDesignationLevel('', this.departmentId); 

     })
     this.employeeDetailsForm.controls.employeeCode.disable();
     if (this.isView) {
        this.employeeDetailsForm.disable();
     }
  }

  exitForm(){
    if(this.isEdit || this.isView) {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
    } else {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    }    
  }

  compareFnDepartmentName(c1: DropDoenDepartments, c2: EmployeeDetail): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.departmentName === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.departmentName;
    }
  }

  compareFnDesignation(c1: DropDesiganationLevel, c2: EmployeeDetail): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.designation === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.designation;
    }
  }
}
