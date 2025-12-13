declare module "purchase-order-create"

export interface SavePurchaseOrder {
    poType: string;
    depot: string;
    chequeNumber: string; //added by mahesh.kumar
    chequeDate: Date; //added by mahesh.kumar
    chequeAmount: number; //added by mahesh.kumar
    branchCode: string;
    poDate: string;
    poNumber: string;
    totalQuantity: number;
    poStatus: string;
    totalAmount: number;
    totalCreditLimit: number;
    availableLimit: number;
    salesAdmin?:boolean;
    showAllButton?:boolean;
    totalOs: number;
    currentOs: number;
    os0To30Days: number;
    os31To60Days: number;
    os61To90Days: number,
    os90Days: number;
    paymentPending: number;
    netOs: number;
    pendingOrder: number;
    orderUnderProcess: number;
    channelFinanceAvailable: number;
    exposureAmount:number;
    approverRemark?:string;
    machineDetails: MachineDetails[];
    deletedParts?: MachineDetails[];
    draftMode: boolean;
    totalGstAmount: number;
    basicAmount: number;
    id: any;
    dealerMaster?:DealerMaster;
    dealerEmployeeMaster: DealerEmployeeMaster;
    isPurchaseFormValid: boolean;
    isMachineDetailsFormValid: boolean;
    isView: boolean;
    isEdit: boolean;
    isApproveOrReject: boolean;
    chequeLeafImage: File;
    chequeLeaf:string;
    coveringLetterImage: File;
    coveringLetter :string;
    creditApplicationImage: File;
    creditApplication :string;
    chequeCopyImage :File; //added by mahesh.kumar
    chequeCopy :string; //added by mahesh.kumar
    tcsPercent:number;
    tcsValue:number;
    isApprovalRequired?:boolean
}
export interface DealerMaster {
    dealerCode: string,
    dealerName: string,
    billingState:string
}
export interface DealerEmployeeMaster {
    id: number
}
export interface MachineDetails {
    accpacLocationCode: string;
    accpacStockQuantity: number;
    amount: number;
    colour: string;
    id: number;
    itemDescription: string;
    itemNo: string;
    quantity: number;
    unitPrice: number;
    variant: string;

    discountAmount: number,
    discountPercentage: number,
    discountType: string,
    isDelete?: boolean
}

export interface CancelPo {
    approvalFlag: string,
    machineDetails: MachineDetails[],
    purchaseOrderId: number,
    userId: number,
    totalOs: number;
    currentOs: number;
    os0To30Days: number;
    os31To60Days: number;
    os61To90Days: number,
    os90Days: number;
    paymentPending: number;
    netOs: number;
    pendingOrder: number;
    orderUnderProcess: number;
    channelFinanceAvailable: number;
    exposureAmount:number;
    totalGstAmount: number;
    basicAmount: number;
    totalAmount: number;
    totalCreditLimit: number;
    availableLimit: number;
    remark:string;
    tcsPercent:number;
    tcsValue:number;
    isApprovalRequired:boolean;
}
export interface Depo{
    branchName?: string;
}