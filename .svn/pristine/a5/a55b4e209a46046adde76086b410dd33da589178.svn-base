import { environment } from '../../../../../environments/environment'
import { urlService } from '../../../../webservice-config/baseurl'
export abstract class MrcUrl {
    private static readonly module = 'service'
    private static readonly controller = 'mrc'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${MrcUrl.module}/${MrcUrl.controller}`
    static readonly addMrcDetails = `${MrcUrl.apiController}/saveMrc`
    //localhost:8383/api/mrc/saveServiceMrc
    static readonly searchByItemNumberUrl = `${environment.baseUrl}/${environment.api}/spares/sparePartMaster/getSparePartItemDetailsForMrc`
    // GET /api/spares/sparePartMaster/getSparePartItemDetailsForMrc
    static readonly getItemDetailsFromItemNumberUrl = `${MrcUrl.apiController}`
    static readonly getTypeList = `${MrcUrl.apiController}`
    static readonly kaiInvoiceAuto = `${MrcUrl.apiController}/search/autocompleteKaiInvoiceNumber`
    // GET /api/service/mrc/getAccpacInvoiceNumber
    static readonly chassisNumberAuto = `${MrcUrl.apiController}/search/chassisNoByAccPacInvoice`
    // GET /api/service/mrc/getChassisNoByAccpacInvoice
    static readonly getCheckPoint = `${environment.baseUrl}/${environment.api}/getAllAggregateWithCheckPoints`
    // GET /api/mrc/getAllMrcAggregateWithCheckPoints
    static readonly searchMrc = `${MrcUrl.apiController}/mrcSearch`
    // POST /api/service/mrc/searchMrc
    static readonly searchKaiInvoiceAuto = `${MrcUrl.apiController}/getInvoiceForSearch`
    static readonly searchChassisNumberAuto = ``
    static readonly searchMrcNumberAuto = `${MrcUrl.apiController}/getMrcSearchForMrcListing`
    // GET /api/service/mrc/getMrcSearchForMrcListing
    static readonly getMrcViewData = `${MrcUrl.apiController}/getMrcById`
    // GET /api/service/mrc/getServiceMrcById/{id}
    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`

    static readonly checkChassisNumber = `${MrcUrl.apiController}/search/checkChassisNumber`
    //localhost:8383/api/service/mrc/search/checkChassisNumber?chassisNumberId=30&mrcFlag=1

    static readonly printPreSalesServiceMrc = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}${urlService.printPreSalesServiceMrc}`;
    static readonly printForm = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}/printFormF22`;
}