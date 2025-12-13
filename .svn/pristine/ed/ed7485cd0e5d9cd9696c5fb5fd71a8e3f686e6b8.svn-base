import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { SavePurchaseOrder, CancelPo, Depo } from './purchase-order-create';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class PurchaseOrderCreateService {
  private readonly submitPoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.savePurchaseOrder}`;
  private readonly getPoDetailsUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getpurchaseorder}`;
  private readonly cancelOrApprovePOUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.approvePurchaseOrder}`;
  private readonly printPOUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.reports}${urlService.printpourl}`;
  
  public getTotalMachineDetailsQuantity$: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public getPoTotalAmountForExposure$: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  // Add By Ankit rai for sendiong depot payload
  depoChangeSubject : BehaviorSubject<Depo> = new BehaviorSubject<Depo>({});
  // depoChangeSubject : BehaviorSubject<string> = new BehaviorSubject<string>(''); 
  itemSelectionSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false); 
  fileDeleteSubject : BehaviorSubject<string> = new BehaviorSubject<string>('');
  fileSelectionSubject : BehaviorSubject<object> = new BehaviorSubject<object>({});
  managementApprovalCheckSubject : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  constructor(
    private httpClient: HttpClient
  ) { }

  submitPo(poData: SavePurchaseOrder) {
   
    let formData: FormData = new FormData();
    formData.append('purchaseOrder', new Blob([JSON.stringify(poData)], { type: 'application/json' }))
    formData.append('chequeLeaf', poData.chequeLeafImage);
    formData.append('coveringLetter', poData.coveringLetterImage);
    formData.append('creditApplication', poData.creditApplicationImage);
    formData.append('chequeCopy', poData.chequeCopyImage); //added by mahesh.kumar
    
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');
    return this.httpClient.post(this.submitPoUrl, formData)
  }
  getPoDetailsById(poId: string) {
    return this.httpClient.get(`${this.getPoDetailsUrl}/${poId}`);
  }
  cancelOrApprovePO(cancelPoData: CancelPo) {
    return this.httpClient.post(this.cancelOrApprovePOUrl, cancelPoData)
  }
  printPO(ponumber:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printPOUrl, {
          params: new HttpParams().set('ponumber', ponumber)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
