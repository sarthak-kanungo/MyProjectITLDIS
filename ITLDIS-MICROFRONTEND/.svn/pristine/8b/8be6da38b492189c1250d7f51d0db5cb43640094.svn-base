import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { SearchInvoiceService } from './search-invoice.service';
import { FormGroup } from '@angular/forms';
import { DataTable, ColumnSearch, TableHeading, NgswSearchTableService } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { InvoiceSearchForm } from '../../model/invoice-search-filter';
import { SearchInvoiceApiService } from './search-invoice-api.service';
import { SelectList } from '../../../../../core/model/select-list.model';
import { Observable } from 'rxjs';
import { SearchAllotmentDeAllotmentService } from '../../../de-allotment/component/search-allotment-de-allotment/search-allotment-de-allotment.service';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { DateService } from '../../../../../root-service/date.service';
@Component({
  selector: 'app-search-invoice',
  templateUrl: './search-invoice.component.html',
  styleUrls: ['./search-invoice.component.scss'],
  providers: [
    SearchInvoiceService,
    SearchInvoiceApiService,
    SearchAllotmentDeAllotmentService
  ]
})
export class SearchInvoiceComponent implements OnInit {

  options = false
  searchform = false
  selected = 10;
  page: number = 0;
  size: number = 10;
  searchInvoiceForm: FormGroup;
  @Input() dataTable: DataTable;
  @Input() totalSearchRecordCount: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  clickOnTableFields: Array<TableHeading> = [{ title: 'invoiceNumber', isClickable: true },{ title: 'approve', isClickable: true }];
  @Output() searchFilterChange = new EventEmitter<InvoiceSearchForm>()
  column: string;
  searchFilter: any;
  serverCurrentDate: string;
  searchFlag:boolean=true;

  constructor(
    private searchInvoiceService: SearchInvoiceService,
    private router: Router,
    private iFrameService: IFrameService,
    private activatedRoute: ActivatedRoute,
    private dateService: DateService,
    private searchInvoiceApiService: SearchInvoiceApiService
  ) { }

  ngOnInit() {
    this.getDateFromServer();
    this.searchInvoiceForm = this.searchInvoiceService.createSearchInvoice();
  }
  ngAfterViewInit(): void {
    this.searchInvoiceRecord();
  }
  private getDateFromServer() {
    this.searchInvoiceApiService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes.result) {
        this.serverCurrentDate = dateRes.result;
      }
    });
  }
  etSearchDateValueChange(searchDate, ColumnKey) {
      let modifiedDate = null;
      if (searchDate) {
        modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
      }
      this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
    }
  actionOnTableRecord(recordData) {
    if (recordData.btnAction === 'invoiceNumber') {
      this.router.navigate(['view/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
    if (recordData.btnAction === 'approve') {
      this.router.navigate(['approve/', btoa(recordData.record.id)], { relativeTo: this.activatedRoute })
    }
  }
  pageChangeOfSearchTable(event) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    this.searchInvoiceRecord();
  }
  searchTableColumnChanged(column: string) {
    this.column = column;
  }
  searchInvoiceRecord() {
    if(this.dataTable!=undefined || this.dataTable!=null){
      if(this.searchFlag==true)
      {
        this.page=0;
        this.size=10;
        this.dataTable['PageIndex']=this.page    
        this.dataTable['PageSize']=this.size
      }
      else{
        this.dataTable['PageIndex']=this.page    
        this.dataTable['PageSize']=this.size
      }
    }
    else{
      this.page=0;
      this.size=10;
    }
    this.searchFlag=true;
    const searchField = { 
      ...this.searchInvoiceForm.getRawValue(), 
      page: this.page, 
      size: this.size,
      // column: this.column
     };
    ObjectUtil.removeNulls(searchField);
    console.log('searchField: ', searchField);
    this.searchFilter = searchField;
    this.searchFilterChange.emit(searchField);
  }
  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SALE_PRESALE, { url, queryParam } as UrlSegment);
  }
  
}
