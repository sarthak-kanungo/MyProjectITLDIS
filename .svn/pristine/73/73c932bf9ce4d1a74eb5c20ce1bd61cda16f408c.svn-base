import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class EnquiryFollowupService {
  public submitOrResetFollwupData = new EventEmitter<string>()

  constructor() { }

  submitOrResetEnquiryForm(type: string){
      this.submitOrResetFollwupData.emit(type)
  }

  submitOrResetEnquiryFollowupSubscribe(callback: (value) => void){
    this.submitOrResetFollwupData.subscribe(value => {
      callback(value)
    })
  }
}
