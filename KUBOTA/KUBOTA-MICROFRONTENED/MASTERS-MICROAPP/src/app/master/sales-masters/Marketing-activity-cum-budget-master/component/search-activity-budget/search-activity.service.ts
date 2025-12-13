import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { activityTypeDropDown, budgetTypeDropDown } from 'ActivityBudget';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';


@Injectable({
  providedIn: 'root'
})
export class SearchActivityService {
  private readonly getActivityTypeDataUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.getActivityTypes}`;

  private readonly getbudgetTypeDataURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.budgetTypeDropdown}`;


  private readonly searchBymaximumLimitUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.maximumLimitAuto}`;


  private readonly searchBymaximumDayMonthUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.maximumDayMonthAuto}`;


  private readonly searchBykaiShareUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.kaiShareAuto}`;

  private readonly searcActivityBudgetMAsterUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.searchActivityBudgetMaster}`;

  // http://192.168.15.186:8383/api/salesandpresales/activitytypebudgetmaster/changeActiveStatus?budgetId=1
  private readonly changeActiveStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.activitytypebudgetmaster}${urlService.changeActiveStatus}`;
  constructor(private httpClient: HttpClient) { }


  searcActivityBudgetMAster(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      console.log("key ", searchData[key]);
      if (searchData[key] === null) {
        delete searchData[key]
      } else if ((key === 'fromDate') || (key === 'toDate')) {
        searchData[key] = this.convertDateToDDMMYYYY(searchData[key]);
      }
    })
    return this.httpClient.post(this.searcActivityBudgetMAsterUrl, searchData)
  }
  convertDateToDDMMYYYY(simpleDate: Date) {
    let day = new Date(simpleDate).getDate();
    let month = new Date(simpleDate).getMonth() + 1;
    let year = new Date(simpleDate).getFullYear();
    let finalDate = `${day}-${month}-${year}`;
    return finalDate;
  }


  getActivityTypeData(): Observable<BaseDto<Array<activityTypeDropDown>>> {
    return this.httpClient.get<BaseDto<Array<activityTypeDropDown>>>(this.getActivityTypeDataUrl)
  }
  getbudgetTypeData(): Observable<BaseDto<Array<budgetTypeDropDown>>> {
    return this.httpClient.get<BaseDto<Array<budgetTypeDropDown>>>(this.getbudgetTypeDataURL)

  }

  searchBymaximumLimit(searkKey: string) {
    return this.httpClient.get(this.searchBymaximumLimitUrl, {
      params: new HttpParams().set('maximumLimit', searkKey)
    })
  }
  searchBymaximumDayMonth(searkKey: string) {
    return this.httpClient.get(this.searchBymaximumDayMonthUrl, {
      params: new HttpParams().set('maximumDayMonth', searkKey)
    })
  }
  searchBykaiShare(searkKey: string) {
    return this.httpClient.get(this.searchBykaiShareUrl, {
      params: new HttpParams().set('kaiShare', searkKey)
    })
  }


  changeActiveStatus(budgetId) {
    return this.httpClient.put(this.changeActiveStatusUrl, null,{
      params: new HttpParams().set('budgetId', budgetId)
    });
  }
}
