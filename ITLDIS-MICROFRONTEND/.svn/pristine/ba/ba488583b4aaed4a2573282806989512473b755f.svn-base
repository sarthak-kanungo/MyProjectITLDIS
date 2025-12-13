import { environment } from '../../../../../environments/environment';

export abstract class WpdcApi {
    private static readonly module = 'warranty';
    private static readonly controller = 'deliverychallan';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${WpdcApi.module}/${WpdcApi.controller}`;

    static readonly getClaimPartInDc = `${WpdcApi.apiController}/getClaimPartInDc`;
    static readonly saveDeliveryChallan = `${WpdcApi.apiController}/saveDeliveryChallan`;
    static readonly updateWarrantyDeliveryChallan = `${WpdcApi.apiController}/updateWarrantyDeliveryChallan`;
    static readonly deliveryChallanSearch = `${WpdcApi.apiController}/deliveryChallanSearch`;
    static readonly viewWarrantyDeliveryChallan = `${WpdcApi.apiController}/viewWarrantyDeliveryChallan`;
    static readonly searchAutoCompleteWcrNo = `${environment.baseUrl}/${environment.api}/${WpdcApi.module}/Wcr/searchAutoCompleteWcrNo`
} 

