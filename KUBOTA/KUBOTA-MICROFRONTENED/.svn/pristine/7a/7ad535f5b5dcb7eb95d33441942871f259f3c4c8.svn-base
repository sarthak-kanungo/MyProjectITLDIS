import { Component, OnInit, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { EmployeeIdAutoCreate, EmployeeSearchFilter } from '../../domain/kubota-user-domain'
import { SearchKubotaUserService } from './search-kubota-user.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { EventEmitter } from '@angular/core';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-search-kubota-user',
  templateUrl: './search-kubota-user.component.html',
  styleUrls: ['./search-kubota-user.component.scss'],
  providers: [SearchKubotaUserService]
})
export class SearchKubotaUserComponent implements OnInit {

  @Input() kubotaUserForm: FormGroup;

  @Output() userSearchList = new EventEmitter();

  @Output() userSearchCount = new EventEmitter();
  page: number = 0;
  size: number = 10;
  filterData = {} as EmployeeSearchFilter;
  searchFilter: any = {};
  employeeList$: BaseDto<Array<EmployeeIdAutoCreate>>

  constructor(private fb: FormBuilder,
    private iFrameService: IFrameService,
    private searchKubotaUser: SearchKubotaUserService,
  ) { }

  ngOnInit() {

    this.formValueChanges();
    this.onClickSearch();

  }
  formValueChanges() {
    this.kubotaUserForm.get('employeeCode').valueChanges.subscribe(value => {
      if (value) {
        let employeeCode = typeof value == "object" ? value.employeecode : value;
        this.autoEmployeeCode(employeeCode);
      }
      if (value !== null && typeof value == "string") {
        this.kubotaUserForm.get("employeeCode").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });

    this.kubotaUserForm.get('employeeName').valueChanges.subscribe(value => {
      if (value) {
        let employeeCode = typeof value == "object" ? value.employeeName : value;
        this.autoEmployeeCode(employeeCode);
      }
      if (value !== null && typeof value == "string") {
        this.kubotaUserForm.get("employeeName").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });
  }


  autoEmployeeCode(autoEmployeeCode) {
    let searchparam='N'
    if (autoEmployeeCode) {
      this.searchKubotaUser.getEmployeeCodeDropDown(autoEmployeeCode,searchparam).subscribe(res => {
        this.employeeList$ = res
        console.log(this.employeeList$)
      })
    }

  }

  displayFnForEmployeeCode(selectedOption: EmployeeIdAutoCreate) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['displayValue'] : undefined;
  }
  autoEmployeeName(autoEmployeeName) {
    let searchparam='N'
    if (autoEmployeeName) {
      this.searchKubotaUser.getEmployeeCodeDropDown(autoEmployeeName,searchparam).subscribe(res => {
        this.employeeList$ = res
      })
    }

  }

  displayFnForEmployeeName(selectedOption: EmployeeIdAutoCreate) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['displayValue'] : undefined;
  }


  onClickSearch() {
    const fltUserObj = this.kubotaUserForm.value as EmployeeSearchFilter
    this.page = 0;
    this.size = 10;
    fltUserObj.page = this.page;
    fltUserObj.size = this.size;
    fltUserObj.employeeCode = this.kubotaUserForm.value.employeeCode ? this.kubotaUserForm.value.employeeCode.employeecode : null;
    fltUserObj.employeeName = this.kubotaUserForm.value.employeeName ? this.kubotaUserForm.value.employeeName.employee_name : null;

    this.searchDetail();
    this.searchFilter = ObjectUtil.removeNulls(this.filterData);
    console.log('searchFilter=====>', this.filterData);
    this.searchKubotaUser.getEmployeeSearch(fltUserObj).subscribe(searchRes => {
      searchRes.result.forEach(row => {
        row.action = 'edit';
      });
      this.userSearchList.emit(searchRes.result);
      this.userSearchCount.emit(searchRes.count);

    });

  }

  searchDetail() {
    if (this.kubotaUserForm.get('employeeName').value != null) {
      this.filterData.employeeName = this.kubotaUserForm.get('employeeName').value.employee_name;
    }
    if (this.kubotaUserForm.get('employeeCode').value != null) {
      this.filterData.employeeCode = this.kubotaUserForm.get('employeeCode').value.employeecode;
    }
  }

  initialQueryParams(event) {
    console.log('initialQueryParams_event----->', event)

  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.MASTER, { url, queryParam } as UrlSegment);
  }

  clearUserSearch() {
    this.kubotaUserForm.reset();
    this.onClickSearch();
    this.employeeList$.result.length=0
  }

}
