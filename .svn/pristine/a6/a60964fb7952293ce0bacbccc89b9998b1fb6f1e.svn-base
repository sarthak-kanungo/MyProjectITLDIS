import { Component, OnInit, Input } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { HotlineReportPagePresenter } from '../hotline-report-create-page/hotline-report-create-page.presenter'
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute } from '@angular/router';
import { hotlineReport } from '../hotline-report/hotline-service';
import { autoCompleteChassis } from '../../domain/hotline-report.domain';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-hotline-report-machine-details',
  templateUrl: './hotline-report-machine-details.component.html',
  styleUrls: ['./hotline-report-machine-details.component.scss'],
  providers:[hotlineReport]
 
})
export class HotlineReportMachineDetailsComponent implements OnInit {
  
  @Input() materialDetailForm: FormArray;
  machineDetailsHeading: Array<string> = ['Select','Sr No', 'Chassis Number', 'Item Description', 'Vendor Code', 'Vendor Name', 'Vendor Invoice Number', 'Invoice Date', 'Container Number'];
  isView:any
  isEdit:any
  autoCompleteDataChassis:any
  constructor(
    private hotlineReportPagePresenter: HotlineReportPagePresenter,
    private activatedRoute:ActivatedRoute,
    private service:hotlineReport,
    private toaster:ToastrService
    
  ) { }

  ngOnInit() {
    this.checkFormOperation()
    this.formValuesChanges()
  
  }
  private checkFormOperation() {
    this.hotlineReportPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.hotlineReportPagePresenter.operation == Operation.VIEW) {
      this.isView = true;

    } 
    else if (this.hotlineReportPagePresenter.operation == Operation.EDIT) {
      this.isEdit = true;

    } 
    else if (this.hotlineReportPagePresenter.operation == Operation.CREATE) {
      this.hotlineReportPagePresenter.addRowInMaterialDetail();
    }
  }
  chassisSearch(){
    this.formValuesChanges()
  }
  private formValuesChanges(){
    this.materialDetailForm.controls.forEach(elt => {
      elt.get('machineVinMaster');
      elt.get('machineVinMaster').valueChanges.subscribe(val => {
        if (val) {
        this.autoCompleteChassis(val);
        }
      })
    })
  }
  private autoCompleteChassis(txt: string) {
    if (txt && typeof txt == 'string') {
      this.service.autoCompleteChassis(txt).subscribe(res => {
        this.autoCompleteDataChassis = res;
        // console.log(this.autoCompleteDataChassis,'cccccccccccc*888')
      }, err => {
        console.log('err', err);
      });
    }
  }
  
  getChassisDetails(event, list: FormGroup,index) {
    // console.log(event.option.value.chassiNo)
    if (event && event['option']['value']) { 
      if (!this.checkduplicate(index, event['option']['value']['chassiNo'])) {
        this.service.autoCompleteChassis(event.option.value.chassiNo).subscribe(res => {
           list.get('itemDescription').patchValue(res[0].itemDescription)
        });
      } else {
        this.toaster.error("Chassis No can not be duplicate");
        this.materialDetailForm.controls[index].reset();
      }
    }
    
  }
  checkduplicate(index: number, chassiNo: string) {
    if (index > 0) {
      for (let i: number = 0; i < index; i++) {
        // console.log(this.materialDetailForm.controls[i].get('chassisNo').value.chassiNo,'dddasfas***********')
        if (this.materialDetailForm.controls[i].get('machineVinMaster').value.chassiNo===chassiNo) {
          return true;
        }
      }
    }
    return false;
  }
  addRow() {
    this.hotlineReportPagePresenter.addRowInMaterialDetail();
  }
  deleteRow() {
    this.hotlineReportPagePresenter.deleteRow(this.materialDetailForm);
  }
  displayCodeFn(chassis:autoCompleteChassis){
    
    return chassis ? chassis.chassiNo : undefined
  }

  onKeyPressAlphaNumeric(event:KeyboardEvent){
    console.log(event,'event')
    const pattern = /[a-zA-Z0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  }
  onKeyPressString(event:KeyboardEvent){
    console.log(event,'event')
    const pattern = /[a-zA-Z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
  }
}
