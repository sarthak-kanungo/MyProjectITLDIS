import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { SparePartReturnSearchFilter, SparePartReturnSearchResult } from '../../domain/part-return.domain';
import { BaseDto } from 'BaseDto';
import { PartReturnUrl } from '../../url-util/part-return.url';

@Injectable()
export class PartReturnSearchPageApiService {
    constructor(
        private httpClient: HttpClient
    ) {}
    searchPartReturn(search: SparePartReturnSearchFilter) {
        return this.httpClient.post<BaseDto<SparePartReturnSearchResult[]>>(PartReturnUrl.searchPartReturn, search);
    }
}