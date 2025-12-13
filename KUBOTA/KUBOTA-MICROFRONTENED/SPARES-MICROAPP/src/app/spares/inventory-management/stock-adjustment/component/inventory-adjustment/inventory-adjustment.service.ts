import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { StockAdjustmentApi } from '../../url-utils/stock-adjustment-urls';
import { map } from 'rxjs/operators';
@Injectable()
export class InventoryAdjustmentService {

  constructor(public httpClient: HttpClient) { }
  
  uploadExcelItemDetail(uploadableFile:any){
      let formData: FormData = new FormData();
      formData.append('file', uploadableFile['file']);
      const headers = new HttpHeaders();
      headers.set('Content-Type', null);
      headers.set('Accept', 'multipart/form-data');
      return this.httpClient.post(StockAdjustmentApi.excelUploadUri, formData, {
          headers: headers
      });
  }
  
  getStockValueDetails(itemNo:string, storeId:number, binId:number){
      return this.httpClient.get(StockAdjustmentApi.getStockValue, {
          params : new HttpParams().set("itemNo", itemNo).set("storeId", storeId+"").set("binId", binId+"")
      }).pipe(
              map(response => response['result'])
         );
  }
}
