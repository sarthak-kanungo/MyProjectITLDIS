import { SparesSalesInvoiceApi } from './spares-sales-invoice-api';

export abstract class SparesInvoiceExcludeUrl {
    static sparesInvoiceExcludeUrls: Array<string> = [
        SparesSalesInvoiceApi.documentAutoComplete
    ]
}