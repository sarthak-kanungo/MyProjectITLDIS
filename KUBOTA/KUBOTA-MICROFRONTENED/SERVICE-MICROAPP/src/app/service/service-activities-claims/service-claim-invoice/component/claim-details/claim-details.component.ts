import { Component, Input, OnInit } from '@angular/core';
import { ServiceClaimInvoiceCreateService } from '../../pages/service-claim-invoice-create/service-claim-invoice-create.service';

@Component({
  selector: 'app-claim-details',
  templateUrl: './claim-details.component.html',
  styleUrls: ['./claim-details.component.scss']
})
export class ClaimDetailsComponent implements OnInit {
  headsTable = []
  totalInWords:string=''
  totalAmount:number=0;
  totalNetAmount:number=0;
  totalQty:number=0;
  totalCgstAmount:number=0;
  totalSgstAmount:number=0;
  totalIgstAmount:number=0;
  constructor(private createService: ServiceClaimInvoiceCreateService) { }

  ngOnInit() {

    this.createService.viewInvoiceSubject.subscribe(resultSet => {
      if(resultSet){
        this.headsTable = resultSet['Details'];
        this.totalAmount = resultSet['Details'][0]['totalAmount']
        this.totalInWords = resultSet['Details'][0]['totalInWords']
        this.totalNetAmount = resultSet['Details'][0]['totalNetAmount']
        this.totalQty = resultSet['Details'][0]['totalQty']
        this.totalCgstAmount = resultSet['Details'][0]['totalCgstAmount']
        this.totalSgstAmount = resultSet['Details'][0]['totalSgstAmount']
        this.totalIgstAmount = resultSet['Details'][0]['totalIgstAmount']
      }
    });
    
  }

}
