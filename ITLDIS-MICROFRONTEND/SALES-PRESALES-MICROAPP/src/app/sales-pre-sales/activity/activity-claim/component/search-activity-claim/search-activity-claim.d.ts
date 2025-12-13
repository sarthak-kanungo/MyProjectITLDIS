
declare module "ActivityClaimModule" {
    export interface ActivityClaimNumberDomain {
        claimId: number
        claimNumber: string
        displayString: string
    }
    export interface ActivityTypeOfClaimDropdownDomain {
        sourceName: string
    }
    export interface ActivityNumberDomain {
        activityProposalId: number
        activityNumber: string
        displayString : string
    }
    export interface ClaimStatusDomain {
        id: number
        claimStatus: string
    }

    export interface ViewActivityClaimDomain {

    }
    export interface ActivityClaimListSearchDomain {

        location: string;
        claimDate: string;
        hotEnquiries: number;
        totalBookings: number;
        fromDate: string;
        coldEnquiries: number;
        costPerHotEnquiry: number;
        actualClaimAmount: number;
        activityCreationDate: string;
        actualFromDate: string;
        totalEnquiries: number;
        activityNumber: string;
        numberOfDays: number;
        costPerRetail: number;
        approvedAmount: number;
        toDate: string;
        activityEffectiveness: string;
        claimNumber?: any;
        costPerBooking: number;
        activityType: string;
        claiimStatus: string;
        totalRetails: number;
        costPerEnquiry: number;
        claimId: number;
        actualToDate: string;
        warmEnquries: number;
    }
    export interface SearchActivityClaimListDomain {
        activityNumber?: string;
        activityType?: string;
        claimNumber?: string;
        claimStatus?: string;
        fromDate?: string;
        page: number;
        size: number;
        toDate?: string;
        dealerId?:number;
        orgHierId?:number;
        dealerCode?:number;
    }
}