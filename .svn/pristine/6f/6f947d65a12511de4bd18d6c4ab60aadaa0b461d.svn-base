import { environment } from "src/environments/environment";

export abstract class BOCancellation {
    private static readonly module = 'spares'
    private static readonly controller = 'purchase';
    

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${BOCancellation .module }/${BOCancellation.controller}`
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`;

    static readonly autoGetBOCNo = `${BOCancellation.apiController}/BOCancellation/autoGetBOCNo`;
    static readonly getDealerCode = `${BOCancellation.apiController}/BOCancellation/autoGetDealerCode`;
    static readonly printPDf = `${BOCancellation.apiController}/BOCancellation/printBOCReport`;
    static readonly saveBOC = `${BOCancellation.apiController}/BOCancellation/saveBOCancellation`;
    static readonly viewBackOrderCancellation=`${BOCancellation.apiController}/BOCancellation/viewBOCancellation`;
    static readonly searchBackOrderCancellation=`${BOCancellation.apiController}/BOCancellation/searchBOCancellation`;
    static readonly getBOCItemDetails=`${BOCancellation.apiController}/BOCancellation/getBOCItemDetails`

    static readonly autoCompleteDealerCode=`${BOCancellation.apiController}/BOCancellation/autoCompleteDealerCode`;
    static readonly boCancellationApproval=`${BOCancellation.apiController}/BOCancellation/boCancellationApproval` ;
    static readonly searchBOCApproval=`${BOCancellation.apiController}/BOCancellation/searchBOCApproval`
}