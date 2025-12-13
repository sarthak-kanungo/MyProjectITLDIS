import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DataTable } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { DateService } from '../../../../../root-service/date.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { CurrentStockPagePresenter } from './current-stock-page-presenter';
import { CurrentStockService } from '../search-current-stock/current-stock.service';
import { CurrentStockPageStore } from './current-stock-page-store';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
@Component({
  selector: 'app-current-stock-search-page',
  templateUrl: './current-stock-search-page.component.html',
  styleUrls: ['./current-stock-search-page.component.scss'],
  providers: [CurrentStockPagePresenter, CurrentStockService, CurrentStockPageStore]
})
export class CurrentStockSearchPageComponent implements OnInit {

  currentStockSearchResultForm: FormGroup
  currentStockSearch: FormGroup
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchFlag: boolean = true;
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  public filterData: object;
  searchFilterValues: any;
  downloadFlag: boolean = false;
  clearSearchRow = new BehaviorSubject<string>("");

  constructor(
    private presenter: CurrentStockPagePresenter,
    public currentStockService: CurrentStockService,
    private ngswSearchTableService: NgswSearchTableService,
    private dateService: DateService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private iFrameService: IFrameService,
    private toastr: ToastrService,
  ) { }

  ngAfterViewInit() {
    // this.searchData();
  }
  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem('currentStock');
    }
    this.searchFilterValues = localStorage.getItem('currentStock')
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.currentStockSearchResultForm = this.presenter.currentStockSearch
    this.currentStockSearch = this.presenter.currentStockSearch;
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.currentStockSearchResultForm.patchValue(this.searchFilterValues)
    }
    // localStorage.removeItem('currentStock');

  }
  clearForm() {
    this.currentStockSearchResultForm.reset();
    this.clearSearchRow.next("");
    localStorage.removeItem('currentStock');
  }
  searchData() {
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
    let key = 'currentStock';
    localStorage.setItem(key, JSON.stringify(this.currentStockSearchResultForm.value))
    let sendSearchValue = {} as any;
    sendSearchValue.itemNo = (this.currentStockSearchResultForm.get('itemNo').value != null && typeof this.currentStockSearch.get('itemNo').value === 'object' ? this.currentStockSearch.get('itemNo').value.item_no : this.currentStockSearch.get('itemNo').value),
      sendSearchValue.page = this.page,
      sendSearchValue.size = this.size

    if (this.downloadFlag == false) {
      if (sendSearchValue.itemNo == undefined || sendSearchValue.itemNo == null) {
        this.toastr.error(" Please fill item no. ", 'Error');
        return;
      }
    }
    sendSearchValue = this.removeNullFromObjectAndFormatDate(sendSearchValue);

    this.setSearchResultTable({
      ...sendSearchValue
    });
  }
  initialQueryParams(event) {
    console.log('initialQueryParams event: ', event);
    const searchValue = /%2F/g;
    const newValue = '/';
    this.page = event.page
    this.size = event.size
    this.currentStockSearchResultForm.patchValue(event);
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SPARE, { url, queryParam } as UrlSegment);
  }
  private setSearchResultTable(searchValue: any) {
    this.currentStockService.searchCurrentStock(searchValue).subscribe(searchRes => {
      console.log('searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }
  pageChangeOfSearchTable(event: any) {
    // console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false
    this.searchData();
  }
  actionOnTableRecord(recordData: any) {
    if (recordData.btnAction.toLowerCase() === 'print') {

      this.viewPdf('true', recordData.record.transferNumber);
    }
  }
  public removeNullFromObjectAndFormatDate(searchObject: object) {
    console.log("searchObject ", searchObject);
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
      });
      console.log("searchObject ", searchObject);
      return searchObject;
    }
  }
  downloadExcelBasedItemNo(event) {
    const formValues = this.currentStockSearch.getRawValue();
    let sendSearchValue = {} as any;
    sendSearchValue.page = 0;
    sendSearchValue.itemNo = (this.currentStockSearch.get('itemNo').value != null
      && typeof this.currentStockSearch.get('itemNo').value === 'object' ?
      this.currentStockSearch.get('itemNo').value.itemNo :
      this.currentStockSearch.get('itemNo').value);

    this.downloadExcelReport(sendSearchValue);
  }
  downloadExcel(event) {
    this.downloadFlag = true;
    const formValues = this.currentStockSearch.getRawValue();
    let sendSearchValue = {} as any;
    sendSearchValue.page = 0;
    sendSearchValue.itemNo = null;

    this.downloadExcelReport(sendSearchValue);
  }


  private downloadExcelReport(searchObject) {
    this.currentStockService.downloadExcelReport(searchObject).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        this.downloadFlag = false;
        let headerContentDispostion = resp.headers.get('Content-Disposition');
        console.log('downloadExcelReport-->', headerContentDispostion)
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }
  viewPdf(printStatus: string, transferNo: string) {
    //     this.btBtSearchPageWebService.printPO(printStatus,transferNo).subscribe((resp: HttpResponse<Blob>) => {
    //         if (resp) {
    //             let headerContentDispostion = resp.headers.get('content-disposition');
    //             let fileName = headerContentDispostion.split("=")[1].trim();
    //             const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
    //             saveAs(file);
    //           }
    //      })
  }
}

