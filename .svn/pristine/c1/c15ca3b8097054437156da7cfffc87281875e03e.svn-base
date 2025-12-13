import { Component, OnInit, Input } from '@angular/core';
import { TableConfig, ControlsConfig, EtTableValueChangeEvent } from 'editable-table';
import { MachineDetailTableService } from './machine-detail-table.service';
import { FormControl, AbstractControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AccPacInvoicePartDetail } from '../goods-receipt-note-create/goods-receipt-note-create';
@Component({
  selector: 'app-machine-detail-table',
  templateUrl: './machine-detail-table.component.html',
  styleUrls: ['./machine-detail-table.component.css'],
  providers:[MachineDetailTableService]
})
export class MachineDetailTableComponent implements OnInit {
  @Input() machineDetailTableFormControl: FormControl;
  @Input() tableData: Array<AccPacInvoicePartDetail>;
  @Input() viewOnly:boolean;
  etHideActionBtn = true;
  tableConfig: TableConfig[];
  etControlsConfig: ControlsConfig;
  patchValueToEditableTable: { tableRowId: any; patchValue: { receiptQuantity: number; }; }[];
  @Input() grossTotalValue;
  constructor(
    private machineDetailTableService: MachineDetailTableService,
    private toastr: ToastrService,
  ) { 
    this.tableConfig = this.machineDetailTableService.getTableConfigurationJSON();
    this.etControlsConfig = this.machineDetailTableService.getControlsConfigForTableFormGroup();
  }
  ngOnInit() {
    if(this.grossTotalValue){
      this.grossTotalValue = this.grossTotalValue.toFoxed(2)
    }
  }
  editableTableSearchValueChanges(event: EtTableValueChangeEvent<object>) {
    if (event.config.formControlName === 'receiptQuantity') {
      // event.tableRow
      const isValid = this.checkReceiptQtyIsLessThanInvoiceQty(event.tableRow['receiptQuantity'], event.tableRow['invoiceQuantity']);
      this.receiptQuantityValidationHandeler(isValid, event.tableRow)
    }
    if (event.config.formControlName === 'remarks') {
      this.machineDetailTableFormControl.setValidators((control: AbstractControl)=>{
        return null;
      })
    }
    if (event.config.formControlName === 'descripancyQuantity') {
      // event.tableRow
      const isValid = this.descripancyValidators(event.tableRow['descripancyQuantity'], event.tableRow['receiptQuantity']);
      //this.receiptQuantityValidationHandeler(isValid, event.tableRow)
    }
  }
  private receiptQuantityValidationHandeler(isValid: boolean, tableRowData) {
    if (!isValid) {
      this.patchValueToEditableTable = [{
        tableRowId: tableRowData['tableRowId'],
        patchValue: {
          receiptQuantity: 0
        }
      }];
    }
  }
  private checkReceiptQtyIsLessThanInvoiceQty(receiptQty: any, invoiceQty: any) {
    if ((parseFloat(receiptQty) || 0) <= (parseFloat(invoiceQty) || 0)) {
      return true;
    }
    this.toastr.warning('The receipt qty should not greter than invoice qty', 'Wrong Value');
    return false;
  }

  private descripancyValidators(descripancyQty: any, receiptQty: any) {
    if ((parseFloat(descripancyQty) || 0) <= (parseFloat(receiptQty) || 0)) {
      return true;
    }
    this.toastr.warning('Descripancy Quantity should not exceed than Receipt Quantity', 'Wrong Value');
    return false;
  }
}
