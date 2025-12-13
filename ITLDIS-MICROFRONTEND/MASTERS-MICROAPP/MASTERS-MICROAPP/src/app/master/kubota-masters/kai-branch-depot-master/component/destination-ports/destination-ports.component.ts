import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-destination-ports',
  templateUrl: './destination-ports.component.html',
  styleUrls: ['./destination-ports.component.scss']
})
export class DestinationPortsComponent implements OnInit {
  ports: string[] = [
    'Chennai', 'Vizag', 'Mumbai',   
  ];

  constructor() { }

  ngOnInit() {
  }

}
