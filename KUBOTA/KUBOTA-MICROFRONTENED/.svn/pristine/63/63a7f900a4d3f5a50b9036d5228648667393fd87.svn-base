import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class HotlineReportApi {

    private static readonly module = 'warranty';
    private static readonly controller = 'hotlineReport';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${HotlineReportApi.module}/${HotlineReportApi.controller}`;

    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;
    static readonly dealerDepoList = `${HotlineReportApi.apiController}/dealerDepoList`;
    static readonly condionFailureCode=`${HotlineReportApi.apiController}/condionFailureCode`;
    static readonly hotlinePlant=`${HotlineReportApi.apiController}/hotlinePlant`;
    static readonly hoDeparment=`${HotlineReportApi.apiController}/hoDeparment`;
    static readonly deptIncharge=`${HotlineReportApi.apiController}/deptIncharge`;
     static readonly getFailureType=`${HotlineReportApi.apiController}/getFailureType`;
    static readonly chassisNo=`${HotlineReportApi.apiController}/chassisNo`;
    static readonly autoCompletePartNumber = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/autoCompletePartNumber`;
    static readonly getPartDetailsByPartNumber = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/getPartDetailsByPartNumber`; 
    // Search Api URl
    static readonly searchHotlineNo=`${HotlineReportApi.apiController}/searchHotlineNo`;
    static readonly searchHotlineReport=`${HotlineReportApi.apiController}/searchHotlineReport`
    static readonly hotlineStatus=`${HotlineReportApi.apiController}/hotlineStatus`;
    static readonly submitHotlineReport=`${HotlineReportApi.apiController}/submitHotlineReport`
    static readonly viewHotlineReport=`${HotlineReportApi.apiController}/viewHotlineReport`
    static readonly printWarrantyHotlineReport=`${HotlineReportApi.apiController}/printWarrantyHotlineReport`
}

