import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SourceApi } from '../../url-util/source-urls';
import { SubmitSource } from '../../domain/source-domain';

@Injectable()
export class SourcePageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  saveEnquirySource(formData: SubmitSource) {
    return this.httpClient.post(SourceApi.saveEnquirySource, formData)
  }

}
