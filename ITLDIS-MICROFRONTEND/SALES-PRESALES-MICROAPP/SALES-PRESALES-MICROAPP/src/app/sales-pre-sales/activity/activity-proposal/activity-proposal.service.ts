import { Injectable, EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSelectChange } from '@angular/material';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { environment } from '../../../../environments/environment';
import { urlService } from '../../../webservice-config/baseurl';

@Injectable()
export class ActivityProposalService {
  public submitOrResetData = new EventEmitter<string>()
  public activityProposeEvent = new EventEmitter<MatSelectChange>()
  public activityCategoryEvent = new EventEmitter<MatSelectChange>()
  activityProposalStatus: boolean
  itemDetailsStatus: boolean

  private readonly calculateClaimableAmountUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.marketingActivityProposal}/getClaimableAmount`
  private readonly activityProposalPrintUrl =  `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.reports}/getActivityProposalPrint`   
  constructor(private fb: FormBuilder,
          private http:HttpClient) { }

  //submit
  submitOrResetActivityForm(type: string) {
    this.submitOrResetData.emit(type)
  }
  submitOrResetActivityFormSubscribe(callback: (value) => void) {
    this.submitOrResetData.subscribe(value => {
      callback(value)
    })
  }
  
  recalculateClaimableAmount(activityTypeId:number, maxLimit:number, budget:number){
      return this.http.get<BaseDto<number>>(this.calculateClaimableAmountUrl,{
          params: new HttpParams().set('activityTypeId', activityTypeId+"")
              .set('maxLimit', maxLimit+"")
              .set('budget', budget+"")
      });
  }
  
  printActivityProposal(activityNumber: string, printStatus:string) {
      return this.http.get<Blob>(this.activityProposalPrintUrl, {
          params: new HttpParams().set('activityNumber', activityNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
