import { Injectable } from '@angular/core';
import { HttpClient ,HttpParams} from '@angular/common/http';
import { SparesGrnUrl } from '../../url-util/spares-grn.url';
import { SaveSpareGrn } from '../../domain/spare-grn.domain';
import { DateService } from '../../../../../root-service/date.service';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
@Injectable()
export class SpareGrnPageApiService {
    private readonly printUrl = `${environment.baseUrl}${urlService.api}${urlService.spare}${urlService.reports}`;
  constructor(
    private httpClient: HttpClient,
    private dateService: DateService
  ) { }
  saveGrn(saveGrn: SaveSpareGrn) {
    return this.httpClient.post(SparesGrnUrl.saveGrn, saveGrn);
  }
  getServerDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
  getSpareGrnByGrnId(grnId: number) {
    return this.httpClient.get<BaseDto<object>>(`${ SparesGrnUrl.getSpareGrnByGrnId }/${ grnId }`).pipe(map(res => res.result));
  }
  printGrn(grnNumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printUrl+'/getGrnPrint', {
          params: new HttpParams().set('grnNumber', grnNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
  
  printBinningSlip(grnNumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printUrl+"/printBinningSlip", {
          params: new HttpParams().set('grnNumber', grnNumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
  
}
