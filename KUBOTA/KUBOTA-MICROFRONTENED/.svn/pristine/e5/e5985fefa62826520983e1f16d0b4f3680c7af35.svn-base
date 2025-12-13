import { Component, OnInit, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ReInstallationPagePresenter } from '../re-installation-page/re-installation-page-presenter';
import { FormGroup } from '@angular/forms';
import { RepresentativeData, RepresentativeTypesDropDown } from '../../domain/re-installation-domain';
import { ReInstallationPageStore } from '../re-installation-page/re-installation-page.store';
import { RepresentativeDialogWebService } from './representative-dialog-web.service';

export interface BtnAction {
  buttonName: string;
  clickHandler: Observable<any>;
  errorHandler?: (errorRes?: any) => void;
  webCall?: any;
  webCallUrl?: string;
  webCallType?: string;
  data?: any;
}
export interface RepresentativeDialogData {
  title: string;
  btnAction: Array<BtnAction>;
  buttonName?: Array<string>;
  data?: RepresentativeData;
}

@Component({
  selector: 'app-representative-dialog',
  templateUrl: './representative-dialog.component.html',
  styleUrls: ['./representative-dialog.component.scss'],
  providers: [ReInstallationPagePresenter, ReInstallationPageStore, RepresentativeDialogWebService]
})
export class RepresentativeDialogComponent implements OnInit {

  representativeForm: FormGroup
  representativeTypes: Array<RepresentativeTypesDropDown>

  constructor(
    public dialogRef: MatDialogRef<RepresentativeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: RepresentativeDialogData,
    private reInstallationPagePresenter: ReInstallationPagePresenter,
    private representativeDialogWebService: RepresentativeDialogWebService,
  ) { }

  ngOnInit() {
    this.representativeForm = this.reInstallationPagePresenter.representativesForm
    this.reInstallationPagePresenter.setValidatorsForRepresentativesDetails()
    setTimeout(() => {
      this.representativeTypeDropdown();
    }, 0);
     
  }

  representativeTypeDropdown() {
    this.representativeDialogWebService.representativeTypeDropdown().subscribe(response => {
      this.representativeTypes = response
      console.log("this.representativeTypes ", this.representativeTypes);
    })
  }

  submit(btnName: string, index): void {
    if (this.representativeForm.status === 'VALID') {
      this.data.data = this.representativeForm.value as RepresentativeData
    }
    this.dialogRef.close(btnName)
    this.reInstallationPagePresenter.clearValidatorsForRepresentativesDetails()
  }
  close() {
    this.dialogRef.close();
    this.reInstallationPagePresenter.clearValidatorsForRepresentativesDetails()
  }

}