import { Component, OnInit, forwardRef, Input } from '@angular/core';
import { DeliveryChallanCancelService } from './delivery-challan-cancel.service';
import { FormGroup, NG_VALUE_ACCESSOR, NG_VALIDATORS, AbstractControl, ValidationErrors, Validators, ControlValueAccessor } from '@angular/forms';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-delivery-challan-cancel',
  templateUrl: './delivery-challan-cancel.component.html',
  styleUrls: ['./delivery-challan-cancel.component.scss'],
  providers: [DeliveryChallanCancelService,
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DeliveryChallanCancelComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => DeliveryChallanCancelComponent),
      multi: true
    }
  ]
})
export class DeliveryChallanCancelComponent implements OnInit, ControlValueAccessor, Validators {
  public cancellationTypeList: string[];
  public cancellationReasonsList: string[];
  public brandsList: string[];
  public reasonsList: string[];
  public labelPosition = 'before';
  public showHideCancelForm: boolean = false;
  public dcCancellationForm: FormGroup;
  @Input() public isView: boolean;
  public isEdit: boolean;
  public isCancel: boolean;
  public isLostToCompitition: boolean;
  public dcstatus:string 
  constructor(
    private deliveryChallanCancelService: DeliveryChallanCancelService
  ) { }

  ngOnInit() {
    // this.getCancellationType();
    // this.getCancellationReason();
    // this.getAllBrands();
    //this.getAllReasons();
    this.dcCancellationForm = this.deliveryChallanCancelService.createdcCancellationForm();
  }
  public validate(control: AbstractControl): ValidationErrors {
    return this.dcCancellationForm.valid ? null : { invalidForm: { valid: false, message: "Cancelation form is invalid" } };
  }
  public registerOnValidatorChange?(fn: () => void): void {
    // throw new Error("Method not implemented.");
  }
  public writeValue(patchObjectData: any): void {    //Used to patch value to form for edit
    console.log("patchObjectDataCREATE ", patchObjectData);
    if (patchObjectData) {
      this.isView = patchObjectData.isView;
      this.isEdit = patchObjectData.isEdit;
      this.isCancel = patchObjectData.isCancel;
      
      this.dcCancellationForm.patchValue(patchObjectData);
      this.dcstatus = patchObjectData.status; 
      if(patchObjectData.status == 'DC CANCELLED'){
          this.dcCancellationForm.disable();  
      }
    }
  }
  public registerOnChange(fn: any): void {
    fn && this.dcCancellationForm.valueChanges.pipe(
      map((value) => this.dcCancellationForm.getRawValue())
    ).subscribe(fn);
    // throw new Error("Method not implemented.");
  }
  public registerOnTouched(fn: any): void {
    // throw new Error("Method not implemented.");
  }
  public setDisabledState?(isDisabled: boolean): void {
    if(isDisabled){
           
    }
  }

  public showCancelForm(event) {
    this.getCancellationType();
    this.getCancellationReason();
    this.getAllBrands();
    this.showHideCancelForm = !this.showHideCancelForm;
  }

  public getCancellationType() {
    this.deliveryChallanCancelService.getCancellationType().subscribe(res => {
      this.cancellationTypeList = res['result'];
    })
  }
  public getCancellationReason() {
    this.deliveryChallanCancelService.getCancellationReason().subscribe(res => {
      this.cancellationReasonsList = res['result'];
    })
  }
  public getAllBrands() {
    this.deliveryChallanCancelService.getAllBrand().subscribe(res => {
      this.brandsList = res['result'];
    })
  }
  public getAllReasons() {
    let type = this.dcCancellationForm.value.dcCancellationType;
    if (type.toLowerCase() === 'lost' ) {
    this.deliveryChallanCancelService.getAllReasons().subscribe(res => {
      this.reasonsList = res['result'];
    })
    
    }
    else if (type.toLowerCase() === 'machine change') {
      // let dumyChassiNo:any =[{"reason":"Model Change"},{"reason":"ETC"}]
      // this.reasonsList = dumyChassiNo;
      this.deliveryChallanCancelService.getAllReasons().subscribe(res => {
        this.reasonsList = res['result'];

      })
    }
  }
  checkForLostToCompitition(event) {
    this.getAllReasons();
    let type = this.dcCancellationForm.value.dcCancellationType;
    let reason = this.dcCancellationForm.value.dcCancellationReason;
    if (type && reason) {
      if (type.toLowerCase() === 'lost' && reason.toLowerCase() === 'lost to competition') {
        this.dcCancellationForm.setValidators(Validators.required);
        this.isLostToCompitition = true;
      } else {
        this.isLostToCompitition = false;
        this.dcCancellationForm.controls.brand.setValidators(Validators.nullValidator);
        this.dcCancellationForm.controls.model.setValidators(Validators.nullValidator);
      }
    }
  }

}
