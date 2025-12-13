import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class dealerTerritoryApi {

    private static readonly module = 'master';
    private static readonly controlller = 'kaicommonmaster';
    private static readonly api='territoryMaster'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${dealerTerritoryApi.module}/${dealerTerritoryApi.controlller}/${dealerTerritoryApi.api}`;

    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;
    static readonly getDealerList = `${dealerTerritoryApi.apiController}/getAllDealers`;
    static readonly getBranch = `${dealerTerritoryApi.apiController}/getBranchByDealerId`;
    static readonly getCountry = `${dealerTerritoryApi.apiController}/getCountry`;
    static readonly getState=`${dealerTerritoryApi.apiController}/getState`;
    static readonly getDistrict=`${dealerTerritoryApi.apiController}/getDistrict`;
    static readonly  getTehsil=`${dealerTerritoryApi.apiController}/getTehsil`;
    static readonly saveTerritoryMapping=`${dealerTerritoryApi.apiController}/saveTerritoryMapping`;
    static readonly search=`${dealerTerritoryApi.apiController}/searchDTM`;
    static readonly view=`${dealerTerritoryApi.apiController}/viewDealerTerritoryMapping`;
    static readonly autoSearchTerritoryNo=`${dealerTerritoryApi.apiController}/autoSearchTerritoryNo`;
    static readonly autoSearchBranchName=`${dealerTerritoryApi.apiController}/autoSearchBranchName`;
}

