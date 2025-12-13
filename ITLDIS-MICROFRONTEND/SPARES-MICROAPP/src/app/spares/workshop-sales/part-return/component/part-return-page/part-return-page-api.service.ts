import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { PartReturn } from '../../domain/part-return.domain';
import { BaseDto } from 'BaseDto';
import { PartReturnUrl } from '../../url-util/part-return.url';

@Injectable()
export class PartReturnPageApiService {
    constructor(private httpClient: HttpClient) { }
    saveSparePartReturn(partReturn: PartReturn) {
        return this.httpClient.post<BaseDto<object>>(PartReturnUrl.saveSparePartsReturn, partReturn);
    }
    getPartReturnById(returnId){
        return this.httpClient.get<BaseDto<object>>(PartReturnUrl.getPartReturnById+'/'+returnId);
    }
}