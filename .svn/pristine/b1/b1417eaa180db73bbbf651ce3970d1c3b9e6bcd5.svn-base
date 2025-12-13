import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TransporterCode, TransporterName } from '../../domain/transporter-domain';
import { TransporterSearchWebService } from './transporter-search-web.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-transporter-search',
  templateUrl: './transporter-search.component.html',
  styleUrls: ['./transporter-search.component.scss'],
  providers: [TransporterSearchWebService]
})
export class TransporterSearchComponent implements OnInit {


  transporterCode$: Observable<Array<TransporterCode>>
  transporterName$: Observable<Array<TransporterName>>

  @Input() transporterSearchForm: FormGroup;

  constructor(
    private transporterSearchWebService: TransporterSearchWebService
  ) { }

  ngOnInit() {
    this.getTransporterCodeList()
    this.getTransporterNameList()
  }

  private getTransporterCodeList() {
    this.transporterSearchForm.get('transporterCode').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const transporterCode = typeof valueChange == 'object' ? valueChange.transporterCode : valueChange
        this.autoTransporterCode(transporterCode)
      }
    })
  }

  autoTransporterCode(transporterCode: string) {
    this.transporterCode$ = this.transporterSearchWebService.getTransporterCodeAutocomplete(transporterCode)
  }

  displayFnTransporterCode(value: TransporterCode) {
    return value ? value.transporterCode : undefined
  }

  private getTransporterNameList() {
    this.transporterSearchForm.get('transporterName').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const transporterName = typeof valueChange == 'object' ? valueChange.source : valueChange
        this.autoTransporterName(transporterName)
      }
    })
  }

  autoTransporterName(transporterName: string) {
    this.transporterName$ = this.transporterSearchWebService.getTransporterNameAutocomplete(transporterName)
  }

  displayFnTransporterName(value: TransporterName) {
    return value ? value.transporterName : undefined
  }



}
