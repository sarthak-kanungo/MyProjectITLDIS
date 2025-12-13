import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { SubmitDto } from 'src/app/master/dealer-masters/dealer-employee-master/domain/dealer-employee-domain';
import { ObjectUtil } from 'src/app/utils/object-util';
import { AssignOrgHierarchyToDealerSearchPagePresenter } from './assign-org-hierarchy-to-dealer-search-page.presenter';
import { AssignOrgHierarchyToDealerSearchPageStore } from './assign-org-hierarchy-to-dealer-search-page.store';
import { SearchAssignOrgHierarchyToDealerService } from './search-assign-org-hierarchy-to-dealer.service';

@Component({
  selector: 'app-search-assign-org-hierarchy-to-dealer',
  templateUrl: './search-assign-org-hierarchy-to-dealer.component.html',
  styleUrls: ['./search-assign-org-hierarchy-to-dealer.component.scss'],
  providers: [SearchAssignOrgHierarchyToDealerService, AssignOrgHierarchyToDealerSearchPagePresenter,
    AssignOrgHierarchyToDealerSearchPageStore]
})
export class SearchAssignOrgHierarchyToDealerComponent implements OnInit {

  assignOrgHierarchyToDealerSearchForm: FormGroup
  assignOrgHierarchyToDealerForm: FormGroup
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
  newResult: any = {}
  newResultFinal: any = []
  dealerCodeNg: any
  dealerNameNg: any
  departmentNameNg: any
  hierarchyCodeNg: any
  hierarchyDescNg: any
  constructor(
    private fb: FormBuilder,
    private assignOrgHierarchyToDealerSearchPagePresenter: AssignOrgHierarchyToDealerSearchPagePresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private searchAssignOrgHierarchyToDealerService: SearchAssignOrgHierarchyToDealerService
  ) { }

  ngOnInit() {
    this.assignOrgHierarchyToDealerSearchForm = this.assignOrgHierarchyToDealerSearchPagePresenter.assignOrgHierarchyToDealerSearchForm
    this.assignOrgHierarchyToDealerForm = this.assignOrgHierarchyToDealerSearchPagePresenter.assignOrgHierarchyToDealerForm
    this.assignOrgHierarchyToDealerSearchPagePresenter.assignOrgHierarchyToDealerForm.controls.dealerId.patchValue(null)
    this.searchFilterValues = localStorage.getItem('searchFilter')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.assignOrgHierarchyToDealerSearchForm = this.assignOrgHierarchyToDealerSearchPagePresenter.assignOrgHierarchyToDealerSearchForm
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.assignOrgHierarchyToDealerSearchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
  }
  ngAfterViewInit() {
    this.searchAssignOrgHierarchyToDealerForTable();
  }

  searchAssignOrgHierarchyToDealerForTable() {

    const searchFormValues1 = this.assignOrgHierarchyToDealerSearchForm.getRawValue()
    console.log('searchFormValues--', searchFormValues1);
    let searchBasedOn = {} as any
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.assignOrgHierarchyToDealerSearchForm.value))
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
      searchBasedOn['page'] == this.page;
      searchBasedOn['size'] = this.size;
    }
    else {
      searchBasedOn['page'] = this.page;
      searchBasedOn['size'] = this.size;
    }
    const temp = this.assignOrgHierarchyToDealerSearchForm.getRawValue().assignOrgHierarchyToDealerForm
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues1);

    if (searchFormValues1.assignOrgHierarchyToDealerForm.dealerId != null &&
      searchFormValues1.assignOrgHierarchyToDealerForm.dealerId != undefined) {
      searchBasedOn.dealerId = searchFormValues1.assignOrgHierarchyToDealerForm.dealerId.id;
    }
    else {
      searchBasedOn.dealerId = searchFormValues1.assignOrgHierarchyToDealerForm.dealerId;
    }
    searchBasedOn.departmentId = searchFormValues1.assignOrgHierarchyToDealerForm.departmentId;
    this.searchAssignOrgHierarchyToDealerService.searchDealerTable(searchBasedOn).subscribe(res => {

      this.newResultFinal = [];
      for (let i = 0; i < res['result'].length; i++) {
        this.newResult = {}
        let valueNew = res['result'][i]
        this.newResult['action'] = valueNew.action
        this.newResult['dealerCode'] = valueNew.dealerCode
        this.newResult['dealerName'] = valueNew.dealerName
        this.newResult['departmentName'] = valueNew.departmentName
        this.newResult['hierarchyCode'] = valueNew.hierarchyCode
        this.newResult['hierarchyDesc'] = valueNew.hierarchyDesc
        this.newResult['id'] = valueNew.id
        this.newResultFinal.push(this.newResult)
      }
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(this.newResultFinal);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
      this.newResultFinal.map(((res: any) => {
        res.action = 'Edit';
      })
      )
    })
  }


  tableAction(event: object) {
    console.log('event', event)
    this.recordData = event['record']
    if (event && event['btnAction'] && (event['btnAction'] === 'dealerCode')) {
      this.router.navigate(['../view/id'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
        //relativeTo: this.activatedRoute, queryParams: { id: this.recordData, hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
    if (event && event['btnAction'] && (event['btnAction'] === 'action')) {
      this.router.navigate(['../edit/id'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], searchFilter: JSON.stringify(this.searchFilter) }
      })
    }


  }

  initialQueryParams(event) {
    this.assignOrgHierarchyToDealerSearchForm.get('assignOrgHierarchyToDealerForm').patchValue(event);
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchAssignOrgHierarchyToDealerForTable()
  }
tableClear(){
  this.dealerCodeNg=""
  this.dealerNameNg=""
  this.departmentNameNg=""
  this.hierarchyCodeNg=""
  this.hierarchyDescNg=""
}
  clearAssignOrgHierarchyToDealerForm() {
    this.assignOrgHierarchyToDealerSearchForm.reset()
    this.searchFlag = true;
    this.searchAssignOrgHierarchyToDealerForTable()
    this.tableClear()
  }


}
