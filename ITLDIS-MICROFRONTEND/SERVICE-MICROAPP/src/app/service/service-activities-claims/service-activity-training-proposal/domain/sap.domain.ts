export interface ActivityType {
  activityType: string;
  id: number
}

export interface TargetedProduct {
  id: number;
  product: string;
}

export interface MaxAllowedBudget {
  activityTypeId: number;
  proposedBudget: number;
  targetedNumbers: number;
  totalSubActivity: number;
}

export interface Heads {
  headId: number
  head: string;
}

export interface SubActivity {
  activityId: number;
  subActivity: string;
  activityType: string;
  subActivityId: number;
  amount? : number
}

export interface ActivityNo {
  activityNumber: string;
  value: string
  id : number
}

export interface ActivityStatus {
  status: string;
  id : number
}
export interface SaveAndSubmitServiceActivityProposal {
  draftFlag: boolean;
  id? : number
  fromDate: string;
  location: string;
  remarks: string;
  maxAllowedBudget: number;
  noOfDays: number;
  proposedBudget: number;
  serviceMtActivityType: ServiceMtActivityType;
  ServiceActivityProposalSubActivity: ServiceActivityProposalSubActivity[];
  serviceActivityProposalHeads: ServiceActivityProposalHead[];
  targetedNumbers: number;
  targetedProducts: ServiceMtActivityType[];
  toDate: string;
}

export interface ApproveServiceActivityProposal {
  serviceActivityProposalId: number;
  approvedFlag: string
  approvedAmount: number;
  remark: string;
}

export interface ServiceActivityProposalHead {
  id? : number
  amount: number;
  head: string;
  headId?: number
}

export interface ServiceActivityProposalSubActivity {
  id?: number;
  subActivity: string;
  serviceMtSubActivityType?: any;
  amount: number;
}

export interface ServiceMtActivityType {
  id: number;
  activityType?: string;
}

export interface FilterSearchServiceActivityProposal {
  activityNumber?: string;
  activityProposalFromDate?: string;
  activityProposalToDate?: string;
  activityStatus?: string;
  activityType?: string;
  targetedproduct?: string
  page: number;
  size: number;
  orgHierId:number
}

export interface ViewServiceActivityProposal {
  location: string;
  id: number;
  serviceMtActivityType: ServiceMtActivityType;
  maxAllowedBudget: number;
  noOfDays: number;
  serviceActivityProposalHeads: ServiceActivityProposalHead[];
  serviceActivityProposalSubActivity: ServiceActivityProposalSubActivity[];
  serviceActivityProposalApproval?: any;
  targetedProducts: TargetedProduct[];
  dealerEmployeeMaster: DealerEmployeeMaster;
  dealerMaster: DealerEmployeeMaster;
  fromDate: string;
  toDate: string;
  approvedAmount?: any;
  activityNumber: string;
  remarks: string;
  createdDate: string;
  draftFlag: boolean;
  proposedBudget: number;
  targetedNumbers: number;
  status: string;
  kaiRemark?:string
}

export interface DealerEmployeeMaster {
  id: number;
  dealerName:string
}
