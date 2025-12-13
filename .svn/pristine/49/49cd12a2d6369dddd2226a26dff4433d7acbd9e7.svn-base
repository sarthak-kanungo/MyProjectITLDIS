import { Component, OnInit, Input } from '@angular/core';
import { RetroFitmentCampaignSearchWebService } from './retro-fitment-campaign-search-web.service';
import { FormGroup } from '@angular/forms';
import { RfcSearchStatus, AutoCompleteResult } from '../../domain/retro-fitment-campaign.domain';
import { MatDatepickerInput } from '@angular/material';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';

@Component({
  selector: 'app-retro-fitment-campaign-search',
  templateUrl: './retro-fitment-campaign-search.component.html',
  styleUrls: ['./retro-fitment-campaign-search.component.scss'],
  providers: [RetroFitmentCampaignSearchWebService, PcrPageWebService]
})
export class RetroFitmentCampaignSearchComponent implements OnInit {
  @Input() searchForm: FormGroup;
  status: RfcSearchStatus;
  autoCompleteData: AutoCompleteResult;
  newdate = new Date()
  fromDates: Date
  toDate: Date
  constructor(
    private retroFitmentCampaignSearchWebService: RetroFitmentCampaignSearchWebService,
    private pcrPageWebService: PcrPageWebService,
  ) { }

  ngOnInit() {
    this.searchRetrofitmentStatus();
    this.formValueChanges();
    this.getSystemDate();
  }
  ngAfterViewInit() {
    
  }
  private formValueChanges() {
    this.searchForm.get('retrofitmentNumber').valueChanges.subscribe(val => {
      if (val) {
        this.searchRetrofitmentNo(val);
      }
    });
  }

  private searchRetrofitmentStatus() {
    this.retroFitmentCampaignSearchWebService.searchRetrofitmentStatus().subscribe(res =>{
      this.status = res;
    }, err => {
      console.log('err', err);
    });
  }

  private searchRetrofitmentNo(txt: string) {
    this.retroFitmentCampaignSearchWebService.searchRetrofitmentNo(txt).subscribe(res => {
      this.autoCompleteData = res;
    });
  }

  private getSystemDate() {
    this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
      this.toDate = new Date(res);
    });
  }

  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.fromDates = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.toDate = this.newdate;
      else
        this.toDate = maxDate;
      if (this.searchForm.get('toDate').value > this.toDate)
        this.searchForm.get('toDate').patchValue(this.toDate);
    }
  }

}
