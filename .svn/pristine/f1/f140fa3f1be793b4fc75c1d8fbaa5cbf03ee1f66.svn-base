import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { ValidateMobileNumber, ValidateMaxLength, CustomValidators } from '../../../../../utils/custom-validators';
import { BaseDto } from 'BaseDto';
import { SelectList } from '../../../../../core/model/select-list.model';
@Injectable()
export class GoodsReceiptNoteCreateService {
  private readonly getGrnTypeUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.getGrnType }`;
  private readonly getTransporterNameUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.getTransporterName }`;
  private readonly searchAccPacInvoiceNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.searchAccPacInvoiceNumber }`;
  private readonly searchAccPacInvoiceByInvoiceNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.salesandpresales }${ urlService.grn }${ urlService.searchAccPacInvoiceByInvoiceNumber }`;
  private readonly getSystemGeneratedDateUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.getSystemGeneratedDate }`;
  private machineGrnFrom: FormGroup;
  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient
  ) { }
  createmachineGrnFrom() {
    this.machineGrnFrom = this.fb.group({
      grnType: [null, Validators.compose([Validators.required])],
      dmsGrnNumber: [{ value: null, disabled: true }, Validators.compose([])],
      grnDate: [{ value: null, disabled: true }, Validators.compose([])],
      accPacInvoice: [null, Validators.compose([Validators.required])],
      invoiceDate: [{ value: null, disabled: true }, Validators.compose([])],
      grnStatus: [{ value: null, disabled: true }, Validators.compose([])],
      billTo: [{ value: null, disabled: true }, Validators.compose([])],
      shipTo: [{ value: null, disabled: true }, Validators.compose([])],
      transporter: [{ value: null, disabled: false }, Validators.compose([Validators.required])],
      driverName: [{ value: null, disabled: false }, Validators.compose([])],
      driverMobile: [{ value: null, disabled: false }, Validators.compose([ValidateMobileNumber, Validators.required, CustomValidators.minLength(10)])],
      transporterVehicleNumber: [{ value: null, disabled: false }, Validators.compose([ValidateMaxLength(10)])],
      lrNo: [{ value: null, disabled: true }, Validators.compose([])],
      grnBy: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
    });
    this.machineGrnFrom.get('driverMobile').valueChanges.subscribe(res => {
    })
    return this.machineGrnFrom;
  }
  getGrnType(userId: string) {
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getGrnTypeUrl, {
      params: new HttpParams().set('userId', userId)
    });
  }
  getTransporterName() {
    return this.httpClient.get(this.getTransporterNameUrl);
  }
  searchAccPacInvoiceNumber(invoiceNumber: string, grnType:string) {
    return this.httpClient.get(this.searchAccPacInvoiceNumberUrl, {
      params: new HttpParams()
        .set('invoiceNumber', invoiceNumber)
        .set('grnType', grnType)
    });
  }
  searchAccPacInvoiceByInvoiceNumber(invoiceNumber: string, grnType: string) {
    return this.httpClient.get(this.searchAccPacInvoiceByInvoiceNumberUrl, {
      params: new HttpParams()
        .set('invoiceNumber', invoiceNumber)
        .set('grnType', grnType)
    });
  }
  getSystemGeneratedDate() {
    return this.httpClient.get(this.getSystemGeneratedDateUrl);
  }
}
