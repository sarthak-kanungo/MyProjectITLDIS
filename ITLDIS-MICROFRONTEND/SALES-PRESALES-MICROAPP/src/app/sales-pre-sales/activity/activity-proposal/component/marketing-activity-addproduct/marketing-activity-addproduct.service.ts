import { Injectable, EventEmitter } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { environment } from '../../../../../../environments/environment';
import { Observable } from 'rxjs';
import { Head } from 'ActivityProposalModule';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MarketingActivityAddproductService {

  private readonly getHeadByActivityType = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getHeadByActivityType`
  public submitOrResetItemDetailsData = new EventEmitter<string>()
  constructor( private fb: FormBuilder,
    private httpClient : HttpClient) { }

  submitOrResetItemDetailsForm(type: string) {
    this.submitOrResetItemDetailsData.emit(type)
  }

  createAddProductForms(){
    return this.fb.group({
      headName: [''],
      amount: ['']
    })
  }

  getAllHeadsByActivityTypeId(activityTypeId: number): Observable<Head> {
    return this.httpClient.get<BaseDto<Head>>(`${this.getHeadByActivityType}`, {
      params: new HttpParams()
        .append('activityTypeId', activityTypeId.toString())
    }).pipe(map(dto => dto.result))
  }
}
