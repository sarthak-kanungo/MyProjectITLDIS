import { Component, OnInit } from '@angular/core';
import { WcrReportCreatePageWebService } from './wcr-report-create-page-web.service';
import { FormGroup, FormArray } from '@angular/forms';
import { WcrReportPagePresenter } from './wcr-report-create-page.presenter';
import { WcrReportPageStore } from './wcr-report-create-page.store';
import { ToastrService } from 'ngx-toastr';
import { WcrReport, WcrDetail, WcrReportSearch } from '../../domain/wcr-report.domain';
import { Router, ActivatedRoute } from '@angular/router';
import { ObjectUtil } from '../../../../../utils/object-util';
import { DataTable } from 'ngsw-search-table/public-api';
// import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-wcr-report-create-page',
  templateUrl: './wcr-report-create-page.component.html',
  styleUrls: ['./wcr-report-create-page.component.scss'],
  providers: [WcrReportPageStore, WcrReportPagePresenter, WcrReportCreatePageWebService],

})
export class WcrReportCreatePageComponent implements OnInit {
  wcrReportForm: FormGroup;
  wcrReportDetailForm: FormArray;
  wcrReportCreditDetailForm: FormArray;
  wcrDetail = {} as WcrReport;
  wcrResponse: WcrDetail[] = [];
  newdate = new Date()
  fromDate: Date
  toDate: Date
  key = 'wcrrsp';
  searchFilterValues: any;
  page = 0;
  size = 10;
  constructor(
    private wcrReportPagePresenter: WcrReportPagePresenter,
    private wcrReportCreatePageWebService: WcrReportCreatePageWebService,
    private tostr: ToastrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    // private dataservice: DateService
  ) { }

  ngOnInit() {
    window.onbeforeunload = () => {
      localStorage.removeItem(this.key)
    }
    this.searchFilterValues = localStorage.getItem(this.key)
    this.wcrReportForm = this.wcrReportPagePresenter.wcrReportForm;
    this.wcrReportDetailForm = this.wcrReportPagePresenter.wcrReportDetailForm;
    this.wcrReportCreditDetailForm = this.wcrReportPagePresenter.wcrReportCreditDetailForm;
    this.searchFilterValues = JSON.parse(JSON.parse(JSON.stringify(this.searchFilterValues)))
    if (this.searchFilterValues != null || this.searchFilterValues != undefined) {
      this.wcrReportForm.patchValue(this.searchFilterValues)
    }
    if (this.wcrReportForm.get('wcrType').value == null && this.wcrReportForm.get('fromDate').value == null && this.wcrReportForm.get('toDate').value == null) {

      this.toDate = this.newdate
      let backDate = new Date();
      backDate.setMonth(this.newdate.getMonth() - 1);
      this.fromDate = backDate;
      this.wcrReportForm.get('fromDate').patchValue(backDate);
      this.wcrReportForm.get('toDate').patchValue(new Date());
      this.wcrReport()
    } else {
      localStorage.getItem(this.key)
      this.wcrReport()
    }
  }

  wcrReport() {
    if (this.wcrReportForm.valid) {
      // this.wcrDetail = {} as WcrReport;
      const filterObj = this.wcrReportForm.value as WcrReportSearch
      filterObj.page = this.page
      filterObj.size = this.size
      // filterObj.fromDate = filterObj.fromDate ? this.dataservice.getDateIntoYYYYMMDD(filterObj.fromDate) : null
      // filterObj.toDate = filterObj.toDate ? this.dataservice.getDateIntoYYYYMMDD(filterObj.toDate) : null
     
      localStorage.setItem(this.key, JSON.stringify(this.wcrReportForm.value))
      if (this.wcrReportForm.get('dealerCode').value) {
        this.wcrDetail.wcrReportDto.dealerCode = this.wcrReportForm.get('dealerCode').value.code;
      }
      if (this.wcrReportForm.get('fromDate').value)
        // this.wcrDetail.wcrReportDto.fromDate = ObjectUtil.convertDate(this.wcrReportForm.get('fromDate').value);
        filterObj.fromDate = filterObj.fromDate ? ObjectUtil.convertDate(filterObj.fromDate) : null
      if (this.wcrReportForm.get('toDate').value)
        // this.wcrDetail.wcrReportDto.toDate = ObjectUtil.convertDate(this.wcrReportForm.get('toDate').value);
        filterObj.toDate = filterObj.toDate ? ObjectUtil.convertDate(filterObj.toDate) : null
      
        if (this.checkValidDatesInput()) {
        if (this.wcrReportForm.get('wcrType').value || this.wcrReportForm.get('fromDate').value || this.wcrReportForm.get('toDate').value) {

          this.wcrReportCreatePageWebService.wcrReport(filterObj).subscribe(res => {
            console.log('res', res);
            this.wcrResponse = res;
            this.wcrReportDetailForm.clear();
            res.forEach(elt => {
              this.wcrReportPagePresenter.addRowInWcrReportDetail(elt);
              this.wcrReportPagePresenter.addRowInWcrReportCreditDetail(elt);
            });
            if (res.length == 0) {
              this.tostr.info('No data available', 'Info');
            }
          }, err => {
            this.tostr.error('Error while fetching details', 'Error');
          })
        }
        else if (this.wcrReportForm.get('fromDate').value == null && this.wcrReportForm.get('toDate').value == null) {
          this.tostr.error("Please fill atleast one input field.");
        }
      } else {
        this.tostr.error("Please Select Date Range.");
      }
    }
    else {
      this.wcrReportForm.markAllAsTouched();
      this.tostr.error('Please fill all mandatory fields', 'Error');
    }

  }
  checkValidDatesInput() {
    const fltEnqObj = this.wcrReportForm.value

    fltEnqObj.fromDate = this.wcrReportForm.getRawValue() ? this.wcrReportForm.value.fromDate : null
    fltEnqObj.toDate = this.wcrReportForm.getRawValue() ? this.wcrReportForm.value.toDate : null

    console.log("Date Selected: " + fltEnqObj['fromDate'] + fltEnqObj['toDate']);
    let fromdates = ['fromDate'];
    let toDates = ['toDate'];
    let check = [];
    for (let i = 0; i < fromdates.length; i++) {
      if ((fltEnqObj[fromdates[i]] === null || fltEnqObj[fromdates[i]] === "" || fltEnqObj[fromdates[i]] === undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(1);
        } else {
          check.push(0);
        }
      } else if ((fltEnqObj[fromdates[i]] !== null || fltEnqObj[fromdates[i]] !== "" || fltEnqObj[fromdates[i]] !== undefined)) {
        if ((fltEnqObj[toDates[i]] === null || fltEnqObj[toDates[i]] === "" || fltEnqObj[toDates[i]] === undefined)) {
          check.push(0);
        } else {
          check.push(1);
        }
      }
    }
    if (check.includes(0)) {
      return false;
    } else {
      return true;
    }
  }
  clear() {
    this.wcrReportForm.reset();
    this.wcrReportDetailForm.clear();
    this.wcrDetail.wcrReportDto = undefined
    this.wcrResponse.length = 0
    localStorage.removeItem(this.key)
  }

  createDC() {

    if (this.wcrReportPagePresenter.selectedWcrId.length > 0) {
      this.router.navigate(['../../warranty-delivery-challan/create'], { relativeTo: this.activatedRoute, queryParams: { ids: this.wcrReportPagePresenter.selectedWcrId } });
    }
    else {
      this.tostr.error('Please select atleast one report', 'Error');
    }
  }

}
