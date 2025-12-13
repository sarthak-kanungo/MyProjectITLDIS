import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-representative-details',
  templateUrl: './representative-details.component.html',
  styleUrls: ['./representative-details.component.scss']
})
export class RepresentativeDetailsComponent implements OnInit {

  @Input() representativesDetailsTable : FormGroup

  constructor() { }

  ngOnInit() {
  }
    
}
