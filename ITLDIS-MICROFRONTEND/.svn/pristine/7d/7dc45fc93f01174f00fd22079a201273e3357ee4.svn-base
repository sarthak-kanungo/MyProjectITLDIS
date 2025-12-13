import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-service-job-card-hour-pop',
  templateUrl: './service-job-card-hour-pop.component.html',
  styleUrls: ['./service-job-card-hour-pop.component.css']
})
export class ServiceJobCardHourPopComponent implements OnInit {
  hourPopUpForm: FormGroup
  maxChars: number
  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<ServiceJobCardHourPopComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    console.log('data', this.data)
    this.createForm()
    this.maxChars = this.data.priviousHours
    this.hourPopUpForm.controls.meterChangeValue.patchValue(this.data.priviousHours)
  }
  createForm() {
    this.hourPopUpForm = this.formBuilder.group({
      meterChangeValue: [null, Validators.min(this.data.priviousHours)]
    })
  }
  closeDialog(): void {
    this.dialogRef.close();
  }
  submit() {
    if (this.hourPopUpForm.valid) {
      console.log('this.hourPopUpForm.value', this.hourPopUpForm.value)
      this.dialogRef.close(this.hourPopUpForm.value);
    }
  }

}
