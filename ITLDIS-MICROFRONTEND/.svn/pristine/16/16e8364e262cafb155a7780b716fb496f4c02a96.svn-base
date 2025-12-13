import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams , HttpResponse, HttpHeaders} from '@angular/common/http';
import { SparePoApi } from '../../url-utils/spares-purchse-order-urls';
import { map } from 'rxjs/operators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Injectable()
export class ItemDetailsWebService {

  tcsPercentageListUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getTcsPercentageList}`;
  
  constructor(
    private httpClient: HttpClient,
  ) { }

  
  public tcsPercList() : Observable<any>{
      return this.httpClient.get(SparePoApi.apiController+"/tcsPercList");
  }
  public tcsPercentageList() {
    return this.httpClient.get(this.tcsPercentageListUrl)
  }
  public searchByItemNumberAuto(searchKey: string, itemIdsString: string, orderType: string) {
    return this.httpClient.get(SparePoApi.getItemNumberAutoComplete, {
      params: new HttpParams().set('itemNumber', searchKey).set('itemId', itemIdsString).set('orderType', orderType)
    }).pipe(map(res => res['result']))
  }
  public getItemDetailsFromItemNumberId(itemNumber: string, orderType: string, priceType: string) {
    return this.httpClient.get(SparePoApi.getItemDetailsByItemId, {
      params: new HttpParams().set('itemId', itemNumber).set('orderType', orderType).set('priceType', priceType)
    })
  }
  public downloadTemplateForPoItemDetail(filename) : Observable<HttpResponse<Blob>> {
    return this.httpClient.get<Blob>(SparePoApi.apiController+"/downloadTemplate", { 
        params: new HttpParams().set("filename",filename),
        observe: 'response', responseType: 'blob' as 'json' });
  }
  public uploadExcelPoItemDetail(uploadableFile:any){
      let formData: FormData = new FormData();
      formData.append('orderType', uploadableFile['orderType']);
      formData.append('priceType', uploadableFile['priceType']);
      formData.append('file', uploadableFile['file']);
      formData.append('existingItems', uploadableFile['existingItems']);
      
      const headers = new HttpHeaders();
      headers.set('Content-Type', null);
      headers.set('Accept', 'multipart/form-data');

      return this.httpClient.post(SparePoApi.apiController+"/uploadExcel", formData, {
          headers: headers
      });
    }
}
