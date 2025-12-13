import { Injectable } from '@angular/core';
import { CurrentMachineObj } from 'CurrentMachineDetails';


@Injectable()
export class CurrentMachineDetailsContainerService {
  currentMachines = new Array<CurrentMachineObj>()
  constructor() { }
}
