import { Component, OnDestroy, OnInit, Output } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { UploadableFile } from 'itldis-file-upload';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Operation } from 'src/app/utils/operation-util';
import { EventEmitter } from 'events';
import { EnquiryCreateCommonService } from '../enquiry-v2-create/enquiry-create-common.service';
import { EnquiryV2CreateWebService } from '../../services/enquiry-v2-create-web.service';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-enquiry-attachments',
  templateUrl: './enquiry-attachments.component.html',
  styleUrls: ['./enquiry-attachments.component.scss']
})
export class EnquiryAttachmentsComponent implements OnInit, OnDestroy {

  @Output() saveAttachments = new EventEmitter();
  files: Array<UploadableFile> = new Array()
  isViewMobile: boolean;
  isView: boolean;
  disable = true;
  actualReportImageForm: FormGroup

  constructor(
    private fileUploadService: FileUploadService,
    private actRt: ActivatedRoute,
    private fb : FormBuilder,
    private toasterService: ToastrService,
    private enquiryCreateCommonService: EnquiryCreateCommonService,
    private enquiryV2CreateWebService: EnquiryV2CreateWebService) { }

  ngOnInit() {
    this.initOperationForm();
    this.markFormAsTouched();
    this.checkOperationType();
    this.clearAll();
    this.patchImages();
  }

  patchImages(){
    if (this.isView) {
      this.files = this.enquiryCreateCommonService.files;     
      this.fileUploadService.addtoFiles(this.files); 
    }
  }

  private initOperationForm() {
    if (this.isView) {
    }
    else {
     this.createActivityReportImageForm()
    }
  }

  createActivityReportImageForm() {
    this.actualReportImageForm = this.fb.group({
      images: [null, Validators.required],
    })
  }

  private markFormAsTouched() {
    for (const key in this.actualReportImageForm.controls) {
      if (this.actualReportImageForm.controls.hasOwnProperty(key)) {
        this.actualReportImageForm.controls[key].markAsTouched();
      }
    }
  }

  fileSelctionChanges(fileInput: any) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"ENQUIRY")
		if(msg != 'OK'){
			this.toasterService.warning(msg);
			this.actualReportImageForm.get('images').reset()
			return false;
		}
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles()
      this.enquiryCreateCommonService.files = this.files
    }else{
        this.toasterService.error("Maximum 5 documents allowed");
    }
  }

  deleteImage(id: string) {
    console.log("Deleted Files--->", id)
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles()
    this.enquiryV2CreateWebService.deleteAttachment(id).subscribe((val: BaseDto<Object>) => {
      console.log("delete Attachments--->",val)
    });
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }

  ngOnDestroy() {
    this.clearAll()
  }

  clearAll() {
    this.enquiryCreateCommonService.files = [];
    this.files = []
    this.fileUploadService.deleteAllFiles()
  }

  private checkOperationType() {
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == Operation.VIEW
    this.isViewMobile = this.actRt.snapshot.routeConfig.path.split('/')[0] == Operation.VIEW_MOBILE
  }

}
