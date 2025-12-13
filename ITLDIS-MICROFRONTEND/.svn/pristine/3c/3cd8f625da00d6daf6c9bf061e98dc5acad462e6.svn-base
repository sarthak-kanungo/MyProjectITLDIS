import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from './../../../../../confirmation-box/confirmation-box.component';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-spare-delivery-challan',
  templateUrl: './spare-delivery-challan.component.html',
  styleUrls: ['./spare-delivery-challan.component.scss']
})
export class SpareDeliveryChallanComponent implements OnInit {
  isEdit: boolean;
  refdocs: string[] = [
    'Sales Order', 'Job Card'
  ];
  modes: string[] = [
    'Air', 'Surface'
  ];
  transporters: string[] = [
    'Gati', 'Professional', 'DTDC', 'SRMT', 'Tirupati', 'Maruti', 'Others'
  ];
  pricings: string[] = [
    'MRP', 'MO Price', 'EO Price'
  ];
  salestypes: string[] = [
    'Workshop', 'Counter', 'Field', 'Others'
  ];

  deliveryChllanForm : FormGroup;
  constructor(public dialog: MatDialog,private fb:FormBuilder) { }

  ngOnInit() {
    this.createdeliveryChllanForm()
  }

  createdeliveryChllanForm(){
    this.deliveryChllanForm=this.fb.group({
      deliveryChallanNo:['',Validators.compose([])],
      date:[{value:'', disabled:true},Validators.compose([])],
      customerType:[{value:'', disabled:true},Validators.compose([])],
      referenceDocument:['',Validators.compose([])],
      salesType:['',Validators.compose([])],
      modeofTransport:['',Validators.compose([])],
      customerName:['',Validators.compose([])],
      customerAddress1:['',Validators.compose([])],
      customerAddress2:['',Validators.compose([])],
      referenceDocumentDate:[{value:'', disabled:true},Validators.compose([])],
      transporter:['',Validators.compose([])],
      cityVillage:['',Validators.compose([])],
      tehsil:['',Validators.compose([])],
      district:['',Validators.compose([])],
      pricing:['',Validators.compose([])],
      lrNo:['',Validators.compose([])],
      state:['',Validators.compose([])],
      pinCode:['',Validators.compose([])],
      mobileNo:['',Validators.compose([])]
      
      
    })
  }
  
  keyPress(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();

    }
  }
  
  
 
  validateForm() {
    this.openConfirmDialog();
  }

  

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Delivery Challan?';
    if (this.isEdit) {
      message = 'Do you want to update Delivery Challan?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

   /* dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitData();
      }
    });*/
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }

}
