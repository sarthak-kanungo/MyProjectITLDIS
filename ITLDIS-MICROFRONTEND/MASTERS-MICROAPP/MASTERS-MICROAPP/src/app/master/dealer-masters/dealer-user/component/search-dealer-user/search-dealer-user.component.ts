import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { SubmitDto } from 'src/app/master/itldis-masters/itldis-user/domain/itldis-user-domain';
import { ObjectUtil } from 'src/app/utils/object-util';
import { DealerSearchPagePresenter } from './dealer-search-page.presenter';
import { DealerSearchPageStore } from './dealer-search-page.store';
import { DealerSearchService } from './dealer-search.service';

@Component({
  selector: 'app-search-dealer-user',
  templateUrl: './search-dealer-user.component.html',
  styleUrls: ['./search-dealer-user.component.scss'],
  providers:[DealerSearchPagePresenter,DealerSearchService,DealerSearchPageStore]
})
export class SearchDealerUserComponent implements OnInit,AfterViewInit {

  dealerSeacrchForm:FormGroup
  dealerForm:FormGroup
  searchFilter;
  public dataTable: DataTable;
  public totalTableElements: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  searchFlag:boolean=true;
  searchFilterValues: any;
  recordData:SubmitDto
  page = 0;
  size = 10

    employeeCodeNgModel:any
    employeeNameNgModel:any
    loginIdStatusNgModel:any
    employeeStatusNgModel:any

  constructor( private fb: FormBuilder,
    private dealerSearchPagePresenter:DealerSearchPagePresenter,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dealerSearchService:DealerSearchService
    ) { }

  ngOnInit() {
    this.searchFilterValues=localStorage.getItem('searchFilter')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    this.dealerSeacrchForm = this.dealerSearchPagePresenter.dealerSeacrchForm
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined)
    {
      this.dealerSeacrchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
    this.dealerSeacrchForm=this.dealerSearchPagePresenter.dealerSeacrchForm
    this.dealerForm=this.dealerSearchPagePresenter.dealerForm

  }
  ngAfterViewInit(){
    this.searchDealerForTable();
  }

  searchDealerForTable() {
    //const searchFormValues = this.dealerSeacrchForm.getRawValue()
    

    let empcode:string
    if (this.dealerForm.get('employeeCode').value) {
      empcode=this.dealerForm.get('employeeCode').value
      
    }

    let empName:string
    if (this.dealerForm.get('employeeName').value) {
      empName=this.dealerForm.get('employeeName').value
      
    }
    let searchFormValues={} as any
    
    let key='searchFilter';
    localStorage.setItem(key,JSON.stringify(this.dealerSeacrchForm.value))
    if(this.dataTable!=undefined)
    {
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
    if(this.searchFlag==true)
    {
      this.page=0;
      this.size=10;
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }
    else{
      searchFormValues['page'] = this.page;
      searchFormValues['size'] = this.size;
    }

      const temp = this.dealerSeacrchForm.getRawValue().dealerForm
      temp['page'] = this.page
      temp['size'] = this.size
      this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
      searchFormValues.employeeCode=empcode
      searchFormValues.employeeName=empName
      console.log('searchFormValues--',searchFormValues);

    this.dealerSearchService.searchDealerTable(searchFormValues).subscribe(res=>{
      res['result'].forEach(row => {
        row.edit= 'edit';
      });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--',this.totalTableElements );
      
    })
  }

  
  tableAction(event: object) {
    console.log('event', event)
    this.recordData= event['record']
    if (event['btnAction'].toLowerCase() === 'employeecode') {
      this.router.navigate(['../view'], {
      relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
       //relativeTo: this.activatedRoute, queryParams: { id: this.recordData, hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
    if (event['btnAction'].toLowerCase() === 'edit') {
      this.router.navigate(['../edit'], {
        // relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], searchFilter: JSON.stringify(this.searchFilter) }
        relativeTo: this.activatedRoute, queryParams: { id: event['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }

  }

  initialQueryParams(event) {
    this.dealerSeacrchForm.get('dealerForm').patchValue(event);
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    this.searchDealerForTable()
  }

  clearDealerForm() {
    this.dealerSeacrchForm.reset()
    this.searchDealerForTable()
    this.tableSearchClear()
  }

  tableSearchClear(){
    this.employeeCodeNgModel=''
    this.employeeNameNgModel=''
    this.loginIdStatusNgModel=''
    this.employeeStatusNgModel=''
  }


}
