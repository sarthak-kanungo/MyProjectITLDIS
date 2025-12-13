import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AutoCompSubModel, PopulateByItemNo } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/domains/enquiry';
import { ModelBySeries, Product, SeriesByProduct, Variants } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/domains/search-enquiry-v2';
import { ProductInterestedV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/product-interested-v2-web.service';
import { SearchEnquiryV2WebService } from 'src/app/sales-pre-sales/pre-sales/enquiry-v2/services/search-enquiry-v2-web.service';
import { SalesReportService } from '../../../sales-report-service/sales-report.service';
import { MachineMasterReportPagePresenter } from './machine-master-report-page-presenter';

@Component({
  selector: 'app-machine-master-report-page',
  templateUrl: './machine-master-report-page.component.html',
  styleUrls: ['./machine-master-report-page.component.css']
})
export class MachineMasterReportPageComponent implements OnInit {
  machineMasterReportForm:FormGroup



  products: Array<Product> = []
  seriesByProduct: Array<SeriesByProduct> = []
  variants: Array<Variants> = []
  subModelByModel: Array<AutoCompSubModel> = []
  modelBySeries: Array<ModelBySeries> = []
  model: any
  constructor(private presenter:MachineMasterReportPagePresenter,
    private enquiryService: SearchEnquiryV2WebService,
    private salesReportService : SalesReportService,
    private productInterestedV2WebService: ProductInterestedV2WebService,) { }

  ngOnInit() {
    this.machineMasterReportForm = this.presenter.mmrForm
    //this.getProduct()
    this.salesReportService.clearFormSubject.subscribe(response => {
      this.seriesByProduct = [];
      this.subModelByModel = [];
      this.modelBySeries = [];
      this.variants = [];
      this.products=[];
    });
    this.machineMasterReportForm.controls.productGroup.valueChanges.subscribe(value => {
      this.machineMasterReportForm.controls.product.reset();
      this.machineMasterReportForm.controls.series.reset();
      this.machineMasterReportForm.controls.model.reset();
      this.machineMasterReportForm.controls.subModel.reset();
      this.machineMasterReportForm.controls.variant.reset();
      this.salesReportService.clearFormSubject.next('');
      if(value){
        this.enquiryService.getProductByGroup(value).subscribe(response => {
          this.products = response.result
        })
      }
    })
  }

  getProduct(){
    this.enquiryService.getAllProduct().subscribe(response => {
      this.products = response.result
    })
  }
  
  selectProduct(value) {
    this.machineMasterReportForm.controls.series.reset();
    this.machineMasterReportForm.controls.model.reset();
    this.machineMasterReportForm.controls.subModel.reset();
    this.machineMasterReportForm.controls.variant.reset();
    this.seriesByProduct = [];
    this.subModelByModel = [];
    this.modelBySeries = [];
    this.variants = [];
    this.enquiryService.getSeriesByProduct(value).subscribe(response => {
      this.seriesByProduct = response.result;
    })
  }
  selectSeries(value) {
    this.machineMasterReportForm.controls.model.reset();
    this.machineMasterReportForm.controls.subModel.reset();
    this.machineMasterReportForm.controls.variant.reset();
    this.subModelByModel = [];
    this.modelBySeries = [];
    this.variants = [];
    this.enquiryService.getModelBySeries(value).subscribe(response => {
      this.modelBySeries = response.result
    })
  }

  selectModel(value) {
    this.model = value;
    this.machineMasterReportForm.controls.subModel.reset();
    this.machineMasterReportForm.controls.variant.reset();
    this.modelBySeries = [];
    this.variants = [];
    this.productInterestedV2WebService.getSubModel(value).subscribe(response => {
      this.subModelByModel = response.result
    })
  }

  selectSubModel(value) {
    this.variants = [];
    this.machineMasterReportForm.controls.variant.reset();
    this.productInterestedV2WebService.getVariant(this.model, value).subscribe(response => {
      this.variants = response
    })
  }

  compareFnSeries(c1: SeriesByProduct, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.series === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.series;
    }
    return c1 && c2 ? c1.series === c2.series : c1 === c2;
  }

  compareFnModel(c1: ModelBySeries, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.model === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.model;
    }
    return c1 && c2 ? c1.model === c2.model : c1 === c2;
  }

  compareFnSubModel(c1: AutoCompSubModel, c2: PopulateByItemNo): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.subModel === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.subModel;
    }
    return c1 && c2 ? c1.subModel === c2.subModel : c1 === c2;
  }

}
