import { Injectable } from '@angular/core';
import { DateService } from '../../../../../root-service/date.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SapApi } from '../../url-util/sap-urls';
import { MaxAllowedBudget } from '../../domain/sap.domain';

@Injectable()
export class ActivityTrainingProposalDetailsWebService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }

  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient)
  }

  getMaxAllowedBudgetByNumberPerson(numberOfPerson: number) {
    return this.httpClient.get(`${SapApi.getMaxAllowedBudgetByNumberPerson}`, {
      params: new HttpParams()
        .append('numberOfPerson', numberOfPerson.toString())
    })
  }
  calculationForActivityType(calculateActivityType: MaxAllowedBudget) {
    return this.httpClient.post(`${SapApi.calculationForActivityType}`, calculateActivityType)
  }
}
