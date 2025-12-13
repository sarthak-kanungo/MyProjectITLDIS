import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JobCardUrl } from '../../url-util/job-card-url';
import { BaseDto } from 'BaseDto';
import { MechanicName } from '../../domain/job-card-domain';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable()
export class JobServiceCardWebService {

  constructor(private httpClient: HttpClient) { }

  dropDownMechanicName(): Observable<MechanicName> {
    return this.httpClient.get<BaseDto<MechanicName>>(JobCardUrl.getDropDownMechanicName).pipe(map(dto => dto.result))
  }
}
