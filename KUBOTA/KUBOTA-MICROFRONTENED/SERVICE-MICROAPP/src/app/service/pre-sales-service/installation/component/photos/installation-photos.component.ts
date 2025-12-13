import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { InstallationPhotoUploadService } from './installation-photo-upload.service';
import { UploadableFile } from 'kubota-file-upload';
import { Operation } from '../../../../../utils/operation-util';
import { InstallationApi } from '../../url-util/installation-urls';
import { InstallationPagePresenter } from '../installation-page/installation-page-presenter';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { ServiceInstallationPhotosList } from '../../domain/installation-domain';
import { ToastrService } from 'ngx-toastr';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-photos',
  templateUrl: './installation-photos.component.html',
  styleUrls: ['./installation-photos.component.scss'],
  providers: [InstallationPhotoUploadService]
})
export class InstallationPhotosComponent implements OnInit, OnChanges {

  @Input() uploadPhotosForm : FormGroup
  files: Array<UploadableFile> = new Array()
  isCreate: boolean;
  isEdit: boolean;
  isView: boolean;
  previewUrl: string
  fileNamelist = []
  fileName: string
  fileStaticPath: string = InstallationApi.staticPath
  @Input() installationPhotoList: Array<ServiceInstallationPhotosList>

  constructor(
    private fileUploadService: FileUploadService,
    private presenter: InstallationPagePresenter,
    private toastr: ToastrService
  ) { }

  ngOnChanges() {
    if (this.installationPhotoList) {
      this.installationPhotoList.forEach(element => {
        this.fileNamelist.push(element)
      });
    }
  }

  ngOnInit() {
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      this.isView = true
      this.isEdit = false
      this.isCreate = false
    } else if (this.presenter.operation === Operation.EDIT) {
      this.isEdit = true
      this.isView = false
      this.isCreate = false
    } else if (this.presenter.operation === Operation.CREATE) {
      this.isCreate = true
      this.isEdit = false
      this.isView = false
    }
  }

  fileSelctionChanges(fileInput: any) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"INSTALLATION")
		if(msg != 'OK'){
			this.toastr.warning(msg);
			this.uploadPhotosForm.get('photos').reset()
			return false;
		}
    if (!this.isFilesCountThree()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles()
      this.presenter.fileSelectionChanges(this.files)
    } else{
        fileInput.target['value'] = ''
    }
  }
  deleteImage(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles()
    this.presenter.deleteImage(this.files)
  }

  isFilesCountThree() {
    return this.fileUploadService.fileCount() == 3
  }

  ngOnDestroy() {
    this.fileUploadService.deleteAllFiles();
  }
}
