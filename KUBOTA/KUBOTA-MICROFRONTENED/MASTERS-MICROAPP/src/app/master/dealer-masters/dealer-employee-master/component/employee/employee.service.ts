import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import{EmergencyContactNoAuto, DealerMasterDropdown, QualificationsDropdown, MaritalsDropdown, BloodsDropdown, GenderDropdown }from '../../domain/dealer-employee-domain';
import { DealerEmployeeApi } from '../../url-util/dealer-employee-urls';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';


@Injectable()
export class EmployeeService {

  constructor(
    private httpClient : HttpClient
  ) { }

  getHighestQualificationDropdown(): Observable<QualificationsDropdown> {
    return this.httpClient.get<BaseDto<QualificationsDropdown>> (DealerEmployeeApi.highestQualificationDropdown)
    .pipe(map(res => res.result));
  }
  getMaritalStatusDropdown(): Observable<MaritalsDropdown> {
    return this.httpClient.get<BaseDto<MaritalsDropdown>> (DealerEmployeeApi.maritalStatusDropdown)
    .pipe(map(res => res.result));
  }
  getBloodGroupDropdown(): Observable<BloodsDropdown> {
    return this.httpClient.get<BaseDto<BloodsDropdown>> (DealerEmployeeApi.bloodGroupDropdown)
    .pipe(map(res => res.result));
  }
  getSexDropdown(): Observable<GenderDropdown> {
    return this.httpClient.get<BaseDto<GenderDropdown>> (DealerEmployeeApi.genderDropdown)
    .pipe(map(res => res.result));
  }

  /* getEmergencyContactNoAuto(): Observable<EmergencyContactNoAuto> {
    return this.httpClient.get<EmergencyContactNoAuto> (DealerEmployeeApi.emergencyContactNoAuto)
  } */
}
