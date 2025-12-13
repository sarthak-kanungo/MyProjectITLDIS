import { Injectable, EventEmitter } from '@angular/core';
import { ActivityReportEnquiryDetails } from 'ActualReportModule';
import { UploadableFile } from 'itldis-file-upload';

@Injectable()
export class ActivityReportCommonService {
   onActivityNumber: EventEmitter<any> = new EventEmitter<any>()
   onEnquiryDetails: EventEmitter<Array<ActivityReportEnquiryDetails>> = new EventEmitter<Array<ActivityReportEnquiryDetails>>()
   files: Array<UploadableFile> = new Array<UploadableFile>()
   enquiryDetails: Array<ActivityReportEnquiryDetails> = new Array<ActivityReportEnquiryDetails>() 
   conversionType: EventEmitter<boolean> = new EventEmitter<any>()
}