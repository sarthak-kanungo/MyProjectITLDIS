import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UtilsService } from "src/app/utils/utils.service";
import { PlSearch } from "../domain/pl.domain";
import { PickListApi } from "../pickList-urls";

@Injectable()
export class PlSearchPageService {
  constructor(private httpClient:HttpClient,
    private utilsService: UtilsService) { }

    searchPl(searchValue: PlSearch) {
    searchValue = this.utilsService.removeEmptyKey<PlSearch>(searchValue);
    return this.httpClient.post(PickListApi.searchPickList, searchValue)
  }
}