import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class WcrReportApi {
    private static readonly module = 'warranty';
    private static readonly controller = 'Wcr';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${WcrReportApi.module}/${WcrReportApi.controller}`;

    static readonly wcrReport = `${WcrReportApi.apiController}/wcrReport`;
    static readonly getDealerCodeAutocomplete = `${environment.baseUrl}${urlService.api}${urlService.spares}${urlService.salesOrder}/getDealerCodeAutocomplete`

} 
