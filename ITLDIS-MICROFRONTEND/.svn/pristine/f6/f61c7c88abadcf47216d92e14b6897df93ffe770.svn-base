import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class RfcApi {

    private static readonly module = 'warranty';
    private static readonly controller = 'retrofitmentcampaign';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${RfcApi.module}/${RfcApi.controller}`;

    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;
    static readonly autoCompletePartNumber = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/autoCompletePartNumber`; 
    static readonly getPartDetailsByPartNumber = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/getPartDetailsByPartNumber`;
    static readonly saveRetrofitmentCampaign = `${RfcApi.apiController}/saveRetrofitmentCampaign`;
    static readonly searchRetrofitmentCampaign = `${RfcApi.apiController}/searchRetrofitmentCampaign`;
    static readonly viewRetrofitmentCampaign = `${RfcApi.apiController}/viewRetrofitmentCampaign`;
    static readonly searchRetrofitmentStatus = `${RfcApi.apiController}/searchRetrofitmentStatus`;
    static readonly searchRetrofitmentNo = `${RfcApi.apiController}/searchRetrofitmentNo`;
    static readonly getAutoCompleteJobCode = `${RfcApi.apiController}/getAutoCompleteJobCode`;
    static readonly printWarrantyRetrofitmentReport=`${RfcApi.apiController}/printWarrantyRetrofitmentReport`
}
