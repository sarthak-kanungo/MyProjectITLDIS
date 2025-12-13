import { SelectList } from '../../../../core/model/select-list.model';

export interface SpareInvoiceDetail {
    accpacInvoice: SpareAccpacInvoice;
    accpacInvoiceItems: SpareAccpacInvoiceItem[];
}
export interface SpareAccpacInvoice {
    invoiceValue: number;
    noOfBoxesSent: number;
    receiptValue: number;
    transportMode: string;
    transporter: string;
    invoiceDate?:Date;
}
export interface SpareAccpacInvoiceItem {
    id?: number;
    accpacOrderNo: string;
    assessableValue: number;
    cgstAmount: number;
    cgstPercent: number;
    discount: number;
    dmsPoNo: string;
    hsnCode: string;
    igstAmount: number;
    igstPercent: number;
    invoiceQty: number;
    itemDescription: string;
    itemNo: string;
    sgstAmount: number;
    sgstPercent: number;
    unitPrice: number;
    spmgst: number;
    spegst: number;
    spmrp: number;
    balancedQty: number;
    sparePartId?: number;
    sparePurchaseOrderId?:number;
    bin_locaton_id: number;
    binLocation:string
   // spareLocalPartId?: number;
}
export interface Id {
    id: number;
    binLocation?:string
}
export interface SaveSpareGrn {
    basicAmount: number;
    draftFlag: boolean;
    goodsReceiptDate: string;// goodsReceiptDate
    grnStatus: string;
    grnType: string;
    gstAmount: number;
    receiptValue: number;
    noOfBoxesReceived: number;// noOfBoxesReceived
    spareGrnNumber: string;
    sparePartGrnItems: sparePartGrnItem[];
    store: Id;// store
    supplierType: string;
    totalGrnAmount: number;
    invoiceNumber: Id; // invoiceNumber
    invoiceDate?: string;
    supplierInvoiceNumber?: string;
    sparePurchaseOrder?:any
}
export interface sparePartGrnItem {
    accpacOrderNumber: string;// accPacOrderNumber
    assessableValue: number;
    cgstAmount: number;
    cgstPercent: number;
    damagedQuantity: number;// receivedDamageQty
    damagedValue: number;// receivedDamageValue
    discount: number;
    hsnCode: string;
    igstAmount: number;
    igstPercent: number;
    invoiceQuantity: number;// invoiceQty
    nettReceivedQuantity: number;//netReceivedQty
    nettReceivedValue: number;//netReceivedValue
    receivedQuantity: number;//totalReceivedQty
    receivedValue: number;//totalReceivedValue
    sgstAmount: number;
    sgstPercent: number;
    shortQuantity: number;//receivedShortQty
    shortValue: number;//receivedShortValue
    sparePartMaster: SparePartMaster;
    unitPrice: number;
    binLocation: Id;
    netReceivedQty: number;
    sparePurchaseOrder?:any
}
export interface SparePartMaster {
    id: number;
    itemNo: number;
}
export interface SpareGrnForm {
    grnType: SelectList;
    grnNo: string;
    grnDate: string;
    grnStatus: string;
    supplyType: string;
    supplierType: SelectList;
    supplierName: string;
    supplierGstNo: string;
    transportMode: string;
    supplierAddress1: string;
    supplierAddress2: string;
    supplierState: string;
    transporter: string;
    invoiceNumber: SelectList | string;//  sparePartInvoice
    invoiceDate: string;
    invoiceValue: string;
    grnDoneType: string;
    noOfBoxesSent: string;
    noOfBoxesReceived: string;// receivedBoxesQty
    receiptValue: string;
    goodsReceiptDate: string;// grnReceiptDate
    store: SelectList;// storeMaster
}
export interface SpareItemDetailForm {
    uuid: string;
    id: string;
    sparePartMaster: SparePartMaster;
    accPacOrderNumber: string;
    dmsPoNumber: string;
    itemNumber: string;
    itemDescription: string;
    binLocation: SelectList;
    hsnCode: string;
    unitPrice: number;
    invoiceQty: number;
    assessableValue: string;
    discount: number;
    cgstPercent: number;
    cgstAmount: number;
    sgstPercent: number;
    sgstAmount: number;
    igstPercent: number;
    igstAmount: number;
    totalReceivedQty: string;
    totalReceivedValue: number;
    receivedShortQty: number;
    receivedShortValue: number;
    receivedDamageQty: string;
    receivedDamageValue: number;
    netReceivedQty: number;
    netReceivedValue: number;
}
export interface SpareItemDetailTotalForm {
    basicAmount: number;
    gstAmount: number;
    totalGrnAmount: number;
}
export interface SpareGoodsReceiptNoteForm {
    grn: SpareGrnForm;
    itemDetail: SpareItemDetailForm[];
    itemDetailTotal: SpareItemDetailTotalForm;
}
export interface SearchSpareGrn {
    fromDate: string;
    grnStatus: string;
    grnType: string;
    invoiceNumber: string;
    page: number;
    result: string;
    size: number;
    spareGrnNumber: string;
    supplierId: number;
    supplierType: string;
    toDate: string;
}