import { AfterViewInit, Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import { EventEmitter } from '@angular/core';
import { SearchDesignationService } from '../../../designation-master/component/search-designation/search-designation.service';
import { ActionOnTableRecord } from '../../../../../ui/dynamic-table/dynamic-table.domain';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { SearchDesignationLevelService } from './search-designation-level.service';
import { DesignationLevelAuto, SearchDesignationLevel } from 'Designation Level';
import { ObjectUtil } from 'src/app/utils/object-util';
import { BehaviorSubject } from 'rxjs';
import { DepartmentName } from '../../../designation-master/component/search-designation/search-designation';

@Component({
  selector: 'app-search-designation-level',
  templateUrl: './search-designation-level.component.html',
  styleUrls: ['./search-designation-level.component.css']
})
export class SearchDesignationLevelComponent implements OnInit,AfterViewInit {
  designationLevelForm: FormGroup;
  designationLevelList: BaseDto<Array<DesignationLevelAuto>>
  departmentNameList: BaseDto<Array<DepartmentName>>
  clearSearchRow = new BehaviorSubject<string>("");
  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  searchFilter;
  searchFlag: boolean = true;
  searchFilterValues: any;
  @Output() actionOnTableCell = new EventEmitter<Object>();
  designationNgModel: string;
  departmentNameNgModel: string;

  constructor(private fb: FormBuilder,
    private ngswSearchTableService: NgswSearchTableService,
    private searchDesignationLevelService: SearchDesignationLevelService,
    private searchDesignationService: SearchDesignationService,
    public dialog: MatDialog,
    private toastr: ToastrService,) { }

  ngOnInit() {
    this.searchdesignationLevelForm();
    this.getDepartmentNameDropDown();
  }
  ngAfterViewInit() {
    this.searchDesignationLevelMaster();
    this.searchData()
  }
  searchdesignationLevelForm() {
    this.designationLevelForm = this.fb.group({
      designationlevel: ['', Validators.compose([])],
      departmentName: ['', Validators.compose([])],
    })
    this.designationLevelForm.controls.designationlevel.valueChanges.subscribe(value => {
      this.autoDesignationLevel(value)
    })
  }

  getDepartmentNameDropDown() {
    this.searchDesignationService.getDepartmentName().subscribe(response => {
      this.departmentNameList = response
      console.log("getDepartmentNameDropDown--->", response)
    })
  }

  autoDesignationLevel(value) {
    this.searchDesignationLevelService.searchDesignationLevel(value).subscribe(response => {
      console.log(response);
      this.designationLevelList = response
    })
  }

  displayFnDesignation(designationlevel: DesignationLevelAuto) {
    return designationlevel ? designationlevel.designationlevel : undefined
  }
  searchDesignationLevelMaster() {
    console.log('search check----------------')
    const searchFormValues = this.designationLevelForm.value
    console.log('searchFormValues--', searchFormValues);
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.designationLevelForm.value))
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

    const temp = this.designationLevelForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.searchDesignationLevelService.searchDesignationLevelMaster(searchFormValues).subscribe(res => {
      console.log('searchRes=====>', res);
      // .forEach(element => {
      // element.edit='edit'
      // });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
    })
  }
  searchData() {
    console.log("this.designationLevelForm ", this.designationLevelForm);
    let sendDto = {} as SearchDesignationLevel
    sendDto.designationlevel = this.designationLevelForm.get('designationlevel').value ? this.designationLevelForm.get('designationlevel').value.designation : null
    sendDto.departmentName = this.designationLevelForm.get('departmentName').value ? this.designationLevelForm.get('departmentName').value : null

    this.setSearchResultTable({ ...sendDto, page: this.page, size: this.size });
  }

  private setSearchResultTable(searchValue: SearchDesignationLevel) {
    console.log("searchValue---->", searchValue)
    this.searchDesignationLevelService.searchDesignationLevelMaster(searchValue).subscribe(searchRes => {
      console.log('searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
    });
  }

  clearForm() {
    this.designationLevelForm.reset();
    this.searchData();
    this.clearSearchRow.next("");
    this.designationNgModel=''
    this.departmentNameNgModel=''
  }
  pageChangeOfSearchTable(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchDesignationLevelMaster();
    this.searchData();
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
    this.searchDesignationLevelService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
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
