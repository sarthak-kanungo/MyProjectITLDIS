import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormGroup } from "@angular/forms";
import { MatAutocompleteSelectedEvent } from "@angular/material/autocomplete";
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import {
  ColumnSearch,
  DataTable,
  NgswSearchTableService,
} from "ngsw-search-table";
import { ToastrService } from "ngx-toastr";
import { BehaviorSubject } from "rxjs-compat";
import {
  ButtonAction,
  ConfirmationBoxComponent,
  ConfirmDialogData,
} from "src/app/confirmation-box/confirmation-box.component";
import {
  DialogResult,
  Source,
} from "src/app/confirmation-box/confirmation-data";
import { CustomValidators } from "src/app/utils/custom-validators";
import { EmployeeMasterCreatePageService } from "../../dealer-employee-master/component/employee-master-create-page/employee-master-create-page.service";
import { AutoDealerDetails } from "../../dealer-employee-master/domain/dealer-employee-domain";
import {
  AutoEmployeeCode,
  AutoEmployeeName,
  AutoEmployeeNumber,
} from "../../employee-bank-detail/component/model/employee";
import { EmployeServiceService } from "../../employee-bank-detail/component/service/employe-service.service";
import { LocalStorageService } from "src/app/root-service/local-storage.service";

@Component({
  selector: "app-bank-detail-approval",
  templateUrl: "./bank-detail-approval.component.html",
  styleUrls: ["./bank-detail-approval.component.css"],
  providers: [EmployeeMasterCreatePageService],
})
export class BankDetailApprovalComponent implements OnInit {
  bankApprovalForm: FormGroup;
  employeeCodeList: any;
  employeeMobileList: any;
  employeeNameList: any;
  newDate = new Date();
  min = new Date();
  clearSearchRow = new BehaviorSubject<string>("");
  searchValue: ColumnSearch;
  totalTableElements: number;
  dataTable: DataTable;
  page: number = 0;
  size: number = 10;
  searchFlag: boolean = true;
  claimList: any[] = [];
  allChecked: boolean = false;
  claimIds: number[] = [];
  max = new Date();
  statusList: any = [];
  result: string;
  dialogMsg: string;
  dealerdetailsList: AutoDealerDetails[] = [];
  dealerId: any;
  dealerCode: any;
  userType:any
  constructor(
    private service: EmployeServiceService,
    private employeeMasterCreatePageService: EmployeeMasterCreatePageService,
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private services: EmployeeMasterCreatePageService,
    private tableDataService: NgswSearchTableService,
    private toastr: ToastrService,
    private dialog: MatDialog,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit() {
    this.userType = this.localStorageService.getLoginUser();
   
    let userTypes = this.userType["userType"];
  
    this.bankApprovalForm = this.fb.group({
      employeeCode: [""],
      employeeName: [""],
      fromDate: [],
      toDate: [],
      dealerDetails: [],
      details: new FormArray([]),
    });
    this.getEmployeeCode();
    this.searchData();
  }
  addDeatils(data) {
    let FG = this.fb.group({
      isSelect: [false],
      dealerEmployeeId: [null],
      dealerName: [{ value: "", disabled: true }],
      dealerCode: [{ value: "", disabled: true }],
      bankAccountNo: [{ value: "", disabled: true }],
      bankName: [{ value: "", disabled: true }],
      employeeCode: [{ value: "", disabled: true }],
      employeeName: [{ value: "", disabled: true }],
      ifsCode: [{ value: "", disabled: true }],
      remark: this.result,
      panCardNo: [{ value: "", disabled: true }],
      approvalStatus: [{ value: "", disabled: false }],
    });
    FG.patchValue(data);
    FG.controls.dealerEmployeeId.patchValue(data.id);
    (this.bankApprovalForm.controls.details as FormArray).push(FG);
  }

  ngOnChanges() {}
  setToDate(event: any) {}

  onKeyEmployeeeCode(event: KeyboardEvent) {
    this.onFocusGetEmployeeCodeList(event);
  }

  onFocusGetEmployeeCodeList(value) {
    if (value == null || value == undefined) value = "";

    if (typeof value !== "object") {
      //  this.getEmployeeCode(value);
    } else {
      this.employeeCodeList = null;
    }
  }
  onKeyEmployeeeName(event: KeyboardEvent) {
    this.onKeyEmployeeeName(event);
  }

  c(value) {
    if (value == null || value == undefined) value = "";

    if (typeof value !== "object") {
      //  this.getEmployeeCode(value);
    } else {
      this.employeeNameList = null;
    }
  }

  getEmployeeCode() {
    this.bankApprovalForm.get("employeeCode").valueChanges.subscribe((code) => {
      if (code) {
        this.service.getEmployeeCodeAuto(null, code).subscribe((res) => {
          this.employeeCodeList = res;
        });
      }
    });
  }

  initialQueryParams(event: any) {}
  onUrlChange(event: any) {}
  getEmployeename(value) {
    if (value != null || (value != undefined && typeof value !== "object")) {
      this.service.getEmployeeNameAuto(value).subscribe((res) => {
        this.employeeNameList = res;
      });
    }
  }
  autoDealerDetails(value) {
    if (value != null || (value != undefined && typeof value !== "object")) {
      this.employeeMasterCreatePageService
        .dealerDetailsAuto(value)
        .subscribe((res) => {
          this.dealerdetailsList = res;
        });
    }
  }
  dealerDetailsSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.bankApprovalForm.get("dealerDetails").setErrors(null);
    }
    let dealerVal = this.bankApprovalForm.get("dealerDetails").value;
    if (dealerVal == null) {
      this.bankApprovalForm.get("dealerDetails").setErrors({
        selectFromList: "",
      });
    }
    this.dealerId = this.bankApprovalForm.get("dealerDetails").value.id;
    this.dealerCode = this.bankApprovalForm.get("dealerDetails").value.code;
    // this.reporEmpAutoSuggestion('');
  }

  displayFnDealerDetailes(dealerDetails: AutoDealerDetails) {
    return dealerDetails ? dealerDetails.displayString : undefined;
  }

  // For Search

  searchData() {
    let dealerId: string;
    if (this.bankApprovalForm.get("dealerDetails").value) {
      dealerId = this.bankApprovalForm.get("dealerDetails").value.id;
    }
    // let employeeCode:string
    // if (this.bankApprovalForm.get('employeeCode').value) {
    //   employeeCode=this.bankApprovalForm.get('employeeCode').value.employeeCode

    // }
    let employeeName: string;
    if (this.bankApprovalForm.get("employeeName").value) {
      this.bankApprovalForm.get("employeeName").value.employeeName;
    }
    (this.bankApprovalForm.controls.details as FormArray).clear();
    let searchobj = this.bankApprovalForm.value;
    console.log(searchobj, "searchoj");
    if (this.dataTable != undefined) {
      if (this.searchFlag == true) {
        this.page = 0;
        this.size = 10;
        this.dataTable["PageIndex"] = this.page;
        this.dataTable["PageSize"] = this.size;
      } else {
        this.dataTable["PageIndex"] = this.page;
        this.dataTable["PageSize"] = this.size;
      }
    } else if (this.searchFlag == true) {
      this.page = 0;
      this.size = 10;
    }
    searchobj = this.removeNullFromObjectAndFormatDate(searchobj);
    delete searchobj.page;
    delete searchobj.size;
    this.searchFlag = true;
    searchobj.page = this.page;
    searchobj.size = this.size;
    searchobj.dealerId = dealerId;
    searchobj.employeeName = employeeName;

    // searchobj.employeeCode=employeeCode,
    //   searchobj.employeeName=employeeName

    this.service.searchEmployeeBankApproval(searchobj).subscribe(
      (res) => {
        if (res != null && res["result"] != null) {
          this.claimList = res["result"];
          res["result"].forEach((element) => {
            this.addDeatils(element);
            this.dataTable = this.tableDataService.convertIntoDataTable(
              res["result"]
            );
            this.totalTableElements = res["count"];
            if (this.statusList.indexOf(element["lastApproverName"]) < 0) {
              this.statusList.push(element["lastApproverName"]);
            }
          });
        }
      },
      (err) => {
        this.toastr.error("Search Failed.");
        this.dataTable = null;
        this.totalTableElements = 0;
      }
    );
  }
  pageChange(event: any) {}

  public removeNullFromObjectAndFormatDate(searchObject: object) {
    if (searchObject) {
      Object.keys(searchObject).forEach((element) => {
        if (
          element &&
          (searchObject[element] === null ||
            searchObject[element] === "" ||
            searchObject[element] === undefined)
        ) {
          delete searchObject[element];
        }
      });
      return searchObject;
    }
  }
  checkAll(e) {
    this.allChecked = !this.allChecked;
    (this.bankApprovalForm.controls.details as FormArray).controls.forEach(
      (element) => {
        if (e.checked) {
          element.get("isSelect").setValue(true);
        } else {
          element.get("isSelect").setValue(false);
        }
      }
    );
  }

  bankApprove(approvalType) {
    this.dialogMsg = approvalType;
    let flag = false;
    (this.bankApprovalForm.controls.details as FormArray).controls.forEach(
      (element) => {
        if (element.get("isSelect").value == true) {
          flag = true;
        }
      }
    );
    if (!flag) {
      this.toastr.error("Please check Bank Approval Details");
      return false;
    }

    this.openConfirmDialog(approvalType);
  }

  private openConfirmDialog(approvalType): void | any {
    let message = "Do you want to " + approvalType + " Bank Details?";
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: "500px",
      panelClass: "confirmation_modal",
      data: confirmationData,
    });
    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      if (result && result.button === "Confirm") {
        this.employeeBankGroupApproval(result.remarkText, approvalType);
      }
    });
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = "Confirmation";
    confirmationData.message = message;

    confirmationData.buttonName = ["Confirm", "Cancel"];

    confirmationData.remarkConfig = {
      source: Source.APPROVE,
    };

    if (this.dialogMsg.toLocaleLowerCase() === "rejected") {
      confirmationData.remarkConfig = {
        source: Source.REJECT,
      };
    }
    if (this.dialogMsg.toLocaleLowerCase() === "hold") {
      confirmationData.remarkConfig = {
        source: Source.REJECT,
      };
    }

    return confirmationData;
  }
  employeeBankGroupApproval(remark, approvalType) {
    let jsonObj = {
      employeeBankProposalApprovalList: (
        this.bankApprovalForm.controls.details as FormArray
      ).getRawValue(),
      remark: remark,
      approvalType: approvalType,
    };
    this.service.employeeBankGroupApproval(jsonObj).subscribe((res) => {
      if (res) {
        this.toastr.success(res["message"]);
        if (res["status"] == "200") {
          this.searchData();
        }
      } else {
        this.toastr.error("Bank Approval failed", "Approval Failed");
      }
    });
  }
  actionOnTableRecord(event: any) {}
  displayFnEmployeeCode(employeeCode: AutoEmployeeCode) {
    return employeeCode ? employeeCode.employeeCode : undefined;
  }
  displayFnEmployeeName(employeeName: AutoEmployeeName) {
    return employeeName ? employeeName.employeeName : undefined;
  }
  displayFnEmployeeNumber(mobilenumber: AutoEmployeeNumber) {
    return mobilenumber ? mobilenumber.mobilenumber : undefined;
  }
  clearForm() {
    this.bankApprovalForm.reset();
    this.claimList = null;

    (this.bankApprovalForm.controls.details as FormArray).clear();
  }
}
