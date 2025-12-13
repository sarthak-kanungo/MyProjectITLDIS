import { Injectable } from '@angular/core';
import { Adapter } from '../../../../core/adapter';
import { InvoiceForm } from './invoice-form-adaptor.service';
import { InvoiceMaterialByDc } from './invoice-material-by-dc-adaptor.service';
import { UtilsService } from '../../../../utils/utils.service';
import { DateService } from '../../../../root-service/date.service';

export class SaveInvoice {
  id:number;  
  totalBasicAmount: number;
  brand: string;
  insuranceCharges: number;
  invoiceType: string;
  rtoCharges: number;
  // bankMaster: { id: number; };
  customerMaster: { id: number; };
  insuranceCompanyMaster: { id: number; };
  salesInvoiceItems: SalesInvoiceItem[];
  salesInvoiceMachines: SalesInvoiceItem[];
  totalGstAmount: number;
  totalInvoiceAmount: number;
  others: number;
  policyStartDate?: string;
  policyExpiryDate?: string;

  constructor() {
    // this.bankMaster = {} as any;
    this.customerMaster = {} as any;
    this.insuranceCompanyMaster = {} as any;
    this.salesInvoiceItems = [] as SalesInvoiceItem[];
  }
}
export class SalesInvoiceItem {
  id:number;  
  discount: number;
  gstAmount: number;
  gstPercent: number;
  quantity: number;
  rate: number;
  salesInvoice?: {};
  totalAmount: number;
  machineInventory: {
    id: number;
  };
  machineMaster : {
      id: number;
  };
  deliveryChallan: {
    deliveryChallanId: number;
  };
  csbId:number
}
export interface UpdateFinalInvoiceRecord {
  invoiceForm: InvoiceForm;
  accessoryTableForm: Array<InvoiceMaterialByDc>;
  materialTableForm: Array<InvoiceMaterialByDc>;
  totalInvoiceCalcForm: object;
  insuranceForm: object;
}
@Injectable()
export class SaveInvoiceAdaptorService implements Adapter<SaveInvoice> {
  constructor(
    private dateService: DateService
  ) { }
  adapt<R>(item: any, keyMap?: any): SaveInvoice | R {
    return item;
  }
  saveAdapt?<R>(record: R): SaveInvoice {
    console.log('SaveInvoiceAdaptorService record', record);
    let updatedInvoice = {} as SaveInvoice;
    if (record && record['invoiceForm']) {
      let invoiceForm = this.adaptInvoiceFormDataIntoSaveInvoice(record['invoiceForm']);
      updatedInvoice = { ...updatedInvoice, ...invoiceForm};
    }
    if (record && record['materialTableForm']) {
      let updateMaterialTableForm = this.transformMaterialAndAccessoryIntoSalesInvoiceItem(record['materialTableForm'].table);
      if (updatedInvoice && !updatedInvoice.salesInvoiceMachines) {
        updatedInvoice.salesInvoiceMachines = [] as SalesInvoiceItem[];
        updatedInvoice.salesInvoiceMachines = [...updateMaterialTableForm]
      } else {
        updatedInvoice.salesInvoiceMachines = [...updatedInvoice.salesInvoiceMachines, ...updateMaterialTableForm];
      }
    }
    if (record && record['accessoryTableForm']) {
      let updateMaterialTableForm = this.transformMaterialAndAccessoryIntoSalesInvoiceItem(record['accessoryTableForm'].table);
      if (updatedInvoice && !updatedInvoice.salesInvoiceItems) {
        updatedInvoice.salesInvoiceItems = [] as SalesInvoiceItem[];
        updatedInvoice.salesInvoiceItems = [...updateMaterialTableForm]
      } else {
        updatedInvoice.salesInvoiceItems = [...updatedInvoice.salesInvoiceItems, ...updateMaterialTableForm];
      }

      updatedInvoice.salesInvoiceItems.forEach(element => {
          element.machineMaster = {id : element.machineInventory.id};
      })
      
    }
    if (record && record['totalInvoiceCalcForm']) {
      const updatedInvoiceCalc = this.partialAdaptTotalInvoiceCalcFormDataIntoSaveInvoice(record['totalInvoiceCalcForm']);
      updatedInvoice = { ...updatedInvoice, ...updatedInvoiceCalc };
    }
    if (record && record['insuranceForm']) {
      if (record['insuranceForm'].insuranceCompanyMaster && record['insuranceForm'].insuranceCompanyMaster.id) {
        updatedInvoice.insuranceCompanyMaster = {} as any;
        updatedInvoice.insuranceCompanyMaster.id = record['insuranceForm'].insuranceCompanyMaster ? record['insuranceForm'].insuranceCompanyMaster.id : null;
      }
      if (record['insuranceForm'].policyExpiryDate) {
        updatedInvoice.policyExpiryDate = this.dateService.getDateIntoDDMMYYYY(record['insuranceForm'].policyExpiryDate);
      }
      if (record['insuranceForm'].policyStartDate) {
        updatedInvoice.policyStartDate = this.dateService.getDateIntoDDMMYYYY(record['insuranceForm'].policyStartDate);
      }
    }
    return updatedInvoice as SaveInvoice;
  }
  private transformMaterialAndAccessoryIntoSalesInvoiceItem(materialTableForm: InvoiceMaterialByDc[]) {
    let salesInvoiceItems = [] as SalesInvoiceItem[];
    salesInvoiceItems = materialTableForm.map(res => {
      let salesInvoiceItems = {} as SalesInvoiceItem;
      salesInvoiceItems.deliveryChallan = {} as { deliveryChallanId: number };
      salesInvoiceItems.machineInventory = {} as { id: number };
      salesInvoiceItems.machineMaster = {} as { id: number };
      salesInvoiceItems.deliveryChallan.deliveryChallanId = res.dcId;
      salesInvoiceItems.machineInventory.id = res.machineInventoryId;
      salesInvoiceItems.machineMaster.id = res.machineInventoryId;
      salesInvoiceItems.discount = res.discount || null;
      salesInvoiceItems.gstAmount = res.gstAmount || null;
      salesInvoiceItems.gstPercent = res.gstPercent ? +res.gstPercent : null;
      salesInvoiceItems.quantity = res['qty'];
      salesInvoiceItems.rate = res.rate;
      salesInvoiceItems.totalAmount = res.totalAmount;
      salesInvoiceItems.id = res.id;
      salesInvoiceItems.csbId = res.csbNo?res.csbNo.id:null;
      return salesInvoiceItems;
    });
    return salesInvoiceItems;
  }
  private adaptInvoiceFormDataIntoSaveInvoice(updatedInvoice: InvoiceForm) {
    let partialInvoice = new SaveInvoice();
    UtilsService.deleteProperty(partialInvoice, [
      'brand',
      'insuranceCharges',
      'insuranceCompanyMaster',
      'rtoCharges',
      'salesInvoiceItems',
      'totalBasicAmount',
      'totalGstAmount',
      'totalInvoiceAmount',
    ]);
    if (updatedInvoice.hypothecation && updatedInvoice.hypothecation.id) {
      partialInvoice['bankMaster'] = {};
      partialInvoice['bankMaster'].id = updatedInvoice.hypothecation.id ? updatedInvoice.hypothecation.id : null;
    }
    partialInvoice.id = updatedInvoice.id;
    partialInvoice.invoiceType = updatedInvoice.invoiceType;
    partialInvoice.customerMaster.id = updatedInvoice.customerCode.id ? updatedInvoice.customerCode.id : null;
    return partialInvoice;
  }
  private partialAdaptTotalInvoiceCalcFormDataIntoSaveInvoice(totalInvoiceCalcForm: any) {
    // const insuranceCharges = totalInvoiceCalcForm.insuranceCharges;
    // const totalInvoiceAmount = totalInvoiceCalcForm.totalInvoiceAmount;
    // const rtoCharges = totalInvoiceCalcForm.rtoCharges;
    // const others = totalInvoiceCalcForm.others;
    const {
      insuranceCharges = null,
      totalInvoiceAmount = null,
      rtoCharges = null,
      others = null } = { ...totalInvoiceCalcForm };
    const totalBasicAmount = totalInvoiceCalcForm.baseAmount;
    const totalGstAmount = totalInvoiceCalcForm.totalGSTAmount;
    return {
      totalBasicAmount,
      insuranceCharges,
      rtoCharges,
      totalGstAmount,
      others,
      totalInvoiceAmount
    };
  }
}
