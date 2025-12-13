import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { MarketIntelligenceService } from '../market-intelligence.service';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { GeneralInfoService } from 'src/app/sales-pre-sales/purchase/channel-finance-indent/component/general-info/general-info.service';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageLoginUser } from 'LoginDto';

export interface Category {
  lookupId: string;
  lookupval: string;
}

@Component({
  selector: 'app-market-intelligence-create',
  templateUrl: './market-intelligence-create.component.html',
  styleUrls: ['./market-intelligence-create.component.scss'],
  providers: [MarketIntelligenceService, GeneralInfoService]
})
export class MarketIntelligenceCreateComponent implements OnInit {
  marketDetailsForm: FormGroup;
  dropDownExchangeBrand = [];
  dropdownProduct = [];
  isShowSubmitBtn: boolean;
  isSubmitDisable: boolean = false;
  categories: Category[];
  public todaysDate = new Date();
  loginUser:StorageLoginUser
  constructor(
    private fb: FormBuilder,
    private service: MarketIntelligenceService,
    private toasterService: ToastrService,
    private loginService: LoginFormService,
    private generalInfoService: GeneralInfoService,
    private router : Router,
    private activatedRoute: ActivatedRoute
  ) { 
    this.loginUser = loginService.getLoginUser();
  }

  ngOnInit() {
    this.createMarketDetailsForm();
    this.dropdownExcanhgeBrand();
    this.dropDownProduct();
    this.service.dropdownFeedbackCategory().subscribe(response => {
      this.categories = response['result'];
    })
  }

  createMarketDetailsForm() {
    this.marketDetailsForm = this.fb.group({
      username: [{ value: this.loginUser.username, disabled: true }, Validators.compose([])],
      dealerCode: [{ value: this.loginUser.dealerCode, disabled: true }],
      dealer_name: [{ value: this.loginUser.name, disabled: true }, Validators.compose([])],
      date: [{ value: this.todaysDate, disabled: true }, Validators.compose([])],
      feedbackCategoryId: ['', Validators.required],
      productName: ['', Validators.required],
      competitorName: ['', Validators.required],
      feedbackDesc: ['', Validators.required],
    })
  }

  dropdownExcanhgeBrand() {
    this.service.dropdownExchangeBrand().subscribe(response => {
      this.dropDownExchangeBrand = response['result'];
    });
  }

  dropDownProduct() {
    this.service.dropdownProduct().subscribe(response => {
      this.dropdownProduct = response['result'];
    })
  }

  onSubmit() {
    if (this.marketDetailsForm.valid) {
      this.service.submitMarketIntelligenceData(this.marketDetailsForm.value).subscribe((response: any)=>{
        this.toasterService.success('Data submitted successfully');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      })
    }
  }


}
