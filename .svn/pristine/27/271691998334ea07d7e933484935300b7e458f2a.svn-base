import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { SaveBtBt } from '../../domain/bin-to-bin-transfer';
import { BtBtApi } from '../../url-utils/btbt-urls';
import { Observable } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class BtbtPageWebService {
  constructor(
    public httpClient: HttpClient
  ) { }
  postBtBtDetails(formData: SaveBtBt) {
    return this.httpClient.post(BtBtApi.saveBtBtDetails, formData)
  }

  getAvlblQtyForStoreBin(itemNo: string, Stockstoreid: number, StockBInId: number) {
    return this.httpClient.get(BtBtApi.getAvlQtyForStoreBin, {
      params: new HttpParams()
        .set('itemNo', itemNo)
        .set('Stockstoreid', Stockstoreid+"")
        .set('StockBInId', StockBInId+"")
    })
  }

  getToBinLocationList(storeId: number, binLocation: string,itemNo: string, selectedFromBin: string): Observable<SelectList[]> {
    return this.httpClient.get<BaseDto<SelectList[]>>(BtBtApi.getToBinLocations, {
      params: new HttpParams()
        .set('storeId', storeId+"")
        .set('binLocation', binLocation)
        .set('itemNo', itemNo)
        .set('tranType','BIN2BIN')
        .set('selectedFromBin', selectedFromBin)
    }).pipe(
      map(res => res.result)
    );
  }

  uploadExcelItemDetail(uploadableFile:any){
      let formData: FormData = new FormData();
      formData.append('file', uploadableFile['file']);
      formData.append('existingItems', uploadableFile['existingItems']);
      
      const headers = new HttpHeaders();
      headers.set('Content-Type', null);
      headers.set('Accept', 'multipart/form-data');
      return this.httpClient.post(BtBtApi.uploadExcel, formData, {
          headers: headers
      });
}
  
}
