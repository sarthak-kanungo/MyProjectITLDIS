import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { BaseDto } from 'BaseDto';
import { Observable } from 'rxjs';
import { AutoDealerCodeSearch } from 'src/app/service/customer-service/job-card/domain/job-card-domain';
import { ServiceMonitoringBoardService } from 'src/app/service/report/service-monitoring-board/service-monitoring-board.service';
import { MrcSearchWebService } from '../../../machine-receipt-checking/component/mrc-search/mrc-search-web.service';
import { AutoChassisNumber, InstallationNumber } from '../../domain/installation-domain';
import { InstallationCommonWebService } from '../../service/installation-common-web.service';
import { InstallationSearchWebService } from './installation-search-web.service';

@Component({
  selector: 'app-installation-search',
  templateUrl: './installation-search.component.html',
  styleUrls: ['./installation-search.component.scss'],
  providers: [InstallationSearchWebService,MrcSearchWebService,ServiceMonitoringBoardService]
})
export class InstallationSearchComponent implements OnInit,AfterViewInit {

  @Input() installationSearchForm: FormGroup
  chassisNoList$: Observable<Array<AutoChassisNumber>>
  installationNumber$: Observable<Array<InstallationNumber>>
  installationTypes: string[] = ['Delivery Installation', 'Field Installation']
  minDate: Date;
  newdate= new Date()
  maxDate: Date
  dealercodeList: BaseDto<Array<AutoDealerCodeSearch>>
  constructor(
    private installationCommonWebService: InstallationCommonWebService,
    private installationSearchWebService : InstallationSearchWebService,
    
 private mrcSearchService:  MrcSearchWebService,
 private smbService: ServiceMonitoringBoardService,
  ) { }

  ngOnInit() {
    this.getChassisNoList()
    this.getInstallationNoList()
    this.getDealer()

  }
  ngAfterViewInit() {
   
  }
  private getDealer(){
    this.installationSearchForm.controls.dealerShip.valueChanges.subscribe((res) => {
      if (res) {
        // if (typeof res === 'string') {
        //   this.jobCardForm.controls.dealerName.patchValue('');
        // }
        this.smbService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response as BaseDto<Array<AutoDealerCodeSearch>>
        })
      }
    })
  }
  private getChassisNoList() {
    this.installationSearchForm.get('chassisNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const chassisNo = typeof valueChange == 'object' ? valueChange.code : valueChange
        this.autoChassisNo(chassisNo)
      }
    })
  }
  dealerSelect(event:any){

  }
  autoChassisNo(chassisNo: string) {
    this.chassisNoList$ = this.installationSearchWebService.chassisNoAutoForSearch(chassisNo)
  }

  private getInstallationNoList() {
    this.installationSearchForm.get('installationNo').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const installationNo = typeof valueChange == 'object' ? valueChange.installationNumber : valueChange
        this.autoInstallationNo(installationNo)
      }
    })
  }

  autoInstallationNo(installationNo: string) {
    this.installationNumber$ = this.installationSearchWebService.installationNumberAuto(installationNo)
  }
  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }

  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.installationSearchForm.get('toDate').value > this.maxDate)
        this.installationSearchForm.get('toDate').patchValue(this.maxDate);
    }
  }

}
