import { AfterViewInit, Component, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { itldisUserSearchPageStore } from './itldis-user-search-page.store';
import { itldisUserSearchPagePresenter } from './itldis-user-search-page-presenter';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeSearchFilter } from '../../domain/itldis-user-domain';
import { SearchitldisUserService } from '../search-itldis-user/search-itldis-user.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { EventEmitter } from 'events';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-itldis-user-search-page',
  templateUrl: './itldis-user-search-page.component.html',
  styleUrls: ['./itldis-user-search-page.component.scss'],
  providers: [itldisUserSearchPagePresenter, itldisUserSearchPageStore, NgswSearchTableService, SearchitldisUserService]
})
export class itldisUserSearchPageComponent implements OnInit, AfterViewInit {

  searchitldisUserForm: FormGroup
  itldisUserForm: FormGroup
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
    private itldisUserSearchPagePresenter: itldisUserSearchPagePresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private actRt: ActivatedRoute,
    private searchitldisUser: SearchitldisUserService,
  ) { }

  ngOnInit() {
    this.searchitldisUserForm = this.itldisUserSearchPagePresenter.searchitldisUserForm;
    this.itldisUserForm = this.itldisUserSearchPagePresenter.itldisUserForm;
  }
  ngAfterViewInit() {
    this.searchitldisUserMaster();
    // this.searchData()
  }
  searchitldisUserMaster() {
    console.log('search check----------------')
    const searchFormValues = this.searchitldisUserForm.value
    console.log('searchFormValues--', searchFormValues);
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.searchitldisUserForm.value))
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

    const temp = this.searchitldisUserForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.searchitldisUser.getEmployeeSearch(searchFormValues).subscribe(res => {
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
    this.searchitldisUserMaster()
    // this.searchUserMaster.page = event.page;
    // this.searchUserMaster.size = event.size;

    // this.searchitldisUser.getEmployeeSearch(this.searchUserMaster).subscribe(searchRes => {
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
