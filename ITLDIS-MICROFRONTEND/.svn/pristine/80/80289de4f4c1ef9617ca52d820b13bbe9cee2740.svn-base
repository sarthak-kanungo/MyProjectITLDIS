import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { FailurePart } from '../../domain/product-concern-report.domain';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { FailurePartsWebService } from './failure-parts-web.service';
import { PcrPagePresenter } from '../pcr-page/pcr-page.presenter';
@Component({
  selector: 'app-failure-parts',
  templateUrl: './failure-parts.component.html',
  styleUrls: ['./failure-parts.component.scss'],
  providers: [FailurePartsWebService]
})
export class FailurePartsComponent implements OnInit {

  @Input() public failurePartForm: FormArray;
  @Input() machineMasterId: number;
  pcrNo: string;
  public failureCode: FailurePart[];
  private selectedCode: string;
  private selectedForm: FormGroup;
  operation: string;
  constructor(
    private failurePartsService: FailurePartsWebService,
    private activatedRoute: ActivatedRoute,
    private pcrPagePresenter: PcrPagePresenter,

  ) { }

  ngOnInit() {
    this.operation = this.pcrPagePresenter.operation;
    console.log('this.operation_failurePart', this.operation);
    this.activeRoute();
    this.formValueChanges();
  }

  private activeRoute() {
    this.activatedRoute.queryParams.subscribe(qParam => {
      if(qParam.pcrNo) {
        this.pcrNo = qParam.pcrNo;
      }
    });
  }

  private formValueChanges() {
    console.log('this.failurePartForm', this.failurePartForm)
    this.failurePartForm.controls.forEach(element => {
      element.get('failureCode').valueChanges.subscribe(val => {
        console.log('this.selectedCode', this.selectedCode);

        if(val) {
          this.autoCompleteFailurePart(val);
        }
      });
      element.get('failureCode').statusChanges.subscribe(sts => {
        if(sts == 'INVALID') {
          element.get('failureDescription').reset();
        }
      })
      element.get('approvedQuantity').valueChanges.subscribe(val => {
        if(val > element.get('claimQuantity').value) {
          element.get('approvedQuantity').patchValue(element.get('claimQuantity').value); 
        }
      })
    });
  }
 
  selectedOption(evt: MatAutocompleteSelectedEvent, list: FormGroup) {
    if(evt) {
      this.selectedForm = list;
      this.selectedCode = evt.option.value.code;
      this.selectedForm.get('failureDescription').patchValue( evt.option.value.description);
    }
  }

  autoCompleteFailurePart(txt: FailurePart | string) {
    txt = typeof txt == 'string'? txt : txt.code;
    this.failurePartsService.autoCompleteFailurePart(JSON.stringify(this.machineMasterId), txt).subscribe(res => {
      this.failureCode = res;
      // if(this.selectedCode == txt) {
      //   this.failureCode.forEach(elt => {
      //     this.selectedForm.get('failureDescription').patchValue(elt.description);
      //   })
      // }
    });
  }

  displayCodeFn(obj: FailurePart): string | number | undefined {
    return obj ? obj.code : undefined;
  }



}
