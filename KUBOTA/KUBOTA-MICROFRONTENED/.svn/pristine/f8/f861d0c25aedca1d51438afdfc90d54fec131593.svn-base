import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { EnquiryApi } from '../url-utils/enquiry-urls';
import { Occupation, SoilType, MajorCropGrown } from '../domains/enquiry';
import { map } from 'rxjs/operators';

@Injectable()
export class ProspectBackgroundV2WebService {

  constructor(
    private http : HttpClient
  ) { }

  getOccupation(): Observable<BaseDto<Array<Occupation>>> {
    return this.http.get<BaseDto<Array<Occupation>>>(EnquiryApi.getOccupation)
  }

  getSoilType(): Observable<BaseDto<Array<SoilType>>> {
    return this.http.get<BaseDto<Array<SoilType>>>(EnquiryApi.getSoilType)
  }

  getMajorCropGrown(): Observable<BaseDto<Array<MajorCropGrown>>> {
    return this.http.get<BaseDto<Array<MajorCropGrown>>>(EnquiryApi.getMajorCropGrown)
  }
}
