import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { UploadableFile } from 'itldis-file-upload';
import { BehaviorSubject } from 'rxjs';
import { v4 as uuid } from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private fileSizeLimit : number = 200; //KB

  public files: Array<UploadableFile> = new Array()
  public audioFile: Array<UploadableFile> = new Array()
  public newtype:BehaviorSubject<string> = new BehaviorSubject<string>('');

  constructor(private sanitizer:DomSanitizer) { }

  onFileSelect(fileEvent: Event) {
    if (fileEvent && fileEvent.target['files'][0] && this.newtype.value=='') {
      let uploadableFile = { file: fileEvent.target['files'][0], previewUrl: '', id: uuid()}
      this.files.push(uploadableFile)
      this.previewIfImage(uploadableFile)
      fileEvent.target['value'] = ''
    }
    else if (fileEvent && fileEvent.target['files'][0] && this.newtype.value=='COMPLAINT') {
      let uploadableFile = { file: fileEvent.target['files'][0], previewUrl: '', id: uuid()}
      this.audioFile.push(uploadableFile)
      this.previewIfImage(uploadableFile)
      fileEvent.target['value'] = ''
    }
    else  if (fileEvent && fileEvent.target['files'][0] && this.newtype.value=='CALLATTEMPT') {
      let uploadableFile = { file: fileEvent.target['files'][0], previewUrl: '', id: uuid()}
      this.files.push(uploadableFile)
      this.previewIfImage(uploadableFile)
      fileEvent.target['value'] = ''
    }
    
  }


   previewIfImage(uploadableFile: UploadableFile) {
    var mimeType = uploadableFile.file.type;
    // if (mimeType.match(/image\/*/) == null ) {
    //   return;
    // }
    var reader = new FileReader();
    reader.readAsDataURL(uploadableFile.file);
    reader.onload = (event) => {
      // uploadableFile.previewUrl = event.target['result']
      uploadableFile.previewUrl =  this.sanitizer.bypassSecurityTrustUrl((event.target['result'].toString()))
      /* above line is commented and added by Vinay to make previewUrl as safeUrl to remove error (unsafe url value base64)*/
      
    }
  }

  deleteFile(id: string) {
    this.files = this.files.filter(file => file.id !== id)
  }
  deleteAudioFile(id: string) {
    this.audioFile = this.audioFile.filter(file => file.id !== id)
  }

  fileCount() {
    return this.files.length
  }

  listUploadableFiles() {
    return this.files
  }

  complaintlistUploadableFiles() {
    return this.audioFile
  }
  deleteAllFiles() {
    this.files = []
  }

  validateFileSize(uploadFile:File, isFor?:string){
    let size = uploadFile.size/1000;
    if(size > this.fileSizeLimit){
      return "Maximum file size limit is "+this.fileSizeLimit+" KB";
    }
    return "OK";
  }

}
