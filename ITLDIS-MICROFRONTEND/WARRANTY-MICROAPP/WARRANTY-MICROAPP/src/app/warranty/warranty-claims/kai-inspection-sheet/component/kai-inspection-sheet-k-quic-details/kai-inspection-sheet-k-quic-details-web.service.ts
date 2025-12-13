import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { KaiInspectionSheetApi } from '../../url-utils/kai-inspection-sheet-url';
import { Observable } from 'rxjs/Observable';
import { KaiDropDown } from '../../domain/kai-inspection-sheet.domain';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class KaiInspectionSheetKQuicDetailsWebService {

  constructor(
    private httplient: HttpClient
  ) { }

  dropDownTypeOfUse(): Observable<KaiDropDown> {
    return this.httplient.get<BaseDto<KaiDropDown>>(KaiInspectionSheetApi.dropDownTypeOfUse).pipe(map(res => res.result));
  }
  dropDownFailureUnit(): Observable<KaiDropDown> {
    return this.httplient.get<BaseDto<KaiDropDown>>(KaiInspectionSheetApi.dropDownFailureUnit).pipe(map(res => res.result));
  }
  dropDownFailureMode(): Observable<KaiDropDown> {
    return this.httplient.get<BaseDto<KaiDropDown>>(KaiInspectionSheetApi.dropDownFailureMode).pipe(map(res => res.result));
  }
}
