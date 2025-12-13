import { Component, OnInit, Input } from '@angular/core';
import { FormControl, AbstractControl } from '@angular/forms';
import { AccPacInvoicePartDetail } from '../goods-receipt-note-create/goods-receipt-note-create';
import { TableConfig, ControlsConfig, EtTableValueChangeEvent } from 'editable-table';
import { AccessoryDetailTableService } from './accessory-detail-table.service';
import { ToastrService } from 'ngx-toastr';
import { ValidateMax } from 'src/app/utils/custom-validators';

@Component({
  selector: 'app-accessory-detail-table',
  templateUrl: './accessory-detail-table.component.html',
  styleUrls: ['./accessory-detail-table.component.css'],
  providers:[AccessoryDetailTableService]
})
export class AccessoryDetailTableComponent implements OnInit {
  @Input() accessoryDetailTableFormControl: FormControl;
  @Input() tableData: Array<AccPacInvoicePartDetail>;
  @Input() viewOnly:boolean;
  etHideActionBtn = true;
  tableConfig: TableConfig[];
  etControlsConfig: ControlsConfig;
  patchValueToEditableTable: { tableRowId: any; patchValue: { receiptQuantity: number }; }[];
  @Input() grossTotalValue;
  constructor(
    private accessoryDetailTableService: AccessoryDetailTableService,
    private toastr: ToastrService,
  ) { 
    this.tableConfig = this.accessoryDetailTableService.getTableConfigurationJSON();
    this.etControlsConfig = this.accessoryDetailTableService.getControlsConfigForTableFormGroup();
  }

  ngOnInit() {
  }
  editableTableSearchValueChanges(event: EtTableValueChangeEvent<object>) {
    console.log("event ", event);
    if (event.config.formControlName === 'receiptQuantity') {
      // event.tableRow
      const isValid = this.checkReceiptQtyIsLessThanInvoiceQty(event.tableRow['receiptQuantity'], event.tableRow['invoiceQuantity']);
      this.receiptQuantityValidationHandeler(isValid, event.tableRow)
    }
    if (event.config.formControlName === 'remarks') {
      this.accessoryDetailTableFormControl.setValidators((control: AbstractControl)=>{
        console.log('control: ', control);
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
