import { Component, OnInit } from '@angular/core';
import { DeAllotmentSearchService } from './de-allotment-search.service';

@Component({
  selector: 'app-de-allotment-search',
  templateUrl: './de-allotment-search.component.html',
  styleUrls: ['./de-allotment-search.component.scss'],
  providers:[DeAllotmentSearchService]
})
export class DeAllotmentSearchComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
