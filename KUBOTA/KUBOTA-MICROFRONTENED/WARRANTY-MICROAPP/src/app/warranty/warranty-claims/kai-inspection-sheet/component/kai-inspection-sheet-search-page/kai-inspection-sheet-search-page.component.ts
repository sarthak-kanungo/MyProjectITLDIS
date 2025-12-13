import { Component, OnInit } from "@angular/core";
import { KaiInspectionSheetSearchPageWebService } from "./kai-inspection-sheet-search-page-web.service";
import { SearchInspectionSheet } from "../../domain/kai-inspection-sheet.domain";
import { ToastrService } from "ngx-toastr";
import { DataTable, ColumnSearch, NgswSearchTableService, InfoForGetPagination } from 'ngsw-search-table';
import { Router, ActivatedRoute } from '@angular/router';
import { KisSearchPageStore } from './kai-inspection-sheet-search-page.store';
import { KisSearchPagePresenter } from './kai-inspection-sheet-search-page.presenter';
import { FormGroup } from '@angular/forms';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { BehaviorSubject } from "rxjs";

@Component({
  selector: "app-kai-inspection-sheet-search-page",
  templateUrl: "./kai-inspection-sheet-search-page.component.html",
  styleUrls: ["./kai-inspection-sheet-search-page.component.scss"],
  providers: [KisSearchPageStore, KisSearchPagePresenter, KaiInspectionSheetSearchPageWebService]
})
export class KaiInspectionSheetSearchPageComponent implements OnInit {
  page = 0;
  size = 10;
  private searchDetail = {} as SearchInspectionSheet;
  searchForm: FormGroup;
  actionButtons = [];
  dataTable: DataTable;
  searchValue: ColumnSearch;
  totalTableElements: number;
  searchFilter: any;
  searchFlag:boolean=true
  pageLoadCount:number=0
  clearSearchRow = new BehaviorSubject<string>("");
  constructor(
    private kaiInspectionSheetSearchPageWebService: KaiInspectionSheetSearchPageWebService,
    private kisSearchPagePresenter: KisSearchPagePresenter,
    private tostr: ToastrService,
    private tableDataService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private iFrameService: IFrameService
  ) {}

  ngOnInit() {
    this.searchForm = this.kisSearchPagePresenter.kisSearchForm;
    this.searchDetail.page = this.page;
    this.searchDetail.size = this.size;
    this.searchKaiInspectionSheet(this.searchDetail);
  }

  private searchKaiInspectionSheet(searchDetail: SearchInspectionSheet) {
    this.kaiInspectionSheetSearchPageWebService.searchKaiInspectionSheet(searchDetail)
    .subscribe(
      res => {
        console.log("res_searchDetail", res);
        this.dataTable = this.tableDataService.convertIntoDataTable(res.result);
        this.totalTableElements = res.count;
      },
      err => {
        this.tostr.error("Error while fetching details", "Error");
      }
    );
  }

  tableAction(event: object) {
    if (event && event['btnAction'] && (event['btnAction'].toLowerCase() === 'inspectionno')) {
     this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { inspectionNo: event['record']['inspectionNo'], wcrNo: event['record']['wcrNo']} });
   } else if (event && event['btnAction'] && (event['btnAction'].toLowerCase() === 'action')) {
    this.router.navigate(['../approve'], { relativeTo: this.activatedRoute, queryParams: { inspectionNo: event['record']['inspectionNo'], wcrNo: event['record']['wcrNo']} });
  }
 }
 pageSizeChange(event: InfoForGetPagination) {
   
     this.page = event['page'];
     this.size = event['size'];
     this.searchFlag=false;
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
   this.searchDetail = this.searchForm.value;
   
   if(this.dataTable){
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
   this.searchFlag = true;
   this.searchDetail.page = this.page;
   this.searchDetail.size = this.size;
   this.searchDetail.toDate = this.searchForm.get('toDate').value?ObjectUtil.convertDate(this.searchForm.get('toDate').value):null;
   this.searchDetail.fromDate = this.searchForm.get('fromDate').value?ObjectUtil.convertDate(this.searchForm.get('fromDate').value):null;
   this.searchDetail = ObjectUtil.removeNulls(this.searchDetail);

   this.searchKaiInspectionSheet(this.searchDetail);
 }
 clear() {
   this.searchForm.reset();
   this.dataTable = null
  //  this.searchDetail = this.searchForm.getRawValue();
  //  this.searchDetail.page = 0;
  //  this.searchDetail.size = this.size; 
  //  this.searchDetail =  ObjectUtil.removeNulls(this.searchDetail);
  //  this.searchKaiInspectionSheet(this.searchDetail);
   this.clearSearchRow.next("");
 }
 /**
  * ----------Following is state management code------------
  */

 initialQueryParams(event){
  console.log('initialQueryParams event: ', event);
  this.searchForm.patchValue(event);
  if (event.fromDate) {
  this.searchForm.get('fromDate').patchValue(new Date(event.fromDate));
  }
  if (event.toDate) {
  this.searchForm.get('toDate').patchValue(new Date(event.toDate))
  }
  }

 onUrlChange(event) {
  console.log('onUrlChange event: ', event);
  if (!event) {
  return;
  }
  const {queryParam=null, url=''} = {...event};
  this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.WARRANTY, { url, queryParam } as UrlSegment);
  }


}
