import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class PoMachineDetailsDataHandleService {
  public itemNumberValueChange: BehaviorSubject<string> = new BehaviorSubject(null);

  constructor() { }

  emitItemNumberChange(valueChange: string) {
    if (valueChange !== null) {
      this.itemNumberValueChange.next(valueChange);
    }
  }
}
