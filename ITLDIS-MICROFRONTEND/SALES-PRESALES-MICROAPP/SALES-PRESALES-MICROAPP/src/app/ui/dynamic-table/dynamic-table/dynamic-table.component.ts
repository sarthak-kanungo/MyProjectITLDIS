import {
  Component,
  OnInit,
  AfterViewInit,
  Input,
  Output,
  EventEmitter,
  SimpleChanges,
  ViewChild,
  ElementRef,
  Inject,
  OnChanges
} from '@angular/core';
// import { LoginModel } from '../../../pages/login/login-model';
import { InfoForGetPagination, DataTable, ActionButton, ActionOnTableRecord, TableSort, TableHeading } from '../dynamic-table.domain';
import { FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatPaginatorIntl } from '@angular/material';
// import { Urlconst } from 'app/constants/urlConst';

declare const $: any;

@Component({
  selector: 'app-dynamic-table',
  templateUrl: './dynamic-table.component.html',
  styleUrls: ['./dynamic-table.component.scss'],
  providers: [MatPaginatorIntl]
})
export class DynamicTableComponent implements OnInit, AfterViewInit, OnChanges {

  @Input() downloadTempleteLink: string;
  @Input() exportLink: string;
  @Input() isExcelImport: boolean = false;
  @Input() totalElements: number = 5;
  @Input()
  hideCheckbox: boolean;
  @Input()
  clickOnTableFields: Array<TableHeading>;
  @Input()
  public dataTable: DataTable;
  @Input()
  recordPerPageList: Array<number> = [1, 5, 10, 25, 50, 100];
  private dataTableCopy: DataTable;
  @Input()
  public loginUser: object;
  @Input()
  public page = 1;
  @Input()
  public showAction = true;
  @Input()
  public actionButtons: Array<ActionButton>;
  @Input()
  public userRole: string;
  @Input()
  public tableTitle: string;
  @Input()
  public retriveValue: Array<string> = ['currencyName', 'firstName', 'hotelName', 'supplierName'];
  @Input()
  public reportUrl: string;
  @Input('excelBtnStatus')
  public excelBtnStatus = false;
  @Input()
  public showAssignButton = false;
  @Input()
  public showRefuseButton = false;
  @Input() public showGlobelSearch = false;
  @Output() uploadedFiles = new EventEmitter<Array<File>>();
  @Output()
  public sortBy = new EventEmitter<object>();
  @Output()
  public pageChange = new EventEmitter<object>();
  @Output()
  public pageSizeChanges = new EventEmitter<number>();
  @Output()
  public actionOnTableRecord = new EventEmitter<object>();
  @Output()
  public excel = new EventEmitter<object>();
  @Output()
  public searchKey = new EventEmitter<string>();
  @Output()
  public exportToExcel = new EventEmitter<any>();
  @Output() columnLengthChanges = new EventEmitter<string>();

  @ViewChild('downloadExcel', { static: false }) public downloadExcel: ElementRef;
  public fileDownloadUrl: string;

  public isPreviewActionBtn = true;
  public isAccountActionBtn = false;
  public isEditActionBtn = false;
  public isRecordDate: boolean;
  public checked = false;
  public sortOrder: TableSort = { active: '', hover: '', direction: '' };
  selectedTableRecordList = [] as Array<object>;
  sortedData: object[];
  searchField = new FormControl('');
  selected: any;
  private size = 10;
  actionButtonsLength: number;
  filesToUpload = [];
  private columnFilterMap = new Map();

  constructor(private httpClient: HttpClient, private matPaginatorIntl: MatPaginatorIntl) {
    // this.dataTable = this.initDataTable();
    // this.actionButtons = this.initActionButtons();
    // // console.log('this.actionButtons', this.actionButtons)
    this.matPaginatorIntl.itemsPerPageLabel = '';
  }
  ngOnChanges(changes: SimpleChanges): void {
    // // console.log('changes', changes);
    if (changes.hasOwnProperty('dataTable') && !!this.dataTable && this.dataTable.tableBody['number']) {
      this.page = this.dataTable.tableBody['number'] + 1;
      // // console.log('this.page', this.page);
    }
    if (!!this.clickOnTableFields && !!this.dataTable && this.clickOnTableFields.length > 0) {
      this.clickOnTableFields.forEach(clickableField => {
        for (let index = 0; index < this.dataTable.headerRow.length; index++) {
          if (this.dataTable.headerRow[index].title === clickableField.title) {
            this.dataTable.headerRow[index].icon = clickableField.icon;
            this.dataTable.headerRow[index].isClickable = true;
            this.dataTable.headerRow[index].iconClass = clickableField.iconClass;
            break;
          }
        }
      });
    }
    if (changes.hasOwnProperty('dataTable') && this.dataTable && changes.dataTable.currentValue) {
      if (!changes.dataTable.previousValue) {
        this.dataTableCopy = JSON.parse(JSON.stringify(this.dataTable));
        // // console.log('changes this.dataTableCopy', this.dataTableCopy);

      }
    }
  }
  ngOnInit() {
    if (this.actionButtons !== undefined) {
      this.actionButtonsLength = this.actionButtons.length;
    }
  }

  ngAfterViewInit() {
    // $('.card .material-datatables label').addClass('form-group');
  }
  private initDataTable(): DataTable {
    return { headerRow: [], tableBody: {} } as DataTable;
  }
  private initActionButtons() {
    let actionBtns: Array<ActionButton> = [] as Array<ActionButton>;
    actionBtns.push({
      toolTipText: 'Edit',
      matIcon: 'edit'
    } as ActionButton);
    return actionBtns;
  }
  compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
  enter(hoverField: string) {
    if (hoverField === this.sortOrder.active) {
      return;
    }
    this.sortOrder.hover = hoverField;
  }
  leave(hoverField: string) {
    // if (hoverField === this.sortOrder.active) {
    //   return;
    // }
    this.sortOrder.hover = '';
  }
  public searchIntoTable(searchText: string, event?: any) {
    // this.dataTableCopy = JSON.parse(JSON.stringify(this.dataTable));
    // // console.log(' this.dataTableCopy', this.dataTableCopy);
    // // console.log(' this.dataTable',  this.dataTable);
    if (!searchText) {
      this.dataTable.tableBody.content = JSON.parse(JSON.stringify(this.dataTableCopy.tableBody.content));
      return;
    }
    this.dataTable.tableBody.content = this.dataTable.tableBody.content.filter((category) => {
      // // console.log('category', category);
      let searchResult: boolean;
      for (const key in category) {
        if (category.hasOwnProperty(key) && typeof category[key] === 'string') {
          // // console.log('if');
          const element = category[key];
          // // console.log('category[key]', category[key]);
          searchResult = category[key].toLowerCase().indexOf(searchText.toLowerCase()) > -1;
          if (searchResult) {
            break;
          }
        } else {
          // // console.log('else');
          if (typeof category[key] === 'number') {
            const element = category[key];
            // // console.log('category[key]', category[key]);
            searchResult = category[key].toString().toLowerCase().indexOf(searchText.toLowerCase()) > -1;
            if (searchResult) {
              break;
            }
          }
        }
      }
      return searchResult;
      // return (
      //   this.dataTable.headerRow.forEach((key) => {
      //     return category[key].toLowerCase().indexOf(searchText.toLowerCase()) > -1;
      //   })
      // );
    });
  }
  public searchIntoRow(searchColumn: string, event?: any) {
    // this.dataTableCopy = JSON.parse(JSON.stringify(this.dataTable));
    // // console.log(' this.dataTableCopy', this.dataTableCopy);
    const searchText = event.srcElement.value;
    // console.log('searchText, searchColumn', searchText, searchColumn, event, event.target.value);
    this.columnFilterMap.set(searchColumn['title'], searchText);

    if (!searchText) {
      this.columnFilterMap.delete(searchColumn['title']);
    }
    if (this.columnFilterMap.size === 0) {
      this.dataTable.tableBody.content = JSON.parse(JSON.stringify(this.dataTableCopy.tableBody.content));
      return;
    }
    console.log('this.columnFilterMap', this.columnFilterMap, this.columnFilterMap.size);
    this.dataTable.tableBody.content = this.dataTableCopy.tableBody.content.filter((category) => {
      // console.log('category', category);
      let validFilterCount: number = 0;
      for (const key in category) {
        let searchResult: boolean;
        // if (category.hasOwnProperty(key) && key !== searchColumn) {
        //   return;
        // }
        // console.log('key', key);

        if (category.hasOwnProperty(key) && typeof category[key] === 'string') {
          // console.log('if');
          const element = category[key];
          console.log('category[key]', key, category[key]);
          if (this.columnFilterMap.has(key)) {
            searchResult = category[key].toLowerCase().indexOf(this.columnFilterMap.get(key).toLowerCase()) > -1;
            console.log('searchResult', searchResult);
          }
          if (searchResult) {
            validFilterCount++;
            console.log('validFilterCount, this.columnFilterMap.size', validFilterCount, this.columnFilterMap.size);
            // break;
          }
        } else {
          // // console.log('else');
          if (typeof category[key] === 'number') {
            const element = category[key];
            // console.log('category[key]', category[key]);
            // searchResult = category[key].toString().toLowerCase().indexOf(searchText.toLowerCase()) > -1;
            // if (searchResult) {
            //   break;
            // }
            if (this.columnFilterMap.has(key)) {
              searchResult = category[key].toString().toLowerCase().indexOf(this.columnFilterMap.get(key).toLowerCase()) > -1;
            }
            if (searchResult) {
              validFilterCount++;
              // break;
            }
          }
        }
      }

      return validFilterCount === this.columnFilterMap.size;
      // return (
      //   this.dataTable.headerRow.forEach((key) => {
      //     return category[key].toLowerCase().indexOf(searchText.toLowerCase()) > -1;
      //   })
      // );
    });
  }
  public sortClicked(orderField: TableSort, startSortOrder?: { stopPropagation: () => void; }) {
    // // console.log('orderField', orderField);
    startSortOrder.stopPropagation();
    this.sortOrder = orderField as TableSort;

    const data = this.dataTable.tableBody.content.slice();
    // // console.log('data', data);

    if (!orderField.active || orderField.direction === '') {
      this.sortedData = data;
      return;
    }
    this.sortedData = data.sort((a, b) => {
      const isAsc = orderField.direction === 'asc';
      return this.compare(a[orderField.active], b[orderField.active], isAsc);
    });
    // // console.log(' this.sortedData', this.sortedData);
    this.dataTable.tableBody.content = this.sortedData;
    // if (orderField === 'DESC' || orderField === 'ASC') {
    //   const paginator: InfoForGetPagination = {
    //     startSortOrder,
    //     orderField,
    //     userId: this.loginUser['user'].userId,
    //     page: this.dataTable.tableBody['number'],
    //     size: this.dataTable.tableBody['size']
    //   } as InfoForGetPagination;
    //   this.sortBy.emit(paginator);
    // }
  }
  public emitPageChange(event: { pageIndex: number; pageSize: number; }) {
    // // console.log('event', event);

    if (event) {
      const paginator: InfoForGetPagination = {
        page: event.pageIndex,
        size: event.pageSize
      } as InfoForGetPagination;
      this.dataTableCopy = null;
      this.searchField.reset();
      this.pageChange.emit(paginator);
    }
  }
  public isActiveSortBtn(property: any, direction: any) {
    if (
      this.dataTable.tableBody['sort'][0].direction === direction &&
      this.dataTable.tableBody['sort'][0].property === property
    ) {
      return true;
    }
    return false;
  }
  public emitActionOnTableRecord(record: object, btnAction: string, recordIndex: number
  ) {
    // // console.log('emited currency edit record', record);
    // btnAction = btnAction.toLowerCase();
    if (record) {
      // const downloadFileType = this.getDownloadFileType(this.tableTitle);
      const data: ActionOnTableRecord = {
        record,
        btnAction,
        tableName: this.tableTitle,
        recordIndex
      };
      this.actionOnTableRecord.emit(data);
    }
  }
  private getDownloadFileType(tableTitle: string) {
    const splitTitel = tableTitle.split(' ');
    splitTitel.pop();
    splitTitel[0] = splitTitel[0].toLowerCase();
    return splitTitel.join('');
  }
  public emitDownloadExcel(excelTitel: string) {
    // const splitTitel = excelTitel.split(' ');
    // splitTitel.pop();
    // splitTitel[0] = splitTitel[0].toLowerCase();
    // const excelNm = splitTitel.join('');

    // // console.log('excelTitel', excelNm);

    this.excel.emit({ excelTitel, tableData: this.selectedTableRecordList });
  }
  public emitSearchKey(event: { [x: string]: number; }, key: string) {
    // // console.log('event', event);
    // // console.log('key', key);
    if (key && event['keyCode'] === 13) {
      this.searchKey.emit(key);
    }
  }
  public checkIsDate(key: string, tableRecord?: object) {
    this.isRecordDate = false;
    switch (key) {
      case 'checkIn':
        this.isRecordDate = true;
        break;
      case 'checkOut':
        this.isRecordDate = true;
        break;
      case 'createdDate':
        this.isRecordDate = true;
        break;
      case 'endDate':
        this.isRecordDate = true;
        break;
      case 'pickUpDate':
        this.isRecordDate = true;
        break;
      case 'startDate':
        this.isRecordDate = true;
        break;
      default:
        this.isRecordDate = false;
        break;
    }
    return this.isRecordDate;
  }
  allTableRecordSelectionChanges(event: { checked: any; }) {
    if (event.checked) {
      this.selectedTableRecordList = this.dataTable.tableBody.content;
      // // console.log('this.selectedTableRecordList', this.selectedTableRecordList);
      return;
    }
    this.selectedTableRecordList = [] as Array<object>;
    // // console.log('this.selectedTableRecordList', this.selectedTableRecordList);
  }
  tableRecordSelectionChanges(event: { checked: any; }, selectedTableRecord: object, recordIndex: any) {
    // // console.log('event, selectedTableRecord', event, selectedTableRecord);
    if (event.checked) {
      this.selectedTableRecordList.push(selectedTableRecord);
      // // console.log('this.selectedTableRecordList', this.selectedTableRecordList);
      return;
    }
    this.selectedTableRecordList = this.removeUnselectedTableRecord(selectedTableRecord, this.selectedTableRecordList);
    // // console.log('deleted List', this.selectedTableRecordList);
  }
  removeUnselectedTableRecord(removableRecord: object, originalRecordList: Array<object>) {
    return originalRecordList.filter((existedRecord: object) => {
      if (removableRecord && existedRecord) {
        return !(existedRecord['id'] === removableRecord['id']);
      }
      if (!removableRecord) {
        return true;
      }
      return false;
    });
  }
  tableSize(size: number, event?: any) {
    // // console.log('size', size);
    if (!!size) {
      this.pageSizeChanges.emit(size);
    }
  }
  uploadFile(importFileRef: HTMLInputElement) {
    // console.log('importFileRef', importFileRef);
    if (importFileRef) {
      importFileRef.click();
    }
  }
  handleUploadedFile(files: FileList) {
    console.log('files', files);
    for (const key in files) {
      if (files.hasOwnProperty(key) && key !== 'length') {
        // this.filesToUpload.push(files.item(parseInt(key, 10)));
        this.filesToUpload[0] = files.item(0);
      }
    }
    this.uploadedFiles.emit(this.filesToUpload);
    console.log('this.filesToUpload', this.filesToUpload);
  }
  downloadExport() {
    console.log('this.exportLink', this.exportLink);
    // const out = this.exportLink.slice(0, this.exportLink.length - 1).replace('&&','&');
    // console.log('out', out);

    this.httpClient.get(this.exportLink, { headers: new HttpHeaders().set('Accept', 'application/vnd.ms-excel') }).subscribe(res => {
      console.log('res', res);
    })
  }

  clickExportToExcel() {
    this.exportToExcel.emit(true)
  }
  columnLengthChanged(event) {
    console.log('columnValueChanges', event);
    this.columnLengthChanges.emit(event.target.value);
  }
}
