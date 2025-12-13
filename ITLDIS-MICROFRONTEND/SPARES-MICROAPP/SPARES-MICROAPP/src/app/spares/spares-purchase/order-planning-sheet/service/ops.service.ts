import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { orderplanningsheet } from '../url-utils/order-planning-url';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { searchOrderPlanning } from '../search-order-planning-sheet/search-order-planning-domain';

@Injectable({
  providedIn: 'root'
})
export class OPSService {

  constructor(private http:HttpClient) {

   }
   getSystemDate(){
    return this.http.get<BaseDto<any>>(orderplanningsheet.getSystemDateUrl);
  }
  autoGenerateSheetNo(opsNo:any): Observable<any> {
    return this.http.get<BaseDto<any>>(orderplanningsheet.autoGetOPSheetNo, {
      params: new HttpParams().set('opsNo', opsNo)
    }).pipe(map(res => res.result))
  }
  seachOrderPlanningSheet(planningData: searchOrderPlanning): Observable<BaseDto<Array<searchOrderPlanning>>> {
    return this.http.post<BaseDto<Array<searchOrderPlanning>>>(orderplanningsheet.searchOPSheet, planningData)
  }
 
  getReorderQty(){
    return this.http.get<BaseDto<any>>(orderplanningsheet.getReorderQtyMonth);
  }
  getSuggestedQty(){
    return this.http.get<BaseDto<any>>(orderplanningsheet.getSuggestedQtyMonth);
  }

  getLogic(){
    return this.http.get(orderplanningsheet.getAllLogic);
  }

  getOrderType(){
    return this.http.get(orderplanningsheet.getOrderType);
  }
  getItemDetails(getorderPlanningdata:any): Observable<any> {
    return this.http.post<BaseDto<Array<any>>>(orderplanningsheet.getOPSheetItemDetails, getorderPlanningdata)
    // return this.http.post<BaseDto<any>>(orderplanningsheet.getOPSheetItemDetails, {
    //   // params: new HttpParams().set('logicId', logicId)
    // }).pipe(map(res => res.result))
  }

  getOrderPlannningSheetView(opSheetNo: string): Observable<any> {
    return this.http.get<BaseDto<any>>(orderplanningsheet.viewOPSheet, {
      params: new HttpParams().append('opSheetNo', opSheetNo)
    }).pipe(map(res => res.result))
  }


  submitOrderPlanningSheet(submitform:any):Observable<any>{
    return this.http.post(orderplanningsheet.saveOrderPlanningSheet,submitform);
  }  

  searchOrderPlanningSheet(searchForm:any):Observable<any>{
    return this.http.post(orderplanningsheet.searchOrderPlanningSheet,searchForm);
  }

  downloadViewPdf(opSheetId:any,opSheetNo:any,printStatus:any){
    return this.http.get<Blob>(orderplanningsheet.printOPSheetReport, {
      params: new HttpParams().set('opSheetId', opSheetId)
                              .set('opSheetNo', opSheetNo).set('printStatus',printStatus),
     observe: 'response', responseType: 'blob' as 'json'                         
  });
  }


}
