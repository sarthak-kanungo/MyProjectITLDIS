import { FormControl } from "@angular/forms";

declare module "ActivityClaimModule" {
    export interface ActivityClaimDomain {
        activityClaimNo: string,
        activityClaimDate: string,
        activityNo: string,
        activityCreationDate: string,
        activityType: string,
        activityClaimStatus: string,
        noOfDays: string,
        fromDate: string,
        toDate: string,
        actualFromDate: string,
        actualToDate: string,
        location: string,
        totalEnquiries: string,
        hotEnquiry: string,
        warmEnquiry: string,
        coldEnquiry: string,
        costPerEnquiry: string,
        costPerHotEnquiry: string,
        totalBookings: string,
        costPerBooking: string,
        totalRetails: string,
        costPerRetails: string,
        activityEffectiveness: string
    }
    export interface ActivityNumberDomain {
        id: number
        activityNumber: string
    }
    export interface ActivityDetailsDomain {
        fromDate: string;
        totalBooking: number;
        toDate: string;
        actualToDate: string;
        totalRetails: number;
        hotEnquiries: number;
        activityType: string;
        warmEnquiries: number;
        coldEnquiries: number;
        location: string;
        totalEnquiry: number;
        numberOfDays: number;
        createdDate: string;
        actualFromDate: string;
        // heads: Array<ActivityClaimHead>
        // reportImages: Array<MarketingActivityReportImageView>
        activityNumber?: string
    }

    export interface ActivityClaimHead {
        headName: string
        approvedAmount: number,
        image?: File | string
        actualClaimAmount?: number
        amount?: number
        id?: number;
        gstPercent: number
        gstAmount : number
        total? : number,
        vendorName?:string,
        billNo?:string,
        billDate:any
    }

    export interface DropDownGST {
        value : number
        id: number
    }

    export interface MarketingActivityReportImage {
        fileName: string
        id?: number;
    }

    export interface ActivityEffectivenessDomain {
        effectiveness: string;
        id: number;
    }

    export interface ActivitiyClaim {
        totalEnquiries: number;
        warmEnquiries: number;
        hotEnquiries: number;
        coldEnquiries: number;
        costPerEnquiry: number;
        costPerHotEnquiry: number;
        totalBookings: number;
        costPerBooking: number;
        totalRetails: number;
        costPerRetail: number;
        activityEffectiveness: string;
        gstInvoiceDocument: any;
        gstPercent: number;
        gstAmount: number;
        claimHeads: ActivityClaimHead[];
        totalApprovedAmount: number;
        totalActualClaimAmount: number;
        dealerEmployeeId: number;
        marketingActivityProposalId: number
    }

    export interface MarketingActivityProposalMaster {
        activityProposalId: number;
    }

    export interface DealerEmployeeMaster {
        id: number;
    }

    export interface ViewEditActivityClaimDomain {
        activityClaimHeaderData: ActivityClaimHeaderData;
        dealerInfo : DealerInfo
        activityClaimHeads: ActivityClaimHead[];
        reportImages: MarketingActivityReportImage[]
        approvalDetails : []
      }

      export interface DealerInfo {
        dealerCode: string
        dealerName: string
        state: string
      }
      
     export interface ActivityClaimHeaderData {
        totalEnquiries: number;
        costPerBooking: number;
        activityNumber: string
        claimNumber: string
        actualFromDate: string;
        actualToDate: string;
        coldEnquiries: number;
        costPerHotEnquiry: number;
        hotEnquiries: number;
        activityCreationDate: string
        warmEnquiries: number;
        numberOfDays: number;
        claimStatus: string
        fromDate: string;
        activityType: string;
        location: string;
        totalBookings: number;
        totalRetails: number;
        claimDate: string;
        toDate: string;
        effectiveness: string;
        costPerRetail: number;
        costPerEnquiry: number;
        approvedAmount :  number
        marketingActivityProposalId:any;
      }
}