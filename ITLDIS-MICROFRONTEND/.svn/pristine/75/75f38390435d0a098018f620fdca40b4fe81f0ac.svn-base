import { environment } from '../../../../../environments/environment'

export abstract class StockAdjustmentApi {
    private static readonly module = 'spares'
    private static readonly controller = 'stockAdjustment'

    private static readonly apiController = `${environment.baseUrl}/${environment.api}/${StockAdjustmentApi.module}/${StockAdjustmentApi.controller}`

    public static readonly excelUploadUri = `${StockAdjustmentApi.apiController}/excelUpload`
        
    public static readonly getStockValue = `${StockAdjustmentApi.apiController}/getStockValue`
        
    public static readonly saveStockAdjDetails = `${StockAdjustmentApi.apiController}/saveStockAdjDetails`
        
    public static readonly searchStockAdjDetails = `${StockAdjustmentApi.apiController}/seachStockAdjDetails`
    
    public static readonly getStockAdjDetails = `${StockAdjustmentApi.apiController}/getStockAdjDetails`
        
    public static readonly adjustmentNoAuto = `${StockAdjustmentApi.apiController}/adjustmentNoAuto`

    public static readonly adjustmentApproval = `${StockAdjustmentApi.apiController}/approval`

    public static readonly downloadReportExcelUrl = `${StockAdjustmentApi.apiController}/downloadAdjustmentReport`
    static readonly lookupUrl = `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`;
    static readonly getDealerCodeAutocomplete = `${StockAdjustmentApi.apiController}/getDealerCodeAutocomplete`;
}