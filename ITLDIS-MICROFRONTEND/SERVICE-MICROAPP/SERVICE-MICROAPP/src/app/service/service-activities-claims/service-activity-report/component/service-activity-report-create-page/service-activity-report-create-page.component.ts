import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ServiceActivityReportCreatePageStore } from './service-activity-report-create-page.store';
import { ServiceActivityReportCreatePagePresenter } from './service-activity-report-create-page.presenter';
import { FormGroup } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ServiceActivityReport, MachineDetailsByActivityNo, ServiceDetailsByActivityNo, SubmitServiceActivityReport, JobCardDetailsByActivityNo, ViewServiceActivityReport } from '../../domain/service-activity-report.domain';
import { DateService } from '../../../../../root-service/date.service';
import { ServiceActivityReportCreatePageWebService } from './service-activity-report-create-page-web.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-service-activity-report-create-page',
  templateUrl: './service-activity-report-create-page.component.html',
  styleUrls: ['./service-activity-report-create-page.component.scss'],
  providers: [ServiceActivityReportCreatePageStore, ServiceActivityReportCreatePagePresenter, ServiceActivityReportCreatePageWebService]
})
export class ServiceActivityReportCreatePageComponent implements OnInit {

  isView: boolean
  activityReportForm: FormGroup
  activityReportDetailsForm: FormGroup
  documentUplodedForm: FormGroup
  viewActivityReport : ViewServiceActivityReport
  isSubmitDisable:boolean = false;
  activityNumber: string;
  id: any;
  printStatus: string;
  constructor(
    public dialog: MatDialog,
    private serviceActivityReportCreatePagePresenter: ServiceActivityReportCreatePagePresenter,
    private activateRoute: ActivatedRoute,
    private toastr: ToastrService,
    private dateService: DateService,
    private router: Router,
    private serviceActivityReportCreatePageWebService: ServiceActivityReportCreatePageWebService
  ) { }

  ngOnInit() {
    this.serviceActivityReportCreatePagePresenter.operation = OperationsUtil.operation(this.activateRoute)
    this.activityReportForm = this.serviceActivityReportCreatePagePresenter.serviceActivityReportForm
    this.activityReportDetailsForm = this.serviceActivityReportCreatePagePresenter.activityReportForm
    this.documentUplodedForm = this.serviceActivityReportCreatePagePresenter.documentUploded
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityReportCreatePagePresenter.operation === Operation.VIEW) {
      this.isView = true
      this.activityReportForm.disable()
      this.parseIdAndViewOrEditForm()
    } else if (this.serviceActivityReportCreatePagePresenter.operation === Operation.CREATE) {
      this.isView = false
    }
  }

  private parseIdAndViewOrEditForm() {
    this.activateRoute.params.subscribe(prms => {
      this.serviceActivityReportCreatePageWebService.getActivityReportById(`` + prms['id']).subscribe(response => {
        // console.log(prms.id,'respomse')
        this.id=prms.id;
      this.serviceActivityReportCreatePagePresenter.patchValueForViewServiceActivityReport(response)
      this.viewActivityReport = response
      this.activityNumber=response.activityReportHeaderData.activityNumber
      // console.log(this.activityNumber,'report shh')
      })
    })
  }


  

  submitActivityReport() {
   
    if (this.activityReportForm.status === 'VALID') {
      const jobCardDetailsLength = this.activityReportForm.controls.JobCardDetailstableData['controls'].JobCardDetailsdataTable.controls.length;

      if (jobCardDetailsLength > 0) {
        // Check if photos are selected
        if (this.serviceActivityReportCreatePagePresenter.selectedPhotos === undefined || this.serviceActivityReportCreatePagePresenter.selectedPhotos.length < 1) {
          this.toastr.error(`File Attachment mandatory`, 'Report Attachment Photo');
        } else {
          this.openConfirmDialog();
        }
      } else {
        // Job card list not found
        this.toastr.error("Job Card Details Not Found!");
      }
    
      
    } else {
      this.serviceActivityReportCreatePagePresenter.markFormAsTouched();
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
    
  }
  
  submitData() {
    
    this.serviceActivityReportCreatePageWebService.saveServiceActivityReport(this.submitServiceActivityReport()).subscribe(response => {
      if (response) {
        const displayMsg = response['message']
        this.toastr.success(displayMsg, 'Success')
        this.router.navigate(['../'], {relativeTo : this.activateRoute})
      }else {
         this.isSubmitDisable = false;
         this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
         this.isSubmitDisable = false;
         this.toastr.error('Error generated while saving','Transaction failed');
    })
  }
  submitServiceActivityReport() {
    const activityReport = this.activityReportForm.getRawValue()
    const submitServiceActivityReport = {} as SubmitServiceActivityReport
    const serviceActivityReport = {} as ServiceActivityReport
    submitServiceActivityReport.serviceActivityReport = serviceActivityReport
    submitServiceActivityReport.multipartFileList = this.serviceActivityReportCreatePagePresenter.selectedPhotos
    submitServiceActivityReport.serviceActivityReport.serviceActivityProposal = {
      id: activityReport.serviceActivityReportForm.activityNo.id
    }
    submitServiceActivityReport.serviceActivityReport.activityEffectiveness = activityReport.serviceActivityReportForm.activityEffectiveness.effectiveness
    submitServiceActivityReport.serviceActivityReport.remarks = activityReport.serviceActivityReportForm.remarks
    submitServiceActivityReport.serviceActivityReport.actualFromDate = activityReport.serviceActivityReportForm.actualFromDate
    //  this.dateService.getDateIntoDDMMYYYY(activityReport.serviceActivityReportForm.actualFromDate)
    submitServiceActivityReport.serviceActivityReport.actualToDate = activityReport.serviceActivityReportForm.actualToDate
    //  this.dateService.getDateIntoDDMMYYYY(activityReport.serviceActivityReportForm.actualToDate)
    submitServiceActivityReport.serviceActivityReport.serviceActivityReportMachineDetails = this.buildJsonForMachineDetails(activityReport.machineDetailstableData.machineDetailsdataTable)
    submitServiceActivityReport.serviceActivityReport.serviceActivityReportServiceDetails = this.buildJsonForServiceDetails(activityReport.serviceDetailstableData.serviceDetailsdataTable)
    submitServiceActivityReport.serviceActivityReport.serviceActivityReportJobCardDetails = this.buildJsonForJobCardDetails(activityReport.JobCardDetailstableData.JobCardDetailsdataTable)
    
    return submitServiceActivityReport
  }
  buildJsonForMachineDetails(machineDetailsTableData: MachineDetailsByActivityNo[]) {
    const serviceActivityReportMachineDetails = []
    machineDetailsTableData.forEach(element => {
      serviceActivityReportMachineDetails.push({
        machineSeries: { id: element.id },
        noOfMachine: element.noOfMachines
      })
    })
    return serviceActivityReportMachineDetails
  }
  buildJsonForServiceDetails(serviceDetailsTableData: ServiceDetailsByActivityNo[]) {
    const serviceActivityReportServiceDetails = []
    serviceDetailsTableData.forEach(element => {
      serviceActivityReportServiceDetails.push({
        noOfMachine: element.serviceTypeCount,
        serviceMtServiceTypeInfo: { id: element.id }
      })
    });
    return serviceActivityReportServiceDetails
  }
  buildJsonForJobCardDetails(JobCardDetailsTableData: JobCardDetailsByActivityNo[]){
    const serviceActivityReportJobCardDetails = []
    JobCardDetailsTableData.forEach(element => {
      serviceActivityReportJobCardDetails.push({
        serviceJobCard : {id: element.id} ,
        labourCharges: element.labourCharges,
        sparePartSale: element.sparePartSale
      })
    })

    return serviceActivityReportJobCardDetails
  }
  private openConfirmDialog(): void | any {
    let message = `Do you want to submit Service Activity Report?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }
  onClickExit(){
    this.router.navigate(['../'], {relativeTo : this.activateRoute})
  }
  onExit(){
    this.router.navigate(['/service-activities-claims/service-activity-report'], {relativeTo : this.activateRoute})
  }
  onDownloadPdf(){
      this.printStatus='true';
    this.serviceActivityReportCreatePageWebService.printPCR(this.id,this.activityNumber,this.printStatus).subscribe((resp: HttpResponse<Blob>) => {
      
            const blobUrl = window.URL.createObjectURL(resp.body);
            const iframe = document.createElement('iframe');
            iframe.style.display = 'none';
            iframe.src = blobUrl;
            document.body.appendChild(iframe);
            iframe.contentWindow.print();
            this.activityNumber=null
            this.id=null
            this.printStatus=null

          // }
     })
 }
}