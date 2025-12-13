import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ModelSurveyMasterUrls } from '../url-util/model-survey-master-urls';

@Injectable({
  providedIn: 'root'
})
export class ModelSurveyMasterService {

  constructor(private httpClient:HttpClient) { }

  submitModelSurveyMaster(formData): Observable<any> {
    return this.httpClient.post<any>(ModelSurveyMasterUrls.submitFormData, formData)
  }




 /* Search Page Calls */
 getModelSurveyMasterSearchTableData(searchData: object) {
  Object.keys(searchData).forEach((key, index) => {
    if (searchData[key] === null) {
      delete searchData[key]
    }
  })
  return this.httpClient.post(ModelSurveyMasterUrls.getModelSurveySearchData, searchData)
}
}
