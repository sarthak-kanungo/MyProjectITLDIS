import { environment } from '../../../../../environments/environment';

export abstract class PartIssueUrl {
    private static readonly module = 'spares';
    private static readonly controller = 'partissue';
    private static readonly apiController = `${ environment.baseUrl }/${ environment.api }/${ PartIssueUrl.module }/${ PartIssueUrl.controller }`;

    static readonly getRequisitionAgainstIssue = `${PartIssueUrl.apiController}/getRequisitionAgainstIssue`;
    static readonly getIssueType = `${PartIssueUrl.apiController}/getIssueType`;
    static readonly searchByDocumentForPartIssue = `${PartIssueUrl.apiController}/searchByDocumentForPartIssue`;
    static readonly getPartRequisitionDetailsById = `${PartIssueUrl.apiController}/getPartRequisitionDetailsById`;
    static readonly getIssueTo = `${PartIssueUrl.apiController}/getIssueTo`;
    static readonly getAvailableStockForPartIssue = `${PartIssueUrl.apiController}/getAvailableStockForPartIssue`;
    static readonly searchPartRequisitionNo = `${PartIssueUrl.apiController}/searchPartRequisitionNo`;
    static readonly searchJobCardNo = `${PartIssueUrl.apiController}/searchJobCardNo`;
    static readonly searchPartIssue = `${PartIssueUrl.apiController}/searchPartIssue`;
    static readonly saveSparePartIssue = `${PartIssueUrl.apiController}/saveSparePartIssue`;
    static readonly searchApiNo = `${PartIssueUrl.apiController}/searchApiNo`;
    // static readonly getAvailableStockFromAdvancedPartIssue = `${PartIssueUrl.apiController}/getAvailableStockFromAdvancedPartIssue`;
    static readonly getPartIssueById = `${PartIssueUrl.apiController}/getPartIssueById`;
    static readonly getDropDownCategory = `${ environment.baseUrl }/${ environment.api }/${ PartIssueUrl.module }/${ PartIssueUrl.controller }/getDropDownCategory`
    static readonly getAvailableStockByItemNumber = `${environment.baseUrl}/${environment.api}/spares/salesOrder/getAvailableStockByItemNumber`
    static readonly checkLastIssueAgainstJobcard = `${PartIssueUrl.apiController}/checkLastIssueAgainstJobcard`;
}
