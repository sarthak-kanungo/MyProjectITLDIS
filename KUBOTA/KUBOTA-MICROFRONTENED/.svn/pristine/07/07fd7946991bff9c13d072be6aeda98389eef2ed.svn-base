import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { QuestionMaster, QuestionMasterHeader, QuestionMasterList } from '../domain/question-master-domain';
import { QuestionMasterApi } from '../url-util/question-master-urls';

@Injectable({
  providedIn: 'root'
})
export class QuestionMasterService {

  constructor(private http: HttpClient) { }

  getQuestionType(){
    return this.http.get<Array<any>>(QuestionMasterApi.getQuestionType)
  }

  submitQuestionMaster(questionHeader :any) : Observable<any>{
    return this.http.post<any> (QuestionMasterApi.saveQuestionMaster,questionHeader)
  } 


  getAutoQuestionCode(questionCode:string){
    return this.http.get<Array<any>>(QuestionMasterApi.getAutoQuestionCode,{
      params: new HttpParams().set('questionCode', questionCode)
    })
  }

  public searchQuestionMaster(searchQuestion: QuestionMaster): Observable<BaseDto<Array<QuestionMasterList>>> {
    return this.http.post<BaseDto<Array<QuestionMasterList>>>(QuestionMasterApi.searchQuestionMaster, searchQuestion)
  }

  getDataforViewEdit(questionId:any){
    return this.http.get<Array<any>>(QuestionMasterApi.getViewEditData,{
      params: new HttpParams().set('questionId', questionId)
    })
  }

  updateQuestionMaster(questionHeader :any) : Observable<any>{
    return this.http.post<any> (QuestionMasterApi.updateQuestionMaster,questionHeader)
  } 

}
