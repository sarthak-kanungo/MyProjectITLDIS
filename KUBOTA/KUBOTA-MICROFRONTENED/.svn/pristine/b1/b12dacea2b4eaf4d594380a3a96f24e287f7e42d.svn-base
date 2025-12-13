import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from '../../../../../environments/environment';

export abstract class PdcApi {
    private static readonly module = 'service'
    private static readonly controller = 'pdc'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${PdcApi.module}/${PdcApi.controller}`

    static readonly getChassisNumberAutoComplete = `${PdcApi.apiController}/getChassisNumberAutoComplete`
    static readonly getChassisDetailsByChassisNo = `${PdcApi.apiController}/getChassisDetailsByChassisNo`
    static readonly getAggregateAndCheckPointByModel = `${environment.baseUrl}/${environment.api}/getAllAggregateWithCheckPoints`
    static readonly savePdc = `${PdcApi.apiController}/savePdc`
    static readonly searchPdc = `${PdcApi.apiController}/searchPdc`
    static readonly getPdcById = `${PdcApi.apiController}/getPdcById`
    static readonly pdcCreateAutoCompleteChassisNo = `${PdcApi.apiController}/pdcCreateAutoCompleteChassisNo`
    static readonly specificationDropdown = `${environment.baseUrl}/${environment.api}/master/service/checkpointSpecification/specificationDropdown`
    static readonly getChassisDetailsByChassisNoInJobCard = `${environment.baseUrl}/${environment.api}/service/jobcard/getChassisDetailsByChassisNoInJobCard`

    static readonly printPreSalesServicePdc = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}${urlService.printPreSalesServicePdc}`;
}
//http://192.168.15.109:8383/api/service/jobcard/getChassisDetailsByChassisNoInJobCard?chassisNo=k&preSalesFlag=true&jobCardFlag=false

