import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';

@Injectable()
export class nonMovementStore {
    nonMovementInventoryForm:FormGroup

    constructor(
        private fb: FormBuilder,
    ) {
       this.nonMovementInventoryForm=this.fb.group({
          searchForm:this.searchNonMovmentInventory(),
          partDetailsForm:this.fb.array([]),
       })
    }
   
 
    searchNonMovmentInventory() {
        const searchForm = this.fb.group({
           partNo:[{value:null,disabled:false}],
           aging:[{value:null,disabled:false}],
           auction:[{value:null,disabled:false}]
        });
        return searchForm;
    }



      partDetails(materialDetail?:any) {
        const partDetailsForm = this.fb.group({
          id:[{value:null,disabled:true}],
          isNonMoving:[{value:false,disabled:false}],
            itemNo:[{value:null,disabled:true}],
            itemDesc:[{value:null,disabled:true}],
            aging:[{value:null,disabled:true}],
            isOemPart:[{value:null,disabled:true}],
            isoilSupplierPart:[{value:null,disabled:true}],
            islocalPart:[{value:null,disabled:true}],
            spegst:[{value:null,disabled:true}],
            spmgst:[{value:null,disabled:true}],
            spmrp:[{value:null,disabled:true}],
            localpurchaseprice:[{value:null,disabled:true}],
            alternatepartno:[{value:null,disabled:true}],
            igstpercent:[{value:null,disabled:true}],
            uom:[{value:null,disabled:true}],
            hsncode:[{value:null,disabled:true}],
            // isAuction:[]
          });
          return partDetailsForm;

      }

      initializePartDetailForm(materialDetail?: any) {
        return this.partDetails(materialDetail);
      }



}