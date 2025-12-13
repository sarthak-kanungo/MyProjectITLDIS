import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-new-parts-claim-table',
  templateUrl: './new-parts-claim-table.component.html',
  styleUrls: ['./new-parts-claim-table.component.scss']
})
export class NewPartsClaimTableComponent implements OnInit {

  itemDetailsFrom: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {

    this.createitemDetailsFrom()

    this.itemDetailsFrom.valueChanges.subscribe(frm => console.log(frm))
  }


createitemDetailsFrom() {
  this.itemDetailsFrom = this.fb.group({
    itemDetails: this.fb.array([])
  });
}

 itemDetailsRow() {
  return this.fb.group({
    itemNo: this.fb.control({value:'', disabled: true}),
    itemDesc: this.fb.control({value:'', disabled: true}),
    invoiceQuantity: this.fb.control({value:'', disabled: true}),
    receiptQuantity:this.fb.control({value:'', disabled: true}),
    shortQty:this.fb.control({value:'', disabled: true}),
    damageQty:this.fb.control({value:'', disabled: true}),
    excessQty:this.fb.control({value:'', disabled: true}),
    returnQty:this.fb.control(''),
    value:this.fb.control({value:'', disabled: true}),
    remarks:this.fb.control(''),
    acceptedQty:this.fb.control({value:'', disabled: true}),
    reasons:this.fb.control({value:'', disabled: true}),
    isSelected: this.fb.control(false)
  })
}


addRow() {
  let itemDetails = this.itemDetailsFrom.controls.itemDetails as FormArray
  console.log("addRow itemDetails ", itemDetails);
  itemDetails.push(this.itemDetailsRow())
}


deleteRow() {
  let itemDetails = this.itemDetailsFrom.controls.itemDetails as FormArray;
  let nonSelected = itemDetails.controls.filter( machinery => !machinery.value.isSelected )
  itemDetails.clear()
  nonSelected.forEach(el => itemDetails.push(el) )
}

}