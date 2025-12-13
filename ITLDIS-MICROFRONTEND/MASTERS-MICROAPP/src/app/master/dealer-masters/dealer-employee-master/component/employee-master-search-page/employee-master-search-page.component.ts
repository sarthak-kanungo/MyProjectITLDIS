import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { SubmitDto } from 'src/app/master/itldis-masters/itldis-user/domain/itldis-user-domain';
import { ObjectUtil } from 'src/app/utils/object-util';
import { EmployeeMasterSearchPageStore } from './employee-master-page-search.store';
import { EmployeeMasterSearchPagePresenter } from './employee-master-search-page.presenter';
import { EmployeeMasterSearchPageService } from './employee-master-search-page.service';

@Component({
  selector: 'app-employee-master-search-page',
  templateUrl: './employee-master-search-page.component.html',
  styleUrls: ['./employee-master-search-page.component.scss'],
  providers: [EmployeeMasterSearchPageStore, EmployeeMasterSearchPagePresenter, EmployeeMasterSearchPageService]
})
export class EmployeeMasterSearchPageComponent implements OnInit, AfterViewInit {

  searchDealerEmployeeForm: FormGroup

  public dataTable: DataTable;
  public searchValue: ColumnSearch;
  public actionButtons = [];
  public totalTableElements: number;
  employeeForm: FormGroup
  searchFilter;
  searchFlag: boolean = true;
  searchFilterValues: any;
  recordData: SubmitDto
  page = 0;
  size = 10
  clearSearchRow = new BehaviorSubject<string>("");
  private employeeMasterSearch = {} as SearchEmployeeMaster;
  employeeCodeNgModel: any
  isClickableNgModel: any
  titleNgModel: any
  employeeNameNgModel: any
  middleNameNgModel: any
  lastNameNgModel: any
  emailIdNgModel: any
  mobileNumberNgModel: any
  alternateMobileNumberNgModel: any
  departmentNameNgModel: any
  designationNgModel: any
  licenceTypeNgModel: any
  drivingLicenceNumberNgModel: any
  drivingLicenceExpiryDateNgModel: any
  joiningDateNgModel: any
  latestSalaryNgModel: any
  leavingDateNgModel: any
  pfNumberNgModel: any
  panNumberNgModel: any
  esiNumberNgModel: any
  bankAccountNumberNgModel: any
  bankNameNgModel: any
  bankBranchNgModel: any
  pinCodeNgModel: any
  localityNgModel: any
  tehsilNgModel: any
  districtNgModel: any
  cityNgModel: any
  stateNgModel: any
  countryNgModel: any
  activeStatusNgModel: any

  constructor(
    private employeeMasterSearchPagePresenter: EmployeeMasterSearchPagePresenter,
    private employeeMasterSearchPageService: EmployeeMasterSearchPageService,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) { }


  ngAfterViewInit() {
    this.searchEmployeeMaster();
  }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem('searchFilter')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.searchDealerEmployeeForm = this.employeeMasterSearchPagePresenter.searchDealerEmployeeForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchDealerEmployeeForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    this.searchDealerEmployeeForm = this.employeeMasterSearchPagePresenter.searchDealerEmployeeForm
    this.employeeForm = this.employeeMasterSearchPagePresenter.employeeForm
  }

  searchEmployeeMaster() {
    // console.log('search check----------------')
    const searchFormValues = this.employeeForm.value as SearchEmployeeMaster;
    
  if(this.employeeForm.get('employeeCode').value != null) {
    this.employeeMasterSearch.employeeCode = this.employeeForm.get('employeeCode').value.employeeCode;
    // console.log(this.employeeForm.get('employeeCode').value.employeeCode)
  }
  if(this.employeeForm.get('mobileNo') != null) {
    this.employeeMasterSearch.mobileNo = this.employeeForm.get('mobileNo').value;
  }
  let dealerId:string
  if ( this.employeeForm.get('dealerCode').value!=null) {
    dealerId= this.employeeForm.get('dealerCode').value.id;
  }
  if(this.employeeForm.get('departmentId').value != null) {

  }
 
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.searchDealerEmployeeForm.value))
    if (this.dataTable != undefined) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
      else {
        this.dataTable['PageIndex'] = this.page
        this.dataTable['PageSize'] = this.size
      }
    }
    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    else {
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    const temp = this.searchDealerEmployeeForm.getRawValue().employeeForm
    temp['page'] = this.page
    temp['size'] = this.size
    searchFormValues.dealerId=dealerId
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);

    this.employeeMasterSearchPageService.searchEmployeeMasterTable(searchFormValues).subscribe(res => {
      res['result'].forEach(element => {
        element.edit = 'edit'
      });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      // console.log('totalTableElements--', this.totalTableElements);
    })
  }

  tableAction(event: object) {
    // console.log('event', event)
    this.recordData = event['record']
    if (event['btnAction'].toLowerCase() === 'employeecode') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
        //relativeTo: this.activatedRoute, queryParams: { id: this.recordData, hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
    if (event['btnAction'].toLowerCase() === 'edit') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
  }

  initialQueryParams(event) {
    this.searchDealerEmployeeForm.get('employeeForm').patchValue(event);
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchEmployeeMaster()
  }

  clearEmployeeMaster() {
    this.searchDealerEmployeeForm.reset()
    // this.searchEmployeeMaster()
    this.tableSearchClear()
    this.clearSearchRow.next("");
  }

  tableSearchClear() {
    this.dataTable=null;
    this.employeeCodeNgModel = ''
    this.isClickableNgModel = ''
    this.titleNgModel = ''
    this.employeeNameNgModel = ''
    this.middleNameNgModel = ''
    this.lastNameNgModel = ''
    this.emailIdNgModel = ''
    this.mobileNumberNgModel = ''
    this.alternateMobileNumberNgModel = ''
    this.departmentNameNgModel = ''
    this.designationNgModel = ''
    this.licenceTypeNgModel = ''
    this.drivingLicenceNumberNgModel = ''
    this.drivingLicenceExpiryDateNgModel = ''
    this.joiningDateNgModel = ''
    this.latestSalaryNgModel = ''
    this.leavingDateNgModel = ''
    this.pfNumberNgModel = ''
    this.panNumberNgModel = ''
    this.esiNumberNgModel = ''
    this.bankAccountNumberNgModel = ''
    this.bankNameNgModel = ''
    this.bankBranchNgModel = ''
    this.pinCodeNgModel = ''
    this.localityNgModel = ''
    this.tehsilNgModel = ''
    this.districtNgModel = ''
    this.cityNgModel = ''
    this.stateNgModel = ''
    this.countryNgModel = ''
    this.activeStatusNgModel = ''
  }

 

}
export interface SearchEmployeeMaster{
  employeeCode:string,
  mobileNo:number,
  employeeName: number,
  state:string,
  dealerId:string,
  departmentId: number,

}
