declare module "SparePaymentReceiptModule" {

    export interface CheckDdBank {
        cheque: string;
    }
    export interface Cards {
        cardType: string;
    }
    export interface Bank {
        bank: string;
    }
    export interface EnquiryCode {
        enquiryCode: string;
    }
    export interface AddPaymentReceiptData {
        cardName?: string;
        cardNo?: string;
        cardType?: string;
        chequeDdBank?: string;
        chequeDdDate?: string;
        chequeDdNo?: string;
        receiptDate?: string;
        receiptMode?: string;
        receiptNo?: string;
        receiptType?: string;
        remarks: string;
        serviceProvides: string;
        receiptAmount: string;
        transactionDate: string;
        transactionNo: string;

        payeeType: string;
        customerAddress1: string;
        customerAddress2: string;
        customerName: string;
        contactNumber: number;

        customerMaster?: IdMaster;
        partyMaster?: IdMaster;
        coDealer?: IdMaster;

        country: string;
        state: string;
        district: string;
        tehsil: string;
        village: string;
        pinCode: string;
        postOffice: string;

        customerDealerMechanicCode?: IdMaster;
    }
    export interface Enquiry {
        enquiryNumber: string;
    }
    export interface ReceiptType {
        receiptAmount: number;
        customerBalance: number;
    }
    export interface IdMaster {
        id: number,
        value: string
    }
    export interface SearchSparesPaymentReceipt {
        customerContactNumber: string;
        receiptNumber: string;
        customerName: string;
        receiptMode: string;
        fromDate: string;
        toDate: string;
        page: number;
        size: number
    }
}