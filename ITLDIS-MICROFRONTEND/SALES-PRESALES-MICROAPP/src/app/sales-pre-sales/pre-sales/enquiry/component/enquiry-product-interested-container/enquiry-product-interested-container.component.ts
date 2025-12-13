import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { ItemNo, DropDownExchangeBrand, AutoPopulateByItemNo, AutoModel, SubModel, AutoPopulateByModel } from 'EnquiryProductIntrested';
import { EnquiryProductInstrestedContainerService } from './enquiry-product-instrested-container.service';
import { SalesPreSalesCommonService } from '../../../../sales-pre-sales-common-service/sales-pre-sales-common.service';
import { BaseDto } from 'BaseDto';
import { ViewEnquiryDomain } from 'EnquiryCreation';

@Component({
  selector: 'app-enquiry-product-interested-container',
  templateUrl: './enquiry-product-interested-container.component.html',
  styleUrls: ['./enquiry-product-interested-container.component.scss'],
  providers: [EnquiryCommonService, EnquiryProductInstrestedContainerService,SalesPreSalesCommonService]
})
export class EnquiryProductInterestedContainerComponent implements OnInit {
  itemNoList: BaseDto<Array<ItemNo>>
  dropDownExchangeBrand: BaseDto<Array<DropDownExchangeBrand>>
   autoPopulateByItemNo: BaseDto<AutoPopulateByItemNo>
   PopulateByItemNo : AutoPopulateByItemNo
   model: BaseDto<Array<AutoModel>>
   subModel : BaseDto<Array<SubModel>>
   autoPopulateByModel : BaseDto<AutoPopulateByModel>
   @Output() modelValue = new EventEmitter<string>()
   @Input() getMobileNumber : string
  @Output() sendDataWithFormName = new EventEmitter<object>()
  @Input() viewPatchEnquiryData : ViewEnquiryDomain
  constructor(
    private enquiryCommonService : EnquiryCommonService,
    private enquiryProductInstrestedContainerService : EnquiryProductInstrestedContainerService,
    private salesPreSalesCommonService : SalesPreSalesCommonService
  ) { }

  ngOnInit() {
    this.dropdownExcanhgeBrand()
  }

  dropdownExcanhgeBrand() {
    this.enquiryProductInstrestedContainerService.dropdownExchangeBrand().subscribe(response => {
      // console.log('exchangeBrands =>', response);
      this.dropDownExchangeBrand = response as BaseDto<Array<DropDownExchangeBrand>>
    })
  }


  autoItemNo(event){
    this.salesPreSalesCommonService.searchItemNo(event,'').subscribe(response => {
          //console.log("searchItemNo ", response['result']);
          this.itemNoList = response as BaseDto<Array<ItemNo>>
        });
  }

  autoPopulatebyItemNo(event){
    this.salesPreSalesCommonService.searchByItemNo(event).subscribe(response => {
         // console.log('byItemno', response);
          this.autoPopulateByItemNo = response as BaseDto<AutoPopulateByItemNo>
          this.modelValue.emit( this.autoPopulateByItemNo.result.model)
        })  
  }

  autoModel(event){
    //if (this.productInterestedFrom.controls.itemNo.untouched) {
          this.enquiryProductInstrestedContainerService.searchModel(event).subscribe(response => {
            //console.log("searchItemNo ", response['result']);
           this.model = response as BaseDto<Array<AutoModel>>
          });
 }

 autoPopulateDatabyModel(event){
   this.enquiryProductInstrestedContainerService.getSeriesAndProductByModel(event).subscribe(response => {
     console.log('getdata', response);
     this.autoPopulateByModel = response as BaseDto<AutoPopulateByModel>
   })
   this.modelValue.emit(event)
  this.autoSubModel(event)
 }

 autoSubModel(event) {
    this.enquiryProductInstrestedContainerService.searchSubModel(event).subscribe(response => {
      // console.log("searchModel ", response);
      this.subModel = response as BaseDto<Array<SubModel>>
    });
  }

  getFormStatusandData(event){
  console.log('product', event)
    this.sendDataWithFormName.emit({form: 'Product Insterested', event: event})
  }

}
