import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpParams, HttpClient } from '@angular/common/http';
import { SaveMachineDescripancyComplaintDomain } from 'MachineDescripancyComplaintModule';

@Injectable()
export class MachineDescripancyComplaintsCreateService {
  machineGrnFrom: FormGroup
  constructor(
    private fb: FormBuilder,
    private httpClient: HttpClient
  ) { }
  private readonly searchChasisNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.searchChassisNumber}`;
  private readonly getDetailsByChasisNoUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.getDetailsByChassisNumber}`;
  private readonly saveMachineDescripancyComplaintUrl = `${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.saveMachineDescripancyComplaint}`;
  private readonly getSystemGeneratedDateUrl=`${environment.baseUrl}${urlService.api}${urlService.getSystemGeneratedDate}`
  private readonly getMachineDescripancyComplaintByIdUrl=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}${urlService.getMachineDescripancyComplaintById}`;
  private readonly approveComplaintDetailsUrl=`${environment.baseUrl}${urlService.api}${urlService.salesandpresales}${urlService.purchase}${urlService.machineDescripancyComplaint}/approveMachineDescripancyComplaint`;  

  createmachineGrnFrom() {
    this.machineGrnFrom = this.fb.group({
      complaintNo: [{ value: '', disabled: true }],
      complaintDate: [{ value: '', disabled: true }],
      chassisNo: [''],
      engineNo: [{ value: '', disabled: true }],
      dmsGrnNumber: [{ value: '', disabled: true }],
      complaintStatus: [{ value: '', disabled: true }],
      kaiInvoiceDate: [{ value: '', disabled: true }],
      grnDate: [{ value: '', disabled: true }],
      transporterName: [{ value: '', disabled: true }],
      transporterVehicleNumber: [{ value: '', disabled: true }],
      lrNo: [{ value: '', disabled: true }],
    })
    return this.machineGrnFrom;
  }
 

  getChasisNumber(chassisNumber, dealerId) {
    return this.httpClient.get(`${this.searchChasisNoUrl}`, {
      params: new HttpParams().set('chassisNumber', chassisNumber).set('dealerId', dealerId)
    })
  }

  populateDataByChasisNo(chassisNumber) {
    return this.httpClient.get(`${this.getDetailsByChasisNoUrl}`, {
      params: new HttpParams().set('chassisNumber', chassisNumber)
    })
  }

  saveMachineDescripancyComplaint(formData: SaveMachineDescripancyComplaintDomain) {
    return this.httpClient.post(this.saveMachineDescripancyComplaintUrl, formData)
  }

  viewMachineDescComplaintForm() {
    return this.fb.group({
      complaintNo: this.fb.control({ value: '', disabled: true }),
      complaintDate: this.fb.control({ value: '', disabled: true }),
      chassisNo: this.fb.control({ value: '', disabled: true }),
      engineNo: this.fb.control({ value: '', disabled: true }),
      dmsGrnNumber: this.fb.control({ value: '', disabled: true }),
      kaiInvoiceDate: this.fb.control({ value: '', disabled: true }),
      grnDate: this.fb.control({ value: '', disabled: true }),
      transporterName: this.fb.control({ value: '', disabled: true }),
      transporterVehicleNumber: this.fb.control({ value: '', disabled: true }),
      lrNo: this.fb.control({ value: '', disabled: true }),
      complaintStatus: this.fb.control({ value: '', disabled: true }),
    })
  }
  getSystemGeneratedDate(){
    return this.httpClient.get(`${this.getSystemGeneratedDateUrl}`)
  }
  
  
  approveClaim(claimDetails:any){
    return this.httpClient.post(this.approveComplaintDetailsUrl, claimDetails);
  }

  getMachineDescComplaintById(complaintId: number){
    return this.httpClient.get(`${this.getMachineDescripancyComplaintByIdUrl}/${complaintId}`)
  }

}
