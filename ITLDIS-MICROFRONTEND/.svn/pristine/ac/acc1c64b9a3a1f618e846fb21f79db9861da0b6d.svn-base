import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-modal-file-upload',
  templateUrl: './modal-file-upload.component.html',
  styleUrls: ['./modal-file-upload.component.css']
})
export class ModalFileUploadComponent implements OnInit {
  
  fileUploadForm: FormGroup
  file : File
  fileuploadname:string;
  constructor(public dialogRef: MatDialogRef<ModalFileUploadComponent>,
              private fb : FormBuilder) { }

  ngOnInit() {
    this.fileUploadForm = this.fb.group({
      fileUpload: [null, Validators.required],
    })
  }

  public close(){
    this.dialogRef.close();
  }

  public submit(){
    if(this.fileUploadForm.valid){
        this.dialogRef.close({event:'upload',data:this.file});
    }
  }

  public fileSelctionChanges(fileEvent){
    this.file = fileEvent.target['files'][0];
    if(this.file){
        this.fileuploadname = this.file.name;
    }else{
        this.fileuploadname = '';
    }
  }


}
