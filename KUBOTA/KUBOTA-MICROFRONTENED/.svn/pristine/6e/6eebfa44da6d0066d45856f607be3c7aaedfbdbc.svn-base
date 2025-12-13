
import { urlService } from './../../../../../webservice-config/baseurl';
import { ActivityNumberDomain, ActivityDetailsDomain, ActivityClaimHead, ActivitiyClaim, ActivityEffectivenessDomain, ViewEditActivityClaimDomain, DropDownGST } from 'ActivityClaimModule';
import { ActivityClaimCreationService } from './activity-claim-creation.service';
import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import { environment } from '../../../../../../environments/environment';
import { MarketingActivityReportImage } from 'ActualReportModule';
import { CustomValidators } from 'src/app/utils/custom-validators';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';
import { DatePipe, Location } from '@angular/common';

@Component({
  selector: 'app-activity-claim-creation',
  templateUrl: './activity-claim-creation.component.html',
  styleUrls: ['./activity-claim-creation.component.scss'],
  providers: [{ provide: DateAdapter, useClass: AppDateAdapter },
  { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },
    ActivityClaimCreationService,
     DatePipe
  ]
})
export class ActivityClaimCreationComponent implements OnInit {
  activityClaimForm: FormGroup
  isView: boolean;
  isEdit: boolean;
  claimId: any
  isApprove: boolean;
  approvalDetails: [];
  dealerInfo: any;
  searchActivityNumber: BaseDto<Array<ActivityNumberDomain>>
  selectedActivityNo: ActivityNumberDomain
  activityClaimHeads: Array<ActivityClaimHead>
  reportImages: Array<MarketingActivityReportImage>
  totalActualAmount = 0
  totalApprovedAmount = 0
  gstAmount = 0
  totalActualMainClaimAmt = 0
  selectedGstPercent = 0
  activityDataDto: BaseDto<ActivityDetailsDomain>
  previewUrl: string
  areHeadsAvailable: boolean
  areReportImagesAvailable: boolean
  isApprovedAmount: boolean
  fileNamelist = []
  reportFileListApprove=[]
  fileGstInvoice: File
  readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`
  dropdownActivityEffectiveness: BaseDto<Array<ActivityEffectivenessDomain>>
  dropDownGSTPersentage: BaseDto<Array<DropDownGST>>
  selectedValue: number;
  gstPercent: FormArray;
  isSubmitDisable: boolean = false;
  vendorName = new FormControl();
  billNo=new FormControl();
  billDates=new FormControl();
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private activityClaimCreationService: ActivityClaimCreationService,
    private claimRt: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private location: Location,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    this.checkOperationType()
    this.initOperationForm()
    this.patchOrCreate()
    this.getActivityEffectiveness();
    this.dropDownGST()

    this.gstPercent = new FormArray([])
    
  }

  private patchOrCreate() {
    // console.log('check whether form is in patchorcreate or not');
    if (this.isView) {
      console.log('true')
      // console.log(`Viweing Form in claim`)
      this.parseIdAndPatchViewForm()
      this.isView = true
      this.vendorName.disable();
      this.billNo.disable();
      this.billDates.disable();
      
    }
    else {
      this.isView = false
      // console.log(`CreatingForm`)
    }
    if (this.isEdit) {
      this.parseIdAndPatchViewForm()
      this.isEdit = true
      this.activityClaimForm.disable();
    }
  }

  createActivityClaimForm() {
    this.activityClaimForm = this.fb.group({
      claimNumber: [null],
      claimDate: [null],
      activityNumber: [null],
      activityCreationDate: [{ value: '', disabled: true }],
      activityType: [{ value: '', disabled: true }],
      claimStatus: [null],
      numberOfDays: [{ value: '', disabled: true }],
      fromDate: [{ value: '', disabled: true }],
      toDate: [{ value: '', disabled: true }],
      actualFromDate: [{ value: '', disabled: true }],
      actualToDate: [{ value: '', disabled: true }],
      location: [{ value: '', disabled: true }],
      totalEnquiries: [{ value: '', disabled: true }],
      hotEnquiry: [{ value: '', disabled: true }],
      warmEnquiry: [{ value: '', disabled: true }],
      coldEnquiry: [{ value: '', disabled: true }],
      costPerEnquiry: [{ value: '', disabled: true }],
      costPerHotEnquiry: [{ value: '', disabled: true }],
      totalBookings: [{ value: '', disabled: true }],
      costPerBooking: [{ value: '', disabled: true }],
      totalRetails: [{ value: '', disabled: true }],
      costPerRetail: [{ value: '', disabled: true }],
      activityEffectiveness: ['', Validators.compose([Validators.required])],
      approvedAmount: [null]
    });
    this.activityClaimForm.controls.activityNumber.valueChanges.subscribe(value => {
      const activityNo = typeof value === 'object' ? value.activityNumber : value
      this.autoActivityNumber(activityNo)

      if (value !== null && typeof value !== 'object') {
        this.activityClaimForm.get('activityNumber').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })
  }

  viewActivityClaimForm() {
    this.activityClaimForm = this.fb.group({
      claimNumber: this.fb.control({ value: '', disabled: true }),
      claimDate: this.fb.control({ value: '', disabled: true }),
      activityNumber: this.fb.control({ value: '', disabled: true }),
      activityCreationDate: this.fb.control({ value: '', disabled: true }),
      activityType: this.fb.control({ value: '', disabled: true }),
      claimStatus: this.fb.control({ value: '', disabled: true }),
      numberOfDays: this.fb.control({ value: '', disabled: true }),
      fromDate: this.fb.control({ value: '', disabled: true }),
      toDate: this.fb.control({ value: '', disabled: true }),
      actualFromDate: this.fb.control({ value: '', disabled: true }),
      actualToDate: this.fb.control({ value: '', disabled: true }),
      location: this.fb.control({ value: '', disabled: true }),
      totalEnquiries: this.fb.control({ value: '', disabled: true }),
      hotEnquiry: this.fb.control({ value: '', disabled: true }),
      warmEnquiry: this.fb.control({ value: '', disabled: true }),
      coldEnquiry: this.fb.control({ value: '', disabled: true }),
      costPerEnquiry: this.fb.control({ value: '', disabled: true }),
      costPerHotEnquiry: this.fb.control({ value: '', disabled: true }),
      totalBookings: this.fb.control({ value: '', disabled: true }),
      costPerBooking: this.fb.control({ value: '', disabled: true }),
      totalRetails: this.fb.control({ value: '', disabled: true }),
      costPerRetail: this.fb.control({ value: '', disabled: true }),
      activityEffectiveness: this.fb.control({ value: '', disabled: true }),
      proposalApprovedAmount: this.fb.control({ value: null, disabled: true }),
      approvedAmount: this.fb.control({ value: '', disabled: true })
    });
  }

  autoActivityNumber(activityNo) {
    this.activityClaimCreationService.getActivityNumber(activityNo).subscribe(response => {
      // console.log('---response', response);
      this.searchActivityNumber = response as BaseDto<Array<ActivityNumberDomain>>
    })
  }

  optionSelectedActivityNumber(event: MatAutocompleteSelectedEvent) {
    this.selectedActivityNo = event.option.value
    this.patchActivityClaimHeaderDateFromActivtyNumber(event.option.value.id)
    this.patchActivityClaimHeadDateFromActivtyNumber(event.option.value.id)
    this.patchActivityReportPhotosFromActivtyNumber(event.option.value.id)

    if (event instanceof MatAutocompleteSelectedEvent) {
      // console.log('in option selected');
      this.activityClaimForm.get('activityNumber').setErrors(null);
    }
  }

  private patchActivityClaimHeaderDateFromActivtyNumber(acNoId) {
    this.activityClaimCreationService.getActivityClaimHeaderDetails(acNoId).subscribe(response => {
      // console.log('ResponseVoiewww', response);
      if (response) {
        this.activityDataDto = response as BaseDto<ActivityDetailsDomain>
        this.activityClaimForm.patchValue(this.activityDataDto.result)
        this.activityClaimForm.controls.totalEnquiries.patchValue(this.activityDataDto.result.totalEnquiry)
        this.activityClaimForm.controls.hotEnquiry.patchValue(this.activityDataDto.result.hotEnquiries)
        this.activityClaimForm.controls.warmEnquiry.patchValue(this.activityDataDto.result.warmEnquiries)
        this.activityClaimForm.controls.coldEnquiry.patchValue(this.activityDataDto.result.coldEnquiries)
        this.patchDatesForCreate(this.activityDataDto.result)
        this.activityClaimForm.controls.costPerEnquiry.patchValue(this.calculateCostPerEnquiry(this.activityDataDto.result.totalEnquiry, this.totalActualMainClaimAmt))
        this.activityClaimForm.controls.costPerHotEnquiry.patchValue(this.calculateCostPerHotEnquiry(this.activityDataDto.result.hotEnquiries, this.totalActualMainClaimAmt))
        this.activityClaimForm.controls.costPerBooking.patchValue(this.calculateCostPerBooking(this.activityDataDto.result.totalBooking, this.totalActualMainClaimAmt))
        this.activityClaimForm.controls.costPerRetail.patchValue(this.calculateCostPerRetail(this.activityDataDto.result.totalRetails, this.totalActualMainClaimAmt))
      }
    })
  }

  private patchDatesForCreate(domain: ActivityDetailsDomain) {
    this.activityClaimForm.controls.activityCreationDate.patchValue(new Date(domain.createdDate))
    this.activityClaimForm.controls.fromDate.patchValue(new Date(domain.fromDate))
    this.activityClaimForm.controls.toDate.patchValue(new Date(domain.toDate))
    this.activityClaimForm.controls.actualFromDate.patchValue(new Date(domain.actualFromDate))
    this.activityClaimForm.controls.actualToDate.patchValue(new Date(domain.actualToDate))
  }

  private patchActivityClaimHeadDateFromActivtyNumber(activityNoId) {
    this.activityClaimCreationService.getActivityClaimHeadsDetails(activityNoId).subscribe(response => {
      const head = response as BaseDto<Array<ActivityClaimHead>>
      this.patchActivityHeads(head.result)
    })
  }

  private patchActivityHeads(heads: Array<ActivityClaimHead>) {
    this.activityClaimHeads = heads
    this.areHeadsAvailable = true
    this.gstAmount = 0;
    this.totalActualAmount = 0;
    let totalGstAmount = 0
    this.activityClaimHeads.forEach(head => {
      head.actualClaimAmount = head.actualClaimAmount ? head.actualClaimAmount : 0;
      this.totalActualAmount += head.actualClaimAmount;
      head.total = head.actualClaimAmount + head.gstAmount
      totalGstAmount += head.gstAmount
      head.image = `${this.staticPath}/${head.image}`
      head.vendorName = head.vendorName,
        head.billNo = head.billNo,
        head.billDate = head.billDate
    })
    this.totalActualMainClaimAmt = this.totalActualAmount + totalGstAmount
    heads.forEach(head => this.totalApprovedAmount += head.amount)
    this.totalActualAmount = this.totalApprovedAmount
  }

  dropDownGST() {
    this.activityClaimCreationService.DropDowngetGst().subscribe(response => {
      // console.log("response ", response);
      this.dropDownGSTPersentage = response as BaseDto<Array<DropDownGST>>
    })
  }
  // Add for Vendor Name
  onChangeVendorName(name: string, head: ActivityClaimHead) {
    head.vendorName = name;
  }

  // Add for Bill No
  onChangeBillNo(billNo: string, head: ActivityClaimHead) {
    head.billNo = billNo;

  }

  onChangeBillDate(billDate: any, head: ActivityClaimHead) {
    head.billDate = billDate;
    // console.log(head.billDate, 'billdate')
  }
  onChangeInputActivityActualAmount(amt: string, head: ActivityClaimHead) {
    head.actualClaimAmount = parseInt(amt)
    this.totalActualAmount = 0
    let totalGstAmount = 0
    this.activityClaimHeads.forEach(head => {
      this.totalActualAmount += head.actualClaimAmount ? head.actualClaimAmount : head.amount
      head.gstAmount = (head.actualClaimAmount * head.gstPercent) / 100 || 0
      head.total = head.actualClaimAmount + head.gstAmount
      totalGstAmount += head.gstAmount

    })
    this.totalActualMainClaimAmt = this.totalActualAmount + totalGstAmount
    let activityDetails = this.activityDataDto.result
    this.patchCalculations(activityDetails)
  }

  onGstOptionSelected(event, head: ActivityClaimHead) {
    // console.log("event ", event);
    head.gstPercent = event.value
    let totalGstAmount = 0
    this.activityClaimHeads.forEach(head => {
      head.gstAmount = (head.actualClaimAmount * head.gstPercent) / 100 || 0
      head.total = head.actualClaimAmount + head.gstAmount
      totalGstAmount += head.gstAmount

    })
    this.totalActualMainClaimAmt = this.totalActualAmount + totalGstAmount

    this.activityClaimForm.controls.costPerEnquiry.patchValue(this.calculateCostPerEnquiry(this.activityDataDto.result.totalEnquiry, this.totalActualMainClaimAmt))
    this.activityClaimForm.controls.costPerHotEnquiry.patchValue(this.calculateCostPerHotEnquiry(this.activityDataDto.result.hotEnquiries, this.totalActualMainClaimAmt))
    this.activityClaimForm.controls.costPerBooking.patchValue(this.calculateCostPerBooking(this.activityDataDto.result.totalBooking, this.totalActualMainClaimAmt))
    this.activityClaimForm.controls.costPerRetail.patchValue(this.calculateCostPerRetail(this.activityDataDto.result.totalRetails, this.totalActualMainClaimAmt))
  }

  private calculateCostPerEnquiry = (totalEnquiries, totalClaimAmount) => Number.isFinite(totalClaimAmount / totalEnquiries) ? (totalClaimAmount / totalEnquiries).toFixed(2) : 0
  private calculateCostPerHotEnquiry = (totalHotEnquiries, totalClaimAmount) => Number.isFinite(totalClaimAmount / totalHotEnquiries) ? (totalClaimAmount / totalHotEnquiries).toFixed(2) : 0
  private calculateCostPerBooking = (totalBookings, totalClaimAmount) => Number.isFinite(totalClaimAmount / totalBookings) ? (totalClaimAmount / totalBookings).toFixed(2) : 0
  private calculateCostPerRetail = (totalRetail, totalClaimAmount) => Number.isFinite(totalClaimAmount / totalRetail) ? totalClaimAmount / totalRetail.toFixed(2) : 0

  private patchActivityReportPhotosFromActivtyNumber(activityNoId) {
    this.activityClaimCreationService.getActivityReportPhotos(activityNoId).subscribe(response => {
      this.fileNamelist = [];
      this.areReportImagesAvailable = true
      const reportImage = response['result'] as Array<MarketingActivityReportImage>
      reportImage.forEach(image => {
        this.fileNamelist.push(image)
      })
    })
  }
  back() {
    this.location.back();
  }
  // validateForm() {
  //   if (this.activityClaimForm.valid && this.validateActivityHeads() && this.validateActivityCheckForVendor()) {
  //     this.openConfirmDialog();
  //   } else {
  //     if (!this.activityClaimForm.valid) {
  //       this.toastr.error('Kindly Select Mandatory Fields', "Mandatory Fields")
  //       this.markFormAsTouched()
  //     }
  //     if (!this.validateActivityHeads()) {
  //       this.toastr.error('Kindly Select Head Document', 'Heads')
  //     }
  //    if (!this.validateActivityCheckForVendor()) {
       
  //       this.toastr.error('Kindly Fill Vendor Name, Bill No, Bill Date', 'Heads')
  //     }
  //   }
  // }

  validateForm() {
    if (this.activityClaimForm.valid && this.validateActivityHeads() && this.validateActivityCheckForVendor()) {
      this.openConfirmDialog();
    } else {
      if (!this.activityClaimForm.valid) {
        this.toastr.error('Kindly Select Mandatory Fields', "Mandatory Fields")
        this.markFormAsTouched();
      }
      if (!this.validateActivityHeads()) {
        this.toastr.error('Kindly Select Head Document', 'Heads');
      }
      if (!this.validateActivityCheckForVendor()) {
        this.toastr.error('Kindly Fill Vendor Name, Bill No, Bill Date', 'Heads');
      }
    } 
  }
  
 
  private validateActivityCheckForVendor(): boolean {
    let isValidName: boolean = true;
  
    if (this.activityClaimHeads) {
      for (const head of this.activityClaimHeads) {
        // Assuming vendorName, billNo, and billDates are properties of the head object
        const vendorNameValid = head.vendorName && typeof head.vendorName === 'string';
        const billNoValid = head.billNo && typeof head.billNo === 'string';
         const billDatesValid = head.billDate !=null && head.billDate!=undefined
        if (!vendorNameValid || !billNoValid || !billDatesValid) {
          isValidName = false;
          break; // Exit the loop if any validation fails for a head
        }
      }
  
      // Mark all fields as touched outside of the loop
      this.vendorName.markAllAsTouched();
      this.billNo.markAllAsTouched();
      this.billDates.markAllAsTouched();
    }
  
    return isValidName;
  }
  
  
  submitData() {
    this.activityClaimCreationService
      .saveActivityClaim(this.buildActivityClaimObject())
      .subscribe(res => {
        if (res) {
          this.toastr.success('marketing activity claim successfully saved.', 'Success')
          this.router.navigate(['..'], { relativeTo: this.claimRt });
        } else {
          this.toastr.error("Error generated while saving", "Transaction Failed");
          this.isSubmitDisable = false;
        }
      }, err => {
        this.toastr.error("Error generated while saving", "Transaction Failed");
        this.isSubmitDisable = false;
      });
  }

  onClaimImageClick(head: ActivityClaimHead, fileEvent: Event, img: HTMLElement) {

    if (fileEvent && fileEvent.target['files'][0]) {
      head.image = fileEvent.target['files'][0]
      this.previewImage(head.image, img)
    }
  }

  onActivityNoKeyPress(event) {
    // console.log(event)
    event.target.value = ''
    this.activityClaimForm.reset()
    this.activityClaimHeads = null
    this.reportImages = null
    this.areHeadsAvailable = false
    this.areReportImagesAvailable = false
  }

  private parseIdAndPatchViewForm() {
    this.claimRt.params.subscribe(prms => {
      this.claimId = atob(prms['claimId']);
      this.fetchDataForId(atob(prms['claimId']))
    })
  }

  private fetchDataForId(claimId: any) {
    // console.log('Activity Claim ID ', claimId)
    this.activityClaimCreationService.getActivityClaimById(claimId).subscribe(
      dto => { this.formForViewSetup(dto['result']) }
    )
  }

  private formForViewSetup(domain: ViewEditActivityClaimDomain) {
  //  console.log(' Domain------->', domain.activityClaimHeaderData.marketingActivityProposalId)
    if (domain) {
      this.activityClaimForm.patchValue(domain.activityClaimHeaderData)
      this.patchDatesForView(domain)
      this.activityClaimForm.controls.hotEnquiry.patchValue(domain.activityClaimHeaderData.hotEnquiries)
      this.activityClaimForm.controls.warmEnquiry.patchValue(domain.activityClaimHeaderData.warmEnquiries)
      this.activityClaimForm.controls.coldEnquiry.patchValue(domain.activityClaimHeaderData.coldEnquiries)
      this.patchActivityHeads(domain.activityClaimHeads)
      this.activityClaimHeads = domain.activityClaimHeads;
      this.patchEffectivenessForView(domain)
      this.patchActivityNumberForView(domain)
      this.approvalDetails = domain.approvalDetails;
      this.dealerInfo = domain.dealerInfo;
      if (domain.reportImages) {
        this.areReportImagesAvailable = true
        domain.reportImages.forEach(image => {
          this.fileNamelist.push(image)
        })
      }
      if (this.isApprove || domain.activityClaimHeaderData.approvedAmount) {
        this.isApprovedAmount = true
        if (this.isApprove) {
          this.activityClaimForm.controls.approvedAmount.enable();
          this.activityClaimForm.controls.approvedAmount.setValidators(Validators.compose([Validators.required, CustomValidators.floatNumber]))
        }
      }
      if (this.isApprove && domain.activityClaimHeaderData.approvedAmount || this.isView) {
        this.isApprovedAmount = true
        if (this.isApprove || this.isView) {
         this.activityClaimCreationService.getActivityReport(domain.activityClaimHeaderData.marketingActivityProposalId).subscribe(res=>{
         
          res['result'].forEach(image => {
            this.reportFileListApprove.push(image)
          })
         })
        }
      }
      
    }
  }

  private patchActivityNumberForView(domain: ViewEditActivityClaimDomain) {
    this.activityClaimForm.controls.activityNumber.patchValue({ activityNumber: domain.activityClaimHeaderData.activityNumber })
  }
  private patchDatesForView(domain: ViewEditActivityClaimDomain) {
    this.activityClaimForm.controls['fromDate'].patchValue(new Date(domain.activityClaimHeaderData.fromDate))
    this.activityClaimForm.controls['toDate'].patchValue(new Date(domain.activityClaimHeaderData.toDate))
    this.activityClaimForm.controls['actualFromDate'].patchValue(new Date(domain.activityClaimHeaderData.actualFromDate))
    this.activityClaimForm.controls['actualToDate'].patchValue(new Date(domain.activityClaimHeaderData.actualToDate))
    this.activityClaimForm.controls['activityCreationDate'].patchValue(new Date(domain.activityClaimHeaderData.activityCreationDate))
    this.activityClaimForm.controls['claimDate'].patchValue(new Date(domain.activityClaimHeaderData.claimDate))
  }

  private patchEffectivenessForView(domain: ViewEditActivityClaimDomain) {
    this.activityClaimForm.controls.activityEffectiveness.patchValue({ effectiveness: domain.activityClaimHeaderData.effectiveness })
  }

  private validateActivityHeads(): boolean {
    let isValid: boolean = false;
    if (this.activityClaimHeads) {
      this.activityClaimHeads.forEach(head => {
        isValid = head.actualClaimAmount >= 0 && (head.image != undefined && head.image != null && typeof head.image != 'string')
      })
    }
    return isValid;
  }

  // private validateActivityCheckForVendor(): boolean {
  //   let isValidName: boolean = false;
  //   if (this.activityClaimHeads) {
  //     this.activityClaimHeads.forEach(head => {
  //       this.vendorName.markAllAsTouched();
  //       this.billNo.markAllAsTouched();
  //       this.billDates.markAllAsTouched();
  //       console.log(head.vendorName,'ddddd')
  //       isValidName = (head.vendorName != undefined && head.vendorName != null && typeof head.vendorName != 'string')
  //     })
  //   }
  //   return isValidName;
  // }

  private patchCalculations(activityDetails: ActivityDetailsDomain) {
    this.activityClaimForm.controls.costPerEnquiry.patchValue(this.calculateCostPerEnquiry(activityDetails.totalEnquiry, this.totalActualAmount))
    this.activityClaimForm.controls.costPerHotEnquiry.patchValue(this.calculateCostPerHotEnquiry(activityDetails.hotEnquiries, this.totalActualAmount))
    this.activityClaimForm.controls.costPerBooking.patchValue(this.calculateCostPerBooking(activityDetails.totalBooking, this.totalActualAmount))
    this.activityClaimForm.controls.costPerRetail.patchValue(this.calculateCostPerRetail(activityDetails.totalRetails, this.totalActualAmount))
  }

  private buildActivityClaimObject() {
    let claim: any = this.activityClaimForm.getRawValue() as ActivitiyClaim

    claim['activityCreationDate'] = null
    claim['activityType'] = null
    claim['fromDate'] = null
    claim['toDate'] = null
    claim['actualFromDate'] = null
    claim['actualToDate'] = null
    claim['location'] = null
    claim['activityNo'] = null

    const claimHeads = []
    this.activityClaimHeads.forEach(element => {
      claimHeads.push({
        actualClaimAmount: element.actualClaimAmount,
        id: element.id,
        image: element.image ? element.image : null,
        gstPercent: element.gstPercent,
        gstAmount: element.gstAmount,
        headName: element.headName,
        vendorName: element.vendorName,
        billNo: element.billNo,
        billDate: element.billDate
      })

    })
    claim.claimHeads = claimHeads
    claim.totalActualClaimAmount = this.totalActualMainClaimAmt
    claim.marketingActivityProposalId = this.selectedActivityNo.id
    claim.activityEffectiveness = claim.activityEffectiveness.effectiveness

    Object.keys(claim).forEach(key => (claim[key] == null) && delete claim[key]);
    // console.log('claim is ', claim)
    return this.obj2fd(claim)

  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Activity Claim?';
    if (this.isEdit) {
      message = 'Do you want to update Activity Claim?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.editForm();
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

  private previewImage(file, img: HTMLElement) {
    var mimeType = file.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = (event) => {
      img['src'] = event.target['result']
    }
  }

  private getActivityEffectiveness() {
    this.activityClaimCreationService.getActivityEffectiveness().subscribe(res => {
      this.dropdownActivityEffectiveness = res as BaseDto<Array<ActivityEffectivenessDomain>>
    })
  }

  displayActivityNoFn(actNo: ActivityNumberDomain) {
    return actNo ? actNo.activityNumber : undefined
  }

  fileSelctionChanges(fileEvent: Event) {
    if (fileEvent && fileEvent.target['files'][0]) {
      this.fileGstInvoice = fileEvent.target['files'][0]
    }
  }

  private obj2fd = (obj, form?, namespace?) => {
    let fd = form || new FormData();
    let formKey;

    for (let property in obj) {
      if (obj.hasOwnProperty(property)) {
        if (namespace) {
          formKey = namespace + '[' + property + ']';
        } else {
          formKey = property;
        }
        if (obj[property] instanceof Date) {
          fd.append(formKey, obj[property].toISOString());
        } else if (typeof obj[property] === 'object'
          && !(obj[property] instanceof File)
          && !(obj[property] instanceof Blob)) {
          this.obj2fd(obj[property], fd, formKey);
        } else {
          fd.append(formKey, obj[property]);
        }
      }
    }
    return fd;
  }

  keyNumbersOnly(event: any) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  keypressValidateVendorName(event:any){
    const pattern = /[a-zA-z ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }

  }
  validateBillNo(event:any){
    const pattern = /^[a-zA-Z0-9]+$/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }
  clearAll() {
    this.activityClaimForm.reset()
    this.reportImages = []
    this.activityClaimHeads = []
    this.areHeadsAvailable = false
    this.areReportImagesAvailable = false
  }

  navigateToMain() {
    this.location.back();
  }

  private markFormAsTouched() {
    for (const key in this.activityClaimForm.controls) {
      if (this.activityClaimForm.controls.hasOwnProperty(key)) {
        this.activityClaimForm.controls[key].markAsTouched();
      }
    }
  }

  private checkOperationType() {
    // console.log(this.claimRt.snapshot.routeConfig.path)
    this.isView = this.claimRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.claimRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
    this.isApprove = this.claimRt.snapshot.routeConfig.path.split('/')[0] == 'approve'
    if (this.isApprove) {
      this.isView = true;
    }
  }

  private initOperationForm() {
    if (this.isView) {
      this.viewActivityClaimForm()
    }
    else {
      this.createActivityClaimForm()
    }
  }

  compareFnActivityEffectiveness(c1: ActivityEffectivenessDomain, c2: ActivityEffectivenessDomain): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.effectiveness === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.effectiveness;
    }
    return c1 && c2 ? c1.effectiveness === c2.effectiveness : c1 === c2;
  }


  approveRejectClaim(functionalKey: string) {
    if (this.activityClaimForm.valid) {
      this.openConfirmDialogApprove(functionalKey);
    } else {
      this.activityClaimForm.markAllAsTouched();
      this.toastr.error("Enter Mandatory Fields", "Mandatory Fields")
    }

  }

  aproveClaim(result: DialogResult, approvalStatus: string) {

    this.activityClaimCreationService.approveClaim({
      approvedAmount: this.activityClaimForm.controls.approvedAmount.value,
      activityClaimId: this.claimId,
      remark: result.remarkText,
      approvalStatus: approvalStatus
    }).subscribe(res => {
      if (res) {
        this.toastr.success(res['message'], 'Approved');
        this.router.navigate(['../..'], { relativeTo: this.claimRt })
      } else {
        this.toastr.error("Error generated while saving", "Transaction Failed");
        this.isSubmitDisable = false;
      }
    }, error => {
      this.toastr.error("Error generated while saving", "Transaction Failed");
      this.isSubmitDisable = false;
    });

  }


  openConfirmDialogApprove(functionalKey): void | any {
    let message = 'Do you want to ' + functionalKey + ' Activity Claim?';
    const confirmationData = this.setConfirmationModalDataApprove(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      if (result) {
        if (result.button === 'Confirm') {
          this.isSubmitDisable = true;
          this.aproveClaim(result, functionalKey)
        }
      }
    });
  }

  private setConfirmationModalDataApprove(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    confirmationData.remarkConfig = {
      source: Source.APPROVE
    }
    return confirmationData;
  }

  validation(event: any) {
    const proposalApprovedAmount = this.activityClaimForm.controls.proposalApprovedAmount.value
    const approvedAmount = event.target.value;
    if (approvedAmount > proposalApprovedAmount) {
      this.toastr.warning("Approved Amount should not be greater than Proposal Approved Amount");
      this.activityClaimForm.controls.approvedAmount.reset();
      return false;

    }

  }

  updateForm() {
    let hasEmptyValues = false;
    this.activityClaimHeads.forEach(element => {
      if (element.vendorName === '' || element.billNo === '' || element.billDate === '') {
        hasEmptyValues = true;
      }
    });
    if (hasEmptyValues) {
    this.vendorName.markAsTouched();
      this.toastr.error("Vendor Name, Bill No, and Bill Date are required for all items.");
    } else {
      this.openConfirmDialog();
    }
  }
 
  editForm() { 
    const formData = new FormData();
    formData.append('claimId', this.claimId.toString()); // Assuming claimId is a string, convert if needed
    formData.append('claimStatus', this.activityClaimForm.controls.claimStatus.value);
    this.activityClaimHeads.forEach((element, index) => {
      // console.log(element,'')

       formData.append(`activityClaimHeads[${index}].actualClaimAmount`, String(element.actualClaimAmount));
      formData.append(`activityClaimHeads[${index}].id`, String(element.id));
   
      formData.append(`activityClaimHeads[${index}].gstPercent`, String(element.gstPercent));
      formData.append(`activityClaimHeads[${index}].gstAmount`, String(element.gstAmount));
      formData.append(`activityClaimHeads[${index}].headName`, element.headName);
      formData.append(`activityClaimHeads[${index}].vendorName`, element.vendorName);
      formData.append(`activityClaimHeads[${index}].billNo`, element.billNo);
      const dateObject = new Date(element.billDate);
     formData.append(`activityClaimHeads[${index}].billDateTran`, this.datePipe.transform(dateObject, 'yyyy-MM-dd  h:mm:ssZZZZZ'));
      if (element.image instanceof File) {
        formData.append(`activityClaimHeads[${index}].image`, element.image, element.image.name);
      }
    });
    this.activityClaimCreationService.updateForm(formData).subscribe(res => {
      if(res){
        this.toastr.success(res['message']);
        this.location.back();
      }else{
        this.toastr.error(res['message']);
      }
    });
  }
  

  
}




