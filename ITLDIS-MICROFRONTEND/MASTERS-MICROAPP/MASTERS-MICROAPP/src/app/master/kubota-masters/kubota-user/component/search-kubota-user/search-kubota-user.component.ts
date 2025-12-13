import { Component, OnInit, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { EmployeeIdAutoCreate, EmployeeSearchFilter } from '../../domain/itldis-user-domain'
import { SearchitldisUserService } from './search-itldis-user.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { EventEmitter } from '@angular/core';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-search-itldis-user',
  templateUrl: './search-itldis-user.component.html',
  styleUrls: ['./search-itldis-user.component.scss'],
  providers: [SearchitldisUserService]
})
export class SearchitldisUserComponent implements OnInit {

  @Input() itldisUserForm: FormGroup;

  @Output() userSearchList = new EventEmitter();

  @Output() userSearchCount = new EventEmitter();
  page: number = 0;
  size: number = 10;
  filterData = {} as EmployeeSearchFilter;
  searchFilter: any = {};
  employeeList$: BaseDto<Array<EmployeeIdAutoCreate>>

  constructor(private fb: FormBuilder,
    private iFrameService: IFrameService,
    private searchitldisUser: SearchitldisUserService,
  ) { }

  ngOnInit() {

    this.formValueChanges();
    this.onClickSearch();

  }
  formValueChanges() {
    this.itldisUserForm.get('employeeCode').valueChanges.subscribe(value => {
      if (value) {
        let employeeCode = typeof value == "object" ? value.employeecode : value;
        this.autoEmployeeCode(employeeCode);
      }
      if (value !== null && typeof value == "string") {
        this.itldisUserForm.get("employeeCode").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });

    this.itldisUserForm.get('employeeName').valueChanges.subscribe(value => {
      if (value) {
        let employeeCode = typeof value == "object" ? value.employeeName : value;
        this.autoEmployeeCode(employeeCode);
      }
      if (value !== null && typeof value == "string") {
        this.itldisUserForm.get("employeeName").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });
  }


  autoEmployeeCode(autoEmployeeCode) {
    let searchparam='N'
    if (autoEmployeeCode) {
      this.searchitldisUser.getEmployeeCodeDropDown(autoEmployeeCode,searchparam).subscribe(res => {
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
      this.searchitldisUser.getEmployeeCodeDropDown(autoEmployeeName,searchparam).subscribe(res => {
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
    const fltUserObj = this.itldisUserForm.value as EmployeeSearchFilter
    this.page = 0;
    this.size = 10;
    fltUserObj.page = this.page;
    fltUserObj.size = this.size;
    fltUserObj.employeeCode = this.itldisUserForm.value.employeeCode ? this.itldisUserForm.value.employeeCode.employeecode : null;
    fltUserObj.employeeName = this.itldisUserForm.value.employeeName ? this.itldisUserForm.value.employeeName.employee_name : null;

    this.searchDetail();
    this.searchFilter = ObjectUtil.removeNulls(this.filterData);
    console.log('searchFilter=====>', this.filterData);
    this.searchitldisUser.getEmployeeSearch(fltUserObj).subscribe(searchRes => {
      searchRes.result.forEach(row => {
        row.action = 'edit';
      });
      this.userSearchList.emit(searchRes.result);
      this.userSearchCount.emit(searchRes.count);

    });

  }

  searchDetail() {
    if (this.itldisUserForm.get('employeeName').value != null) {
      this.filterData.employeeName = this.itldisUserForm.get('employeeName').value.employee_name;
    }
    if (this.itldisUserForm.get('employeeCode').value != null) {
      this.filterData.employeeCode = this.itldisUserForm.get('employeeCode').value.employeecode;
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
    this.itldisUserForm.reset();
    this.onClickSearch();
    this.employeeList$.result.length=0
  }

}
