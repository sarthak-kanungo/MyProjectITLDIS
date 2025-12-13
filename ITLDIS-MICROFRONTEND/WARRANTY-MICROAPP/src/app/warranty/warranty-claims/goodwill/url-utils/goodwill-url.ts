import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class GoodwillApi {
    private static readonly module = 'warranty';
    private static readonly controller = 'goodwill';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${GoodwillApi.module}/${GoodwillApi.controller}`;

    static readonly dropDownGoodwillType = `${GoodwillApi.apiController}/dropDownGoodwillType`;
    static readonly dropDownPriceType = `${GoodwillApi.apiController}/dropDownPriceType`;
    static readonly searchWarrantyGoodwill = `${GoodwillApi.apiController}/searchWarrantyGoodwill`;
    static readonly saveGoodwill = `${GoodwillApi.apiController}/saveGoodwill`;
    static readonly warrantyGoodwillView = `${GoodwillApi.apiController}/warrantyGoodwillView`;
    static readonly approveWarrantyGoodwill = `${GoodwillApi.apiController}/approveWarrantyGoodwill`;
    static readonly dropDownGoodwillStatus = `${GoodwillApi.apiController}/dropDownGoodwillStatus`;
    static readonly searchAutoCompleteGoodwillNo = `${GoodwillApi.apiController}/searchAutoCompleteGoodwillNo`;
}
