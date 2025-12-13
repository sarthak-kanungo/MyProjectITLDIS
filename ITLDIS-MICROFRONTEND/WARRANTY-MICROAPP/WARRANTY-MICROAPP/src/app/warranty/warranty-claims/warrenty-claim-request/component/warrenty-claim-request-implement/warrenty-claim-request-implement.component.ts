import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';

@Component({
  selector: 'app-warrenty-claim-request-implement',
  templateUrl: './warrenty-claim-request-implement.component.html',
  styleUrls: ['./warrenty-claim-request-implement.component.scss']
})
export class WarrentyClaimRequestImplementComponent implements OnInit {
  @Input() implementForm: FormArray;
  implementList = [{ val1: '1', val2: 'CAT-II', val3: 'METHOD-3', val4: '490', val5: 'YU-908', val6: '3/4', val7: '50', val8: '45', val9: '7' }];
  implementHeading = [];
  constructor() { }

  ngOnInit() {
    this.implementHeading = ['SL.NO', 'IMPLEMENT CATEGORY', 'IMPLEMENT', 'IMPLEMENT MAKE', 'IMPLEMENT SL.NO.','GEAR RATIO', 'ENGINE RPM', '% OF USAGE', 'USAGE DURING FAILURE'];
  }

}
