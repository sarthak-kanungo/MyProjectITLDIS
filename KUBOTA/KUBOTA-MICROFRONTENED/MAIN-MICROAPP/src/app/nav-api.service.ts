import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { urlService } from './webservice-config/baseurl';
import { UserFunctionality } from './core/model/user-functionality.model';
import { BaseDto } from 'BaseDto';

@Injectable({
  providedIn: 'root'
})
export class NavApiService {

  private readonly getFunctionalityMappedToUserUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.kubotauser }${ urlService.getFunctionalityMappedToUser }`;
  constructor(
    private httpClient: HttpClient
  ) { }

  getFunctionalityMappedToUser(loginUserId: any) {
    return this.httpClient.get<BaseDto<UserFunctionality[]>>(`${ this.getFunctionalityMappedToUserUrl }/${ loginUserId }`);
  }
}
