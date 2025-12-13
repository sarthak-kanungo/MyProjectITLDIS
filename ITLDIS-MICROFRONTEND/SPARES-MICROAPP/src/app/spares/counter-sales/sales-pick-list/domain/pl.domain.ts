export interface AutoCustomerOrder {
    saleOrderNumber?: string,
    picklistNumber?: string,
    id: number
}
export interface SavePickList {
    picklistHdrId?: number;
    spareSalesOrderId: number;
    picklistNumber?: string;
    itemDetails?: SavePickItemDetails[];
}
export interface SavePickItemDetails {
    picklistDtlId?:number,
    spareSalesOrderPartId: number,
    pickedItemNo:string,
    issueQty: number,
    binLocationId : number,
    storeId : number,
    unitPrice : number,
    finalIssueQty : number,
    totalReturnQty : number,
    returnQty : number,
    spegst : number,
    spmgst : number,
    spmrp : number
}
export interface HeaderResponse {
    id?: number;
    saleOrderNumber: string;
    customerName: string;
    contactNumber: string;
    city: string;
    picklistNumber: string;
    picklistDate: string;
    state: string;

}
export interface ItemNumberAuto {
    itemNo: string
    id: number,
    value: string
}
export interface ItemDetail {
    id?:number;
    itemNumber: string;
    itemDescription?: string;
    orderQuantity: number;
    balanceQuantity: number;
    finalIssueQuantity : number;
    returnedQuantity : number;
    returnQuantity : number;
    currentStock: number;
    issueQuantity?: number;
    store: string;
    binLocation: string;
    mrp: number;
    sparePartDtlId : number;
    storeId:number;
    binId:number;
    spegst:number;
    spmgst:number;
    unitPrice:number;
}
export interface PickListPatchJson {
    headerResponse: HeaderResponse;
    partDetails: ItemDetail[];
}
export interface ItemNumberAuto {
    itemNo: string
    id: number,
    value: string
}

export interface PlSearch {

    customerName: string;
    orderFromDate: string;
    orderStatus: string;
    orderToDate: string;
    picklistNumber?: number;
    page: number;
    size: number;
}
