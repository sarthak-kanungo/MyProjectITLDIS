import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { QuotationSearchDomain } from 'quotation-dto';
import { BaseDto } from 'BaseDto';
import { ImplementsDomain, EditableTableAutocompleteList } from 'add-implements-dto';
import { AddImplementsContainerService } from './add-implements-container.service';
import { Observable, forkJoin, of } from 'rxjs';
import { map, every, mergeMap, scan, toArray, concatAll } from 'rxjs/operators';
import { mapToMapExpression } from '@angular/compiler/src/render3/util';

@Component({
  selector: 'app-add-implements-container',
  templateUrl: './add-implements-container.component.html',
  styleUrls: ['./add-implements-container.component.scss'],
  providers: [AddImplementsContainerService]
})
export class AddImplementsContainerComponent implements OnInit {
  implementsDto: BaseDto<ImplementsDomain>
  itemNoDto: EditableTableAutocompleteList

  @Input() dataAutoPopulatebyEnquiryNo: QuotationSearchDomain;
  @Output() autoPopulatedataByitemNo: EventEmitter<ImplementsDomain> = new EventEmitter<ImplementsDomain>();
  @Input() isValidateForm: boolean;
  @Input() viewOnly = false;
  @Output() validatedFormData = new EventEmitter<object>();
  patchValueToEditableTable: Observable<{ tableRowId: any; patchValue: any; }[]>;
  constructor(
    private addImplementsContainerService: AddImplementsContainerService
  ) { }

  ngOnInit() {
  }

  autoPopulateBydataitemNo(event) {
    this.addImplementsContainerService.searchgetMachineDetailByItemNo(event.option.code).subscribe(response => {
      this.implementsDto = response as BaseDto<ImplementsDomain>
      /*console.log("implementsDomain ", this.implementsDto);
      console.log("dataAutoPopulatebyEnquiryNo ",this.dataAutoPopulatebyEnquiryNo)*/
      if(this.dataAutoPopulatebyEnquiryNo && this.implementsDto && this.implementsDto!=null && this.implementsDto.result && this.implementsDto.result!=null){
          if(this.dataAutoPopulatebyEnquiryNo.igst!=null && this.dataAutoPopulatebyEnquiryNo.igst>0){
              this.implementsDto.result.sgst=0;
              this.implementsDto.result.cgst=0;
          }else{
              this.implementsDto.result.igst=0;
          }
      }
      this.patchValueToEditableTable = of([{
        tableRowId: event.tableRow.tableRowId,
        patchValue: { ...this.implementsDto.result }
      }]);
    })
  }
  autoEnquiryNo(event) {
    // console.log("event ", event);
  }
  autoItemNo(event: object) {
    this.addImplementsContainerService.searchItemNo(event['itDs'], 'implements', 'QUOTATION').subscribe(res => {
      this.itemNoDto = {
        list: res['result'],
        config: event['config'],
        searchKey: event['itDs']
      }
    });
  }
  validatedFormDataFn(formData) {
    if (formData) {
      this.validatedFormData.emit(formData);
    }
  }
}
