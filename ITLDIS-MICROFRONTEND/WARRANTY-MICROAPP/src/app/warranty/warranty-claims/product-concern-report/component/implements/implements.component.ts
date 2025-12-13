import { Component, OnInit, Input } from '@angular/core';
import { PcrPagePresenter } from '../pcr-page/pcr-page.presenter';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { distinctUntilChanged } from 'rxjs/operators';
import { PcrWebService } from '../pcr/pcr-web.service';
@Component({
  selector: 'app-implements',
  templateUrl: './implements.component.html',
  styleUrls: ['./implements.component.scss']
})
export class ImplementsComponent implements OnInit {
  
  @Input() implementForm: FormArray;
  usageArr = [];
  viewName: any;
  isDisabled = false;
  catgList = [];
  gearRatioList = [];
  constructor(
    private pcrPagePresenter: PcrPagePresenter,
    private activatedRoute: ActivatedRoute,
    private tostr: ToastrService,
    private pcrWebService: PcrWebService,
  ) { }

  ngOnInit() {
    this.activeRoute();
    
    this.pcrWebService.dropDownImplCatg().subscribe(response => {
        this.catgList = response;
    })
   
    this.pcrWebService.getLookupByCode('PCR_GEAR_RATIO').subscribe(response => {
      this.gearRatioList = response;
    })
  }
  private activeRoute() {
    this.activatedRoute.queryParams.subscribe((qParam) => {
      this.viewName = qParam.name;
    })
    // this.addRow1();
    const implementForm = this.implementForm.controls[this.implementForm.controls.length-1] as FormGroup;
    implementForm.get('implement').valueChanges.subscribe(val => {
      this.formValueChanges(implementForm);
    })
    implementForm.get('implementCategory').valueChanges.subscribe(val => {
      this.formValueChanges(implementForm);
    })
    implementForm.get('implementMake').valueChanges.subscribe(val => {
      this.formValueChanges(implementForm);
    })
  }

  addRow1() {
    const uuid = this.pcrPagePresenter.addRowInImplement().get('uuid');
    const fromIndex = this.implementForm.controls.findIndex(fg=>fg.get('uuid')===uuid);

    console.log('newform', uuid,fromIndex, this.implementForm.controls[fromIndex])
   
    this.implementForm.controls[fromIndex].get('percentOfUsage').valueChanges.subscribe(val => {
      console.log('val', val)
      if(val) {
        const sum = this.sum(this.implementForm.controls, 'percentOfUsage')
        console.log('sum', sum)
        if(sum > 100) {
          this.implementForm.controls[fromIndex].get('percentOfUsage').setErrors({moreThan: true})
        }else {
          this.implementForm.controls[fromIndex].get('percentOfUsage').setErrors(this.implementForm.controls[fromIndex].get('percentOfUsage').validator(this.implementForm.controls[fromIndex].get('percentOfUsage')));
        }
        console.log('this.implementForm.controls[fromIndex]>>>>', this.implementForm.controls[fromIndex])
      }
      console.log('this.implementForm1111.', this.implementForm)
      
      
    })
    console.log('this.implementForm222.', this.implementForm)

  }

  sum(items, prop: string) {
    return items.reduce((prevVal, currVal) => {
       console.log('currVal', currVal);
       return prevVal + parseInt(currVal.getRawValue()[prop]);
    }, 0);
}

  addRow() {
    const implementForm = this.implementForm.controls[this.implementForm.controls.length-1] as FormGroup;
    this.formValueChanges(implementForm);
    
    if(this.pcrPagePresenter.PCRForm.get('customerName').value == null || this.pcrPagePresenter.PCRForm.get('customerName').value == ' ') {
      if(implementForm.valid) {
        this.pcrPagePresenter.addRowInImplement();
      }
      else {
        this.tostr.error('Please fill form', 'Error');
        implementForm.markAllAsTouched();
      }
    }
    else {
      if(this.implementForm.valid) {
        this.pcrPagePresenter.addRowInImplement();
      }
      else {
        this.tostr.error('Please fill form', 'Error');
        this.implementForm.markAllAsTouched();
      }
    }
  }
  deleteRow() {
    this.pcrPagePresenter.deleteRowInImplement();
  }

  private formValueChanges(implementForm: FormGroup) {
    if(implementForm.get('implement').value == null || implementForm.get('implement').value == '' || implementForm.get('implementCategory').value == null || implementForm.get('implementCategory').value == '' || implementForm.get('implementMake').value == null || implementForm.get('implementMake').value == '') {
      if(implementForm.get('implementCategory').value =='Not Applicable'){
          implementForm.get('implement').clearValidators()
          implementForm.get('implementMake').clearValidators()
          implementForm.get('implementMake').updateValueAndValidity();
          implementForm.get('implement').updateValueAndValidity();
      }else{
        this.setValidators(implementForm);
      }
    }

  }

  private setValidators(newForm :FormGroup) {
    newForm.get('implement').setValidators(Validators.required);
    newForm.get('implement').updateValueAndValidity({emitEvent: false});
    newForm.get('implementCategory').setValidators(Validators.required);
    newForm.get('implementCategory').updateValueAndValidity({emitEvent: false});
    newForm.get('implementMake').setValidators(Validators.required);
    newForm.get('implementMake').updateValueAndValidity({emitEvent: false});
  }
  
  totalUsage(list: FormGroup) {
  }
/* numberOnlyForGearRatio created by vinay*/
  checkGearRatio(fg :FormGroup) {
    if ((fg.get('gearRatio').value. indexOf(':'))>-1) {
      let val=fg.get('gearRatio').value.split(":").slice(1,2)
      if (val[0]==='') {
        fg.get('gearRatio').setErrors({
          gearRatioError1:'Please enter value in ration form, e.g. a:b'
        })
      }
     }
     else{
      fg.get('gearRatio').setErrors({
        gearRatioError:'Please enter value in ration form, e.g. a:b'
      })
     }
  }

}
