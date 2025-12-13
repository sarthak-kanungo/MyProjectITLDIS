declare module "IncentiveSchemeClaimModule" {
    export interface WholesaleIncentiveSchemeDetails{
        dealerId: number;
        dealerCode: string;
        dealerName: string;
        wholesaleQty: number;	
        incentiveValue:number;
        retails:number;
        claimAmount:number;
        totalAmount:number;
        invoiceUploadedBy?:number;
        status?:string
    }
    export interface RetailIncentiveSchemeDetails{
        schemeId?: number;
        customerName: string;
        mobileNo: string;
        address1: string;
        pincode: string;
        postOffice: string;
        tehsil: string;
        district: string;
        state: string;
        city: string;
        village: string;
        dcNo: string;
        dcDate: string;
        modelVariant: string;
        scheme: string;
        dse: string;
        tm: string;
        gm: string;
        amount: number;
    }
}