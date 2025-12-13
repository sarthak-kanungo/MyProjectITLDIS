import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-image-preview',
  templateUrl: './image-preview.component.html',
  styleUrls: ['./image-preview.component.scss']
})
export class ImagePreviewComponent implements OnInit {
  static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;

  fileName: string
  constructor(private formBuilder: FormBuilder,
    private toastr : ToastrService,
    private dialogRef: MatDialogRef<ImagePreviewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { 
      this.fileName = data;
    }

  ngOnInit() {
    
  }
  closeDialog(){
    this.dialogRef.close();
  }

  onImgError(event) { 
    event.target.src = ImagePreviewComponent.staticPath+'/no-image-icon.jpg';
}

}
