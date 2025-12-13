import { environment } from '../../../../../environments/environment';

export abstract class SparesPaymentReceiptApi {
    private static readonly module = 'spares';
    private static readonly dbModule = 'dbEntities';
    private static readonly controller = 'paymentReceipt';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${SparesPaymentReceiptApi.module}/${SparesPaymentReceiptApi.controller}`;
    static readonly dbEntityController = `${environment.baseUrl}/${environment.api}/${SparesPaymentReceiptApi.module}/${SparesPaymentReceiptApi.dbModule}`;
    static readonly salesApiController = `${environment.baseUrl}/${environment.api}/sales/paymentReceipt`;

    static readonly getReceiptModeUrl = `${SparesPaymentReceiptApi.salesApiController}/getReceiptMode`;
    static readonly getCustomerType = `${SparesPaymentReceiptApi.dbEntityController}/getSpareCustomerTypeDropdown`;
    static readonly getCustomerCodeUrl = `${environment.baseUrl}/${environment.api}/customerMaster/autocompleteMobileNumber`;
    static readonly getCustomerDetailsUrl = `${environment.baseUrl}/${environment.api}/customerMaster/getCustomerDetailsForPaymentReceipt`;
    static readonly getReceiptTypeUrl = `${environment.baseUrl}/${environment.api}/sales/${SparesPaymentReceiptApi.controller}/getSparesReceiptType`;
    static readonly getDealerDetailsUrl = `${environment.baseUrl}/${environment.api}/${SparesPaymentReceiptApi.module}/salesOrder/getDealerDetails`;
    static readonly getDealerCodeAutocompleteUrl = `${environment.baseUrl}/${environment.api}/${SparesPaymentReceiptApi.module}/salesOrder/getDealerCodeAutocomplete`;
    static readonly getRetailerOrMechanicDetailsUrl = `${environment.baseUrl}/${environment.api}/${SparesPaymentReceiptApi.module}/salesOrder/getRetailerOrMechanicDetails`;
    static readonly getRetailerOrMechanicAutocompleteUrl = `${environment.baseUrl}/${environment.api}/${SparesPaymentReceiptApi.module}/salesOrder/getRetailerOrMechanicAutocomplete`;

    static readonly getCheckDdBankUrl = `${SparesPaymentReceiptApi.salesApiController}/getCheckDdBank`;
    static readonly getCardTypeUrl = `${SparesPaymentReceiptApi.salesApiController}/getCardType`;
    static readonly getBankTypeUrl = `${SparesPaymentReceiptApi.salesApiController}/getBank`;

    static readonly savePaymentReceiptUrl = `${SparesPaymentReceiptApi.salesApiController}/addPayment`;
    static readonly searchByReceiptNumber = `${SparesPaymentReceiptApi.salesApiController}/searchByReceiptNumber`;
    static readonly searchByCustomerNameUrl = `${SparesPaymentReceiptApi.salesApiController}/searchByCustomerName`;
    static readonly searchByCustomerMobileNoUrl = `${SparesPaymentReceiptApi.salesApiController}/searchByMobileNumber`;
    static readonly searchPaymentReceiptForSpare = `${SparesPaymentReceiptApi.salesApiController}/searchPaymentReceiptForSpare`;
    static readonly getSparePaymentReceiptByIdUrl = `${SparesPaymentReceiptApi.salesApiController}/getSparePaymentReceiptById`;
}