import { Injectable } from '@angular/core';
import { UploadableFile } from 'kubota-file-upload';
import { v4 as uuid } from 'uuid';
import { Observable } from 'rxjs';
import { SearchAutocomplete, JobNumberData, SearchAutocompleteJobCode, DropDownCategory } from '../../domain/job-card-domain';
import { JobCardUrl } from '../../url-util/job-card-url';
import { HttpParams, HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BaseDto } from 'BaseDto';

@Injectable()
export class OutsideJobWebService {
  private files: Array<UploadableFile> = new Array()
  private serviceCouponList: Array<UploadableFile> = new Array()
  private houMeterList: Array<UploadableFile> = new Array()
  private chassisNumberList: Array<UploadableFile> = new Array()
  constructor(private httpClient: HttpClient) { }
  onFileSelect(fileEvent: Event, searchKey) {

    if (fileEvent && fileEvent.target['files'][0]) {
      let uploadableFile = { file: fileEvent.target['files'][0], previewUrl: '', id: uuid() }
      if (searchKey == "couponOne") {
        this.files.push(uploadableFile)
      }
      if (searchKey == "couponTwo") {
        this.serviceCouponList.push(uploadableFile)
      }
      if (searchKey == "hourMeter") {
        this.houMeterList.push(uploadableFile)
      }
      if (searchKey == "chassisNumber") {
        this.chassisNumberList.push(uploadableFile)
      }
      this.previewIfImage(uploadableFile)
      fileEvent.target['value'] = ''
    }
  }


  private previewIfImage(uploadableFile: UploadableFile) {
    const mimeType = uploadableFile.file.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }
    const reader = new FileReader();
    reader.readAsDataURL(uploadableFile.file);
    reader.onload = (event) => {
      uploadableFile.previewUrl = event.target['result']
    }
  }

  deleteFile(id: string) {
    this.files = this.files.filter(file => file.id !== id)
  }
  deleteFileCouponTwo(id: string) {
    this.serviceCouponList = this.serviceCouponList.filter(file => file.id !== id)
  }
  deleteFileHour(id: string) {
    this.houMeterList = this.houMeterList.filter(file => file.id !== id)
  }
  deleteFileChassis(id: string) {
    this.chassisNumberList = this.chassisNumberList.filter(file => file.id !== id)
  }

  fileCount() {
    return this.files.length
  }
  fileCountCoupon() {
    return this.serviceCouponList.length
  }
  fileCountMeter() {
    return this.houMeterList.length
  }
  fileCountChassis() {
    return this.chassisNumberList.length
  }

  listUploadableFiles() {
    return this.files
  }
  listUploadableFilesCoupon() {
    return this.serviceCouponList
  }
  listUploadableFileshourMeter() {
    return this.houMeterList
  }
  listUploadableFilesChassis() {

    return this.chassisNumberList
  }

  listUploadableFilesServiceCouponList() {
    return this.serviceCouponList
  }

  listUploadableFilesHourMeterList() {
    return this.houMeterList
  }

  listUploadableFilesChassisNumberList() {
    return this.chassisNumberList
  }

  deleteAllFiles() {
    this.files = []
  }


  jobCodeAutocomplete(jobcode: string): Observable<Array<SearchAutocompleteJobCode>> {
    return this.httpClient.get<BaseDto<Array<SearchAutocompleteJobCode>>>(JobCardUrl.autoCompletejobNumber, {
      params: new HttpParams().set('jobcode', jobcode)
    }).pipe(map(dto => dto.result))
  }

  getDataFromJobCardNumber(jobcode: string): Observable<Array<JobNumberData>> {
    return this.httpClient.get<BaseDto<Array<JobNumberData>>>(JobCardUrl.getDataFromJobNumberUrl, {
      params: new HttpParams().set('jobcode', jobcode)
    }).pipe(map(dto => dto.result))
  }
  serviceCategory(): Observable<DropDownCategory> {
    return this.httpClient.get<BaseDto<DropDownCategory>>(JobCardUrl.dropDownOsLabourChargeCategory, {
      params: new HttpParams()
    }).pipe(map(dto => dto.result))
  }
}
