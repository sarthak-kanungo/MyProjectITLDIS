import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';

@Component({
  selector: 'app-machine-detail',
  templateUrl: './machine-detail.component.html',
  styleUrls: ['./machine-detail.component.scss']

})
export class MachineDetailComponent implements OnInit {

  @Input()
  machineDetailsForm:FormGroup
  chassisList:any
  customerId:number;
  @Input() showSalesFormData:any
  @Input()
  isView:boolean
  @Input() viewPostSalesfeedback:any
  @Input() showAllServiceFeedbackForm:any
  @Input() viewPostServicefeedback:any
  constructor(private dealerCustomerCareCallPresenter:DelearCustomerCareExCallPagePresenter,
    private service: DelearCustomerCareExCallService) { }

  ngOnInit(): void {
    this.service.fetchCustomerMachineDtlSubject.subscribe(obj => {
      
      if(obj['customerId']!='' && obj['customerId']!=null && obj['customerId']!=undefined){
        this.customerId = obj['customerId'] ;
        this.service.getMachineDtl(obj['customerId']).subscribe(response => {
          this.chassisList = response['result'];
          if(response['result'] && response['result'].length > 0){
            if(obj['vinId']!=null){
              response['result'].forEach(element => {
                if(element.vinId == obj['vinId']){
                  this.machineDetailsForm.patchValue(element);
                  this.machineDetailsForm.get('chassisNo').patchValue(this.chassisList.filter(chassisDtl => chassisDtl.chassisNo === element.chassisNo)[0]);
                  return;
                }
              });
            }else{
              this.machineDetailsForm.patchValue(response['result'][0]);
              this.machineDetailsForm.get('chassisNo').patchValue(this.chassisList.filter(chassisDtl => chassisDtl.chassisNo === response['result'][0]['chassisNo'])[0]);
              this.service.fetchServiceCallHistorySubject.next({customerId:this.customerId, vinId:response['result'][0]['vinId']});
            }
          }
        });
      }
    });
  }

  patchMachineDtl(machineDtl){
    this.machineDetailsForm.patchValue(machineDtl);
    this.machineDetailsForm.get('chassisNo').patchValue(this.chassisList.filter(chassisDtl => chassisDtl.chassisNo ===machineDtl['chassisNo'])[0]);
    this.service.fetchServiceCallHistorySubject.next({customerId:this.customerId, vinId:machineDtl.vinId});
  }

}
