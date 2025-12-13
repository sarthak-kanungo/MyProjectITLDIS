import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';
import { map } from 'rxjs/operators';
import { PartReturnUrl } from '../../url-util/part-return.url';


@Injectable({
  providedIn: 'root'
})
export class SearchPartReturnApiService {

  constructor(private httpClient: HttpClient) { }
  searchPartRequisitionNo(serachKey:string){
    return this.httpClient.get<BaseDto<SelectList[]>>(PartReturnUrl.searchByRequisitionNo, {
      params: new HttpParams().set('requisitionNo', serachKey)
    }).pipe(map(res=>res.result));
  }
  searchByJobCardNoForPartReturn(jobCardNo:string){
    return this.httpClient.get<BaseDto<SelectList[]>>(PartReturnUrl.searchByJobCardNo, {
        params: new HttpParams().set('jobCardNo', jobCardNo)
    })
        .pipe(map(res => res.result));
  }
  searchByReturnNo(returnNo:string){
      return this.httpClient.get<BaseDto<SelectList[]>>(PartReturnUrl.searchByReturnNo, {
          params: new HttpParams().set('returnNo', returnNo)
      })
          .pipe(map(res => res.result));
    }
  
  getReasonsForReturn() {
      return this.httpClient.get<BaseDto<SelectList[]>>(PartReturnUrl.getReasonsForReturn)
          .pipe(map(res => res.result));
  }
}
