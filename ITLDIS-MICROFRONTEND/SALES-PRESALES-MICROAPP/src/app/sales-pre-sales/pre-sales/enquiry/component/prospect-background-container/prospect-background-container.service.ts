import { Injectable } from '@angular/core';
import { environment } from '../../../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { urlService } from '../../../../../webservice-config/baseurl';
import { ProspectBackgroundObj, EnquirySoilType, EnquiryCropGrow } from 'ProspectBackground';


@Injectable()
export class ProspectBackgroundContainerService {
  private readonly occupationURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getOccupation}`;
  private readonly soilTypeURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getSoilType}`;
  private readonly majorCropGrownURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.enquiry}${urlService.getMajorCropGrown}`;
 
  prospectBackgroundObj = {} as ProspectBackgroundObj
  enquirySoilType = Array<EnquirySoilType>()
  enquiryCropGrow = Array<EnquiryCropGrow>()
  constructor(
    private http: HttpClient
  ) { }

  dropdownoccupation() {
    return this.http.get(`${this.occupationURL}`)
  }
  dropdownsoilType() {
    return this.http.get(`${this.soilTypeURL}`)
  }
  dropdownmajorCropGrown() {
    return this.http.get(`${this.majorCropGrownURL}`)
  }
}
