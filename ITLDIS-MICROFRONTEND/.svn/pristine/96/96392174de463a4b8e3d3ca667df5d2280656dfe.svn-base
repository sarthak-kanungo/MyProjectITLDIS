import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class ProspectBackgroundService {
  private  prospectBackgroundForm: FormGroup;
  
  constructor(private fb: FormBuilder
  ) { }

  createprospectBackgroundForm() {
    this.prospectBackgroundForm = this.fb.group({
      landSize: [null, Validators.compose([Validators.required])],
      occupation:[null,Validators.compose([Validators.required])],
      soilName:[null,Validators.compose([])],
      cropName:[null,Validators.compose([])],
      otherOccupation:[null,Validators.compose([Validators.pattern('^[a-zA-Z \-\']+')])]
    })
    return this.prospectBackgroundForm
  }

  keyPress(event: any) {
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  viewprospectBackgroundForm() {
    this.prospectBackgroundForm = this.fb.group({
      landSize: this.fb.control({ value: null, disabled: true }),
      occupation:this.fb.control(null),
      soilName:this.fb.control(null),
      cropName:this.fb.control(null),
      otherOccupation:this.fb.control(null)
    })
    return this.prospectBackgroundForm
  }

  viewMobileprospectBackgroundForm() {
    this.prospectBackgroundForm = this.fb.group({
      landSize: this.fb.control({ value: null, disabled: false }),
      occupation:this.fb.control({ value: null, disabled: false }),
      soilName:this.fb.control({ value: null, disabled: false }),
      cropName:this.fb.control({ value: null, disabled: false }),
      otherOccupation:this.fb.control({ value: null, disabled: false })
    })
    return this.prospectBackgroundForm
  }

 
}
