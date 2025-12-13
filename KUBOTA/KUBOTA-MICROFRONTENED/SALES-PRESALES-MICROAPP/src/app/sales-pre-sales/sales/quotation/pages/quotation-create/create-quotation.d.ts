export declare interface CreateQuotation {
    amount?: number;
    basicValue: number;
    charges: number;
    color: string;
    createdBy: { id: number };
    discount: number;
    enquiry: { id: number };
    igst: number;
    cgst: number;
    sgst: number;
    grossDiscount: number;
    // grossTotal: number;
    gstAmount: number;
    id?: number;
    itemNo: string;
    qty: number;
    quotationImplements: Array<QuotationImplements>;
    rate: number;
    rto: number;
    totalAmount: number;
    totalGstAmount: number;
    quotationNumber?: string;
}
export interface QuotationImplements {
    color: string;
    grossDiscount: number;
    gstAmount: number;
    igst: number;
    cgst: number;
    sgst: number;
    id?: number;
    itemNo: string;
    itemDescription: string;
    basicPrice:number;
    qty: number;
    rate: number;
    rateGst: number;
    totalAmount: number
}
export interface CreateQuotationSubFormStatus {
    quotation: boolean;
    prospectDetails: boolean;
    addImplements: boolean;
}

export interface ViewQuotationDTO {
    createdDate: string;
    enquiry: EnquiryAtViewQuotationDTO;
    gstAmount: number;
    totalAmount: number;
    quotationNumber: string;
    qty: number;
    rate: number;
    grossDiscount: number;
    amountAfterDiscount: number;
    igst: number;
    cgst: number;
    sgst: number;
    basicPrice:number;
    basicValue: number;
    discount: number;
    totalGstAmount: number;
    charges: number;
    quotationImplements: Array<QuotationImplements>;
    itemDescription: string;
    color: string;
}
export interface EnquiryAtViewQuotationDTO {
    model: string;
    purposeOfPurchase: string;
    pinCode: string;
    postOffice: string;
    firstName: string;
    middleName: string;
    lastName: string;
    dob: string;
    address1: string;
    address2: string;
    address3: string;
    prospectType: string;
    mobileNumber: number;
    enquiryNumber: string;
    prospectCode: string;
    companyName: string;
    fatherName: string;
    whatsAppNumber: string;
    alternateMobileNumber: string;
    std: string;
    telephoneNumber: string;
    emailId: string;
    city: string;
    panNumber: string;
    product: string;
    series: string;
    enquiryDate: string;
    country: string;
    variant: string;
    source: string;
    id: number;
    state: string;
    language: string;
    customerMaster : CustomerMaster
}

export interface CustomerMaster{
    customerCode:string;
    customerType:string;
}