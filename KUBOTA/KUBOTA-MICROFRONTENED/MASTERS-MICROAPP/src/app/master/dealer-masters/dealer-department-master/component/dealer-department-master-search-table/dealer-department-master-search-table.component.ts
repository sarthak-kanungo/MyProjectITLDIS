import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dealer-department-master-search-table',
  templateUrl: './dealer-department-master-search-table.component.html',
  styleUrls: ['./dealer-department-master-search-table.component.scss']
})
export class DealerDepartmentMasterSearchTableComponent implements OnInit {

  isView: boolean;

  constructor() { }

  ngOnInit() {
    this.isView = true
  }

}
