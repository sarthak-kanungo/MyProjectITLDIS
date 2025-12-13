import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map, mapTo, every } from 'rxjs/operators';
// import { ItemNoDomain } from 'SearchQuotationsModule';

@Injectable()
export class AddImplementsContainerService {

  private readonly getItemNoUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.machineMaster }${ urlService.autoCompleteItems }`;
  // machineMaster/autoCompleteItems

  private readonly getMachineDetailByItemNoUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.Quotation }${ urlService.getMachineDetailByItemNo }`;
  // /api/Quotation/getMachineDetailByItemNo

  constructor(
    private http: HttpClient
  ) { }

  searchItemNo(itemNo: string, productGroup:string, functionality:string,machineType?:string) {
    return this.http.get(`${ this.getItemNoUrl }`, {
      params: new HttpParams()
        .set('itemNo', itemNo)
        .set('productGroup', productGroup)
        .set('functionality', functionality)
        .set('machineType',machineType)
    });
  }

  searchgetMachineDetailByItemNo(getMachineDetailByItemNo: string) {
    return this.http.get(`${ this.getMachineDetailByItemNoUrl }`, {
      params: new HttpParams().set('itemNo', getMachineDetailByItemNo)
    })
  }
}
