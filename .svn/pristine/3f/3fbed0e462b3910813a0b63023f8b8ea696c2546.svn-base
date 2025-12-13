import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SearchAssignUserToBranchPagePresenter } from './search-assign-user-to-branch-page-presenter';
import { SearchAssignUserToBranchPageStore } from './search-assign-user-to-branch-page-store';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { ObjectUtil } from 'src/app/utils/object-util';
import { SearchAssignUserToBranchService } from './search-assign-user-to-branch.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-search-assign-user-to-branch',
  templateUrl: './search-assign-user-to-branch.component.html',
  styleUrls: ['./search-assign-user-to-branch.component.scss'],
  providers: [SearchAssignUserToBranchPageStore, SearchAssignUserToBranchPagePresenter]
})
export class SearchAssignUserToBranchComponent implements OnInit, AfterViewInit {
  assignuserToBranchForm: FormGroup;
  userToBranchForm: FormGroup
  searchFilter;
  public dataTable: DataTable;
  public totalTableElements: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  searchFlag: boolean = true;
  searchFilterValues: any;
  recordData: any
  page = 0;
  size = 10
  clearSearchRow = new BehaviorSubject<string>("");

  employeeNameNgModel: any = ''
  branchNameNgModel: any = ''
  isActiveNgModel: any = ''


  constructor(private pagePresenter: SearchAssignUserToBranchPagePresenter,
    private SearchService: SearchAssignUserToBranchService,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,) { }

  ngOnInit() {
    this.searchFilterValues = localStorage.getItem('searchFilter')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.assignuserToBranchForm = this.pagePresenter.searchAssignUserToBranchForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.assignuserToBranchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    this.assignuserToBranchForm = this.pagePresenter.searchAssignUserToBranchForm
    this.userToBranchForm = this.pagePresenter.userToBranchForm

  }
  ngAfterViewInit() {
    this.searchAssignUserToBranch()

  }


  searchAssignUserToBranch() {
    let dealershipCode: string
    if (this.userToBranchForm.get('dealership').value) {
      dealershipCode = this.assignuserToBranchForm.getRawValue().userToBranchForm.dealership.code
    }

    let userName: string
    if (this.userToBranchForm.get('userId').value) {
      userName = this.assignuserToBranchForm.getRawValue().userToBranchForm.userId.employeeName

    }


    let searchFormValues = {} as any
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.assignuserToBranchForm.value))
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

    const temp = this.assignuserToBranchForm.getRawValue().userToBranchForm
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    searchFormValues.userId = dealershipCode
    searchFormValues.dealerId = userName
    console.log('searchFormValues--', searchFormValues);
    this.SearchService.searchAssignUserToBranchTable(searchFormValues).subscribe(res => {
      console.log('result-------',res)
      res['result'].forEach(row => {
        row.edit = 'edit';
      });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
    })
  }

  tableAction(event: object) {
    console.log('event', event)
    this.recordData = event['record']
    if (event['btnAction'].toLowerCase() === 'employeename') {
      this.router.navigate(['../view'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], dealerEmployeeId: event['record']['dealerEmployeeId'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
    if (event['btnAction'].toLowerCase() === 'edit') {
      this.router.navigate(['../edit'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], dealerEmployeeId: event['record']['dealerEmployeeId'], searchFilter: JSON.stringify(this.searchFilter) }
      })
    }

  }

  initialQueryParams(event) {
    this.assignuserToBranchForm.get('userToBranchForm').patchValue(event);
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchAssignUserToBranch()
  }

  clearUserAssignToBranch() {
    this.assignuserToBranchForm.reset()
    this.searchAssignUserToBranch()
    this.tableSearchClear()
    this.clearSearchRow.next("");
  }

  tableSearchClear() {
    this.employeeNameNgModel = ''
    this.branchNameNgModel = ''
    this.isActiveNgModel = ''
  }




}
