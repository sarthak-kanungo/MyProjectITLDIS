import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { TollFreeService } from '../../service/toll-free.service';
import { MobileNo } from '../../domain/toll-free-domain';
import { TollFreePagePresenter } from '../create-toll-free/toll-free-page.prensenter';
import { ToastrService } from 'ngx-toastr';
import { MatAutocompleteSelectedEvent, MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-enquiry',
  templateUrl: './enquiry.component.html',
  styleUrls: ['./enquiry.component.css']
})
export class EnquiryComponent implements OnInit {
  @Input() enquiryDetailsForm: FormGroup
  districtId:number;
  villageList: any;
  districtList: any[];
  mobileNo$: Observable<Array<MobileNo>>;
  @Input()
  isView:boolean
  dealercodeList
  constructor(private tollfreePagePresenter: TollFreePagePresenter,
    private tollFreeService: TollFreeService,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService) { }

    ngOnInit(): void {
      this.tollFreeService.getDistrictList('Y').subscribe(response => {
        this.districtList = response['result'];
      });
      this.enquiryDetailsForm.get('pincode').valueChanges.subscribe(searchValue => {
        if(this.enquiryDetailsForm.get('pincode').value){
          if(this.enquiryDetailsForm.get('district').value){
            this.enquiryDetailsForm.get('pincode').setErrors({'selectFromList':'Select From List'});
            if(typeof searchValue === 'object'){
              this.enquiryDetailsForm.get('pincode').setErrors(null);
              searchValue = searchValue.pinCode;
            }
            this.tollFreeService.getVillageListAutoSearch(searchValue, this.districtId).subscribe(response => {
              this.villageList = response['result'];
            });
          }else{
            this.toastr.error('Please Select District','Required Field')
            this.enquiryDetailsForm.get('district').markAsTouched();
            this.enquiryDetailsForm.get('pincode').reset();
          }
        }
      });
      this.enquiryDetailsForm.get('dealerName').valueChanges.subscribe(response => {
        if(this.enquiryDetailsForm.get('dealerName').enable && typeof response == 'string'){
          this.tollFreeService.getDealerCodeList(response).subscribe(response => {
            this.dealercodeList = response['result'];
          })
        }
      });
    }
    displayDealerCodeFn(selectedOption?: object): string | undefined {
      if (!!selectedOption && typeof selectedOption === 'string') {
        return selectedOption;
      }
      return selectedOption ? selectedOption['name'] : undefined;
    }
    compareFnDistrict(c1: any, c2: any): boolean {
      if (typeof c1 !== typeof c2) {
        if (typeof c1 === "object" && typeof c2 === "string")
          return c1.district === c2;
        if (typeof c2 === "object" && typeof c1 === "string")
          return c1 === c2.district;
      }
      return c1 && c2 ? c1.district === c2.district : c1 === c2;
    }
    displayFnVillage(pincodeDtl){
      return pincodeDtl ? pincodeDtl.pinCode : undefined;
    }
    selectionDistrict(event: MatSelectChange) {
      this.districtId = event.value.districtId;
      this.enquiryDetailsForm.get('pincode').reset();
    }
    dealerSelect(event: MatAutocompleteSelectedEvent){
      
        this.getDealerDetails(event.option.value.id);
      
    }
    getDealerDetails(dealerId){
      this.tollFreeService.getDealerDetailsById(dealerId).subscribe(response => {
        this.enquiryDetailsForm.get('tsm').patchValue(response['result']['tsm']);
        this.enquiryDetailsForm.get('tsmContactNo').patchValue(response['result']['tsmContactNo']);
        this.enquiryDetailsForm.get('dealerLocation').patchValue(response['result']['dealerLocation']);
        this.enquiryDetailsForm.get('dealerContactNo').patchValue(response['result']['dealerContactNo']);
      });
    }
    selectedPinCode(pincodeDtl:any){
      this.tollFreeService.getPinDtl(pincodeDtl.option.value.cityId, pincodeDtl.option.value.pinID).subscribe(response => {
        delete response['result']['district'];
        this.enquiryDetailsForm.patchValue(response['result']);
        this.enquiryDetailsForm.controls.village.patchValue(response['result']['postOffice']);

        this.tollFreeService.getTsmDetails(pincodeDtl.option.value.pinCode).subscribe(response => {
          
            this.enquiryDetailsForm.get('tsm').patchValue(response['result']['tsm']);
            this.enquiryDetailsForm.get('tsmContactNo').patchValue(response['result']['tsmContactNo']);
          
            this.enquiryDetailsForm.get('dealerName').patchValue(response['result']['dealerName']);
            this.enquiryDetailsForm.get('dealerLocation').patchValue(response['result']['dealerLocation']);
            this.enquiryDetailsForm.get('dealerContactNo').patchValue(response['result']['dealerContactNo']);
          
          if(response['result']['dealerName']!=null){  
            this.enquiryDetailsForm.get('dealerName').disable();
          }else{

            this.enquiryDetailsForm.get('dealerName').enable();
          }
        });
        
      });
    }
}
