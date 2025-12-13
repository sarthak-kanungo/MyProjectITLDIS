import { Injectable } from '@angular/core';
import { UploadableFile } from 'kubota-file-upload';
import { v4 as uuid } from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private fileSizeLimit:number = 2000; //KB
  private files: Array<UploadableFile> = new Array()

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
    return this.files.length
  }

  listUploadableFiles() {
    return this.files
  }

  deleteAllFiles() {
    this.files = []
  }

  addtoFiles(files){
    this.files = files;
  }

  
  validateFileSize(uploadFile:File, isFor?:string){
    let size = uploadFile.size/1000;
    if(size > this.fileSizeLimit){
      return "Maximum file size limit is "+this.fileSizeLimit+" KB";
    }
    return "OK";
  }

}
