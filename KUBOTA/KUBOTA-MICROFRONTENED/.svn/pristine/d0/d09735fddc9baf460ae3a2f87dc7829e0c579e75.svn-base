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

  constructor(
    private httpClient: HttpClient
  ) { }

  getFormattedDate(data) {
    // 2019-04-24 18:19:38.4900000
    const todayTime = new Date(data);
    const month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
    const day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
    const year = todayTime.getFullYear();
    const hh = todayTime.getHours();
    const mm = todayTime.getMinutes();
    const ss = todayTime.getSeconds();
    const ms = todayTime.getMilliseconds()
    return `${year}-${month}-${day} ${hh}:${mm}:${ss}.${ms}`;
  }
  getDateIntoYYYYMMDD(data): string {
    const todayTime = new Date(data);
    const month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
    const day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
    const year = todayTime.getFullYear();
    return `${year}-${month}-${day}`;
  }
  getDateIntoDDMMYYYY(data): string {
    const todayTime = new Date(data);
    const month = (todayTime.getMonth() + 1) < 10 ? '0' + (todayTime.getMonth() + 1) : todayTime.getMonth() + 1;
    const day = todayTime.getDate() < 10 ? '0' + todayTime.getDate() : todayTime.getDate();
    const year = todayTime.getFullYear();
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

  getYearsDiffrance(date1, date2) {
    const diff = Math.floor(date1.getTime() - date2.getTime());
    const day = 1000 * 60 * 60 * 24;

    const days = Math.floor(diff / day);
    const months = Math.floor(days / 31);
    const years = Math.floor(months / 12);

    return years
  }
  getSystemGeneratedDate(httpClient?: HttpClient) {
    if (httpClient) {
      return httpClient.get<BaseDto<string>>(this.getSystemGeneratedDateUrl);
    }
    return this.httpClient.get<BaseDto<string>>(this.getSystemGeneratedDateUrl);
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
