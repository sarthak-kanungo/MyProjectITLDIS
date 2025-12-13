import { urlService } from "src/app/webservice-config/baseurl"
import { environment } from "src/environments/environment"

export abstract class PartyMasterApi {
    private static readonly module = '/master'
    private static readonly controller = '/partyMaster'

    static readonly submitData = `${environment.baseUrl}${urlService.api}${PartyMasterApi.module}${PartyMasterApi.controller}${urlService.addPartyMaster}`
    static readonly categoryDropdown = `${environment.baseUrl}${urlService.api}${PartyMasterApi.module}${PartyMasterApi.controller}${urlService.getPartyCategories}`
    static readonly partyCodeAuto = `${environment.baseUrl}${urlService.api}${PartyMasterApi.module}${PartyMasterApi.controller}${urlService.partyCodeAuto}`
    static readonly partyNameAuto = `${environment.baseUrl}${urlService.api}${PartyMasterApi.module}${PartyMasterApi.controller}${urlService.partyNameAuto}`
    static readonly partyLocationAuto = ``
    static readonly gstNoAuto = ``
    static readonly panNoAuto = ``
    static readonly aadharCardNoAuto = ``
    static readonly titleDropdown = ``
    static readonly firstNameAuto = ``
    static readonly middleNameAuto = ``
    static readonly lastNameAuto = ``
    static readonly designationAuto = ``
    static readonly mobileAuto = ``
    static readonly emailAuto = ``
    static readonly pinCodeAuto = ``
    static readonly localityDropdown = ``
    static readonly branchCodeAuto = `${environment.baseUrl}${urlService.api}${PartyMasterApi.module}${PartyMasterApi.controller}${urlService.getBranchCode}`
    static readonly searchPartyFilter = `${environment.baseUrl}${urlService.api}${PartyMasterApi.module}${PartyMasterApi.controller}${urlService.searchParty}`
    static readonly updateData = `${environment.baseUrl}${urlService.api}${PartyMasterApi.module}${PartyMasterApi.controller}${urlService.updatePartyMaster}`

}