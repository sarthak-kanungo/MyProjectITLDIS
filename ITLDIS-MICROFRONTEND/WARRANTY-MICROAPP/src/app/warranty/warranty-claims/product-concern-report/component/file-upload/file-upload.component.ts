import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { FileUploadService } from '../../../../../ui/file-upload/file-upload.service';
import { PcrPagePresenter } from '../pcr-page/pcr-page.presenter';
import { WarrantyPcrPhotos, WarrantyPcrVideos } from '../../domain/product-concern-report.domain';
import { PcrApi } from '../../url-utils/product-concern-report.urls';
import { Operation } from '../../../../../utils/operation-util';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { UploadableFile, UploadableVideo } from 'src/app/ui/file-upload/file-upload';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit ,AfterViewInit{
  public files: Array<UploadableFile>  = new Array();
  Videofiles: Array<UploadableVideo> = new Array()
  @Input() public warrantyPcrPhotos: WarrantyPcrPhotos[];
  @Input() public warrantyPcrVideos:any;
  public fileStaticPath: string = PcrApi.staticPath;
  viewName: string;
  operation: string;
  uploadFileForm: FormGroup;
  allowVideoFlag:boolean
  videoCheck:boolean

  constructor(
    private fileUploadService: FileUploadService,
    private pcrPagePresenter: PcrPagePresenter,
    private tostr: ToastrService
  ) { }
  ngAfterViewInit() {
    this.viewVideo()
  }

  ngOnInit() {
    this.operation = this.pcrPagePresenter.operation;
    this.uploadFileForm = this.pcrPagePresenter.uploadFileForm;
    if(this.operation == Operation.VIEW || this.operation == Operation.EDIT) {
      this.warrantyPcrPhotos = this.uploadFileForm.controls.uploadFile.value;
      this.uploadFileForm.controls.uploadFile.clearValidators();
      if (this.warrantyPcrVideos.allowVideoUpload===true) {
        this.allowVideoFlag=true
      }
    }
  }

  fileSelectionChanges(fileInput: Event) {
    const msg = this.fileUploadService.validateFileSize(fileInput.target['files'][0],"PCR")
		if(msg != 'OK'){
			this.tostr.warning(msg);
			this.uploadFileForm.get('uploadFile').reset()
			return false;
		}
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelect(fileInput)
      this.files = this.fileUploadService.listUploadableFiles();
      this.pcrPagePresenter.collectPCRFiles(this.files);

    }
  }
  deleteImage(id: string) {
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    if(this.files.length == 0) {
      this.uploadFileForm.reset();
    }
    this.pcrPagePresenter.collectPCRFiles(this.files);
  }

  isFilesCountFive() {
    if(this.operation == Operation.JOBCARD) {
      return this.fileUploadService.fileCount() == 5
    }
    else if(this.operation == Operation.EDIT) {
      return this.warrantyPcrPhotos.length + this.files.length == 5;
    }
  }

  deleteVideo(id: string) {
    this.fileUploadService.deleteVideoFiles(id)
    this.Videofiles = this.fileUploadService.listUploadableVideo();
    if(this.Videofiles.length == 0) {
      this.uploadFileForm.reset();
    }
    this.pcrPagePresenter.collectPCRVideo(this.files);
  }
  
  pcrVideoUpload(fileInput: Event){
      let videoSize= (fileInput.target['files'][0].size/1024/1024).toFixed(2)
      let videoType = fileInput.target['files'][0].type
      if (parseInt(videoSize)>=20) {
        this.tostr.error('Video not allowed Greater Than 20 MB')
        this.uploadFileForm.get('pcrVideo').setErrors({
          videoError:"Video not allowed Greater Than 20 MB"
        })
      }else{
        this.fileUploadService.pcrVideo(fileInput)
        this.Videofiles = this.fileUploadService.listUploadableVideo();
        this.pcrPagePresenter.collectPCRVideo(this.Videofiles);
      }

  }
  viewVideo(){
    this.warrantyPcrPhotos.forEach(file=>{
      let fileType=file.fileName.split(".").slice(1,2)
      fileType.forEach(f=>{
        if (f==='mp4') {
          this.videoCheck=true
        }
      })
    })
  }

}
