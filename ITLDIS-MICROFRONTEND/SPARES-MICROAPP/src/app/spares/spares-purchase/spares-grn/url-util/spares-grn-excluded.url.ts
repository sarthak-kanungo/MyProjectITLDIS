import { SparesGrnUrl } from './spares-grn.url';

export abstract class SparesGrnExcludedUrl {
    static urls: Array<string> = [
        SparesGrnUrl.getBinLocationByStoreId,
        SparesGrnUrl.getSearchSparesInvoiceNo,
        SparesGrnUrl.searchBySpareGrnNumber,
        SparesGrnUrl.searchInvoiceNumberFromGrn,
        SparesGrnUrl.searchSupplierName,
        SparesGrnUrl.searchPoNumberForGrn
    ]
}