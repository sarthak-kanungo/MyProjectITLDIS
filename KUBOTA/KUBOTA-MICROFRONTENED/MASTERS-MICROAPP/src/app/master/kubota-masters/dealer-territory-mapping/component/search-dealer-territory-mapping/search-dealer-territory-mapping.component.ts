import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ColumnSearch, DataTable, NgswSearchTableService } from 'ngsw-search-table';
import { BehaviorSubject } from 'rxjs';
import { DealerTerritoryMappingService } from '../../service/dealer-territory-mapping.service';
import { ActivatedRoute, Router } from '@angular/router';
import { delay } from 'rxjs-compat/operator/delay';

@Component({
  selector: 'app-search-dealer-territory-mapping',
  templateUrl: './search-dealer-territory-mapping.component.html',
  styleUrls: ['./search-dealer-territory-mapping.component.css']
})
export class SearchDealerTerritoryMappingComponent implements OnInit {
  searchdealerMapping:FormGroup
  clearSearchRow = new BehaviorSubject<string>("");
  searchFilter;
  searchFlag: boolean = true;
  searchFilterValues: any;
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  departmentCodeNgModel:string
  departmentNameNgModel:string
  pageLoadCount:any
  dealerList: any;
  territoryList: any;
  autoSearchBranchList: any;
  territoryNoNgModel:''
  dealerNameNgModel:''
  branchNameNgModel:''
  countryNameNgModel:''
  stateNameNgModel:''
  tehsilNameNgModel:''
  constructor(
    private fb:FormBuilder,
    private service:DealerTerritoryMappingService,
    private ngswSearchTableService:NgswSearchTableService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit() {
    this.searchdealerMapping=this.fb.group({
      dealerName:[{value:null,disabled:false}],
      branchName:[{value:null,disabled:false}],
      territoryNo:[{value:null,disabled:false}]
    })
    this.formValueChanges()
  }

  searchDesignationMaster() {
    const searchFormValues = this.searchdealerMapping.value
    

    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.searchdealerMapping.value))
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
    if(searchFormValues['designation']){
      searchFormValues['designation'] = searchFormValues['designation']['designation']
    }
    
    // this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.service.searchData(searchFormValues).subscribe(res => {
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
    })
  }

  actionOnTableRecord(event){

  
    if(event.btnAction==="territoryNo"){
      this.router.navigate(['../view', btoa(event['record']['territoryNo'])], { relativeTo: this.activatedRoute });
      // this.router.navigate(['../view'], { relativeTo: this.activatedRoute, queryParams: { territoryNo: event['record']['territoryNo']} });
      return false
    }
    if(event.record.action==="Edit"){
      this.router.navigate(['../edit', btoa(event['record']['territoryNo'])], { relativeTo: this.activatedRoute });
      // this.router.navigate(['../edit'], { relativeTo: this.activatedRoute, queryParams: { territoryNo: event['record']['territoryNo']} });
    }
  }

  pageChangeOfSearchTable(event){
   
  
   this.page = event.page;
   this.size = event.size;
   this.searchFlag = false;
   if(this.pageLoadCount>0){
    this.searchDesignationMaster()
   }
   this.pageLoadCount=1
  }
  displayDealerShip(dealer){
    
    return dealer?dealer:null
  }
  private formValueChanges(){
  this.searchdealerMapping.get('dealerName').valueChanges.subscribe(res => {
    if (res) {
      this.getDealerDetails(res)
    }
  })
  this.searchdealerMapping.get('territoryNo').valueChanges.subscribe(res => {
    if (res) {
      this.getTerritoryDetails(res)
    }
  })
  this.searchdealerMapping.get('branchName').valueChanges.subscribe(res => {
    if (res) {
      this.getBranchDetails(res)
    }
  })
}

getDealerDetails(value) {
  
  this.service.getDealerLists(value).subscribe(result => {
    this.dealerList = result;
  }) 
}
getTerritoryDetails(value) {
  
  this.service.territoryNo(value).subscribe(result => {
    this.territoryList = result;
  }) 
}
getBranchDetails(value) {
  
  this.service.getAutoSerchBranch(value).subscribe(result => {
    this.autoSearchBranchList = result;
  }) 
}


displayTerritoryNo(territoryNo){
  return territoryNo?territoryNo:null

}
displayDealerBranch(branchName){
  return branchName?branchName:null
}
clearForm(){
  this.dataTable=null;
  this.searchdealerMapping.reset()
}

}
