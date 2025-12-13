import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { HotlineReportApi } from '../../url-utils/hotline-report-url';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { HotlineReportPagePresenter } from '../hotline-report-create-page/hotline-report-create-page.presenter';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { ToastrService } from 'ngx-toastr';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hotline-report-vendor-response',
  templateUrl: './hotline-report-vendor-response.component.html',
  styleUrls: ['./hotline-report-vendor-response.component.scss']
})
export class HotlineReportVendorResponseComponent implements OnInit {
  @Input() vendorRespomseForm: FormGroup;
  @Input() answerPhoto:any;
  files: Array<UploadableFile> = new Array();
  fileStaticPath = HotlineReportApi.staticPath;
  operation: string;
 
  isView:any;
  isEdit:any;
  constructor(
    private fileUploadService: FileUploadService,
    private hotlineReportPagePresenter: HotlineReportPagePresenter,
    private toaster:ToastrService,
    private activatedRoute:ActivatedRoute
  ) { }

  ngOnInit() {
    this.checkFormOperation()
    // this.operation = this.hotlineReportPagePresenter.operation;
  }
  private checkFormOperation() {
    this.hotlineReportPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.hotlineReportPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
    }
    else if (this.hotlineReportPagePresenter.operation == Operation.EDIT) {
      this.isEdit = true;
    }
  }

  fileSelectionChanges(fileInput: Event) {
    console.log(fileInput)
    if (fileInput.target['files'][0].size > 1000000) {
      this.toaster.error("You can't upload Maximum 1mb file");
      return
    }
    if (fileInput.target['files'][0].size < 100000) {
      this.toaster.error("You can't upload  Minimum 100kb file");
      return
    }
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput);
      this.files = this.fileUploadService.listUploadableFiles();
      this.hotlineReportPagePresenter.collectPCRFiles(this.files);
    }
  }
  deleteImage(id: string) {
    // console.log(this.files);
    this.fileUploadService.deleteFile(id);
    this.files = this.fileUploadService.listUploadableFiles();
    this.hotlineReportPagePresenter.collectPCRFiles(this.files);
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5;
  }
}
