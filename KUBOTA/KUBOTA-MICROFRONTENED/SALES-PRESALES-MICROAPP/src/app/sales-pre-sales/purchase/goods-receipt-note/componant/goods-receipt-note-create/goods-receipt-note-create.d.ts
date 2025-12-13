export interface SearchAccPacInvoiceByInvoiceNumber {
    id?: number;
    invoiceNumber: string;
    invoiceDate: string;
    billTo: string;
    shipTo: string;
    lrNo: string;
    accPacInvoicePartDetails: Array<AccPacInvoicePartDetail>;
}
export interface AccPacInvoicePartDetail {
    id?: number;
    itemNo: string;
    itemDescription: string;
    invoiceQuantity: number;
    chassisNo: string;
    engineNo: string;
    unitPrice: number;
    totalValue: number;
    receiptQuantity?: number
    descripancyQuantity?:number
}