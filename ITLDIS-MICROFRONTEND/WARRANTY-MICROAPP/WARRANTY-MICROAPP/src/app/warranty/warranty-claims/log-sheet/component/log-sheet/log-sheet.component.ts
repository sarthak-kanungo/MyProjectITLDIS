import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { MatCheckboxChange, MatAutocompleteSelectedEvent } from '@angular/material';
import { LogSheetWebService } from './log-sheet-web.service';
import { AutoCompleteResult, LogsheetType, LogSheet } from '../../domain/log-sheet.domain';
import { LogSheetPagePresenter } from '../log-sheet-page/log-sheet-page.presenter';
import { ActivatedRoute } from '@angular/router';
import { PcrWebService } from '../../../product-concern-report/component/pcr/pcr-web.service';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { FieldCondition, FailureType, CropGrown, SoilType } from '../../../product-concern-report/domain/product-concern-report.domain';
import { PcrPageWebService } from '../../../product-concern-report/component/pcr-page/pcr-page-web.service';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { LogSheetApi } from '../../url-utils/log-sheet-urls';

@Component({
  selector: 'app-log-sheet',
  templateUrl: './log-sheet.component.html',
  styleUrls: ['./log-sheet.component.scss'],
  providers: [PcrWebService, PcrPageWebService, LogSheetWebService, LogSheetPagePresenter]
})
export class LogSheetComponent implements OnInit {


  @Input() logForm: FormGroup;
  @Output() machineInventory = new EventEmitter(); 
  fieldCondition: FieldCondition[];
  failureType: FailureType[];
  cropCondition: FieldCondition[];
  soilType: SoilType[];
  majorCropGrown: CropGrown[];
  status = [];
  isChecked: boolean;
  logsheetType: LogsheetType[];
  autoCompleteDataJob: AutoCompleteResult;
  autoCompleteDataChassis: AutoCompleteResult;
  isShowChassis = false;
  isView = false;
  isEdit = false;
  machineInventoryId: number;
  constructor(
    private pcrService: PcrWebService,
    private pcrPageWebService: PcrPageWebService,
    private logSheetService: LogSheetWebService,
    private logSheetPagePresenter: LogSheetPagePresenter,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.dropDownLogsheetType();
    this.dropDownFieldCondition();
    this.dropDownFailureType();
    this.dropDownCropCondition();
    this.getSoilType();
    this.getMajorCropGrown();
    this.checkFormOperation();
    
  }

  private checkFormOperation() {
    this.logSheetPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.logSheetPagePresenter.operation == Operation.VIEW) {
      this.isView = true;
      //  this.logForm.get('logsheetType').valueChanges.subscribe(val => {
      //   if (val == 'Against Job Card') {
      //     this.isShowChassis =  this.logForm.get('logsheetType').value == 'Against Job Card'? true:false;
      //   } 
      // });
    } else if (this.logSheetPagePresenter.operation == Operation.EDIT) {
      this.isEdit = true;
    } else if (this.logSheetPagePresenter.operation == Operation.CREATE) {
      this.formValueChanges();
    }
  }

  private formValueChanges() {
    this.logForm.get('chassisNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompleteChassisNoInJobCard(val);
      }
    });
    this.logForm.get('jobCardNo').valueChanges.subscribe(val => {
      if (val && typeof val!='object') {
        this.searchAutoCompleteJobCode(val);
      }
    });
    this.logForm.get('linkjobCardNo').valueChanges.subscribe(val => {
      if (val && typeof val == 'string') {
        if (this.logForm.get('chassisNo').value) {
          this.searchAutoCompleteJobCode(val, this.logForm.get('chassisNo').value.code);
        }
        else {
          this.toastr.error('Select Chassis No');
          this.logForm.get('linkjobCardNo').reset();
          this.logForm.get('chassisNo').markAsTouched();
        }
        this.logForm.get('linkjobCardNo').setErrors({ 'selectFromList': 'Select From List' })
      } else {
        this.logForm.get('linkjobCardNo').setErrors(null)
      }
    });


    this.logForm.get('logsheetType').valueChanges.subscribe(val => {
      if (!this.isEdit && !this.isView) {
        this.logForm.get('jobCardNo').reset();
        this.logForm.get('chassisNo').reset();
        this.logForm.get('customerName').reset();
        this.logForm.get('installationDate').reset();
        this.logForm.get('model').reset();
        this.logForm.get('address').reset();
        this.logForm.get('mobileNumber').reset();
        this.logForm.get('registrationNumber').reset();
        this.logForm.get('engineNo').reset();
        this.logForm.get('soldToDealer').reset();
        this.logForm.get('pcrNumber').reset();
      }
      if (val == 'Against Job Card') {
        this.logForm.get('jobCardNo').enable();
        this.logForm.get('chassisNo').disable();
      } else {
        this.logForm.get('chassisNo').enable();
        this.logForm.get('jobCardNo').disable();
      }
    });
  }

  private dropDownLogsheetType() {
    this.logSheetService.dropDownLogsheetType().subscribe(res => {
      this.logsheetType = res;
    });
  }
  private dropDownFieldCondition() {
    this.pcrService.dropDownFieldCondition().subscribe(result => {
      this.fieldCondition = result;
    }, err => {
      console.log('err', err);
    });
  }
  private dropDownFailureType() {
    this.pcrService.dropDownFailureType('0').subscribe(result => {
      this.failureType = result;
    }, err => {
      console.log('err', err);
    });

  }

  private dropDownCropCondition() {
    this.pcrService.dropDownCropCondition().subscribe(result => {
      this.cropCondition = result;
    }, err => {
      console.log('err', err);
    });


  }
  private getSoilType() {
    this.pcrService.getSoilType().subscribe(result => {
      this.soilType = result;
    }, err => {
      console.log('err', err);
    });


  }
  private getMajorCropGrown() {
    this.pcrService.getMajorCropGrown().subscribe(result => {
      this.majorCropGrown = result;
    }, err => {
      console.log('err', err);
    });
  }

  private searchAutoCompleteJobCode(txt: string, chassis?: string) {
    if (txt && typeof txt == 'string') {
      this.logSheetService.searchAutoCompleteJobCode(txt, chassis).subscribe(res => {
        this.autoCompleteDataJob = res;
      }, err => {
        console.log('err', err);
      })
    }
  }

  private autoCompleteChassisNoInJobCard(txt: string) {
    if (txt && typeof txt == 'string') {
      this.logSheetService.autoCompleteChassisNoInJobCard(txt).subscribe(res => {
        this.autoCompleteDataChassis = res;
      }, err => {
        console.log('err', err);
      });
    }
  }

  getJobCardDetails(evt: MatAutocompleteSelectedEvent) {
  
    this.logSheetService.getJobCardDetails(evt.option.value.id).subscribe(res => {
      delete res.jobCardViewDto.jobCardNo;
      res.jobCardViewDto.hours = res.jobCardViewDto.totalHour;
      res.jobCardViewDto.installationDate = res.jobCardViewDto.dateOfInstallation;
      res.jobCardViewDto.failureDate = res.jobCardViewDto.dateOfFailure;
      res.jobCardPartDto.forEach(elt => {
        this.logSheetPagePresenter.addRowInFailurePart(elt)
      })
      // this.logSheetPagePresenter.addRowInFailurePart()
      this.machineInventoryId= res.jobCardViewDto.machineInventoryId,
      this.machineInventory.emit(this.machineInventoryId)
      this.serviceHistory(res.jobCardViewDto.machineInventoryId, res.jobCardViewDto);
    }, err => {
      console.log('err', err);
    })
  }
  

  getChassisDetailsByChassisNoInJobCard(evt: MatAutocompleteSelectedEvent) {
    this.logSheetPagePresenter.clearRowFailerPart();
    this.logSheetService.getChassisDetailsByChassisNoInJobCard(evt.option.value.code).subscribe(res => {
      this.serviceHistory(res.machineInventoryId, res);
      this.logSheetPagePresenter.addRowInFailurePart();
    }, err => {
      console.log('err', err);
    })
  }
  getPcrNumberByJobCardId(evt: MatAutocompleteSelectedEvent) {
    this.logSheetService.getPcrNumberByJobCardId(evt.option.value.id).subscribe(res => {
      this.logForm.get('pcrNumber').patchValue(res.pcrNumber)
    })
  }


  private serviceHistory(id: number, logsheetData?: LogSheet) {
    
    this.logSheetPagePresenter.clearRowServiceHistory();
    this.pcrPageWebService.serviceHistory(id).subscribe(serviceHistryResponse => {
      const serviceHistoryDetails = serviceHistryResponse;
      if (serviceHistoryDetails.length > 0) {
        serviceHistoryDetails.forEach(elt => {
          this.logSheetPagePresenter.addRowInServiceHistory(elt);
        })
      }
      this.logSheetPagePresenter.patchFormVal({ ...logsheetData, ...{ jobCardHistory: serviceHistoryDetails } });
    }, err => {
      console.log('err', err);
    });
  }

  selectFailure(event: MatCheckboxChange) {
    event.checked ? this.isChecked = true : this.isChecked = false;
  }

  displayCodeFn(obj: AutoCompleteResult): string | number | undefined {

    return obj && typeof obj == 'object' ? obj.code : undefined;
  }
}
