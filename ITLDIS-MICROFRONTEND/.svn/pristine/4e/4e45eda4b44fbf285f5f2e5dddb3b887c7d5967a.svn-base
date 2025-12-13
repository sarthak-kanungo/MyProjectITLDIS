import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DealerDepartmentMasterSearchPageStore } from './dealer-department-master-search-page.store';
import { DealerDepartmentMasterSearchPagePresenter } from './dealer-department-master-search-page.presenter';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ActivatedRoute, Router } from '@angular/router';
import { ObjectUtil } from 'src/app/utils/object-util';
import { SearchDealerDepartmentService } from '../search-dealer-department/search-dealer-department.service';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ActionOnTableRecordForDealerDept } from '../../domain/dealer-department-master-domain';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-dealer-department-master-search-page',
  templateUrl: './dealer-department-master-search-page.component.html',
  styleUrls: ['./dealer-department-master-search-page.component.scss'],
  providers: [DealerDepartmentMasterSearchPageStore, DealerDepartmentMasterSearchPagePresenter,SearchDealerDepartmentService]
})
export class DealerDepartmentMasterSearchPageComponent implements OnInit {

  searchForm: FormGroup
  dealerDepartmentSearchDetailsForm: FormGroup

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

  departmentCodeNgModel:any=''
  departmentNameNgModel:any=''

  constructor(
    private dealerDepartmentMasterSearchPagePresenter: DealerDepartmentMasterSearchPagePresenter,
    private SearchService: SearchDealerDepartmentService,
    private ngswSearchTableService: NgswSearchTableService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.searchForm = this.dealerDepartmentMasterSearchPagePresenter.searchDealerDepartmentMasterForm
    this.dealerDepartmentSearchDetailsForm = this.dealerDepartmentMasterSearchPagePresenter.dealerDepartmentSearchDetailsForm

    this.searchFilterValues=localStorage.getItem('searchFilter')
    this.searchFilterValues=JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if(this.searchFilterValues!=null || this.searchFilterValues!=undefined)
    {
      this.searchForm.patchValue(this.searchFilterValues)
    }
    localStorage.removeItem('searchFilter');
  }

  searchDealerDepartment() {
    let departmentCode:string
    if (this.dealerDepartmentSearchDetailsForm.get('departmentCode').value) {
      departmentCode=this.dealerDepartmentSearchDetailsForm.get('departmentCode').value.code
    }

    let departmentName:string
    if (this.dealerDepartmentSearchDetailsForm.get('departmentname').value) {
      departmentName=this.dealerDepartmentSearchDetailsForm.get('departmentname').value.name
      
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

      const temp = this.searchForm.getRawValue().dealerDepartmentSearchDetailsForm
      temp['page'] = this.page
      temp['size'] = this.size
      this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
      searchFormValues.departmentCode=departmentCode
      searchFormValues.departmentName=departmentName

    this.SearchService.searchDeptForSearchPageTable(searchFormValues).subscribe(res=>{
      console.log('result--------------------------',res)
      res['result'].forEach(row => {
        row.edit= 'edit';
      });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--',this.totalTableElements );
      
    })
  }


  initialQueryParams(event) {
    this.searchForm.get('dealerDepartmentSearchDetailsForm').patchValue(event);
    this.page = event.page
    this.size = event.size
  }

  pageChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    this.searchDealerDepartment()
  }


  tableSearchClear(){
    this.departmentCodeNgModel=''
    this.departmentNameNgModel=''
}


  clearForm() {
    this.searchForm.reset()
    this.tableSearchClear()
    this.dealerDepartmentSearchDetailsForm.reset()
    this.searchDealerDepartment() 
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

  tableAction(recordData) {
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

    if (recordData.btnAction.toLowerCase() ===  'departmentcode') {
      this.router.navigate(['../view'], {
      relativeTo: this.activatedRoute, queryParams: { id: recordData['record']['id'], hasButton: false, searchFilter: JSON.stringify(this.searchFilter) }
      })
    }
  }

  private openConfirmDialog(emitedId: ActionOnTableRecordForDealerDept): void | any {
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

  private changeActiveStatus(emitedId: ActionOnTableRecordForDealerDept) {
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
