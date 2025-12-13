import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-kai-branch-depot',
  templateUrl: './search-kai-branch-depot.component.html',
  styleUrls: ['./search-kai-branch-depot.component.scss']
})
export class SearchKaiBranchDepotComponent implements OnInit {
  branchCodes: string[] = [
    'Chennai', 'Pune',
  ];
  depots: string[] = [
    'Chennai', 'Odisha'
  ];
  kaiBranchDepotForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchkaiBranchDepotForm();
  }

  searchkaiBranchDepotForm() {
    this.kaiBranchDepotForm = this.fb.group({
      
      branchCode: ['', Validators.compose([])],
      depot: ['', Validators.compose([])],
    })
  }

}
