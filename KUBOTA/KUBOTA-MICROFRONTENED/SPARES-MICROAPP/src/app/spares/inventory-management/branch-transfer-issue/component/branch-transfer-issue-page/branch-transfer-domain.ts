import { SelectList } from "src/app/core/model/select-list.model";

export interface PartIssueForm{
formIssue: PartIssueSubForm;
draftFlag:string;
    branchIssueItem: PartIssueItemSubForm[];
}
export interface PartIssueSubForm{
    draftFlag:string
    employeeId:any
}
export interface PartIssueItem {
    binLocationMaster: Id;
    issuedQty: number,
    mrp: number,
    remark: string,
    sparePartMaster: SparePartMaster,
    storeMaster: Id;
}
export interface PartIssueItemSubForm {
    itemNo: SelectList | string;
    itemDescription: string;
    uom?: string;
    reqQuantity: number;
    issuedQty:number;
    sparePartMaster?: SparePartMaster;
    transferIndent?:TransferIndent;
    mrp: number;
    remark?: string;
    binLocationMaster?: BinLocationMaster;
     storeMaster?: StoreMatser;
    
    store: string,
    binLocation:string,
          
}
export interface TransferIndent{
 id:number
}
export interface StoreMatser{
    id:number
    name:any
   }
export interface BinLocationMaster{
    id:number,
    binLocation:any
}
export interface Id {
    id: number;
}
export interface SparePartMaster {
    id: number;
    itemNo: string;
}
export interface searchBranchTransferIssue{
    fromDate:any
    toDate:any
    page:any
    size:any
}