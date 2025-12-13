import { Component, OnInit } from '@angular/core';
import { GoodwillSearch } from '../../domain/goodwill.domain';
import { GoodwillSearchPageWebService } from './goodwill-search-page-web.service';
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { GoodwillSearchPageStore } from './goodwill-search-page.store';
import { GoodwillSearchPagePresenter } from './goodwill-search-page.presenter';
import { FormControl, FormGroup } from '@angular/forms';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-goodwill-search-page',
  templateUrl: './goodwill-search-page.component.html',
  styleUrls: ['./goodwill-search-page.component.scss'],
  providers: [GoodwillSearchPageWebService, GoodwillSearchPageStore, GoodwillSearchPagePresenter]
})
export class GoodwillSearchPageComponent implements OnInit {
  private page = 0;
  private size = 10;
  private goodwillSearch = {} as GoodwillSearch;
  searchForm: FormGroup;
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  dealerCode: string
  searchFlag: boolean = true
  clearSearchRow = new BehaviorSubject<string>("");
  toDate: Date;
  toDate1: Date;
  toDate2: Date;
  newdate = new Date()
  key = 'gsp';
  searchFilterValues: any;
  pageLoadCount:number=0
  searchFilter: any;
  constructor(
    private goodwillSearchPageWebService: GoodwillSearchPageWebService,
    private goodwillSearchPagePresenter: GoodwillSearchPagePresenter,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService
  ) { }

  ngOnInit() {

    this.searchFilterValues = localStorage.getItem(this.key)
    this.searchForm = this.goodwillSearchPagePresenter.goodwillSearchForm;
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.searchForm.patchValue(this.searchFilterValues)
    }
    this.dealerCode = this.goodwillSearchPagePresenter.loginUser.dealerCode;
   
    if (this.searchForm.get('goodwillNo').value == null && this.searchForm.get('goodwillType').value == null && this.searchForm.get('goodwillStatus').value == null && this.searchForm.get('jobCardNo').value == null && this.searchForm.get('pcrNo').value == null && this.searchForm.get('chassisNo').value == null && this.searchForm.get('goodwillFromDate').value == null && this.searchForm.get('goodwillToDate').value == null) {
      this.toDate2 = this.newdate
      this.searchForm.get('goodwillToDate').patchValue(this.newdate);
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.toDate1 = backDate;
      this.searchForm.get('goodwillFromDate').patchValue(backDate);
      this.search();
    } else {
      localStorage.getItem(this.key)
      this.search();
    }

  }
  ngAfterViewInit() {
    this.searchForm.valueChanges.subscribe(val => {
      this.goodwillSearch = val;
    })


    //this.searchWarrantyPcr(this.warrantyPcr);
  }
// Commented by kanhaiya
  // searchDetail() {
  //   localStorage.setItem(this.key, JSON.stringify(this.searchForm.value))
  //   if (this.searchForm.get('pcrNo').value != null) {
  //     this.goodwillSearch.pcrNo = this.searchForm.get('pcrNo').value.code;
  //   }
  //   if (this.searchForm.get('jobCardNo').value != null) {
  //     this.goodwillSearch.jobCardNo = this.searchForm.get('jobCardNo').value.code;
  //   }
  //   if (this.searchForm.get('chassisNo').value != null) {
  //     this.goodwillSearch.chassisNo = this.searchForm.get('chassisNo').value.code;
  //   }
  //   if (this.searchForm.get('machineModel').value != null) {
  //     this.goodwillSearch.machineModel = this.searchForm.get('machineModel').value;
  //   }
  //   if (this.searchForm.get('registrationNo').value != null) {
  //     this.goodwillSearch.registrationNo = this.searchForm.get('registrationNo').value;
  //   }
  //   if (this.searchForm.get('goodwillNo').value != null) {
  //     this.goodwillSearch.goodwillNo = this.searchForm.get('goodwillNo').value.code;
  //   }
  //   if (this.searchForm.get('failureType').value != null) {
  //     this.goodwillSearch.failureType = this.searchForm.get('failureType').value;
  //   }
  //   if (this.searchForm.get('goodwillType').value != null) {
  //     this.goodwillSearch.goodwillType = this.searchForm.get('goodwillType').value;
  //   }
  //   if (this.searchForm.get('goodwillStatus').value != null) {
  //     this.goodwillSearch.status = this.searchForm.get('goodwillStatus').value;
  //   }
  //   if (this.searchForm.get('mobileNo').value != null) {
  //     this.goodwillSearch.mobileNo = this.searchForm.get('mobileNo').value;
  //   }
  //   this.goodwillSearch.goodwillToDate = ObjectUtil.convertDate(this.searchForm.get('goodwillToDate').value);
  //   this.goodwillSearch.goodwillFromDate = ObjectUtil.convertDate(this.searchForm.get('goodwillFromDate').value)
  //   this.goodwillSearch.jobCardToDate = ObjectUtil.convertDate(this.searchForm.get('jobCardToDate').value)
  //   this.goodwillSearch.jobCardFromDate = ObjectUtil.convertDate(this.searchForm.get('jobCardFromDate').value)

  //   if (this.dataTable != undefined) {
  //     if (this.searchFlag == true) {
  //       this.page = 0;
  //       this.size = 10;
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //     else {
  //       this.dataTable['PageIndex'] = this.page
  //       this.dataTable['PageSize'] = this.size
  //     }
  //   }

  //   this.goodwillSearch.page = this.page;
  //   this.goodwillSearch.size = this.size;

  //   this.searchFlag = true;

  // }


  private searchWarrantyGoodwill(goodwillSearch: GoodwillSearch) {
    this.goodwillSearchPageWebService.searchWarrantyGoodwill(goodwillSearch).subscribe(
      res => {
        this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
        this.totalTableElements = res.count;
      },
      err => {
        console.log('err', err);
      }
    );
  }
  tableAction(event: object) {
    let goodwillno = btoa(event['record']['goodwillNo'])
    if (event && event['btnAction'] && (event['btnAction'].toLowerCase() === 'goodwillno' || event['btnAction'].toLowerCase() === 'approve')) {
      this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { goodwillNo: goodwillno, name: event['btnAction'] } });
    }
  }
  pageSizeChange(event: InfoForGetPagination) {

    this.page = event['page'];
    this.size = event['size'];
    this.searchFlag = false;
    if(this.pageLoadCount > 0){
      this.search();
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
    const filterObj = this.searchForm.value as GoodwillSearch
    let key = 'searchFilter';
    localStorage.setItem(this.key, JSON.stringify(this.searchForm.value))

    
    filterObj.goodwillNo = filterObj ? filterObj.goodwillNo : null
    filterObj.goodwillFromDate = filterObj.goodwillFromDate ? ObjectUtil.convertDate(filterObj.goodwillFromDate) : null
    filterObj.goodwillToDate = filterObj.goodwillToDate ? ObjectUtil.convertDate(filterObj.goodwillToDate) : null
    this.searchFilter = ObjectUtil.removeNulls(filterObj);
    if (this.checkValidDatesInput()) {
      
      // if (this.searchFlag == true && this.searchForm.get('goodwillNo').value || this.searchForm.get('goodwillType').value || this.searchForm.get('goodwillStatus').value || this.searchForm.get('jobCardNo').value || this.searchForm.get('pcrNo').value || this.searchForm.get('chassisNo').value || this.searchForm.get('goodwillFromDate').value || this.searchForm.get('goodwillToDate').value) {

      //   this.searchWarrantyGoodwill(filterObj);
      // }
      // else if (this.searchForm.get('goodwillFromDate').value == null && this.searchForm.get('goodwillToDate').value == null) {
      //   this.tostr.error("Please fill atleast one input field.");
      // }
      delete filterObj.page;
      delete filterObj.size;

      if (Object.keys(filterObj).length>0) {

        localStorage.setItem(this.key, JSON.stringify(this.searchFilter))
  
        filterObj.page = this.page
        filterObj.size = this.size
  
        if(filterObj.dealerShip && typeof filterObj.dealerShip == 'object'){
          filterObj.dealerId = this.searchFilter.dealerShip.id;
          delete filterObj.dealerShip;
        }
        this.searchWarrantyGoodwill(filterObj);
      
      } else{
        this.tostr.error("Please fill atleast one input field.");
      }
      
    } else {
      this.tostr.error("Please Select Date Range.");
    }
  }
  checkValidDatesInput() {
    const fltEnqObj = this.searchForm.value

    fltEnqObj.fromDate = this.searchForm.getRawValue() ? this.searchForm.value.goodwillFromDate : null
    fltEnqObj.toDate = this.searchForm.getRawValue() ? this.searchForm.value.goodwillToDate : null

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
    this.dataTable = null
    //  this.goodwillSearch = this.searchForm.getRawValue();
    //  this.goodwillSearch.page = 0;
    //  this.goodwillSearch.size = this.size; 
    //  this.goodwillSearch =  ObjectUtil.removeNulls(this.goodwillSearch);
    //  this.searchWarrantyGoodwill(this.goodwillSearch);
    this.clearSearchRow.next("");
    localStorage.removeItem(this.key)
  }

}
