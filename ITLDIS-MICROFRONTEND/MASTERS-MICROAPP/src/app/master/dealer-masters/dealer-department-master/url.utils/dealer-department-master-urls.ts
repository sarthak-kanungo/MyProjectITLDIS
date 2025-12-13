import { environment } from 'src/environments/environment'

export abstract class DealerDepartmentMasterApi {
    private static readonly module = 'dealerDepartmentMaster'
    private static readonly controller = ''
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${DealerDepartmentMasterApi.module}`
    static readonly submitData = `${DealerDepartmentMasterApi.apiController}/saveDealerDepartment`
    static readonly update = `${DealerDepartmentMasterApi.apiController}/updateDepartmentDetails`

    static readonly departmentCodeAndName = `${DealerDepartmentMasterApi.apiController}/departmentCodeAndName`
    static readonly departmentNameDropdown = `${DealerDepartmentMasterApi.apiController}/departmentNameDropdown`

    static readonly departmentSearchPage = `${DealerDepartmentMasterApi.apiController}/getDeptDetailsForSearchPage`

    static readonly checkDepartmentCode = `${DealerDepartmentMasterApi.apiController}/toCheckDepartmentCode`
    static readonly checkDepartmentName = `${DealerDepartmentMasterApi.apiController}/toCheckDepartmentName`
    static readonly changeActiveStatus = `${DealerDepartmentMasterApi.apiController}/changeActiveStatus`
    static readonly viewDepartment = `${DealerDepartmentMasterApi.apiController}/viewDepartmentDetails`
    
}