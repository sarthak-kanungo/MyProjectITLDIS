import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GRNSearchFilter } from './search-machine-grn-dto';
import { DateService } from '../../../../../root-service/date.service';
import { UtilsService } from '../../../../../utils/utils.service';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';

@Injectable()
export class SearchMachineGrnService {

  private searchGrnUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.searchGrn }`;
  private readonly searchByDmsGrnNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.searchByDmsGrnNumber }`;
  private readonly searchGrnInvoiceNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.searchGrnInvoiceNumber }`;
  private readonly getGrnStatusUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.getGrnStatus }`;
  private readonly getGrnTypeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.getGrnType }`;
  
  private searchMachineGrnForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient,
    private dateService: DateService,
    private utilsService: UtilsService
  ) { }

  createSearchMachineGrnForm() {
    this.searchMachineGrnForm = this.fb.group({
      dmsGrnNumber: [null, Validators.compose([])],
      grnType: [null, Validators.compose([])],
      grnStatus: [null, Validators.compose([])],
      invoiceNumber: [null, Validators.compose([])],
      fromDate: [{ value: null, disabled: false }, Validators.compose([])],
      toDate: [{ value: null, disabled: false }, Validators.compose([])],
      itemNo : [null],
      supplierType: [null],
      page:[null],
      size:[null]
    })
    return this.searchMachineGrnForm;
  }
  searchGrn(searchValue: GRNSearchFilter) {
    searchValue = this.utilsService.removeEmptyKey<GRNSearchFilter>(searchValue);
    if (searchValue.fromDate) {
      searchValue.fromDate = this.dateService.getDateIntoYYYYMMDD(searchValue.fromDate);
    }
    if (searchValue.toDate) {
      searchValue.toDate = this.dateService.getDateIntoYYYYMMDD(searchValue.toDate);
    }
    return this.httpClient.post(this.searchGrnUrl, searchValue)
  }
  searchByDmsGrnNumber(grnNumber: string, userId: number) {
    return this.httpClient.get(this.searchByDmsGrnNumberUrl, {
      params: new HttpParams().set('grnNumber', grnNumber).set('userId', userId.toString())
    })
  }
  searchGrnInvoiceNumber(invoiceNumber: string, userId: number) {
    return this.httpClient.get(this.searchGrnInvoiceNumberUrl, {
      params: new HttpParams().set('invoiceNumber', invoiceNumber).set('userId', userId.toString())
    });
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
  getGrnStatus(userId) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getGrnStatusUrl, {
      params: new HttpParams().set('userId', userId.toString())
    });
  }
  getGrnType(userId) {
      return this.httpClient.get<BaseDto<SelectList[]>>(this.getGrnTypeUrl, {
        params: new HttpParams().set('userId', userId.toString())
      });
  }
}
