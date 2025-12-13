import { environment } from '../../../../../environments/environment';

export abstract class TransporterApi {
    private static readonly module = 'transporter'
    private static readonly controller = 'enquiryTransporter'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${TransporterApi.module}/${TransporterApi.controller}`

    static readonly saveEnquiryTransporter = `${TransporterApi.apiController}/SaveEnquiryTransporter`
    static readonly searchTransporterMaster = `${TransporterApi.apiController}/searchTransporterMaster`
    static readonly getTransporterCodeAutocomplete = `${TransporterApi.apiController}/getTransporterCodeAutocomplete`
    static readonly getTransporterNameAutocomplete = `${TransporterApi.apiController}/getTransporterNameAutocomplete`
    static readonly changeActiveStatus = `${TransporterApi.apiController}/changeActiveStatus`
}//api/salesAndPrSales/enquirySource/changeActiveStatus?id=1