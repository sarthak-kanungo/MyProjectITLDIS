import { Component, OnInit, Input } from '@angular/core';
import { PcrApi } from '../../../product-concern-report/url-utils/product-concern-report.urls';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { ActivatedRoute } from '@angular/router';
import { GoodwillPagePresenter } from '../goodwill-create-page/goodwill-create-page.presenter';
import { WarrantyPcrPhotos } from '../../../product-concern-report/domain/product-concern-report.domain';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { FormGroup } from '@angular/forms';
import { Operation } from 'src/app/utils/operation-util';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-goodwill-upload',
  templateUrl: './goodwill-upload.component.html',
  styleUrls: ['./goodwill-upload.component.css']
})
export class GoodwillUploadComponent implements OnInit {
  @Input() warrantyPcrPhotos: WarrantyPcrPhotos;
  files: Array<UploadableFile>  = new Array();
  fileStaticPath: string = PcrApi.staticPath;
  operation: string;
  uploadFileForm: FormGroup;
  constructor(
    private fileUploadService: FileUploadService,
    private goodwillPagePresenter: GoodwillPagePresenter,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.operation = this.goodwillPagePresenter.operation;
    this.uploadFileForm = this.goodwillPagePresenter.uploadFileForm;
    if(this.operation == Operation.VIEW || this.operation == Operation.EDIT) {
      this.warrantyPcrPhotos = this.uploadFileForm.controls.uploadFile.value;
      this.uploadFileForm.controls.uploadFile.clearValidators();
    }
  }

  fileSelectionChanges(fileInput: Event) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"GOODWILL")
		if(msg != 'OK'){
			this.toastr.warning(msg);
			this.uploadFileForm.get('uploadFile').reset()
			return false;
		}
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles();
      this.goodwillPagePresenter.collectPCRFiles(this.files);

    }
  }
  deleteImage(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    if(this.files.length == 0) {
      this.uploadFileForm.reset();
    }
    this.goodwillPagePresenter.collectPCRFiles(this.files);
  }

  isFilesCountFive() {
      return this.fileUploadService.fileCount() == 5
  }

}
