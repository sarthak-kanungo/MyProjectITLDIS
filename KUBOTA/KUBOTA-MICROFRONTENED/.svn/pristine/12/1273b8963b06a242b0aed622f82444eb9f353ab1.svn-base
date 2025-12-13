import { Injectable } from '@angular/core';
import { ProductIntrestedObj } from 'EnquiryProductIntrested';

@Injectable()
export class CurrentMachineryDetailsService {
  productIntrestedObj = {} as ProductIntrestedObj

  constructor() { }

  keyyear(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();

    }
  }   


  
}
