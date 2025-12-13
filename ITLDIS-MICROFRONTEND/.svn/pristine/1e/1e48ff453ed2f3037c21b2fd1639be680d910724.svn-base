import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Brand } from '../domains/enquiry';
import { BaseDto } from 'BaseDto';
import { EnquiryApi } from '../url-utils/enquiry-urls';
import { map } from 'rxjs/operators';

@Injectable()
export class CurrentMachineryDetailsV2WebService {

  constructor(
    private http : HttpClient
  ) { }

  getBrands(): Observable<BaseDto<Array<Brand>>> {
    return this.http.get<BaseDto<Array<Brand>>>(EnquiryApi.getBrands)
  }

}
