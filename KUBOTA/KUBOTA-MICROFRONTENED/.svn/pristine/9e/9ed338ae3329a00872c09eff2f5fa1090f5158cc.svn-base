import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SearchMrc } from '../../domain/machine-receipt-checking.domain';
import { UtilsService } from '../../../../../utils/utils.service';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';

@Injectable()
export class MrcSearchPageService {

  constructor(private httpClient:HttpClient,
    private utilsService: UtilsService) { }

    searchMrc(searchValue: SearchMrc) {
    searchValue = this.utilsService.removeEmptyKey<SearchMrc>(searchValue);
    return this.httpClient.post(MrcUrl.searchMrc, searchValue)
  }
}
