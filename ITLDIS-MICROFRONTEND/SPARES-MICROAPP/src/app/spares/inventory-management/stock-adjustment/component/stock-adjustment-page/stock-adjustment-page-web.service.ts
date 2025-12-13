import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SaveStockAdjustment } from '../../domain/stock-adjustment.domain';
import { StockAdjustmentApi } from '../../url-utils/stock-adjustment-urls';

@Injectable()
export class StockAdjustmentPageWebService {

  constructor(
    public httpClient: HttpClient
  ) { }
  postAdjustDetails(formData: SaveStockAdjustment) {
    return this.httpClient.post(StockAdjustmentApi.saveStockAdjDetails, formData)
  }
  
  getAdjustDetails(id:number){
      return this.httpClient.get(StockAdjustmentApi.getStockAdjDetails, {
          params : new HttpParams().set("id",id+"")
      });
  }
  approveAdjustment(remark:string, adjustmentId: string, flag:string){
    return this.httpClient.post(StockAdjustmentApi.adjustmentApproval, {
        'adjId': adjustmentId, 'remarks': remark, 'approvalStatus':flag
    });
  }
}
