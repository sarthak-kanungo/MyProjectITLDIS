import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PcrWebService } from '../../../product-concern-report/component/pcr/pcr-web.service';
import { FieldCondition, FailureType, SoilType, CropGrown } from '../../../product-concern-report/domain/product-concern-report.domain';
import { GoodwillWebService } from './goodwill-web.service'
import { Goodwilltype } from '../../domain/goodwill.domain';
@Component({
  selector: 'app-goodwill',
  templateUrl: './goodwill.component.html',
  styleUrls: ['./goodwill.component.scss'],
  providers: [PcrWebService, GoodwillWebService]
})
export class GoodwillComponent implements OnInit {
  @Input() goodwillForm: FormGroup;
  fieldCondition: FieldCondition[] = [];
  failureType: FailureType[] = [];
  cropCondition: FieldCondition[] = [];
  soilType: SoilType[]  =[];
  majorCropGrown: CropGrown[] = [];
  goodwillType: Goodwilltype;
  @Input() isView:boolean
  constructor(
    private pcrService: PcrWebService,
    private goodwillWebService: GoodwillWebService,
  ) { }

  ngOnInit() {
    this.dropDownFieldCondition();
    this.dropDownFailureType();
    this.dropDownCropCondition();
    this.getSoilType();
    this.getMajorCropGrown();
    this.dropDownGoodwillType();
  }

  private dropDownFieldCondition() {
    this.pcrService.dropDownFieldCondition().subscribe(result => {
      this.fieldCondition = result;
    }, err => {
      console.log('err', err);
    });
  }
  private dropDownFailureType() {
     this.pcrService.dropDownFailureType('0').subscribe(result => {
      this.failureType = result;
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
 private dropDownGoodwillType() {
  this.goodwillWebService.dropDownGoodwillType().subscribe(res => {
    this.goodwillType = res;
  }, err => {
    console.log('err', err);
  });
 }

}
