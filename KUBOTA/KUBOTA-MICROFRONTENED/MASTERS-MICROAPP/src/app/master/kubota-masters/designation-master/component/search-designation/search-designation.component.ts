import { Component, OnInit, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SearchDesignationService } from './search-designation.service';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { ActionOnTableRecord } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { BaseDto } from 'BaseDto';
import { ObjectUtil } from 'src/app/utils/object-util';
import { BehaviorSubject } from 'rxjs';
import { DepartmentName, DesignationAuto } from './search-designation';

@Component({
  selector: 'app-search-designation',
  templateUrl: './search-designation.component.html',
  styleUrls: ['./search-designation.component.scss']
})
export class SearchDesignationComponent implements OnInit, AfterViewInit {
  designationForm: FormGroup;
  searchFilter;
  searchFlag: boolean = true;
  searchFilterValues: any;
  designationList: BaseDto<Array<DesignationAuto>>
  departmentNameList: BaseDto<Array<DepartmentName>>
  clearSearchRow = new BehaviorSubject<string>("");
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  designationNgModel: string
  departmentNameNgModel: string
  totalSearchRecordCount: number;
  @Output() actionOnTableCell = new EventEmitter<Object>();
  constructor(private fb: FormBuilder,
    private searchDesignationService: SearchDesignationService,
    private ngswSearchTableService: NgswSearchTableService,
    public dialog: MatDialog,
    private toastr: ToastrService,) { }

  ngOnInit() {
    this.searchdesignationForm();
    this.getDepartmentNameDropDown();
  }
  ngAfterViewInit() {
    this.searchDesignationMaster();
    //this.searchData()
  }
  searchdesignationForm() {
    this.designationForm = this.fb.group({
      designation: ['', Validators.compose([])],
      departmentName: ['', Validators.compose([])],
    })
    this.designationForm.controls.designation.valueChanges.subscribe(value => {
      this.autoDesignation(value)
    })
  }
  autoDesignation(value) {
    this.searchDesignationService.searchDesignation(value).subscribe(response => {
      console.log(response);
      this.designationList = response
    })
  }
  displayFnDesignation(designation: DesignationAuto) {
    return designation ? designation.designation : undefined
  }

  getDepartmentNameDropDown() {
    this.searchDesignationService.getDepartmentName().subscribe(response => {
      this.departmentNameList = response
      console.log("getDepartmentNameDropDown--->", response)
    })
  }
  searchDesignationMaster() {
    const searchFormValues = this.designationForm.value
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.designationForm.value))
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
    
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.searchDesignationService.searchDesignationMaster(searchFormValues).subscribe(res => {
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
    })
  }
  // searchData() {
  //   console.log("this.designationForm ", this.designationForm);
  //   let sendDto = {} as SearchDesignation
  //   // sendDto.designation = this.designationForm.get('designation').value ? this.designationForm.get('designation').value.designation : null
  //   // sendDto.departmentName = this.designationForm.get('departmentName').value ? this.designationForm.get('departmentName').value : null

  //   this.setSearchResultTable({ ...sendDto, page: this.page, size: this.size });
  // }
  // private setSearchResultTable(searchValue: SearchDesignation) {
  //   console.log("searchValue---->", searchValue)
  //   this.searchDesignationService.searchDesignationMaster(searchValue).subscribe(searchRes => {
  //     console.log('searchRes=====>', searchRes);
  //     this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
  //     this.totalSearchRecordCount = searchRes['count'];
  //   });
  // }
  clearForm() {
    this.designationForm.reset();
    this.searchDesignationMaster();
    this.clearSearchRow.next("");
    this.designationNgModel=''
    this.departmentNameNgModel=''
  }
  pageChangeOfSearchTable(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchDesignationMaster();
    //this.searchData();
  }
  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    this.actionOnTableCell.emit(recordData)
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
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
    this.searchDesignationService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
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
}


