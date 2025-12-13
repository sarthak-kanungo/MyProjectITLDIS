import { environment } from '../../../../../environments/environment';

export class itldisUserApi{
    private static readonly module = 'itldisuser'
    private static readonly controller = ''
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${itldisUserApi.module}`

    static readonly submitData = `${itldisUserApi.apiController}`
    static readonly updateData = `${itldisUserApi.apiController}/update`

    static readonly employeeIdSearch = `${itldisUserApi.apiController}/employeeCodeDropdown`
    static readonly employeeNameSearch = ``
    static readonly viewitldisUserDetails = `${itldisUserApi.apiController}/viewitldisUserDetails`
    static readonly employeeSearch = `${itldisUserApi.apiController}/searchitldisUsers`

    static readonly employeeIdCreate = ``
    static readonly loginIdStatus = ``
    static readonly assignRole = `${itldisUserApi.apiController}/assignRoleDropdown`
    static readonly assignFunctions = `${itldisUserApi.apiController}/assignFunctions`

}