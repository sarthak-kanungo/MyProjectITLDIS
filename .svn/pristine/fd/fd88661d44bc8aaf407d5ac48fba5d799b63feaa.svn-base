import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { SaveDesignation } from '../search-designation/search-designation';

@Injectable({
  providedIn: 'root'
})
export class AddDesignationService {

  private readonly submitDesignationUrl = `${environment.baseUrl}${urlService.api}${urlService.designation}`
  private readonly tocheckKaiDesignation = `${environment.baseUrl}${urlService.api}${urlService.designation}${urlService.tocheckKaiDesignation}`
  private readonly tocheckKaiDesignationCode = `${environment.baseUrl}${urlService.api}${urlService.designation}${urlService.tocheckKaiDesignationCode}`
  // POST /api/designation
  constructor(private httpClient: HttpClient) { }
  submitDesignation(formData:SaveDesignation) {
    return this.httpClient.post(this.submitDesignationUrl, formData)
  }

  checkKaiDesignation(designation:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(this.tocheckKaiDesignation,{
        params: new HttpParams().append('designation',designation)
      }).pipe(map(res=>res.result))
  }

  checkKaiDesignationCode(designationCode:string):Observable<any>{
    return this.httpClient.get<BaseDto<any>>(this.tocheckKaiDesignationCode,{
        params: new HttpParams().append('designationCode',designationCode)
      }).pipe(map(res=>res.result))
  }
}
