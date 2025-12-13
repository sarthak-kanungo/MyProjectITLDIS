import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';

@Injectable()
export class SearchMtrListService {

  private readonly searchMtrUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.searchMachineTransferRequest}`;

  private readonly requeststatusDropDownServUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.getMachineTransferStatus}`;

  private readonly productsDropDownServUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllProduct}`;

  private readonly seriesListDropDownServUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSeriesByProduct}`;

  private readonly modelsDropDownServUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getModelBySeries}`;

  private readonly submodelsDropDownServUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getSubModel}`;

  private readonly variantsDropDownServUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getAllVariant}`;
  
  private readonly searchByrequestNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}${urlService.searchRequestNumber}`;
  
  private readonly searchByitemNoUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}/machineMaster/autoCompleteItems`;

  private readonly getDataByItemNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.getByItemNo}`;

  constructor(private httpClient: HttpClient) { }

  searchMtr(searchData: object) {
    Object.keys(searchData).forEach((key, index) => {
      if (searchData[key] === null) {
        delete searchData[key]
      }// else if ((key === 'requestFromDate') || (key === 'requestToDate')) {
      //   searchData[key] = this.convertDateToDDMMYYYY(searchData[key]);
      // }
    })
    return this.httpClient.post(this.searchMtrUrl, searchData)
  }
  // convertDateToDDMMYYYY(simpleDate: Date) {
  //   let day = new Date(simpleDate).getDate();
  //   let month = new Date(simpleDate).getMonth() + 1;
  //   let year = new Date(simpleDate).getFullYear();
  //   let finalDate = `${day}-${month}-${year}`;
  //   return finalDate;
  // }
  requeststatusDropDownServ() {
    return this.httpClient.get(this.requeststatusDropDownServUrl)
  }
  productsDropDownServ() {
    return this.httpClient.get(this.productsDropDownServUrl)
  }


  seriesListDropDownServ(searkKey: string) {
    return this.httpClient.get(this.seriesListDropDownServUrl, {
      params: new HttpParams().set('product', searkKey)
    })
  }

  modelsDropDownServ(searkKey: string) {
    return this.httpClient.get(this.modelsDropDownServUrl, {
      params: new HttpParams().set('series', searkKey)
    })
  }


  submodelsDropDownServ(searkKey: string) {
    return this.httpClient.get(this.submodelsDropDownServUrl, {
      params: new HttpParams().set('model', searkKey)
    })
  }
  variantsDropDownServ() {
    return this.httpClient.get(this.variantsDropDownServUrl)
  }

  searchByrequestNo(searkKey: string) {
    return this.httpClient.get(this.searchByrequestNoUrl, {
      params: new HttpParams().set('requestNumber', searkKey)
    })
  }
  searchByitemNo(searkKey: string) {
    return this.httpClient.get(this.searchByitemNoUrl, {
      params: new HttpParams().set('itemNo', searkKey).set('productGroup','').set('functionality', 'MachineTransfer')
    })
  }



  getDataByItemNumber(searkKey: string) {
    return this.httpClient.get(this.getDataByItemNumberUrl, {
      params: new HttpParams().set('itemNo', searkKey)
    })
  }
}
