import { Component, OnInit, Input } from '@angular/core';
import { MatCheckboxChange } from '@angular/material';
import { MrcCheckPoint, MrcCheckpointList,DropDownDataSpecification } from '../../domain/machine-receipt-checking.domain';
import { CheckPointWebService } from './check-point-web.service';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { MachineReceiptCheckingPresenter } from '../mrc-details-page/mrc-page.presenter';
import { MrcCreatePageStore } from '../mrc-details-page/mrc-create-page.store';
import { ActivatedRoute } from '@angular/router';
import { OperationsUtil } from '../../../../../utils/operation-util';
import { Operation } from '../../../../../utils/operation-util';
import { PdiChecklistWebService } from '../../../pre-delivery-inspection/component/pdi-checklist/pdi-checklist-web.service';
import { ToastrService } from 'ngx-toastr';

@Component({
    selector: 'app-check-point',
    templateUrl: './check-point.component.html',
    styleUrls: ['./check-point.component.scss'],
    providers: [CheckPointWebService, MrcCreatePageStore,PdiChecklistWebService]

})
export class CheckPointComponent implements OnInit {
    spans: any = [];
    mcrCheckPointForm: FormGroup;
    table: FormArray;
    isView: boolean = false;
    mrcCheckPointDetails: any = [];
    checkViewOrEdit: string;
    dummyList:any=[{"observedSpecification":"Select"}]
    checkListDropDown: DropDownDataSpecification
    @Input() public set sendCheckPointListData(sendCheckPointListData: Array<MrcCheckPoint>) {
        console.log("sendCheckPointListData ", sendCheckPointListData);
        if (sendCheckPointListData) {
            this.spans = [];
            sendCheckPointListData.forEach(material => {
                // console.log(material)
                if(material.fieldType === 'DROP DOWN'){
                    material.defaultTick = false;
                }
                this.addRow(material);
                this.rowSpan('aggregate', d => d.aggregate, sendCheckPointListData);
                // SameRowMearge.rowSpan('aggregate', d => d.aggregate, sendCheckPointListData)
                // this.spans = SameRowMearge.returnSpansArray()
            });
        }
    }
    @Input() public set editCheckPointListData(editCheckPointListData: Array<MrcCheckpointList>) {
        if (editCheckPointListData) {
            this.spans = [];
            editCheckPointListData.forEach(material => {
                if(material.fieldType === 'DROP DOWN' && material.observedSpecification==null){
                    material.defaultTick = false;
                }
                this.addRow(material);
                this.rowSpan('aggregate', d => d.aggregate, editCheckPointListData)
                // SameRowMearge.rowSpan('aggregate', d => d.aggregate, editCheckPointListData);
                // this.spans = SameRowMearge.returnSpansArray();
                console.log("material.checkpointId",material.checkpointId)
            });
            this.getDataForEdit();
            
        }
    }
    @Input() public set viewCheckPointListData(viewCheckPointListData: Array<MrcCheckpointList>) {
        console.log("viewCheckPointListData ", viewCheckPointListData);
        if (viewCheckPointListData) {
            this.spans = [];
            viewCheckPointListData.forEach(material => {
                this.addRowForView(material);
                this.rowSpan('aggregate', d => d.aggregate, viewCheckPointListData)
                // SameRowMearge.rowSpan('aggregate', d => d.aggregate, viewCheckPointListData);
                // this.spans = SameRowMearge.returnSpansArray();
            });

            this.getDataForEdit();
        }
    }
    @Input() checkPointTableForm: FormGroup;
    constructor(
        private presenter: MachineReceiptCheckingPresenter,
        private activateRoute: ActivatedRoute,
        private pdiChecklistService : PdiChecklistWebService,
        private toastr: ToastrService,
    ) { }
    ngOnInit() {
        this.presenter.operation = OperationsUtil.operation(this.activateRoute)
        if (this.presenter.operation === Operation.VIEW) {
          this.isView = true
        }
    }
    onChange(event: MatCheckboxChange, mcrCheckPoint: FormGroup) {
        if(mcrCheckPoint.get('fieldType').value.value === 'DROP DOWN' && mcrCheckPoint.get('observedSpecification').value === undefined){
            this.toastr.error('Select Specification');
            mcrCheckPoint.get('defaultTick').patchValue(false);
            return;
        }
        mcrCheckPoint.get('defaultTick').patchValue(event.checked)
        if (mcrCheckPoint.get('defaultTick').value) {
            // mcrCheckPoint.get('remarks').enable();
            mcrCheckPoint.get('remarks').clearValidators()
            mcrCheckPoint.get('remarks').updateValueAndValidity()
        } else {
            // mcrCheckPoint.get('remarks').disable();
            mcrCheckPoint.get('remarks').markAsTouched({ onlySelf: true })
            mcrCheckPoint.get('remarks').setValidators(Validators.required)
            mcrCheckPoint.get('remarks').updateValueAndValidity()
        }
    }
    rowSpan(key: string, accessor: Function, listData: Array<object>) {
        for (let i = 0; i < listData.length;) {
            const currentValue = accessor(listData[i]);
            let count = 1;
            // Iterate through the remaining rows to see how many match
            // the current value as retrieved through the accessor.
            for (let j = i + 1; j < listData.length; j++) {
                if (currentValue != accessor(listData[j])) {
                    break;
                }
                count++;
            }
            if (!this.spans[i]) {
                this.spans[i] = {};
            }
            // Store the number of similar values that were found (the span)
            // and skip i to the next unique row.
            this.spans[i][key] = count;
            i += count;
        }
    }
    addRow(checkList: object) {
        this.presenter.addRow(checkList);
    }
    addRowForView(checkList: object) {
        this.presenter.addRowForView(checkList);
    }
    contenteditableValuechange(value: any, rowFormGroup: FormGroup, formControlName: any) {
        rowFormGroup.get(formControlName).patchValue(value);
    }
    getRowSpan(col: object, index: number) {
        // console.log("index ", index);
        // console.log("col ", col);
        // console.log("this.spans ", this.spans);
        return this.spans[index] && this.spans[index]['aggregate'];
        // return this.spans[index] && this.spans[index][col];
    }

    checkListChange(event: MouseEvent, id: any) {
        this.checkListDropDown=this.dummyList
        this.pdiChecklistService.getDropDownCheckListData(id.value).subscribe(res => {
          this.checkListDropDown = res
        })
    }
    
    getDataForEdit() {
          this.pdiChecklistService.getDropDownCheckListData('0').subscribe(res => {
            this.checkListDropDown = res
          })
    }
}









