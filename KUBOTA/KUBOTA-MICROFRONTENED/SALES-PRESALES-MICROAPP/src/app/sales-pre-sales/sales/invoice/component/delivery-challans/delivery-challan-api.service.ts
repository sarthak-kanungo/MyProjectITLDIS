import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { of } from 'rxjs';
import { InvoiceMaterialByDc, InvoiceMaterialByDcAdaptorService } from '../../model/invoice-material-by-dc-adaptor.service';
import { InvoiceDcByCustomerCode } from '../../model/invoice-dc-by-customer-code-adaptor.service';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';

@Injectable()
export class DeliveryChallanApiService {

  private readonly getMaterialDetailsByDcIdUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getMaterialDetailsByDcId }`;
  constructor(
    private httpclient: HttpClient,
    private invoiceMaterialByDcAdaptorService: InvoiceMaterialByDcAdaptorService
  ) { }
  getMaterialDetail(dc: InvoiceDcByCustomerCode) {
    return this.httpclient.get<BaseDto<InvoiceMaterialByDc[]>>(this.getMaterialDetailsByDcIdUrl, {
      params: new HttpParams().set('dcId', dc.id.toString())
    }).pipe(
      map(res => {
        res.result = this.invoiceMaterialByDcAdaptorService.adapt<InvoiceMaterialByDc[]>(res.result);
        res.result.forEach((element, i) => {
          res.result[i].dcuuid = dc.uuid;
        });
        return res;
      })
    )
  }
}
