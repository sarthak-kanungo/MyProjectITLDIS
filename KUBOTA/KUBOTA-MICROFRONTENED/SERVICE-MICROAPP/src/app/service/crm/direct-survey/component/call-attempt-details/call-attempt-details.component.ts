import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UploadAudio } from '../../domain/direct-survey-domain';
import { DirectSurveyPagePresenter } from '../create-direct-survey/direct-survey-page.presenter';
import { DirectSurveyPageStore } from '../create-direct-survey/direct-survey-page.store';
import { v4 as uuid } from 'uuid';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { UploadableFile } from 'kubota-file-upload';
import { DirectSurveyService } from '../../direct-survey.service';
import { ActivatedRoute } from '@angular/router';
import { Operation } from 'src/app/utils/operation-util';
import { DirectSurveyApi } from '../../url-util/direct-survey-urls';

@Component({
  selector: 'app-call-attempt-details',
  templateUrl: './call-attempt-details.component.html',
  styleUrls: ['./call-attempt-details.component.css'],
})
export class CallAttemptDetailsComponent implements OnInit {

  callAttemptForm:FormGroup
  public files: Array<UploadableFile>  = new Array();
  fileName:any
  viewTable:boolean
  callResponseList:any[]=[]

  isEdit: boolean
  isView: boolean
  isCreate: boolean
  today = new Date();
  constructor(private directSurveyPagePresenter: DirectSurveyPagePresenter,
              private tostr: ToastrService,
              private fileUploadService: FileUploadService,
              private service:DirectSurveyService,
              private activityRoute: ActivatedRoute,
              ) { }

  ngOnInit() {
    this.callAttemptForm = this.directSurveyPagePresenter.calAttemptDetails
    this.getCallResponse()
    this.viewEditCreate()
    this.fileUploadService.files.length=0
  }

  private viewEditCreate() {
    if (this.directSurveyPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
    }
    if (this.directSurveyPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true

    }
    if (this.directSurveyPagePresenter.operation === Operation.VIEW) {
      this.isView = true
    }
  }

  getCallResponse(){
    this.service.getLookupByCode("SURVEY_RESPONSE_TYPE").subscribe(response => {
      this.callResponseList = response['result'];
      this.service.viewCall.next(this.callResponseList)
    });
  }

  deleteAudio(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    if(this.files.length == 0) {
      //this.callAttemptForm.reset();
      this.viewTable=false
    }
  }

  uploadRecording(fileInput: Event) {
    let type = fileInput.target['files'][0].type
    console.log('vinay_type----',type);
    if (type==='audio/ogg' || type==='video/x-ms-wmv' || type==='audio/mpeg' || type==='audio/wav') {
        if (this.fileUploadService.files.length<1) {
          let newType = 'CALLATTEMPT'
          this.fileUploadService.newtype.next(newType);
          this.fileUploadService.onFileSelect(fileInput)
          this.files = this.fileUploadService.listUploadableFiles();
          this.directSurveyPagePresenter.callAttemptRecordingFiles(this.files);
          this.viewTable=true
        }
        else if(this.fileUploadService.files.length>=1){
          this.tostr.error('Maximum 1 file can be attached')
        }
      }
    else{
      this.tostr.error('Please upload a Valid Audio File')
      }
  }

  // isFilesCountFive() {
  //   return this.fileUploadService.fileCount() == 5
  // }

  // dummtyToSubmit(){
  //   const formdata = new FormData();
  //   formdata.append('img', this.recordedFile,this.recordedFile.name);
  // }

}
