import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { KisPagePresenter } from '../kai-inspection-sheet-create-page/kai-inspection-sheet-create-page.presenter';
import { KaiInspectionSheetApi } from '../../url-utils/kai-inspection-sheet-url';
import { KaiInspectionSheetPhoto } from '../../domain/kai-inspection-sheet.domain';
import { FormGroup } from '@angular/forms';
import { Operation } from '../../../../../utils/operation-util';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';

@Component({
  selector: 'app-kai-inspection-sheet-file-upload',
  templateUrl: './kai-inspection-sheet-file-upload.component.html',
  styleUrls: ['./kai-inspection-sheet-file-upload.component.scss']
})
export class KaiInspectionSheetFileUploadComponent implements OnInit {
  @Input() uploadFileForm: FormGroup;
  kaiInspectionSheetPhoto: KaiInspectionSheetPhoto[];
  files: Array<UploadableFile> = new Array();
  fileStaticPath = KaiInspectionSheetApi.staticPath;
  operation: string;
  constructor(
    private fileUploadService: FileUploadService,
    private kisPagePresenter: KisPagePresenter,
  ) { }

  ngOnInit() {
    this.operation = this.kisPagePresenter.operation;
    if(this.operation == Operation.VIEW || this.operation == Operation.APPROVE) {
      this.kaiInspectionSheetPhoto = this.uploadFileForm.controls.uploadFile.value;
    }else{
      this.fileUploadService.deleteAllFiles();
    }
  }
 
  fileSelectionChanges(fileInput: Event) {
    console.log("fileInput ", fileInput);
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles();
      this.kisPagePresenter.collectPCRFiles(this.files);
      console.log(" this.files ", this.files);


    }
  }
  deleteImage(id: string) {
    console.log(this.files)
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    if(this.files.length == 0) {
      this.uploadFileForm.reset();
    }
    this.kisPagePresenter.collectPCRFiles(this.files);
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }

}
