import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PartRequisitionUrl } from '../../url-util/part-requisition.url';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { DateService } from '../../../../../root-service/date.service';
import { SelectList } from '../../../../../core/model/select-list.model';

@Injectable()
export class PartRequisitionApiService {

  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }
  getRequisitionPurpose(): Observable<SelectList[]>{
   return this.httpClient.get<BaseDto<SelectList[]>>(PartRequisitionUrl.getRequisitionPurpose).pipe(
      map(res=>res.result)
    );
  }
  getServerDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
}
