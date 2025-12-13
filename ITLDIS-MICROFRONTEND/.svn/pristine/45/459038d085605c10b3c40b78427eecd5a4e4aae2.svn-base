import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { FilterSearchMachineDescripancyClaim, SearchMachineDescripancyClaimListDomain } from 'SearchMachineDescripancyClaim';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';

@Injectable()
export class SearchMachineDescripancyCiaimService {
  machineDescripancyClaimListForm: FormGroup;
  private readonly searchClaimNoURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyClaim}${urlService.searchClaimNumber}`
  private readonly getClaimStatusURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyClaim}${urlService.getClaimStatus}`
  private readonly getdescripancyClaimListURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyClaim}${urlService.searchBy}`

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) { }

  searchMachineDescripancyClaimListForm() {
    this.machineDescripancyClaimListForm = this.fb.group({
      claimNumber: [null],
      claimStatus: [null],
      claimFromDate: [null],
      claimToDate: [null]
    })
    return this.machineDescripancyClaimListForm;
  }

  searchClaimNo(claimNumber: string) {
    return this.http.get(this.searchClaimNoURL, {
      params: new HttpParams().set('claimNumber', claimNumber)
    })
  }

  getClaimStatus() {
    return this.http.get(this.getClaimStatusURL)
  }

  getDescripancyClaimList(filter: FilterSearchMachineDescripancyClaim): Observable<BaseDto<Array<SearchMachineDescripancyClaimListDomain>>> {
    return this.http.post<BaseDto<Array<SearchMachineDescripancyClaimListDomain>>>(
      this.getdescripancyClaimListURL, filter)
  }
}
