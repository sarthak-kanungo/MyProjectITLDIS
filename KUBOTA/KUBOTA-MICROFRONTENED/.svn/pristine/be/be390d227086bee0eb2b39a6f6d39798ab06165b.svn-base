import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DealerDepartmentMasterSearchPagePresenter } from '../dealer-department-master-search-page/dealer-department-master-search-page.presenter';
import { SearchDealerDepartmentService } from './search-dealer-department.service';
import { DepartmentCodeAndName, DepartmentCodeAuto, DepartmentNameDropdown } from '../../domain/dealer-department-master-domain';
import { MatAutocompleteSelectedEvent } from '@angular/material';

@Component({
  selector: 'app-search-dealer-department',
  templateUrl: './search-dealer-department.component.html',
  styleUrls: ['./search-dealer-department.component.scss'],
  providers: [SearchDealerDepartmentService]
})
export class SearchDealerDepartmentComponent implements OnInit {

  departmentsCode: DepartmentCodeAuto;
  departmentsCodeAndName: DepartmentCodeAndName;

  searchDealerDepartmentForm: FormGroup

  constructor(
    private dealerDepartmentMasterSearchPagePresenter: DealerDepartmentMasterSearchPagePresenter,
    private searchDealerDepartmentService: SearchDealerDepartmentService
  ) { }

  ngOnInit() {
    this.searchDealerDepartmentForm = this.dealerDepartmentMasterSearchPagePresenter.dealerDepartmentSearchDetailsForm
    this.getDepartmentName()
  }

  getDepartmentCode(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.searchDealerDepartmentService.searchDepartmentCode(value).subscribe(res=>{
        this.departmentsCode=res
      })
    }
  }

  displayFnDeptCode(displayString: DepartmentCodeAuto) {
    return displayString ? displayString.displayString : undefined;
  }

  getDepartmentName(){
    this.searchDealerDepartmentService.searchDepartmentCode('').subscribe(res=>{
      this.departmentsCodeAndName=res
    })
  }

}
