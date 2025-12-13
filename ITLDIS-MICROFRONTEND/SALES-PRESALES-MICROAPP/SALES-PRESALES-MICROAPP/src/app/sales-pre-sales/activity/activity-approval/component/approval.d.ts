declare module "approval" {

  interface Approval {
    activityProposalHeaderData: ActivityHeaderData
    dealerInfo: DealerMaster
    activityProposalHeads: ActivityHead[];
    activityProposalEnquiries: Enquiry[]
    approvalHierDetails : any
  }

  export interface ActivityHeaderData {
    activityProposalId: number;
    activityNumber: string;
    createdDate: string;
    activityPurpose: string;
    activityType: string;
    location: string;
    activityStatus: string;
    fromDate: string;
    toDate: string;
    numberOfDays: number;
    proposedBudget: number;
    maxAllowedBudget: number;
    sourcePurposeId: number;
    activityTypeId: number;
    approvedAmount: number;
    claimableAmount:number;
    activityCategoryId:number;
    activityCategory:string;
  }
  export interface ActivityHead {
    headName?: string
    approvedAmount?: number,
    image?: File | string
    actualClaimAmount?: number
    amount?: number
    id?: number;
    gstPercent?: number
    gstAmount? : number
    total? : number
  }

  interface DealerMaster {
    dealerCode: string
    dealerName: string
    state: string
  }

  export interface Enquiry {
    enquiryNumber: string,
    firstName: string,
    mobileNumber: string,
    city: string,
    tehsil: string,
    model: string,
    enquiryType: string
    id?: number
  }

  interface ApproveProposalDomain {
    activityClaimId?: number
    approvedAmount?: number
    activityProposalId?: number;
    remark?: string,
    approvalStatus?: string        
  }
  
}