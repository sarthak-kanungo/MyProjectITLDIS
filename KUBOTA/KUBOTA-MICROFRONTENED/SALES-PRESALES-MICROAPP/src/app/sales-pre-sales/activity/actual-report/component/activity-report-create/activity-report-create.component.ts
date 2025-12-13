import { BaseDto } from 'BaseDto';
import { urlService } from './../../../../../webservice-config/baseurl';
import { environment } from './../../../../../../environments/environment';
import { Component, OnInit, Input, OnDestroy, OnChanges, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup } from '@angular/forms';
import { ActivityReportCreateService } from './activity-report-create.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityReportCommonService } from './activity-report-common.service';
import { ActualReportCreatePageService } from '../../pages/actual-report-create/actual-report-create-page.service';
import { ToastrService } from 'ngx-toastr';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { SubmitActivityReport, ActivityReport, ViewActualReport } from 'ActualReportModule';
import { DateService } from '../../../../../root-service/date.service';
import {MarketingActivitySearchContainerService} from '../../../activity-proposal/component/marketing-activity-search-container/marketing-activity-search-container.service'


@Component({
  selector: 'app-activity-report-create',
  templateUrl: './activity-report-create.component.html',
  styleUrls: ['./activity-report-create.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }, ActivityReportCreateService,MarketingActivitySearchContainerService
  ]
})

export class ActivityReportCreateComponent implements OnInit, OnDestroy {
  activityTypeList: BaseDto<Array<any>>
  autoActivityNo: BaseDto<Array<any>>
  autoPopulateActivityNo: BaseDto<any>
  selectedACtivityNo: any
  getActivityTypes: BaseDto<Array<any>>
  getTableData: BaseDto<Array<any>>
  isEdit: boolean;
  isView: boolean;
  data: Object;
  disable = true;
  @Output() activityNumber:EventEmitter<string>  = new EventEmitter<string>();
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  dropDownActivityType: Array<any>
  ngUnsubscribe$: Subject<any> = new Subject();
  actualReportFrom: FormGroup

  constructor(
    private activityReportCreateService: ActivityReportCreateService,
    private activityReportCommonService: ActivityReportCommonService,
    private actRt: ActivatedRoute,
    private pageService: ActualReportCreatePageService,
    private toastr: ToastrService,
    private router: Router,
    private dateService: DateService,
    private marketingActivitySearchContainerService : MarketingActivitySearchContainerService
  ) { }
  
  ngOnInit() {
    this.checkOperationType()
    this.initOperationForm()
    this.patchOrCreate()
    this.buttonSubscribers()
  }

  private patchOrCreate() {
    if (this.isView) {
      this.parseIdAndPatchViewForm()
    } else {
      this.formForCreateSetup()
    }
  }

  private buttonSubscribers() {
    this.pageService.btnClick.pipe(takeUntil(this.ngUnsubscribe$)).subscribe(which => {
      if (which == 2) {
        console.log('Dialog Confirm Clicked')
        this.onConfirmClicked()
      }
      if (which == 1) {
        console.log('Submit Clicked')
        if (this.actualReportFrom.invalid) {
          this.markFormAsTouched()
          this.toastr.error('Kindly fillup all the mandatory fields and Document Upload', 'Mandatory Fields')
        }
        // if(this.actualReportFrom.get('activityType').value==='ACP BOARD' || this.actualReportFrom.get('activityType').value==='OTHER ACTIVITY'){
          
        // }else{
        //   this.toastr.error("Enquiry Details are Required");
        //   return false;
        // }
        if (!(this.activityReportCommonService.files.length > 0 && this.activityReportCommonService.files.length <= 5)) {
          this.toastr.error('Atleast one report image is mandatory', 'Report Image')
        }
        if ((this.activityReportCommonService.files.length > 0 && this.activityReportCommonService.files.length <= 5) && this.actualReportFrom.valid) {
          this.pageService.btnClick.emit(3)
        }
      }
      if (which == 4) {
        this.clearAll()
      }
    })
  }

  private onConfirmClicked() {
    this.activityReportCreateService.postReport(
      this.prepareActivityReportDomainForSave()).subscribe(res => {
        if (res) {
          this.toastr.success(res['message'], 'Success')
          this.router.navigate(['..'], { relativeTo: this.actRt })
        }
      }, err => {
        this.toastr.error('Problem in saving the report', 'Error')
      })
  }

  private prepareActivityReportDomainForSave() {
    const serviceReport = this.actualReportFrom.getRawValue()
    const submitActivityReport = {} as SubmitActivityReport
    const activityReport = {} as ActivityReport
    submitActivityReport.marketingActivityReport = activityReport
    submitActivityReport.marketingActivityReport.marketingActivityProposal = {
      activityProposalId: serviceReport.activityNumber.id
    }
    submitActivityReport.marketingActivityReport.totalBookings = serviceReport.totalBookings
    submitActivityReport.marketingActivityReport.totalRetails = serviceReport.totalRetails
    submitActivityReport.marketingActivityReport.totalEnquiries = serviceReport.totalEnquiries
    submitActivityReport.marketingActivityReport.actualFromDate = this.dateService.getDateIntoDDMMYYYY(serviceReport.actualFromDate)
    submitActivityReport.marketingActivityReport.actualToDate = this.dateService.getDateIntoDDMMYYYY(serviceReport.actualToDate)
    submitActivityReport.multipartFileList = this.activityReportCommonService.files
    const activityReportEnquiryDetails = []
    this.activityReportCommonService.enquiryDetails.forEach(enquiries => {
      activityReportEnquiryDetails.push({
        tentativeDateOfPurchase : enquiries.tentativePurchaseDate,
        isContacted: enquiries.isContacted,
        enquiry : {id: enquiries.id}
      })
    })
    submitActivityReport.marketingActivityReport.activityReportEnquiryDetails = activityReportEnquiryDetails
    console.log(submitActivityReport)
    return submitActivityReport;
  }

  private formForCreateSetup() {
    this.actualReportFrom = this.activityReportCreateService.createActivityReportForm()
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate() + 1);
    this.actualReportFrom.controls.activityNumber.valueChanges.subscribe(value => {
      console.log("typed value--->", value)
      if (value) {
        const activityNo = typeof value === 'object' ? value.activityNumber : value
        this.autoActivityNumber(activityNo)
      }

      if (value !== null && typeof value !== 'object') {
        this.actualReportFrom.get('activityNumber').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })
    this.actualReportFrom.controls.actualToDate.valueChanges.subscribe(value => {
      console.log("actualToDate---->", value);
        if(this.selectedACtivityNo
                && this.actualReportFrom.controls.actualFromDate.value !=null && this.actualReportFrom.controls.actualFromDate.value != ''
                && value != null && value != ''    ){
           this.activityReportCommonService.onActivityNumber.emit({proposalId : this.selectedACtivityNo.id, 
               actualFromDate : this.convertDateToServerFormat(this.actualReportFrom.controls.actualFromDate.value), 
               actualToDate : this.convertDateToServerFormat(value)
           });
        }
    });
    
    
    this.actualReportFrom.controls.actualFromDate.valueChanges.subscribe(value => {
        if(this.selectedACtivityNo
                && this.actualReportFrom.controls.actualToDate.value !=null && this.actualReportFrom.controls.actualToDate.value != ''
                && value != null && value != ''    ){
           this.activityReportCommonService.onActivityNumber.emit({proposalId : this.selectedACtivityNo.id, 
               actualFromDate : this.convertDateToServerFormat(value), 
               actualToDate : this.convertDateToServerFormat(this.actualReportFrom.controls.actualToDate.value) 
           });
        }
    });
    
    this.activityReportCommonService.onEnquiryDetails.subscribe(enquiryDetails => {
       this.assignEnquiryTotals(enquiryDetails)
    });
    
  }

  private convertDateToServerFormat(dt: string) {
      if (dt) {
        let date = new Date(dt)
        let formattedDate = date.getFullYear() + '-' +((date.getMonth()+1)<10? '0'+(date.getMonth() + 1):(date.getMonth() + 1)) + '-' + (date.getDate()<10? '0'+date.getDate():date.getDate())
        console.log("formattedDate--->", formattedDate)
        return formattedDate
      }
      return ''
      }
  
  autoActivityNumber(event) {
   /* this.activityReportCreateService.getActivityNo(event).subscribe(response => {
      console.log('response', response);
      this.autoActivityNo = response as BaseDto<Array<any>>
    })*/
    console.log('event--->', event);
      this.marketingActivitySearchContainerService.getActivityNo(event,'REPORT').subscribe(response => {
          console.log('response', response);
          this.autoActivityNo = response as BaseDto<Array<any>>
      })  
  }

  optionSelectedActivityNo(event) {
    console.log(event)
    this.selectedACtivityNo = { activityNumber: event.option.value.activityNumber, id: event.option.value.id }
    //this.activityReportCommonService.onActivityNumber.emit(event.option.value.id)
    this.activityReportCreateService.getActivityReportHeaderDetails(event.option.value.id).subscribe(response => {
      if (response) {
        let dto = response as BaseDto<any>
        this.patchDates(dto.result)
        this.actualReportFrom.controls.activityNumber.patchValue({ activityNumber: dto.result.activityNumber, id: event.option.value.id })
        this.actualReportFrom.get('activityNumber').disable();
        this.actualReportFrom.controls.actualFromDate.enable();
        this.actualReportFrom.controls.actualToDate.enable();
      }
    })
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.actualReportFrom.get('activityNumber').setErrors(null);
    }
    this.actualReportFrom.controls.actualFromDate.reset();
    this.actualReportFrom.controls.actualToDate.reset();
  }

  private patchDates(domain: any) {
    this.actualReportFrom.patchValue(domain)
    this.actualReportFrom.controls.activityCreationDate.patchValue(new Date(domain.createdDate))
    this.actualReportFrom.controls.fromDate.patchValue(new Date(domain.fromDate))
    this.actualReportFrom.controls.toDate.patchValue(new Date(domain.toDate))
  }

  private convertToPatchFormat(dt: string) {
    if (dt) {
      return dt.split('-').reverse().join('-')
    }
    return ''
  }

  private parseIdAndPatchViewForm() {
    this.actRt.params.subscribe(prms => this.fetchDataForId(atob(prms['id'])))
  }

  private fetchDataForId(id: string) {
    this.activityReportCreateService.getActivityReportById(`` + id).subscribe(
      dto => {
        console.log(dto)
        this.formForViewSetup(dto.result)
      }
    )
  }

  private formForViewSetup(domain: ViewActualReport) {
    console.log(domain)

    if (domain) {
      this.actualReportFrom.patchValue(domain);
      this.activityNumber.emit(domain.marketingActivityProposal.activityNumber);
      this.actualReportFrom.controls.activityNumber.patchValue({ activityNumber: domain.marketingActivityProposal.activityNumber })
      this.actualReportFrom.controls.activityType.patchValue(domain.marketingActivityProposal.activityTypeName)
      this.patchDatesForView(domain)
      this.patchViewImages(domain)
      this.patchEnquiryDetailsForView(domain.activityReportEnquiryDetails)
    }
  }

  private patchViewImages(domain: any) {
    if (domain) {
      domain.marketingActivityReportImages.forEach(img => {
        console.log(`previewUrl`, img.fileName.toString());
        this.activityReportCommonService.files.push({ previewUrl: `${environment.baseUrl}/${environment.api}${urlService.staticPath}/${img.fileName.toString()}`, id: '' + img.id, file: undefined })
        console.log('path', `${environment.baseUrl}/${environment.api}${urlService.staticPath}/${img.fileName.toString()}`);
      })
    }
  }

  private patchDatesForView(domain: any) {
    this.actualReportFrom.controls.fromDate.patchValue(this.convertToPatchFormat(domain.marketingActivityProposal.fromDate))
    this.actualReportFrom.controls.toDate.patchValue(this.convertToPatchFormat(domain.marketingActivityProposal.toDate))
    this.actualReportFrom.controls.actualFromDate.patchValue(this.convertToPatchFormat(domain.actualFromDate))
    this.actualReportFrom.controls.actualToDate.patchValue(this.convertToPatchFormat(domain.actualToDate))
    this.actualReportFrom.controls.activityCreationDate.patchValue(this.convertToPatchFormat(domain.marketingActivityProposal.activityCreationDate))
  }

  private patchEnquiryDetailsForView(enqDetails: Array<any>) {
    enqDetails.forEach(enq => {
      let enqDtl: any = {} as any
      enqDtl.enquiryNumber = enq.enquiry.enquiryNumber
      enqDtl.enquiryDate = enq.enquiry.enquiryDate
      enqDtl.enquiryType = enq.enquiry.enquiryType
      enqDtl.mobileNumber = enq.enquiry.mobileNumber
      enqDtl.firstName = `${enq.enquiry.firstName}`
      enqDtl.lastName = `${enq.enquiry.lastName}`
      enqDtl.model = enq.enquiry.model
      enqDtl.variant = enq.enquiry.variant
      enqDtl.enquiryStatus = enq.enquiry.enquiryStatus
      enqDtl.tentativePurchaseDate = enq.tentativeDateOfPurchase
      enqDtl.isContacted = enq.isContacted
      this.activityReportCommonService.enquiryDetails.push(enqDtl)
    })
  }

  private checkOperationType() {
    console.log(this.actRt.snapshot.routeConfig.path)
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
    console.log(`Edit = ${this.isEdit} View = ${this.isView}`)
  }

  private assignEnquiryTotals(enquiryDetails: any) {
    
    if(enquiryDetails && enquiryDetails.length != 0 ){  
        this.actualReportFrom.controls.totalBookings.patchValue(enquiryDetails[0].totalBookings)
        this.actualReportFrom.controls.totalRetails.patchValue(enquiryDetails[0].totalRetails)
        this.actualReportFrom.controls.totalEnquiries.patchValue(enquiryDetails[0].totalEnquiries)
    }
  
  }
  autoActivityNumberdisplayFn(act: any): string {
    return  act.activityNumber ? act.activityNumber :'' ;
  }

  private markFormAsTouched() {
    for (const key in this.actualReportFrom.controls) {
      if (this.actualReportFrom.controls.hasOwnProperty(key)) {
        this.actualReportFrom.controls[key].markAsTouched();
      }
    }
  }

  ngOnDestroy() {
    this.ngUnsubscribe$.next()
    this.ngUnsubscribe$.complete()
  }


  private initOperationForm() {
    if (this.isView) {
      this.actualReportFrom = this.activityReportCreateService.viewActivityReportForm()
    }
    else {
      this.actualReportFrom = this.activityReportCreateService.createActivityReportForm()
    }
  }

  private clearAll() {
    this.activityReportCommonService.files = []
    this.activityReportCommonService.enquiryDetails = []
    this.actualReportFrom.reset();
    this.actualReportFrom.controls.activityNumber.patchValue({ activityNumber: '' })
    this.actualReportFrom.controls.actualFromDate.disable();
    this.actualReportFrom.controls.actualToDate.disable();
    this.actualReportFrom.get('activityNumber').enable();
  }

}




