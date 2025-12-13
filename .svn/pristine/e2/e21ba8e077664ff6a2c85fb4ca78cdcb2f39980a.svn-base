import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { Category} from '../../domain/spares-sales-invoice.domain'
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';


@Injectable()
export class LabourChargesWebService {
  static readonly dropDownServiceCategory = `${environment.baseUrl}/${environment.api}/service/jobcard/dropDownServiceCategory`
 
  constructor(private httpClient: HttpClient) { }


  serviceCategory(): Observable<Category> {
    return this.httpClient.get<BaseDto<Category>>(LabourChargesWebService.dropDownServiceCategory, {
      params: new HttpParams()
    }).pipe(map(dto => dto.result))
  }
}
