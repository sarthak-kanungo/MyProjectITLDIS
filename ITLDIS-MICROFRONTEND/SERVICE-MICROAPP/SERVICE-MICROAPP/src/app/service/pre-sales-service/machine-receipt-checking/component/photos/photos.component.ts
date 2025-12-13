import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MrcPhotoUploadService } from './mrc-photo-upload.service';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { UploadableFile } from 'itldis-file-upload';
import { ActivatedRoute } from '@angular/router';
import { MachineReceiptCheckingPresenter } from '../mrc-details-page/mrc-page.presenter';
import { Operation } from '../../../../../utils/operation-util';
import { MrcPageService } from '../mrc-details-page/mrc-page-web.service';
import { MrcUrl } from '../../url-util/machine-receipt-checking-urls';
import { FormGroup } from '@angular/forms';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-photos',
  templateUrl: './photos.component.html',
  styleUrls: ['./photos.component.scss'],
  providers: [MrcPhotoUploadService]
})
export class PhotosComponent implements OnInit {
  files: Array<UploadableFile> = new Array()
  isCreate: boolean;
  isEdit: boolean;
  isView: boolean;
  previewUrl: string
  @Output() selectedPhotos = new EventEmitter<Array<UploadableFile>>()
  fileNamelist = []
  fileName: string
  fileStaticPath: string = MrcUrl.staticPath
  @Input() photoUploadFileForm: FormGroup
  constructor(
    private fileUploadService: FileUploadService,
    private activateRoute: ActivatedRoute,
    private presenter: MachineReceiptCheckingPresenter,
    private mrcPageService: MrcPageService,
    private toastr : ToastrService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.activateRoute.paramMap.subscribe(param => {
      if (param.has('id')) {
        if (this.presenter.operation === Operation.VIEW) {
          this.isView = true;
          this.isEdit = false
          this.patchPhoto(atob((param.get('id'))));
        }
      }
      if (this.presenter.operation === Operation.CREATE || this.presenter.operation === Operation.EDIT) {
        this.isView = false;
        this.isEdit = true
        if (this.presenter.operation === Operation.EDIT) {
          this.patchPhoto((param.get('id')));
          this.photoUploadFileForm.get('uploadFile').clearValidators();
        }
      }

    })
  }
  fileSelctionChanges(fileInput: any) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"MRC")
		if(msg != 'OK'){
			this.toastr.warning(msg);
			this.photoUploadFileForm.get('uploadFile').reset()
			return false;
		}
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles()
      this.selectedPhotos.emit(this.files)

    }else{
        fileInput.target['value'] = ''
    }
  }
  deleteImage(id: string) {
    this.openConfirmDialog(id);
  }

  private openConfirmDialog(id): void | any {
    let message = `Do you want to delete?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm'){
        this.fileUploadService.deleteFile(id)
        this.files = this.fileUploadService.listUploadableFiles()
        if (this.files.length == 0) {
          this.photoUploadFileForm.reset()
        }
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }

  isFilesCountFive() {
    return (this.fileUploadService.fileCount() == 5)
  }
  private patchPhoto(id: any) {
    this.mrcPageService.getMrcViewdata(id).subscribe(viewResult => {
      viewResult['mrcPhotoList'].forEach(element => {
        console.log("element ", element);
        this.fileNamelist.push(element)
        console.log("this.fileNamelist ", this.fileNamelist);
      });
    })
  }
  ngOnDestroy() {
    this.fileUploadService.deleteAllFiles();
  }
}
