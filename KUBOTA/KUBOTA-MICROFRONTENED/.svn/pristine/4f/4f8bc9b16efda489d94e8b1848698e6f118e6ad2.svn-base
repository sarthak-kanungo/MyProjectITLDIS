import { Component, OnInit, Input, AfterViewInit, Output, EventEmitter, OnChanges, SimpleChanges, forwardRef, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { FormArray, Validators, FormBuilder, FormGroup, ControlValueAccessor, Validator, NG_VALUE_ACCESSOR, NG_VALIDATORS, AbstractControl, ValidationErrors, FormControl } from '@angular/forms';
import { of, Observable, Subject, Subscription, BehaviorSubject } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { TableConfig, PatchValue, EtTableValueChangeEvent, EtAutocompleteSelectedEvent } from './editable-table-config';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import * as uuid from 'uuid';
import { EtDataHandlerService } from './et-data-handler.service';


@Component({
  selector: 'editable-table',
  templateUrl: './editable-table.component.html',
  styleUrls: ['./editable-table.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => EditableTableComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => EditableTableComponent),
      multi: true
    }
  ]
})
export class EditableTableComponent implements OnInit, OnDestroy, OnChanges, ControlValueAccessor, Validator {
  editableTableForm: FormGroup
  isEdit: boolean;
  isView: boolean;
  filteredOptions = {};
  selectOptions = {};
  @Input() viewOnly = false;
  @Input() hideActionBtn = false;
  subscription = new Subscription();
  @Input()
  set assignListToSelect(v) {
    if (!!v) {
      v.forEach(ele => {
        this.selectOptions[ele.formControlName] = ele.list;
      });
    }
  }
  @Input()
  tableConfig = [] as Array<TableConfig>;
  @Input() set getFormData(v) {
    v.subscribe(res => {
      if (!!res) {
        this.getTableRecordChange.emit((this.editableTableForm.controls.table as FormArray).getRawValue());
      }
    });
  }
  @Output() getTableRecordChange = new EventEmitter<object>();
  @Output() optionSelected = new EventEmitter<EtAutocompleteSelectedEvent<object>>();
  @Input()
  set assignListToAutocomplete(v) {
    if (v && v.config.inputField === 'autocomplete') {
      this.filteredOptions[v.config.formControlName] = this._filter(v.searchKey, v.list, v.config.displayKey)
    }
  }
  private _patchValue: Array<PatchValue>;
  @Input() set patchValue(v: Array<PatchValue>) {
    this._patchValue = v;
  }
  @Input() set patchValueToSelect(v: Array<PatchValue>) {
    if (!!v && this.editableTableForm) {
      let form = (this.editableTableForm.controls.table as FormArray)
      v.forEach(ele => {
        if (form.controls[ele.rowIndex]) {
          form.controls[ele.rowIndex].patchValue(ele.patchValue);
        }
      });
    }
  }
  private dirtyAutocompleteColumn: TableConfig;
  private _tableData;
  @Input('tableData')
  public set tableData(v) {
    {
      if (!!v && v.length > 0) {
        this._tableData = v;
        this.createTableWithData(v)
      }
    }
  }
  private _formGroupForTableRow;
  @Output() searchValueChanges = new EventEmitter<EtTableValueChangeEvent<object>>();
  @Output() tableValueChanges = new EventEmitter<object>();
  @Input() public set formGroupForTableRow(fb) {
    this._formGroupForTableRow = { ...fb };
  }
  @Output() deletedTableRecords = new EventEmitter<Array<object>>();
  @Input() matAutocompleteDisplayFn = new Subject();
  @Input() etFormArray: FormArray;
  @Input() control: FormControl;
  constructor(
    private fb: FormBuilder,
    private etDataHandlerService: EtDataHandlerService,
    private _changeDetectorRef: ChangeDetectorRef,
  ) { }

  ngOnInit() {
    this.implementsdetailsForm();
    this.subscribeToSendColumnNameRecord();
    this.subscribeToValidateTable();
    this.control.markAsTouched = () => {
      console.log('Control marked as touched');
      this.editableTableForm.markAllAsTouched();
    };
    // this.setQuotationListToTable();
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (!!changes.patchValue && !!changes.patchValue.currentValue) {
      this._patchValue = changes.patchValue.currentValue;
      this.patchValueToTable(this._patchValue);
    }
  }

  private patchValueToTable(v) {
    if (!!v && v.length > 0) {
      let form = (this.editableTableForm.controls.table as FormArray)
      let rowIndex;
      v.forEach(ele => {
        if (ele.tableRowId) {
          for (const key in form.value) {
            if (form.value.hasOwnProperty(key)) {
              const tableRow = form.value[key];
              if (tableRow['tableRowId'] === ele.tableRowId) {
                rowIndex = key;
              }
            }
          }
        }
        rowIndex = (!rowIndex && ele.rowIndex) ? ele.rowIndex : rowIndex;
        rowIndex ? form.controls[rowIndex].patchValue(ele.patchValue) : console.error('patch row undefined');
      });
    }
  }
  private createTableWithData(tableData) {
    tableData.forEach((element, index) => {
      if (index === 0) {
        this.patchValueToTable([{ rowIndex: '0', patchValue: element }]);
        return;
      }
      this.addRow(element);
    });
  }
  // @Input() private tableData;
  private subscribeToSendColumnNameRecord() {
    this.etDataHandlerService.sendColumnNameRecord$.subscribe((formControlName) => {
      if (this.viewOnly) {
        return;
      }
      let form = (this.editableTableForm.controls.table as FormArray);
      let columnValues = [];
      const tableRecord = form.getRawValue();
      if (formControlName.toLowerCase() === 'all') {
        let sortedConfigList = this.tableConfig.filter(config => config.isNeedValueChanges);
        sortedConfigList.forEach((config) => {
          columnValues = this.collectColumnRecord(tableRecord, config.formControlName)
          this.etDataHandlerService.emitColumnRecord(columnValues, config.formControlName);
        });
        return;
      }
      columnValues = this.collectColumnRecord(tableRecord, formControlName)
      this.etDataHandlerService.emitColumnRecord(columnValues, formControlName);
    });
  }
  private collectColumnRecord(tableRecord: Array<object>, tableRowKey: string) {
    let columnValues = []
    for (const key in tableRecord) {
      if (tableRecord.hasOwnProperty(key)) {
        const tableRow = tableRecord[key];
        columnValues.push(tableRow[tableRowKey])
      }
    }
    return columnValues;
  }
  private subscribeToValidateTable() {
    const validateSbscrb = this.etDataHandlerService.validateTable().subscribe(status => {
      if (status) {
        this.editableTableForm.markAllAsTouched();
        console.log('this.editableTableForm', this.editableTableForm);

        if (this.editableTableForm.status === 'VALID' || this.editableTableForm.status === 'DISABLED') {
          this.etDataHandlerService.sendTableRecord({
            data: (this.editableTableForm.controls.table as FormArray).getRawValue(),
            valid: true
          });
          return;
        }
        this.etDataHandlerService.sendTableRecord({
          data: [],
          valid: false
        });
      }
    });
    this.subscription.add(validateSbscrb);
  }
  implementsdetailsForm() {
    if (this.etFormArray) {
      this.editableTableForm = this.fb.group({
        table: this.etFormArray
      });
    } else {
      this.editableTableForm = this.fb.group({
        table: this.fb.array([])
      });
    }
    if (
      (this.editableTableForm.controls.table as FormArray).length === 0
      && (this.tableConfig.length > 0 || this.etFormArray.length > 0)
    ) {
      this.addRow();
    }
    this.tableValueChanges.emit(this.editableTableForm.valueChanges);
  }
  implementsDetailsRow(row?: object) {
    this._formGroupForTableRow['tableRowId'] = this.fb.control(uuid.v4());
    const fg = this.fb.group(this._formGroupForTableRow);
    if (!!row && !this.viewOnly) {
      fg.patchValue(row);
    }
    if (this.viewOnly) {
      fg.disable();
      !!row ? fg.patchValue(row, { onlySelf: true, emitEvent: false }) : null;
    }
    this.tableConfig.forEach((ele: TableConfig) => {
      // fg.valueCha nges
      if (ele.inputField === 'autocomplete' || ele.isNeedValueChanges) {
        fg.controls[ele.formControlName].valueChanges.subscribe(searchValue => {
          this.searchValueChanges.emit({ key: searchValue, config: ele, tableRow: fg.getRawValue() });
          this.dirtyAutocompleteColumn = { ...ele };
        });
      }
    });
    return fg;
  }
  private _filter(value: string | object, options: Array<Object | string>, key): Array<Object | string> {
    let filterValue;
    if (!!value || value === "") {
      return options;
    }
    if (typeof value === 'string') {
      filterValue = value.toLowerCase();
    }
    if (typeof value === 'object') {
      filterValue = value[key].toLowerCase();
    }
    return options.filter(option => option[key].toLowerCase().indexOf(filterValue) === 0);
  }

  submitData() {
  }
  addRow(row?: object) {
    if (this.etFormArray) {
      console.log('editableTableForm this.etFormArray===', this.etFormArray);

      this.editableTableForm.controls.table = this.etFormArray;
      console.log('this.editableTableForm.controls.table', this.editableTableForm.controls.table);

      return;
    }
    let implementsDetails = this.editableTableForm.controls.table as FormArray
    implementsDetails.push(this.implementsDetailsRow(row));
  }
  resetTable(row?) {
    let implementsDetails = this.editableTableForm.controls.table as FormArray
    implementsDetails.clear();
    implementsDetails.push(this.implementsDetailsRow(row));
  }
  deleteRow() {
    let implementsDetails = this.editableTableForm.controls.table as FormArray;
    let deleteRecordList = [];
    let nonSelected = implementsDetails.controls.filter((machinery: FormGroup) => {
      if (machinery.value.isSelected) {
        deleteRecordList.push(machinery.getRawValue());
      }
      return !machinery.value.isSelected
    });
    this.deletedTableRecords.emit(deleteRecordList);
    implementsDetails.clear()
    nonSelected.forEach(el => implementsDetails.push(el))
  }

  private generateAutocompleteDisplayFn(config: TableConfig) {
    const key = config.patchKey || config.displayKey;
    const displayWith = (user): string | undefined => {
      return (user && typeof user === 'object') ? user[key] : user;
    }
    // return of(displayWith);
    this.matAutocompleteDisplayFn.next(displayWith);
    this._changeDetectorRef.detectChanges();
  }
  autocompleteClicked(config: TableConfig, formGroup: FormArray) {
    this.filteredOptions[config.formControlName] = [];
    this.generateAutocompleteDisplayFn(config);
    if (formGroup.controls[config.formControlName].value) {
      this.searchValueChanges.emit({ key: formGroup.controls[config.formControlName].value, config: config, tableRow: formGroup.getRawValue() });
      this.dirtyAutocompleteColumn = { ...config };
    }
  }
  valueSelectedFromAutocomplete(event: MatAutocompleteSelectedEvent, rowIndex, rowFormGroup: FormGroup, config: TableConfig) {
    let option = (event && typeof event.option.value === 'object') ? { ...event.option.value } : event.option.value || '';
    this.optionSelected.emit({ option, ...{ rowIndex, tableRow: rowFormGroup.getRawValue(), config } });
  }
  selectionChanged(event) {

  }
  contenteditableValuechange(value, rowFormGroup: FormGroup, formControlName) {
    rowFormGroup.controls[formControlName].patchValue(value);

  }
  public onTouched: () => void = () => {
    this.editableTableForm.markAllAsTouched();
  };
  writeValue(val: any): void {
    if (val && val.resetForm) {
      this.editableTableForm.reset();
      this.resetTable();
      return;
    }
    val && val.length > 0 && this.createTableWithData(val);
  }
  registerOnChange(fn: any): void {
    fn && this.editableTableForm.controls.table.valueChanges.pipe(
      map((value) => (this.editableTableForm.controls.table as FormArray).getRawValue())
    ).subscribe(fn);
  }
  registerOnTouched(fn: any): void {
    fn && (this.onTouched = fn);
  }
  setDisabledState?(isDisabled: boolean): void {
    isDisabled ? this.editableTableForm.controls.table.disable() : this.editableTableForm.controls.table.enable();
    // throw new Error("Method not implemented.");
  }
  validate(control: AbstractControl): ValidationErrors {
    return this.editableTableForm.controls.table.valid ? null : { invalidForm: { valid: false, message: "editableTableForm fields are invalid" } };
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
