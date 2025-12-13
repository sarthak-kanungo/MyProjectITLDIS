import { Component, OnInit } from '@angular/core';
import { ServiceClaimService } from '../../pages/service-claim.service';

@Component({
  selector: 'app-claim-machine-service-details',
  templateUrl: './claim-machine-service-details.component.html',
  styleUrls: ['./claim-machine-service-details.component.scss']
})
export class ClaimMachineServiceDetailsComponent implements OnInit {
  machineDetailsTable = [];
  serviceDetailsTable = [];
  totalNoOfMachine:number = 0
  totalNoOfService:number = 0
  constructor(private claimService: ServiceClaimService) { }

  ngOnInit() {
    this.claimService.productServiceBehaviourSubject.subscribe(data => {
      if(data){
        this.machineDetailsTable = data.filter(d => d.ctype == 'Product');
        this.serviceDetailsTable = data.filter(d => d.ctype == 'Service');
        this.machineDetailsTable && this.machineDetailsTable.forEach(dtl => this.totalNoOfMachine = this.totalNoOfMachine + dtl.noOfMachine)
        this.serviceDetailsTable && this.serviceDetailsTable.forEach(dtl => this.totalNoOfService = this.totalNoOfService + dtl.noOfMachine)
      }
    })
  }

}
