import { environment } from "src/environments/environment";

export abstract class SalesReportApi {
    private static readonly module = 'salesandpresales';
    private static readonly controller = 'reports';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${SalesReportApi.module}/${SalesReportApi.controller}`
    static readonly searchMachineMasterReport = `${SalesReportApi.apiController}/searchMachineMasterReport`;
    static readonly downloadMachineMasterReport = `${SalesReportApi.apiController}/downloadMachineMasterReport`;
    static readonly downloadCurrentStockReport=`${SalesReportApi.apiController}/printMachineInventoryReport`
}