import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
// import { HttpClient } from 'node_modules_1/@angular/common/http';
import { blockPartsForSale } from '../url-util/url-util';

@Injectable({
  providedIn: 'root'
})
export class BlockPartServiceService {

  constructor(private http:HttpClient) { }
  public getPartsNoByPartNo(partNoStr:string) {
    return this.http.get(blockPartsForSale.getPartsNoByPartNo,{
      params: new HttpParams().set('partNoStr', partNoStr)
    })
  }
  public getPartDetailsByPartNo(partNo:string) {
    return this.http.get(blockPartsForSale.getPartDetailsByPartNo,{
      params: new HttpParams().set('partNo', partNo)
    })
  }
  getBlockPartForSaleSearch(searchFilter:any){
    return this.http.post(blockPartsForSale.getBlockPartForSaleSearch, searchFilter);
  }
  changeActiveStatus(id) {
    return this.http.get(blockPartsForSale.changeActiveStatus,  {
      params: new HttpParams().set('id', id)
    });
  }
}
