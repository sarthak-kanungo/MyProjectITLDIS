import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from '../../../../../environments/environment'

export abstract class branchTransfer {
    private static readonly module = 'spares'
    private static readonly controller = 'branchTransfer';
    

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${branchTransfer .module }/${branchTransfer.controller}`
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`;
    // static readonly getReqToBranchDeatilsById = `${environment.baseUrl}/${environment.api}/getReqToBranchDeatilsById`;
    static readonly getReqToBranchDeatilsById = `${branchTransfer.apiController}/indent/getReqToBranchDeatilsById`;
    static readonly getAllStatus = `${branchTransfer.apiController}/indent/getAllStatus`;
    static readonly autoGetIndentNo=`${branchTransfer.apiController}/indent/autoGetIndentNo`;
    static readonly  getSubBranch=`${branchTransfer.apiController}/indent/getSubBranch`;
    static readonly getSpareItemDetails=`${branchTransfer.apiController}/indent/getSpareItemDetails`;
    static readonly excelUpload=`${branchTransfer.apiController}/indent/excelUpload`;
    static readonly saveBTIndent=`${branchTransfer.apiController}/indent/saveBTIndent`;
    static readonly autoCompleteItemNumber = `${branchTransfer.apiController}/indent/autoCompleteItemNumber`;
     static readonly searchBTIndent = `${branchTransfer.apiController}/indent/searchBTIndent`;
     static readonly viewBTIndentByReqNo = `${branchTransfer.apiController}/indent/viewBTIndentByReqNo`;
     static readonly printBTIndentReport=`${branchTransfer.apiController}/indent/printBTIndentReport`;
}