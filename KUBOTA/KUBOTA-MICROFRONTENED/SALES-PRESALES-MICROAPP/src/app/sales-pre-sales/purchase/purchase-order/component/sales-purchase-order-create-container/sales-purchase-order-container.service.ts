import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class SalesPurchaseOrderContainerService {
  private readonly getAllPoTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.purchaseOrderType}`;
  private readonly getAllDeposUrl = `${environment.baseUrl}${urlService.api}${urlService.depot}`;
  private readonly getKaiOutstandingStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.accpac}${urlService.getOsStatus}`
  private readonly getTotalCreditsFromDealerCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.dealerbankdetails}${urlService.getDealerAvailableCreditByDealerCode}`
  private readonly getKaiStatusChannelFinanceUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getChannelFinanceAvailable}`;
  
  constructor(
    private httpClient: HttpClient
  ) { }

  getPoTypes() {
    return this.httpClient.get(this.getAllPoTypeUrl)
  }
  getDepos(poIdForEdit?: string) {
    let updatedParams = new HttpParams();
    if (poIdForEdit) {
      updatedParams = updatedParams.append('poId', poIdForEdit)
    }
    return this.httpClient.get(this.getAllDeposUrl, {
      params: updatedParams
    })
  }
  getKaiOutstandingStatus(dealerCode: string, poid:string) {
    return this.httpClient.get(this.getKaiOutstandingStatusUrl, {
      params: new HttpParams().set('dealerCode', dealerCode)
          .set('poid', poid)
    })
  }
  getKaiStatusChannelFinance(dealerCode: string) {
    return this.httpClient.get(this.getKaiStatusChannelFinanceUrl, {
      params: new HttpParams().set('dealerCode', dealerCode)
    })
  }
  getTotalCreditsFromDealerCode(dealerCode: string) {
    return this.httpClient.get(this.getTotalCreditsFromDealerCodeUrl, {
      params: new HttpParams().set('dealerCode', dealerCode)
    })
  }
}
