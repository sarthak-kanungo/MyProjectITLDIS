import { environment } from '../../../../../environments/environment'

export abstract class CurrentStockaApi {
    private static readonly module = 'currentstock'
    private static readonly controller = 'customerMaster'
    private static readonly masterModule = 'master'
    private static readonly areaMasterController = 'areamaster'
    private static readonly spareController = 'spares'

    private static readonly sparePartMasterController = 'sparePartMaster'

    private static readonly apiController = `${environment.baseUrl}/${environment.api}/${CurrentStockaApi.spareController}/${CurrentStockaApi.module}`

    // static readonly saveBtBtDetails = `${BtBtApi.apiController}/saveBtBtDetails`
    // static readonly getAvlQtyForStoreBin = `${BtBtApi.apiController}/getAvlQtyForStoreBin`
    // static readonly getToBinLocations = `${BtBtApi.apiController}/getToBinLocations`
    static readonly searchCurrentStock = `${CurrentStockaApi.apiController}/searchCurrentStock`
    // static readonly searchAutoTransferNumber = `${BtBtApi.apiController}/searchAutoTransferNumber`
    // static readonly uploadExcel = `${BtBtApi.apiController}/uploadExcel` 
    static readonly downloadExcelReport = `${CurrentStockaApi.apiController}/downloadCurrentStockExcelReport` 
}