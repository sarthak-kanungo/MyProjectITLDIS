import { urlService } from 'src/app/webservice-config/baseurl'
import { environment } from '../../../../../environments/environment'

export abstract class PdiUrl {
    private static readonly module = 'service'
    private static readonly control = 'pdi'
    private static readonly master = 'master'
    private static readonly controller = 'pdiAggregate'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${PdiUrl.master}/${PdiUrl.module}/${PdiUrl.controller}`
    static readonly apiSearchController = `${environment.baseUrl}/${environment.api}/${PdiUrl.module}/${PdiUrl.control}`

    static readonly getDropDownCheckListDataUrl = `${environment.baseUrl}/${environment.api}/master/service/checkpointSpecification/specificationDropdown`
    static readonly chassisNumberAuto = `${PdiUrl.apiController}/autoCompleteChassisNo`
    static readonly getDataFromChasisNumberUrl = `${PdiUrl.apiController}/grnDetailsByChassisNo`
    static readonly getDataFromModelURL = `${environment.baseUrl}/${environment.api}/getAllAggregateWithCheckPoints`
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`
    static readonly savePdiDataUrl = `${PdiUrl.apiSearchController}/savePdi`

    static readonly chassisNumberUrl = `${PdiUrl.apiSearchController}/search/autocompleteChassis`
    static readonly invoiceNumberUrl = `${PdiUrl.apiSearchController}/search/autocompleteKaiInvoiceNumber`
    static readonly grnNumberUrl = `${PdiUrl.apiSearchController}/search/autocompleteDmsGrnNumber`

    static readonly postPdiTableSearchUrl = `${PdiUrl.apiSearchController}/pdiSearch`
    static readonly dataForViewUrl = `${PdiUrl.apiSearchController}/getPdiById`
    static readonly printPreSalesServicePdi = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}${urlService.printPreSalesServicePdi}`;
}

