import { Injectable } from '@angular/core';
import { v4 as uuid } from 'uuid';
import { UploadableFile } from './file-upload';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  private fileSizeLimit : number = 200; //KB
  private files: Array<UploadableFile> = new Array()
  private Videofiles: Array<UploadableFile> = new Array()
  videoUrl:any

  constructor() { }

  onFileSelect(fileEvent: Event) {
    console.log('fileEvent', fileEvent)
    if (fileEvent && fileEvent.target['files'][0]) {
      let uploadableFile = { file: fileEvent.target['files'][0], previewUrl: '', id: uuid() }
      this.files.push(uploadableFile)
      this.previewIfImage(uploadableFile)
      fileEvent.target['value'] = ''
    }
  }


  private previewIfImage(uploadableFile: UploadableFile) {
    var mimeType = uploadableFile.file.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }
    var reader = new FileReader();
    reader.readAsDataURL(uploadableFile.file);
    reader.onload = (event) => {
      uploadableFile.previewUrl = event.target['result']
    }
  }

  deleteFile(id: string) {
    this.files = this.files.filter(file => file.id !== id)
  }

  fileCount() {
    return this.files.length;
  }

  listUploadableFiles() {
    return this.files
  }

  deleteAllFiles() {
    this.files = []
  }

  // pcrVideo(event){
  //   const file = event.target.files && event.target.files[0];
  //   if (file) {
  //     var reader = new FileReader();
  //     reader.readAsDataURL(file);
  //     reader.onload = (event) => {
  //       this.videoUrl = (<FileReader>event.target).result;
  //     }
  //   }
  // }

  listUploadableVideo() {
    return this.Videofiles
  }
  deleteVideoFiles(id: string) {
    this.Videofiles = this.Videofiles.filter(file => file.id !== id)
  }

  pcrVideo(fileEvent: Event){
    if (fileEvent && fileEvent.target['files'][0]) {
      let uploadableFile = { file: fileEvent.target['files'][0], previewUrl: '', id: uuid() }
      this.Videofiles.push(uploadableFile)
      this.pcrVideoPreview(uploadableFile)
      fileEvent.target['value'] = ''
    }
  }
  pcrVideoPreview(uploadableFile: UploadableFile){
    var reader = new FileReader();
    reader.readAsDataURL(uploadableFile.file);
    reader.onload = (event) => {
      uploadableFile.previewUrl = event.target['result']
    }
  }
  validateFileSize(uploadFile:File, isFor?:string){
    let size = uploadFile.size/1000;
    if(size > this.fileSizeLimit){
      return "Maximum file size limit is "+this.fileSizeLimit+" KB";
    }
    return "OK";
  }
}
