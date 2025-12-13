import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { SparePartIssueSearchFilter, SparePartIssueSearchResult } from '../../domain/part-issue.domain';
import { BaseDto } from 'BaseDto';
import { PartIssueUrl } from '../../url-util/part-issue.url';

@Injectable()
export class PartIssueSearchPageApiService {
    constructor(private httpClient: HttpClient) { }
    searchPartIssue(search: SparePartIssueSearchFilter) {
        return this.httpClient.post<BaseDto<SparePartIssueSearchResult[]>>(PartIssueUrl.searchPartIssue, search);
    }
}