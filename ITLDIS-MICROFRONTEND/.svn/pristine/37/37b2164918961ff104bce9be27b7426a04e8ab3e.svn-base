import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseDto } from "BaseDto";
import { Observable } from "rxjs";
import { SalesReportApi } from "../../url-utils/sales-report-url";

@Injectable({
    providedIn:'root',
}
)

export class machineInventoryService{
    constructor(
        private httpClient:HttpClient,
       
      ) { 

      }
      downloadInventoryReport(filter): Observable<HttpResponse<Blob>>{
        return this.httpClient.post<Blob>(SalesReportApi.downloadCurrentStockReport, filter,
            {observe: 'response', responseType: 'blob' as 'json' }
          )
      }
}