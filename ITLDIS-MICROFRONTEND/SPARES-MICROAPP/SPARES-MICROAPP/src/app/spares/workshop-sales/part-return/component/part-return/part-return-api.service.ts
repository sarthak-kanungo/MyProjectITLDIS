import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from '@angular/common/http';
import { PartReturnUrl } from '../../url-util/part-return.url';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';
import { map } from 'rxjs/operators';
import { PartIssueDetailsForPartReturnByRequisitionId } from '../../domain/part-return.domain';

@Injectable()
export class PartReturnApiService {
    constructor(private httpClient: HttpClient) { }
    getReasonsForReturn() {
        return this.httpClient.get<BaseDto<SelectList[]>>(PartReturnUrl.getReasonsForReturn)
            .pipe(map(res => res.result));
    }
    searchByRequisitionNoMobileNoEmpName(searchString: string) {
        return this.httpClient.get<BaseDto<SelectList[]>>(PartReturnUrl.searchByRequisitionNoMobileNoEmpName, {
            params: new HttpParams().set('searchString', searchString)
        })
            .pipe(map(res => res.result));
    }
    getPartIssueDetailsForPartReturnByRequisitionId(requisitionId: string) {
        return this.httpClient.get<BaseDto<PartIssueDetailsForPartReturnByRequisitionId>>(PartReturnUrl.getPartIssueDetailsForPartReturnByRequisitionId, {
            params: new HttpParams().set('requisitionId', requisitionId)
        })
            .pipe(map(res => res.result));
    }
    searchByJobCardNoForPartReturn(jobCardNo:string){
        return this.httpClient.get<BaseDto<SelectList[]>>(PartReturnUrl.searchByJobCardNoForPartReturn, {
            params: new HttpParams().set('jobCardNo', jobCardNo)
        })
            .pipe(map(res => res.result));
    }
}