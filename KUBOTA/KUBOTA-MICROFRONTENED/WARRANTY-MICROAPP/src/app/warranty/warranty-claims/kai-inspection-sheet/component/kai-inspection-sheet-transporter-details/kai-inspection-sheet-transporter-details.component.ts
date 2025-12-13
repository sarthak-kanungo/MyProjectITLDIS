import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-kai-inspection-sheet-transporter-details',
  templateUrl: './kai-inspection-sheet-transporter-details.component.html',
  styleUrls: ['./kai-inspection-sheet-transporter-details.component.scss']
})
export class KaiInspectionSheetTransporterDetailsComponent implements OnInit {
  @Input() transporterDetailForm: FormGroup;
  constructor() { }

  ngOnInit() {
  }

}
