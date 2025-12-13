
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDatepickerInput } from '@angular/material';
import { DataTable, ColumnSearch, NgswSearchTableService } from 'ngsw-search-table';
import { ToastrService } from 'ngx-toastr';
import { BehaviorSubject } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';
import { saveAs } from 'file-saver';
import { HttpResponse } from '@angular/common/http';
import { DashboardService } from './dashboard.service';
import { ActivatedRoute, Router } from '@angular/router';
import { OperationsUtil } from '../utils/operation-util';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [DashboardService]
})
export class DashboardComponent implements OnInit {

  typeList: string[] = [];// ['Sales', 'Marketing', 'Service', 'Spare', 'Warranty'];
  searchGroupForm: FormGroup;
  searchobj
  max: Date | null
  serverDate: Date;
  minDate: Date;
  maxDate: Date
  newdate = new Date()
  key = 'dashboard';
  isKai: boolean = false;
  parentArray: any[] = []
  childArray: any[] = []
  orgHierLevelList: any[] = []
  controlsArr: any[] = []
  orgHierarchyId: number
  loginUser: any;
  searchFilterValues: any;
  dealercodeList: any;
  branches: any[] = [];
  chassisData;
  engineData;
  seriesByProduct;
  modelBySeries;
  subModelByModel;
  variants;
  products;

  MReportType: any = "Month Wise";

  actionButtons = [];
  totalTableElements: number
  dataTable: DataTable;
  page: number = 0
  size: number = 10
  searchValue: ColumnSearch;
  totalSearchRecordCount: number;
  clearSearchRow = new BehaviorSubject<string>("");
  searchFlag: boolean = true;

  dashboardType: string;
  salesReportType: string = 'Others';
  salesReportOption: string = 'Model';
  jobcardReport: any[]
  installationReport: any[]
  activityReport: any[]
  complaintReport: any[]
  presaleServiceReport: any[]
  activityKubotaSupportedReport: any[]
  activityDealerOwnReport: any[]
  enquiryReport: any[]
  stockReport: any[]
  retailReport: any[]

  M_budgetStatusReport: any[];
  M_propoalStatusReport: any[];
  M_enquiryStatuReport: any[];
  M_claimStatusReport: any[];
  approvedProposalTillDateAmount = 0

  goodwillStatusReport: any[];
  pcrStatusReport: any[];
  wcrStatusReport: any[];
  settlementStatusReport: any[];
  total: number = 0;
  totalCountSum: number = 0;

  // variable declaration By Ankit
  claimUpload: number = 0;
  claimApprovedCount: number = 0;
  claimRejectCount: number = 0;
  claimRejectAmount: number = 0;
  claimApprovedAmount: number = 0;
  pendingForApprovalCount: number = 0;
  pendingForApprovalAmount: number = 0
  age: number = 0
  ages: number = 0
  dhApproval: number = 0
  completedCount: number = 0
  completedAmount: number = 0
  pendingCount: number = 0
  pendingAmount: number = 0
  reportUpload: number = 0;
  reportPending: number = 0;
  contactRecieve: number = 0;
  hpRecieve: number = 0;
  bookingRecieve: number = 0
  retailDone: number = 0
  drop: number = 0
  lost: number = 0
  opencontact: number = 0
  openhp: number = 0
  //  for issue
  pendingsProposalCount: number = 0
  proposalReceivedsAmount: number = 0
  pendingsProposalAmount: number = 0
  //  
  proRecAmount: number = 0
  proposalRecieved: number = 0
  pendingForApprovalsAmount: number = 0
  proposalRejectedCount: number = 0
  proposalRejectedAmount: number = 0
  proposalHoldCount: number = 0
  proposalHoldAmount: number = 0
  proposalApprovedCount: number = 0
  proposalApprovedAmount: number = 0
  pendingForApprovalscount: number = 0
  budgetAvailableCount: number = 0
  budgetAvailableAmount: number = 0
  proRecievedCount: number = 0
  proRejectedCount: number = 0
  proRejectedAmount: number = 0
  proApprovedCount: number = 0
  proApprovedAmount: number = 0
  balanceBudget: number = 0
  pendingForApprovalAmounts: number = 0

  prosrecievedAmount: number = 0
  // variable declare

  penLastMonthCount: number = 0
  penLastMonthAmount: number = 0
  prorecievedCount: number = 0;
  prorecievedAmount: number = 0;
  newProposalRejectedCount: number = 0;
  newProposalRejectedAmount: number = 0;
  newProposalHoldCount: number = 0;
  newProposalHoldAmount: number = 0;
  newProposalApprovedCount: number = 0
  newProposalApprovedAmount: number = 0
  newPendingApprovalCount: number = 0
  newPendingApprovalAmount: number = 0

  // 
  constructor(private fb: FormBuilder,
    private tableDataService: NgswSearchTableService,
    private dateService: DateService,
    private dashboardService: DashboardService,
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit() {
    this.dashboardType = OperationsUtil.operation(this.route);
    this.searchGroupForm = this.fb.group({
      dashboardType: [this.dashboardType],
      salesReportType: ['Others'],
      salesReportOption: ['modelWise'],
      fromDate: [null],
      toDate: [null],
      product: [null],
      series: [null],
      machineModel: [null],
      subModel: [null],
      variant: [null],
      chassisNo: [null],
      orgHierarchyId: [null],
      dealerCode: [null],
      branch: [null],
      dealerName: [{ value: null, disabled: true }],
      budgetReportType: ["MonthWise"],
      mReportType: ["MonthWise"]
    });
    this.getDateFromServer();

    this.loginUser = localStorage.getItem('kubotaUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))

    this.searchGroupForm.controls.mReportType.valueChanges.subscribe(value => {
      if (value) {
        this.MReportType = value;
        this.total = null,
          this.totalCountSum = null
        this.reportUpload = null
        this.reportPending = null
        this.contactRecieve = null
        this.hpRecieve = null
        this.bookingRecieve = null
        this.retailDone = null
        this.drop = null
        this.lost = null
        this.opencontact = null
        this.openhp = null
        this.searchobj['mReportType'] = value;
        this.dashboardService.getSearchRecords(this.searchobj, 'EnquiryStatus').subscribe(res => {
          this.M_enquiryStatuReport = res['result'];

          if(this.M_enquiryStatuReport){
          this.M_enquiryStatuReport.forEach((element: any) => {
            this.total = this.total + element.approvedProposalTillDateAmount;
            this.totalCountSum = this.totalCountSum + element.approvedProposalTillDate
            this.reportUpload = this.reportUpload + element.reportUploadedTillDate
            this.reportPending = this.reportPending + element.reportPending
            this.contactRecieve = this.contactRecieve + element.contactReceived
            this.hpRecieve = this.hpRecieve + element.hpReceived
            this.bookingRecieve = this.bookingRecieve + element.bookingReceived
            this.retailDone = this.retailDone + element.retailDone
            this.drop = this.drop + element.drop
            this.lost = this.lost + element.lost
            this.opencontact = this.opencontact + (Number(element.openContact))
            this.openhp = this.openhp + (Number(element.openHp))
          });
        }
          //  }
        })
      }
    });



    this.dashboardService.getFunctionalityMappedToUser(this.loginUser.loginUserId).subscribe(response => {
      if (response && response.result) {
        response.result.forEach(data => {
          if (data.functionality === 'Dashboard Module') {
            if (data.children.length > 0) {
              data.children.forEach(child => {
                if (child.functionality == 'Dashboard') {
                  child.children.forEach(roles => {
                    this.typeList.push(roles.functionality);
                  });
                }
              });
            }
          }
        })
      }
    });
    // this.searchFilterValues = localStorage.getItem(this.key)
    // this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))


    // this.searchGroupForm.controls.dashboardType.valueChanges.subscribe(value => {
    //   if(value){
    //     this.dashboardType = value;

    if (this.loginUser.userType != 'dealer') {
      if (this.dashboardType == 'Service' || this.dashboardType == 'Warranty')
        this.getLevelByDeprtment('SE');
      if (this.dashboardType == 'Sales')
        this.getLevelByDeprtment('SA');
    }
    //     this.searchData();
    //   }
    // });
    this.searchGroupForm.controls.salesReportType.valueChanges.subscribe(value => {
      if (value) {
        this.salesReportType = value;
      }
    });
    this.searchGroupForm.controls.salesReportOption.valueChanges.subscribe(value => {
      if (value) {
        if (value == 'modelWise')
          this.salesReportOption = 'Model';
        else if (value == 'regionWise')
          this.salesReportOption = 'Region';
        else if (value == 'dealerWise')
          this.salesReportOption = 'Dealer';

      }
    });
    // if (this.searchFilterValues != null || this.searchFilterValues != undefined && this.searchGroupForm != null) {
    //   this.searchGroupForm.patchValue(this.searchFilterValues)
    //   this.searchData();
    // }
    // else{
    //if(this.dashboardType=='Service' || this.dashboardType=='Sales'){
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 12);
    this.minDate = backDate;
    this.searchGroupForm.get('fromDate').patchValue(backDate);
    this.searchGroupForm.get('toDate').patchValue(new Date());
    // }else{
    // this.maxDate = this.newdate
    // let backDate = new Date();
    // backDate.setMonth(this.newdate.getMonth() - 1);
    // this.minDate = backDate;
    // this.searchGroupForm.get('fromDate').patchValue(backDate);
    // this.searchGroupForm.get('toDate').patchValue(new Date());
    // }
    //this.searchData();
    //}
    this.getChassis()
    this.getBranches();
    this.dashboardService.getAllProduct().subscribe(res => {
      this.products = res['result'];
    });
    this.searchGroupForm.controls.dealerCode.valueChanges.subscribe((res) => {
      if (res) {
        this.dashboardService.getDealerCodeList(res).subscribe(response => {
          this.dealercodeList = response;
          if (typeof res === 'object') {
            this.branches = this.branches.filter(obj => obj['dealercode'] == res['code']);
            this.searchGroupForm.controls.branch.reset();
          }
        })
      } else {
        // this.getBranches();
        this.searchGroupForm.controls.branch.reset();
      }
    })
    if (this.loginUser.userType != 'dealer') {
      //this.getLevelByDeprtment('SA');
      this.isKai = true;
    }

    if (this.searchGroupForm.controls.salesReportOption.value == undefined) {
      this.searchGroupForm.controls.salesReportOption.patchValue('modelWise')
    }
    // this.searchData();

  }
  getBranches() {
    this.dashboardService.getBranchCodeList().subscribe(res => {
      this.branches = res['result'];
      // if(this.branches && this.branches.length==1){
      //   this.searchGroupForm.controls.branch.patchValue(this.branches[0]['BRANCH_ID']);
      // }
    });
  }
  getChassis() {
    this.searchGroupForm.get('chassisNo').valueChanges.subscribe(txt => {
      if (txt) {
        this.dashboardService.autoCompleteSearchChassisNo(txt).subscribe(res => {
          this.chassisData = res['result'];
        });
      }
    });
  }
  private getDateFromServer() {
    this.dashboardService.getSystemGeneratedDate().subscribe(dateRes => {
      if (dateRes['result']) {
        this.maxDate = new Date(dateRes['result'])
      }
    });
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (this.dashboardType == 'Service' || this.dashboardType == 'Sales') {
      if (event && event['value']) {
        this.minDate = new Date(event['value']);
        let maxDate = new Date(event['value']);
        maxDate.setMonth(maxDate.getMonth() + 12);
        if (maxDate > this.newdate)
          this.maxDate = this.newdate;
        else
          this.maxDate = maxDate;
        if (this.searchGroupForm.get('toDate').value > this.maxDate)
          this.searchGroupForm.get('toDate').patchValue(this.maxDate);
      }
    } else {
      if (event && event['value']) {
        this.minDate = new Date(event['value']);
        let maxDate = new Date(event['value']);
        maxDate.setMonth(maxDate.getMonth() + 1);
        if (maxDate > this.newdate)
          this.maxDate = this.newdate;
        else
          this.maxDate = maxDate;
        if (this.searchGroupForm.get('toDate').value > this.maxDate)
          this.searchGroupForm.get('toDate').patchValue(this.maxDate);
      }
    }
  }
  selectProduct(value) {
    this.dashboardService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response['result'];
      this.modelBySeries = null;
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectSeries(value) {
    this.dashboardService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response['result']
      this.subModelByModel = null;
      this.variants = null;
    })
  }

  selectModel(value) {
    this.dashboardService.getSubModelByModel(value).subscribe(response => {
      this.subModelByModel = response['result']
      this.variants = null;
    })
  }

  selectSubModel(value) {
    this.dashboardService.getVariantBySubModel(value).subscribe(response => {
      this.variants = response['result']
    })
  }

  getLevelByDeprtment(dept) {
    if (this.controlsArr.length > 0) {
      this.controlsArr.forEach(controlName => {
        this.searchGroupForm.removeControl(controlName);
      });
    }
    this.parentArray = [];
    this.parentArray.length = 0
    this.dashboardService.getLevelByDepartment(dept).subscribe(res => {
      this.orgHierLevelList = res['result'];
      if (this.orgHierLevelList && this.orgHierLevelList.length > 0) {
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.searchGroupForm.addControl(obj.LEVEL_CODE, new FormControl(obj.LEVEL_CODE));
          this.controlsArr.push(obj.LEVEL_CODE);
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }


  getLevelsForDept(levelId) {
    let orgHierId = 0
    this.dashboardService.getHierarchyByLevel(levelId, orgHierId).subscribe(res => {
      this.childArray = res['result']
      this.parentArray[0] = this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.orgHierarchyId = orgHierId;
    this.searchGroupForm.controls.orgHierarchyId.patchValue(this.orgHierarchyId);
    this.dashboardService.getHierarchyByLevel(salesHoId.value.child_level, orgHierId).subscribe(res => {
      this.childArray = res['result'];
      this.parentArray[index + 1] = this.childArray;
      for (let i = index + 2; i < this.parentArray.length; i++) {
        this.parentArray[i] = [];
      }
    })
  }

  getDealerName(event) {
    if (typeof event.option.value === 'object') {
      this.searchGroupForm.controls.dealerName.patchValue(event.option.value.name);
    } else {
      this.searchGroupForm.controls.dealerName.patchValue('');
    }
  }

  displayDealerCodeFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
  searchData() {
    this.claimUpload = null;
    this.claimApprovedCount = null;
    this.claimRejectCount = null;
    this.claimRejectAmount = null;
    this.claimApprovedAmount = null;
    this.pendingForApprovalCount = null;
    this.pendingForApprovalAmount = null
    this.age = null
    this.ages = null
    this.dhApproval = null
    this.completedCount = null
    this.completedAmount = null
    this.pendingCount = null
    this.pendingAmount = null
    this.reportUpload = null
    this.reportPending = null
    this.contactRecieve = null
    this.hpRecieve = null
    this.bookingRecieve = null
    this.retailDone = null
    this.drop = null
    this.lost = null
    this.opencontact = null
    this.openhp = null
    this.pendingsProposalCount = null
    this.pendingsProposalAmount = null
    this.proposalRecieved = null
    this.pendingForApprovalsAmount = null
    this.proposalRejectedCount = null
    this.proposalRejectedAmount = null
    this.proposalHoldCount = null
    this.proposalHoldAmount = null
    this.proposalApprovedCount = null
    this.proposalApprovedAmount = null
    this.pendingForApprovalscount = null
    this.budgetAvailableCount = null
    this.budgetAvailableAmount = null
    this.proRecievedCount = null
    this.proRejectedCount = null
    this.proRejectedAmount = null
    this.proApprovedCount = null
    this.proApprovedAmount = null
    this.balanceBudget = null
    this.pendingForApprovalAmounts = null
    this.proposalReceivedsAmount = null
    this.prosrecievedAmount = null
    this.penLastMonthCount = null
    this.penLastMonthAmount = null
    this.total = null,
    this.totalCountSum = null
  //   this.prorecievedCount= null
  //  this. prorecievedAmount =null
  //   this.newProposalRejectedCount = null
  //  this. newProposalRejectedAmount = null
    this.newProposalHoldCount= null
   this. newProposalHoldAmount = null
    // this.newProposalApprovedCount = null
    // this.newProposalApprovedAmount=null
   this. newPendingApprovalCount = null
    this.newPendingApprovalAmount = null
    this.searchobj = this.searchGroupForm.value;

    if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    this.searchobj = this.removeNullFromObjectAndFormatDate(this.searchobj);
    delete this.searchobj.page;
    delete this.searchobj.size;

    this.searchFlag = true;

    if (Object.keys(this.searchobj).length > 0) {
      if (!this.dateService.checkValidDatesInput(this.searchobj.fromDate, this.searchobj.toDate)) {
        this.toastr.error("Please Select Due Date Range.");
        return false;
      }

      localStorage.setItem(this.key, JSON.stringify(this.searchobj))

      this.searchobj.page = this.page
      this.searchobj.size = this.size
      this.searchobj.dealerId = this.searchobj.dealerCode ? this.searchobj.dealerCode['id'] : null;
      this.searchobj.branchId = this.searchobj.branch ? this.searchobj.branch : null;
      this.searchobj.orgHierId = this.orgHierarchyId;

      delete this.searchobj.dealerCode;
      delete this.searchobj.branch;

      this.dashboardType && this.dashboardService.getSearchRecords(this.searchobj, this.dashboardType).subscribe(res => {

        // this.dataTable = this.tableDataService.convertIntoDataTable(res['result']);
        // this.totalTableElements = res['count'];
        if (res && res['result']) {
          this.jobcardReport = res['result']['jobcardReport'];
          this.installationReport = res['result']['installationReport'];
          this.activityReport = res['result']['activityReport'];
          this.complaintReport = res['result']['complaintReport'];
          this.presaleServiceReport = res['result']['presaleServiceReport'];
          this.activityKubotaSupportedReport = res['result']['activityKubotaSupportedReport'];
          this.activityDealerOwnReport = res['result']['activityDealerOwnReport'];
          this.enquiryReport = res['result']['enquiryReport'];
          this.stockReport = res['result']['stockReport'];
          this.retailReport = res['result']['retailReport'];  
          this.goodwillStatusReport = res['result']['goodwillStatusReport'];
          this.pcrStatusReport = res['result']['pcrStatusReport'];
          this.wcrStatusReport = res['result']['wcrStatusReport'];
          this.settlementStatusReport = res['result']['settlementStatusReport'];
          this.M_budgetStatusReport = res['result']['budgetStatusReport'];
          
          if(this.M_budgetStatusReport){
          this.M_budgetStatusReport.forEach((add:any) => {
            this.budgetAvailableCount = this.budgetAvailableCount + add.budgetAvailable
            this.budgetAvailableAmount = this.budgetAvailableAmount + add.budgetAvailableAmount
            this.proRecievedCount = this.proRecievedCount + add.propoalReceived
            this.proRejectedCount = this.proRejectedCount + add.proposalRejected
            this.proRejectedAmount = this.proRejectedAmount + add.proposalRejectedAmount
            this.proApprovedCount = this.proApprovedCount + add.proposalApproved
            this.proApprovedAmount = this.proApprovedAmount + add.proposalApprovedAmount
            this.balanceBudget = this.balanceBudget + add.balanceBudget
            this.prosrecievedAmount = this.prosrecievedAmount + add.proposalReceivedAmount
          })
        }
          this.M_propoalStatusReport = res['result']['proposalStatusReport'];
          if(this.M_propoalStatusReport){
          this.M_propoalStatusReport.forEach((eles:any) => {
            this.penLastMonthCount = this.penLastMonthCount + eles.pendingProposal
            this.penLastMonthAmount = this.penLastMonthAmount + eles.pendingProposalAmount
            this.prorecievedCount = this.prorecievedCount + eles.propoalReceived
            this.prorecievedAmount = this.prorecievedAmount + eles.proposalReceivedAmount
            this.newProposalRejectedCount = this.newProposalRejectedCount + eles.proposalRejected
            this.newProposalRejectedAmount = this.newProposalRejectedAmount + eles.proposalRejectedAmount
            this.newProposalHoldCount = this.newProposalHoldCount + eles.proposalHold;
            this.newProposalHoldAmount = this.newProposalHoldAmount + eles.proposalHoldAmount;
            this.newProposalApprovedCount = this.newProposalApprovedCount + eles.proposalApproved
            this.newProposalApprovedAmount = this.newProposalApprovedAmount + eles.proposalApprovedAmount
            this.newPendingApprovalCount = this.newPendingApprovalCount + eles.pendingForApproval
            this.newPendingApprovalAmount = this.newPendingApprovalAmount + eles.pendingForApprovalAmount
          })
        }
          this.M_enquiryStatuReport = res['result']['enquiryStatusReport'];
          if(this.M_enquiryStatuReport){
          this.M_enquiryStatuReport.forEach((element: any) => {
            this.total = this.total + element.approvedProposalTillDateAmount;
            this.totalCountSum = this.totalCountSum + element.approvedProposalTillDate
            this.reportUpload = this.reportUpload + element.reportUploadedTillDate
            this.reportPending = this.reportPending + element.reportPending
            this.contactRecieve = this.contactRecieve + element.contactReceived
            this.hpRecieve = this.hpRecieve + element.hpReceived
            this.bookingRecieve = this.bookingRecieve + element.bookingReceived
            this.retailDone = this.retailDone + element.retailDone
            this.drop = this.drop + element.drop
            this.lost = this.lost + element.lost
            this.opencontact = this.opencontact + (Number(element.openContact))
            // console.log(this.opencontact)
            this.openhp = this.openhp + (Number(element.openHp))
          });
        }
          this.M_claimStatusReport = res['result']['claimStatusReport'];
          if(this.M_claimStatusReport){
          this.M_claimStatusReport.forEach(ele => {
            this.claimUpload = this.claimUpload + ele.claimUploaded
            this.claimApprovedCount = this.claimApprovedCount + ele.claimApproved
            this.claimApprovedAmount = this.claimApprovedAmount + ele.claimApprovedAmount
            this.claimRejectCount = this.claimRejectCount + ele.claimRejected;
            this.claimRejectAmount = this.claimRejectAmount + ele.claimRejectedAmount;
            this.pendingForApprovalCount = this.pendingForApprovalCount + ele.pendingForApproval
            this.pendingForApprovalAmount = this.pendingForApprovalAmount + ele.pendingForApprovalAmount
            this.age = this.age + ele.pendingLess30;
            this.ages = this.ages + ele.pending31To45;
            this.dhApproval = this.dhApproval + ele.dhApproval;
            this.completedCount = this.completedCount + ele.completed
            this.completedAmount = this.completedAmount + ele.completedAmount
            this.pendingCount = this.pendingCount + ele.pending
            this.pendingAmount = this.pendingAmount + ele.pendingAmount
          })
        }
        }
      }, err => {
        this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalSearchRecordCount = 0;
      });

    } else {
      this.toastr.error("Please fill atleast one input field.");
    }

  }

  clearForm() {
    this.searchGroupForm.reset();
    this.maxDate = this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth() - 12);
    this.minDate = backDate;
    this.searchGroupForm.get('fromDate').patchValue(backDate);
    this.searchGroupForm.get('toDate').patchValue(new Date());
    this.searchGroupForm.get('dashboardType').patchValue(this.dashboardType);
    if (this.dashboardType == 'Marketing') {
      this.searchGroupForm.controls.budgetReportType.patchValue('MonthWise');
      this.searchGroupForm.controls.mReportType.patchValue('MonthWise');
      this.searchGroupForm.controls.salesReportType.patchValue('Others');
      this.searchGroupForm.controls.salesReportOption.patchValue('modelWise');
    }
    if (this.dashboardType == 'Sales') {
      this.getLevelByDeprtment('SA');
      this.searchGroupForm.controls.salesReportType.patchValue('Others');
      this.searchGroupForm.controls.salesReportOption.patchValue('modelWise');
    }
    if (this.dashboardType == 'Service' || this.dashboardType == 'Warranty')
      this.getLevelByDeprtment('SE');

    // this.searchData();
  }

  pageSizeChange(event: any) {
    this.page = event.page;
    this.size = event.size;
    this.searchFlag = false;
    this.searchData();
  }

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach(element => {
        if (element && (searchObject[element] === null || searchObject[element] === "" || searchObject[element] === undefined)) {
          delete searchObject[element];
        }
        if (searchObject[element] && (element === 'fromDate' || element === 'toDate')) {
          searchObject[element] = this.dateService.getDateIntoYYYYMMDD(searchObject[element])
        }
      });
      return searchObject;
    }
  }

  etSearchDateValueChange(searchDate, ColumnKey) {
    const modifiedDate = this.dateService.getDateIntoDDMMYYYY(searchDate);
    this.searchValue = new ColumnSearch(modifiedDate, ColumnKey);
  }

  getTotalValueSales() {
    var map = new Map();
    return map;
  }

  getTotalValueService() {
    var map = new Map();
    return map;
  }

  getColor(name) {
    switch (name) {
      case "Sales":
        return "#f60";
      case "Service":
        return "rgb(210, 46, 46)";
      case "Warranty":
        return "rgb(55, 141, 59)";
      case "Spare":
        return "rgb(0, 150, 166)";
      case "Marketing":
        return "rgb(96, 96, 96)";
    }

  }

  callDashboard(dashboardType) {
    this.dashboardType = dashboardType;
    if (this.loginUser.userType != 'dealer') {
      if (this.dashboardType == 'Service')
        this.getLevelByDeprtment('SE');
      if (this.dashboardType == 'Sales')
        this.getLevelByDeprtment('SA');
    }
    this.searchData();
  }
  mainDashboard() {
    this.router.navigate(['./dashboard'])
  }
}
