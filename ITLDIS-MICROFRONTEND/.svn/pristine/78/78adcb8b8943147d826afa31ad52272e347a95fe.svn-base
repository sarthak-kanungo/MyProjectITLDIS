import { Component, OnInit, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { SearchDepartmentService } from './search-department.service';
import { DepartmentCodeAuto, SearchMaster, DropDownDepartmentNames } from 'SearchDepartment'

import { DataTable, NgswSearchTableService, ColumnSearch } from 'ngsw-search-table';
import { ActionOnTableRecord } from 'ngsw-search-table';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import { ObjectUtil } from 'src/app/utils/object-util';
import { BehaviorSubject } from 'rxjs';


@Component({
  selector: 'app-search-department',
  templateUrl: './search-department.component.html',
  styleUrls: ['./search-department.component.scss']
})
export class SearchDepartmentComponent implements OnInit, AfterViewInit {

  departmentForm: FormGroup;

  departmentCodeList: BaseDto<Array<DepartmentCodeAuto>>

  departmentNamesList: BaseDto<Array<DropDownDepartmentNames>>
  // dealersList: BaseDto<Array<DropDownDealers>>
  // codeList: BaseDto<Array<DropDownCode>>
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
  @Output() actionOnTableCell = new EventEmitter<Object>();

  pageLoadCount:number=0;

  constructor(private fb: FormBuilder,
    private searchDepartmentService: SearchDepartmentService,
    private ngswSearchTableService: NgswSearchTableService,
    public dialog: MatDialog,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.searchdepartmentForm();
    this.loadAllDropDownData();
    // this.loadAllDropDownData().subscribe(dt => {
    //   console.log("drop downs (dt) ---->", dt)
    //   this.departmentNamesList = dt[0] as BaseDto<Array<DropDownDepartmentNames>>
    //   this.dealersList = dt[1] as BaseDto<Array<DropDownDealers>>
    //   this.codeList = dt[2] as BaseDto<Array<DropDownCode>>

    //   // this.patchOrCreate()
    // })
  }
  ngAfterViewInit() {
    // this.searchDepartmentMaster();
    // this.searchData()
  }
  searchdepartmentForm() {
    this.departmentForm = this.fb.group({
      departmentCode: [''],
      departmentName: [''],
      linkedtoDealer: [''],
      dealerCode: [''],
    })
    this.departmentForm.controls.departmentCode.valueChanges.subscribe(value => {
      this.autoDepartmentCode(value)
    })
  }
  searchDepartmentMaster() {
    console.log('search check----------------')
    const searchFormValues = this.departmentForm.value
    console.log('searchFormValues--', searchFormValues);
    let key = 'searchFilter';
    localStorage.setItem(key, JSON.stringify(this.departmentForm.value))
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

    const temp = this.departmentForm.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchFilter = ObjectUtil.removeNulls(searchFormValues);
    this.searchDepartmentService.searchDepartmentMaster(searchFormValues).subscribe(res => {
      console.log('searchRes=====>', res);
      // .forEach(element => {
      // element.edit='edit'
      // });
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
      console.log('totalTableElements--', this.totalTableElements);
    })
  }
  autoDepartmentCode(value) {
    try {
      this.searchDepartmentService.searchDepartmentCode(value).subscribe(response => {
        if(response){
        this.departmentCodeList = response;
        }else{
          console.log('Depart Code not Found!')
        }
      });
    } catch (error) {
      console.error("An error occurred while calling the API:", error);
      // Handle the error as needed, such as showing a user-friendly message or logging it.
    }
    
   
  }
  displayFnDepartmentCode(departmentCd: DepartmentCodeAuto) {
    return departmentCd ? departmentCd.departmentCode : undefined
  }
  // private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
  //   let dropDownTask = [];
  //   dropDownTask.push(this.searchDepartmentService.getDealersList())
  //   dropDownTask.push(this.searchDepartmentService.getDealersList())
  //   dropDownTask.push(this.searchDepartmentService.getCodeList())

  //   return forkJoin(...dropDownTask)
  // }

  private loadAllDropDownData() {
    this.searchDepartmentService.getDepartmentNamesList().subscribe(res => {
      this.departmentNamesList = res;
    })
    // this.searchDepartmentService.getDealersList().subscribe(res => {
    //       console.log("getDealersList dropdown--->", res)
    //       this.dealersList = res;
    //     })
    // this.searchDepartmentService.getCodeList().subscribe(res => {
    //   console.log("getCodeList dropdown--->", res)
    //       this.codeList = res;
    //     })
  }

  searchData() {
    let sendDto = {} as SearchMaster
    // sendDto.departmentCode = this.departmentForm.get('departmentCode').value ? this.departmentForm.get('departmentCode').value.departmentCode : null
    // sendDto.departmentName = this.departmentForm.get('departmentName').value ? this.departmentForm.get('departmentName').value : null
    // console.log("sendDto ", sendDto);

    this.setSearchResultTable({ ...sendDto, page: this.page, size: this.size });
  }
  private setSearchResultTable(searchValue: SearchMaster) {
    console.log("searchText----->", searchValue)
    this.searchDepartmentService.searchDepartmentMaster(searchValue).subscribe(searchRes => {
      console.log('searchRes=====>', searchRes);
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(searchRes['result']);
      this.totalSearchRecordCount = searchRes['count'];
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
  clearForm() {
    this.departmentForm.reset();
    // this.searchData();
    this.clearSearchRow.next("");
    this.departmentCodeNgModel=''
    this.departmentNameNgModel=''
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
    this.searchDepartmentService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
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
  pageChangeOfSearchTable(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    this.searchFlag=false;
    if(this.pageLoadCount > 0){
      this.searchDepartmentMaster();
      this.searchData();
    }
    this.pageLoadCount = 1;
   
  }

}
