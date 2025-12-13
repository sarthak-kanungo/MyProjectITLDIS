import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-branch-transfer-req-item-details',
  templateUrl: './branch-transfer-req-item-details.component.html',
  styleUrls: ['./branch-transfer-req-item-details.component.scss']
})
export class BranchTransferReqItemDetailsComponent implements OnInit {

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
    itemNo: this.fb.control(''),
    itemDesc: this.fb.control(''),
    reqQuantity: this.fb.control(''),
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