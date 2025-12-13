import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs/Observable';
import { ValidateMobileNumber, CustomValidators, ValidateMaxLength } from 'src/app/utils/custom-validators';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';
import { FieldLevel, LevelHierarchy } from '../domain/create-assign-org-hierarchy-to-dealer-domain';

@Injectable({
  providedIn: 'root'
})
export class AssignOrgHierarchyToDealerPopUpService {
  private assignOrgHierarchyToDealerFrom: FormGroup;
  private readonly getLevelDropUrl= `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.kaicommonmaster}${urlService.assignOrgHierarchyToDealer}${urlService.getLevelByDepartment}`
  private readonly getHierDropUrl= `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.kaicommonmaster}${urlService.assignOrgHierarchyToDealer}${urlService.getHierarchyByLevel}`

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient,
        public dialog: MatDialog,
  ) {}
  createAssignOrgHierarchyToDealerFrom() {
    this.assignOrgHierarchyToDealerFrom = this.fb.group({
      saleremarks: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
      sellingprice: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
      buyerContactNo: [{ value: null, disabled: false }, Validators.compose([Validators.required, Validators.pattern('[1-9]\\d{9}')])],
      buyerName: [{ value: null, disabled: false}, Validators.compose([Validators.required, Validators.pattern('^[a-zA-Z \-\']+')])],
    });
    return this.assignOrgHierarchyToDealerFrom;
  }

  getLevelByDepartment(departmentCode:any): Observable<BaseDto<Array<FieldLevel>>> {
    return this.httpClient.get< BaseDto<Array<FieldLevel>>>(this.getLevelDropUrl, {
      params: new HttpParams().set('departmentCode', departmentCode)
    })
  }
  getHierarchyByLevel(levelId:any,orgHierId): Observable<BaseDto<Array<LevelHierarchy>>> {
    return this.httpClient.get< BaseDto<Array<LevelHierarchy>>>(this.getHierDropUrl, {
      params: new HttpParams().set('levelId', levelId).set('orgHierarchyId',orgHierId),
      
    })
  }
}
