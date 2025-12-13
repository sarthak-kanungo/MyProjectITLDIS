export interface SaveSparesPurchaseOrder {
    availableLimit?: number,
    creditLimit?: number,
    currentOutStanding?: number,
    freightBorneBy: string,
    netAmountPayable?: number,
    orderType: IdMaster,
    ordersUnderProcess?: number,
    overduesOutStanding?: number,
    paymentUnderProcess?: number,
    purchaseOrderDate?: string,
    remarks: string,
    supplierType: string,
    priceType: string,
    opsId:number,
    id?: number,
    coDealerMaster?: IdMaster;
    sparesMtSupplier: IdMaster,
    serviceJobCard?: IdMaster,
    transportMode: string,
    transporter: string,
    draftFlag: boolean,
    sparePurchaseOrderPartDetail?: SparesPOPartDetails[];
}

export interface SparesPOPartDetails {
    currentStock: string,
    gstPercent: string,
    sparePartMaster: SparePartMaster,
    //spareLocalPartMaster?: SpareLocalPartMaster;
    quantity: number,
    unitPrice: string,
    itemNo?: string,
    itemId?:number,        
    id?: number,
    itemDescription?: string,
    backOrderAtKai?: string,
    transitFromKai?: string,
    baseAmount?: any,
    gstAmount?: any,
    totalAmount?: any,
    accpacOrderNumber?: string,
    isJobCardItem: boolean,
    serviceCategory?:string,
    isValidItem?:string,     
    msg?:string,        
    tcsPercent?:number,
    tcsValue?:number,
    moq?:number        
}
export interface SpareLocalPartMaster {
    id: number,
    dmsItemNumber: string
}
export interface IdMaster {
    id: number,
    orderType?:string
     code?:string
}
export interface SparePartMaster {
    id: number,
    itemNo: string;
}

