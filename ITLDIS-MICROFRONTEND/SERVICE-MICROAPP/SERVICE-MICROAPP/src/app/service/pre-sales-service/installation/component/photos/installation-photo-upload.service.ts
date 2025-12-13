import { Injectable } from '@angular/core';
import { UploadableFile } from 'itldis-file-upload';
@Injectable()
export class InstallationPhotoUploadService {
   files: Array<UploadableFile> = new Array<UploadableFile>()
}

