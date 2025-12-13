import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { IncentiveScheme } from 'IncentiveSchemeMasterModule';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Injectable()
export class CreateIncentiveSchemeMasterService {
  incentiveSchemeMaster: FormGroup
  selectZonesIdForRegion: Array<number> = []
  selectProductIdForSeries: Array<number> = []
  selectSeriesIdForModel: Array<number> = []
  selectModelIdForSubModel: Array<number> = []
  selectSubModelIdForVariant: Array<number> = []
  private readonly getAllZonesURL = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveSchemeMaster/getAllZones`
  private readonly getRegionByZoneIdURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}/incentiveSchemeMaster/getRegionsByZoneIds`
  private readonly getSchemeDateURL = `${environment.baseUrl}${urlService.api}${urlService.getSystemGeneratedDate}`
  private readonly seriesDropdownURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.seriesDropdown}`
  private readonly modelDropdownURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.modelDropdown}`
  private readonly subModelDropdownURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.subModelDropdown}`
  private readonly variantDropdownURL = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.product}${urlService.variantDropdown}`
  private readonly productDropdownURL = `${environment.baseUrl}${urlService.api}/service/activityProposal/getAllProduct`
  private readonly downloadTemplateURL = `${environment.baseUrl}${urlService.api}/spares/purchaseOrder/downloadTemplate`
  private readonly saveSchemeURL = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveSchemeMaster/saveIncentiveSchemeMaster`
  private readonly viewSchemeUrl = `${environment.baseUrl}${urlService.api}/salesandpresales/incentiveSchemeMaster/viewIncentiveSchemeMaster`
  static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}` 
  constructor(
    private http: HttpClient,
    private fb: FormBuilder
  ) { }

  createincentiveSchemeMaster() {
    this.incentiveSchemeMaster = this.fb.group({
      schemeNo: [null],
      status: [null],
      referenceSchemeNo: [null, Validators.compose([Validators.required])],
      validTo: [null, Validators.compose([Validators.required])],
      validFrom: [null, Validators.compose([Validators.required])],
      schemeType: [null, Validators.compose([Validators.required])],
      schemeDate: [{ value: '', disabled: true }],
      activityNo: [null, Validators.compose([])],
      zones: [null, Validators.compose([])],
      regions: [null, Validators.compose([])],
      product: [null, Validators.compose([])],
      series: [null, Validators.compose([])],
      model: [null, Validators.compose([])],
      submodels: [null, Validators.compose([])],
      variant: [null, Validators.compose([])],
      itemNo: [{value: null, disabled: false}, Validators.compose([])],
      maxQty: [{ value: false, disabled: true }, Validators.compose([])],
      claimAttachReq: [false, Validators.compose([])],
      exceedQty: [false, Validators.compose([])]
    })
    return this.incentiveSchemeMaster
  }
  viewincentiveSchemeMaster() {
    this.incentiveSchemeMaster = this.fb.group({
      schemeNo: [{ value: '', disabled: true }],
      status: [{ value: '', disabled: true }],
      referenceSchemeNo: [{ value: '', disabled: true }],
      validTo: [{ value: '', disabled: true }],
      validFrom: [{ value: '', disabled: true }],
      activityNo: [{ value: '', disabled: true }],
      schemeType: [{ value: '', disabled: true }],
      schemeDate: [{ value: '', disabled: true }],
      zones: [{ value: '', disabled: true }],
      regions: [{ value: '', disabled: true }],
      product: [{ value: '', disabled: true }],
      series: [{ value: '', disabled: true }],
      model: [{ value: '', disabled: true }],
      submodels: [{ value: '', disabled: true }],
      variant: [{ value: '', disabled: true }],
      itemNo: [{ value: '', disabled: true }],
      maxQty: [{ value: false, disabled: true }],
      claimAttachReq: [{ value: false, disabled: true }],
      exceedQty: [{ value: false, disabled: true }],
      image:[{value:null,disabled:false}],
      maximumQuantity:[{value:null,disabled:false},CustomValidators.numberOnly]
    })
    return this.incentiveSchemeMaster
  }

  getAllZones() {
    return this.http.get(`${this.getAllZonesURL}`)
  }

  getRegionByZoneId(zoneIds: string) {
    return this.http.get(`${this.getRegionByZoneIdURL}`, {
      params: new HttpParams().set('zoneIds', zoneIds)
    })
  }
  addToSelectedZones(zoneIds: number) {
    this.selectZonesIdForRegion.push(zoneIds)
  }

  removeFromSelectedZones(zoneIds: number) {
    this.selectZonesIdForRegion = this.selectZonesIdForRegion.filter(zone => zone !== zoneIds)
  }
  getSchemeDate() {
    return this.http.get(`${this.getSchemeDateURL}`)
  }

  addToSelectedProduct(proId: number) {
    this.selectProductIdForSeries.push(proId)
  }
  removeFromSelectedProduct(proId: number) {
    this.selectProductIdForSeries = this.selectProductIdForSeries.filter(prod => prod !== proId)
  }
  dropdownProduct(){
    return this.http.get(`${this.productDropdownURL}`);
  }
  seriesDropdown(productNames: string) {
    return this.http.get(`${this.seriesDropdownURL}`, {
      params: new HttpParams().set('product', productNames)
    })
  }

  addToSelectedSeries(seriesId: number) {
    this.selectSeriesIdForModel.push(seriesId)
  }
  removeFromSelectedSeries(seriesId: number) {
    this.selectSeriesIdForModel = this.selectSeriesIdForModel.filter(series => series !== seriesId)
  }

  modelDropdown(series: string) {
    return this.http.get(`${this.modelDropdownURL}`, {
      params: new HttpParams().set('series', series.toString())
    })
  }

  addToSelectedModel(modelId: number) {
    this.selectModelIdForSubModel.push(modelId)
  }
  removeFromSelectedModel(modelId: number) {
    this.selectModelIdForSubModel = this.selectModelIdForSubModel.filter(model => model !== modelId)
  }
  subModelDropdown(models: string) {
    return this.http.get(`${this.subModelDropdownURL}`, {
      params: new HttpParams().set('model', models)
    })
  }

  variantDropdown(submodels: string) {
    return this.http.get(`${this.variantDropdownURL}`, {
      params: new HttpParams().set('subModel', submodels)
    })
  }

  public downloadTemplate(filename) : Observable<HttpResponse<Blob>> {
    return this.http.get<Blob>(`${this.downloadTemplateURL}`, { 
        params: new HttpParams().set("filename",filename),
        observe: 'response', responseType: 'blob' as 'json' });
  }

  saveScheme(schemeMaster: IncentiveScheme){
    let formData: FormData = new FormData();
    formData.append('incentiveSchemeMaster', new Blob([JSON.stringify(schemeMaster)], { type: 'application/json' }))
     if(schemeMaster.multipartFile!=undefined || schemeMaster.multipartFile!=null ){
      schemeMaster.multipartFile.forEach(markImg => {
        formData.append('multipartFile', markImg['file'])
      })
     }
    
    const headers = new HttpHeaders();
    headers.set('Content-Type', null);
    headers.set('Accept', 'multipart/form-data');

    return this.http.post(this.saveSchemeURL, formData, {
      headers: headers
    })
  }

  viewScheme(schemeNo:any){
    return this.http.get(`${this.viewSchemeUrl}`, {
      params : new HttpParams().set('schemeNo', schemeNo)
    })
  }
}
