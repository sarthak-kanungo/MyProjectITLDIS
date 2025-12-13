import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BaseDto } from 'BaseDto';
import { SearchkubotaEmployeeService } from '../search-kubota-empolyee/searchkubota-employee.service';
import { ReportingEmployeeService } from './reporting-employee.service';
import { EmployeeName } from '../empolyee-details/employee-details';
import { autoEmployeeCode } from '../search-kubota-empolyee/search-kubota-employee';
import { EmplyeeDetailsService } from '../empolyee-details/emplyee-details.service';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Component({
  selector: 'app-reporting-employee-details',
  templateUrl: './reporting-employee-details.component.html',
  styleUrls: ['./reporting-employee-details.component.scss']
})
export class ReportingEmployeeDetailsComponent implements OnInit {
  disable = true
  reportingemployeeDetailsForm: FormGroup;

  isEdit: boolean;
  isView: boolean;

  reportingEmployeeCodeList: BaseDto<Array<autoEmployeeCode>>
  @Output() getReportingFormData = new EventEmitter<object>()

  employeeName: Array<EmployeeName>

  constructor(private fb: FormBuilder,
    private searchkubotaEmployeeService: SearchkubotaEmployeeService,
    private reportingEmployeeService: ReportingEmployeeService,
    public emplyeeDetailsService: EmplyeeDetailsService,
    private activatedRoute: ActivatedRoute,
    ) { }

  ngOnInit() {    
    this.checkOperationType();
    this.patchOrCreate();
  }

  createreportingemployeeDetailsForm() {
    this.reportingemployeeDetailsForm = this.fb.group({
      reportingEmployeeCode: [{value: null, disabled: false}, Validators.compose([Validators.required, CustomValidators.selectValueFromList()])],
      reportingEmployeeName: [{ value: null, disabled: true }],
    })
    this.reportingemployeeDetailsForm.controls.reportingEmployeeCode.valueChanges.subscribe(value => {
      this.autoEmployeeCode(value)
    })
    this.emplyeeDetailsService.reportingEmployeeForm = this.reportingemployeeDetailsForm;
  }

  autoEmployeeCode(value) {
      this.searchkubotaEmployeeService.searchEmployeeCode(value).subscribe(response => {
        console.log(response);
        this.reportingEmployeeCodeList = response
      }) 
  }

  displayFnReportingEmployeeCode(employeeCode: autoEmployeeCode) {
    return employeeCode ? employeeCode.employeeCode : undefined
  }
  
  selectReportingEmployeeCode(event) {
    console.log("event ", event);
    const empCode = event.option.value.employeeCode;
    this.emplyeeDetailsService.reportingEmployeeDetail = event.option.value;
    this.reportingEmployeeService.getEmployeeNameByEmployeeCode(empCode).subscribe(res => {
      const reportingEmpName = res['result'][0]['employeeName']
      this.reportingemployeeDetailsForm.controls.reportingEmployeeName.patchValue(reportingEmpName)
    })
  }

  private checkOperationType() {
    console.log(this.activatedRoute.snapshot.routeConfig.path)
    this.isView = this.activatedRoute.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.activatedRoute.snapshot.routeConfig.path.split('/')[0] == 'edit'
    console.log(`Edit = ${this.isEdit} View = ${this.isView}`)
  }

  private patchOrCreate() {  
    if (this.isView) {
      this.viewReportingEmployeeDetailsForm();
      console.log(`Viweing Form`)
      this.isView = true; 
      this.activatedRoute.params.subscribe(prms =>{
      console.log("employee code----->", prms.viewId);
      this.patchData(prms.viewId);  
      })
    }
    else if (this.isEdit) {
      this.createreportingemployeeDetailsForm();
      console.log(`Editing Form`) 
      this.isEdit = true;
      this.activatedRoute.params.subscribe(prms =>{
        console.log("employee code----->", prms.editId);
        this.patchData(prms.editId);  
        })  
    }
    else {
      this.createreportingemployeeDetailsForm();
      console.log(`CreatingForm`)
    }
  }

  patchData(employeeCode){    
      this.reportingemployeeDetailsForm.get('reportingEmployeeCode').setValue({employeeCode: employeeCode});
      this.reportingEmployeeService.getEmployeeNameByEmployeeCode(employeeCode).subscribe(res => {
        console.log("response---->", res);
        const reportingEmpName = res['result'][0]['employeeName']
        this.reportingemployeeDetailsForm.controls.reportingEmployeeName.patchValue(reportingEmpName)
      })
  }

  viewReportingEmployeeDetailsForm(){
    this.reportingemployeeDetailsForm = this.fb.group({
      reportingEmployeeCode: [{value: '', disabled: true}],
      reportingEmployeeName: [{ value: '', disabled: true }],
    })
  }

}
