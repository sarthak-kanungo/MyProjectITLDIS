import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-self-run',
  templateUrl: './self-run.component.html',
  styleUrls: ['./self-run.component.css']
})
export class SelfRunComponent implements OnInit {
   @Input() selfRunForm:FormGroup
  constructor() { }

  ngOnInit() {
  }

  fileSelectionChanges(event:any){

  }
}
