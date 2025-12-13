import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';
import { Observable, of } from 'rxjs';
import { SelectListAdapter, SelectList } from '../../../../../core/model/select-list.model';
import { AllotmentInfoByEnquiryNumberAdaptor, AllotmentInfoByEnquiryNumber, MachineDetail } from '../../../../../core/model/allotment-info-by-enquiry-number.model';

export const staticData = {
  message: 'static data',
  status: '200',
  result: [{ id: 1, value: 'static 1' }, { id: 2, value: 'static 2' }]
};
@Injectable()
export class AllotmentDeAllotmentService {

  private readonly getEnquiryCodeUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.searchByEnquiryNumberForAllotment}`;
  private readonly getMobileNumberAutoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getMobileNumber}`;
  private readonly getDataFromMobileNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.getCustomer}`;
  private readonly getEnquiryMachineDetailByEnquiryIdUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.getEnquiryMachineDetailByEnquiryId}`;
  private readonly getChassisNumberFromItemNoUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.autoCompleteChassisNo}`;
  private readonly getItemNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.autoCompleteImplementItemNo}`;
  private readonly searchMachineByChassisNumberUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.searchMachineByChassisNumber}`
  private readonly getSystemGeneratedDateUrl = `${environment.baseUrl}${urlService.api}${urlService.getSystemGeneratedDate}`;
  private readonly machineAllotmentUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}`;
  private readonly getAllotmentByIdUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.getAllotmentById}`;
  private readonly deAllotMainMachineUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.deAllotMainMachine}`;
  private readonly deAllotMachineImplementsUrl = `${environment.baseUrl}${urlService.api}${urlService.sales}${urlService.machineAllotment}${urlService.deAllotMachineImplements}`;
  private readonly getPostOfficeFromPinUrl = `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.areaMaster}${urlService.getPostOffice}`;
  private readonly getPinCodeAutoUrl = `${environment.baseUrl}${urlService.api}${urlService.dealerMaster}${urlService.autoCompleteTehsilCityPincode}`;
  private readonly getLoginUserAddressDetailsUrl = `${environment.baseUrl}${urlService.api}${urlService.dealerMaster}${urlService.getDealerRegionInfo}`;
  private readonly getMainAddressDataFromPinUrl = `${environment.baseUrl}${urlService.api}${urlService.dealerMaster}${urlService.getPincodeDetail}`;
  private readonly getRequestNumberForAllotmentUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}/getRequestForAllotment`;
  private readonly getTransferRequestDetailsUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchaseOrder}/getTransferRequestDetails`;
  
  constructor(
    private httpClient: HttpClient,
    private selectListAdapter: SelectListAdapter,
    private allotmentInfoByEnquiryNumberAdaptor: AllotmentInfoByEnquiryNumberAdaptor
  ) { }
  getSystemGeneratedDate() {
    return this.httpClient.get<BaseDto<string>>(this.getSystemGeneratedDateUrl);
  }
  searchEnquiryCode(enquiryNo, userId = '1'): Observable<BaseDto<Array<SelectList>>> {
    let keyMap = {
      id: 'id',
      value: 'value',
      code: 'enquiryNumber'
    };
    return this.httpClient.get<BaseDto<Array<SelectList>>>(this.getEnquiryCodeUrl, {
      params: new HttpParams()
        .set('searchString', enquiryNo)
        .set('userId', userId)
    })
      .pipe(
        map(res => {
          res['result'] = this.selectListAdapter.adapt(res['result'], keyMap);
          return res;
        })
      );
  }
  searchMobileNumber(mobileNumber): Observable<BaseDto<Array<SelectList>>> {
    let keyMap = {
      // id: 'id',
      value: 'value',
      code: 'mobileNumber'
    };
    return this.httpClient.get<BaseDto<Array<SelectList>>>(this.getMobileNumberAutoUrl, {
      params: new HttpParams()
        .set('mobileNumber', mobileNumber)
    })
      .pipe(
        map(res => {
          res['result'] = this.selectListAdapter.adapt(res['result'], keyMap);
          return res;
        })
      );
  }
  getChassisNumbers(itemNumberId: string, chassisNumber: string) {
    if(chassisNumber === undefined)chassisNumber='';  
    let keyMap = {
      id: 'id',
      value: 'chassisNo'
    };
    return this.httpClient.get(this.getChassisNumberFromItemNoUrl, {
      params: new HttpParams().set('machineMasterId', itemNumberId).set('chassisNo', chassisNumber)
    }).pipe(
      map(res => {
        res['result'] = this.selectListAdapter.adapt(res['result'], keyMap);
        return res;
      })
    );
  }

  getItemNumber(itemNumber: string) {
    let keyMap = {
      id: 'id',
      value: 'itemNo'
    };
    return this.httpClient.get(this.getItemNumberUrl, {
      params: new HttpParams().set('itemNo', itemNumber)
    }).pipe(
      map(res => {
        res['result'] = this.selectListAdapter.adapt(res['result'], keyMap);
        return res;
      })
    );
  }

  searchMachineByChassisNumber(chassisNumber: string) {
    return this.httpClient.get<BaseDto<MachineDetail>>(`${this.searchMachineByChassisNumberUrl}`, {
      params: new HttpParams()
        .set('chassisNumber', chassisNumber)
    }).pipe(
      map((res) => {
        res['result'] = this.allotmentInfoByEnquiryNumberAdaptor.convertIntoMachineDetail(res['result']);
        // res['result'] = MachineDetail.mockData()
        return res;
      })
    )
  }
  getEnquiryMachineDetailByEnquiryNumber(enquiryId: string, userId) {
    return this.httpClient.get<BaseDto<AllotmentInfoByEnquiryNumber>>(`${this.getEnquiryMachineDetailByEnquiryIdUrl}`, {
      params: new HttpParams()
        .set('enquiryId', enquiryId)
        .set('userId', userId)
    }).pipe(
      map((res) => {
        res['result'] = this.allotmentInfoByEnquiryNumberAdaptor.adapt({ ...res['result'], ...res['result']['customerDetail'], });
        return res;
      })
    )
  }

  machineAllotment(saveObj: object) {
    return this.httpClient.post(this.machineAllotmentUrl, saveObj);
  }
  deAllotMainMachine(deAllot) {
    return this.httpClient.post(this.deAllotMainMachineUrl, deAllot);
  }
  deAllotMachineImplements(deAllot) {
    return this.httpClient.post(this.deAllotMachineImplementsUrl, deAllot);
  }
  getAllotmentById(allotmentId) {
    return this.httpClient.get(`${this.getAllotmentByIdUrl}/${allotmentId}`)
      .pipe(
        map((res) => {console.log(res['result']['customerDetail'])
          const newRes = this.allotmentInfoByEnquiryNumberAdaptor.adapt({ ...res['result'], ...res['result']['customerDetail'] });
          newRes['enquiryNumber'] = { enquiryNumber: res['result']['customerDetail'].enquiryNumber }
          newRes['allotmentNumber'] = res['result']['customerDetail'].allotmentNumber;
          newRes['allotmentDate'] = res['result']['customerDetail'].allotmentDate;
          newRes['pincode'] = { pinCode: res['result']['customerDetail'].pinCode };
          newRes['isDeAllot'] = res['result']['customerDetail'].isDeAllot;
          newRes['deAllotReason'] = res['result']['customerDetail'].deAllotReason;
          res['result'] = newRes;
          return res;
        })
      )
  }

  public getDataFromMobileNumber(mobileNumber: string) {
    return this.httpClient.get(this.getDataFromMobileNumberUrl, {
      params: new HttpParams().set('mobileNumber', mobileNumber)
    })
  }
  getPostOfficeFromPin(pinCode: string) {
    return this.httpClient.get(this.getPostOfficeFromPinUrl, {
      params: new HttpParams().set('pinCode', pinCode)
    })
  }
  getPinCodeAuto(districtId: string, pinCode: string) {
    return this.httpClient.get(this.getPinCodeAutoUrl, {
      params: new HttpParams().set('districtId', districtId).set('code', pinCode)
    })
  }
  getMainAddressDataFromPin(pincodeId: string, cityId: string) {
    return this.httpClient.get(this.getMainAddressDataFromPinUrl, {
      params: new HttpParams().set('pincodeId', pincodeId).set('cityId', cityId)
    })
  }
  getLoginUserAddressDetails() {
    return this.httpClient.get(this.getLoginUserAddressDetailsUrl)
  }

  getRequestNumberAutoList(requestNo){
    return this.httpClient.get(this.getRequestNumberForAllotmentUrl,{
      params : new HttpParams().set("requestNo",requestNo)
    })
  }

  getTransferRequestDetails(requestNo){
    return this.httpClient.get(this.getTransferRequestDetailsUrl,{
      params : new HttpParams().set("requestNo",requestNo)
    })
  }
}
