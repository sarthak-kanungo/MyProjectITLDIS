import { Component, OnInit, Input } from '@angular/core';
import {  FormGroup, FormBuilder } from '@angular/forms';
import {  DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { CreateBranchTransferRequestService } from './create-branch-transfer-request.service';

@Component({
  selector: 'app-create-branch-transfer-request',
  templateUrl: './create-branch-transfer-request.component.html',
  styleUrls: ['./create-branch-transfer-request.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    CreateBranchTransferRequestService
  ]

})
export class CreateBranchTransferRequestComponent implements OnInit {

  branchTransferForm: FormGroup;
  // @Input() branches = [];

 
  branches: string[] = [
    'Branch 1', 'Branch 2'
  ];

  
  constructor(
    private fb: FormBuilder,
    private createBranchTransferRequestService:CreateBranchTransferRequestService) 
    { }

  ngOnInit() { 
    this.branchTransferForm = this.createBranchTransferRequestService.createbranchTransferForm();
  }
}
