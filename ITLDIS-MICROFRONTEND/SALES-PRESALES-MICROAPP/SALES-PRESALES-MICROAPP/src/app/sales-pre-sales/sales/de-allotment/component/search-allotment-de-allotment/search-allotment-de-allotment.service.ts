import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { SelectListAdapter, SelectListKeyMap, SelectList } from '../../../../../core/model/select-list.model';
import { BaseDto } from 'BaseDto';
import { AllotmentSearch } from '../../../../../core/model/allotment-search';
import { UtilsService } from '../../../../../utils/utils.service';
import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class SearchAllotmentDeAllotmentService {

  private readonly getAllProductUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getAllProduct }`;
  private readonly searchByEnquiryNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.machineAllotment }${ urlService.searchByEnquiryNumber }`;
  private readonly getItemNoUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.machineMaster }${ urlService.autoCompleteItems }`;
  private readonly searchByAllotmentNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.machineAllotment }${ urlService.searchByAllotmentNumber }`;
  private readonly getChassisNumbersUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.machineAllotment }${ urlService.searchMachineChassisNumber }`;
  private readonly searchMachineAllotmentUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.machineAllotment }${ urlService.searchMachineAllotment }`;
  private readonly searchByEngineNumberUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.sales }${ urlService.machineAllotment }${ urlService.searchByEngineNumber }`;


  private readonly getSeriesByProductUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getSeriesByProduct }`;
  private readonly getModelBySeriesUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getModelBySeries }`;
  private readonly getSubModelUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getSubModel }`;
  private readonly getVariantBySubModelUrl = `${ environment.baseUrl }${ urlService.api }${ urlService.master }${ urlService.product }${ urlService.getVariantBySubModel }`;


  constructor(
    private httpClient: HttpClient,
    private selectListAdapter: SelectListAdapter,
    private utilsService: UtilsService,
    private dateService: DateService
  ) { }
  searchItemNo(itemNo: string) {
    return this.httpClient.get<BaseDto<string[]>>(`${ this.getItemNoUrl }`, {
      params: new HttpParams().set('itemNo', itemNo)
          .set('productGroup','')
          .set('functionality','ALLOTMENT_SEARCH')
    });
  }
  dropdownGetAllProductType() {
    let keyMap = {
      id: 'id',
      value: 'product'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(`${ this.getAllProductUrl }`).pipe(
      map(res => {
        res.result = this.selectListAdapter.adapt(res.result, keyMap);
        return res;
      })
    )
  }
  getSeriesByProduct(product: string) {
    let keyMap = {
      id: 'id',
      value: 'series'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getSeriesByProductUrl, {
      params: new HttpParams().set('product', product)
    })
      .pipe(
        map(res => {
          res.result = this.selectListAdapter.adapt(res.result, keyMap);
          return res;
        })
      );
  }

  getModelBySeries(series: string) {
    let keyMap = {
      id: 'id',
      value: 'model'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(`${ this.getModelBySeriesUrl }`, {
      params: new HttpParams().set('series', series)
    })
      .pipe(
        map(res => {
          res.result = this.selectListAdapter.adapt(res.result, keyMap);
          return res;
        })
      );
  }
  getSubModelByModel(model: string) {
    let keyMap = {
      id: 'id',
      value: 'subModel'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(this.getSubModelUrl, {
      params: new HttpParams().set('model', model)
    })
      .pipe(
        map(res => {
          res.result = this.selectListAdapter.adapt(res.result, keyMap);
          return res;
        })
      );
  }
  searchEnquiryCode(enquiryNo) {
    let keyMap = {
      id: 'id',
      value: 'value'
    };
    return this.httpClient.get<BaseDto<Array<SelectList>>>(this.searchByEnquiryNumberUrl, {
      params: new HttpParams().set('enquiryNumber', enquiryNo)
    }).pipe(
      map(res => {
        res.result = this.selectListAdapter.adapt(res.result, keyMap);
        return res;
      })
    );
  }
  getAllVariant(subModel: string) {
    let keyMap = {
      id: 'id',
      value: 'variant'
    };
    return this.httpClient.get<BaseDto<SelectList[]>>(`${ this.getVariantBySubModelUrl }`, {
      params: new HttpParams().set('subModel', subModel)
    })
      .pipe(
        map(res => {
          res.result = this.selectListAdapter.adapt(res.result, keyMap);
          return res;
        })
      );
  }
  searchByAllotmentNumber(allotmentNumber: string, userId: string) {
    let keyMap = {
      id: 'id',
      value: 'value'
    };
    return this.httpClient.get<BaseDto<Array<SelectList>>>(this.searchByAllotmentNumberUrl, {
      params: new HttpParams()
        .set('allotmentNumber', allotmentNumber)
        .set('userId', userId)
    })
      .pipe(
        map(res => {
          console.log(' res.result', res.result);

          res.result = this.selectListAdapter.adapt(res.result, keyMap);
          return res;
        })
      );
  }
  searchByEngineNumber(engineNumber: string, userId: string) {
    let keyMap = {
      id: 'id',
      value: 'value'
    };
    return this.httpClient.get<BaseDto<Array<SelectList>>>(this.searchByEngineNumberUrl, {
      params: new HttpParams()
        .set('engineNumber', engineNumber)
        .set('userId', userId)
    })
      .pipe(
        map(res => {

          res.result = this.selectListAdapter.adapt(res.result, keyMap);
          console.log(' res.result', res.result);
          return res;
        })
      );
  }
  searchChassisNumbers(chassisNumber) {
    let keyMap = {
      id: 'id',
      value: 'chassisNo'
    };
    return this.httpClient.get<BaseDto<Array<SelectList>>>(this.getChassisNumbersUrl, {
      params: new HttpParams().set('chassisNumber', chassisNumber)
    }).pipe(
      map(res => {
        res['result'] = this.selectListAdapter.adapt(res['result'], keyMap);
        return res;
      })
    );
  }
  searchMachineAllotment(searchAllotment: AllotmentSearch) {
    searchAllotment = this.utilsService.removeEmptyKey<AllotmentSearch>(searchAllotment);
    if (searchAllotment.fromDate) {
      searchAllotment.fromDate = this.dateService.getFormattedDate(searchAllotment.fromDate);
    }
    if (searchAllotment.toDate) {
      searchAllotment.toDate = this.dateService.getFormattedDate(searchAllotment.toDate);
    }
    return this.httpClient.post(this.searchMachineAllotmentUrl, searchAllotment)
  }
  getSystemGeneratedDate() {
    return this.dateService.getSystemGeneratedDate(this.httpClient);
  }
}
