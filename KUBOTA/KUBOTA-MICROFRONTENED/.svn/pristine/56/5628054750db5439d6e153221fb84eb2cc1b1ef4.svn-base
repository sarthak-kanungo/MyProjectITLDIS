import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { RetroFitmentCampaignSearchPageWebService } from "./retro-fitment-campaign-search-page-web.service";
import { SearchWarrantyRfc } from "../../domain/retro-fitment-campaign.domain";
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { FormGroup } from '@angular/forms';
import { RfcSearchPagePresenter } from './retro-fitment-campaign-search-page.presenter'
import { RfcSearchPageStore } from './retro-fitment-campaign-search-page.store';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from "rxjs";
import { ToastrService } from "ngx-toastr";
// import { DateService } from "src/app/root-service/date.service";

@Component({
  selector: "app-retro-fitment-campaign-search-page",
  templateUrl: "./retro-fitment-campaign-search-page.component.html",
  styleUrls: ["./retro-fitment-campaign-search-page.component.scss"],
  providers: [RfcSearchPageStore, RfcSearchPagePresenter, RetroFitmentCampaignSearchPageWebService]
})
export class RetroFitmentCampaignSearchPageComponent implements OnInit {
  private warrantyRfc = {} as SearchWarrantyRfc;
  private page = 0;
  private size = 10;
  searchForm: FormGroup;
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  clearSearchRow = new BehaviorSubject<string>("");
  newdate = new Date()
  fromDate: Date
  toDate: Date
  searchFilterValues: any;
  pageLoadCount:number=0
  key = 'retro-fit-csp';
  searchFlag: boolean = true;
  searchFilter: any;
  constructor(
    private retroFitmentCampaignSearchPageWebService: RetroFitmentCampaignSearchPageWebService,
    private tableDataService: NgswSearchTableService,
    private rfcSearchPagePresenter: RfcSearchPagePresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    // private dataservice: DateService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key)
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchForm = this.rfcSearchPagePresenter.rfcSearchForm;
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchForm.patchValue(this.searchFilterValues)
    }

    // this.warrantyRfc.page = this.page;
    // this.warrantyRfc.size = this.size;
    if (this.searchForm.get('retrofitmentNumber').value==null && this.searchForm.get('status').value==null && this.searchForm.get('fromDate').value==null && this.searchForm.get('toDate').value==null) {
      this.toDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      this.searchForm.get('fromDate').patchValue(backDate);
      this.searchForm.get('toDate').patchValue(new Date());
      this.search()
    } else {
      localStorage.getItem(this.key)
      this.search()
    }
  }

  private searchRetrofitmentCampaign(warrantyRfc: SearchWarrantyRfc) {
    this.retroFitmentCampaignSearchPageWebService.searchRetrofitmentCampaign(warrantyRfc).subscribe(res => {
      this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
      this.totalTableElements = res.count;
    }, err => {
      console.log('err', err);
    });
  }
  tableAction(event: object) {
    console.log('event_retro', event)
    if (event && event['btnAction'] && event['btnAction'].toLowerCase() === 'retrofitmentnumber') {
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { retrofitmentNo: event['record']['retrofitmentNumber'] } });
    }
  }
  pageSizeChange(ev: InfoForGetPagination) {
    console.log('pageSizeChange_retro', ev)
    this.page = ev['page'];
    this.size = ev['size'];
    this.warrantyRfc.page = this.page;
    this.warrantyRfc.size = this.size;
    if(this.pageLoadCount > 0){
      this.searchRetrofitmentCampaign(this.warrantyRfc);
    }
    this.pageLoadCount = 1;
    
  }

  etSearchDateValueChange(searchDate: string, columnKey: string) {
    let modifiedDate = null;
    if (searchDate) {
      modifiedDate = ObjectUtil.getDateIntoDDMMYYYY(searchDate);
    }
    this.searchValue = new ColumnSearch(modifiedDate, columnKey);
  }

  search() {
    let rfcNo:string
    if (this.searchForm.get('retrofitmentNumber').value) {
      rfcNo=this.searchForm.get('retrofitmentNumber').value
      
    }
    if (this.dataTable != undefined || this.dataTable != null) {
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
    else {
      this.page = 0;
      this.size = 10;
    }
    this.searchFlag = true;
    const filterObj = this.searchForm.value as SearchWarrantyRfc
    filterObj.page = this.page
    filterObj.size = this.size
    filterObj.rfcNo=rfcNo
    filterObj.fromDate = filterObj.fromDate ? ObjectUtil.convertDate(filterObj.fromDate) : null
    filterObj.toDate = filterObj.toDate ? ObjectUtil.convertDate(filterObj.toDate) : null
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchForm.value))
   
    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      if ( this.searchForm.get('retrofitmentNumber').value|| this.searchForm.get('status').value || this.searchForm.get('fromDate').value || this.searchForm.get('toDate').value) {
        this.searchRetrofitmentCampaign(filterObj);
      }
      else if (this.searchForm.get('retrofitmentNumber').value==null&&this.searchForm.get('fromDate').value == null && this.searchForm.get('toDate').value == null) {
        this.tostr.error("Please fill atleast one input field.");
      }
    } else {
      this.tostr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchForm.value

    fltEnqObj.fromDate = this.searchForm.getRawValue() ? this.searchForm.value.fromDate : null
    fltEnqObj.toDate = this.searchForm.getRawValue() ? this.searchForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }
    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }
  }
  clear() {
    this.searchForm.reset();
    this.clearSearchRow.next("");
    this.dataTable = null
    localStorage.removeItem(this.key)
  }
}
