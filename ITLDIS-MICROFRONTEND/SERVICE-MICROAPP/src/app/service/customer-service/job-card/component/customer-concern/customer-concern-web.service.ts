import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { JobCardUrl } from '../../url-util/job-card-url';
import { map } from 'rxjs/operators';
import { ServiceRepresentative } from '../../domain/job-card-domain';

@Injectable()
export class CustomerConcernWebService {

  constructor(private httpClient: HttpClient) { }

  dropDownService(): Observable<ServiceRepresentative> {
    return this.httpClient.get<BaseDto<ServiceRepresentative>>(JobCardUrl.getDropDownServiceRepresentative).pipe(map(dto => dto.result))
  }
}
