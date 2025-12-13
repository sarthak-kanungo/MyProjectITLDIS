import { environment } from '../../../../../environments/environment';

export abstract class PartReturnUrl {
    private static readonly module = 'spares';
    private static readonly controller = 'partreturn';
    private static readonly partissue = 'partissue';
    private static readonly apiController = `${ environment.baseUrl }/${ environment.api }/${ PartReturnUrl.module }/${ PartReturnUrl.controller }`;

    static readonly getRequisitionAgainstIssue = `${ environment.baseUrl }/${ environment.api }/${ PartReturnUrl.module }/${PartReturnUrl.partissue}/getRequisitionAgainstIssue`;
    static readonly getReasonsForReturn = `${PartReturnUrl.apiController}/getReasonsForReturn`;
    static readonly searchByRequisitionNoMobileNoEmpName = `${PartReturnUrl.apiController}/searchByRequisitionNoMobileNoEmpName`;
    static readonly searchByJobCardNoForPartReturn = `${PartReturnUrl.apiController}/searchByJobCardNoForPartReturn`;
    static readonly getPartIssueDetailsForPartReturnByRequisitionId = `${PartReturnUrl.apiController}/getPartIssueDetailsForPartReturnByRequisitionId`;
    static readonly saveSparePartsReturn = `${PartReturnUrl.apiController}/saveSparePartsReturn`;
    static readonly searchByReturnNo = `${PartReturnUrl.apiController}/searchByReturnNo`;
    static readonly searchByRequisitionNo = `${PartReturnUrl.apiController}/searchByRequisitionNo`;
    static readonly searchByJobCardNo = `${PartReturnUrl.apiController}/searchByJobCardNo`;
    static readonly searchPartReturn = `${PartReturnUrl.apiController}/searchPartReturn`;
    static readonly getPartReturnById= `${PartReturnUrl.apiController}/getPartReturnById`;
    
}
