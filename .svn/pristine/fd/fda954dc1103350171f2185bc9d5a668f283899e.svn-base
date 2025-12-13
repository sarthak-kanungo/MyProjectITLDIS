import { Injectable } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { Operation } from "src/app/utils/operation-util";
import { UploadableFile } from "kubota-file-upload";
import { ToastrService } from "ngx-toastr";
import { nonMovementStore } from "./non-movment-store";


@Injectable()
export class nonMovmentPresenter {
   
    photo1: UploadableFile[];
    photo2:UploadableFile[];
    operation: any; 
  constructor(
    private store:nonMovementStore,
  ){
    
  } 
    get searchSpareDesClaimForm() {
        return this.store.nonMovementInventoryForm.get('searchForm') as FormGroup;
    }
    get partDetailForm(): FormArray {
        return this.store.nonMovementInventoryForm.get('partDetailsForm') as FormArray;
    }
}