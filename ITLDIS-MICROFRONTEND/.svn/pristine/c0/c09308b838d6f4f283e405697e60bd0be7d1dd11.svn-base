import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { RoleMasterPresenter } from '../../services/role-master-presenter';
import { NgswSearchTableService, ColumnSearch, DataTable } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-role-search-result',
  templateUrl: './role-search-result.component.html',
  styleUrls: ['./role-search-result.component.scss'],
  providers: [RoleMasterPresenter, NgswSearchTableService]
})
export class RoleSearchResultComponent implements OnInit {

  roleMasterSearchForm: FormGroup;

  @Input() dataTable: DataTable
  @Output() pageChangeValue = new EventEmitter();
  @Input() totalTableElements: number;
  @Input() actionButtons = [];
  @Input() searchFilter: any;
  @Input() clearSearchRow = new BehaviorSubject<string>("");
  @Input() searchTabledata: any
  actionNg: any
  applicableToNg: any
  roleActiveNg: any
  roleCodeNg: any
  roleNameNg: any
  clickOnTableFields: { 'title': string; 'isClickable': boolean; }[];
  public searchValue: ColumnSearch;
  public filterData: object
  constructor(private roleMasterPresenter: RoleMasterPresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,) { }

  ngOnInit() {
    this.clickOnTableFields = [{ 'title': 'roleCode', 'isClickable': true }, { 'title': 'action', 'isClickable': true }]
    this.roleMasterSearchForm = this.roleMasterPresenter.buildFormSearch();

  }

  actionOnTableRecord(event: object) {
    console.log('event_pcr', event)
    if (event && event['btnAction'] && (event['btnAction'] === 'roleCode')) {
      this.router.navigate(['../view/id'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
    if (event && event['btnAction'] && (event['btnAction'] === 'action')) {
      this.router.navigate(['../edit/id'], {
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
  }
  pageChange(event) {
    if (!!event && event.page >= 0) {
      this.pageChangeValue.emit(event)
      // this.searchFlag=false;
    }
  }
  tableClear() {
    this.actionNg = ""
    this.applicableToNg = ""
    this.roleActiveNg = ""
    this.roleCodeNg = ""
    this.roleNameNg = ""
  }
  clearForm() {
    this.roleMasterSearchForm.reset()
    this.clearSearchRow.next("");
  }
}
