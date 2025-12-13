declare module "ActivityProposalModule" {

    export interface MarketingActivityCreateDomain {
        activityHeads: MarketingActivityAddproductDomain[];
        //conversionTypeActivityProposals: ConversionTypeDomain[];
        activityNumber: string;
        activityCreationDate: string;
        activityPurpose: string;
        activityType: string;
        activityCategory:number;
        location: string;
        activityStatus: string;
        fromDate: string;
        toDate: string;
        numberOfDays: number;
        proposedBudget: number;
        maxAllowedBudget: number;
        claimableAmount: number;
        createdDate: string;
        lastModifiedDate: string;
        dealerEmployeeMaster: object
    }

    interface SubmitActivityProposal {
        fromDate: string;
        location: string;
        maxAllowedBudget: number;
        proposedBudget: number;
        claimableAmount: number;
        activityPurpose: string;
        toDate: string;
        numberOfDays : number;
        activityType: string;
        activityCategory:number;
        enquiries: Enquiries[]
        activityHeads: MarketingActivityAddproductDomain[];
    }

    export interface Enquiries {
        id: number
    }

    export interface PurposeActivityDomain {
        id: number
        purpose: string
    }
    export interface ActivityTypeDomain {
        activityType?: string
        id: number
    }
    export interface ActivityCategoryDomain {
        id: number
        category:string
    }
    export interface AutoActivityNo {
        activityNumber: string
    }
    export interface MaxAllowedBudget {
        maxAllowedBudget: number
        maximumLimit: number
        claimableAmount: number
    }
    export interface ActivityType {
        activityPurpose: string
        activityType: string
    }

    interface ViewEditActivityProposalDomain {
        activityProposalHeaderData : ActivityHeaderData
        // dealerMaster: DealerMaster
        activityProposalHeads: MarketingActivityAddproductDomain[];
        activityProposalEnquiries: EnquiryDetailsForConversion[]
        
        approvalHierDetails : ApprovalHierDetails[];
    }
    export interface ActivityHeaderData {
        activityProposalId: number;
        activityNumber: string;
        createdDate: string;
        activityPurpose: string;
        activityType: string;
        activityCategory: string,
        location: string;
        activityStatus: string;
        fromDate: string;
        toDate: string;
        numberOfDays: number;
        proposedBudget: number;
        maxAllowedBudget: number;
        claimableAmount: number;
        sourcePurposeId: number
        activityTypeId: number;
        activityCategoryId: number
        approvedAmount?: number
    }

    export interface DealerMaster {
        state: string
        dealerCode: string
        dealerName: string
    }
    export interface Head {
        id?: number
        headName: string
        remark?: string
    }

    
    interface ApprovalHierDetails {
        approvalStatus:string,
        isFinalApprover:string,
        approvedBy:string,
        approvalDate:string,
        approverRemarks:string,
        approvedAmt:number
    }
}