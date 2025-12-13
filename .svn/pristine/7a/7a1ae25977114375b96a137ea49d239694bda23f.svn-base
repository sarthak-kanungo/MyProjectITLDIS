import { environment } from "src/environments/environment";

export abstract class orderplanningsheet {
    private static readonly module = 'spares'
    private static readonly controller = 'purchase';


    static readonly apiController = `${environment.baseUrl}/${environment.api}/${orderplanningsheet.module}/${orderplanningsheet.controller}`
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`;
    static readonly autoGetOPSheetNo = `${orderplanningsheet.apiController}/orderplanningsheet/autoGetOPSheetNo`;
    static readonly searchOPSheet = `${orderplanningsheet.apiController}/orderplanningsheet/searchOPSheet`;
    static readonly getReorderQtyMonth = `${orderplanningsheet.apiController}/orderplanningsheet/getReorderQtyMonth`;
   static readonly getOrderType=`${orderplanningsheet.apiController}/orderplanningsheet/getOrderTypes`;
    static readonly getSuggestedQtyMonth = `${orderplanningsheet.apiController}/orderplanningsheet/getSuggestedQtyMonth`;
    static readonly getAllLogic = `${orderplanningsheet.apiController}/orderplanningsheet/getAllLogic`;
    static readonly getOPSheetItemDetails = `${orderplanningsheet.apiController}/orderplanningsheet/getOPSheetItemDetails`;
    static readonly viewOPSheet = `${orderplanningsheet.apiController}/orderplanningsheet/viewOPSheet`;
    static readonly saveOrderPlanningSheet = `${orderplanningsheet.apiController}/orderplanningsheet/saveOPSheet`;
    static readonly searchOrderPlanningSheet = `${orderplanningsheet.apiController}/orderplanningsheet/searchOPSheet`;
    static readonly printOPSheetReport=`${orderplanningsheet.apiController}/orderplanningsheet/printOPSheetReport`;
   
}