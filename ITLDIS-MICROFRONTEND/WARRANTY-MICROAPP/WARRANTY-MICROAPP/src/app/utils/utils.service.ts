import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }
  removeEmptyKey<T>(searchValue: T) {
    searchValue = { ...searchValue };
    if (!!searchValue && typeof searchValue === 'object') {
      for (const key in searchValue) {
        if (searchValue.hasOwnProperty(key)) {
          let element = searchValue[key];
          if (!element && !(typeof element === 'number' && element === 0)) {
            delete searchValue[key];
          }
        }
      }
    }
    return searchValue;
  }
  static deleteProperty(obj: object, keys: Array<string>) {
    if (keys.length <= 0) {
      return obj;
    }
    if (keys.length === 1) {
      delete obj[keys[0]];
      return obj
    }
    keys.forEach(key => {
      delete obj[key];
    });
    return obj;
  }
}
