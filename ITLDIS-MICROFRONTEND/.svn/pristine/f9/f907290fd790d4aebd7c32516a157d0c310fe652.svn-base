import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { of } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { SparesGrnUrl } from '../../url-util/spares-grn.url';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { SpareInvoiceDetail } from '../../domain/spare-grn.domain';

@Injectable()
export class SparesGrnApiService {

  constructor(
    private httpClient: HttpClient
  ) { }
  searchInvoiceNumber(invoiceNo: string, supplierDealerId:string, supplierType:string, storeId: number) {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.getSearchSparesInvoiceNo, {
      params: new HttpParams().set('invoiceNo', invoiceNo)
                              .set('supplierDealerId', supplierDealerId)
                              .set('supplierType', supplierType)
                              .set('storeId',storeId.toString())
    }).pipe(map(res => res.result))
  }
  getInvoiceDetailByInvoiceNo(invoiceId: number, supplierDealerId:string, supplierType:string, storeId: number) {
    if (!invoiceId && invoiceId !== 0) {
      invoiceId = null;
    }
    return this.httpClient.get<BaseDto<SpareInvoiceDetail>>(SparesGrnUrl.getSparesInvoiceDetails, {
      params: new HttpParams().set('invoiceId', invoiceId.toString())
          .set('supplierDealerId', supplierDealerId)
          .set('supplierType', supplierType)
          .set('storeId',storeId.toString())
    }).pipe(map(res => {
      // res.result.accpacInvoiceItems = [{ itemNo: 'inv/12314' }] as any;
      return res.result
    }));
  }
  getGrnType() {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.getGrnType).pipe(
      map(res => res.result)
    );
  }
  getSuppilerType(grnType: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.getSupplierType, {
      params: new HttpParams().set('grnType', grnType)
    }).pipe(
      map(res => res.result)
    );
  }
  getStoresName(tranType?:string) {
    if(tranType==undefined || tranType==null)
        tranType='';
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.getStoresName,{
        params: new HttpParams()
            .set('tranType', tranType) 
    }).pipe(
      map(res => res.result)
    );
  }
  searchSupplierName(supplierType: string, supplierName: string) {
    console.log('supplierType', supplierType);  
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.searchSupplierName, {
      params: new HttpParams()
        .set('supplierType', supplierType)
        .set('supplierName', supplierName)
    }).pipe(map(res => res.result))
  }
  searchPoNumberForGrn(supplierId: string, poNumber: string, supplierType:string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(SparesGrnUrl.searchPoNumberForGrn, {
      params: new HttpParams()
        .set('supplierId', supplierId)
        .set('poNumber', poNumber)
        .set('supplierType', supplierType)
    }).pipe(map(res => res.result))
  }
  getSupplierDetailsBySupplierId(supplierId: number, supplierType:string) {
    if (!supplierId && supplierId !== 0) {
      supplierId = null;
    }
    return this.httpClient.get<BaseDto<SpareInvoiceDetail>>(SparesGrnUrl.getSupplierDetailsBySupplierId, {
      params: new HttpParams().set('supplierId', supplierId.toString())
          .set('supplierType', supplierType)
    }).pipe(map(res => {
      return res.result
    }));
  }
  getPoDetailsByPoNumberForGrn(poId: number, storeId: number) {
    if (!poId && poId !== 0) {
      poId = null;
    }
    return this.httpClient.get<BaseDto<SpareInvoiceDetail>>(SparesGrnUrl.getPoDetailsByPoNumberForGrn, {
      params: new HttpParams().set('poId', poId.toString())
      .set('storeId', storeId.toString())
    }).pipe(map(res => {
      return res.result
    }));
  }
}
