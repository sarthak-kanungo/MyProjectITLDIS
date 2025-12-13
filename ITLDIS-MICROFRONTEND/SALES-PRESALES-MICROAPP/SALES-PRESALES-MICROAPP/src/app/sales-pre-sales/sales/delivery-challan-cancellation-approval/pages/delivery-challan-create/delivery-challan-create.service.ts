import { Injectable } from '@angular/core';
import { CreateDeliveryChallan, DcCustomerMaster, DcMachineDetails, IdMaster } from './delivery-challan-create-cancel.domain';
import { BehaviorSubject } from 'rxjs';
import { HttpClient,HttpParams } from '@angular/common/http';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginFormService } from '../../../../../root-service/login-form.service'; import { DateService } from '../../../../../root-service/date.service';

@Injectable()
export class DeliveryChallanCreateService {
  private readonly saveDeliveryChallanUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.addDeliveryChallan}`;
  private readonly getDcDetailsFromIdUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.getDcById}`;
  private readonly cancelDcUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.dcCancel}`;
  private readonly approveDcUrl = `${environment.baseUrl}${urlService.api}${urlService.deliveryChallan}${urlService.approveDc}`;

  private readonly printGatepassUrl = `${environment.baseUrl}${urlService.api}${ urlService.salesandpresales }${urlService.reports}${urlService.printGatepass}`;
  private readonly printDCUrl = `${environment.baseUrl}${urlService.api}${ urlService.salesandpresales }${urlService.reports}${urlService.printDc}`;
  
  deliveryChallanMainData: CreateDeliveryChallan = {} as CreateDeliveryChallan;   //For save DC
  getMachineCustomerAndCheklistDataFromEnq = new BehaviorSubject<object>(null);
  clearOrMarkAsTouchedAll = new BehaviorSubject<object>(null);

  constructor(
    private httpClient: HttpClient,
    private formBuilder: FormBuilder,
    private dateService: DateService,
    private loginFormService: LoginFormService,
    private fb: FormBuilder
  ) { }

  createForm(): FormGroup {
    return this.formBuilder.group({
      deliveryChallan: [null],
      implementsForm: [null],
      prospectForm: [null],
      insuranceForm: [null],
      challanCancelForm: [null]
    })
  }
  
  saveDeliveryChallan() {
    console.log('...save1')  
    let customerMaster: DcCustomerMaster = {} as DcCustomerMaster;
    let finalMachineDetails = [] as DcMachineDetails[];
    finalMachineDetails = [... this.deliveryChallanMainData.dcMachineDetailFirst];
    //if (this.deliveryChallanMainData.dcMachineDetail && this.deliveryChallanMainData.dcMachineDetail.length > 0 && this.deliveryChallanMainData.dcMachineDetail[0].machineInventory !== null) {
    //  finalMachineDetails = [... this.deliveryChallanMainData.dcMachineDetailFirst];//, ...this.deliveryChallanMainData.dcMachineDetail
    //}
    this.deliveryChallanMainData.dcMachineDetail = finalMachineDetails;
    console.log(finalMachineDetails)
   /* if (this.deliveryChallanMainData.dcMachineDetail && this.deliveryChallanMainData.dcMachineDetail.length > 0) {
      this.deliveryChallanMainData.dcMachineDetail.forEach(element => {
        element['id'] = element['machineDetailId'];
        delete element['machineDetailId'];
      })
    }*/
    /*if (!this.deliveryChallanMainData.customerMaster) {
      customerMaster.mobileNumber = this.deliveryChallanMainData.mobileNumber;
      this.deliveryChallanMainData.customerMaster = customerMaster;
    }*/
    this.deliveryChallanMainData.dealerMachineTransfer ? delete this.deliveryChallanMainData.customerMaster : this.deliveryChallanMainData.customerMaster;

    if (this.deliveryChallanMainData.deliverableChecklist && this.deliveryChallanMainData.deliverableChecklist.length > 0) {
      this.deliveryChallanMainData.deliverableChecklist.forEach(element => {
        element['dcDeliverableChecklistMaster'] = {id : element['id']};
      })
    }
    // if (this.deliveryChallanMainData.policyExpiryDate && this.deliveryChallanMainData.policyStartDate) {
    //   this.deliveryChallanMainData.policyStartDate = this.dateService.getDateIntoYYYYMMDD(this.deliveryChallanMainData.policyStartDate);
    //   this.deliveryChallanMainData.policyExpiryDate = this.dateService.getDateIntoYYYYMMDD(this.deliveryChallanMainData.policyExpiryDate);
    // }
    /*this.deliveryChallanMainData.createdBy = {} as IdMaster;
    this.deliveryChallanMainData.createdBy.id = this.loginFormService.getLoginUserId();*/
    
    if (typeof this.deliveryChallanMainData.insuranceCompanyMaster === 'string') {
      delete this.deliveryChallanMainData.insuranceCompanyMaster
    }
    
    console.log("FINAL OBJ DC", this.deliveryChallanMainData);
    return this.httpClient.post(this.saveDeliveryChallanUrl, this.deliveryChallanMainData)
  }

  getDcDetailsFromId(dcId: string) {
    return this.httpClient.get(`${this.getDcDetailsFromIdUrl}/${dcId}`);
  }
  cancelDc(cancelFormData: object) {
    return this.httpClient.post(this.cancelDcUrl, cancelFormData)
  }
  approveDc(dcId: string, remarks:string, approvalFlag:string) {
    let approveObj = {};
    approveObj['approvalFlag'] = approvalFlag;
    approveObj['dcId'] = dcId;
    approveObj['remarks'] = remarks;
    return this.httpClient.post(this.approveDcUrl, approveObj)
  }
  clearOrMarkAsTouched(functionalKey: string) {
    this.clearOrMarkAsTouchedAll.next({ key: functionalKey })
  }
  printDC(dcno:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printDCUrl, {
          params: new HttpParams().set('dcNumber', dcno)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
  printGatepass(gatepassno:string, printStatus:string){
      return this.httpClient.get<Blob>(this.printGatepassUrl, {
          params: new HttpParams().set('gatePassNo', gatepassno)
                                  .set('printStatus', printStatus),
         observe: 'response', responseType: 'blob' as 'json'                         
      });
  }
}
