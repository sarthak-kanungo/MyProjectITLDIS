import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { KaiInspectionSheetSearchPageWebService } from '../kai-inspection-sheet-search-page/kai-inspection-sheet-search-page-web.service';
import { SearchPCRAutoComplete } from '../../../product-concern-report/domain/product-concern-report.domain';
import { MatDatepickerInput } from '@angular/material';
@Component({
  selector: 'app-kai-inspection-sheet-search',
  templateUrl: './kai-inspection-sheet-search.component.html',
  styleUrls: ['./kai-inspection-sheet-search.component.scss'],
  providers: [KaiInspectionSheetSearchPageWebService]
})
export class KaiInspectionSheetSearchComponent implements OnInit {
  @Input() searchForm: FormGroup;
  pcrData: SearchPCRAutoComplete;
  wcrData: SearchPCRAutoComplete;
  newdate = new Date()
  fromDate: Date
  toDate: Date
  public status: Array<string> = ['Disaptched', 'Approved'];
  constructor(private inspectionSearchWebService: KaiInspectionSheetSearchPageWebService) { }

  ngOnInit() {
    this.searchForm.get('inspectionNo').valueChanges.subscribe(val => {
      if (val) {
        this.autoCompletePcrNo(val);
      }
    });
    this.searchForm.get('wcrNo').valueChanges.subscribe(val => {
      if (val && typeof val =='string') {
        this.inspectionSearchWebService.autoCompleteWcrNo(val).subscribe(res => {
          this.wcrData = res;
        });
      }
    });
  }
  ngAfterViewInit() {
    this.toDate= this.newdate
    let backDate = new Date();
    backDate.setMonth(this.newdate.getMonth()-1);
    this.fromDate = backDate;
    this.searchForm.get('fromDate').patchValue(backDate);
    this.searchForm.get('toDate').patchValue(new Date());

  }
  private autoCompletePcrNo(txt: string) {
    this.inspectionSearchWebService.autoCompleteInspectionNo(txt).subscribe(res => {
      this.pcrData = res;
    });
  }
  displayCodeFn(obj: SearchPCRAutoComplete): string | number | undefined {
    return obj ? obj.code : undefined;
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.fromDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.toDate = this.newdate;
      else
        this.toDate = maxDate;
      if (this.searchForm.get('toDate').value > this.toDate)
        this.searchForm.get('toDate').patchValue(this.toDate);
    }
  }
}
