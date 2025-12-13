export interface searchIssueNo {
    id: any
    issueNo: number
}
export interface PartReciept {

}
export interface partRecieptForm {
    formReciept: PartIssueSubForm;
    draftFlag: string;
    itemDetails: PartIssueItemSubForm[];
}
export interface PartIssueSubForm {
    transferIssue: any
    issuingBranchId: any
}
export interface PartIssueItemSubForm {

}
export interface PartIssueItem {
    binLocationMaster: Id;
    issuedQty: number,
    mrp: number,
    remark: string,
    sparePartMaster: SparePartMaster,
    storeMaster: Id;
}
export interface SparePartMaster {
    id: number,
    itemNo: string
}
export interface Id {
    id: any
    binLocation: any
}