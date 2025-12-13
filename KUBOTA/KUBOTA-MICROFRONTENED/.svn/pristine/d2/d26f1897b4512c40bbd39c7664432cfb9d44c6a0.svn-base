import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class DelaerMasterApi {
    
    private static readonly module = 'master';
    private static readonly controller = 'dealerMaster';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${DelaerMasterApi.module}/${DelaerMasterApi.controller}`;
    static readonly searchController = `${environment.baseUrl}/${environment.api}/${DelaerMasterApi.controller}`;
    
    static readonly searchDealer = `${DelaerMasterApi.searchController}/searchDealer`;
    static readonly dealerNameAuto = `${DelaerMasterApi.searchController}/dealerNameAuto`;
    static readonly dealerCodeAuto = `${DelaerMasterApi.searchController}/dealerCodeAuto`;
    static readonly allocatedTerritoryDropdown = `${DelaerMasterApi.searchController}/allocatedTerritoryDropdown`;
    static readonly areaLevelDropdown = `${DelaerMasterApi.searchController}/areaLevelDropdown`;
   
}