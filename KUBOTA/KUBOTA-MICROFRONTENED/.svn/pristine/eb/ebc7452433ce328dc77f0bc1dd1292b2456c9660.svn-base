import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  getColor(name) {
    switch (name) {
      case "Machine PO":
        return "rgb(47, 62, 158)";
      case "Machine GRN":
        return "rgb(210, 46, 46)";
      case "Machine Stock":
        return "rgb(55, 141, 59)";
      case "Machine Desc. Claim":
        return "rgb(0, 150, 166)";
      case "Machine Transfer Request":
        return "rgb(96, 96, 96)";
      case "Enquiries":
        return "rgb(244, 123, 0)";
      case "Machine Allotment":
        return "#212F3C";
      case "Deliveries for the Month":
        return "#0B5345";
      case "DC Cancellation Request":
        return "rgb(96, 96, 96)";
      case "Pending Invoice":
        return "rgb(244, 123, 0)";
      case "Invoice Cancellation Request":
        return "#3e4fb4";
      case "Machine Branch Transfer Request":
        return "#B7950B";
      case "Machine Branch Transfer Receipt":
        return "rgb(210, 46, 46)";
      case "Marketing Activity Proposal":
        return "#CD5C5C";
      case "Marketing Activity Approval":
        return "#154360";
      case "Marketing Activity Report":
        return "rgb(244, 123, 0)";
      case "Marketing Activity Claim":
        return "rgb(55, 141, 59)";
      case "Marketing Activity Claim Approval":
        return "#34495E";
      case "Sales Incentive Schemes Approval":
        return "#784212";
      case "Sales Incentive Claim":
        return "rgb(0, 150, 166)";
      case "Sales Incentive Claim Approval":
        return "rgb(96, 96, 96)";
    }

  }

  public purchase = [
    { "pending": 'Pending - 2', "approved": 'Approved - 5', "name": 'Machine PO' },
    { "pending": 'Pending - 2', "name": 'Machine GRN' },
    { "pending": 'Total - 34', "name": 'Machine Stock' },
    { "pending": 'Pending - 2', "approved": 'Approved - 5', "name": 'Machine Desc. Claim' },
    { "pending": 'Pending - 2', "approved": 'Approved - 5', "name": 'Machine Transfer Request' },
  ];

  public enquiry = [
    { "hot": 'Hot - 2', "warm": 'Warm - 5', "cold": 'Cold - 20', "name": 'Enquiries' },
  ];

  public sales = [
    { "pending": 'Pending - 2', "name": 'Machine Allotment' },
    { "pending": 'Pending - 122', "name": 'Deliveries for the Month' },
    { "pending": 'Pending - 2', "approved": 'Approved - 5', "name": 'DC Cancellation Request' },
    { "pending": 'Total - 2', "name": 'Pending Invoice' },
    { "pending": 'Pending - 2', "approved": 'Approved - 5', "name": 'Invoice Cancellation Request' },
  ];

  public branch = [
    { "pending": 'Pending - 2', "name": 'Machine Branch Transfer Request' },
    { "pending": 'Pending - 2', "name": 'Machine Branch Transfer Receipt' },
  ];

  public activity = [
    { "pending": 'Pending - 2', "approved": 'Approved - 5', "name": 'Marketing Activity Proposal' },
    { "pending": 'Pending - 2', "name": 'Marketing Activity Approval' },
    { "pending": 'Pending - 2', "name": 'Marketing Activity Report' },
    { "pending": 'Total - 2', "approved": 'Approved - 5', "name": 'Marketing Activity Claim' },
    { "pending": 'Pending - 2', "name": 'Marketing Activity Claim Approval' },
  ];

  public schemes = [
    { "pending": 'Pending - 2', "name": 'Sales Incentive Schemes Approval' },
    { "pending": 'Pending - 2', "approved": 'Approved - 5', "name": 'Sales Incentive Claim' },
    { "pending": 'Pending - 2', "name": 'Sales Incentive Claim Approval' },
  ];


  constructor() { }

  ngOnInit() {
      
  }

}
