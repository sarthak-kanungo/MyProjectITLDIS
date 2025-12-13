import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-transporter-master',
  templateUrl: './search-transporter-master.component.html',
  styleUrls: ['./search-transporter-master.component.scss']
})
export class SearchTransporterMasterComponent implements OnInit {

  transporterForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchtransporterForm();
  }

  searchtransporterForm() {
    this.transporterForm = this.fb.group({
      
      transporterCode: ['', Validators.compose([])],
      transporterName: ['', Validators.compose([])],
    })
  }
}
