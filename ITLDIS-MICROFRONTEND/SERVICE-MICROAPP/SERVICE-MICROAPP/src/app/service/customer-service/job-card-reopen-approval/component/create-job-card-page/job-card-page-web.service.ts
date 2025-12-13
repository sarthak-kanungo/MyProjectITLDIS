import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { JobCardUrl } from '../../url-util/job-card-url';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ViewJobCard, FinalSaveJobCard, ServiceJobCard, DropDownCategory } from '../../domain/job-card-domain';
import { UpdateJobCard } from '../../domain/job-card-update-domain';

@Injectable()
export class JobCardPageWebService {

  constructor(private httpClient: HttpClient) { }
 
  jobCardReopenApproval(id:string,remark:string,approvalStatus:string){
      return this.httpClient.post(JobCardUrl.approveJobcardReopen, {'id':id,'remark':remark,'approvalStatus':approvalStatus});  
  }
  
  viewJobCard(jobCardNo: string): Observable<ViewJobCard> {
    return this.httpClient.get<BaseDto<ViewJobCard>>(JobCardUrl.viewJobCard, {
      params: new HttpParams().set('jobCardNo', jobCardNo)
    }).pipe(map(dto => dto.result))
  }
  getJobCardApprovalDetails(id:string){
      return this.httpClient.get(JobCardUrl.getJobCardApprovalDetails,{
          params : new HttpParams().set('id',id)
      })
  }
  serviceCategory(preSalesFlag: string): Observable<DropDownCategory> {
    return this.httpClient.get<BaseDto<DropDownCategory>>(JobCardUrl.getDropDownCategory, {
      params: new HttpParams().set('preSalesFlag', preSalesFlag)
    }).pipe(map(dto => dto.result))
  }
}
