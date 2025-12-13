import { Injectable } from '@angular/core';
import { HttpClient,HttpParams } from '@angular/common/http';
import { PartIssue } from '../../domain/part-issue.domain';
import { BaseDto } from 'BaseDto';
import { PartIssueUrl } from '../../url-util/part-issue.url';
import { pipe } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class PartIssuePageApiService {
  private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}/printPartIssue`;
    
  constructor(private httpClient: HttpClient) { }
  saveSparePartIssue(partIssue: PartIssue) {
    return this.httpClient.post<BaseDto<object>>(PartIssueUrl.saveSparePartIssue, partIssue);
  }
  getPartIssueById(partIssueId: number) {
    return this.httpClient.get<BaseDto<object>>(`${PartIssueUrl.getPartIssueById}/${partIssueId}`)
      .pipe(map(res => {
        // res.result['partRequisitionItem'] && res.result['partRequisitionItem'].forEach(item => {
        //   item['availableStock'] = 1000;
        //   item['balancedQuantity'] = item.reqQuantity || 0
        // });
        return res.result
      }));
  }
  printPO(partIssueNo:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printUrl, {
          params: new HttpParams().set('partIssueNo', partIssueNo)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
