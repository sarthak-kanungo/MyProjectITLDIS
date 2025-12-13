import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from '../../../../../environments/environment';

export abstract class PscApi {
    private static readonly module = 'service'
    private static readonly controller = 'psc'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${PscApi.module}/${PscApi.controller}`

    static readonly autoCompleteChassisNumberForSearch = `${PscApi.apiController}/autoCompleteChassisNumberForSearch`
    static readonly autoCompleteChassisNumber = `${PscApi.apiController}/autoCompleteChassisNumber`
    static readonly getDetailsByChassisNo = `${PscApi.apiController}/getDetailsByChassisNo`
    static readonly getAllCheckpoints = `${environment.baseUrl}/${environment.api}/getAllAggregateWithCheckPoints`
    static readonly autoCompletePscNo = `${PscApi.apiController}/autoCompletePscNo`
    static readonly savePsc = `${PscApi.apiController}/savePsc`
    static readonly searchPsc = `${PscApi.apiController}/searchPsc`
    static readonly getPscById = `${PscApi.apiController}/getPscById`

   //api/service/psc/autoCompleteChassisNumberForSearch?chassisNo=K

   static readonly printPreSalesServicePsc = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}${urlService.printPreSalesServicePsc}`;
}