import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class EnquiryService {
  public submitOrResetData = new EventEmitter<string>()
  selectExchangeRequired = new EventEmitter<boolean>()
  estimatedExcnangPrice = new EventEmitter<number>()
  mobileNumber = new EventEmitter<string>()
  constructor() { }

  submitOrResetEnquiryForm(type: string){
      this.submitOrResetData.emit(type)
  }

  submitOrResetEnquiryFormSubscribe(callback: (value) => void){
    this.submitOrResetData.subscribe(value => {
      callback(value)
    })
  }
}
