import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { LogSheetPagePresenter } from '../log-sheet-page/log-sheet-page.presenter';
import { PcrApi } from '../../../product-concern-report/url-utils/product-concern-report.urls';
import { WarrantyPcrPhotos } from '../../../product-concern-report/domain/product-concern-report.domain';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-log-sheet-uploadfile',
  templateUrl: './log-sheet-uploadfile.component.html',
  styleUrls: ['./log-sheet-uploadfile.component.scss'],
})
export class LogSheetUploadfileComponent implements OnInit, OnChanges {
  files: Array<UploadableFile> = new Array()
  Videofiles: Array<UploadableFile> = new Array()
  fileStaticPath: string = PcrApi.staticPath;
  videoCheck:boolean
  @Input() warrantyLogsheetPhotosList: WarrantyPcrPhotos[];
  operation: string;
  constructor(
    private fileUploadService: FileUploadService,
    private logSheetPagePresenter: LogSheetPagePresenter,
    private tostr : ToastrService
  ) { }
  ngOnChanges(changes: SimpleChanges): void {
    if(this.operation=='view'){
      this.viewVideo();
    }
  }

  ngOnInit() {
    this.operation = this.logSheetPagePresenter.operation;
    
  }

  fileSelectionChanges(fileInput: Event) {
    console.log("fileInput ", fileInput);
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles();
      this.logSheetPagePresenter.collectPCRFiles(this.files);
      console.log(" this.files ", this.files);

    }
  }
  deleteImage(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    this.logSheetPagePresenter.collectPCRFiles(this.files);
  }

  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }

  pcrVideoUpload(fileInput: Event){
    let videoSize= (fileInput.target['files'][0].size/1024/1024).toFixed(2)
    let videoType:string = fileInput.target['files'][0].type
    
    if (videoType.toLocaleLowerCase()=='video/mp4' || videoType.toLocaleLowerCase()=='video/x-ms-wmv') {
     
    }else{
      this.tostr.error('Please upload MP4/WMV type')
      fileInput.target['files'][0].value=null
      return;
    }
    if (parseInt(videoSize)>=20) {
      this.tostr.error('Video not allowed Greater Than 20 MB')
      // this.uploadFileForm.get('pcrVideo').setErrors({
      //   videoError:"Video not allowed Greater Than 20 MB"
      // })
    }else{
      this.fileUploadService.pcrVideo(fileInput)
      this.Videofiles = this.fileUploadService.listUploadableVideo();
      this.logSheetPagePresenter.collectPCRVideo(this.Videofiles);
    }

  }
  viewVideo(){
    this.warrantyLogsheetPhotosList.forEach(file=>{
      let fileType=file.fileName.split(".").slice(1,2)
      fileType.forEach(f=>{
        if (f.toLocaleLowerCase()=='mp4' || f.toLocaleLowerCase()=='wmv') {
          file.videoFlag = true
        }
      })
    })
  }
  deleteVideo(id: string) {
    this.fileUploadService.deleteVideoFiles(id)
    this.Videofiles = this.fileUploadService.listUploadableVideo();
    this.logSheetPagePresenter.collectPCRVideo(this.files);
  }
}
