import { Component, OnInit } from '@angular/core';
import { ServiceClaimService } from '../service-claim.service';

@Component({
  selector: 'app-service-claim-create',
  templateUrl: './service-claim-create.component.html',
  styleUrls: ['./service-claim-create.component.scss'],
  providers: [ServiceClaimService]
})
export class ServiceClaimCreateComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
