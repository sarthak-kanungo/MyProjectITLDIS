import { AfterViewInit, Component, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { KubotaUserSearchPageStore } from './kubota-user-search-page.store';
import { KubotaUserSearchPagePresenter } from './kubota-user-search-page-presenter';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeSearchFilter } from '../../domain/kubota-user-domain';
import { SearchKubotaUserService } from '../search-kubota-user/search-kubota-user.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { EventEmitter } from 'events';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-kubota-user-search-page',
  templateUrl: './kubota-user-search-page.component.html',
  styleUrls: ['./kubota-user-search-page.component.scss'],
  providers: [KubotaUserSearchPagePresenter, KubotaUserSearchPageStore, NgswSearchTableService, SearchKubotaUserService]
})
export class KubotaUserSearchPageComponent implements OnInit, AfterViewInit {

  searchKubotaUserForm: FormGroup
  kubotaUserForm: FormGroup
  searchUserMaster = {} as EmployeeSearchFilter;
  actionButtons = []
  dataTable: DataTable;
  totalTableRecords: number;
  page: number = 0;
  size: number = 10;
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  searchFilterValues: any;
  public totalTableElements: number;
  public searchFilter: any;
  actionNg: any
  employeeCodeNg: any
  employeeNameNg: any
  employeeStatusNg: any
  loginIdStatusNg: any
  constructor(
    private kubotaUserSearchPagePresenter: KubotaUserSearchPagePresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private actRt: ActivatedRoute,
    private searchKubotaUser: SearchKubotaUserService,
  ) { }

  ngOnInit() {
    this.searchKubotaUserForm = this.kubotaUserSearchPagePresenter.searchKubotaUserForm;
    this.kubotaUserForm = this.kubotaUserSearchPagePresenter.kubotaUserForm;
  }
  ngAfterViewInit() {
    this.searchKubotaUserMaster();
    // this.searchData()
  }
  searchKubotaUserMaster() {
    console.log('search check----------------')
    const searchFormValues = this.searchKubotaUserForm.value
    console.log('searchFormValues--', searchFormValues);
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.searchKubotaUserForm.value))
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

    const temp = this.searchKubotaUserForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.searchKubotaUser.getEmployeeSearch(searchFormValues).subscribe(res => {
      console.log('searchRes=====>', res);
      // .forEach(element => {
      // element.edit='edit'
      // });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
    })
  }
  pageChange(event) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    // this.searchDetail();
    this.searchKubotaUserMaster()
    // this.searchUserMaster.page = event.page;
    // this.searchUserMaster.size = event.size;

    // this.searchKubotaUser.getEmployeeSearch(this.searchUserMaster).subscribe(searchRes => {
    //   searchRes.result.forEach(row => {
    //     row.action = 'edit';
    //   });  
    //   this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes.result);
    //   this.totalTableRecords = searchRes.count;
    // })
    // commented By Kanhaiya
  }
  tableClear() {
    this.actionNg = ""
    this.employeeCodeNg = ""
    this.employeeNameNg = ""
    this.employeeStatusNg = ""
    this.loginIdStatusNg = ""
  }
  

  getDatatableResult(data) {
    this.dataTable = this.ngswSearchTableService.convertIntoDataTable(data);
    this.tableClear()
  }

  getSearchCount(count) {
    this.totalTableRecords = count;
  }

  actionOnTableRecord(clickData) {
    if (clickData['btnAction'].toLocaleLowerCase() === 'action') {
      this.router.navigate(['edit', clickData.record.employeeCode], { relativeTo: this.actRt, queryParams: {} });
    } else {
      this.router.navigate(['view', clickData.record.employeeCode], { relativeTo: this.actRt, queryParams: {} });
    }
  }
}
