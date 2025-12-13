import { Component, OnInit, AfterViewInit, AfterContentInit, OnChanges, Input, EventEmitter, Output, ViewChild, ContentChildren, QueryList, Renderer2, SimpleChanges, ElementRef } from '@angular/core';
import { MatPaginatorIntl, MatDatepicker } from '@angular/material';
import { TableHeading, DataTable, ActionButton, TableSort, InfoForGetPagination, ActionOnTableRecord, ColumnSearchInterface } from './ngsw-search-table-dto';
import { SearchFieldDirective } from './directives/search-field.directive';
import { FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {BehaviorSubject} from 'rxjs'
export class ColumnSearch implements ColumnSearchInterface {
  constructor(public searchValue: string, public searchColumnName: string) { }
}

@Component({
  selector: 'ngsw-search-table',
  templateUrl: './ngsw-search-table.component.html',
  styleUrls: ['./ngsw-search-table.component.scss'],
  providers: [MatPaginatorIntl]
})
export class NgswSearchTableComponent implements OnInit, AfterViewInit, OnChanges, AfterContentInit {
  @Input() clearSearchRow = new BehaviorSubject<string>("");
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
  public showPartial:boolean=false;
  @Input()
  public page = 0;
  @Input()
  public size = 10;
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
  @Input()
  set searchValue(value: ColumnSearch) {
    // console.log('value', value, '--->');
    if (!(value instanceof ColumnSearch) || !value.searchColumnName) {
      return;
    }
    this.searchIntoRow(value.searchColumnName, value.searchValue);

  }
  @Output() uploadedFiles = new EventEmitter<Array<File>>();
  @Output()
  public sortBy = new EventEmitter<object>();
  @Output()
  public pageChange = new EventEmitter<InfoForGetPagination>();
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
  @ContentChildren(SearchFieldDirective, { descendants: true }) searchFieldChildren !: QueryList<SearchFieldDirective>;

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
  actionButtonsLength: number;
  filesToUpload = [];
  private columnFilterMap = new Map();
  pageIndexValue:any=1;

  constructor(private httpClient: HttpClient, private matPaginatorIntl: MatPaginatorIntl, private renderer: Renderer2) {
    // console.log('this.actionButtons', this.actionButtons)
    this.matPaginatorIntl.itemsPerPageLabel = '';
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.pageIndexValue=1;
    if(changes.dataTable!=undefined || changes.dataTable!=null)
    {
      if(changes.dataTable.previousValue!=undefined || changes.dataTable.previousValue!=null)
      {
        if(changes.dataTable.previousValue.PageIndex!=undefined || changes.dataTable.previousValue.PageIndex!=null)
        {
          for(let i=0;i<this.recordPerPageList.length;i++)
          {
            if(this.recordPerPageList[i]==changes.dataTable.previousValue.PageSize)
            {
                for(let j=0;j<changes.dataTable.previousValue.PageSize;j++)
                {
                    this.pageIndexValue=changes.dataTable.previousValue.PageSize*changes.dataTable.previousValue.PageIndex+1;
                }
            }
          }
        }
        else{
          this.pageIndexValue=1;
        }
      }
    }
    else{
      this.pageIndexValue=changes["page"].currentValue+1;
    }
     if(this.dataTable!=undefined || this.dataTable!=null)
     {
      for(let i=0;i<this.dataTable.tableBody.content.length;i++)
      {
        // this.dataTable.tableBody.content.push({pageIndexValue:this.pageIndexValue});
        this.dataTable.tableBody.content[i]['rowno']=this.pageIndexValue;
        this.pageIndexValue++;
      }
     }

    console.log('changes', changes);
    if (changes.hasOwnProperty('dataTable') && !!this.dataTable && this.dataTable.tableBody['number']) {
      this.page = this.dataTable.tableBody['number'] + 1;
      // console.log('this.page', this.page);
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
      // if (!changes.dataTable.previousValue) {
      this.dataTableCopy = JSON.parse(JSON.stringify(this.dataTable));
      console.log('changes this.dataTableCopy', this.dataTableCopy);

      // }
    }

    if(changes.dataTable!=undefined || changes.dataTable!=null)
    {
        if(changes.dataTable.previousValue!=null || changes.dataTable.previousValue!=undefined)
        {
          if(changes.dataTable.previousValue.PageIndex!=null || changes.dataTable.previousValue.PageIndex!=undefined)
          {
            this.dataTable['PageIndex']=changes.dataTable.previousValue.PageIndex    
            this.dataTable['PageSize']=changes.dataTable.previousValue.PageSize
          }   
        }
    }
  }
  ngOnInit() {
    if (this.actionButtons !== undefined) {
      this.actionButtonsLength = this.actionButtons.length;
    }
    this.clearSearchRow.subscribe(res => {
        if (this.columnFilterMap.size > 0) {
          this.dataTable.headerRow.forEach(heading => {
              this.searchIntoRow(heading['title'],"")
          })
      }
    });
  }

  ngAfterViewInit() {
    this.pageChange.emit({ page: this.page, size: this.size });
  }
  ngAfterContentInit(): void {
    // console.log('searchFieldChildren', this.searchFieldChildren);
    this.searchFieldChildren.map(res => {
      // console.log('res => searchFieldChildren ', res);
      if (res && res.searchFieldRef && res.searchFieldRef.nodeName === 'INPUT') {
        let simple = this.renderer.listen(res.searchFieldRef, 'keyup', (evt) => {
          // console.log('Clicking the button', evt);
          this.searchIntoRow(res.searchColumnName, evt.srcElement.value);
        });
      }
      if (res && res.searchFieldRef && res.searchFieldRef.nodeName === 'SELECT') {
        let simple = this.renderer.listen(res.searchFieldRef, 'change', (evt) => {
          // console.log('change', evt);
          this.searchIntoRow(res.searchColumnName, evt.srcElement.value);
        });
      }
      if (res && res.searchFieldRef && res.searchFieldRef instanceof MatDatepicker) {
        // console.log('MatDatepicker');
        res['searchFieldRef' as any].closedStream.subscribe(res => {
          // console.log('datepicker closedStream', res);
        })
      }
    })

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
    this.sortOrder.hover = '';
  }
  public searchIntoTable(searchText: string, event?: any) {
    // console.log(' this.dataTableCopy', this.dataTableCopy);
    // console.log(' this.dataTable',  this.dataTable);
    if (!searchText) {
      this.dataTable.tableBody.content = JSON.parse(JSON.stringify(this.dataTableCopy.tableBody.content));
      return;
    }
    this.dataTable.tableBody.content = this.dataTable.tableBody.content.filter((category) => {
      // console.log('category', category);
      let searchResult: boolean;
      for (const key in category) {
        if (category.hasOwnProperty(key) && typeof category[key] === 'string') {
          // console.log('if');
          const element = category[key];
          // console.log('category[key]', category[key]);
          searchResult = category[key].toLowerCase().indexOf(searchText.toLowerCase()) > -1;
          if (searchResult) {
            break;
          }
        } else {
          // console.log('else');
          if (typeof category[key] === 'number') {
            const element = category[key];
            // console.log('category[key]', category[key]);
            searchResult = category[key].toString().toLowerCase().indexOf(searchText.toLowerCase()) > -1;
            if (searchResult) {
              break;
            }
          }
        }
      }
      return searchResult;
    });
  }
  public searchIntoRow(searchColumn: string, searchText: string) {
    console.log(' this.dataTableCopy', this.dataTableCopy);
    // console.log('searchText, searchColumn', searchText, searchColumn, event, event.target['value']);
    this.columnFilterMap.set(searchColumn, searchText);

    if (!searchText) {
      this.columnFilterMap.delete(searchColumn);
    }
    if (this.columnFilterMap.size === 0) {
      this.dataTable.tableBody.content = JSON.parse(JSON.stringify(this.dataTableCopy.tableBody.content));
      return;
    }
    // console.log('this.columnFilterMap', this.columnFilterMap, this.columnFilterMap.size);
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
          // console.log('category[key]', key, category[key]);
          if (this.columnFilterMap.has(key)) {
            searchResult = category[key].toLowerCase().indexOf(this.columnFilterMap.get(key).toLowerCase()) > -1;
            // console.log('searchResult', searchResult);
          }
          if (searchResult) {
            validFilterCount++;
            // console.log('validFilterCount, this.columnFilterMap.size', validFilterCount, this.columnFilterMap.size);
            // break;
          }
        } else {
          // console.log('else');
          if (typeof category[key] === 'number') {
            const element = category[key];
            // console.log('category[key]', category[key]);
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
    });
  }
  public sortClicked(orderField: TableSort, startSortOrder?: { stopPropagation: () => void; }) {
    // console.log('orderField', orderField);
    startSortOrder.stopPropagation();
    this.sortOrder = orderField as TableSort;

    const data = this.dataTable.tableBody.content.slice();
    // console.log('data', data);

    if (!orderField.active || orderField.direction === '') {
      this.sortedData = data;
      return;
    }
    this.sortedData = data.sort((a, b) => {
      const isAsc = orderField.direction === 'asc';
      return this.compare(a[orderField.active], b[orderField.active], isAsc);
    });
    // console.log(' this.sortedData', this.sortedData);
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
    // console.log('event', event);

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
    // console.log('emited currency edit record', record);
    if (record) {
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
    this.excel.emit({ excelTitel, tableData: this.selectedTableRecordList });
  }
  public emitSearchKey(event: { [x: string]: number; }, key: string) {
    // console.log('event', event);
    // console.log('key', key);
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
      // console.log('this.selectedTableRecordList', this.selectedTableRecordList);
      return;
    }
    this.selectedTableRecordList = [] as Array<object>;
    // console.log('this.selectedTableRecordList', this.selectedTableRecordList);
  }
  tableRecordSelectionChanges(event: { checked: any; }, selectedTableRecord: object, recordIndex: any) {
    // console.log('event, selectedTableRecord', event, selectedTableRecord);
    if (event.checked) {
      this.selectedTableRecordList.push(selectedTableRecord);
      // console.log('this.selectedTableRecordList', this.selectedTableRecordList);
      return;
    }
    this.selectedTableRecordList = this.removeUnselectedTableRecord(selectedTableRecord, this.selectedTableRecordList);
    // console.log('deleted List', this.selectedTableRecordList);
  }
  removeUnselectedTableRecord(removableRecord: object, originalRecordList: Array<object>) {
    return originalRecordList.filter((existedRecord: object) => {
      if (removableRecord && existedRecord) {
        return !(existedRecord['id'] === removableRecord['id']);
      }
      if (removableRecord && existedRecord) {
        return !(existedRecord['secondId'] === removableRecord['secondId']);
      }
      if (!removableRecord) {
        return true;
      }
      return false;
    });
  }
  uploadFile(importFileRef: HTMLInputElement) {
    // console.log('importFileRef', importFileRef);
    if (importFileRef) {
      importFileRef.click();
    }
  }
  handleUploadedFile(files: FileList) {
    // console.log('files', files);
    for (const key in files) {
      if (files.hasOwnProperty(key) && key !== 'length') {
        this.filesToUpload[0] = files.item(0);
      }
    }
    this.uploadedFiles.emit(this.filesToUpload);
    // console.log('this.filesToUpload', this.filesToUpload);
  }
  downloadExport() {
    // console.log('this.exportLink', this.exportLink);
    this.httpClient.get(this.exportLink, { headers: new HttpHeaders().set('Accept', 'application/vnd.ms-excel') }).subscribe(res => {
      // console.log('res', res);
    })
  }

  clickExportToExcel() {
    this.exportToExcel.emit(true)
  }
  columnLengthChanged(event) {
    // console.log('columnValueChanges', event);
    this.columnLengthChanges.emit(event.target.value);
  }
}
