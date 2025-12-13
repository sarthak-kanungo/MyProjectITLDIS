import { Injectable } from '@angular/core';
import { UploadableFile } from 'itldis-file-upload';
@Injectable()
export class MrcPhotoUploadService {
   files: Array<UploadableFile> = new Array<UploadableFile>()
}

