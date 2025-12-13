import { SoApi } from './so-urls';
export abstract class SoExcludedUrl {
    static soExcludedUrls: Array<string> = [
        SoApi.autoCompleteCustomerNMobileN,
        SoApi.autocompleteQuotationNo,
        SoApi.getStateAutocomplete,
        SoApi.getDistrictAutocomplete,
        SoApi.getTehsilAutocomplete,
        SoApi.getCityAutocomplete,
        SoApi.getPinCodeAutocomplete,
        SoApi.getPostOfficeAutocomplete,
        SoApi.getRetailerOrMechanicAutocomplete,
        SoApi.getDealerCodeAutocomplete,
        SoApi.autocompleteQuotationNo,
        SoApi.getRetailerOrMechanicDetails,
        SoApi.getSalesOrderNumberAutocomplete,
        SoApi.autocompletePartNo,
        SoApi.getCustomerNameAutocomplete,
    ]
}
