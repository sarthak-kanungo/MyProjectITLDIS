import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { DeptDropdownForDesignation, DesignationAuto, } from '../../domain/dealer-designation-master-domain';
import { DealerDesignationMasterPageStore } from '../dealer-designation-master-page/dealer-designation-master-page.store';
import { DealerDepartmentMasterSearchPagePresenter } from '../dealer-designation-master-search-page/dealer-designation-master-search-page.presenter';
import { SearchDesignaionMasterService } from './search-designaion-master.service';

@Component({
  selector: 'app-search-designation-master',
  templateUrl: './search-designation-master.component.html',
  styleUrls: ['./search-designation-master.component.scss'],
  providers: [SearchDesignaionMasterService]
})
export class SearchDesignationMasterComponent implements OnInit {

  @Input() searchDealerDesignationForm: FormGroup

  designation: any;
  departmentsforDealer:DeptDropdownForDesignation[]=[]

  
  constructor(
    private dealerDepartmentMasterSearchPagePresenter: DealerDepartmentMasterSearchPagePresenter,
    private SearchDesignaionMasterService: SearchDesignaionMasterService
  ) { }

  ngOnInit() {
    this.searchDealerDesignationForm = this.dealerDepartmentMasterSearchPagePresenter.dealerDesignationSearchDetailsForm
    this. getDepartmentsforDealer()
  }

  
  getDesignation(value){
    if (value!=null || value!=undefined && typeof value !== 'object') {
      this.SearchDesignaionMasterService.searchDesignation(value).subscribe(res=>{
        this.designation=res
      })
    }
  }

  displayFnDesignation(displayString: DesignationAuto) {
    return displayString ? displayString.displayString : undefined;
  }

  getDepartmentsforDealer() {
    this.SearchDesignaionMasterService.getSearchDepartmentAuto().subscribe(res=>{
      this.departmentsforDealer=res['result']
    })
    
  }

}
