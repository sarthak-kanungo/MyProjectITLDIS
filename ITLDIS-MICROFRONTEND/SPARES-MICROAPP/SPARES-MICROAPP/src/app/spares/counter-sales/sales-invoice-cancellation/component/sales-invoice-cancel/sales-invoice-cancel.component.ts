import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sales-invoice-cancel',
  templateUrl: './sales-invoice-cancel.component.html',
  styleUrls: ['./sales-invoice-cancel.component.scss']
})
export class SalesInvoiceCancelComponent implements OnInit {


  refdocs: string[] = [
    'Sales Order', 'Job Card'
  ];
  saletypes: string[]= [
    'Workshop', 'Counter','Field','Others'
  ];

  pricings: string[]= [
    'MRP', 'MO Price','EO Price'
  ];
  transporters: string[]= [
    'Gati', 'Professional','DTDC','SRMT','Tirupati','Maruti','Others'
  ];

  transports: string[]= [
    'Air', 'Surface'
  ];

  custtypes: string[]=
  [
    'Existing','New','Retailers','Mechanic','Co-dealer','Branch'
  ];

  constructor() { }

  ngOnInit() {
  }

}
