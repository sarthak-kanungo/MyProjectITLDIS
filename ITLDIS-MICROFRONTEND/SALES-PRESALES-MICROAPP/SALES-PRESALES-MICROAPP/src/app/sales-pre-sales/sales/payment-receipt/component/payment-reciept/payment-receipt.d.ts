declare module "CreatePaymentReceiptModule" { 

    export interface PaymentReceiptDomain{
        receiptType:string;
    }
    export interface ReceiptModeDomain{
        receiptMode:string;
    }
    export interface CheckDdBankDomain{
        cheque:string;
    }
    export interface CardsDomain{
        cardType:string;
    }
    export interface BankDomain{
        bank:string;
    }
    export interface EnquiryCodeDomain{
        enquiryCode:string;
    }
    export interface EnquiryDataDomain{
        city:string;
        address1:string;
        firstName:string;
        pinCode:string;
        district:string;
        mobileNumber:string;
        tehsil:string;
        postOffice:string;
        variant:string;
        state:string;
        enquiryDate:string;
    }
    export interface AddPaymentReceiptDataDomain{
        cardName:string;
        cardNo:string;
        cardType:string;
        chequeDdBank:string;
        chequeDdDate:string;
        chequeDdNo:string;
        receiptDate:string;
        receiptMode:string;
        receiptNo: string;
        receiptType: string;
        remarks: string;
        serviceProvides: string;
        customerBalance:string;
        receiptAmount:string;
        transactionDate: string;
        transactionNo: string;
        enquiry: Enquiry;
    }
    export interface Enquiry {
        enquiryNumber: string;
    }
  export interface ReceiptTypeDomain {
        receiptAmount: number;
        customerBalance: number;
      }
}