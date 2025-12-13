import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class DeliveryChallanCancelService {
  private readonly getCancellationTypeUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcCancellationType}`;
  private readonly getCancellationReasonUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcCancellationReason}`;
  private readonly getAllBrandUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getBrands}`;
  private readonly getAllModelUrl = `${environment.baseUrl}${urlService.api}${urlService}`;
  private readonly getAllReasonsUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcCancellationOtherReason}`;

  dcCancellationForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient
  ) { }

  createdcCancellationForm() {
    return this.fb.group({
      isCancelTick: [false],
      dcCancellationType: [null, Validators.compose([Validators.required])],
      dcCancellationReason: [null, Validators.compose([Validators.required])],
      brand: [null, Validators.compose([])],
      model: [null, Validators.compose([])],
      otherReason: [null, Validators.compose([Validators.required])],
      dcCancelRemark: [null, Validators.compose([Validators.required])],
    })
  }

  getCancellationType() {
    return this.httpClient.get(this.getCancellationTypeUrl);
  }
  getCancellationReason() {
    return this.httpClient.get(this.getCancellationReasonUrl);
  }
  getAllBrand() {
    return this.httpClient.get(this.getAllBrandUrl);
  }
  getAllModel() {
    return this.httpClient.get(this.getAllModelUrl);
  }
  getAllReasons() {
    return this.httpClient.get(this.getAllReasonsUrl);
  }
}
