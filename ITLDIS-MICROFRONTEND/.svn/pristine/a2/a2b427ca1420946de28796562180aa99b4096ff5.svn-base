import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-hours-pop-up-service-booking',
  templateUrl: './hours-pop-up-service-booking.component.html',
  styleUrls: ['./hours-pop-up-service-booking.component.scss']
})
export class HoursPopUpServiceBookingComponent implements OnInit {
  hourPopUpForm: FormGroup
  maxChars: number
  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<HoursPopUpServiceBookingComponent>,
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
