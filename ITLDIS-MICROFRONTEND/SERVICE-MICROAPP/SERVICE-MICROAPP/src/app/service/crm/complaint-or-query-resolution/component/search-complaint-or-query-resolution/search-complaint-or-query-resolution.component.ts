import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { SearchComplaintOrQueryResolutionService } from './search-complaint-or-query-resolution.service';
import { MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-search-complaint-or-query-resolution',
  templateUrl: './search-complaint-or-query-resolution.component.html',
  styleUrls: ['./search-complaint-or-query-resolution.component.css']
})
export class SearchComplaintOrQueryResolutionComponent implements OnInit {

  @Input() searchcomplaintOrQueryResolutionForm: FormGroup
  @Input() isAdvanceSearch: boolean
  statusType = ['All', 'Open', 'Close'];
  complaintType=['All','Survey','TollFree'];
  departmentType=['All','Sales','Service','Spares','QA']
  constructor(
    private searchComplaintOrQueryResolutionService: SearchComplaintOrQueryResolutionService
  ) { }

  ngOnInit() {
    console.log('--Deler',this.searchcomplaintOrQueryResolutionForm)
  }

}
