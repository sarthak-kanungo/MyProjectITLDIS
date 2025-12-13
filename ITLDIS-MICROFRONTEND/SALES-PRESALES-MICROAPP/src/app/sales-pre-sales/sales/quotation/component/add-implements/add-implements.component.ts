import { Component, OnInit, Input, EventEmitter, Output, AfterViewInit } from '@angular/core';
import { FormBuilder, FormArray, FormGroup, FormControl } from '@angular/forms';
import { AddImplementsService } from './add-implements.service';
import { QuotationSearchDomain } from 'quotation-dto';
import { ItemNoDomain, SendValidAddImplementsFormDomain } from 'add-implements-dto';
import { TableConfig, EtDataHandlerService, EtTableValueChangeEvent, ETValidationError } from 'editable-table';
import { EditableTableHandelerService } from './editable-table-handeler.service';
import { Subject } from 'rxjs';
import { QuotationImplements } from '../../pages/quotation-create/create-quotation';
import { MatCheckboxChange } from '@angular/material';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-implements',
  templateUrl: './add-implements.component.html',
  styleUrls: ['./add-implements.component.scss'],
  providers: [
    AddImplementsService,
    EditableTableHandelerService
  ]
})
export class AddImplementsComponent implements OnInit, AfterViewInit {
  @Output() autoItemNo = new EventEmitter<string>()
  @Output() autoItDs = new EventEmitter<object>()
  @Output() optionSelectedChanges = new EventEmitter<object>();
  etValidationError: any;
  isView = false;
  editableTable_viewOnly = false;
  @Input()
  public set viewOnly(v: boolean) {
    this.editableTable_viewOnly = v;
    this.isView = v;
  }
  implementsDetailsForm: FormGroup;
  implementsDisplayForm: FormGroup;
  chargesForm: FormGroup;
  calcDisplayForm: FormGroup;
  isShowAddImplementsTable: boolean;
  tableConfig: Array<TableConfig>;
  tableData: Array<object>;
  editableTableFormGroup: object;
  private _patchValueToEditableTable;
  @Input() public set patchValueToEditableTable(v) {
    this._patchValueToEditableTable = v;
  }

  public get patchValueToEditableTable(): Array<object> {
    return this._patchValueToEditableTable
  }


  @Input() set dataAutoPopulatebyEnquiryNo(qtDt: QuotationSearchDomain) {
    if (this.implementsDisplayForm) {
      // need to remove this once web service will return actual data
      qtDt.qty = qtDt.qty || 1;
      qtDt.igst = qtDt.igst || 0;
      qtDt.sgst = qtDt.sgst || 0;
      qtDt.cgst = qtDt.cgst || 0;
     
      this.implementsDisplayForm.patchValue(qtDt)
    }
  }

  @Input() dataAutoItemNo: Array<ItemNoDomain>;
  data: Object;
  show: boolean;
  labelPosition = 'before';
  disable = true;
  tableTitle = ['Select', 'Item Description',
    'Color',
    'Qty',
    'Rate(Excluding GST)',
    'Basic Price',
    'Gross Discount',
    'GST%',
    'GST Amount',
    'Total Amount'];
  getFormData = new Subject<boolean>();
  @Output() validatedFormData = new EventEmitter<object>();
  @Input()
  public set isValidateForm(v: string) {
    if (!!v) {
      this.validateForm();
    }
  }
  editableTableFormControl = new FormControl(null);
  constructor(
    private fb: FormBuilder,
    private addImplementsService: AddImplementsService,
    private editableTableHandeler: EditableTableHandelerService,
    private etDataHandlerService: EtDataHandlerService,
    private toastr: ToastrService
  ) {
    this.tableConfig = this.editableTableHandeler.getTableConfigurationJSON();
    this.editableTableFormGroup = this.editableTableHandeler.getTableFormGroupJSON();
  }

  optionSelecteditemNo(event) {
    
  }

  ngOnInit() {
    // this.implementsDetailsForm = this.addImplementsService.implementsdetailsForm();
    this.implementsDisplayForm = this.addImplementsService.implementsDisplayFormCreate(this.isView);
    this.setQuotationListToTable();
    this.chargesForm = this.addImplementsService.chargesFormCreate();
    this.calcDisplayForm = this.addImplementsService.calcDisplayFormCreate();
    if (!this.isView) {
        this.addImplementsService.subject$.subscribe(obj => this.autoItDs.emit(obj));
    }
    this.addImplementsService.getQuotationDataForView(this.setPatchValueToEditableTable);
    
  }
  ngAfterViewInit(): void {
    if (this.isView) {
      this.implementsDisplayForm.disable();
      this.chargesForm.disable();
      this.calcDisplayForm.disable();
      
      this.calcTaxableAmountInCalcDisplayForm();
    }
  }
  private setPatchValueToEditableTable = (patchValue: object[]) => {
    
    if (patchValue.length <= 0) {
      return;
    }
    setTimeout(() => {
      this.tableData = patchValue;
    }, 1000);
    this.checkChange({ checked: true } as MatCheckboxChange)
  }

  private setQuotationListToTable() {

  }

  addRow() {
    const group = this.addImplementsService.addRow()
  }

  deleteRow() {
    this.addImplementsService.deleteRow()
  }

  checkChange(matCheckboxEvt: MatCheckboxChange) {
    if(matCheckboxEvt.source){
      matCheckboxEvt.source.setDisabledState(true);
    }
    if (!matCheckboxEvt.checked) {
      this._patchValueToEditableTable = [];
    }
    this.addImplementsService.checkChange(matCheckboxEvt.checked).subscribe(res => {
      if (res) {
        this._patchValueToEditableTable = [];
        this.tableData = []
        this.isShowAddImplementsTable = false;
        this.addImplementsService.isShowAddImplementsTable = false;
      }
    })
    this.isShowAddImplementsTable = matCheckboxEvt.checked;

  }
  editableTableSearchValueChanges(event: EtTableValueChangeEvent<object>) {
    if (this.isView) {
         return;
    }
    if (event.config.formControlName === 'itemNo') {
        if (typeof event.key === 'object') {
        this.autoItDs.emit({
          grpId: event.key.code,
          itDs: event.key.code, config: event.config
        });
        return;
      }
      this.autoItDs.emit({
        grpId: event.key,
        itDs: event.key, config: event.config
      });
      this.resetEditableTableRowWhenItemDetailChanged(event.tableRow);
      this.etValidationError = new ETValidationError(event.config.formControlName, event.tableRow['tableRowId'], 'Please select from list', { selectList: true });
    }
    if (event.config.formControlName === 'grossDiscount') {
      const tableRowData = event && event.tableRow;
      if (this.checkDiscountIsLessThanRate(event.tableRow)) {
        this.calculateTotalAmount(event.tableRow, event.key, event.config)
        this.etDataHandlerService.sendColumnNameRecord$.next(event.config.formControlName);
      } else {
        this.patchValueToEditableTable = [{
          tableRowId: tableRowData['tableRowId'],
          patchValue: {
            grossDiscount: 0
          }
        }]
      }
    }
    if (event.config.formControlName === 'rate') {
      const tableRowData = event && event.tableRow;
      if (this.checkDiscountIsLessThanRate(event.tableRow)) {
        this.calculateTotalAmount(event.tableRow, event.key, event.config)
      } else {
        this.patchValueToEditableTable = [{
          tableRowId: tableRowData['tableRowId'],
          patchValue: {
            grossDiscount: 0
          }
        }]
      }
    }
    if (
      event.config.formControlName === 'igst'
      || event.config.formControlName === 'sgst'
      || event.config.formControlName === 'cgst'
    ) {
      this.calculateTotalAmount(event.tableRow, event.key, event.config)
      // setTimeout(() => {
      //   this.etDataHandlerService.sendColumnNameRecord$.next('basicPrice');
      // }, 10);
    }
    if (
      event.config.formControlName === 'amountAfterDiscount'
      || event.config.formControlName === 'gstAmount'
          || event.config.formControlName === 'basicPrice'    
    ) {
      this.etDataHandlerService.sendColumnNameRecord$.next(event.config.formControlName);
    }
  }
  private resetEditableTableRowWhenItemDetailChanged(tableRow) {
    this.patchValueToEditableTable = [{
      tableRowId: tableRow.tableRowId,
      patchValue: {
        itemDescription: null,
        color: null,
        qty: 1,
        rate: null,
        basicPrice: null,
        grossDiscount: null,
        amountAfterDiscount: null,
        igst: null,
        sgst: null,
        cgst: null,
        gstAmount: null,
        totalAmount: null,
      }
    }]
  }
  checkDiscountIsLessThanRate(rowRecord: any) {
    if (rowRecord.grossDiscount === null || rowRecord.grossDiscount == 0) {
      return true;
    }
    return this.addImplementsService.checkDiscountIsLessThanRate(rowRecord.grossDiscount, rowRecord.rate);
  }
  calcBasePrice = (qty: number, rate: number) => qty * rate;
  calcAmountAfterDiscount = (base: number, discount: number) => base - discount;
  calcGstAmt = (amountAfterDiscount: number, gst: number) => (amountAfterDiscount * gst) / 100;
  calcTotalAmt = (base: number, gst: number, discount: number) => (base + gst) - discount;
  calculateTotalAmount(tableRowData, grossDiscount?, config?) {
    let basicPrice = this.calcBasePrice(tableRowData.qty, tableRowData.rate);
    let amountAfterDiscount = this.calcAmountAfterDiscount(basicPrice, tableRowData.grossDiscount);
    let gstAmount: number;
    if (tableRowData.igst) {
      gstAmount = this.calcGstAmt(amountAfterDiscount, tableRowData.igst);
    } else {
      gstAmount = this.calcGstAmt(amountAfterDiscount, (tableRowData.cgst || 0) + (tableRowData.sgst || 0));
    }
    let totalAmount = this.calcTotalAmt(basicPrice, gstAmount, tableRowData.grossDiscount);

    this.patchValueToEditableTable = [{
      tableRowId: tableRowData.tableRowId,
      patchValue: {
        basicPrice,
        amountAfterDiscount,
        gstAmount,
        totalAmount
      }
    }];
    // this.calcDisplayForm.patchValue({ discount })
  }
  optionSelected(event) {
    this.etValidationError = new ETValidationError(event.config.formControlName, event.tableRow['tableRowId']);
    this.optionSelectedChanges.emit(event);
  }
  tableValueChange(event) {
    event.subscribe(res => {
    
    })
  }
  private validateForm() {
    this.implementsDisplayForm.markAllAsTouched();
    this.chargesForm.markAllAsTouched();
    this.calcDisplayForm.markAllAsTouched();
    
    if (!this.isShowAddImplementsTable) {
    
      if (this.checkAllFormValid() && !this.isShowAddImplementsTable) {
        this.validatedFormData.emit({ value: this.mergeAllFormData([]), isValid: true });
        return;
      }
    }
    let subscription = this.etDataHandlerService.getValidTableRecord().subscribe(tableRecordRes => {
      subscription.unsubscribe();
      if (this.checkAllFormValid() && tableRecordRes.valid) {
        this.validatedFormData.emit({ value: this.mergeAllFormData(tableRecordRes.data), isValid: true });
        return;
      }
      this.validatedFormData.emit({ isValid: false });
    })
  }
  private checkAllFormValid(): boolean {
    if (this.implementsDisplayForm.invalid || this.chargesForm.invalid || this.calcDisplayForm.invalid) {
      return false;
    }
    return true;
  }
  private mergeAllFormData(editableTableData: Array<QuotationImplements>): SendValidAddImplementsFormDomain<QuotationImplements> {
    return {
      editableTableData,
      implementsDisplayForm: this.implementsDisplayForm.getRawValue(),
      chargesForm: this.chargesForm.getRawValue(),
      calcDisplayForm: this.calcDisplayForm.getRawValue()
    }
  }
  deletedTableRecordsFromEditableTable(deletedTableRecords) {
   
    setTimeout(() => {
      this.etDataHandlerService.sendColumnNameRecord$.next('grossDiscount');
      this.etDataHandlerService.sendColumnNameRecord$.next('amountAfterDiscount');
      this.etDataHandlerService.sendColumnNameRecord$.next('gstAmount');
      this.etDataHandlerService.sendColumnNameRecord$.next('basicPrice');
    }, 100);
  }
  calcTaxableAmountInCalcDisplayForm(){
    
  }
}