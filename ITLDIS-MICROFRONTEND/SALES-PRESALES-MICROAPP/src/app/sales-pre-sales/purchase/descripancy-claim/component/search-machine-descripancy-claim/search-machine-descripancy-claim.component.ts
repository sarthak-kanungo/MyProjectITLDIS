import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SearchMachineDescripancyCiaimService } from './search-machine-descripancy-ciaim.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FilterSearchMachineDescripancyClaim, AutoComplateClaimNoDomain, DropDownClaimStatus } from 'SearchMachineDescripancyClaim';
import { BaseDto } from 'BaseDto';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';

@Component({
  selector: 'app-search-machine-descripancy-claim',
  templateUrl: './search-machine-descripancy-claim.component.html',
  styleUrls: ['./search-machine-descripancy-claim.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SearchMachineDescripancyCiaimService]
})
export class SearchMachineDescripancyClaimComponent implements OnInit {

  @Input() machineDescripancyClaimListForm: FormGroup;
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  minDate: Date;
  maxDate: Date
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size : number = 10
  clearSearchRow = new BehaviorSubject<string>("");
  searchValue: ColumnSearch = {} as ColumnSearch
  autoComplateClaimNoDomain : BaseDto<Array<AutoComplateClaimNoDomain>>
  dropDownClaimStatus : BaseDto<Array<DropDownClaimStatus>>
  userType:any
  constructor(
    private searchMachineDescripancyCiaimService: SearchMachineDescripancyCiaimService,
    private router: Router,
    private tableDataService:NgswSearchTableService,
    private claimRt: ActivatedRoute,
    private dateservice: DateService,
    private loginServie: LoginFormService
    ) { 
      this.userType = loginServie.getLoginUserType();
    }
    searchFlag:boolean=true;

    claimIdNgModel:any="";
    claimNumberNgModel:any="";
    claimDateNgModel:any="";
    claimStatusNgModel:any="";
    totalClaimQuantityNgModel:any="";
    totalApprovedQuantityNgModel:any="";
    remarksNgModel:any="";
    searchFilterValues: any;
  ngOnInit() {
    this.searchFilterValues=localStorage.getItem('searchFilter')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.machineDescripancyClaimListForm = this.searchMachineDescripancyCiaimService.searchMachineDescripancyClaimListForm();
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined)
    {
      this.machineDescripancyClaimListForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');

    this.cliamNumber()
    this.dropdownClaimStatus()
    // this.onSearchDescripancyClaimList()
  }
  ngAfterViewInit()
  {
    this.maxDate= this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth()-1);
    this.minDate = backDate;
    this.machineDescripancyClaimListForm.get('claimFromDate').patchValue(backDate);
    this.machineDescripancyClaimListForm.get('claimToDate').patchValue(new Date());
    //this.onSearchDescripancyClaimList();
  }
  fromDateSelected(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.machineDescripancyClaimListForm.get('claimToDate').value > this.maxDate)
        this.machineDescripancyClaimListForm.get('claimToDate').patchValue(this.maxDate);
    }
  }
  autoClaimNo(value){
    this.searchMachineDescripancyCiaimService.searchClaimNo(value).subscribe(response => {
      console.log(response);
      if(response){
      this.autoComplateClaimNoDomain = response as BaseDto<Array<AutoComplateClaimNoDomain>>
      }
    })
    
  }

  private cliamNumber(){
    this.machineDescripancyClaimListForm.controls.claimNumber.valueChanges.subscribe(value => {
      if(value){
      this.autoClaimNo(value)
      }
    })
  }

  displayFnClaimNo(ClaimNum: AutoComplateClaimNoDomain) {
    return ClaimNum ? ClaimNum.claimNumber : undefined 
  }

  dropdownClaimStatus(){
    this.searchMachineDescripancyCiaimService.getClaimStatus().subscribe(response => {
      this.dropDownClaimStatus = response as BaseDto<Array<DropDownClaimStatus>>
    })
  }

  onSearchDescripancyClaimList(){
    this.claimIdNgModel="";
    this.claimNumberNgModel="";
    this.claimDateNgModel="";
    this.claimStatusNgModel="";
    this.totalClaimQuantityNgModel="";
    this.totalApprovedQuantityNgModel="";
    this.remarksNgModel="";
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
    let filterData = this.machineDescripancyClaimListForm.value as FilterSearchMachineDescripancyClaim
    let key='searchFilter';
    localStorage.setItem(key,JSON.stringify(this.machineDescripancyClaimListForm.value))
    filterData.claimNumber = this.machineDescripancyClaimListForm.controls.claimNumber.value ? this.machineDescripancyClaimListForm.controls.claimNumber.value.claimNumber : null
    filterData.page = this.page
    filterData.size = this.size
    filterData.claimFromDate = this.machineDescripancyClaimListForm.controls.claimFromDate.value ? this.dateservice.getDateIntoYYYYMMDD(this.machineDescripancyClaimListForm.controls.claimFromDate.value) : null
    filterData.claimToDate = this.machineDescripancyClaimListForm.controls.claimToDate.value ? this.dateservice.getDateIntoYYYYMMDD(this.machineDescripancyClaimListForm.controls.claimToDate.value) : null
    
    Object.keys(filterData).forEach(element => {
      if (element && (filterData[element] === null || filterData[element] === "" || filterData[element] === undefined)) {
        delete filterData[element];
      }
      return filterData;
    });

    this.searchMachineDescripancyCiaimService.getDescripancyClaimList(filterData).subscribe(response =>{
      this.dataTable = this.tableDataService.convertIntoDataTable(response.result)
      console.log('datatable',this.dataTable);
    })
  }

  clearDescripancyClaimSearch(){
    this.machineDescripancyClaimListForm.reset()
    this.clearSearchRow.next("");
  }

  actionOnTableRecord(recordData) {
    console.log('recordData', recordData);

    if (recordData.btnAction.toLowerCase() === 'claimnumber') {
      if (recordData.record.claimStatus === 'draft'){
        this.router.navigate(['edit'], {queryParams: {id:btoa(recordData.record.id)}, relativeTo: this.claimRt })
      }else {
        this.router.navigate(['view'], {queryParams: {id:btoa(recordData.record.id)}, relativeTo: this.claimRt })
      }
    }
    if (recordData.btnAction.toLowerCase() === 'action') {
      if (recordData.record.action === 'Approve'){
        this.router.navigate(['approve'], {queryParams: {id:btoa(recordData.record.id)}, relativeTo: this.claimRt })
      }
    }

  }
  pageChange(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;

    this.onSearchDescripancyClaimList()
  }

  test(event, searchColumnName){
    console.log('event', event);
    const date: Date = event.value as Date
    const searchValue = {
      searchValue: date.getDate() + '-' + (((date.getMonth() + 1) < 10) ? `0${(date.getMonth() + 1)}` : (date.getMonth() + 1)) + '-' + date.getFullYear(),
      searchColumnName
    };
    this.searchValue = new ColumnSearch(searchValue.searchValue, searchValue.searchColumnName);
  }


  // private convertDateToServerFormat(dt: string) {
  //   if (dt) {
  //     let date = new Date(dt)
  //     let formattedDate = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear() 
  //     console.log(formattedDate)
  //     return formattedDate
  //   }
  //   return null
  // }

}
