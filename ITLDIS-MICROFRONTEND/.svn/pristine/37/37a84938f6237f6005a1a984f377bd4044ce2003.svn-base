import { environment } from '../../../../../environments/environment';

export abstract class PartRequisitionUrl {
    private static readonly module = 'spares';
    private static readonly controller = 'partrequisition';
    private static readonly apiController = `${ environment.baseUrl }/${ environment.api }/${ PartRequisitionUrl.module }/${ PartRequisitionUrl.controller }`;

    static readonly getPartRequisitionById = `${PartRequisitionUrl.apiController}/getPartRequisitionById`;
    static readonly getRequisitionPurpose = `${PartRequisitionUrl.apiController}/getRequisitionPurpose`;
    static readonly getSparesPartItemNoDetails = `${PartRequisitionUrl.apiController}/getSparesPartItemNoDetails`;
    static readonly savePartRequisition = `${PartRequisitionUrl.apiController}/savePartRequisition`;
    static readonly searchJobCardNo = `${PartRequisitionUrl.apiController}/searchJobCardNo`;
    static readonly searchPartRequisition = `${PartRequisitionUrl.apiController}/searchPartRequisition`;
    static readonly searchPartRequisitionNo = `${PartRequisitionUrl.apiController}/searchPartRequisitionNo`;
    static readonly searchSparesPartItemNo = `${PartRequisitionUrl.apiController}/searchSparesPartItemNo`;
    static readonly searchApiNo = `${PartRequisitionUrl.apiController}/searchApiNo`;
}
