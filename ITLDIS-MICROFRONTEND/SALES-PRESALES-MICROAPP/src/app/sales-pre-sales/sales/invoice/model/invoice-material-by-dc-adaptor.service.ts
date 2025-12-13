import { Injectable } from '@angular/core';
import { Adapter, ResponseConvertor } from '../../../../core/adapter';
import * as uuid from 'uuid';
import { SelectList } from '../../../../core/model/select-list.model';

export class InvoiceMaterialByDc {
  id: number;
  itemNumber: string;
  itemDescription: string;
  hsnCode: string;
  chassisNumber: string;
  engineNumber: string;
  rate: number;
  basicAmount: number;
  discount: number;
  quantity: number;
  gstPercent: SelectList;
  gstAmount: number;
  totalAmount: number;
  uuid?: string;
  dcuuid?: string;
  productGroup: string;
  machineInventoryId: number;
  dcId: number;
  model:string;
  csbNo:SelectList;
  constructor(parameters?: object) {
    let { id = null, itemNumber = null, itemDescription = null, hsnCode = null, quantity=null, chassisNumber = null, engineNumber = null, rate = null, discount = null, gstPercent = null, gstAmount = null, totalAmount = null, basicAmount = null, productGroup = null, dcId = null, machineInventoryId = null, model=null, csbNO=null } = { ...parameters };
    this.id = id;
    this.itemNumber = itemNumber;
    this.itemDescription = itemDescription;
    this.hsnCode = hsnCode;
    this.chassisNumber = chassisNumber;
    this.engineNumber = engineNumber;
    this.rate = rate;
    this.discount = discount;
    this.gstPercent = gstPercent;
    this.gstAmount = gstAmount;
    this.totalAmount = totalAmount;
    this.basicAmount = basicAmount;
    this.productGroup = productGroup;
    this.dcId = dcId;
    this.quantity = quantity;
    this.machineInventoryId = machineInventoryId;
    this.model = model;
    this.csbNo = csbNO;
  }
  static mockData() {
    let dc = new InvoiceMaterialByDc();
    dc.id = 0;
    dc.itemNumber = 'static';
    dc.itemDescription = 'static';
    dc.hsnCode = 'static';
    dc.chassisNumber = 'static';
    dc.engineNumber = 'static';
    dc.quantity = 0;
    dc.rate = 0;
    dc.discount = 0;
    dc.gstPercent = SelectList.mockData();
    dc.gstAmount = 0;
    dc.totalAmount = 0;
    dc.basicAmount = 0;
    dc.productGroup = 'Machinery';
    dc.machineInventoryId = 0;
    dc.dcId = 0;
    dc.model='static';
    dc.csbNo = SelectList.mockData();
    return dc;
  }
}
export class InvoiceMaterialByDcKeyMap {
  id = 'id';
  itemNumber = 'itemNumber';
  itemDescription = 'itemDescription';
  hsnCode = 'hsnCode';
  chassisNumber = 'chassisNumber';
  engineNumber = 'engineNumber';
  quantity = 'quantity';
  rate = 'rate';
  discount = 'discount';
  gstPercent = 'gstPercent';
  gstAmount = 'gstAmount';
  totalAmount = 'totalAmount';
  basicAmount = 'basicAmount';
  productGroup = 'productGroup';
  machineInventoryId = 'machineInventoryId';
  dcId = 'dcId';
  model = 'model';
  csbNo = 'csbNo';
  constructor(fixProperty?: object, extraProperty?: { [key: string]: string }) {

    if (!fixProperty && !extraProperty) {
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
export class InvoiceMaterialByDcAdaptorService implements Adapter<InvoiceMaterialByDc[]> {
  constructor(private responseConvertor: ResponseConvertor) { }
  adapt<R>(item: any, keyMap?: any): InvoiceMaterialByDc[] | R {
    return this.convertResponse(item, keyMap)
  }

  private convertResponse(response, keyMap = null): InvoiceMaterialByDc[] {
    const finalKeyMap = new InvoiceMaterialByDcKeyMap(keyMap);

    const newMaterialByDc = [] as InvoiceMaterialByDc[];
    response.forEach(element => {
      let material = this.responseConvertor.transformObject<InvoiceMaterialByDc>(element, finalKeyMap);
      material.uuid = uuid.v4();
      newMaterialByDc.push(material);
    });
    return newMaterialByDc;
  }
}
