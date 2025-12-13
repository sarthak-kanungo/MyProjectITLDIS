import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-branch-sub-dealer',
  templateUrl: './search-branch-sub-dealer.component.html',
  styleUrls: ['./search-branch-sub-dealer.component.scss']
})
export class SearchBranchSubDealerComponent implements OnInit {
  categories: string[] = [
    'Branch', 'Sub-Dealer',
  ];
  branchSubDealerForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchbranchSubDealerForm();
  }

  searchbranchSubDealerForm() {
    this.branchSubDealerForm = this.fb.group({
      
      category: ['', Validators.compose([])],
      branchSubDealerCode: ['', Validators.compose([])],
    })
  }
}
