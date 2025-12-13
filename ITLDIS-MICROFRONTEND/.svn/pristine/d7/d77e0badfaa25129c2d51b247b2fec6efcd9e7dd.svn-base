export interface AutoCompActivityNo {
  activityNumber: string;
  value: string
  id: number
}

export interface ActivityClaimNo {
  claimNumber: string;
  value: string
  id: number
}

export interface DropdownActivityType {
  activityType: string;
  id: number
}

export interface ActivityEffectivenessDropDown {
  id: number;
  effectiveness: string;
}

export interface ActivityDetailsByActivityNo {
  createdDate: string
  activityType: string
  fromDate: string
  actualFromDate: string
  toDate: string
  actualToDate: string
  noOfDays: number
  location: string
  activityEffectiveness: string
  approvedAmount: number
}

export interface HeadsDetailsByActivityNumber {
  head: string
  amount: number
  actualClaimAmount: number
  headImage: File
  id: number
  imageType?:string
  claimApprovedAmount?:number,
  vendorName:any,
  billNo:any,
  billDate:any
}

export interface SubActivityDetailsByActivityNumber {
  subActivity: string
  amount: number
  actualClaimAmount: number
  image: File
  id: number
  claimApprovedAmount?:number,
  vendorName:any,
  billNo:any,
  billDate:any
}
export interface FilterSearchServiceActivityClaim {
  activityClaimNumber?: string;
  activityClaimStatus?: string;
  activityNumber?: string;
  fromDate?: string;
  toDate?: string;
  activityType?: string;
  activityEffectiveness?: string;
  page: number;
  size: number;
  dealerId?:number;
  orgHierId?:number;
}

export interface SubmitServiceActivityClaim {
  approvedAmount: number
  totalClaimAmount: number
  serviceActivityProposalId: number
  claimHeads: HeadsDetailsByActivityNumber[]
  subActivities : SubActivityDetailsByActivityNumber[]
}

export interface ServiceActivityProposal {
  id: number
}

export interface ApproveServiceActivityClaim {
  serviceActivityClaimId: number
  approvedAmount: number
  approvalType:string
  remark:string;
  headDetails?:any;
  subActivityDetails?:any;
}

export interface ViewActivityClaim {
  activityClaimHeaderData: ActivityClaimHeaderData;
  activityClaimHeads: HeadsDetailsByActivityNumber[];
  subActivities : SubActivityDetailsByActivityNumber[]
  reportPhotos : ReportPhotos[]
  
  approvalDetails?:[];
}

export interface ActivityClaimHeaderData {
  actualFromDate: string;
  toDate: string;
  actualToDate: string;
  createdDate: string;
  claimDate: string;
  activityEffectiveness: string;
  fromDate: string;
  location: string;
  claimStatus: string;
  noOfDays: number;
  activityNumber: string;
  activityType: string;
  claimNumber?: any;
  activityNumberId: number
  totalClaimAmount: number
  id: number
  approvedAmount?: number
  kaiRemark?:string,
  activityTypeId?:number
  proposalId:any,
}

export interface ReportPhotos {
  fileName: string;
}