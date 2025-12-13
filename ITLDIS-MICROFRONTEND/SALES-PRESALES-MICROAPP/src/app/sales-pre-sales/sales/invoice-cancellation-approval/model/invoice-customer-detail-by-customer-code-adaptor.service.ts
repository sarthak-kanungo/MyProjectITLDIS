import { Injectable } from '@angular/core';
import { SelectList } from '../../../../core/model/select-list.model';
import { Adapter, ResponseConvertor } from '../../../../core/adapter';

export class CustomerByCustomerCode {
  id: number;
  customerType: string;
  companyName: string;
  customerCode: string;
  customerName: string;
  invoicingCustomerName: string;
  address1: string;
  address2: string;
  address3: string;
  pinCode: string;
  postOffice: string;
  mandal: string;
  district: string;
  city: string;
  state: string;
  mobileNumber: string;
  panNumber: string;
  gstInNumber: string;
  constructor(fixProperty?: object) {
    let {
      customerType = null, companyName = null, customerCode = null, customerName = null, invoicingCustomerName = null, address1 = null, address2 = null, address3 = null, pinCode = null, postOffice = null, mandal = null, district = null, city = null, state = null, mobileNumber = null, panNumber = null, gstInNumber = null } = { ...fixProperty };
    this.customerType = customerType
    this.companyName = companyName
    this.customerCode = customerCode
    this.customerName = customerName
    this.invoicingCustomerName = invoicingCustomerName
    this.address1 = address1
    this.address2 = address2
    this.address3 = address3
    this.pinCode = pinCode
    this.postOffice = postOffice
    this.mandal = mandal
    this.district = district
    this.city = city
    this.state = state
    this.mobileNumber = mobileNumber
    this.panNumber = panNumber
    this.gstInNumber = gstInNumber
  }
  static mockData() {
    let customerByCustomerCode = new CustomerByCustomerCode();
    customerByCustomerCode.customerType = 'static';
    customerByCustomerCode.companyName = 'static';
    customerByCustomerCode.customerCode = 'static';
    customerByCustomerCode.customerName = 'static';
    customerByCustomerCode.invoicingCustomerName = 'static';
    customerByCustomerCode.address1 = 'static';
    customerByCustomerCode.address2 = 'static';
    customerByCustomerCode.address3 = 'static';
    customerByCustomerCode.pinCode = 'static';
    customerByCustomerCode.postOffice = 'static';
    customerByCustomerCode.mandal = 'static';
    customerByCustomerCode.district = 'static';
    customerByCustomerCode.city = 'static';
    customerByCustomerCode.state = 'static';
    customerByCustomerCode.mobileNumber = 'static';
    customerByCustomerCode.panNumber = 'static';
    customerByCustomerCode.gstInNumber = 'static';
    customerByCustomerCode.id = 0;
    return customerByCustomerCode;
  }
}
export class CustomerByCustomerCodeKeyMap {
  id: 'id';
  customerType = 'customerType';
  companyName = 'companyName';
  customerCode = 'customerCode';
  customerName = 'customerName';
  invoicingCustomerName = 'invoicingCustomerName';
  address1 = 'address1';
  address2 = 'address2';
  address3 = 'address3';
  pinCode = 'pinCode';
  postOffice = 'postOffice';
  mandal = 'mandal';
  district = 'district';
  city = 'city';
  state = 'state';
  mobileNumber = 'mobileNumber';
  panNumber = 'panNumber';
  gstInNumber = 'gstInNumber';
  constructor(fixProperty?: object, extraProperty?: { [key: string]: string }) {
    let empty = { ...fixProperty };
    let emptyExtraProperty = { ...extraProperty }
    if (!empty && !emptyExtraProperty) {
      return;
    }
    const fixPropertyMap = new Map(Object.entries(fixProperty))
    for (var [key, value] of fixPropertyMap) {
      console.log(key + ' = ' + value);
      this[key] = value;
    }
    for (const key in extraProperty) {
      if (extraProperty.hasOwnProperty(key)) {
        const value = extraProperty[key];
        this[key] = value;
      }
    }
  }
}
@Injectable()
export class InvoiceCustomerDetailByCustomerCodeAdaptorService implements Adapter<CustomerByCustomerCode> {
  constructor(private responseConvertor: ResponseConvertor) { }
  adapt<R>(item: any, keyMap?: any): CustomerByCustomerCode | R {
    return this.convertResponse(item, keyMap)
  }
  convertResponse(response, keyMap = null) {
    const finalKeyMap = new CustomerByCustomerCodeKeyMap(keyMap);
    const newCustomerByCustomerCode = this.responseConvertor.transformObject<CustomerByCustomerCode>(response, finalKeyMap);
    console.log('newCustomerByCustomerCode', newCustomerByCustomerCode);
    return null
  }
}
