import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { CreateMachineTransferRequestService } from '../../pages/create-machine-transfer-request/create-machine-transfer-request.service';

@Component({
  selector: 'app-machine-transfer-req-item-details',
  templateUrl: './machine-transfer-req-item-details.component.html',
  styleUrls: ['./machine-transfer-req-item-details.component.scss']
})
export class MachineTransferReqItemDetailsComponent implements OnInit {

  totalQty = 0
  show : boolean;
  @Input()
  isView:boolean
  transferItemDetailsForm:FormArray;
  itemNoList:any[]

  constructor(private fb: FormBuilder,
    private createMachineTransferRequestService: CreateMachineTransferRequestService) { }

  ngOnInit() {
    this.transferItemDetailsForm = this.createMachineTransferRequestService.getTransferItemDetailsForm();
    if(!this.isView){
      this.addRow();  
    }
  }

  addRow(){
    const fg:FormGroup = this.createMachineTransferRequestService.addRow();
    this.machineTransferTableValueChanges(fg);
  }

  deleteRow(){
    this.createMachineTransferRequestService.deleteRow();
    this.calculateTotalQty(true);
  }

  calculateTotalQty(deleteFlag?:boolean){
      this.totalQty = 0;
      if(deleteFlag){
        this.selectedItemsArray = [];
      }
      this.transferItemDetailsForm.controls.forEach(element => {
        this.totalQty = this.totalQty + (element.get('quantity').value==null || (typeof element.get('quantity').value == 'string')?0:parseInt(element.get('quantity').value))

        if(deleteFlag){
          typeof element.get('itemNo').value==='object' && this.selectedItemsArray.push(element.get('itemNo').value.itemNo);
        }

      });
      this.createMachineTransferRequestService.totalQtySubject.next(this.totalQty);
  }
  private machineTransferTableValueChanges(fg:FormGroup) {
    
    fg.controls.quantity.valueChanges.subscribe(values => {
      this.calculateTotalQty();
    });
    fg.controls.itemNo.valueChanges.subscribe(value => {
       if(value){
        fg.controls.itemNo.setErrors({'selectFromList':'Select From List'}); 
        if(typeof value === 'object'){
          value = value['itemNo'];
          fg.controls.itemNo.setErrors(null);
        }
        this.createMachineTransferRequestService.searchByItemNumber(value).subscribe(res => {
          this.itemNoList =  res['result'].filter(item => this.selectedItemsArray.indexOf(item.itemNo) < 0)
        });
      }else{
        fg.controls.itemNo.setErrors(null);
      }
    });
  }
  selectedItemsArray:string[] = [];

  optionSelectedItem(event, fg:FormGroup) {
    this.getDetailsFromItemNumbers(event, fg);
    this.selectedItemsArray.push(fg.get('itemNo').value['itemNo']);
  }

  private getDetailsFromItemNumbers(event, fg:FormGroup) {
    this.createMachineTransferRequestService.getItemDetailsFromItemNumber(event.option.value.itemNo).subscribe(res => {
      fg.patchValue(res['result']);
    })
  }

  displayFnItemNo(itemNo: any) {
    if (typeof itemNo === 'string') {
      return itemNo;
    }
    return itemNo ? itemNo.itemNo : undefined
  }

}
