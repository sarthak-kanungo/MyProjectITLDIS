import { Component, OnInit, EventEmitter, Output, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { DataTable, NgswSearchTableService, ColumnSearch, InfoForGetPagination } from 'ngsw-search-table';
import { SearchitldisEmployeeService } from './searchitldis-employee.service';
import { ActionOnTableRecord } from 'ngsw-search-table';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { BaseDto } from 'BaseDto';
import { autoEmployeeCode } from './search-itldis-employee';
import { autoEmployeeName } from './search-itldis-employee';
import { SearchEmpMaster } from './search-itldis-employee';
import { IFrameService, IFrameMessageSource, UrlSegment } from '../../../../../root-service/iFrame.service';
import { ObjectUtil } from '../../../../../utils/object-util';
import { Router, ActivatedRoute } from '@angular/router';
import { OrghierarchyModalComponent } from '../../orghierarchy-Modal/orghierarchy-modal/orghierarchy-modal.component';
import { OrghierarchyModalService } from '../../orghierarchy-Modal/orghierarchy-modal.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-search-itldis-empolyee',
  templateUrl: './search-itldis-empolyee.component.html',
  styleUrls: ['./search-itldis-empolyee.component.scss']
})
export class SearchitldisEmpolyeeComponent implements OnInit, AfterViewInit {

  employeeForm: FormGroup;
  searchEmpMaster = {} as SearchEmpMaster;
  employeeCodeList: BaseDto<Array<autoEmployeeCode>>
  employeeNameList: BaseDto<Array<autoEmployeeName>>

  isEdit: boolean;
  isView: boolean;
  clearSearchRow = new BehaviorSubject<string>("");

  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  @Output() actionOnTableCell = new EventEmitter<Object>();
  searchFilter: any = {};
  searchFlag: boolean = true;
  searchFilterValues: any;
  actionNg: any
  activeStatusNg: any
  contactNoNg: any
  departmentNameNg: any
  designationNg: any
  designationLevelNg: any
  emailIdNg: any
  employeeCodeNg: any
  employeeNameNg: any
  managementAccessNg: any
  reportingEmployeeCodeNg: any
  reportingEmployeeNameNg: any
  updateOrghierarchyNg: any
   
  constructor(private fb: FormBuilder,
    private searchitldisEmployeeService: SearchitldisEmployeeService,
    private router: Router,
    private actRt: ActivatedRoute,
    private ngswSearchTableService: NgswSearchTableService,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private iFrameService: IFrameService,
    private orgHierModalService: OrghierarchyModalService,
  ) { }

  ngOnInit() {
    this.searchemployeeForm();
  }

  ngAfterViewInit() {
    this.employeeForm.valueChanges.subscribe(val => {
      this.searchEmpMaster = val;
    })
    this.searchEmpMaster.page = this.employeeForm.get('page').value;
    this.searchEmpMaster.size = this.employeeForm.get('size').value;
    this.search()
    // this.searchDetail();
    // this.setSearchResultTable(this.searchEmpMaster); // commented By Kanhaiya
    this.searchEmployeeMaster()
  }

  searchemployeeForm() {
    this.employeeForm = this.fb.group({
      employeeCode: [null],
      employeeName: [null],
      page: [0],
      size: [10]
    })
    this.employeeForm.controls.employeeCode.valueChanges.subscribe(value => {
      this.autoEmployeeCode(value)
    })
    this.employeeForm.controls.employeeName.valueChanges.subscribe(value => {
      this.autoEmployeeName(value)
    })               
  }
  autoEmployeeCode(value) {
    this.searchitldisEmployeeService.searchEmployeeCode(value).subscribe(response => {
      console.log(response);
      this.employeeCodeList = response
    })
  }
  displayFnEmployeeCode(empCode: autoEmployeeCode) {
    return empCode ? empCode.employeeCode : undefined
  }

  autoEmployeeName(value) {
    this.searchitldisEmployeeService.searchEmployeeName(value).subscribe(response => {
      console.log(response);
      this.employeeNameList = response
    })
  }
  displayFnEmployeeName(empName: autoEmployeeName) {
    return empName ? empName.employeeName : undefined
  }
  // private setSearchResultTable(searchValue: SearchEmpMaster) {
  //   this.searchitldisEmployeeService.searchEmpMaster(searchValue).subscribe(searchRes => {
  //     searchRes.result.forEach(row => {
  //       row.action = 'edit';
  //     });
  //     searchRes.result.forEach(row => {
  //       row.updateOrghierarchy = 'updateOrghierarchy';
  //     });
  //     console.log('searchRes=====>', searchRes);
  //     this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
  //     this.totalSearchRecordCount = searchRes['count'];
  //   });
  // }
  // commented By Kanhaiya
  searchEmployeeMaster() {
    console.log('search check----------------')
    const searchFormValues = this.employeeForm.value
    console.log('searchFormValues--', searchFormValues);
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.employeeForm.value))
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

    const temp = this.employeeForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.searchitldisEmployeeService.searchEmpMaster(searchFormValues).subscribe(res => {
      console.log('searchRes=====>', res);
      res.result.forEach(row => {
        row.action = 'edit';
      });
      res.result.forEach(row => {
        row.updateOrghierarchy = 'updateOrghierarchy';
      });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
    })
  }
  tableClear(){
    this.actionNg=""
    this.activeStatusNg=""
    this.contactNoNg=""
    this.departmentNameNg=""
    this.designationNg=""
    this.designationLevelNg=""
    this.emailIdNg=""
    this.employeeCodeNg=""
    this.employeeNameNg=""
    this.managementAccessNg=""
    this.reportingEmployeeCodeNg=""
    this.reportingEmployeeNameNg=""
    this.updateOrghierarchyNg=""

  }
  clearForm() {
    this.employeeForm.reset();
    this.employeeForm.get('page').patchValue(this.page);
    this.employeeForm.get('size').patchValue(this.size);
    this.searchFilter = ObjectUtil.removeNulls(this.searchEmpMaster);
    // this.setSearchResultTable(this.searchEmpMaster);
    this.searchEmployeeMaster()
    this.clearSearchRow.next("");
    this.tableClear()
  }

  // pageChangeOfSearchTable(event: InfoForGetPagination) {
  //   console.log('event', event);
  //   this.searchEmpMaster.page = event.page;
  //   this.searchEmpMaster.size = event.size;

  //   this.employeeForm.get('page').patchValue(event['page']);
  //   this.employeeForm.get('size').patchValue(event['size']);    
  //   this.searchDetail();  

  //   this.searchFilter = ObjectUtil.removeNulls(this.searchEmpMaster);
  //   this.setSearchResultTable(this.searchEmpMaster);
  //   this.searchFlag=false;
  //   this.searchEmployeeMaster()
  // }
  // commented By Kanhaiya
  pageChangeOfSearchTable(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchEmployeeMaster();
    this.searchDetail();
    this.search()
    // this.setSearchResultTable(this.searchEmpMaster);
  }

  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    this.actionOnTableCell.emit(recordData)
    if (!!recordData && recordData.btnAction.toLowerCase() === 'updateorghierarchy') {
      this.openOrghierarchyModal();
      this.orgHierModalService.hoEmployeeId = recordData.record.id
    }
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
    } else if (recordData['btnAction'].toLocaleLowerCase() === 'action') {
      this.router.navigate(['edit', recordData.record.employeeCode], { relativeTo: this.actRt, queryParams: { filterData: JSON.stringify(this.searchFilter) } });
    } else {
      // this.router.navigate(['view', recordData.record.employeeCode], { relativeTo: this.actRt, queryParams: { filterData: JSON.stringify(this.searchFilter) } });
    }
  }

  search() {
    this.searchDetail();
    if (this.employeeForm.valid) {
      this.searchFilter = ObjectUtil.removeNulls(this.searchEmpMaster);
      // this.setSearchResultTable(this.searchEmpMaster);
      this.searchEmployeeMaster() 
    }
    else {
      this.toastr.error('Please select value from list', 'Error');
    }
  }

  searchDetail() {
    if (this.employeeForm.get('employeeName').value != null) {
      this.searchEmpMaster.employeeName = this.employeeForm.get('employeeName').value.employeeName;
    }
    if (this.employeeForm.get('employeeCode').value != null) {
      this.searchEmpMaster.employeeCode = this.employeeForm.get('employeeCode').value.employeeCode;
    }
  }
  private openConfirmDialog(emitedId: ActionOnTableRecord): void | any {
    let message = 'Are you sure you want to change status?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.changeActiveStatus(emitedId);
      }
    });
  }
  private changeActiveStatus(emitedId: ActionOnTableRecord) {
    this.searchitldisEmployeeService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
      console.log('res', res);
      // const isError =  this.toastr.error('Changing active status of record is failed!', 'Error');
      // if (isError) {
      //   return;
      // }
      this.dataTable.tableBody.content[emitedId.recordIndex]['activeStatus'] = res['result']['activeStatus'];
      this.toastr.success('Active status changed successfully!', 'Success', {
        timeOut: 1500
      });
      console.log('this.dataTable.tableBody.content', this.dataTable.tableBody.content);
    }, err => {
      this.toastr.error('Changing active status of record is failed!');
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  /**
 * ----------Following is state management code------------
 */

  initialQueryParams(event: SearchEmpMaster) {
    console.log('initialQueryParams event: ', event);
    /* const searchValue = /%2F/g;
    const newValue = '/'; */
    this.employeeForm.patchValue(event);
    if (event.employeeCode) {
      this.employeeForm.get('employeeCode').patchValue({ employeeCode: event.employeeCode });
    }
    if (event.employeeName) {
      this.employeeForm.get('employeeName').patchValue({ employeeName: event.employeeName });
    }
    this.page = event.page;
    this.size = event.size;
    this.employeeForm.get('page').patchValue(event.page);
    this.employeeForm.get('size').patchValue(event.size);
  }

  onUrlChange(event) {
    console.log('onUrlChange event: ', event);
    if (!event) {
      return;
    }
    const { queryParam = null, url = '' } = { ...event };
    this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.MASTER, { url, queryParam } as UrlSegment);
  }



  openOrghierarchyModal(): void {

    const confirmationData = this.setConfirmationModalData('message');
    const dialogRef = this.dialog.open(OrghierarchyModalComponent, {
      width: '90%',
      maxHeight: '80vh',
      panelClass: 'confirmation_modal',
      data: ''
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);

    });
  }
}
