import { Component, OnInit, Input } from '@angular/core';
import { LogSheetPagePresenter } from '../log-sheet-page/log-sheet-page.presenter';
import { FormArray, FormGroup } from '@angular/forms';
import { LogSheetFailureWebService } from './log-sheet-failure-web.service'
import { AutoCompleteResult, PartDetails } from '../../domain/log-sheet.domain';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { FailurePart } from '../../../product-concern-report/domain/product-concern-report.domain';
import { FailurePartsWebService } from '../../../product-concern-report/component/failure-parts/failure-parts-web.service';
import { MatRadioChange } from '@angular/material/radio';
@Component({
  selector: 'app-log-sheet-failure-parts',
  templateUrl: './log-sheet-failure-parts.component.html',
  styleUrls: ['./log-sheet-failure-parts.component.scss'],
  providers: [LogSheetFailureWebService,FailurePartsWebService]
})
export class LogSheetFailurePartsComponent implements OnInit {
  @Input() failurePartForm: FormArray; 
  @Input()  machineMasterId: any
  failurePartHeading = ['SL.No', 'Part No.', 'Description', 'Quantity','Failure Code','Failure Description','Key Failure Part'];
  isShow: boolean;
  autoCompleteData:AutoCompleteResult;
  public failureCode: FailurePart[];
  private selectedCode: string;
  hidden:boolean=true
  radioBtnValue: string;
  radioBtn = ['Key Part Number'];
  private selectedForm: FormGroup;
   code: PartDetails;
  constructor(
    private service:FailurePartsWebService,
    private logSheetPagePresenter: LogSheetPagePresenter,
    private logSheetFailureService: LogSheetFailureWebService
    
  ) { }

  ngOnInit() {
   this.formValueChanges();
//  this.autoCompletePartNumber()

  }
 

  private formValueChanges() {
    
    this.failurePartForm.controls.forEach(elt => {
      elt.get('partNo').disabled? this.isShow = false : this.isShow = true;
      elt.get('partNo').valueChanges.subscribe(val => {
         if (val) {
        this.autoCompletePartNumber(val);
        }
      })
    })
    this.failurePartForm.controls.forEach(elt => {
      elt.get('failureCode').disabled? this.isShow = false : this.isShow = true;
      elt.get('failureCode').valueChanges.subscribe(val => {
        if (val) {
        this.getPartFailureCodeByCode(val);
        }
      })
    })
    
    
    this.failurePartForm.controls.forEach(element => {
      element.get('failureCode').valueChanges.subscribe(val => {
        // console.log('this.selectedCode', this.selectedCode)
      });
      
      element.get('failureCode').statusChanges.subscribe(sts => {
        if(sts == 'INVALID') {
          element.get('failureDescription').reset();
        }
      })
      // console.log("ele==================================",element);
      element.get('approvedQuantity').valueChanges.subscribe(val => {
        if(val > element.get('claimQuantity').value) {
          element.get('approvedQuantity').patchValue(element.get('claimQuantity').value); 
        }
      })
    });
  }
  functionName(event:any,i){
    // console.log("i=========",i);
    // console.log(event,'event')
    this.autoCompletePartNumber(this.failurePartForm.controls[i].value.partNo);
    // this.formValueChanges()

  }
  focusApi(event:any,i){
    this.getPartFailureCodeByCode(this.failurePartForm.controls[i].value.failureCode);
  }

  // checking(event,i){
  //   console.log(this.failurePartForm.controls[i].value.partNo);
    
    
  // }
 

  addRow() {
    
   
    this.logSheetPagePresenter.addRowInFailurePart();
    // console.log('calling ad row')
    
  }
  deleteRow() {
    this.logSheetPagePresenter.deleteRowInFailurePart();
  }
  private autoCompletePartNumber(txt: string) {
    if(txt && typeof txt == 'string'){
      this.logSheetFailureService.autoCompletePartNumber(txt).subscribe(res => {
        this.autoCompleteData = res;
  // console.log('****************ankit autocomplete',this.autoCompleteData);
      })
      
    }
  }
  private getPartFailureCodeByCode(txt: string) {
    if(txt && typeof txt == 'string'){
      this.logSheetFailureService.getPartFailureCodeByCode(txt).subscribe(res => {

        this.code= res;

      //  this.code.get('description').patchValue(res.itemDescription)
      })
    }
  }

  selectedRadBtn(evt: MatRadioChange,list:FormGroup) {
    this.radioBtnValue = evt.value;
    this.failurePartForm.controls.forEach(elt => {
      if(elt.get('keyPartNumber'))
        elt.value == list.value ? elt.get('keyPartNumber').patchValue(true) : elt.get('keyPartNumber').patchValue(false);
    })
  }
  selectedOption(evt: MatAutocompleteSelectedEvent, list: FormGroup) {
    
    if(evt) {
      this.selectedForm = list;
      this.selectedCode = evt.option.value.failureCode;
      this.selectedForm.get('failureDescription').patchValue( evt.option.value.failureDescription);
    }
  }
  getPartDetailsByPartNumber(evt: MatAutocompleteSelectedEvent, list: FormGroup) {
    this.logSheetFailureService.getPartDetailsByPartNumber(evt.option.value.value).subscribe(res=> {
      list.get('description').patchValue(res.itemDescription)
    }, err => {
      console.log('err', err);
    });
  }
 
  displayCodeFn(obj) {
   
    if(typeof(obj) === 'string'){
      // console.log("ibnside if");
      return obj ? obj : "";
    }
    else {
      // console.log("ibnside else");
      return obj.value; 
    }   
  }
  displayCodeFns(obj) {
  
    if(typeof(obj)==='string'){
      return obj?obj:'';
    }
    if(typeof(obj)===undefined){
      return obj.failureCode
    }
    else{
   return obj.failureCode;  
    }
    
  }

}
