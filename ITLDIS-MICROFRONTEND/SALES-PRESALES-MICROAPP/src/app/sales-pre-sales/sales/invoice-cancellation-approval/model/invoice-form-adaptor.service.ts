import { Injectable } from '@angular/core';
import { SelectList } from '../../../../core/model/select-list.model';
import { Adapter, ResponseConvertor } from '../../../../core/adapter';


export class InvoiceForm {
  static readonly type = '[Invoice] InvoiceForm';
  invoiceType: SelectList;
  invoiceNumber?: string;
  invoiceDate: string;
  customerCode: SelectList;
  customerName: string;
  invoiceStatus: string;
  hypothecation: SelectList;
  constructor(parameters?: object) {
    let {
      invoiceType = null,
      invoiceNumber = null,
      invoiceDate = null,
      customerCode = null,
      customerName = null,
      invoiceStatus = null,
      hypothecation = null } = { ...parameters };
    this.customerCode = customerCode;
    this.invoiceType = invoiceType;
    this.invoiceNumber = invoiceNumber;
    this.invoiceDate = invoiceDate;
    this.invoiceStatus = invoiceStatus;
    this.customerName = customerName;
    this.hypothecation = hypothecation;
  }
  static mockData() {
    let customerByCustomerCode = new InvoiceForm();
    customerByCustomerCode.customerCode = SelectList.mockData();
    customerByCustomerCode.invoiceType = SelectList.mockData();
    customerByCustomerCode.invoiceNumber = 'invoiceNumber';
    customerByCustomerCode.invoiceDate = '1/1/1970';
    customerByCustomerCode.invoiceStatus = 'static';
    customerByCustomerCode.customerName = 'static';
    customerByCustomerCode.hypothecation = SelectList.mockData();
    return customerByCustomerCode;
  }
}
export class InvoiceFormKeyMap {
  invoiceType = 'invoiceType';
  invoiceNumber = 'invoiceNumber';
  invoiceDate = 'invoiceDate';
  customerCode = 'customerCode';
  customerName = 'customerName';
  invoiceStatus = 'invoiceStatus';
  hypothecation = 'hypothecation';
  constructor(parameters?: object, extraProperty?: { [key: string]: string }) {
    let empty = { ...parameters };
    let emptyExtraProperty = { ...extraProperty }
    if (!empty && !emptyExtraProperty) {
      return;
    }
    let {
      invoiceType = this.invoiceType,
      invoiceNumber = this.invoiceNumber,
      invoiceDate = this.invoiceDate,
      customerCode = this.customerCode,
      customerName = this.customerName,
      invoiceStatus = this.invoiceStatus,
      hypothecation = this.hypothecation } = { ...parameters };
    this.customerCode = customerCode;
    this.invoiceType = invoiceType;
    this.invoiceNumber = invoiceNumber;
    this.invoiceDate = invoiceDate;
    this.invoiceStatus = invoiceStatus;
    this.customerName = customerName;
    this.hypothecation = hypothecation;
    for (const key in extraProperty) {
      if (extraProperty.hasOwnProperty(key)) {
        const value = extraProperty[key];
        this[key] = value;
      }
    }
  }
}
@Injectable({
  providedIn: 'root'
})
export class InvoiceFormAdaptorService implements Adapter<InvoiceForm> {
  constructor(private responseConvertor: ResponseConvertor) { }
  adapt<R>(item: any, keyMap?: any): InvoiceForm | R {
    return this.convertResponse(item, keyMap)
  }
  convertResponse(response, keyMap = null): InvoiceForm {
    const finalKeyMap = new InvoiceFormKeyMap(keyMap);
    const newCustomerByCustomerCode = this.responseConvertor.transformObject<InvoiceForm>(response, finalKeyMap);
    return newCustomerByCustomerCode
  }
}
