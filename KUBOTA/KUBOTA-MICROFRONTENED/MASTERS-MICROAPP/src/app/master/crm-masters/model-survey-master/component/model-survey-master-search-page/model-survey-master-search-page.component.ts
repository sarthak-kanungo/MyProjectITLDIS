import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ModelSurveyMasterSearchPagePresenter } from './model-survey-master-search-page.presenter';
import { ModelSurveyMasterSearchPageStore } from './model-survey-master-search-page.store';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ObjectUtil } from 'src/app/utils/object-util';
import { ModelSurveyMasterService } from '../../service/model-survey-master.service';

@Component({
  selector: 'app-model-survey-master-search-page',
  templateUrl: './model-survey-master-search-page.component.html',
  styleUrls: ['./model-survey-master-search-page.component.css'],
  providers:[ModelSurveyMasterSearchPageStore,ModelSurveyMasterSearchPagePresenter]
})
export class ModelSurveyMasterSearchPageComponent implements OnInit {

  searchForm: FormGroup;
  modelSurveyMaterForm: FormGroup

  @Output() actionOnTableCell = new EventEmitter<Object>(); 
  searchFilter;
  public dataTable: DataTable;
  public totalTableElements: number;
  actionButtons = [];
  searchValue: ColumnSearch;
  searchFlag:boolean=true;
  searchFilterValues: any;
  recordData:any
  page = 0;
  size = 10

  constructor(private pagePresenter:ModelSurveyMasterSearchPagePresenter,
              private ngswSearchTableService: NgswSearchTableService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private service:ModelSurveyMasterService
    ) { }

  ngOnInit() {
    this.searchForm = this.pagePresenter.searchModelSurveyMasterForm
    this.modelSurveyMaterForm = this.pagePresenter.modelSurveyMasterSearchMForm

    this.searchFilterValues=localStorage.getItem('searchFilter')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined){
        this.searchForm.patchValue(this.searchFilterValues)
      }
    localStorage.removeItem('searchFilter');
  }

  searchModelServey() {
    
    let surveyName:string
    // if (this.modelSurveyMaterForm.get('surveyName').value) {
    //   surveyName=this.modelSurveyMaterForm.get('surveyName').value
    // }


    let searchFormValues={} as any
    let key='searchFilter';
    localStorage.setItem(key,JSON.stringify(this.searchForm.value))
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

      const temp = this.searchForm.getRawValue().modelSurveyMaterForm
      temp['page'] = this.page
      temp['size'] = this.size
      this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
      searchFormValues.surveyName=surveyName
      console.log('searchFormValues--',searchFormValues);

    this.service.getModelSurveyMasterSearchTableData(searchFormValues).subscribe(res=>{
      res['result'].forEach(row => {
        row.edit= 'edit';
      });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--',this.totalTableElements );
      
    })
  }
   


  tableAction(recordData) {
    this.actionOnTableCell.emit(recordData)

    if (recordData.btnAction.toLowerCase() ===  'edit') {
      this.router.navigate(['../edit'], {
      relativeTo: this.activatedRoute, queryParams: { id: recordData['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }

    if (recordData.btnAction.toLowerCase() ===  'surveyName') {
      this.router.navigate(['../view'], {
      relativeTo: this.activatedRoute, queryParams: { id: recordData['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
  }

  initialQueryParams(event) {
    this.searchForm.get('modelSurveyMaterForm').patchValue(event);
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    this.searchModelServey()
  }

  clearForm(){}

}
