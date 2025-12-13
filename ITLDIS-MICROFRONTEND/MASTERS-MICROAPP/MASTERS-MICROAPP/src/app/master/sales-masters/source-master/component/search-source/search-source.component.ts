import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, } from '@angular/forms';
import { Purpose, SourceCode, SourceName } from '../../domain/source-domain';
import { SourceCommonWebService } from '../../service/source-common-web.service';
import { SearchSourceWebService } from './search-source-web.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-search-source',
  templateUrl: './search-source.component.html',
  styleUrls: ['./search-source.component.scss'],
  providers: [SearchSourceWebService]
})

export class SearchSourceComponent implements OnInit {

  @Input() sourceSearchForm: FormGroup;
  purposes: Purpose
  sourceCode$: Observable<Array<SourceCode>>
  sourceName$: Observable<Array<SourceName>>

  constructor(
    private sourceCommonWebService: SourceCommonWebService,
    private searchSourceWebService: SearchSourceWebService
  ) { }

  ngOnInit() {
    this.purposeDropDown()
    this.getsourceCodeList()
    this.getsourceNameList()
  }

  purposeDropDown() {
    this.sourceCommonWebService.getPurpose().subscribe(response => {
      this.purposes = response
    })
  }

  private getsourceCodeList() {
    this.sourceSearchForm.get('sourceCode').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const sourceCode = typeof valueChange == 'object' ? valueChange.sourceCode : valueChange
        this.autoSourceCode(sourceCode)
      }
    })
  }

  autoSourceCode(sourceCode: string) {
    this.sourceCode$ = this.searchSourceWebService.getSourceCodeAutocomplete(sourceCode)
  }

  displayFnSourceCode(value: SourceCode) {
    return value ? value.sourceCode : undefined
  }

  private getsourceNameList() {
    this.sourceSearchForm.get('sourceName').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const sourceName = typeof valueChange == 'object' ? valueChange.source : valueChange
        this.autoSourceName(sourceName)
      }
    })
  }

  autoSourceName(sourceName: string) {
    this.sourceName$ = this.searchSourceWebService.getSourceNameAutocomplete(sourceName)
  }

  displayFnSourceName(value: SourceName) {
    return value ? value.source : undefined
  }

}

