import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class ActualReportCreatePageService {

  btnClick = new EventEmitter<number>()

  constructor() { }

}
