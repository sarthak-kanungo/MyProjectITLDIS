import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class CreateBranchTransferRequestService {

  private branchTransferForm:FormGroup;


  constructor(private fb: FormBuilder) { }

  createbranchTransferForm() {
    this.branchTransferForm = this.fb.group({
      requestfrombranch: [{ value: '', disabled: true }, Validators.compose([])],
      requesttobranch: ['', Validators.compose([])],
      requestno: [{ value: '', disabled: true }, Validators.compose([])],
      requestdate: [{ value: '', disabled: true }, Validators.compose([])],
      requestedby: [{ value: '', disabled: true }, Validators.compose([])],
      remark: ['', Validators.compose([])]
    })
    return this.branchTransferForm;
  }
}
