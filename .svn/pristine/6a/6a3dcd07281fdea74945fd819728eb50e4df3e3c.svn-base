import { Injectable } from '@angular/core';
import { UploadableFile } from 'kubota-file-upload';
import { v4 as uuid } from 'uuid';
import { Observable } from 'rxjs';
import { Category} from '../../domain/spares-sales-invoice.domain';
import { HttpParams, HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';


@Injectable()
export class OutsideJobWebService {
  static readonly dropDownServiceCategory = `${environment.baseUrl}/${environment.api}/service/jobcard/dropDownServiceCategory`
        
  constructor(private httpClient: HttpClient) { }
  
  serviceCategory(): Observable<Category> {
    return this.httpClient.get<BaseDto<Category>>(OutsideJobWebService.dropDownServiceCategory, {
      params: new HttpParams()
    }).pipe(map(dto => dto.result))
  }
}
