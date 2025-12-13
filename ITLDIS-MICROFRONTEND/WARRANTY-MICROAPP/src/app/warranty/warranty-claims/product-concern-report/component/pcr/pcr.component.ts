import { Component, OnInit, Input } from '@angular/core';
import { PcrWebService } from './pcr-web.service';
import { FieldCondition, FailureType, SoilType, CropGrown} from '../../domain/product-concern-report.domain';
import { FormGroup } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { PcrPagePresenter } from '../pcr-page/pcr-page.presenter';
@Component({
  selector: 'app-pcr',
  templateUrl: './pcr.component.html',
  styleUrls: ['./pcr.component.scss'],
  providers: [PcrWebService]
})
export class PcrComponent implements OnInit {
  @Input() pcrForm: FormGroup;
  fieldCondition: FieldCondition[] = [];
  failureType: FailureType[] = [];
  cropCondition: FieldCondition[] = [];
  soilType: SoilType[]  =[];
  majorCropGrown: CropGrown[] = [];
  warrantyTypeList = [];
  isShow: boolean = false;
  operation: string;
  @Input() isShowReason: boolean;
  @Input() btnName:string;
  @Input() isKai:boolean=false;
  reasons:any[]
  constructor(
    private pcrService: PcrWebService,    
    private activatedRoute: ActivatedRoute,
    private pcrPagePresenter: PcrPagePresenter,
  ) { 
    
  }

  ngOnInit() {
    this.activeRoute();
  }

  activeRoute() {
    this.operation = this.pcrPagePresenter.operation;
    this.activatedRoute.queryParams.subscribe(qparams => {
      this.dropDownFieldCondition();
      this.dropDownFailureType(qparams.id);
      this.dropDownCropCondition();
      this.getSoilType();
      this.getMajorCropGrown();
      this.getLookupByCode('WA_WARRANTY_TYPE');
      this.getLookupByCode('PCR_CLOSURE_REASON');

      this.pcrForm.get('delayReasonSelect').valueChanges.subscribe(response => {
        if(response && response.toLowerCase()=='others'){
          this.pcrForm.get('delayReason').reset();
          this.pcrForm.get('delayReason').enable();
        }else{
          this.pcrForm.get('delayReason').patchValue(this.pcrForm.get('delayReasonSelect').value);
          this.pcrForm.get('delayReason').disable();
        }
      })
    })
  }

  private getLookupByCode(code){
    this.pcrService.getLookupByCode(code).subscribe(response => {
      if(code=='WA_WARRANTY_TYPE'){
        this.warrantyTypeList = response;
      }
      if(code=='PCR_CLOSURE_REASON'){
        this.reasons = response;
      }
    })
  }
  private dropDownFieldCondition() {
    this.pcrService.dropDownFieldCondition().subscribe(result => {
      this.fieldCondition = result;
    }, err => {
      console.log('err', err);
    });
  }
  private dropDownFailureType(serviceJobcardId: string) {
     this.pcrService.dropDownFailureType(serviceJobcardId).subscribe(result => {
       this.failureType = result;


      /*  console.log('this.failureType', this.failureType)
       console.log('this.pcrForm.get(\'customerName\')', this.pcrForm.get('customerName'))
       if(this.pcrForm.get('customerName').value == null || this.pcrForm.get('customerName').value == ' ') {
         console.log('this.pcrForm.get(\'customerName\')', this.pcrForm.get('customerName'))
         this.pcrForm.get('failureType').patchValue(result[0].failureType);
      } */
    }, err => {
      console.log('err', err);
    });

  }
  private dropDownCropCondition() {
     this.pcrService.dropDownCropCondition().subscribe(result => {
      this.cropCondition = result;
    }, err => {
      console.log('err', err);
    });


  }
  private getSoilType() {
    this.pcrService.getSoilType().subscribe(result => {
     this.soilType = result;
   }, err => {
      console.log('err', err);
    });


 }
 private getMajorCropGrown() {
    this.pcrService.getMajorCropGrown().subscribe(result => {
      this.majorCropGrown= result;
    }, err => {
      console.log('err', err);
    });
 }

 public selectFailure(event: MatCheckboxChange) {
   event.checked ? this.isShow = true : this.isShow = false;
   if(!this.isShow) {
     this.pcrForm.get('failureCount').reset();
   }
 }

}
