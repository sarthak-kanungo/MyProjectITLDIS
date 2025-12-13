import { Component, OnInit, Input, OnChanges, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormArray, FormControl, FormBuilder } from '@angular/forms';
import { SoPagePresenter } from '../so-page/so-page.presenter';
import { MatDialog, MatAutocompleteSelectedEvent,MatDialogConfig,  } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { SoPageWebService } from '../so-page/so-page-web.service';
import { PartDetail, ItemNumberAuto } from '../../domain/so.domain';
import { ItemDetailTableApiService } from './item-detail-table-api.service';
import { Observable } from 'rxjs/Observable';
import * as UUID from 'uuid';
import { SelectList } from '../../../../../core/model/select-list.model';
import { element } from 'protractor';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { CustomerOrderWebService } from '../customer-order/customer-order-web.service';
import { ModalFileUploadComponent } from 'src/app/spares/Utility/modal-file-upload/modal-file-upload.component';
import { ItemErrorReportComponent } from 'src/app/spares/Utility/item-error-report/item-error-report.component';
import { ItemDetailsWebService } from 'src/app/spares/spares-purchase/spares-purchase-order/component/item-details/item-details-web.service';

export interface IItemDetailTableConfig {
  createFn: (data?: any) => FormGroup;
  fb: FormBuilder;
}
export class ItemDetailTableConfig implements IItemDetailTableConfig {
  createFn: (data?: any) => FormGroup;
  private _fb: FormBuilder;
  constructor(createFn: (data?: any) => FormGroup) {
    this.createFn = createFn;
  }

  public set fb(v: FormBuilder) {
    this._fb = v;
  }

  public get fb(): FormBuilder {
    return this._fb;
  }


}
@Component({
  selector: 'app-item-details-table',
  templateUrl: './item-details-table.component.html',
  styleUrls: ['./item-details-table.component.scss'],
  providers: [SoPageWebService, ItemDetailTableApiService, CustomerOrderWebService, ItemDetailsWebService]
})
export class ItemDetailsTableComponent implements OnInit, OnChanges {
  first: boolean;
  @Input() isView: boolean;
  @Input() isEdit: boolean;
  @Input() itemDetailList: Array<object>;
  @Input() itemDetailTable: FormArray;
  @Input() partsTotalForm: FormGroup;
  @Input() itemDetailTableConfig: ItemDetailTableConfig;
  @Input() discountRate: number;
  @Input() state: any;
  @Input() hideLinks:boolean;
  private itemDetailsArray: Array<FormGroup> = []
 
  tableForm: FormArray;
  itemNumberList$: Observable<Array<ItemNumberAuto>>;
  @Output() totalTaxChange = new EventEmitter<number>();
  @Output() totalInvoiceAmountChange = new EventEmitter<number>();
  @Output() totalBasicChange = new EventEmitter<number>();
  @Output() totalDiscountChange = new EventEmitter<number>();
  @Output() deletedRowIds = new EventEmitter<number[]>();
  tableRowSpan = [];
  delList = [];
  isOrderQtyChangedByItemDetailPopup: boolean;
  itemsIdArray = [];
  quantityArray =[]
  constructor(
    private presenter: SoPagePresenter,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private activateRoute: ActivatedRoute,
    private soPageWebService: SoPageWebService,
    private itemDetailTableApiService: ItemDetailTableApiService,
    private fb: FormBuilder,
    private customerOrderWebService: CustomerOrderWebService,
    private itemDetailsWebService: ItemDetailsWebService
  ) { }
  ngOnInit() {
      this.presenter.onDiscountRateChange.subscribe(discRate => {
              if(discRate==null)discRate=0.0;
          
              this.discountRate = discRate;
              this.recalculateItemDetailTable();
          
      })
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes.itemDetailTableConfig) {
      this.itemDetailTableConfig.fb = this.fb;
    }
    if (changes.itemDetailList && changes.itemDetailList.currentValue) {
      //this.calcTableRowSpan('itemNo', d => d.itemNo, changes.itemDetailList.currentValue);
      this.createItemDetailTableUsingDefaultList(changes.itemDetailList.currentValue);
    }
  }
  private createItemDetailTableUsingDefaultList(defaultItemList: object[]) {

    if (this.itemDetailTable) {
      this.itemDetailTable.clear();
      defaultItemList.forEach(itemDetail => {
        itemDetail['itemNo'] = {
          itemNo: itemDetail['itemNo']
        }
        this.addRow(itemDetail);
        if (itemDetail['sparePartMasterId']) {
          this.itemsIdArray.push(itemDetail['sparePartMasterId']);
        }
        if (itemDetail['issueQuantity']) {
          this.quantityArray.push(itemDetail['issueQuantity'])
        }
      })
    }
  }
  private recalculateItemDetailTable() {
    console.log('this.itemDetailTable',this.itemDetailTable);  
    if (this.itemDetailTable) {
      this.itemDetailTable.controls.forEach((itemDetailFG: FormGroup) => {
        this.issueQuantityCalculation(itemDetailFG);
      });
    }
  }
  createNewRow() {
    this.addRow();
    //this.calcTableSrNo(this.itemDetailTable.length - 1, this.tableRowSpan);
  }
  private addRow(itemDetail?: object, excelUpload?:boolean) {
    if (this.itemDetailTableConfig) {
      let fg: FormGroup;
      //if (this.isView || this.isEdit) {
        fg = this.itemDetailTableConfig.createFn(itemDetail);
      /*} else {
        fg = this.itemDetailTableConfig.createFn();
      }*/
      /*if (!fg.get('isSelected')) {
        fg.addControl('isSelected', new FormControl(null));
      }
      fg.addControl('uuid', new FormControl(UUID.v4()))
      if (itemDetail) {
        fg.patchValue(itemDetail);
        fg.get('isSelected').disable();
      }
      fg.addControl('originalQty', this.createOriginalQtyFormControl(fg.get('itemNo').value, this.itemDetailList));
      if (this.isView) {
        fg.get('originalQty').disable();
      }
      fg.addControl('firstChange', new FormControl(null));
      fg.addControl('insertedByUser', new FormControl(this.checkRecordIsInsertedByUser(itemDetail)));
      if (this.checkRowCreatedFirstTime(itemDetail)) {
        fg.get('firstChange').patchValue(true);
      }
      */
      this.itemNumberValueChanges(fg);
      this.qtyValueChanges(fg);
      this.itemDetailTable.push(fg);
      if (excelUpload) {
          fg.controls.itemNo.patchValue({'itemNo':itemDetail['itemNo'], 'id':itemDetail['sparePartMasterId']})
      }
      return fg;
    }
  }
  private qtyValueChanges(fg: FormGroup) {
      fg.get('quantity').valueChanges.subscribe(val => {
          this.issueQuantityCalculation(fg);
          
      });
    }
 /* private checkRecordIsInsertedByUser(itemDetail) {
    if (!itemDetail) {
      return true;
    }
    return false;
  }
  private checkRowCreatedFirstTime(itemDetail) {
    if (!itemDetail || itemDetail && !itemDetail.pickListNo) {
      return true;
    }
    return false;
  }
  private createOriginalQtyFormControl(itemNo, tableData = []) {
    const orderQtyAgainstItemNo = this.calcOrderQtyAgainstItemNo(tableData, itemNo);

    return new FormControl(orderQtyAgainstItemNo)
  }
  private hasRemainingItemDetailRow(fg: FormGroup): boolean {

    return !fg.get('store').value;
  }
 
  private compare(record1, record2, key?) {
    const reducer = d => (d && typeof d === 'object' && key) ? d[key] : d;
    if (reducer(record1) === reducer(record2)) {
      return true;
    }
    return false;
  }
  private hasPickListCreated(issuedDetail: object) {
    if (issuedDetail['pickListNo']) {
      return true;
    }
    return false;
  }*/
  deleteRow() {
    this.deleteRowFromTable();
    this.totalCalculationForSubTotal();
    this.totalCalculationForGstAmount();
    this.calcTotalInvoiceAmount();
  }
  deleteRowFromTable() {
    const selected = (this.itemDetailTable.controls as FormGroup[]).filter(element => element.value.isSelected);

    this.delList = [...this.delList, ...selected]
    const nonSelected = (this.itemDetailTable.controls as FormGroup[]).filter((element) => !element.value.isSelected);
    selected.forEach(element => {
      this.deletedRowIds.emit(element.value.rowId);
      //this.updateRemainingQtyIntoItemDetailTable(nonSelected, element);
    })
    this.itemDetailTable.clear();
    nonSelected.forEach(el => this.itemDetailTable.push(el));

  }
 /* private updateRemainingQtyIntoItemDetailTable(itemDetailTable: FormGroup[], deletedItemDetailFg: FormGroup) {
    const deletedQty: number = deletedItemDetailFg.get('quantity').value;
    const itemNo: any = deletedItemDetailFg.get('itemNo').value
    let index = 0;
    let insertIndex;
    for (const itemDetail of itemDetailTable) {
      if (this.compare(itemNo, itemDetail.get('itemNo').value, 'itemNo')) {
        if (this.hasRemainingItemDetailRow(itemDetail)) {

          const newQuantity = itemDetail.get('quantity').value + (deletedQty || 0);
          itemDetail.get('quantity').patchValue(newQuantity);
          return;
        }
        insertIndex = index;
      }
      index++;
    }

    this.insertItemDetailRowForRemainingOrderQty(this.itemDetailTable, deletedItemDetailFg.getRawValue(), 0, deletedQty, (insertIndex + 1));
  }*/
  /*private originalQtyValueChanges(fg: FormGroup, index) {
    fg.get('originalQty').valueChanges.subscribe(val => {

      if (this.isOrderQtyChangedByItemDetailPopup) {
        return;
      }
      this.validateOriginalQtyWithTotalPickListQty(fg, val).then(this.patchValueToQuantity(fg, val, index));
      if (fg.get('firstChange').value) {
        fg.get('quantity') && fg.get('quantity').patchValue(parseInt(`${val}`))
      }
      // fg.get('balancedQuantity') && fg.get('balancedQuantity').patchValue(val)
    });
  }*/
  /*private patchValueToQuantity(fg: FormGroup, newOrderQty, currentRowIndex) {
    return (totalIssueQtyForPickList: number) => {
      const orderQtyAgainstItemNo = this.calcOrderQtyAgainstItemNo(this.itemDetailTable.getRawValue(), fg.get('itemNo').value);
      const remainingOrderQtyFg: FormGroup = this.getRemainingOrderQty(fg, this.itemDetailTable);
      const totalIssuedQty = orderQtyAgainstItemNo - parseInt(`${remainingOrderQtyFg ? remainingOrderQtyFg.get('quantity').value : 0}`)
      if (totalIssuedQty < parseInt(`${newOrderQty}`)) {
        if (!remainingOrderQtyFg) {
          this.insertItemDetailRowForRemainingOrderQty(this.itemDetailTable, fg.getRawValue(), totalIssueQtyForPickList, parseInt(`${newOrderQty}`), (currentRowIndex + 1));
          return;
        }
        remainingOrderQtyFg.get('quantity') && remainingOrderQtyFg.get('quantity').patchValue(parseInt(`${newOrderQty}`) - totalIssuedQty);
        return;
      }
      if (totalIssuedQty > parseInt(`${newOrderQty}`)) {
        const deleteIndexList = [];
        let pickListCreatedCount = 0;
        this.itemDetailTable.controls.forEach((itemDetail, index) => {
          if (
            this.compare(itemDetail.get('itemNo').value, fg.get('itemNo').value, 'itemNo')
          ) {
            if (!itemDetail.get('pickListNo').value) {
              deleteIndexList.push(index);
              return;
            }
            pickListCreatedCount++;
          }
        });
        deleteIndexList.forEach((deleteIndex, i) => {
          this.itemDetailTable.removeAt(deleteIndex - i);
        });
        const insertIndex = (deleteIndexList[deleteIndexList.length - 1] + pickListCreatedCount) - deleteIndexList.length;

        this.insertItemDetailRowForRemainingOrderQty(this.itemDetailTable, fg.getRawValue(), totalIssueQtyForPickList, parseInt(`${newOrderQty}`), insertIndex);
        return;
      }
    }
  }*/
  /*private getRemainingOrderQty(searchFg: FormGroup, itemDetailList: FormArray) {
    if (itemDetailList && itemDetailList.length > 0) {
      return (itemDetailList.controls as FormGroup[]).find((fg) => {
        if (
          this.compare(fg.get('itemNo').value, searchFg.get('itemNo').value, 'itemNo')
          && !fg.get('store').value
        ) {
          return fg;
        }
      })
    }
    return undefined;
  }*/
 /* private validateOriginalQtyWithTotalPickListQty(fg, val) {
    return new Promise<number>((resolve, reject) => {
      let issueQty = 0;
      if (
        this.itemDetailList
        && fg.get('itemNo').value
        && this.hasAnyPickListCreatedForItemNo(this.itemDetailList, fg.get('itemNo').value)) {

        issueQty = this.calcIssueQtyAgainstItemNoHavePickList(this.itemDetailTable.getRawValue(), fg.get('itemNo').value, d => typeof d === 'object' ? d.itemNo : d);


        if (parseInt(val) < issueQty) {
          fg.get('originalQty').setErrors({ originalQtyExceed: `original qty is not less than ${issueQty}` });
          this.toastr.error(`Original qty is not less than ${issueQty}`, `Invaid Data`);

          reject();
        }
        if (fg.get('originalQty').hasError('originalQtyExceed')) {
          const errors = fg.get('originalQty').errors;
          delete errors.originalQtyExceed;
          fg.get('originalQty').setErrors(errors);
        }
      }
      resolve(issueQty);
    });

  }
  private calcIssueQtyAgainstItemNoHavePickList(itemDetailTable: any[], value, reducer: (d: any) => any): number {
    return itemDetailTable.reduce((previousValue: number, itemDetail): number => {
      if (
        (itemDetail && reducer(itemDetail.itemNo)) === reducer(value)
        && itemDetail['pickListNo']
      ) {
        if (itemDetail.issueQuantity !== undefined) {
          return (previousValue + parseFloat(`${itemDetail.issueQuantity}`)) as number;
        }
      }
      return previousValue;
    }, 0);
  }
*/  
  private itemNumberValueChanges(fg: FormGroup) {
    fg.get('itemNo').valueChanges.subscribe(val => {
      if (typeof val !== 'string') {
        return;
      }

      const tableData = this.itemDetailTable.getRawValue()
      if (tableData.length > 0) {
        tableData.forEach(element => {
          if (typeof element.itemNo === 'object' && element.itemNo) {
            this.itemsIdArray.push(element.itemNo.id);
          }
        })
      }
      this.itemNumberList$ = this.itemDetailTableApiService.searchByItemNumber(val, this.itemsIdArray.join())
    });
  }
  displayFnItemNumber(itemNo: ItemNumberAuto) {
    if (typeof itemNo === 'string') {
      return itemNo;
    }
    return itemNo ? itemNo.itemNo : undefined
  }
  optionSelectedItem(event: MatAutocompleteSelectedEvent, fg: FormGroup) {
    this.itemDetailTableApiService.getItemDetailsByItemNumber(event.option.value.itemNo, this.state).subscribe(result => {
      result.forEach(element => {
        // this.addRow(element)
        delete element['itemNo']
        fg.patchValue(element);
        fg.get('quantity').reset();
        fg.get('amount').reset();
      })
      this.itemNumberList$ = null;
      // this.orderValueChange()
    })

  }
  // fieldsDisableOnCondition(fg: FormGroup) {
  //   if (!(fg.get('invoicedFlag').value)) {
  //     fg.disable();
  //   }
  // }
  private issueQuantityCalculation(fg: FormGroup) {
    console.log('geting_quantity_value--',fg.get('quantity').value)
    
      const issueQuantity = ((fg.get('quantity').value && parseFloat(fg.get('quantity').value)) || 0);
      const unitPrice = ((fg.get('unitPrice').value && parseFloat(fg.get('unitPrice').value)) || 0);
      const amount = parseFloat((issueQuantity * unitPrice).toFixed(2));
      fg.get('amount').patchValue(amount)
      fg.get('discountPercent').patchValue(this.discountRate | 0.0);
      const discountAmount = (parseFloat(((amount * this.discountRate) / 100).toFixed(2)));
      fg.get('discountAmount').patchValue(discountAmount);
      const netAmount = amount - discountAmount;
      fg.get('netAmount').patchValue(netAmount.toFixed(2))
      fg.get('cgstAmount').patchValue((netAmount * (fg.get('cgstPercent').value ? fg.get('cgstPercent').value / 100 : 0)).toFixed(2))
      fg.get('sgstAmount').patchValue((netAmount * (fg.get('sgstPercent').value ? fg.get('sgstPercent').value / 100 : 0)).toFixed(2))
      fg.get('igstAmount').patchValue((netAmount * (fg.get('igstPercent').value ? fg.get('igstPercent').value / 100 : 0)).toFixed(2))
      if (!this.isView) {
        this.totalCalculationForSubTotal();
        this.totalCalculationForGstAmount();
        this.calcTotalInvoiceAmount();
      }
      this.quantityArray.forEach(element => {
        if (fg.get('quantity').value <= element) {
          this.toastr.error(`Quantity should be greater than ${element}`, `Invaid Data`);
          fg.get('quantity').setErrors({
            selectFromList: 'Please select from list',
          })
        }
      });
  }
  private totalCalculationForSubTotal() {
    if (this.itemDetailTable) {
      let subtotal = 0
      let discountAmount = 0
      const tableData = this.itemDetailTable.getRawValue();
      tableData.forEach(element => {
        let tempSubtotal = (element.netAmount && parseFloat(element.netAmount)) || 0
        subtotal += tempSubtotal
        discountAmount += (element.discountAmount && parseFloat(element.discountAmount)) || 0
      });
      subtotal = parseFloat(subtotal.toFixed(2));
      discountAmount = parseFloat(discountAmount.toFixed(2));
      this.totalDiscountChange.emit(discountAmount)
      this.totalBasicChange.emit(subtotal)
      this.partsTotalForm.get('subTotal').patchValue(subtotal);
    }
  }
  private totalCalculationForGstAmount() {
    let gstAmount = 0
    const tableData = this.itemDetailTable.getRawValue();
    tableData.forEach(element => {
      let cgstAmount = (element.cgstAmount && parseFloat(element.cgstAmount)) || 0;
      let sgstAmount = (element.sgstAmount && parseFloat(element.sgstAmount)) || 0;
      let igstAmount = (element.igstAmount && parseFloat(element.igstAmount)) || 0;
      let totalGst = (cgstAmount + sgstAmount + igstAmount) || 0
      gstAmount += totalGst || 0
    });
    gstAmount = parseFloat(gstAmount.toFixed(2));
    this.partsTotalForm.get('gstAmount').patchValue(gstAmount);
    this.totalTaxChange.emit(gstAmount)
  }

  private calcTotalInvoiceAmount() {
    let value = this.partsTotalForm.getRawValue()
    const calcResultTotalAmount = value.subTotal + (value.gstAmount && parseFloat(value.gstAmount) || 0)
    const totalAmount = parseFloat(calcResultTotalAmount.toFixed(2));
    this.totalInvoiceAmountChange.emit(totalAmount)
    this.partsTotalForm.get('totalInvoiceAmount').patchValue(totalAmount)
  }
  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Backspace' || event.key === 'Delete') {
      this.presenter.partsTotalForm.reset();
    }
  }
  /*openItemPopUp(tableRowFg: FormGroup, event: FocusEvent, currentRowIndex?: number) {

    (event.target as HTMLInputElement).blur();
    const tableData = tableRowFg.getRawValue();

    if (!tableData.itemNo) {
      this.toastr.error('Item number is mandatory field', `Data Not Found`);
      return;
    }
    if (!tableData.availableStock) {
      this.toastr.error('Available stock is mandatory field', `Data Not Found`);
      return;
    }
    if (!tableData.quantity && !tableData.balancedQuantity) {
      this.toastr.error(this.isApi ? 'Balanced quantity is mandatory field' : 'Order quantity is mandatory field', `Data Not Found`);
      return;
    }
    // if (tableData && tableData.itemNo && tableData.availableStock && (tableData.quantity || tableData.balancedQuantity)) {
    // }
    this.openItemDetailsPopUp(tableRowFg, currentRowIndex);
  }*/
  /*private openItemDetailsPopUp(fg: FormGroup, currentRowIndex?: number): void {
    const orderQtyAgainstItemNo = this.calcOrderQtyAgainstItemNo(this.itemDetailTable.getRawValue(), fg.get('itemNo').value);
    const itemDetalListWithSameItemNo = this.itemDetailTable.getRawValue().filter((itemDetail, index) => {
      if (typeof itemDetail.itemNo === 'object' && itemDetail.itemNo.itemNo === fg.get('itemNo').value.itemNo) {
        return true;
      }
      if (typeof itemDetail.itemNo === 'string' && itemDetail.itemNo === fg.get('itemNo').value) {
        return true;
      }
    })
    const dialogRef = this.dialog.open(ItemPopUpComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: {
        itemNumber: fg.get('itemNo').value,
        uuid: fg.get('uuid').value,
        itemDescription: fg.get('itemDescription').value,
        quantity: orderQtyAgainstItemNo,
        advancedSparePartIssue: this.advancedSparePartIssue,
        availableStock: fg.get('availableStock').value,
        apiId: this.apiId,
        discountRate: this.discountRate,
        itemDetalList: itemDetalListWithSameItemNo,
        addIssueQuantityForm: this.addIssueQuantityForm
      },
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result: { list: PartDetails[], uuid }) => {
      if (result != undefined) {
        this.isOrderQtyChangedByItemDetailPopup = true;
        this.updateItemDetailFromItemDetailTable(fg, result.uuid, result.list, orderQtyAgainstItemNo, currentRowIndex);
        this.isOrderQtyChangedByItemDetailPopup = false;
      }
    });
  }*/
  /*private calcOrderQtyAgainstItemNo(itemDetailTable: any[], value) {

    return itemDetailTable.reduce((previousValue, issuedQtyDetail, currentIndex, array) => {
      if (
        issuedQtyDetail && this.compare(issuedQtyDetail.itemNo, value, 'itemNo')
      ) {
        if (issuedQtyDetail.quantity !== undefined) {
          return (previousValue + parseFloat(`${issuedQtyDetail.quantity}`));
        }
        if (issuedQtyDetail.balancedQuantity !== undefined) {
          return (previousValue + parseFloat(`${issuedQtyDetail.balancedQuantity}`));
        }
      }
      return previousValue;
    }, 0);
  }*/
  /*private patchValueToRemainingQtyOfAPI(remainingQtyFGOfAPI: FormGroup, currentItemDetail) {
    const {
      sparePartMaster = null,
      advancedSparePartIssue = null,
      itemNo = null,
      itemDescription = null,
      uom = null,
      availableStock = null,
      reqQuantity = null,
      balancedQuantity = null,
    } = { ...currentItemDetail };
    remainingQtyFGOfAPI.patchValue({
      sparePartMaster,
      advancedSparePartIssue,
      itemNo,
      itemDescription,
      uom,
      availableStock,
      reqQuantity,
      balancedQuantity,
      issueQuantity: 0,
    });
  }*/
  /*private insertItemDetailRowForRemainingOrderQty(itemDetailTable: FormArray, currentItemDetail, totalIssuedQty: number, totalOrderQty: number, insertIndex, uuid?: number) {

    const remainingOrderQty = totalOrderQty - totalIssuedQty;
    const remainingOrderQtyFg = this.itemDetailTableConfig.createFn();
    remainingOrderQtyFg.addControl('originalQty', new FormControl(null));
    remainingOrderQtyFg.addControl('firstChange', new FormControl(false));
    remainingOrderQtyFg.addControl('insertedByUser', new FormControl(this.checkRecordIsInsertedByUser(currentItemDetail)));
    if (this.isApi) {
      currentItemDetail['balancedQuantity'] = remainingOrderQty;
      this.patchValueToRemainingQtyOfAPI(remainingOrderQtyFg, currentItemDetail);
      remainingOrderQtyFg.addControl('uuid', new FormControl(UUID.v4()))
      itemDetailTable.insert(insertIndex, remainingOrderQtyFg);
      remainingOrderQtyFg.disable();
      return;
    }
    const {
      hsnCode = null,
      availableStock = null,
      igstPercent = null,
      cgstPercent = null,
      sgstPercent = null,
      unitPrice = null,
      itemNo = null,
      itemDescription = null,
      reqQuantity = null,
      uom = null,
      originalQty = null
    } = { ...currentItemDetail };
    remainingOrderQtyFg.patchValue({
      itemNo,
      itemDescription,
      hsnCode,
      availableStock,
      igstPercent,
      cgstPercent,
      sgstPercent,
      unitPrice,
      reqQuantity,
      uom,
      originalQty,
      ...{
        quantity: remainingOrderQty,
        issueQuantity: 0,
        pickListNo: null
      }
    });
    remainingOrderQtyFg.patchValue({
      id: currentItemDetail.id,
      sparePartMasterId: currentItemDetail.sparePartMasterId
    });
    remainingOrderQtyFg.addControl('uuid', new FormControl(UUID.v4()));
    remainingOrderQtyFg.disable();
    if (this.isEdit) {
      remainingOrderQtyFg.get('issueQuantity').enable();
    }
    itemDetailTable.insert(insertIndex, remainingOrderQtyFg);

  }*/
 /* private updateItemDetailFromItemDetailTable(tableRowFg: FormGroup, uuid, list: Array<PartDetails>, orderQtyAgainstItemNo: number, currentRowIndex?: number, ) {

    const currentItemDetail = { ...tableRowFg.getRawValue() };
    const replacebleItemIndexList: number[] = this.findIndexHavingSameItemNoAndNotPickListNo(currentItemDetail.itemNo);
    if (!currentRowIndex && currentRowIndex !== 0) {
      currentRowIndex = replacebleItemIndexList[0];
    }
    const itemDetailTableList = this.itemDetailTable.getRawValue();
    if (replacebleItemIndexList && replacebleItemIndexList.length > 0) {
      // replacebleItemIndexList.forEach((replacebleIndex, index) => {
      //   
      //   const itemDetail = { ...itemDetailList[replacebleIndex], ...list[index] };
      //   const fg = this.itemDetailTable.get([replacebleIndex]) as FormGroup;
      //   
      //   fg.patchValue(itemDetail);
      //   fg.patchValue({ [this.isApi ? 'balancedQuantity' : 'quantity']: list[index] ? list[index].issueQuantity : null });
      //   if (this.isApi && list[index]) {
      //     fg.patchValue({
      //       binLocationMaster: { id: list[index].binId },
      //       storeMaster: { id: list[index].storeId },
      //       mrp: list[index].unitPrice
      //     });
      //   }
      //   if (!this.isApi) {
      //     this.issueQuantityCalculation(fg);

      //   }
      // });
      this.modifyOldRecordOfItemDetailTable(replacebleItemIndexList, list, itemDetailTableList);
      if (replacebleItemIndexList.length > list.length) {
        const deletebleItemIndexList = replacebleItemIndexList.slice(list.length);
        this.deleteOldRecordFromItemDetailTable(deletebleItemIndexList);
      }
      if (replacebleItemIndexList.length < list.length) {
        const remainingItemIssueDetailList = list.slice(replacebleItemIndexList.length);
        const itemDetail = { ...itemDetailTableList[replacebleItemIndexList[0]] };
        if (!this.isApi) {
          itemDetail.pickListNo = null;
        }
        const lastModifiedIndex = replacebleItemIndexList[replacebleItemIndexList.length - 1];
        remainingItemIssueDetailList.forEach((remainingItemDetail, index) => {
          const insertIndex = lastModifiedIndex + (index + 1);
          this.insertNewRecordIntoItemDetailTable(remainingItemDetail, list, itemDetail, insertIndex);
        })
      }
      const insertIndex = ((replacebleItemIndexList && replacebleItemIndexList[0]) || 0) + list.length;

      const modifiedItemDetailTableList = this.itemDetailTable.getRawValue();
      const totalIssuedQty = modifiedItemDetailTableList.reduce((previousValue, itemDetail) => {
        if (this.compare(currentItemDetail.itemNo, itemDetail.itemNo, 'itemNo')) {
          return (previousValue + parseFloat(`${itemDetail.issueQuantity}`));
        }
        return previousValue;
      }, 0);
      if (totalIssuedQty !== orderQtyAgainstItemNo) {
        this.insertItemDetailRowForRemainingOrderQty(this.itemDetailTable, currentItemDetail, totalIssuedQty, orderQtyAgainstItemNo, insertIndex, uuid);
      }
    }
    this.calcTableRowSpan('itemNo', d => d.itemNo, this.itemDetailTable.getRawValue());
  }*/
 /* private insertNewRecordIntoItemDetailTable(remainingItemDetail: any, list: any[], itemDetail: object, insertIndex) {
    // const remainingItemIssueDetailList = list.slice(replacebleItemIndexList.length);
    // const itemDetail = { ...itemDetailTableRecord[replacebleItemIndexList[0]] };
    // if (!this.isApi) {
    //   itemDetail.pickListNo = null;
    // }
    // const lastModifiedIndex = replacebleItemIndexList[replacebleItemIndexList.length - 1];
    // remainingItemIssueDetailList.forEach((remainingItemDetail, index) => {
    const modifiedItemDetail = { ...itemDetail, ...remainingItemDetail };
    const fg = this.itemDetailTableConfig.createFn();
    fg.addControl('originalQty', new FormControl(null));
    fg.patchValue(modifiedItemDetail);
    fg.addControl('firstChange', new FormControl(false));
    fg.patchValue({ [this.isApi ? 'balancedQuantity' : 'quantity']: remainingItemDetail ? remainingItemDetail.issueQuantity : null });
    fg.addControl('uuid', new FormControl(UUID.v4()))
    if (this.isApi && remainingItemDetail) {
      fg.patchValue({
        binLocationMaster: { id: remainingItemDetail.binId },
        storeMaster: { id: remainingItemDetail.storeId },
        mrp: remainingItemDetail.unitPrice
      });
    }
    this.itemDetailTable.insert(insertIndex, fg);
    if (!this.isApi) {
      fg.get('isSelected').enable();
      this.issueQuantityCalculation(fg);

    }
    // });
  }
  private modifyOldRecordOfItemDetailTable(replacebleItemIndexList: any[], list: any[], itemDetailTableRecord) {
    replacebleItemIndexList.forEach((replacebleIndex, index) => {

      const itemDetail = { ...itemDetailTableRecord[replacebleIndex], ...list[index] };
      console.log('itemDetail-->',itemDetail)
      if (!this.isApi && itemDetail.pickListNo) {
        return;
      }
      const fg = this.itemDetailTable.get([replacebleIndex]) as FormGroup;

      fg.patchValue(itemDetail);
      fg.patchValue({ [this.isApi ? 'balancedQuantity' : 'quantity']: list[index] ? list[index].issueQuantity : null });
      if (this.isApi && list[index]) {
        fg.patchValue({
          binLocationMaster: { id: list[index].binId },
          storeMaster: { id: list[index].storeId },
          mrp: list[index].unitPrice
        });
      }
      if (!this.isApi) {
        fg.get('isSelected').enable();
        this.issueQuantityCalculation(fg);
      }
    });
  }*/
/*  private deleteOldRecordFromItemDetailTable(deletebleItemIndexList: number[]) {
    deletebleItemIndexList.forEach((deleteIndex, index) => {
      const currectDeleteIndex = deleteIndex - index;
      const deletebleRecord = this.itemDetailTable.at(currectDeleteIndex);
      if (!this.isApi) {
        if (deletebleRecord && !deletebleRecord.get('pickListNo').value) {
          this.itemDetailTable.removeAt(currectDeleteIndex);
        }
        return;
      }
      this.itemDetailTable.removeAt(currectDeleteIndex);
    });
  }*/
/*  private findIndexHavingSameItemNoAndNotPickListNo(item): number[] {
    const itemDetailList = this.itemDetailTable.getRawValue();
    const itemIndex: number[] = [];
    itemDetailList.forEach((ele, index) => {
      if (ele['pickListNo']) {
        return;
      }
      if (typeof ele.itemNo === 'object' && ele.itemNo.itemNo === item.itemNo) {
        itemIndex.push(index);
      }
      if (typeof ele.itemNo === 'string' && ele.itemNo === item) {
        itemIndex.push(index);
      }
    });
    return [...itemIndex];
  }*/
  /**
   * mergeCommonTableCell
   */
 /* public mergeCommonTableCell(index: number): number {
    return this.tableRowSpan[index] && this.tableRowSpan[index]['itemNo'];
  }*/
  /*calcTableRowSpan(key: string, accessor: Function, listData: Array<object>) {
    this.tableRowSpan = [];
    for (let i = 0; i < listData.length;) {
      const currentValue = accessor(listData[i]);
      let count = 1;
      // Iterate through the remaining rows to see how many match
      // the current value as retrieved through the accessor.
      for (let j = i + 1; j < listData.length; j++) {
        if (currentValue != accessor(listData[j])) {
          break;
        }
        count++;
      }
      if (!this.tableRowSpan[i]) {
        this.tableRowSpan[i] = {};
      }
      // Store the number of similar values that were found (the span)
      // and skip i to the next unique row.
      //this.calcTableSrNo(i, this.tableRowSpan);
      this.tableRowSpan[i][key] = count;
      i += count;
    }
  }*/
  /*public getTableSrNo(index: number): number {
    return this.tableRowSpan[index] && this.tableRowSpan[index].srNo;
  }*/
  /*public calcTableSrNo(rowIndex: number, tableRowIndexList: any[]) {


    if (!tableRowIndexList) {
      throw new Error("Define list for maintaining serial number.");
    }
    let currentSrNo;
    for (let j = tableRowIndexList.length; j > 0; j--) {

      if (tableRowIndexList[j - 1] && tableRowIndexList[j - 1].srNo) {
        currentSrNo = tableRowIndexList[j - 1].srNo;
        break;
      }
    }
    if (!tableRowIndexList[rowIndex]) {
      tableRowIndexList[rowIndex] = {};
    }
    if (!currentSrNo) {
      tableRowIndexList[rowIndex]['srNo'] = 1;
    } else {
      tableRowIndexList[rowIndex]['srNo'] = currentSrNo + 1;
    }
  }*/
  
  public downloadTemplate() {
      this.itemDetailsWebService.downloadTemplateForPoItemDetail("Customer_Order.xlsx").subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              // let headerContentDispostion = resp.headers.get('content-disposition');
              // let fileName = headerContentDispostion.split("=")[1].trim();            
              const file = new File([resp.body], "Customer_Order.xlsx", { type: 'application/vnd.ms-excel' });
              saveAs(file);
            }
      })
    }

    public uploadExcel() {
       console.log('this.presenter.customerOrderForm.controls.customerType',this.presenter.customerOrderForm.get('customerType').value)
       if(this.presenter.customerOrderForm.get('customerType').value === null){
            this.toastr.warning('Please select Customer Type');
            return false;
        }
      //const customerType =this.customerOrderForm.get('customerType').value.customerType;
       if(this.presenter.customerOrderForm.get('state').value == null){
           this.toastr.warning('State not defined');
           return false;
       }
      const state =this.presenter.customerOrderForm.get('state').value.state
      console.log('state-->',state)
      
      //let uploadableFile = { file: file, orderType: orderType, priceType: priceType, existingItems : itemDetailsIdArray.join() }
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.id = "modal-component";
      dialogConfig.width = "500px";
      const modalDialog = this.dialog.open(ModalFileUploadComponent, dialogConfig);
      modalDialog.afterClosed().subscribe(result => {
           if(result.event === 'upload'){
               let file:File = result.data;
             
                 const itemDetailsIdArray = [];
                 const errorItemDetailsArray:Array<PartDetail> = [];
                 
                 this.itemDetailsArray.forEach((element: FormGroup) => {
                   if (typeof element.get('sparePartMaster').value === 'object' && element.get('sparePartMaster').value) {
                     itemDetailsIdArray.push(element.get('sparePartMaster').value.id);
                   }
                 });
                 if(this.discountRate == undefined || this.discountRate==null)
                     this.discountRate = 0.0;
                 let uploadableFile = { file: file, state: state, existingItems : itemDetailsIdArray.join(), discountRate : this.discountRate+"" }
                 this.customerOrderWebService.uploadExcelPoItemDetail(uploadableFile).subscribe(response => {
                   console.log('customerExcel-->',response)
                   let items:Array<PartDetail> = response['result'];
                   let subTotal:number = this.presenter.partsTotalForm.controls.subTotal.value;
                   let gstAmount:number = this.presenter.partsTotalForm.controls.gstAmount.value;
                   let totalInvoiceAmount:number = this.presenter.partsTotalForm.controls.totalInvoiceAmount.value;
                   
                   if(items!=null && items.length > 0){
                    items.forEach(data => {
                        if(data.isValidItem === 'Y'){
                            //data.itemNo = {'itemNo':data.itemNo};
                            data.sparePartMasterId = data.id;
                            data.id = null;
                            
                            this.addRow(data, true);
                            
                            console.log(data.netAmount, data.gstAmount, data.total);
                            
                            subTotal = (subTotal==null?0:subTotal) + data.netAmount;
                            gstAmount = (gstAmount==null?0:gstAmount) + data.gstAmount;
                            totalInvoiceAmount = totalInvoiceAmount + data.total;
                            
                         }else{
                             errorItemDetailsArray.push(data);
                         }
                    })
                    //this.presenter.editableTableData.patchValue(items.filter(item => item.isValidItem == 'Y'));
                }
                this.presenter.partsTotalForm.controls.subTotal.patchValue(this.convertToTwoDigitsAfterDecimal(subTotal));
                this.presenter.partsTotalForm.controls.gstAmount.patchValue(this.convertToTwoDigitsAfterDecimal(gstAmount));
                this.presenter.partsTotalForm.controls.totalInvoiceAmount.patchValue(this.convertToTwoDigitsAfterDecimal(totalInvoiceAmount));
                
                this.toastr.success(response['message']);
                if(errorItemDetailsArray.length>0){
                    
                    const dialogConfig1 = new MatDialogConfig();
                    dialogConfig1.disableClose = true;
                    dialogConfig1.id = "item-error-component";
                    dialogConfig1.width = "500px";
                    dialogConfig1.height = "350px";
                    dialogConfig1.data = errorItemDetailsArray;
                    const modalDialog = this.dialog.open(ItemErrorReportComponent, dialogConfig1);
                    
                }
                   
                 })
                 
               }});
     }

    private convertToTwoDigitsAfterDecimal(val: number): number {
      return val!=null && parseFloat(val.toFixed(2));
    }
}
