import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { SelectList, SelectListAdapter } from '../../../../../core/model/select-list.model';
import { BaseDto } from 'BaseDto';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { map } from 'rxjs/operators';

@Injectable()
export class MaterialDetailApiService {


  private readonly getGstValueUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.invoice }${ urlService.getGstValue }`;
  constructor(
    private httpClient: HttpClient,
    private selectListAdapter: SelectListAdapter
  ) { }
  getGSTPercentList(): Observable<BaseDto<SelectList[]>> {
    let keyMap = {
      id: 'id',
      value: 'value'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getGstValueUrl).pipe(
      map(res => {
        res.result = this.selectListAdapter.adapt(res.result, keyMap);
        return res;
      })
    )
  }
}
