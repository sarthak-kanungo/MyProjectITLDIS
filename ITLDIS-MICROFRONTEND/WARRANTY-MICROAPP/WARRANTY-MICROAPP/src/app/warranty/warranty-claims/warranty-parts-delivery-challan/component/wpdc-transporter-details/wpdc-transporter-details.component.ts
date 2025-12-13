import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-wpdc-transporter-details',
  templateUrl: './wpdc-transporter-details.component.html',
  styleUrls: ['./wpdc-transporter-details.component.scss']
})
export class WpdcTransporterDetailsComponent implements OnInit {
  @Input() transporterDetailForm: FormGroup;
  today = new Date();
  constructor() { }

  ngOnInit() {
    this.today.setDate(this.today.getDate());
  }

}
