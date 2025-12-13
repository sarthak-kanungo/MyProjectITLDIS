import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { SaveDesignationLevel } from 'Designation Level';

@Injectable({
  providedIn: 'root'
})
export class AddDesignationLevelService {

  constructor(private httpClient: HttpClient) { }

  private readonly submitDesignationLevelUrl = `${environment.baseUrl}${urlService.api}${urlService.designationlevel}`
  // POST /api/designation
 
  submitDesignationLevel(formData:SaveDesignationLevel) {
    return this.httpClient.post(this.submitDesignationLevelUrl, formData)
  }

}
