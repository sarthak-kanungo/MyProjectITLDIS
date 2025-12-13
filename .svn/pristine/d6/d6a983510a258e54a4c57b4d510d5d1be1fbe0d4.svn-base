import { Injectable } from '@angular/core';
import { FormGroup, FormArray, FormBuilder } from '@angular/forms';
import { SoPageStore } from './so-page.store';
import { SoPatchJson, PartDetail } from '../../domain/so.domain';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { Observable,BehaviorSubject } from 'rxjs';
@Injectable()
export class SoPagePresenter {
  //readonly soForm: FormGroup;
  private _operation: string;
  public onDiscountRateChange:BehaviorSubject<number> = new BehaviorSubject<number>(0);
  set operation(type: string) {
    this._operation = type;
  }
  get operation() {
    return this._operation;
  }
  
  discountRate: number;

  constructor(
     private soPageStore: SoPageStore
  ) {
    //this.soPageStore = new SoPageStore(fb);
    //this.soForm = this.soPageStore.soFormGroup;
  }
  public createSoForm(): FormGroup {
      return this.soPageStore.soFormGroup;
  }
  markFormAsTouched() {
      this.soPageStore.soFormGroup.markAllAsTouched();
  }
  get customerOrderForm() {
    return this.soPageStore.soFormGroup.get('customerOrderForm') as FormGroup;
  }
  get partsTotalForm() {
    return this.soPageStore.soFormGroup.get('partsTotal') as FormGroup;
  }
  get itemDetailsTableRow() {
    return this.soPageStore.soFormGroup.get('itemDetailsTableData') as FormGroup;
  }
  get addNewPartForm() {
    return this.soPageStore.soFormGroup.get('addNewPartForm') as FormGroup;
  }
  get createItemDetailsTableRowFn() {
    return this.soPageStore.createItemDetailsTableRow;
  }
  addRow(data: PartDetail) {
    const itemDetails = this.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray;
    itemDetails.push(this.soPageStore.createItemDetailsTableRow(data));
  }
  deleteRow() {
    const itemDetails = this.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray;
    const nonSelected = itemDetails.controls.filter(element => !element.value.isSelected);
    itemDetails.clear()
    nonSelected.forEach(el => itemDetails.push(el))
  }
  
  quotationNoSetValidation() {
    this.customerOrderForm.get('quotationNo').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  customerNameNumberValidation() {
    this.customerOrderForm.get('mobileNumber').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  dealerCodeValidation() {
    this.customerOrderForm.get('dealerCode').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  retailerValidation() {
    this.customerOrderForm.get('retailer').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  mechanicValidation() {
    this.customerOrderForm.get('mechanic').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  setErrorForCustomerName() {
    this.customerOrderForm.get('customerName').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  stateValidation() {
    this.customerOrderForm.get('state').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  districtValidation() {
    this.customerOrderForm.get('district').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  tehsilValidation() {
    this.customerOrderForm.get('tehsil').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  villageValidation() {
    this.customerOrderForm.get('village').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  pinCodeValidation() {
    this.customerOrderForm.get('pinCode').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  postOfficeValidation() {
    this.customerOrderForm.get('postOffice').setErrors({
      selectFromList: 'Please select from list',
    });
  }
  patchDataForViewOrEdit(viewResult: SoPatchJson) {
    this.customerOrderForm.patchValue(viewResult.headerResponse);;
    this.customerOrderForm.get('quotationNo').patchValue({ quotationNumber: viewResult.headerResponse.quotationNumber });
    this.customerOrderForm.get('quotationNoId').patchValue(viewResult.headerResponse.spareQuotationId);
    this.customerOrderForm.get('customerOrderDate').patchValue(new Date(viewResult.headerResponse.salesOrderDate));
    this.customerOrderForm.get('customerOrderNo').patchValue(viewResult.headerResponse.saleOrderNumber);
    this.customerOrderForm.get('mobileNumber').patchValue({ mobileNumber: viewResult.headerResponse.contactNumber });
    this.customerOrderForm.get('customerType').patchValue(viewResult.headerResponse.customerType);
    this.customerOrderForm.get('customerName').patchValue(viewResult.headerResponse.customerName);
    this.customerOrderForm.get('retailer').patchValue({ retailer: viewResult.headerResponse.retailer });
    this.customerOrderForm.get('retailerName').patchValue(viewResult.headerResponse.customerName);
    this.customerOrderForm.get('mechanic').patchValue({ mechanic: viewResult.headerResponse.retailer });
    this.customerOrderForm.get('mechanicName').patchValue(viewResult.headerResponse.customerName);
    this.customerOrderForm.get('dealerCode').patchValue({ dealerCode: viewResult.headerResponse.dealerCode });
    this.customerOrderForm.get('dealerName').patchValue(viewResult.headerResponse.customerName);
    this.customerOrderForm.get('customerAddress1').patchValue(viewResult.headerResponse.customerAddress1);
    this.customerOrderForm.get('customerAddress2').patchValue(viewResult.headerResponse.customerAddress2);
    this.customerOrderForm.get('state').patchValue({ state: viewResult.headerResponse.state });
    this.customerOrderForm.get('district').patchValue({ district: viewResult.headerResponse.district });
    this.customerOrderForm.get('tehsil').patchValue({ tehsil: viewResult.headerResponse.tehsil });
    this.customerOrderForm.get('village').patchValue({ city: viewResult.headerResponse.city });
    this.customerOrderForm.get('pinCode').patchValue({ pinCode: viewResult.headerResponse.pinCode });
    this.customerOrderForm.get('postOffice').patchValue({ postOffice: viewResult.headerResponse.postOffice });
    this.customerOrderForm.get('pinId').patchValue(viewResult.headerResponse.pinId);
    this.customerOrderForm.get('discountRate').patchValue(viewResult.headerResponse.discountRate);
    this.customerOrderForm.get('discountType').patchValue(viewResult.headerResponse.discountType);
    this.customerOrderForm.get('totalBasicValue').patchValue(viewResult.headerResponse.totalBasicValue);
    this.customerOrderForm.get('totalTaxValue').patchValue(viewResult.headerResponse.totalTaxValue);
    this.customerOrderForm.get('totalQuotationAmount').patchValue(viewResult.headerResponse.totalSalesOrderAmount);
    this.customerOrderForm.get('totalDiscountValue').patchValue(viewResult.headerResponse.totalDiscountValue);
  }
  disabledFieldForCustomerName() {
    this.customerOrderForm.get('customerType').disable();
    this.customerOrderForm.get('mobileNumber').disable();
    this.customerOrderForm.get('customerName').disable();
    this.customerOrderForm.get('customerAddress1').disable();
    this.customerOrderForm.get('customerAddress2').disable();
    // this.customerOrderForm.get('country').disable();
    this.customerOrderForm.get('state').disable();
    this.customerOrderForm.get('district').disable();
    this.customerOrderForm.get('tehsil').disable();
    this.customerOrderForm.get('village').disable();
    this.customerOrderForm.get('pinCode').disable();
    this.customerOrderForm.get('postOffice').disable();


    this.customerOrderForm.get('retailer').disable()
    this.customerOrderForm.get('mechanic').disable()
    this.customerOrderForm.get('dealerCode').disable()
  }
  disabledFieldForMobileNumber() {
    this.customerOrderForm.get('customerName').disable();
    this.customerOrderForm.get('customerAddress1').disable();
    this.customerOrderForm.get('customerAddress2').disable();
    // this.customerOrderForm.get('country').disable();
    this.customerOrderForm.get('state').disable();
    this.customerOrderForm.get('district').disable();
    this.customerOrderForm.get('tehsil').disable();
    this.customerOrderForm.get('village').disable();
    this.customerOrderForm.get('pinCode').disable();
    this.customerOrderForm.get('postOffice').disable();
  }
  enabledFieldForCustomerName(type?:string) {
    if(type==undefined){
      this.customerOrderForm.get('mobileNumber').enable();
      this.customerOrderForm.get('customerType').enable();
    }
    this.customerOrderForm.get('customerName').enable();
    this.customerOrderForm.get('customerAddress1').enable();
    this.customerOrderForm.get('customerAddress2').enable();
    // this.customerOrderForm.get('country').enable();
    this.customerOrderForm.get('state').enable();
    this.customerOrderForm.get('district').enable();
    this.customerOrderForm.get('tehsil').enable();
    this.customerOrderForm.get('village').enable();
    this.customerOrderForm.get('pinCode').enable();
    this.customerOrderForm.get('postOffice').enable();
  }
  resetFieldForCustomerName(type?:string) {
    if(type==undefined){
      this.customerOrderForm.get('mobileNumber').reset();
      this.customerOrderForm.get('customerType').reset();
    }
    this.customerOrderForm.get('customerName').reset();
    this.customerOrderForm.get('customerAddress1').reset();
    this.customerOrderForm.get('customerAddress2').reset();
    // this.customerOrderForm.get('country').reset();
    this.customerOrderForm.get('state').reset();
    this.customerOrderForm.get('district').reset();
    this.customerOrderForm.get('tehsil').reset();
    this.customerOrderForm.get('village').reset();
    this.customerOrderForm.get('pinCode').reset();
    this.customerOrderForm.get('postOffice').reset();
    this.toResetItemTable();
  }
  resetFieldForCustomerType() {
    this.customerOrderForm.get('mobileNumber').reset();
    this.customerOrderForm.get('customerName').reset();
    this.customerOrderForm.get('customerAddress1').reset();
    this.customerOrderForm.get('customerAddress2').reset();
    this.customerOrderForm.get('state').reset();
    this.customerOrderForm.get('district').reset();
    this.customerOrderForm.get('tehsil').reset();
    this.customerOrderForm.get('village').reset();
    this.customerOrderForm.get('pinCode').reset();
    this.customerOrderForm.get('postOffice').reset();
    this.customerOrderForm.get('quotationNo').reset();
    this.customerOrderForm.get('mobileNumber').reset();
    this.customerOrderForm.get('discountType').reset();
    this.customerOrderForm.get('discountRate').reset();
    this.customerOrderForm.get('totalDiscountValue').reset();
    this.customerOrderForm.get('totalBasicValue').reset();
    this.customerOrderForm.get('totalTaxValue').reset();
    this.customerOrderForm.get('totalQuotationAmount').reset();
    this.customerOrderForm.get('dealerCode').reset();
    this.customerOrderForm.get('dealerName').reset();
    this.customerOrderForm.get('retailer').reset();
    this.customerOrderForm.get('retailerName').reset();
    this.customerOrderForm.get('mechanic').reset();
    this.customerOrderForm.get('mechanicName').reset();
    this.toResetItemTable();
  }
  toResetItemTable() {
    const itemDetails = this.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray
    itemDetails.clear();
    this.partsTotalForm.reset();
  }
  patchValueForStateToCity(res) {
    this.customerOrderForm.get('state').patchValue({ state: res.state })
    this.customerOrderForm.get('district').patchValue({ district: res.district })
    this.customerOrderForm.get('tehsil').patchValue({ tehsil: res.tehsil })
    this.customerOrderForm.get('village').patchValue({ city: res.village })
    this.customerOrderForm.get('pinCode').patchValue({ pinCode: res.pinCode })
    this.customerOrderForm.get('pinId').patchValue(res.pinCodeId==null?res.pinId:res.pinCodeId);
    this.customerOrderForm.get('postOffice').patchValue({ postOffice: res.postOffice })
  }
  quotationNoSelectEvent(event: MatAutocompleteSelectedEvent) {
    const quotationId = event.option.value.id;
    const customerDetails = this.customerOrderForm.getRawValue();
    this.discountRate = customerDetails.discountRate;
    if (quotationId) {
      this.orderValueChange();
    }
  }
  orderValueChange() {
    if (this.itemDetailsTableRow.get('itemDetailsDataTable')) {
      const tableData = this.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray;
      //  console.log("tableData for PopUp====>>>> ", tableData.controls);
      tableData.controls.forEach((element: FormGroup) => {
        //  console.log("element ", element);
        element.get('quantity').valueChanges.subscribe(result => {
          //  console.log("result ", result);
          this.quantityCalculation();
        })
        element.get('issueQuantity').valueChanges.subscribe(result => {
          //  console.log("result ", result);
          this.validateIssueQty(element);
        })
      })
    }
  }
  quantityCalculation() {
    if (this.itemDetailsTableRow.get('itemDetailsDataTable')) {
      const tableData = this.itemDetailsTableRow.get('itemDetailsDataTable') as FormArray;
      //  console.log("tableData ", tableData);
      tableData.controls.forEach((element: FormGroup) => {
        element.get('amount').patchValue(((element.get('quantity').value && parseFloat(element.get('quantity').value)) || 0) * ((element.get('unitPrice').value && parseFloat(element.get('unitPrice').value)) || 0))
        element.get('discountPercent').patchValue(this.discountRate | 0)
        element.get('discountAmount').patchValue((element.get('amount').value * (this.discountRate / 100) | 0).toFixed(2));
        element.get('netAmount').patchValue(element.get('amount').value - element.get('discountAmount').value);
        element.get('cgstAmount').patchValue((element.get('netAmount').value * (element.get('cgstPercent').value ? element.get('cgstPercent').value / 100 : 0)).toFixed(2))
        element.get('sgstAmount').patchValue((element.get('netAmount').value * (element.get('sgstPercent').value ? element.get('sgstPercent').value / 100 : 0)).toFixed(2))
        element.get('igstAmount').patchValue((element.get('netAmount').value * (element.get('igstPercent').value ? element.get('igstPercent').value / 100 : 0)).toFixed(2))
      })
    }
    this.totalCalculationForSubTotal();
    this.totalCalculationForGstAmount();
    this.totalInvoiceAmount();
  }
  totalCalculationForSubTotal() {
    const tableData = this.itemDetailsTableRow.getRawValue();
    //  console.log(" this.itemDetailsTableRow.getRawValue() ", this.itemDetailsTableRow.getRawValue());
    if (tableData.itemDetailsDataTable) {
      let subtotal = 0;
      let discountAmount = 0;
      tableData.itemDetailsDataTable.forEach(element => {
        //  console.log("element ", element);
        const tempSubtotal = (element.netAmount && parseFloat(element.netAmount)) || 0;
        //  console.log("tempSubtotal ", tempSubtotal);
        subtotal += tempSubtotal;
        //  console.log("subtotal ", subtotal);
        discountAmount += (element.discountAmount && parseFloat(element.discountAmount)) || 0;
      });
      //  console.log("subtotal ", subtotal);
      this.partsTotalForm.get('subTotal').patchValue(subtotal);
      this.customerOrderForm.get('totalDiscountValue').patchValue(discountAmount.toFixed(2));
      this.customerOrderForm.get('totalBasicValue').patchValue(subtotal);
    }
  }
  totalCalculationForGstAmount() {
    const tableData = this.itemDetailsTableRow.getRawValue();
    let gstAmount = 0;
    tableData.itemDetailsDataTable.forEach(element => {
      //  console.log("element ", element);
      const cgstAmount = (element.cgstAmount && parseFloat(element.cgstAmount)) || 0;
      //  console.log("cgstAmount ", cgstAmount);
      const sgstAmount = (element.sgstAmount && parseFloat(element.sgstAmount)) || 0;
      //  console.log("sgstAmount ", sgstAmount);
      const igstAmount = (element.igstAmount && parseFloat(element.igstAmount)) || 0;
      //  console.log("igstAmount ", igstAmount);
      const totalGst = (cgstAmount + sgstAmount + igstAmount) || 0
      //  console.log("totalGst ", totalGst);
      gstAmount += totalGst || 0;
    });
    //  console.log("gstAmount ", gstAmount);
    this.partsTotalForm.get('gstAmount').patchValue(gstAmount.toFixed(2));
    this.customerOrderForm.get('totalTaxValue').patchValue(gstAmount.toFixed(2))
  }

  totalInvoiceAmount() {
    const value = this.partsTotalForm.getRawValue();
    let totalAmount = 0;
    totalAmount = value.subTotal + (value.gstAmount && parseFloat(value.gstAmount) || 0)
    //  console.log("totalAmount ", totalAmount);
    this.partsTotalForm.get('totalInvoiceAmount').patchValue(totalAmount.toFixed(2))
    this.customerOrderForm.get('totalQuotationAmount').patchValue(totalAmount.toFixed(2))
  }
  validateIssueQty(fg: FormGroup) {
    //  console.log("fg ", fg);
    const orderQty = parseInt(fg.get('quantity').value) || 0;
    //  console.log("fg.get('quantity').value ", fg.get('quantity').value);
    const issueQty = parseInt(fg.get('issueQuantity').value) || 0;
    //  console.log("fg.get('issueQuantity').value ", fg.get('issueQuantity').value);
    if (fg.get('issueQuantity').value && issueQty > orderQty) {
      fg.get('issueQuantity').setErrors({ errors: 'Shoudn\'t More than Order qty' });
      return;
    }
    fg.get('issueQuantity').setErrors(null);
  }
}
