import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { JobCardUrl } from 'src/app/service/customer-service/job-card/url-util/job-card-url';

@Component({
  selector: 'app-in-progress',
  templateUrl: './in-progress.component.html',
  styleUrls: ['./in-progress.component.scss']
})
export class InProgressComponent implements OnInit {

  fileStaticPath = JobCardUrl.staticPath
  fileName: string
  constructor(private formBuilder: FormBuilder,
    private toastr : ToastrService,
    private dialogRef: MatDialogRef<InProgressComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { 
      this.fileName = data;
    }

  ngOnInit() {
    
  }
  closeDialog(){
    this.dialogRef.close();
  }

  onImgError(event) { 
    event.target.src = this.fileStaticPath+'/no-image-icon.jpg';
}

}
