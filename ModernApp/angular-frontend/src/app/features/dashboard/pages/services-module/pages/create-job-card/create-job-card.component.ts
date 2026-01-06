import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDividerModule } from '@angular/material/divider';
import { MatRadioModule } from '@angular/material/radio';
import { ServicesApiService, VehicleInfoInitData, VehicleDetails } from '../../services-api.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-job-card',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDividerModule,
    MatRadioModule
  ],
  templateUrl: './create-job-card.component.html',
  styleUrls: ['./create-job-card.component.scss']
})
export class CreateJobCardComponent implements OnInit {
  vehicleForm: FormGroup;
  initData: VehicleInfoInitData | null = null;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private servicesApi: ServicesApiService,
    private route: ActivatedRoute
  ) {
    this.vehicleForm = this.fb.group({
      // Column 1
      productCategory: ['Tractor'],
      vinNo: ['', Validators.required],
      saleDate: [''],
      engineNo: [''],
      registrationNo: [''],
      serviceBookletNo: [''],
      couponNo: [''],
      jobCardPriority: ['Low'],

      // Column 2
      jobType: ['', Validators.required],
      dealerJobCardNo: [''],
      hmrWorking: ['Yes', Validators.required],
      hmr: ['', Validators.required],
      event: ['NA'],
      serviceType: ['', Validators.required],
      ownerDriven: ['NO', Validators.required],
      vorJobCard: ['', Validators.required],

      // Column 3
      modelFamily: ['', Validators.required],
      modelFamilyDesc: [''],
      modelCode: ['', Validators.required],
      modelCodeDesc: [''],
      location: ['', Validators.required],
      tractorInDate: [new Date().toISOString().split('T')[0], Validators.required],
      tractorInTimeHours: ['00'],
      tractorInTimeMinutes: ['00'],
      nextServiceFromHere: ['YES', Validators.required],

      // Hidden / System fields
      jobCardNo: ['System Generated'],
      jobCardDate: [new Date().toLocaleDateString()],
      sellingDealerName: [''],
      customerName: [''],
      customerId: ['']
    });
  }

  ngOnInit(): void {
    this.fetchInitData();
    this.route.queryParams.subscribe(params => {
      if (params['jobType'] === 'PDI') {
        this.vehicleForm.patchValue({ jobType: '5' });
      }
    });
  }

  fetchInitData(): void {
    this.isLoading = true;
    this.servicesApi.getVehicleInfoInitData()
      .pipe(
        catchError(err => {
          console.error('Failed to fetch init data', err);
          this.isLoading = false;
          return of(null);
        })
      )
      .subscribe(data => {
        if (data) {
          this.initData = data;
        }
        this.isLoading = false;
      });
  }

  onVinBlur(): void {
    const vin = this.vehicleForm.get('vinNo')?.value;
    if (vin && vin.length > 5) {
      this.isLoading = true;
      this.servicesApi.getVehicleDetails(vin)
        .pipe(
          catchError(err => {
            console.error('Failed to fetch vehicle details', err);
            this.isLoading = false;
            return of(null);
          })
        )
        .subscribe(details => {
          this.isLoading = false;
          if (details) {
            this.patchVehicleDetails(details);
          }
        });
    }
  }

  patchVehicleDetails(details: VehicleDetails): void {
    this.vehicleForm.patchValue({
      engineNo: details.engineNo,
      saleDate: details.saleDate,
      modelFamily: details.modelFamily,
      sellingDealerName: details.sellingDealerName,
      customerName: details.customerName,
      customerId: details.customerId,
      registrationNo: details.plateNo || ''
    });
  }

  onSubmit(): void {
    if (this.vehicleForm.valid) {
      console.log('Form Submitted', this.vehicleForm.value);
      alert('Vehicle Information Saved! Proceeding to Customer Information...');
    }
  }
}
