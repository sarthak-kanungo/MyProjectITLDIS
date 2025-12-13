import { Component, OnInit, Input, AfterViewChecked } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatRadioChange } from '@angular/material/radio';
import { KisPagePresenter } from '../kai-inspection-sheet-create-page/kai-inspection-sheet-create-page.presenter';
import { Operation } from '../../../../../utils/operation-util';

@Component({
  selector: 'app-kai-inspection-sheet-material-details',
  templateUrl: './kai-inspection-sheet-material-details.component.html',
  styleUrls: ['./kai-inspection-sheet-material-details.component.scss']
})
export class KaiInspectionSheetMaterialDetailsComponent implements OnInit, AfterViewChecked  {
  @Input() materialDetailForm: FormArray;
  materialDetailHeading = ['Sl.No.', 'Item No', 'Item Desc', 'Unit Price', 'Quantity', 'Value', 'Approved Qty', 'Approved Value', 'Inspection Remarks', 'Claimable from Vendor', 'Key Part Number'];
  radioBtn = ['Key Part Number'];
  kQuickForm: FormGroup;
  radioBtnValue: string;
  operation: string;
  constructor(
    private kisPagePresenter: KisPagePresenter,
  ) { }

  ngOnInit() {
    this.kQuickForm = this.kisPagePresenter.kQuickForm;
    this.operation = this.kisPagePresenter.operation;
  }

  ngAfterViewChecked(){
      this.materialDetailForm.controls.forEach(elt => {
          elt.get('approvedQty').valueChanges.subscribe( qty => {
              let approvedValue:number = 0;
              if(elt.get('approvedQty').value){
                  approvedValue = elt.get('unitPrice').value * elt.get('approvedQty').value;   
              }   
              elt.get('approvedVal').patchValue(approvedValue);  
          })
      });
  }
  
  selectedRadBtn(evt: MatRadioChange, list: FormGroup) {
    console.log(evt,'evt')
    this.radioBtnValue = evt.value;
    console.log(this.radioBtnValue,'111')
    this.materialDetailForm.controls.forEach(elt => {
      if(elt.get('keyPartNumber'))
        elt.value.id == list.value.id ? elt.get('keyPartNumber').patchValue(true) : elt.get('keyPartNumber').patchValue(false);
    })
  }

}

