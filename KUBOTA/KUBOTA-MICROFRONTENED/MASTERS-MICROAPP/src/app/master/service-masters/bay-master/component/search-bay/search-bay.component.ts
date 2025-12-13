import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-bay',
  templateUrl: './search-bay.component.html',
  styleUrls: ['./search-bay.component.scss']
})
export class SearchBayComponent implements OnInit {

  bayTypes : string[] = [
    'Bay A', 'Bay B', 'Bay C'
  ]

  bayForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchbayForm();
  }

  searchbayForm() {
    this.bayForm = this.fb.group({
      
      bayType: ['', Validators.compose([])],
      bayCode: ['', Validators.compose([])],
    })
  }

}
