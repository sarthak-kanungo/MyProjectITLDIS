import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MdComplaintsService {
  complaintForm: FormGroup

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient
  ) { }
  private readonly searchItemNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.searchItemNumber}`;
  private readonly getDetailsByItemIdUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.getDetailsByItemId}`;
  private readonly getComplaintTypeUrl= `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.getComplaintType}`;

  

  getItemNumber(itemNo) {
    return this.httpClient.get(`${this.searchItemNoUrl}`, {
      params: new HttpParams().set('itemNo', itemNo)
    })
  }
  getDetailsByItemId(itemId) {
    return this.httpClient.get(`${this.getDetailsByItemIdUrl}/${itemId}`, {
    })
  }

  getComplaintType(){
    return this.httpClient.get(this.getComplaintTypeUrl)
  }
}
