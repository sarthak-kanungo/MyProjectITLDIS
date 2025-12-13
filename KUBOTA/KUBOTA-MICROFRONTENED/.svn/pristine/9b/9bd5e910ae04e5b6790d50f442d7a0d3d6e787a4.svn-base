import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-add-bay',
  templateUrl: './add-bay.component.html',
  styleUrls: ['./add-bay.component.scss']
})
export class AddBayComponent implements OnInit {
  bayTypes : string[] = [
    'Bay A', 'Bay B', 'Bay C'
  ]
  bayDetailsForm: FormGroup;
  constructor( private fb: FormBuilder,public dialog: MatDialog) { }

  ngOnInit() {
    this.createbayDetailsForm();
  }

  createbayDetailsForm() {
    this.bayDetailsForm = this.fb.group({
      
      bayCode: ['', Validators.compose([])],
      bayName: ['', Validators.compose([])],
      bayType: ['', Validators.compose([])],
      effectiveFromdate: ['', Validators.compose([])],
      effectiveToDate: ['', Validators.compose([])],
     
    })
  }
}
