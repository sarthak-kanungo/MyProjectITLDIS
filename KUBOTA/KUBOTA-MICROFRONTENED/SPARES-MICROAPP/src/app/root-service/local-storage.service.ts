import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { StorageLoginUser } from 'LoginDto';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private _isBrowser: boolean;
  private get isBrowser(): boolean {
    return this._isBrowser;
  }
  constructor(@Inject(PLATFORM_ID) platformId: Object) {
    this._isBrowser = isPlatformBrowser(platformId);
  }

  setItem(key: string, value: any) {
    if (this.isBrowser) {
      let convertedValue: string;
      typeof value === 'object' ? convertedValue = JSON.stringify(value) : null;
      typeof value === 'number' ? convertedValue = value.toString() : null;
      return localStorage.setItem(key, convertedValue);
    }
  }
  getItem<T>(key: string): T | string {
    if (this.isBrowser) {
      let item;
      try {
        item = JSON.parse(localStorage.getItem(key));
        return item
      } catch (error) {
        return localStorage.getItem(key);
      }
    }
  }
  removeItem(key: string) {
    if (this.isBrowser) {
      return localStorage.removeItem(key);
    }
  }
  clear() {
    if (this.isBrowser) {
      return localStorage.clear();
    }
  }
  getLoginUser():StorageLoginUser  {
    return this.getItem<StorageLoginUser>('kubotaUser') as StorageLoginUser;
  }
}
