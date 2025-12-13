import { Injectable } from '@angular/core';
import { Adapter, ResponseConvertor } from '../../../../core/adapter';
import * as uuid from 'uuid';
export class InvoiceDcByCustomerCode {
  id: number;
  deliveryChallanNo: string;
  deliveryChallanDate: string;
  modelAndVariant: string;
  uuid?: string;
  constructor(parameters?: object) {
    let {
      id = null,
      deliveryChallanNo = null,
      deliveryChallanDate = null,
      modelAndVariant = null } = { ...parameters };
    this.deliveryChallanNo = deliveryChallanNo;
    this.deliveryChallanDate = deliveryChallanDate;
    this.modelAndVariant = modelAndVariant;
    this.id = id;
  }
  static mockData() {
    let dc = new InvoiceDcByCustomerCode();
    dc.deliveryChallanNo = 'deliveryChallanNo';
    dc.deliveryChallanDate = '1/1/1970';
    dc.modelAndVariant = 'static';
    dc.id = 0;
    return dc;
  }
}
export class InvoiceDcByCustomerCodeKeyMap {
  id = 'deliveryChallanId';
  uuid = 'uuid';
  deliveryChallanNo = 'deliveryChallanNumber';
  deliveryChallanDate = 'deliveryChallanDate';
  modelAndVariant = 'modelVarient';
  constructor(parameters?: object, extraProperty?: { [key: string]: string }) {

    console.log('this', this);
    if (!parameters && !extraProperty) {
      return;
    }

    let {
      id = this.id,
      deliveryChallanNo = this.deliveryChallanNo,
      deliveryChallanDate = this.deliveryChallanDate,
      modelAndVariant = this.modelAndVariant } = { ...parameters };
    this.deliveryChallanNo = deliveryChallanNo;
    this.deliveryChallanDate = deliveryChallanDate;
    this.modelAndVariant = modelAndVariant;
    this.id = id;
    for (const key in extraProperty) {
      if (extraProperty.hasOwnProperty(key)) {
        const value = extraProperty[key];
        this[key] = value;
      }
    }
  }
}

@Injectable()
export class InvoiceDcByCustomerCodeAdaptorService implements Adapter<InvoiceDcByCustomerCode[]> {
  constructor(private responseConvertor: ResponseConvertor) { }
  adapt<R>(item: any[], keyMap?: any): InvoiceDcByCustomerCode[] | R {
    return this.convertResponse(item, keyMap)
  }
  private convertResponse(response, keyMap = null): InvoiceDcByCustomerCode[] {
    const finalKeyMap = new InvoiceDcByCustomerCodeKeyMap(keyMap);
    const newCustomerByCustomerCode = [] as InvoiceDcByCustomerCode[];
    response.forEach(element => {
      let customer = this.responseConvertor.transformObject<InvoiceDcByCustomerCode>(element, finalKeyMap);
      customer.uuid = uuid.v4();
      newCustomerByCustomerCode.push(customer);
    });
    return newCustomerByCustomerCode
  }
}
