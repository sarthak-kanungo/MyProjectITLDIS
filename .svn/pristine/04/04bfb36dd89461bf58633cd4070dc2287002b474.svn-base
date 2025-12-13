import { environment } from "src/environments/environment";


export abstract class branchTransfer {
    private static readonly module = 'spares'
    private static readonly controller = 'branchTransfer';
    

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${branchTransfer .module }/${branchTransfer.controller}`
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`;
    static readonly getissueToBranchDeatils = `${branchTransfer.apiController}/issue/getIssueToBranchDetails`;
    static readonly getIssueingBranch = `${branchTransfer.apiController}/issue/getIssueingBranch`;
    static readonly getIndentNos=`${branchTransfer.apiController}/issue/getIndentNos`;
    static readonly getIndentItemsDetailsByIds=`${branchTransfer.apiController}/issue/getIndentItemsDetailsByIds`
    static readonly saveBTIssue=`${branchTransfer.apiController}/issue/saveBTIssue`
    static readonly viewBTIssue=`${branchTransfer.apiController}/issue/viewBTIssue`
    static readonly searchBTIssue=`${branchTransfer.apiController}/issue/searchBTIssue`
    static readonly autoGetIssueNo=`${branchTransfer.apiController}/issue/autoGetIssueNo`
    static readonly printBTIssueReport=`${branchTransfer.apiController}/issue/printBTIssueReport`
}