import { RoleMasterApi } from './../url-utils/role-master-urls';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { HttpClient, HttpParams } from '@angular/common/http';
import { MenuNode, Role, SearchRoleMaster, RoleValues } from '../domains/role-master';

@Injectable()
export class RoleMasterWebService {

    constructor(
        private http: HttpClient
    ) { }

    getAssignedFunctionalityToRole(applicableTo: string, roleName: string): Observable<BaseDto<Array<MenuNode>>> {
        console.log(RoleMasterApi.getAssignedFunctionalityToRole)
        return this.http.get<BaseDto<Array<MenuNode>>>(RoleMasterApi.getAssignedFunctionalityToRole, {
            params: new HttpParams()
                .append('applicableTo', applicableTo)
                .append('roleName', roleName)
        })
    }

    role(role: Role): Observable<BaseDto<RoleValues>> {
        return this.http.post<BaseDto<RoleValues>>(RoleMasterApi.role, role)
    }
    getRoleCode(roleCode: string, type?:string) {
        if(type==undefined||type==null)type='';
        return this.http.get(RoleMasterApi.role,
            { params: new HttpParams().set('roleCode', roleCode)
            .set('type', type) })
    }
    getMainModulesFunctionalityForRole(applicableTo: string) {
        return this.http.get(RoleMasterApi.getMainModulesFunctionalityForRole, {
            params: new HttpParams()
                .append('applicableTo', applicableTo)
        })
    }

    searchRoleMaster(searchDetail: SearchRoleMaster): Observable<BaseDto<Array<SearchRoleMaster>>> {
        console.log("searchDetail.. ")
        console.log(searchDetail)
        return this.http.post<BaseDto<Array<SearchRoleMaster>>>(RoleMasterApi.searchRole, searchDetail)
    }
    submitRoleMaster(formData: Role): Observable<Role> {
        return this.http.post<Role>(RoleMasterApi.submitRole, formData)
    }
    searchRoleMasterById(id : any) : Observable<any>{
        return this.http.get(`${RoleMasterApi.searchRole}/${id}`)
    }
    updateRoleMaster(formData: Role): Observable<Role> {
        return this.http.post<Role>(RoleMasterApi.updateRoleMaster, formData)
    }
}