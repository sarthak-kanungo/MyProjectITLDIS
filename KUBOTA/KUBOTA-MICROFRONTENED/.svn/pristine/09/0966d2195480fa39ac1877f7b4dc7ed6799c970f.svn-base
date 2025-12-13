declare module "search-payment-receipt-dto" {
    import { Enquiry } from "CreatePaymentReceiptModule";
    import { EnquiryDomain } from "EnquiryCreation";
    export interface ReceiptModeSearchDomain {
        receiptMode: string;
    }
    export interface ProductDomain {
        product: string;
    }

    export interface SeriesDomain {
        series: string;
    }
    export interface ModelDomain {
        model: string;
    }
    export interface SubModelDomain {
        subModel: string;
    }
    export interface VariantDomain {
        variant: string;
    }

    export interface ReceiptNumberDomain {
        receiptNumber: string;
        displayValue: string
    }

    // export interface PaymentReceiptListSearchDomain {
    //     receiptMode:string;
    //     product:string;
    //     series:string;
    //     model:string;
    //     subModel:string;
    //     variant:string;


    // }
    export interface PaymentReceiptListSearchDomain {
        remarks: string;
        enquiryStatus: string;
        enquiryDate?: any;
        receiptDate?: any;
        chequeDdNo: string;
        chequeDdDate?: string;
        receiptAmount: string;
        customerBalance?: string;
        chequeDdBank: string;
        transactionDate?: string;
        cardNo: string;
        cardName: string;
        firstName?: string;
        address1?: any;
        pinCode?: number;
        postOffice?: any;
        tehsil?: any;
        district?: any;
        subModel: string;
        city?: any;
        enquiryNumber: string;
        cardType: string;
        receiptMode: string;
        receiptType: string;
        receiptNumber: string;
        mobilNumber?: any;
        variant: string;
        id: number;
        state?: any;
    }
    export interface SearchFilterPaymentReceiptListDomain {
        page: string;
        size: number;
        receiptMode: string;
        customerName: string;
        customerMobileNo: string;
        fromDate: string;
        toDate: string;
        receiptNo: string;
        receiptType:any
    }

    export interface ViewPaymentReceiptDomain {
        id: number;
        receiptNo: string;
        receiptType: string;
        receiptDate?: any;
        receiptMode: string;
        chequeDdNo: string;
        chequeDdDate?: any;
        receiptAmount: number;
        customerBalance?: any;
        chequeDdBank: string;
        transactionNo: string;
        transactionDate?: any;
        cardNo: string;
        cardType: string;
        cardName: string;
        serviceProvides: string;
        remarks: string;
        status?: any;
        enquiry: EnquiryDomain;
    }


}