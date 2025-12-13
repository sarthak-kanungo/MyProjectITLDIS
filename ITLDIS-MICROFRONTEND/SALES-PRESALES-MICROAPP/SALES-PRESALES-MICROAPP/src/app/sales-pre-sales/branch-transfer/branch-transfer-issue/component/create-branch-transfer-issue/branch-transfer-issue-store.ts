import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class branchTransferIssueStore {
    readonly branchTransferIssueForm:FormGroup
      readonly orderForm:FormGroup
      items:FormArray
    constructor(
        private fb: FormBuilder
    ) {
        this.branchTransferIssueForm = this.fb.group({
            issueForm: this.createbranchTransferIssueForm(),    
        });
        this.orderForm = new FormGroup({  
            items: new FormArray([])
              
          }); 
    }
    // Create Form Group for Item Details
     createItem(): FormGroup {  
        return this.fb.group({  
          itemNo: [{value:'',disabled:false},Validators.required],  
          itemDesc:[{value:'',disabled:true},Validators.required],  
          reqQty: [{value:'',disabled:true}] ,
          issueQty:[{value:'',disabled:true}],
          pendingQty:[{value:'',disabled:false},Validators.required],
          mrp:[{value:'',disabled:true}],
          Value:[{value:'',disabled:true}],
        });  
      } 
    

   
    // create for transfer new
   private createbranchTransferIssueForm() {
        const issueForm = this.fb.group({
            requestNo: ['', Validators.required],
            branchTransferissueNo: ['', Validators.required],
            issueBranch: ['', Validators.required],
            branchtransferissuedate: [{ value: '', disabled: true }, Validators.compose([])],
            issuedby: [{ value: '', disabled: true }, Validators.compose([])],
            issuedtobranch: [{ value: '', disabled: true }, Validators.compose([])],
           
        });
        return issueForm;
      }
    initializeItemDetailsForm() {
        return this.createItem();
    }


 

}