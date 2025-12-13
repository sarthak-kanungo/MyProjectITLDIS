import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SourceCommonWebService } from '../../service/source-common-web.service';
import { Purpose } from '../../domain/source-domain';

@Component({
  selector: 'app-add-new-source',
  templateUrl: './add-new-source.component.html',
  styleUrls: ['./add-new-source.component.scss']
})

export class AddNewSourceComponent implements OnInit {

  @Input() sourceDetailsForm: FormGroup;
  purposes: Purpose

  constructor(
    private sourceCommonWebService: SourceCommonWebService
  ) { }

  ngOnInit() {
    this.purposeDropDown()
  }

  purposeDropDown() {
    this.sourceCommonWebService.getPurpose().subscribe(response => {
      this.purposes = response
    })
  }


}

