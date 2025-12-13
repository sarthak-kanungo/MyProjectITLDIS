import { Injectable, EventEmitter } from '@angular/core';
import { Subscription } from 'rxjs';

@Injectable()
export class PurchaseOrderService {
  public submitOrReset = new EventEmitter<string>();
  private submitResetSubscription: Subscription;

  constructor() { }

  submitOrResetForm(type: string) {
    this.submitOrReset.emit(type);
  }

  submitOrResetFormSubscribe(callback: (value) => void) {
    this.submitResetSubscription = this.submitOrReset.subscribe(value => {
      callback(value);
    })
  }

  destroySubscription() {
    this.submitResetSubscription.unsubscribe();
  }
}
