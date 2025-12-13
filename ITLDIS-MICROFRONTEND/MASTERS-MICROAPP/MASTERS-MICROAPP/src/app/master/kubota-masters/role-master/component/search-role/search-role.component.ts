import { AfterViewInit, Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { ObjectUtil } from 'src/app/utils/object-util';
import { RoleValues, SearchRoleMaster } from '../../domains/role-master';
import { RoleMasterWebService } from '../../services/role-master-web-service';
import { IFrameService, IFrameMessageSource, UrlSegment } from 'src/app/root-service/iFrame.service';
import { DataTable, ColumnSearch, InfoForGetPagination, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { RoleSearchResultComponent } from '../role-search-result/role-search-result.component';

@Component({
  selector: 'app-search-role',
  templateUrl: './search-role.component.html',
  styleUrls: ['./search-role.component.scss'],
  providers: [RoleMasterWebService]
})
export class SearchRoleComponent implements OnInit, AfterViewInit {
  roleForm: FormGroup;
  page = 0;
  size = 10;
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  searchFilterValues: any;
  public totalTableElements: number;
  public searchFilter: any;
  clearSearchRow = new BehaviorSubject<string>("");
  role$: BaseDto<Array<RoleValues>>;
  @ViewChild('componentchild',{static: false}) childComponent: RoleSearchResultComponent;
  private searchRoleMaster = {} as SearchRoleMaster;

  applicables: object[] = [
    { id: 1, applicable: 'itldis' }, { id: 1, applicable: 'Dealer' }
  ];
  IsActive: object[] = [
    { id: 'Yes', active: 'Y' }, { id: 'No', active: 'N' }
  ];
  constructor(private fb: FormBuilder,
    private api: RoleMasterWebService,
    private iFrameService: IFrameService,
    private tableDataService: NgswSearchTableService,


  ) { }

  ngOnInit() {
    this.searchroleForm();
    this.formValueChanges();
    this.searchRoleMaster.page = this.page;
    this.searchRoleMaster.size = this.size;

    this.searchRole(this.searchRoleMaster);
   
  }
  ngAfterViewInit() {
    this.searchMasterRoleMaster();
    // this.searchData()
  }
  searchroleForm() {
    this.roleForm = this.fb.group({
      roleCode: [null, Validators.compose([])],
      roleName: [null, Validators.compose([])],
      Isactive: [null, Validators.compose([])],
      Applicablefor: [null, Validators.compose([])],
      page: [null, Validators.compose([])],
      size: [null, Validators.compose([])],
    })
  }
  ngOnChange() {
    this.searchRole(this.searchRoleMaster);
  }
  private searchRole(searchRoleMaster) {
    this.searchFilter = { ...searchRoleMaster };
    this.api.searchRoleMaster(searchRoleMaster).subscribe(
      res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
        this.totalTableElements = res['count'];
        res.result.map(((res: any) => {
          res.action = 'Edit';
        })
        )
      },
      err => {
        console.log('err', err);
      }
    );
  }
  searchMasterRoleMaster() {
    console.log('search check----------------')
    const searchFormValues = this.roleForm.value
    console.log('searchFormValues--', searchFormValues);
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.roleForm.value))
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

    const temp = this.roleForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.api.searchRoleMaster(searchFormValues).subscribe(res => {
      console.log('searchRes=====>', res);
      // .forEach(element => {
      // element.edit='edit'
      // });
      this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
    })
  }
  pageChange(ev: InfoForGetPagination) {
    console.log('pageSizeChange_pcr', ev);
    // this.searchRoleMaster.page = ev['page'];
    // this.searchRoleMaster.size = ev['size'];

    // this.roleForm.get('page').patchValue(ev['page']);
    // this.roleForm.get('size').patchValue(ev['size']);
    this.page = ev.page;
    this.size = ev.size;
    this.searchFlag = false;
    this.searchDetail();
    this.searchMasterRoleMaster()
    // this.searchFilter = ObjectUtil.removeNulls(this.searchRoleMaster);
    // console.log('this.searchFilter', this.searchFilter);
    // this.searchRole(this.searchRoleMaster);
  }
  // etSearchDateValueChange(searchDate: string, columnKey: string) {
  //   this.searchValue = new ColumnSearch(searchDate, columnKey);
  // }
  search() {
    console.log(this.roleForm.getRawValue())
    this.searchDetail();
    this.searchFilter = ObjectUtil.removeNulls(this.searchRoleMaster);
    this.searchRole(this.searchRoleMaster);
  }

  searchDetail() {
    if (this.roleForm.get('roleName').value != null) {
      this.searchRoleMaster.roleName = this.roleForm.get('roleName').value;
    }
    if (this.roleForm.get('roleCode').value != null) {
      this.searchRoleMaster.roleCode = this.roleForm.get('roleCode').value;
    }

    if (this.roleForm.get('Applicablefor').value != null) {
      this.searchRoleMaster.applicableFor = this.roleForm.get('Applicablefor').value.applicable;
    }

    if (this.roleForm.get('Isactive').value != null) {
      this.searchRoleMaster.isActive = this.roleForm.get('Isactive').value.active;
    }
  }

  clearForm() {
    this.roleForm.reset();
    this.roleForm.get('page').patchValue(this.page);
    this.roleForm.get('size').patchValue(this.size);
    this.searchRoleMaster.roleCode = null;
    this.searchRoleMaster.roleName = null;
    this.searchRoleMaster.isActive = null;
    this.searchRoleMaster.applicableFor = null;
    this.searchFilter = ObjectUtil.removeNulls(this.searchRoleMaster);
    this.searchRole(this.searchRoleMaster);
    this.clearSearchRow.next("");
    this.childComponent.tableClear()
  }

  /**
  * ----------Following is state management code------------
  */

  initialQueryParams(event: SearchRoleMaster) {
    console.log('initialQueryParams event: ', event);
    /* const searchValue = /%2F/g;
    const newValue = '/'; */
    this.roleForm.patchValue(event);
    if (event.roleCode) {
      this.roleForm.get('roleCode').patchValue({ value: event.roleCode });
    }
    if (event.roleName) {
      this.roleForm.get('roleName').patchValue({ value: event.roleName });
    }
    this.page = event.page;
    this.size = event.size;
    this.roleForm.get('page').patchValue(event.page);
    this.roleForm.get('size').patchValue(event.size);
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.MASTER, { url, queryParam } as UrlSegment);
  }

  formValueChanges() {
    this.roleForm.get('roleCode').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        console.log("roleCode---->", value)
        let roleCode = typeof value == "object" ? value.roleCode : value;
        this.autoRoleCode(roleCode);
      }else{
        this.role$ = null;
      }
      if (value !== null && typeof value !== "string") {
        this.roleForm.get("roleCode").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });
    this.roleForm.get('roleName').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        console.log("roleName---->", value)
        let roleName = typeof value == "object" ? value.roleName : value;
        this.autoRoleName(roleName);
      }else{
        this.role$ = null;
      }
      if (value !== null && typeof value !== "string") {
        this.roleForm.get("roleName").setErrors({
          selectFromList: "Please select from list",
        });
      }
    });
  }
  autoRoleCode(autoRoleCode) {
    if (autoRoleCode) {
      this.api.getRoleCode(autoRoleCode,'Search').subscribe(response => {
        this.role$ = response as BaseDto<Array<RoleValues>>
      })
    }
  }
  displayFnForRoleCode(selectedOption: RoleValues) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['roleCode'] : undefined;
  }

  autoRoleName(autoRoleName) {
    if (autoRoleName) {
      this.api.getRoleCode(autoRoleName,'Search').subscribe(response => {
        this.role$ = response as BaseDto<Array<RoleValues>>
      })
    }
  }
  displayFnForRoleName(selectedOption: RoleValues) {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['roleName'] : undefined;
  }
}
