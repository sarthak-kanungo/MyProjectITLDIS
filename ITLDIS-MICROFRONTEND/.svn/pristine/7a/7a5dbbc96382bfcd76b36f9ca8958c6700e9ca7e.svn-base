import { environment } from 'src/environments/environment'

export abstract class DealerDesignationMasterApi {
    private static readonly module = 'dealerDesignationMaster'
    private static readonly controller = ''
    // static readonly apiController = `${environment.baseUrl}/${environment.api}`
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${DealerDesignationMasterApi.module}`
    //static readonly submitData = ``
    static readonly submitData = `${DealerDesignationMasterApi.apiController}/saveDealerDesignation`

    static readonly update = `${DealerDesignationMasterApi.apiController}/updateDesignationDetails`
    
    static readonly searchDesignation = `${DealerDesignationMasterApi.apiController}/getDesignationForDealer`
    static readonly searchDealerDepartment = `${DealerDesignationMasterApi.apiController}/departmentForDealerDesignation`
    static readonly designationSearchPage = `${DealerDesignationMasterApi.apiController}/searchDealerDesignationForSearchPage`
    static readonly checkDesignationCode = `${DealerDesignationMasterApi.apiController}/toCheckDesignationCode`
    static readonly checkDesignationName = `${DealerDesignationMasterApi.apiController}/toCheckDesignationName`
    static readonly changeActiveStatus = `${DealerDesignationMasterApi.apiController}/changeActiveStatus`
    static readonly viewDesignation = `${DealerDesignationMasterApi.apiController}/viewDesignationDetails`
}