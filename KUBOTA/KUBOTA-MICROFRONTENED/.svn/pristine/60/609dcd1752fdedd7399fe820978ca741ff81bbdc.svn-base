import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { SaveAndSubmitDescripancyClaimDomain } from 'MachineDescripancyClaim';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class MachineDescripancyCiaimCreateService {
  machineDescripancyClaimForm: FormGroup
  saveAndSubmitDescripancyClaimDomain = {} as SaveAndSubmitDescripancyClaimDomain
  private readonly getComplaintDetailsURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyClaim}${urlService.getDiscrepancyComplaints}`
  private readonly saveAndSubmitMachineDescripancyClaimURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyClaim}${urlService.saveDiscrepancyClaim}`
  private readonly getByClaimIdURL = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyClaim}${urlService.getDetailsByClaimId}`
  private readonly approveClaimDetailsUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyClaim}/approveMachineDiscrepancyClaim`
  //http://192.168.15.133:8383/api/salesandpresales/purchase/machineDescripancyClaim/getDetailsByClaimId/3
  //http://192.168.15.133:8383/api/salesandpresales/purchase/machineDescripancyClaim/getDiscrepancyComplaints/4
  //http://192.168.15.133:8383/api/salesandpresales/purchase/machineDescripancyClaim/saveDiscrepancyClaim
  constructor(
    private fb: FormBuilder,
    private http : HttpClient
    ) { 
      this.saveAndSubmitDescripancyClaimDomain.machineDescripancyClaim = []
    }

  createMachineDescripancyClaimForm() {
    this.machineDescripancyClaimForm = this.fb.group({ 
      claimDate:[{value:new Date(), disabled:true}],
      remarks:[null]
    })
    return this.machineDescripancyClaimForm;
  }

  viewMachineDescripancyClaimForm() {
    this.machineDescripancyClaimForm = this.fb.group({ 
      claimNumber:[{ value: null, disabled: true }],
      claimDate:[{value:null, disabled:true}],
      claimStatus:[{value:null, disabled:true}],
      remarks:[{value:null, disabled:true}]
    })
    return this.machineDescripancyClaimForm;
  }

  editMachineDescripancyClaimForm() {
    this.machineDescripancyClaimForm = this.fb.group({ 
      claimNumber:[{ value: null, disabled: true }],
      claimDate:[{value:null, disabled:true}],
      claimStatus:[{value:null, disabled:true}],
      remarks:[null]
    })
    return this.machineDescripancyClaimForm;
  }


  getComplaintDetails(id){
    return this.http.get(`${this.getComplaintDetailsURL}/${id}`)
  }

  saveAndSubmitMachineDescripancyClaim(formData){
    return this.http.post(this.saveAndSubmitMachineDescripancyClaimURL, formData)
  }

  getByClaimId(claimId){
    return this.http.get(`${this.getByClaimIdURL}/${claimId}`)
  }

  
  approveClaim(claimDetails:any){
    return this.http.post(this.approveClaimDetailsUrl, claimDetails);
  }
}
