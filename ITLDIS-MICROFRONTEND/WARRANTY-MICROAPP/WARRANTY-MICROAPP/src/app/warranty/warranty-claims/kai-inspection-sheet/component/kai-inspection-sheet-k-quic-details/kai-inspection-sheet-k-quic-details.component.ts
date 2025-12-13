import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { KaiInspectionSheetKQuicDetailsWebService } from './kai-inspection-sheet-k-quic-details-web.service';
import { from } from 'rxjs';
import { KaiDropDown } from '../../domain/kai-inspection-sheet.domain';
import { KisPagePresenter } from '../kai-inspection-sheet-create-page/kai-inspection-sheet-create-page.presenter';
import { MatSelectChange } from '@angular/material';
@Component({
  selector: 'app-kai-inspection-sheet-k-quic-details',
  templateUrl: './kai-inspection-sheet-k-quic-details.component.html',
  styleUrls: ['./kai-inspection-sheet-k-quic-details.component.scss'],
  providers: [KaiInspectionSheetKQuicDetailsWebService]
})
export class KaiInspectionSheetKQuicDetailsComponent implements OnInit {
  @Input() kQuickForm: FormGroup;
  useType: Array<string> = ['Use1', 'Use2', 'Use3', 'Use4'];
  failureMode: KaiDropDown;
  failureUnit: KaiDropDown;
  typeOfUse: KaiDropDown;
  constructor(
    private kaiInspectionSheetKQuicDetailsWebService: KaiInspectionSheetKQuicDetailsWebService,
    private kisPagePresenter: KisPagePresenter,
  ) { }

  ngOnInit() {
    this.dropDownTypeOfUse();
    this.dropDownFailureUnit();
    this.dropDownFailureMode();
  }

  private dropDownTypeOfUse() {
    this.kaiInspectionSheetKQuicDetailsWebService.dropDownTypeOfUse().subscribe(res => {
    this.typeOfUse = res;
    this.kisPagePresenter.typeOfUse = res;
    });
  }
  private dropDownFailureUnit() {
    this.kaiInspectionSheetKQuicDetailsWebService.dropDownFailureUnit().subscribe(res => {
      this.failureUnit = res;
      this.kisPagePresenter.failureUnit = res;
    });
  }
  private dropDownFailureMode() {
    this.kaiInspectionSheetKQuicDetailsWebService.dropDownFailureMode().subscribe(res => {
    this.failureMode = res;
    this.kisPagePresenter.failureMode = res;
    });
  }

  dropDownCompareFn(c1: KaiDropDown, c2: KaiDropDown): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.id === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.id;
    }
    return c1 && c2 ? c1.id === c2.id : c1 == c2;
  }
}
