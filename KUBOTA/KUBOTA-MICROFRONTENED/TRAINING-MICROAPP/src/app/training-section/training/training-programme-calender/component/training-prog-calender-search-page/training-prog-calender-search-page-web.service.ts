import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TrainingApi } from '../../url-utils/training-prog-calender-url';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
 
@Injectable()
export class TrainingProgrammeCalenderSearchPageWebService {
  constructor(
    private httpClient: HttpClient
  ) { }

  
}
