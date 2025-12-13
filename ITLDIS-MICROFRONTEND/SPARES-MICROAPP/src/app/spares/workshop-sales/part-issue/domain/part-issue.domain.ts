import { SelectList } from '../../../../core/model/select-list.model';

export interface PartIssue {
    issueType: string,
    issueAgainst: string,
    issueTo: Id;
    partIssueDate: string,
    partIssueStatus: string,
    sparePartIssueItems: PartIssueItem[],
    sparePartRequisition: Id;
}
export interface Id {
    id: number;
}
export interface SparePartMaster {
    id: number;
    itemNo: string;
}
export interface SparePartIssueSearchFilter {
    partIssueNo: string,
    requisitionNo: string,
    jobCardNo: string,
    requisitionPurpose: string,
    requisitionFromDate: string,
    requisitionToDate: string,
    issueFromDate: string,
    issueToDate: string,
    page: number;
    size: number;
}

export interface PartIssueForm {
    partIssue: PartIssueSubForm;
    partIssueItem: PartIssueItemSubForm[];
}
export interface PartIssueSubForm {
    issueType: string | SelectList;
    issueAgainst: string;
    issueTo: number;
    partIssueDate: string;
    partIssueStatus?: string;
    sparePartRequisition: string;
}
export interface PartIssueItem {
    binLocationMaster: Id;
    issuedQuantity: number,
    mrp: number,
    remark: string,
    sparePartMaster: SparePartMaster,
    storeMaster: Id;
    advancedSparePartIssue?:Id
}
export interface PartIssueItemSubForm {
    itemNo: SelectList | string;
    itemDescription: string;
    uom?: string;
    reqQuantity: number;
    issuedQuantity:number;
    sparePartMaster?: SparePartMaster;
    mrp: number;
    remark?: string;
    binLocationMaster?: Id;
    storeMaster?: Id;
    balancedQuantity: number,
    availableStock:number,
    store: string,
    binLocation:string,
    spmgst?:number;
    spegst?:number;
    spmrp?:number;
    unitPrice?:number;
    category?:DropDownCategory;
    category_id?:number;
    sparePartId?:number;
    binId?:number;
    storeId?:number;        
}
export interface DropDownCategory {
    category: string
    id: number
}
/* ================================================ */
export interface SparePartIssueSearchResult {
    id: number;
    requisitionNo: string;
    requisitionPurpose: string;
    requestedBy: string;
    requisitionDate: string;
    jobCardNo?: string;
    jobCardDate?: string;
}
export interface PartIssueById {
    sparePartIssue: SparePartIssueById;
    sparePartIssueItem: SparePartIssueItemById[];
}
export class SparePartIssueById {
    requisitionPurpose: string;
    requestedBy: String;
    jobCardNo: String;
    jobCardId: String;
    id: number;
    jobCardDate: string;
    requisitionNo: string;
}
export class SparePartIssueItemById {
    itemNo: string | SelectList;
    uom: string;
    requisitionQty: number;
    itemDescription: string;
    sparePartId: number;
    id: number;
}