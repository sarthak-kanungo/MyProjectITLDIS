import { IdMaster } from '../../../spares-purchase/spares-purchase-order/domain/spares-purchase-order.domain';

export interface SparesSalesInvoice {
    invoiceDetails: InvoiceDetails;
    itemDetails: ItemDetail[];
    labourDetails?: LabourDetail[];
    outsideChargeDetails?: OutsideChargeDetail[];
    salesOrder?:IdMaster
}
export interface InvoiceDetails {
    baseAmount: number;
    country: string;
    customerAddress1: string;
    customerAddress2: string;
    customerCode: string;
    customerName: string;
    customerType: string;
    district: string;
    id?: number;
    locality: string;
    mobileNo: string;
    pincode: number;
    saleOrderDate?: string;
    jobCardDate?: string;
    saleOrderNo: string;
    state: string;
    taxAmount: number;
    tehsil: string;
    totalInvoiceAmount: number;
    village: string;
    transportMode: string;
    transporter: string;
    tcsPercent:number;
    tcsAmount:number;
    draftFlag:boolean;
    lrNo: string;
    lrDate?: Date;
    paymentType?:string;
    sparesSalesOrderId?: number;
    serviceJobcardId?: number;
    warrantyWcrId?:number;
    referenceDocumentDate: Date;
    referenceDocument: string;
    referenceDocumentNo?: string;
    referenceId?:number;
    saleOrderOrJobCard?: IdMaster;
    salesInvoiceDate?:Date;  
    invoiceDate?:string;
    itemDetails?: ItemDetail[];   
    labourDetails?: LabourDetail[];
    outsideChargeDetails?: OutsideChargeDetail[];
    placeOfService:string
}
export interface LabourDetail{
    id?:number;
    frsCodeId:number;
    frsCode:string;
    frsCodeDesc:string;
    hsnCode: string;
    laborHour: number;
    unitPrice: number;
    discountPercent: number;
    discountAmount: number;
    netAmount: number;
    cgstAmount: number;
    cgstPercent: number;
    sgstAmount: number;
    sgstPercent: number;
    igstAmount: number;
    igstPercent: number;
    gstAmount: number;
    subTotal:number;
    totalAmount:number;
    discountEditable:string;
}
export interface OutsideChargeDetail{
    id?:number;
    serviceMtJobcodeId:number;
    jobCode:string;
    jobCodeDesc:string;
    hsnCode: string;
    unitPrice: number;
    discountPercent: number;
    discountAmount: number;
    netAmount: number;
    cgstAmount: number;
    cgstPercent: number;
    sgstAmount: number;
    sgstPercent: number;
    igstAmount: number;
    igstPercent: number;
    gstAmount: number;
    subTotal:number;
    totalAmount:number;
    discountEditable:string;
    placeOfService:string
}
export interface ItemDetail{
    id?:boolean;
    spmrp?:number;
    isSelected?:boolean;
    picklistNumber?:string;
    picklistDtlId?:number;
    sparePartMasterId?:string;
    itemNo: string;
    itemDescription: string;
    hsnCode: string;
    unitPrice: number;
    quantity: number;
    amount: number;
    discountPercent: number;
    discountAmount: number;
    netAmount: number;
    cgstAmount: number;
    cgstPercent: number;
    sgstAmount: number;
    sgstPercent: number;
    igstAmount: number;
    igstPercent: number;
    gstAmount: number;
    subTotal:number;
    totalAmount:number;
    discountEditable:string;
}
export interface Category {
    id: number
    category: string
}
export interface SearchPCRAutoComplete {
    id: number;
    code: string;
    value: string;
  }
  export interface SearchPCRAutoComplete{
    id: number;
    jobcode: string;
    value: string;
  }