import { Injectable } from '@angular/core';

@Injectable()
export class DateFormat {

  fromDatePersistent:any;
  toDatePersistent:any;
  flagPersistent:boolean;
  convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear()
      return formattedDate
    }
    return null
  }

  convertToPatchFormat(dt: string) {
    if (dt) {
      return dt.split('-').reverse().join('-')
    }
    return null
  }
}