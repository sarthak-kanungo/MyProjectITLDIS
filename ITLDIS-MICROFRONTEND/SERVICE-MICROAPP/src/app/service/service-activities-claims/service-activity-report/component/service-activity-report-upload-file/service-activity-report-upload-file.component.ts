import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Operation } from '../../../../../utils/operation-util';
import { UploadableFile } from 'itldis-file-upload';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { ServiceActivityReportCreatePagePresenter } from '../service-activity-report-create-page/service-activity-report-create-page.presenter';
import { ServiceActivityReportApi } from '../../url-util/service-activity-report-urls';
import { ViewServiceActivityReport } from '../../domain/service-activity-report.domain';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-service-activity-report-upload-file',
  templateUrl: './service-activity-report-upload-file.component.html',
  styleUrls: ['./service-activity-report-upload-file.component.scss']
})
export class ServiceActivityReportUploadFileComponent implements OnInit, OnChanges {

  @Input() documentUplodedForm : FormGroup
  files: Array<UploadableFile> = new Array()
  isCreate: boolean;
  isView: boolean;
  previewUrl: string
  fileNamelist = []
  fileName: string
  fileStaticPath: string = ServiceActivityReportApi.staticPath
  @Input() viewActivityReport: ViewServiceActivityReport

  constructor(
    private fileUploadService: FileUploadService,
    private serviceActivityReportCreatePagePresenter: ServiceActivityReportCreatePagePresenter,
    private toastr : ToastrService
  ) { }

  ngOnChanges() {
    if (this.viewActivityReport) {
      this.viewActivityReport.activityReportPhotoList.forEach(element => {
        this.fileNamelist.push(element)
        console.log("fileNamelist ", this.fileNamelist);
      });
    }
  }

  ngOnInit() {
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityReportCreatePagePresenter.operation === Operation.VIEW) {
      this.isView = true
      this.isCreate = false
    } else if (this.serviceActivityReportCreatePagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.isView = false
    }
  }

  fileSelctionChanges(fileInput: any) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"ACTIVITY_REPORT")
		if(msg != 'OK'){
			this.toastr.warning(msg);
			this.documentUplodedForm.get('photos').reset()
			return false;
		}
    if (!this.isFilesCountThree()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles()
      this.serviceActivityReportCreatePagePresenter.fileSelectionChanges(this.files)
    }
  }
  deleteImage(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles()
    this.serviceActivityReportCreatePagePresenter.deleteImage(this.files)
  }

  isFilesCountThree() {
    return this.fileUploadService.fileCount() == 5
  }

  ngOnDestroy() {
    this.fileUploadService.deleteAllFiles();
  }

}
