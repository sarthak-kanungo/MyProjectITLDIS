import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PartRequisition, PartRequisitionById } from '../../domain/part-requisition.domain';
import { PartRequisitionUrl } from '../../url-util/part-requisition.url';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class PartRequisitionPageApiService {

  constructor(private httpClient: HttpClient) { }
  savePartRequisition(partRequisition:PartRequisition){
    return this.httpClient.post<BaseDto<object>>(PartRequisitionUrl.savePartRequisition, partRequisition);
  }
  getPartRequisitionById(partRequisitionById:number){
    return this.httpClient.get<BaseDto<PartRequisitionById>>(`${PartRequisitionUrl.getPartRequisitionById}/${partRequisitionById}`).pipe(
      map(res=> res.result)
    )
  }

}
