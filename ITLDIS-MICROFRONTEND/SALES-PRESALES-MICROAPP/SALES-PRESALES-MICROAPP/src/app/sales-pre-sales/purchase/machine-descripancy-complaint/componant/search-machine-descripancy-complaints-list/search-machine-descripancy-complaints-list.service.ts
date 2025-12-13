
import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { SearchMachineDescComplaintDomain, MachineDescListSearchDomain } from 'MachineDescripancyComplaintModule';

@Injectable()
export class SearchMachineDescripancyComplaintsListService {

  private readonly searchComplaintNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.searchComplaintNumber}`;
  private readonly getComplaintStatusUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.getComplaintStatus}`;
  private readonly searchGrnNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.searchGrnNumber}`;
  private readonly searchExistingChassisNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.searchExistingChassisNumber}`;
  private readonly searchByUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.searchBy}`;
 

  machineDescripancyComplaintListForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient

  ) { }

  createMachineDescripancyComplaintListForm() {
    this.machineDescripancyComplaintListForm = this.fb.group({
      complaintNo: ['', Validators.compose([])],
      complaintStatus: ['', Validators.compose([])],
      grnNo: ['', Validators.compose([])],
      chassisNo: ['', Validators.compose([])],
      complaintFromDate: ['', Validators.compose([])],
      complaintToDate: ['', Validators.compose([])]
    })
    return this.machineDescripancyComplaintListForm;
  }

  getComplaintNumber(complaintNumber) {
    return this.httpClient.get(`${this.searchComplaintNoUrl}`, {
      params: new HttpParams().set('complaintNumber', complaintNumber)
    })
  }

  getGrnNumber(grnNumber) {
    return this.httpClient.get(`${this.searchGrnNumberUrl}`, {
      params: new HttpParams().set('grnNumber', grnNumber)
    })
  }

  getExistingChasisNumber(chassisNumber) {
    return this.httpClient.get(`${this.searchExistingChassisNumberUrl}`, {
      params: new HttpParams().set('chassisNumber', chassisNumber)
    })
  }

  getComplaintStatus() {
    return this.httpClient.get(this.getComplaintStatusUrl)
  }

  searchUsingFilter(filter: SearchMachineDescComplaintDomain): Observable<BaseDto<Array<MachineDescListSearchDomain>>> {
    console.log("------------filter ", filter);
    return this.httpClient.post<BaseDto<Array<MachineDescListSearchDomain>>>(this.searchByUrl,filter)
  }

}
