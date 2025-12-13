import { Component, OnInit, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TitleDropdown, StatusDropdown, DivisionDropdown, DepartmentDropdown, DesidnationDropdown, LicenceTypeDropdown, AutoEmployeeCode, AutoDealerDetails, ReportingEmployeeCodeAuto, SubmitDto } from '../../domain/dealer-employee-domain';
import { EmployeeMasterService } from './employee-master.service';
import { EmployeeMasterCreatePageService } from '../employee-master-create-page/employee-master-create-page.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeMasterCreatePagePresenter } from '../employee-master-create-page/employee-master-page.presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';

@Component({
  selector: 'app-employee-master',
  templateUrl: './employee-master.component.html',
  styleUrls: ['./employee-master.component.scss'],
  providers: [EmployeeMasterService,EmployeeMasterCreatePageService,EmployeeMasterCreatePagePresenter,
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class EmployeeMasterComponent implements OnInit {
  reportingEmpId:number;
  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  @Input() employeeId:number
  @Input() employeeMasterDetails:FormGroup
  employeeCodeList: AutoEmployeeCode[]=[];
  dealerdetailsList: AutoDealerDetails[]=[]
  reportingEmloyeeCodeList: ReportingEmployeeCodeAuto[]=[]
  dealerId:number
  reportingEmployeeName:string
  isShowPcrButton: boolean
  today = new Date();
  dealerCode:string
  titles:Array<TitleDropdown> = [
    'Mr.', 'Miss', 'Mrs.',
  ];

  data: Object;
  disable = true;
  
  statuses: Array<StatusDropdown> = [
    'INACTIVE', 'ACTIVE',
  ];

  divisions: Array<DivisionDropdown> = [
    'Service', 'General', 'Sales', 'Spare Parts'
  ];

  // departments:Array<DepartmentDropdown> = [
  //   'Service', 'General', 'Sales', 'Spare Parts'
  // ];
  departments:DepartmentDropdown

  // designations:Array<DesidnationDropdown> = [
  //   'Workshop Incharge', 'Workshop Manager', 'Mechanic', 'Local Mechanic', 'Helper'
  // ];
  designations:DesidnationDropdown

  licences: Array<LicenceTypeDropdown> = [
    '2W', '3W', 'LCV', 'HCV', 
  ];

  //licences: LicenceTypeDropdown

  employeemasterForm: FormGroup;
  constructor( private fb: FormBuilder,public dialog: MatDialog,
    private employeeMasterService:EmployeeMasterService,
    private employeeMasterCreatePageService:EmployeeMasterCreatePageService,
		private toastr: ToastrService,
		private activateRoute: ActivatedRoute,
		private router: Router,
    private employeeMasterCreatePagePresenter: EmployeeMasterCreatePagePresenter,
    ) { }

  ngOnInit() {
    //this.valueChangesForAutoComplete();
    this.employeeMasterCreatePagePresenter.operation = OperationsUtil.operation(this.activateRoute)
    this.viewOrEditOrCreate()
    this.employeMastDepartment();
    this.employeMastDesignations();
    this. checkRouterParamsForEmpMaster();
    this.employeeMasterDetails.get('mobileNo').valueChanges.subscribe(response => {
      
      if(response && (response+'').length==10){
        this.employeeMasterCreatePageService.validateMobileNo(response, this.employeeId).subscribe(res => {
          

          if(res && res['message']=='EXIST'){
            this.employeeMasterDetails.get('mobileNo').setErrors({'DuplicateMobileNo':'Employee with same mobile number already available'});
          }else{
            this.employeeMasterDetails.get('mobileNo').setErrors(null);
          }
          this.employeeMasterDetails.get('mobileNo').markAsTouched();
        });
      }
    });
    
  }

  checkRouterParamsForEmpMaster() {
    this.activateRoute.queryParamMap.subscribe(param => {
      console.log('param--',param);
      if (param.has('id')) {
				if (this.isEdit== true) {
					this.viewEmployeeMaster(param.get('id'))
				}
				if (this.isView) {
					this.viewEmployeeMaster(param.get('id'))
				}
			}
      
    })
  }

  private viewOrEditOrCreate() {
		if (this.employeeMasterCreatePagePresenter.operation === Operation.VIEW) {
			this.isView = true
		} else if (this.employeeMasterCreatePagePresenter.operation === Operation.EDIT) {
			this.isEdit = true
		} 
    
    else if (this.employeeMasterCreatePagePresenter.operation === Operation.CREATE) {
			//this.isCreate = true
      this.isCreate = true
      // this.employeeMasterDetails.get('status').patchValue('ACTIVE')
      // this.employeeMasterDetails.get('status').disable()
		}
	}
  
  viewEmployeeMaster(id:any){
    this.employeeMasterCreatePageService.viewEmployeeMaster(id).subscribe(res=>{
      this.reportingEmpId = res['0']['reportingEmployeeId'];
      this.employeeMasterCreatePagePresenter.patchDataFromView(res[0])
      this.employeeMasterCreatePagePresenter.patchDataForDealerDetails(res[1])
      this.reporEmpAutoSuggestion('');
      })
  }

  


  autoDealerDetails(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.employeeMasterCreatePageService.dealerDetailsAuto(value).subscribe(res=>{
        this.dealerdetailsList=res
      })
    }
  }
  
  displayFnDealerDetailes(dealerDetails: AutoDealerDetails) {
    return dealerDetails ? dealerDetails.displayString : undefined;
  }

  dealerDetailsSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeMasterDetails.get('dealerDetails').setErrors(null);
    }
    let dealerVal=this.employeeMasterDetails.get('dealerDetails').value
    if (dealerVal==null) {
      this.employeeMasterDetails.get('dealerDetails').setErrors({
        selectFromList:''
      })
    }
    this.dealerId= this.employeeMasterDetails.get('dealerDetails').value.id
    this.dealerCode= this.employeeMasterDetails.get('dealerDetails').value.code
    this.reporEmpAutoSuggestion('');
  }

  onKeyEmployeeeCode(event: KeyboardEvent) {
      this.onFocusGetEmployeeCodeList('');
  }
  onFocusGetEmployeeCodeList(value){
    if (value == null || value == undefined)
        value = '';
    
    if(typeof value !== 'object'){
        this.getEmployeeCode(value);
    }
    else{
      this.employeeCodeList = null;
  }
  }

  
  valueChangesForAutoComplete() {
    this.employeemasterForm.get('employeeCode').valueChanges.subscribe(value => {
      this.onFocusGetEmployeeCodeList(value);
    })
  }

  getEmployeeCode(employeeCode: string){
    this.employeeMasterCreatePageService.employeeCodeAuto(this.dealerId,employeeCode).subscribe(res=>{
    this.employeeCodeList=res
    
    })
  }

  displayFnEmployeeCode(employeeCode: AutoEmployeeCode) {
    return employeeCode ? employeeCode.employeeCode : undefined;
  }


  employeeCodeSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeMasterDetails.get('employeeCode').setErrors(null);
    }
   // this.employeeDetailsById(event);
   let employeeCodeCheck=this.employeeMasterDetails.get('employeeCode').value.employeeCode
  // this.checkDuplicateEmployeeCode(employeeCodeCheck)

  }
  

  checkDuplicateEmployeeCode(employeeCodeCheck) { 
    this.employeeMasterService.checkDuplicateEmployeeCode(employeeCodeCheck).subscribe(res => {
      if (res!=null || res!='') {
        this.employeeMasterDetails.get('employeeCode').setErrors({
          selectFromList:''
        })
      }
     
    })
  }

  employeeDetailsById(event: MatAutocompleteSelectedEvent) { 
    this.employeeMasterService.getEmployeeDetailsById(event.option.value.id).subscribe(res => {
      this.employeeMasterDetails.patchValue(res.headerResponse);
      console.log('employeeId---',event.option.value.id);
      
    })
  }

  employeMastDepartment() { 
    this.employeeMasterService.departmentDropdown().subscribe(res=>{
      this.departments=res
    })
  }

  employeMastDesignations() { 
    this.employeeMasterService.designationsDropdown().subscribe(res=>{
      this.designations=res
    })
  }

  employeMastlicences() { 
    this.employeeMasterService.licencesDropdown().subscribe(res=>{
     // this.licences=res
    })
  }

  
  autoReportingEmployeeCode(event:KeyboardEvent){
    let value= event
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.reporEmpAutoSuggestion(value);
    }else{
      this.reporEmpAutoSuggestion(value['reportingEmpCode']);
    }
  }

  reporEmpAutoSuggestion(searchText){
    let dealerCode= this.employeeMasterDetails.get('dealerDetails').value.code
    let empCode= ''
    if(this.isView || this.isEdit){
      empCode= this.employeeMasterDetails.get('employeeCode').value.employeeCode
    }
    this.employeeMasterCreatePageService.reportingEmployeeAuto(dealerCode,searchText,empCode).subscribe(res=>{
      this.reportingEmloyeeCodeList=res

      
      if((this.isView || this.isEdit) && this.employeeMasterDetails.get('reportingEmployeeCode').value==null){
        
        const selectedEmp = this.reportingEmloyeeCodeList.filter(obj => obj['reportingEmpID']===this.reportingEmpId)[0]

        this.employeeMasterDetails.get('reportingEmployeeCode').patchValue(selectedEmp);
        this.employeeMasterDetails.get('reportingEmployeeName').patchValue(selectedEmp['reportingEmpName']);
      }

    })
  }
  displayFnReportingEmployee(reportingEmployeeCode: ReportingEmployeeCodeAuto) {
    return reportingEmployeeCode ? reportingEmployeeCode.displayValue : undefined;
  }

  reportingEmpCodeSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeMasterDetails.get('reportingEmployeeCode').setErrors(null);
    }
    let reportingEmpName = this.employeeMasterDetails.get('reportingEmployeeCode').value.reportingEmpName
    this.employeeMasterDetails.get('reportingEmployeeName').patchValue(reportingEmpName);
  }


}
