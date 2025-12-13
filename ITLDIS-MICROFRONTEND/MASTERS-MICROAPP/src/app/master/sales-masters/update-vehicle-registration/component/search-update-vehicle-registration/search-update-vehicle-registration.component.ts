import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SearchVehicleRegistrationService } from './search-vehicle-registration.service';
import { AutoCompleteChassisNo, AutoCompleteCustomerCode, FilterSearchVehicleRegistration } from 'VehicleRegistration';
import { BaseDto } from 'BaseDto';
import { DataTable } from 'ngsw-search-table';
import { TableDataService } from '../../../../../ui/dynamic-table/table-data.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-search-update-vehicle-registration',
  templateUrl: './search-update-vehicle-registration.component.html',
  styleUrls: ['./search-update-vehicle-registration.component.scss'],
  providers:[SearchVehicleRegistrationService]
})
export class SearchUpdateVehicleRegistrationComponent implements OnInit {
  totalTableElements;
actionButtons=[];
  dataTable: DataTable;
  size=10;
  page=0;
  vehicleRegistrationNoForm: FormGroup;
  autoCompleteChassisNo:BaseDto<Array<AutoCompleteChassisNo>>
  autoCompleteCustomerCode:BaseDto<Array<AutoCompleteCustomerCode>>
  constructor( private fb: FormBuilder,private router:Router,
    private searchVehicleRegistrationService :SearchVehicleRegistrationService, private tableDataService: TableDataService,
    ) { }

  ngOnInit() {
    this.searchvehicleRegistrationNoForm();
    this.onSearchVehicleRegistration()
  }

  searchvehicleRegistrationNoForm() {
    this.vehicleRegistrationNoForm = this.fb.group({
      
      chassisNo: [null, Validators.compose([])],
      customerCode: [null, Validators.compose([])],
    })
    this.vehicleRegistrationNoForm.controls.chassisNo.valueChanges.subscribe(
      value =>{
        this.autoChassisNumber(value)
      }
    )

    this.vehicleRegistrationNoForm.controls.customerCode.valueChanges.subscribe(
      value=>{
        this.autoCustomerCode(value)
      }
    )
  }
  FilterSearchVehicleRegistration(recordData){
    
  }

  // createVehicleRegistration() {
  //   this.router.navigate(['master/salesmasters/updatevehicleregistration/create'])
  // }

  onSearchVehicleRegistration() {
    const filterObj = this.vehicleRegistrationNoForm.value as FilterSearchVehicleRegistration
    filterObj.page = this.page
    filterObj.size =this.size
    console.log('filterObj', filterObj);

    this.searchVehicleRegistrationService.getSearchData(filterObj).subscribe(response => {
      console.log('response', response);
      this.dataTable = this.tableDataService.convertIntoDataTable(response.result)
      console.log("dataTable ", this.dataTable);
    })
  }

  pageChange(event){
    console.log(event);
    
  }

  autoChassisNumber(value){
    this.searchVehicleRegistrationService.getChassisNo(value).subscribe(
      res=>{
        console.log('searchVehicleRegistrationService',res);
        this.autoCompleteChassisNo=res as BaseDto<Array<AutoCompleteChassisNo>>
      }
    )
  }

  autoCustomerCode(value){
    this.searchVehicleRegistrationService.getCustomerCode(value).subscribe(
      res=>{
        console.log('searchVehicleRegistrationService',res);
        this.autoCompleteCustomerCode= res as BaseDto<Array<AutoCompleteCustomerCode>>
        
      }
    )
  }

}
