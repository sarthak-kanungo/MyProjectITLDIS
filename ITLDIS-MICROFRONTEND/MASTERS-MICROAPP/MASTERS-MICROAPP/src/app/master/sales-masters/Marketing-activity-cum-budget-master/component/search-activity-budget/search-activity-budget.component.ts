import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { SearchActivityService } from './search-activity.service';
import { DataTable, ColumnSearch, NgswSearchTableService, ActionOnTableRecord } from 'ngsw-search-table';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { activityTypeDropDown, budgetTypeDropDown } from 'ActivityBudget';
import { BaseDto } from 'BaseDto';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, forkJoin, of, BehaviorSubject } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-search-activity-budget',
  templateUrl: './search-activity-budget.component.html',
  styleUrls: ['./search-activity-budget.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SearchActivityService,
    NgswSearchTableService
  ]
})

export class SearchActivityBudgetComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  selected = 10
  actionButtons = [];
  page = 0;
  size = 10
  public dataTable: DataTable;
  searchValue: ColumnSearch;
  public totalTableElements: number;
  activityTypes: BaseDto<Array<activityTypeDropDown>>
  budgetTypes: BaseDto<Array<budgetTypeDropDown>>
  maximumLimitList: Observable<(string | object)[]>;
  maximumDayMonthList: Observable<(string | object)[]>;
  kaiShareList: Observable<(string | object)[]>;
  private searchFormValues: object = {} as object;
  recordPerPageList: Array<number> = [5, 10, 25, 50];
  activityTypeBudget: FormGroup;
  searchFlag: boolean = true
  clearSearchRow = new BehaviorSubject<string>("");
  activeStatusNg: any
  activityTypeNg: any
  budgetTypeNg: any
  kaiShareNg: any
  maximumDayMonthNg: any
  maximumLimitNg: any
  constructor(private fb: FormBuilder,
    public dialog: MatDialog,
    private searchActivityService: SearchActivityService,
    private ngswSearchTableService: NgswSearchTableService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
  ) { }

  ngOnInit() {
    this.searchActivityTypeBudget();
    this.loadAllDropDownData().subscribe(dt => {
      this.activityTypes = dt[0] as BaseDto<Array<activityTypeDropDown>>
      this.budgetTypes = dt[1] as BaseDto<Array<budgetTypeDropDown>>

      // this.patchOrCreate()
    })
  }

  searchActivityTypeBudget() {
    this.activityTypeBudget = this.fb.group({
      activityType: [null],
      budgetType: [null],
      maximumLimit: [null],
      maximumDayMonth: [null],
      kaiShare: [null],
    })
    // this.maximumLimitChanges()
    // this.requestmaximumDayMonth()
    // this.requestkaiShare()
  }
  private maximumLimitChanges() {
    this.activityTypeBudget.get('maximumLimit').valueChanges.subscribe(changedValue => {
      this.searchActivityService.searchBymaximumLimit(changedValue).subscribe(res => {
        this.maximumLimitList = of(res['result']);
      })
    })
  }
  private requestmaximumDayMonth() {
    this.activityTypeBudget.get('maximumDayMonth').valueChanges.subscribe(changedValue => {
      this.searchActivityService.searchBymaximumDayMonth(changedValue).subscribe(res => {
        this.maximumDayMonthList = of(res['result']);
      })
    })
  }
  private requestkaiShare() {
    this.activityTypeBudget.get('kaiShare').valueChanges.subscribe(changedValue => {
      this.searchActivityService.searchBykaiShare(changedValue).subscribe(res => {
        this.kaiShareList = of(res['result']);
      })
    })
  }

  ativityTypeDropDown() {
    this.searchActivityService.getActivityTypeData().subscribe(res => {
      this.activityTypes = res as BaseDto<Array<activityTypeDropDown>>
    })
  }
  budgetTypeDropDown() {
    this.searchActivityService.getbudgetTypeData().subscribe(res => {
      this.budgetTypes = res as BaseDto<Array<budgetTypeDropDown>>

    })
  }

  searchActivityBudget() {
    this.searchFormValues = this.activityTypeBudget.value;

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
      this.searchFormValues['page'] = this.page;
      this.searchFormValues['size'] = this.size;
    }
    else {
      this.searchFormValues['page'] = this.page;
      this.searchFormValues['size'] = this.size;
    }

    const temp = this.activityTypeBudget.getRawValue()
    temp['page'] = this.page
    temp['size'] = this.size
    this.searchActivityService.searcActivityBudgetMAster(this.searchFormValues).subscribe(res => {
      console.log('searchFormValues-------------', this.searchFormValues)
      this.dataTable = this.ngswSearchTableService.convertIntoDataTable(res['result']);
      this.totalTableElements = res['count'];
    })
  }
tableClear(){
  this.activeStatusNg=""
  this.activityTypeNg=""
  this.budgetTypeNg=""
  this.kaiShareNg=""
  this.maximumDayMonthNg=""
  this.maximumLimitNg=""
}
  clear() {
    this.activityTypeBudget.reset()
    this.searchActivityBudget()
    this.clearSearchRow.next("");
    this.tableClear()
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
    console.log('emitedId', emitedId)
    this.searchActivityService.changeActiveStatus(emitedId.record['id']).subscribe(res => {
      console.log('res', res);

      this.dataTable.tableBody.content[emitedId.recordIndex]['activeStatus'] = res['result']['activeStatus'];
      this.toastr.success('Active status changed successfully!', 'Success', {
        timeOut: 1500
      });
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

  actionOnTableRecord(recordData) {
    console.log("recordData ", recordData);
    // this.actionOnTableCell.emit(recordData)
    if (!!recordData && recordData.btnAction.toLowerCase() === 'activestatus') {
      this.openConfirmDialog(recordData);
    }
  }

  pageChange(event) {
    console.log('event', event);
    this.page = event.page;
    this.size = event.size;
    console.log('page,size', this.page, this.size);
    this.searchFlag = false
    this.searchActivityBudget();
  }
  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.searchActivityService.getActivityTypeData())
    dropDownTask.push(this.searchActivityService.getbudgetTypeData())

    return forkJoin(...dropDownTask)
  }

}