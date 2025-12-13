import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-manage-organization-hierarchy-search',
  templateUrl: './manage-organization-hierarchy-search.component.html',
  styleUrls: ['./manage-organization-hierarchy-search.component.scss']
})
export class ManageOrganizationHierarchySearchComponent implements OnInit {

  isKai:boolean
  searchMIForm:FormGroup
  constructor() { }

  ngOnInit() {
  }

}
