import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DealerDesignationMasterSearchPageStore } from './dealer-designation-master-search-page.store';
import { DealerDepartmentMasterSearchPagePresenter } from './dealer-designation-master-search-page.presenter';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { ObjectUtil } from 'src/app/utils/object-util';
import { SearchDesignaionMasterService } from '../search-designation-master/search-designaion-master.service';
import { ActionOnTableRecordForDealerDesignation } from '../../domain/dealer-designation-master-domain';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-dealer-designation-master-search-page',
  templateUrl: './dealer-designation-master-search-page.component.html',
  styleUrls: ['./dealer-designation-master-search-page.component.scss'],
  providers: [DealerDepartmentMasterSearchPagePresenter, DealerDesignationMasterSearchPageStore,SearchDesignaionMasterService]
})
export class DealerDesignationMasterSearchPageComponent implements OnInit {

  searchForm: FormGroup;
  dealerDesignationSearchDetailsForm: FormGroup

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
  clearSearchRow = new BehaviorSubject<string>("");

  designationNgModel:any=''
  department_nameNgModel:any=''

  constructor(
    private dealerDepartmentMasterSearchPagePresenter: DealerDepartmentMasterSearchPagePresenter,
    private SearchService: SearchDesignaionMasterService,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.searchForm = this.dealerDepartmentMasterSearchPagePresenter.searchDealerDesignationMasterForm
    this.dealerDesignationSearchDetailsForm = this.dealerDepartmentMasterSearchPagePresenter.dealerDesignationSearchDetailsForm

    this.searchFilterValues=localStorage.getItem('searchFilter')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined)
    {
      this.searchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
  }

  searchDealerDesignation() {
    let designationName:string
    if (this.dealerDesignationSearchDetailsForm.get('designationName').value) {
      designationName=this.dealerDesignationSearchDetailsForm.get('designationName').value.name
    }

    let department:string
    if (this.dealerDesignationSearchDetailsForm.get('department').value) {
      department=this.dealerDesignationSearchDetailsForm.get('department').value.department_name
      
    }


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

      const temp = this.searchForm.getRawValue().dealerDesignationSearchDetailsForm
      temp['page'] = this.page
      temp['size'] = this.size
      this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
      searchFormValues.designation=designationName
      searchFormValues.department=department
      console.log('searchFormValues--',searchFormValues);

    this.SearchService.searchDesignationForSearchPageTable(searchFormValues).subscribe(res=>{
      console.log('result---------------------',res)
      res['result'].forEach(row => {
        row.edit= 'edit';
      });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--',this.totalTableElements );
      
    })
  }

  


  tableAction(recordData) {
    this.SearchService.viewDetailsData=recordData
    console.log("recordData ", recordData);
    this.actionOnTableCell.emit(recordData)
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
    }

    if (recordData.btnAction.toLowerCase() ===  'edit') {
      this.router.navigate(['../edit'], {
      relativeTo: this.activatedRoute, queryParams: { id: recordData['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }

    if (recordData.btnAction.toLowerCase() ===  'designation') {
      this.router.navigate(['../view'], {
      relativeTo: this.activatedRoute, queryParams: { id: recordData['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
  }

  initialQueryParams(event) {
    this.searchForm.get('dealerDesignationSearchDetailsForm').patchValue(event);
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    this.searchDealerDesignation()
  }

  tableSearchClear(){
    this.designationNgModel=''
    this.department_nameNgModel=''
  }



  clearForm() {
    this.searchForm.reset()
    this.tableSearchClear()
    this.dealerDesignationSearchDetailsForm.reset()
    this.searchDealerDesignation()
    this.clearSearchRow.next("");
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  private openConfirmDialog(emitedId: ActionOnTableRecordForDealerDesignation): void | any {
    let message = 'Are you sure you want to change status?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });

    dialogRef.afterClosed().subscribe(result => {
        if (result === 'Confirm') {
            this.changeActiveStatus(emitedId);}
        });
  }

  private changeActiveStatus(emitedId: ActionOnTableRecordForDealerDesignation) {
    this.SearchService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
      this.dataTable.tableBody.content[emitedId.recordIndex]['activeStatus'] = res['result']['activeStatus'];
        this.toastr.success('Active status changed successfully!', 'Success',{
              timeOut: 1500
            });
            console.log('Table_Body_content', this.dataTable.tableBody.content);
      }, err => {
        this.toastr.error('Changing active status of record is failed!');
    });
  }

}
