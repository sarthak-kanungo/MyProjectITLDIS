import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { StoreSearchPageWebService } from './store-search-page-web.service';
import { FormSearchPageStore } from './form-search-page.store';
import { StoreSearchPagePresenter } from './store-search-page-presenter';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-store-search-page',
  templateUrl: './store-search-page.component.html',
  styleUrls: ['./store-search-page.component.scss'],
  providers: [StoreSearchPageWebService, FormSearchPageStore, StoreSearchPagePresenter]
})
export class StoreSearchPageComponent implements OnInit {

  searchForm: FormGroup
  searchStoreForm: FormGroup
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number
  page: number = 0
  size: number = 10

  constructor(
    private router: Router,
    private tableDataService: NgswSearchTableService,
    private storeSearchPageWebService: StoreSearchPageWebService,
    private storeSearchPagePresenter: StoreSearchPagePresenter
  ) { }

  ngOnInit() {
    this.searchForm = this.storeSearchPagePresenter.storeSearchForm
    this.searchStoreForm = this.storeSearchPagePresenter.searchForm
    this.onClickSearch()
  }

  onClickSearch() {
    this.storeSearchPageWebService.getStoreList(this.page, this.size).subscribe(response => {
      this.dataTable = this.tableDataService.convertIntoDataTable(response['response'])
    })
  }

  onClickClear() {
    this.searchForm.reset()
    this.onClickSearch()
  }
  actionOnTableRecord(recordData){}

  pageChange(event) {
    this.page = event.page
    this.size = event.size
    this.onClickSearch()
  }

  navigateToCreateStore() {
    this.router.navigate(['/master/spare-masters/store-master/create'])
  }

}