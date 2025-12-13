import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { urlService } from '../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  private readonly getSystemGeneratedDateUrl = `${environment.baseUrl}${urlService.api}${urlService.getSystemGeneratedDate}`;

  constructor() { }
  getFormattedDate(data) {
    // 2019-04-24 18:19:38.4900000
    var todayTime = new Date(data);
    var month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
    var day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
    var year = todayTime.getFullYear();
    var hh = todayTime.getHours();
    var mm = todayTime.getMinutes();
    var ss = todayTime.getSeconds();
    var ms = todayTime.getMilliseconds()
    // 
    return `${year}-${month}-${day} ${hh}:${mm}:${ss}.${ms}`;
  }
  getDateIntoYYYYMMDD(data): string {
    var todayTime = new Date(data);
    var month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
    var day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
    var year = todayTime.getFullYear();
    return `${year}-${month}-${day}`;
  }
  getDateIntoDDMMYYYY(data): string {
    var todayTime = new Date(data);
    var month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
    var day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
    var year = todayTime.getFullYear();
    return `${day}-${month}-${year}`;
  }

  getDateFromMatDatepicker(dateEvent: Object) {
    if (dateEvent) {
      if (dateEvent['_d']) {
        return dateEvent['_d'];
      } else {
        return dateEvent;
      }
    }
  }
  getSystemGeneratedDate(httpClient: HttpClient) {
    return httpClient.get<BaseDto<string>>(this.getSystemGeneratedDateUrl);
  }
  checkValidDatesInput(fromDate:string, toDate:string){
    if(fromDate && toDate){
      return true;
    } else if(fromDate || toDate){
      return false;
    } else {
      return true;
    }
  }
}
