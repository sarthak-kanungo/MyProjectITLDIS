import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Injectable()
export class SearchActivityProposalService {
  activityProposalList : FormGroup;
  constructor(private fb: FormBuilder) { }

  searchActivityProposalList(){
    this.activityProposalList=this.fb.group({
      activityNo:[''],
      activityType:[''],
      activityStatus:[''],
      activityFromDate:[''],
      activityToDate:[''],
    })
    return this.activityProposalList;
  }
}
