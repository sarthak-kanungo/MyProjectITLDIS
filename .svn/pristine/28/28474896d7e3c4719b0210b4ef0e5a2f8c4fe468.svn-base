import { SelectList } from '../../../../core/model/select-list.model';

export interface Id {
    id: number;
}
export interface SparePartMaster {
    id: number;
    itemNo: string;
}

export interface SparePartReturnSearchFilter {
    returnNo: string;
    requisitionNo: string;
    jobCardNo: string;
    requisitionPurpose: string;
    reasonForReturn: string;
    requisitionFromDate: string;
    requisitionToDate: string;
    jobCardFromDate: string;
    jobCardToDate: string;
    page: number;
    size: number;
}
export interface PartReturn {
    reasonForReturn: string;
    sparePartRequisition: Id;
    serviceJobCard: Id;
    sparePartReturnItems: PartReturnItem[];
}
export interface PartReturnItem {
    remark: string;
    returnQuantity: number;
    sparePartMaster: SparePartMaster;
    binLocationMaster: Id;
    storeMaster: Id;
    sparePartIssue: Id;
}

export interface PartReturnForm {
    partReturn: PartReturnSubForm;
    partReturnItem: PartReturnItemSubForm[];
}
export interface PartReturnSubForm {
    issueType: string | SelectList;
    issueAgainst: string;
    issueTo: number;
    partReturnDate: string;
    partReturnStatus?: string;
    sparePartRequisition: SelectList;
    id;
    returnNo;
    returnDate: string;
    reasonForReturn:string;
    serviceJobCard;
    requisitionDate;
    jobCardDate;
    requisitionPurpose:string;
    requisitionType:string;
    returnRequestBy:string;
    partsReceivedBy:string;
    partReceivedById?:number;
    dealerEmployeeMaster?:Id;
}

export interface PartReturnItemSubForm {
    itemNo: string;
    itemDescription: string;
    uom: string;
    reqQuantity: number;
    issuedQuantity: number;
    mrp: number;
    remark: string;
    returnQuantity: number;
    id: number;
    pendingQuantity: number;
    store: string;
    binLocation: string;
    sparePartMaster: SparePartMaster;
    binLocationMaster: Id;
    storeMaster: Id;
    sparePartIssue: Id;
    tableRowId?: string;
    partsReceivedBy?:string
}
export interface PartIssueDetailsForPartReturnByRequisitionId {
    headerData: HeaderData;
    lineItems: LineItem[]
}
export interface HeaderData {
    jobCardNo: string;
    jobCardDate: string;
    requisitionDate: string;
    requisitionPurpose: string;
}
export interface LineItem {
    partIssueDate: string;
    issuedQuantity: number;
    returnQuantity?: number;
    pendingQuantity?: number;
    reqQuantity: number;
    storeId: number;
    uom: string;
    binId: number;
    store: string;
    itemDescription: string;
    partsReceivedBy: string;
    partIssueItemId: number;
    binLocation: string;
    partIssueNo: string;
    mrp: number;
    itemNo: string;
    partIssueId: number;
    sparePartId:number;
}
export interface SparePartReturnSearchResult {
    id: number;
    partsReceivedBy: string;
    requisitionDate: string;
    jobCardNo: string;
    returnDate: string;
    requisitionNo: string;
    returnNo: string;
    reasonForReturn: string;
    jobCardDate: string;
    requisitionPurpose: string;
}
/* ================================================ */
export interface PartReturnById {
    sparePartReturn: SparePartReturnById;
    sparePartReturnItem: SparePartReturnItemById[];
}
export class SparePartReturnById {
    requisitionPurpose: string;
    requestedBy: String;
    jobCardNo: String;
    jobCardId: String;
    id: number;
    jobCardDate: string;
    requisitionNo: string;
}
export class SparePartReturnItemById {
    itemNo: string | SelectList;
    uom: string;
    requisitionQty: number;
    itemDescription: string;
    sparePartId: number;
    id: number;
}