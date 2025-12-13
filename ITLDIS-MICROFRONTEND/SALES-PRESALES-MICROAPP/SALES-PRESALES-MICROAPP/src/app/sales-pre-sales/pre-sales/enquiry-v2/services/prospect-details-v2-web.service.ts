import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { BaseDto } from "BaseDto";
import { EnquiryApi } from "../url-utils/enquiry-urls";
import {
  ProspectType,
  MobileNo,
  AutoCompPinCode,
  PostOffice,
  SaveEnquiry,
  Title,
  DealerInformation,
  AutoTehsil,
  PincodeDetail,
} from "../domains/enquiry";
import { map } from "rxjs/operators";

@Injectable()
export class ProspectDetailsV2WebService {
  constructor(private http: HttpClient) {}

  getProspectType(): Observable<BaseDto<Array<ProspectType>>> {
    return this.http.get<BaseDto<Array<ProspectType>>>(
      EnquiryApi.getProspectType
    );
  }

  dropDownTitle(): Observable<BaseDto<Array<Title>>> {
    return this.http.get<BaseDto<Array<Title>>>(EnquiryApi.dropDownTitle);
  }

  getDealerRegionInfo(): Observable<Array<DealerInformation>> {
    return this.http
      .get<BaseDto<Array<DealerInformation>>>(EnquiryApi.getDealerRegionInfo)
      .pipe(map((dto) => dto.result));
  }

  getMobileNumber(mobileNumber: string): Observable<Array<MobileNo>> {
    return this.http
      .get<BaseDto<Array<MobileNo>>>(EnquiryApi.getMobileNumber, {
        params: new HttpParams().append("mobileNumber", mobileNumber),
      })
      .pipe(map((dto) => dto.result));
  }

  getDataByMobileNo(mobileNumber: string): Observable<SaveEnquiry> {
    return this.http
      .get<BaseDto<SaveEnquiry>>(EnquiryApi.getDataByMobileNo, {
        params: new HttpParams().append("mobileNumber", mobileNumber),
      })
      .pipe(map( (dto) => {if(dto!=null)return dto.result} ));
  }

  autoCompleteTehsilCityPincode(
    districtId: number,
    code: string
  ): Observable<Array<AutoCompPinCode>> {
    return this.http
      .get<BaseDto<Array<AutoCompPinCode>>>(
        EnquiryApi.autoCompleteTehsilCityPincode,
        {
          params: new HttpParams()
            .append("districtId", districtId.toString())
            .append("code", code),
        }
      )
      .pipe(map((dto) => dto.result));
  }

  getPincodeDetail(
    pincodeId: number,
    cityId: number
  ): Observable<PincodeDetail> {
    return this.http
      .get<BaseDto<PincodeDetail>>(EnquiryApi.getPincodeDetail, {
        params: new HttpParams()
          .append("cityId", cityId.toString())
          .append("pincodeId", pincodeId.toString())
      })
      .pipe(map((dto) => dto.result));
  }

  getPinCode(pinCode: string): Observable<Array<AutoCompPinCode>> {
    return this.http
      .get<BaseDto<Array<AutoCompPinCode>>>(EnquiryApi.getPinCode, {
        params: new HttpParams().append("pinCode", pinCode),
      })
      .pipe(map((dto) => dto.result));
  }

  getByPinCode(pinCode: string, cityId: number): Observable<SaveEnquiry> {
    return this.http
      .get<BaseDto<SaveEnquiry>>(EnquiryApi.getByPinCode, {
        params: new HttpParams()
          .append("pinCode", pinCode)
          .append("cityId", cityId.toString()),
      })
      .pipe(map((dto) => dto.result));
  }

  checkItemNumberModelInEnquiry(model: string, mobileNumber: string) {
    return this.http.get(EnquiryApi.checkItemNumberModelInEnquiry, {
      params: new HttpParams()
        .append("model", model)
        .append("mobileNumber", mobileNumber),
    });
  }

  languagesForEnquary():Observable<any>{
    return this.http.get<BaseDto<any>>(EnquiryApi.getLanguages).pipe(map(dto => dto.result))
  }
}
