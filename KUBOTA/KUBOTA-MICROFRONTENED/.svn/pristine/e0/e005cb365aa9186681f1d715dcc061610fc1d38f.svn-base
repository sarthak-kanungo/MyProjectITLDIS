import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { activityTypeDropDown, budgetTypeDropDown } from 'ActivityBudget';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { BaseDto } from 'BaseDto';
import { HeadDropDown } from './saveBudget.Dto';

@Injectable({
  providedIn: 'root'
})
export class AddNewBudgetService {

  private readonly getActivityTypeDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.getActivityTypes}`;
  private readonly getHeadDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}/getMarketingHeadsDropDown`;
  //api/salesAndPreSales/activityTypeBudgetMaster/getMarketingHeadsDropDown
  private readonly getbudgetTypeDataURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.budgetTypeDropdown}`;
  private readonly saveDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.saveactivitytypebudgetmaster}`;

  constructor(private httpClient: HttpClient) { }

  getActivityTypeData(): Observable<BaseDto<Array<activityTypeDropDown>>> {
    return this.httpClient.get<BaseDto<Array<activityTypeDropDown>>>(this.getActivityTypeDataUrl)
  }
  getHeadData(): Observable<BaseDto<Array<HeadDropDown>>> {
    return this.httpClient.get<BaseDto<Array<HeadDropDown>>>(this.getHeadDataUrl)
  }
  getbudgetTypeData(): Observable<BaseDto<Array<budgetTypeDropDown>>> {
    return this.httpClient.get<BaseDto<Array<budgetTypeDropDown>>>(this.getbudgetTypeDataURL)

  }


  saveData(data) {
    return this.httpClient.post(this.saveDataUrl, data)
  }







  keyPressMaxLimit(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressMaxDay(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keyPressShare(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
}
