import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { CreateNewDealerUser } from 'src/app/master/dealer-masters/dealer-user/url-util/create-new-dealer-user';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { DropDoenDepartments } from '../../../kubota-empolyee-master/component/empolyee-details/employee-details';
import { SubmitAssignOrgHierarchyToDealerDto } from '../../domain/create-assign-org-hierarchy-to-dealer-domain';

@Injectable({
  providedIn: 'root'
})
export class CreateNewAssignOrgHierarchyToDealerService {

  private readonly getDeprtmentDropUrl= `${environment.baseUrl}${urlService.api}${urlService.department}${urlService.getDepartment}`
  private readonly addAssignOrgHierarchyToDealer= `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.kaicommonmaster}${urlService.assignOrgHierarchyToDealer}${urlService.addAssignOrgHierarchyToDealer}`
  private readonly searchByDealerIdAssignOrgHier= `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.kaicommonmaster}${urlService.assignOrgHierarchyToDealer}${urlService.searchAllAssignOrgHierarchyToDealer}`

  constructor(private httpClient : HttpClient) { }
    
  dealerAuto(dealer: string): Observable<any> {
    return this.httpClient.get<BaseDto<any>>(CreateNewDealerUser.getDealer, {
      params: new HttpParams().append('dealer', dealer)
    }).pipe(map(res=>res.result))
  }
  // GET /api/department/departmentNameDropdown
  getDeprtmentDrop(applicableTo:string): Observable<BaseDto<Array<DropDoenDepartments>>> {
    return this.httpClient.get< BaseDto<Array<DropDoenDepartments>>>(this.getDeprtmentDropUrl, {
      params: new HttpParams().set('applicableTo', applicableTo)
    })
  }

  submitAssignOrgHierarchyToDealer(formData : SubmitAssignOrgHierarchyToDealerDto[]) : Observable<SubmitAssignOrgHierarchyToDealerDto[]>{
    console.log(formData)
    console.log("form data........")
    return this.httpClient.post<SubmitAssignOrgHierarchyToDealerDto[]> (this.addAssignOrgHierarchyToDealer,formData)
  }
  searchByDealerIdAssignOrgHierarchyToDealer(formData : any) : Observable<any[]>{
    return this.httpClient.post<any[]>(this.searchByDealerIdAssignOrgHier, formData);
  }
}
