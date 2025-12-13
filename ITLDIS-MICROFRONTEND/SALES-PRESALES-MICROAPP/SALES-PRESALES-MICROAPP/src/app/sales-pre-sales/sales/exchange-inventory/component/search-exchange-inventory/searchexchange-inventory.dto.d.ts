declare module "search-exchange-inventory-dto" {

    export interface EnquiryNumberDomain {
        enquiryNumber: string;
        value: string;
      }
    

    // export interface PaymentReceiptListSearchDomain {
    //     receiptMode:string;
    //     product:string;
    //     series:string;
    //     model:string;
    //     subModel:string;
    //     variant:string;


    // }
    export interface ExchangeInventoryListSearchDomain {
        action:any
        enquiryNumber:any
        soleStatus:any
        brandName:any
        modelName:any
        modelyear:any
        invInDate:any
        estimatedExchangePrice:any
        buyerName:any
        buyerContactNo:any
        saleDate:any
        sellingPrice:any
        saleRemarks:any;
    }
    export interface SearchFilterExchangeInventoryListDomain {
        page: number
        size: number
        userId: number
        enquiryNumber: string
        fromDate: string
        toDate: string,
        dealerCode?: any,
        dealerId?:number,        
        hierId?:number,
        pageIndexValue?:any,
        status?:any;
    }

    export interface ViewExchangeInventoryDomain {
        id: number;
        enquiryNumber: string;
        fromDate?: any;
        toDate?: any;
        status?: any;
    }


}