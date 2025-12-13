import { environment } from "src/environments/environment";

export abstract class branchTransfer {
    private static readonly module = 'spares'
    private static readonly controller = 'branchTransfer';
    

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${branchTransfer .module }/${branchTransfer.controller}`
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`;
    static readonly autoGetRecieptNo = `${branchTransfer.apiController}/receipt/autoGetIssueNoInReceipt`;
    static readonly getIssueingBranchName = `${branchTransfer.apiController}/receipt/getIssueingBranchName`;

    static readonly getReceivingBranch=`${branchTransfer.apiController}/receipt/getReceivingBranch`;
    static readonly getReceiptItemDetails=`${branchTransfer.apiController}/receipt/getReceiptItemDetails`;
    static readonly saveBTReciept=`${branchTransfer.apiController}/receipt/saveBTReceipt`;
    static readonly  searchBTReceipt=`${branchTransfer.apiController}/receipt/searchBTReceipt `;
    static readonly viewBTReceipt=`${branchTransfer.apiController}/receipt/viewBTReceipt`
    static readonly printBTReceiptReport=`${branchTransfer.apiController}/receipt/printBTReceiptReport`;
    static readonly  autoPopulateReceiptNo=`${branchTransfer.apiController}/receipt/autoPopulateReceiptNo`;
    static readonly  autoPopulateIssueNo=`${branchTransfer.apiController}/receipt/autoPopulateIssueNo`
}