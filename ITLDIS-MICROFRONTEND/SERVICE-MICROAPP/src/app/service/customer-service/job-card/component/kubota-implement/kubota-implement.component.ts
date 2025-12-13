import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-itldis-implement',
  templateUrl: './itldis-implement.component.html',
  styleUrls: ['./itldis-implement.component.css']
})
export class itldisImplementComponent implements OnInit {
@Input() implementListForShowData:any;
  constructor() { }

  ngOnInit() {
   
  }
  ngOnChanges(){
  // this.implementListForShowData;
  //  this.implementListForShowData.forEach(res=>{
  //  })
  }
}
