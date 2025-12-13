import { environment } from '../../../../../environments/environment'

export abstract class MachineStockApi {
    private static readonly module = 'spares'
    private static readonly enquiryModule = 'salesandpresales'
    private static readonly controller = 'machinestock'
    private static readonly enquiryController = `${environment.baseUrl}/${environment.api}/${MachineStockApi.module}/enquiry`

    private static readonly apiController = `${environment.baseUrl}/${environment.api}/${MachineStockApi.module}/${MachineStockApi.controller}`

    public static readonly searchMachineStock = `${MachineStockApi.apiController}/searchMachineStock`
        
    public static readonly getModelBySeries = `${environment.baseUrl}/${environment.api}/master/product/getModelBySeries`
    
    public static readonly getSeriesByProduct = `${environment.baseUrl}/${environment.api}/master/product/getSeriesByProduct`
            
    public static readonly getAllProduct = `${environment.baseUrl}/${environment.api}/master/product/getAllProduct`
            
    public static readonly getSubModel = `${environment.baseUrl}/${environment.api}/master/product/getSubModel`
    
    public static readonly getVariant = `${environment.baseUrl}/${environment.api}/master/product/getVariant`

    public static readonly getMachineStockExcel = `${MachineStockApi.apiController}/getMachineStockExcel`
}