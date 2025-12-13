import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ServiceClaimInvoiceCreateService } from './service-claim-invoice-create.service';

@Component({
  selector: 'app-service-claim-invoice-create',
  templateUrl: './service-claim-invoice-create.component.html',
  styleUrls: ['./service-claim-invoice-create.component.scss'],
  providers: [ServiceClaimInvoiceCreateService]
})
export class ServiceClaimInvoiceCreateComponent implements OnInit {

  resultSet:any=null;

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router,
    private createService : ServiceClaimInvoiceCreateService,
    private toastr : ToastrService) { 

    }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(param => {
      const id = atob(param.get('id'));
      this.getInvoiceDetails(id);
    });
  }

  getInvoiceDetails(id){
    this.createService.getInvoiceDetails(id).subscribe(response => {
      if(response){
        this.resultSet = response['result'];
        this.createService.viewInvoiceSubject.next(this.resultSet);
      }else { 
        this.toastr.error('Error generated while fetching details','View Data Failed')
      }
    })
  }

}
