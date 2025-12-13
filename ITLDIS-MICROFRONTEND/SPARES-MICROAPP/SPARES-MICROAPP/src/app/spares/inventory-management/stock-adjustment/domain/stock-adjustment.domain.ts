export interface SaveStockAdjustment {
    draftFlag: boolean,
    stockAdjustmentDtls:SubmitItemDetail[]
}

export interface SearchStockAdjustment {
    page:number,
    size:number,
    stockAdjustmentNo:string,
    adjustmentFromDate:string,
    adjustmentToDate:string

}
export interface ItemNoAuto {
    itemNo: string
    id: number,
    value?: string
}
export interface SubmitItemDetail{
    rowId?:string
    id?:number
    srNo:number;
    adjustmentNumber?:string;
    adjustmentDate?:string;
    adjustmentType:string;
    adjustmentStatus?:string;
    partNo:string;
    description?:string
    storeId:number;
    store:string;
    stockBinId:number;
    binLocation:string; 
    mrp:number;
    qtyAdjusted:number;
    increasedAmount?:number;
    decreasedAmount?:number;
    
}
export interface ItemDetail{
    currentStock: number,
    binLocation: IdMaster,
    store: IdMaster,
    id?: number,
    isSelected: boolean,
    itemNo: ItemNoAuto,
    itemDescription: string,
    adjustmentType: string,
    rowId?: number,
    transferQty: number
}
export interface IdMaster{
    id: number,
    name : string
}
export interface ItemErrorDetail {
    itemNo: string,
    itemDescription: string,
    msg:string
}