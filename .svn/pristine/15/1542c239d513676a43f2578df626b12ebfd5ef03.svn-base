import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { searchViewDetails } from '../component/search-view-trasit-detail-page/search-model';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { viewDetails } from '../url-utils/view-transit-url-utils';

@Injectable({
  providedIn: 'root'
})
export class ViewTransitService {

  constructor(private http:HttpClient) { 
    
  }
  searchViewDetails(viewData: searchViewDetails): Observable<BaseDto<Array<searchViewDetails>>> {
    return this.http.post<BaseDto<Array<searchViewDetails>>>(viewDetails.getTransitReport, viewData)
  }
}
