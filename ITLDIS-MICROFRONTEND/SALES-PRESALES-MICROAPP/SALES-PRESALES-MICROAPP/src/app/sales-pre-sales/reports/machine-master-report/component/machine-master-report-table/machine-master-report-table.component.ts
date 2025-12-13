import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ProductInterestedV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/product-interested-v2-web.service';
import { SearchEnquiryV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/search-enquiry-v2-web.service';
import { MachineMasterReportPagePresenter } from '../machine-master-report-page/machine-master-report-page-presenter';
import { MachineMasterReportPageStore } from '../machine-master-report-page/machine-master-report-page-store';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { SalesReportService } from '../../../sales-report-service/sales-report.service';
import { ObjectUtil } from 'src/app/utils/object-util';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { DateService } from 'src/app/root-service/date.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-machine-master-report-table',
  templateUrl: './machine-master-report-table.component.html',
  styleUrls: ['./machine-master-report-table.component.css'],
  providers:[MachineMasterReportPageStore,MachineMasterReportPagePresenter,SearchEnquiryV2WebService,ProductInterestedV2WebService]
})
export class MachineMasterReportTableComponent implements OnInit {

  machineMasterReportForm:FormGroup

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
  key = "survey";
  clearSearchRow = new BehaviorSubject<string>("");
  constructor( private salesReportService:SalesReportService,
    private tableDataService: NgswSearchTableService,
    private presenter:MachineMasterReportPagePresenter,) { }

  ngOnInit() {
    this.machineMasterReportForm = this.presenter.mmrForm
  }

  searchMachineMasterReport(){
    const searchFormValues = this.machineMasterReportForm.getRawValue()  
    
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.machineMasterReportForm.value))
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
      searchFormValues['page'] == this.page;
      searchFormValues['size'] = this.size;
    }
    else {
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    const temp = this.machineMasterReportForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    console.log('vinay-----',searchFormValues);
    
      this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
          this.salesReportService.searchMachineMasterReport(searchFormValues).subscribe( res => {
              this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
              this.totalTableElements = res['count'];
            }
          );

  }
  
  initialQueryParams(event) {
    this.machineMasterReportForm.patchValue(event)
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchMachineMasterReport()
  }

  clearMachineMasterReport() {
    this.clearSearchRow.next("");
    this.salesReportService.clearFormSubject.next('clear');
    this.machineMasterReportForm.reset()
    this.dataTable=null
    localStorage.removeItem(this.key)
  }

  machineMasterExcelReport(event) {
    const searchFormValues = this.machineMasterReportForm.getRawValue();
    searchFormValues['page'] = this.page;
    searchFormValues['size'] = this.size;
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
     this.downloadExcel(searchFormValues)
  }

  downloadExcel(data){
    this.salesReportService.downloadMachineMasterReport(data).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
        let headerContentDispostion = resp.headers.get('content-disposition');
       
        let fileName = headerContentDispostion.split("=")[1].trim();
        const file = new File([resp.body], `${fileName}`, { type: 'application/vnd.ms-excel' });
        saveAs(file);
      }
    })
  }


}
