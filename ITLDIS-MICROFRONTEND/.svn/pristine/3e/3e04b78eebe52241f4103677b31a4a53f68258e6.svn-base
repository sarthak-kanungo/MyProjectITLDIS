import { Component, EventEmitter, Injectable, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DealerFunction, SubmitNewDealerDto } from '../../domain/create-dealer-user-domain';
import { FunctionService } from './function.service';

@Component({
  selector: 'app-function',
  templateUrl: './function.component.html',
  styleUrls: ['./function.component.scss'],
})

export class FunctionComponent implements OnInit,OnChanges {

  dealerFunctionForm:FormGroup
  @Input() dealerFunctionList
  @Output() functionForm = new EventEmitter<any>()
  roleFunctionForm:FormArray
  private _operation: string

  constructor(private fb:FormBuilder,
    private activityRoute: ActivatedRoute,) {
    }

  ngOnInit() {
    this.operation = OperationsUtil.operation(this.activityRoute)
      this.roleFunctionForm =  new FormArray([])
  }
  set operation(type: string) {
      this._operation = type
  }
  get operation() {
      return this._operation
  }

  ngOnChanges() {
    this.displayRoleFunction(this.dealerFunctionList)
    this.functionForm.emit(this.buildJsonForFunctionForm())
    
  }

  createFunctionForm(){
    return this.fb.group({
      roleid : [''],
      assignRole : [false],
      role_name: ['', Validators.compose([])],
    })
  }

  onChange(event:MatCheckboxChange){
    this.functionForm.emit(this.buildJsonForFunctionForm())
 }

  // buildJsonForFunctionForm() {
  //   let submitFunctionForm = {} as SubmitNewDealerDto
  //   this.dealerFunctionList.forEach(element => {
  //     let vv=element.role_name
  //     this.dealerFunctionForm.get('function').patchValue(vv)
  //   });
  //   submitFunctionForm.assignRole= this.dealerFunctionForm.get('assignRole').value? 'Y' : 'N'
  //   submitFunctionForm.function= this.dealerFunctionForm.get('function').value
  //   return submitFunctionForm;
  // }
//   public get roll() {
//     return this.dealerFunctionForm.get('roleFunctionForm') as FormArray
// }

  buildJsonForFunctionForm() {
    
    //let submitFunctionForm = {} as SubmitNewDealerDto
    return this.roleFunctionForm
  }

  addRow(data){
    const fg = this.createFunctionForm();
      if(data){
          fg.patchValue(data);
      }
    this.roleFunctionForm.push(fg);
    
    if (this.operation === Operation.VIEW) {
      this.roleFunctionForm.disable()
    }
  }

  displayRoleFunction(list:any){
    list.forEach(row => {
      row['assignRole'] = row['assigned']==='Y'?true:false;
      row['roleid'] = row['id'];
      this.addRow(row)
    });
  }


}
