import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class ActivityClaimApprovalCommonWebService {
  public submitOrResetData = new EventEmitter<string>()
  constructor() { }

  approveActivityClaimForm(type: string){
    this.submitOrResetData.emit(type)
}

approveActivityClaimFormSubscribe(callback: (value) => void){
  this.submitOrResetData.subscribe(value => {
    callback(value)
  })
}
}
