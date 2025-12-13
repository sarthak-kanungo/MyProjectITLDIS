import { AfterContentChecked, AfterContentInit, AfterViewInit, Component, Inject, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDialogRef, MAT_DIALOG_DATA, ThemePalette } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { exit } from 'process';
import { element } from 'protractor';
import { orgHierDepartment, orgHierDeptMarketing, orgHierDeptSales, orgHierDeptService, orgHierDeptSpare } from '../../component/search-itldis-empolyee/search-itldis-employee';
import { OrghierarchyModalService } from '../orghierarchy-modal.service';

@Component({
  selector: 'app-orghierarchy-modal',
  templateUrl: './orghierarchy-modal.component.html',
  styleUrls: ['./orghierarchy-modal.component.css']
})
export class OrghierarchyModalComponent implements OnInit, OnChanges {

  isCheckedActive: boolean;
  isCheckedInactive: boolean
  orgHierForm: FormGroup = new FormGroup({});
  orgHierItemsForm: FormGroup = new FormGroup({});
  orgHierItems: FormArray;
  departmentsList: any
  statusForm: FormGroup
  //zoneViewSale: boolean = true
  //viewOegHierDetailsList:any[]=[]
  orghierarchyForm: FormArray

  viewSalesTableBodyList: any[] = []
  viewSalesTableBodyListHtml: any[] = []
  viewSalesTableHedderList: any[] = []
  salesTableView: boolean
  saveOrgHier: any[] = []

  viewMktTableBodyList: any[] = []
  viewMktTableBodyListHtml: any[] = []
  viewMktTableHedderList: any[] = []
  mktTableView: boolean

  viewServiceTableBodyList: any[] = []
  viewServiceTableBodyListHtml: any[] = []
  viewServiceTableHedderList: any[] = []
  serviceTableView: boolean

  viewSparesTableBodyList: any[] = []
  viewSparesTableBodyListHtml: any[] = []
  viewSparesTableHedderList: any[] = []
  sparesTableView: boolean

  parentArray: any[] = []
  tableView: boolean
  deptName: string
  childArray: any[] = []
  marketing: any[] = []
  service: any[] = []
  spares: any[] = []
  tableListForHeader: any[] = []
  tableListForBody: any[] = []

  orgHierLevelList: any[] = []
  duplicateForSale: any[] = []
  currentRows = 0
  saveOrgHierId: any

  hoEmpOrgHierDetails: any[] = []
  view: boolean = false
  hoOrgHierEmployeeStatus: any;
  newRow: any

  constructor(public dialogRef: MatDialogRef<OrghierarchyModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private orgHierModalService: OrghierarchyModalService,
    private fb: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private activateRoute: ActivatedRoute,
  ) {
    this.orgHierForm = this.fb.group({
      department: ''
    })
  }

  // ngAfterContentChecked(): void {
  //   if (this.zoneViewSale) {
  //     this.orgHierForm = this.createOrgHierForm(this.orgHierLevelList);
  //     this.orgHierForm.addControl('department',new FormControl("department"))
  //   }
  // }
  ngOnChanges() {
  }

  ngOnInit() {
    this.employeMastDepartment();
    this.hoEmployeeOrgHierDetails()
    this.orghierarchyForm = new FormArray([])
  }
  createStatusForm() {
    return this.statusForm = this.fb.group({
      id: [''],
      status: [''],
      departmentName: [''],
      employee_name: [''],
      hierarchyCode: [''],
      hierarchyDesc: [''],
      hoDepartmentId: ['']
    })
  }
  // createOrgHierForm(orgHierFormCntrol): FormGroup {
  //   let group: any = {};
  //   orgHierFormCntrol.forEach(x => {
  //     group[x.LEVEL_DESC] = new FormControl('');
  //   })
  //   return new FormGroup(group);
  // }

  employeMastDepartment() {
    this.orgHierModalService.orgHierDepartmentDropdown().subscribe(res => {
      this.departmentsList = res
    })

  }
  addRow(data) {
    const fg = this.createStatusForm();
    if (data) {
      fg.patchValue(data);
    }
    this.orghierarchyForm.push(fg);
  }

  hoEmployeeOrgHierDetails() {
    this.orgHierModalService.getOrgHierDetailsForEmployee(this.orgHierModalService.hoEmployeeId).subscribe(res => {
      this.hoEmpOrgHierDetails = res
      this.hoEmpOrgHierDetails.forEach(row => {
        row['status'] = row['isActive'] === 'Y' ? true : false;
        row['id'] = row['EmpFieldHierID'];
        this.newRow = row
        this.addRow(this.newRow)
      });
      if (this.hoEmpOrgHierDetails.length !== 0) {
        this.view = true
      }
    })
  }

  onFormSubmit(id: any, status: any) {
    const postdata = {
      id: id,
      status: status
    }
    this.orgHierModalService.updateHoOrgHierEmployeeStatus(postdata).subscribe(res => {
      this.hoOrgHierEmployeeStatus = res
      if (this.hoOrgHierEmployeeStatus.status == 200) {
        this.toastr.success(this.hoOrgHierEmployeeStatus.message, 'Success', {
          timeOut: 1500
        });
      }
    })

  }
  empMastDeptSelect(deptSelect) {
    this.orgHierItemsForm = new FormGroup({});
    this.deptName = deptSelect.value.departmentName
    this.parentArray=[];
    this.parentArray.length = 0
    //this.zoneViewSale = true
    this.orgHierModalService.getLevelForOrgHier(deptSelect.value.id).subscribe(res => {
      this.orgHierLevelList = res
      if(this.orgHierLevelList && this.orgHierLevelList.length>0){
        this.orgHierLevelList.forEach(obj => {
          this.parentArray.push([]);
          this.orgHierItemsForm.addControl(obj.LEVEL_DESC, new FormControl(obj.LEVEL_DESC));
        })
        this.getLevelsForDept(this.orgHierLevelList[0].LEVEL_ID)
      }
    })
  }

  displayFnDepartmentName(department: orgHierDepartment) {
    return department ? department.departmentName : undefined;
  }
  getLevelsForDept(deptId) {
    let orgHierId = 0
    this.orgHierModalService.getLevelDetailsForOrgHier(deptId, orgHierId).subscribe(res => {
      this.childArray = res
      this.parentArray[0]=this.childArray;
    })
  }

  selectLevels(salesHoId, index) {
    //this.zoneViewSale = false;
    let orgHierId = salesHoId.value.org_hierarchy_id;
    this.saveOrgHierId = salesHoId.value.org_hierarchy_id
    this.orgHierModalService.getLevelDetailsForOrgHier(salesHoId.value.level_id, orgHierId).subscribe(res => {
      this.childArray = res;
      this.parentArray[index+1]=this.childArray;
      for(let i=index+2;i<this.parentArray.length;i++){
        this.parentArray[i]=[];
      }
    })
  }


  // addOrgHierItems() {
  //   this.orgHierItems = this.orgHierForm.get('orgHierItems') as FormArray
  // }

  // removeOrgHierItems(i) {
  //   const remove = this.orgHierForm.get('orgHierItems') as FormArray
  //   if (i > 0) {
  //     remove.removeAt(i);
  //   }
  // }

  closeDialog(): void {
    this.dialogRef.close();
  }

  validateForm() {
    this.orgHierForm.markAllAsTouched();
    if (this.orgHierForm.valid) {
      // this.buildJsonForSubmitForm();
      this.viewOegHierDetails()
    }
  }
  buildJsonFromForm() {
    let formJson = {}
    const jsonWithoutKey = []
    this.orgHierLevelList.forEach(items => {
      let value = (items.LEVEL_DESC).replace(" ", "_")
      let val = value.replace("- ", "")
      let fieldValue = this.orgHierItemsForm.get(items.LEVEL_DESC).value.hierarchy_desc
      if (fieldValue !== undefined) {
        formJson[val] = fieldValue
      }

    })
    Object.values(formJson).forEach(element => {
      jsonWithoutKey.push(element)
    })


    return formJson
  }

  viewOegHierDetails() {
    if (this.saveOrgHierId) {
      if (this.deptName === "SALES" && this.orgHierLevelList.length > 0 && this.saveOrgHierId) {
        this.viewSalesTableHedderList = this.orgHierLevelList
        this.viewSalesTableBodyList.push(this.buildJsonFromForm())
        this.currentRows = this.viewSalesTableBodyList.length
        let flag: boolean = false;
        this.viewSalesTableBodyListHtml.forEach(html => {
          let already = Object.entries(html).toString()
          let comming = Object.entries(this.buildJsonFromForm()).toString()
          if (already) {
            flag = true
            this.toastr.warning('Higher Level already assigned')
          }
        })
        if (!flag) {
          this.viewSalesTableBodyListHtml.push(this.buildJsonFromForm())
          this.saveOrgHier.push({ orgHierarchyId: this.saveOrgHierId })
        }
        if (this.viewSalesTableBodyListHtml.length >= 1 && this.viewSalesTableHedderList.length >= 1 && this.saveOrgHierId) {
          this.salesTableView = true
          this.tableView = true
        } else { this.salesTableView = false }

      }
      if (this.deptName === "MARKETING" && this.orgHierLevelList.length > 0 && this.saveOrgHierId) {
        this.viewMktTableHedderList = this.orgHierLevelList
        this.viewMktTableBodyList.push(this.buildJsonFromForm())
        this.currentRows = this.viewMktTableBodyList.length
        let flag: boolean = false;
        this.viewMktTableBodyListHtml.forEach(html => {
          let already = Object.entries(html).toString()
          let comming = Object.entries(this.buildJsonFromForm()).toString()
          if (already) {
            flag = true
            this.toastr.warning('Higher Level already assigned')
          }
        })
        if (!flag) {
          this.viewMktTableBodyListHtml.push(this.buildJsonFromForm())
          this.saveOrgHier.push({ orgHierarchyId: this.saveOrgHierId })
        }
        if (this.viewMktTableBodyList.length > 0 && this.saveOrgHierId) {
          this.mktTableView = true
          this.tableView = true
        }
      }

      if (this.deptName === "SERVICE" && this.saveOrgHierId) {
        this.viewServiceTableHedderList = this.orgHierLevelList
        this.viewServiceTableBodyList.push(this.buildJsonFromForm())
        this.currentRows = this.viewServiceTableBodyList.length
        let flag: boolean = false;
        this.viewServiceTableBodyListHtml.forEach(html => {
          let already = Object.entries(html).toString()
          let comming = Object.entries(this.buildJsonFromForm()).toString()
          if (already) {
            flag = true
            this.toastr.warning('Higher Level already assigned')
          }
        })
        if (!flag) {
          this.viewServiceTableBodyListHtml.push(this.buildJsonFromForm())
          this.saveOrgHier.push({ orgHierarchyId: this.saveOrgHierId })
        }
        if (this.viewServiceTableBodyList.length > 0 && this.saveOrgHierId) {
          this.serviceTableView = true
          this.tableView = true
        }
      }
      if (this.deptName === "SPARE PARTS" && this.saveOrgHierId) {
        this.viewSparesTableHedderList = this.orgHierLevelList
        this.viewSparesTableBodyList.push(this.buildJsonFromForm())
        this.currentRows = this.viewSparesTableBodyList.length
        let flag: boolean = false;
        this.viewSparesTableBodyListHtml.forEach(html => {
          let already = Object.entries(html).toString()
          let comming = Object.entries(this.buildJsonFromForm()).toString()
          if (already) {
            flag = true
            this.toastr.warning('Higher Level already assigned')
          }
        })
        if (!flag) {
          this.viewSparesTableBodyListHtml.push(this.buildJsonFromForm())
          this.saveOrgHier.push({ orgHierarchyId: this.saveOrgHierId })
        }
        // }

        if (this.viewSparesTableBodyList.length > 0 && this.saveOrgHierId) {
          this.sparesTableView = true
          this.tableView = true
        }
      }
    }
    else {
      if (!this.deptName) {
        this.toastr.error('Please Select Department First');
      }
      else {
        this.toastr.error('Please Select Higher Level');

      }
    }
  }

  clearFormData() {
    //this.orgHierForm.reset();
    //this.orgHierLevelList.length=0
    this.toastr.error('Please Select Department First');
  }

  deleteSalesRow(x) {
    //var delBtn = confirm(" Do you want to delete ?");
    // if ( delBtn == true ) {
    this.viewSalesTableBodyListHtml.splice(x, 1);
    this.saveOrgHier.splice(x, 1)
    this.toastr.info('Selected Row Is Deleted');
    if (x == 0) {
      this.salesTableView = false
      // this.tableView = false
    }
    // } 
    // if (x == 0) {
    //   this.viewSalesTableHedderList.length = 0
    // }

    // commented by Kanhaiya
  }

  deleteMktRow(x) {
    this.viewMktTableBodyListHtml.splice(x, 1);
    this.saveOrgHier.splice(x, 1)
    this.toastr.info('Selected Row Is Deleted');
    if (x == 0) {
      this.mktTableView = false
      // this.tableView = false
    }
  }

  deleteServiceRow(x) {
    this.viewServiceTableBodyListHtml.splice(x, 1);
    this.saveOrgHier.splice(x, 1)
    this.toastr.info('Selected Row Is Deleted');
    if (x == 0) {
      this.serviceTableView = false
      // this.tableView = false
    }
  }

  deleteSparesRow(x) {
    this.viewSparesTableBodyListHtml.splice(x, 1);
    this.saveOrgHier.splice(x, 1)
    this.toastr.info('Selected Row Is Deleted');
    if (x == 0) {
      this.sparesTableView = false
      // this.tableView = false
    }
  }

  oegHierSubmitJson() {
    const oegHierSubmitJson = {
      hoEmployeeId: this.orgHierModalService.hoEmployeeId,
      saveOrgHier: this.saveOrgHier
    }
    return oegHierSubmitJson

  }
  submitData() {
    this.orgHierModalService.assigneDeptOrgHier(this.oegHierSubmitJson()).subscribe(res => {
      if (res['status'] === 200) {
        this.toastr.success(res['message'], 'Success');
        this.closeDialog()
      }
      else {
        this.toastr.error(res['message'], 'Error');
      }
    })
  }
}
