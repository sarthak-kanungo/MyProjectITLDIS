import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ServiceActivityClaimApi } from '../../url-util/service-activity-claim-urls';
import { SubmitServiceActivityClaim, ViewActivityClaim } from '../../domain/service-activity-claim.domain';
import { BehaviorSubject, Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class ServiceActivityClaimPageWebService {

  totalApprovedAmount:BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor(
    private httpClient: HttpClient
  ) { }

  saveServiceActivityClaim(formData: FormData){
    return this.httpClient.post(ServiceActivityClaimApi.saveServiceActivityClaim, formData)
  }

  getActivityClaimById(id: string): Observable<ViewActivityClaim> {
    return this.httpClient.get<BaseDto<ViewActivityClaim>>(`${ServiceActivityClaimApi.getActivityClaimById}/${id}`
     ).pipe(map(dto => dto.result))
  }
  
  approveClaim(approveServiceActivityClaim){
    return this.httpClient.post(ServiceActivityClaimApi.approveServiceActivityClaim, approveServiceActivityClaim)
  }
  updateServiceActivityClaim(formData:FormData){
    return this.httpClient.post(ServiceActivityClaimApi.editServiceActivityClaim, formData)
  }
}
