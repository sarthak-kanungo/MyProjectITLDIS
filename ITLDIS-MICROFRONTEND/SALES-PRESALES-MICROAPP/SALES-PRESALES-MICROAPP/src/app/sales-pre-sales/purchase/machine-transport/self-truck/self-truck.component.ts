import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-self-truck',
  templateUrl: './self-truck.component.html',
  styleUrls: ['./self-truck.component.css']
})
export class SelfTruckComponent implements OnInit {
   @Input() selfTruckForm:FormGroup
  constructor() { }

  ngOnInit() {
    
  }

  fileSelectionChanges(event:any){

  }
}
