import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { DirectSurveyService } from '../../../direct-survey/direct-survey.service';
import { TollFreeService } from '../../service/toll-free.service';

@Component({
  selector: 'app-machine-detail',
  templateUrl: './machine-detail.component.html',
  styleUrls: ['./machine-detail.component.css']
})
export class MachineDetailComponent implements OnInit {
  @Input()
  machineDetailsForm:FormGroup
  chassisList:any
  dealerList:any[]
  constructor(private service: TollFreeService,
    private directSurvey: DirectSurveyService) { }
  customerId:any;
  @Input()
  isView:boolean
  ngOnInit() {
    this.service.fetchCustomerMachineDtlSubject.subscribe(obj => {
      if(obj['customerId']!=null){
        this.customerId = obj['customerId'];
        this.service.getMachineDtl(obj['customerId']).subscribe(response => {
          this.chassisList = response['result'];
          if(response['result'] && response['result'].length > 0){
            if(obj['vinId']!=null){
              response['result'].forEach(element => {
                if(element.vinId == obj['vinId']){
                  
                  this.machineDetailsForm.patchValue(element);
                  this.machineDetailsForm.get('chassisNo').patchValue(this.chassisList.filter(chassisDtl => chassisDtl.chassisNo === element.chassisNo)[0]);
                  this.service.fetchDealerDtlSubject.next({'soldToDealer':element['dealerName'], 'dealerContactName':element['dealerContactName'], 'dealerContactNo':element['dealerContactNo']})

                  return;
                }
              });
            }else{
              this.machineDetailsForm.patchValue(response['result'][0]);
              this.machineDetailsForm.get('soldToDealer').patchValue({'displayString':response['result'][0]['dealerCode'],'code':response['result'][0]['dealerCode'],'id':response['result'][0]['dealerId']});
              this.machineDetailsForm.get('chassisNo').patchValue(this.chassisList.filter(chassisDtl => chassisDtl.chassisNo === response['result'][0]['chassisNo'])[0]);
              this.service.fetchServiceCallHistorySubject.next({customerId:this.customerId, vinId:response['result'][0]['vinId']});
              this.service.fetchDealerDtlSubject.next({'soldToDealer':response['result'][0]['dealerName'], 'dealerContactName':response['result'][0]['dealerContactName'], 'dealerContactNo':response['result'][0]['dealerContactNo']})

            }
          }
        });
      }
    });
  }
  patchMachineDtl(machineDtl){
    this.machineDetailsForm.patchValue(machineDtl);
    this.machineDetailsForm.get('soldToDealer').patchValue({'displayString':machineDtl['dealerCode'],'code':machineDtl['dealerCode'],'id':machineDtl['dealerId']});
    this.machineDetailsForm.get('chassisNo').patchValue(this.chassisList.filter(chassisDtl => chassisDtl.chassisNo ===machineDtl['chassisNo'])[0]);
    this.service.fetchServiceCallHistorySubject.next({customerId:this.customerId, vinId:machineDtl.vinId});
  }


  selectSoldToDealer(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.machineDetailsForm.get('soldToDealer').setErrors(null);
    }
  }

  displayFnSoldToDealer(soldTodealer) {
    return soldTodealer ? soldTodealer.code : undefined;
  }
  autoSoldToDealer(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.directSurvey.autoSoldToDealer (value).subscribe(res=>{
        this.dealerList=res
      })
    }
  }
}
