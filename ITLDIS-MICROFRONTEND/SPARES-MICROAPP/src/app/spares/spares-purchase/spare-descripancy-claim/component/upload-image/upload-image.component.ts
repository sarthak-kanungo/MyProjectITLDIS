import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { SpareDescripancyClaimPresenter } from '../store-presenter/spare-descripancy-claim-presenter';
import { UploadableFile } from 'itldis-file-upload';
import { spareDescripancy } from '../url-utils/spare-descripancy-url';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {
  @Input()  attachementForm:FormGroup;
  @Input() attachmentList:any;
  @Input() isView:boolean;
  @Input() isEdit:boolean;
  @Input() isApprove:boolean;
  files: Array<UploadableFile> = new Array();
  filesPhoto2: Array<UploadableFile> = new Array();
  fileStaticPath: string = spareDescripancy.staticPath
  constructor(
    private fileUploadService: FileUploadService,
    private toaster:ToastrService,
    private presenter:SpareDescripancyClaimPresenter
  ) { }

  ngOnInit() {
  
  }

  ngOnChanges(){
    if(this.isEdit || this.isView || this.isApprove){
    this.attachmentList=this.attachmentList;
    
    this.attachmentList.forEach(photo=>{
      if(photo){
        this.presenter.createAttachmentForm.get('photo1').patchValue(photo.fileName)
      }
    })
  }
  }

  fileSelctionChangesPhoto1(fileInput:any){
    console.log('photo1')
//    if (fileInput.target['files'][0].size > 1000000) {
//   this.toaster.error("You can't upload Maximum 1mb file");
//   return
// }
// if (fileInput.target['files'][0].size < 100000) {
//   this.toaster.error("You can't upload  Minimum 100kb file");
//   return
// }
if (!this.isFilesCountFive()) {
  this.fileUploadService.onFileSelect(fileInput)
  this.files = this.fileUploadService.listUploadableFiles();
  this.presenter.collectPhoto1(this.files);

}
  }
  isFilesCountFive() {
    return this.fileUploadService.fileCount() == 5
  }


  deleteImagePhoto1(id:string){
    this.fileUploadService.deleteFile(id)
    this.files = this.fileUploadService.listUploadableFiles();
    this.presenter.collectPhoto1(this.files);
  }

  fileSelctionChangesPhoto2(fileInput:any){

    console.log('call photp 2')
    if (fileInput.target['files'][0].size > 1000000) {
      this.toaster.error("You can't upload Maximum 1mb file");
      return
    }
    if (fileInput.target['files'][0].size < 100000) {
      this.toaster.error("You can't upload  Minimum 100kb file");
      return
    }
    if (!this.isFilesCountFive()) {
      this.fileUploadService.onFileSelects(fileInput)
      this.filesPhoto2 = this.fileUploadService.listUploadableFilessss();
      console.log(this.filesPhoto2,'filePhoto2')
      this.presenter.collectPhoto2(this.filesPhoto2);
    
  }
}

}
