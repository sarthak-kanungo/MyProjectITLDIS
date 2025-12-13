import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SubmitTransporter } from '../../domain/transporter-domain';
import { TransporterApi } from '../../url-util/transporter-urls';

@Injectable()
export class TransporterPageWebService {

  constructor(
    private httpClient: HttpClient
  ) { }

  saveEnquiryTransporter(formData: SubmitTransporter) {
    return this.httpClient.post(TransporterApi.saveEnquiryTransporter, formData)
  }
}
