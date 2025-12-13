import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BloodsDropdown, GenderDropdown, MaritalsDropdown, QualificationsDropdown } from '../../domain/dealer-employee-domain';
import { EmployeeService } from './employee.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss'],
  providers: [EmployeeService]
})
export class EmployeeComponent implements OnInit {
@Input() employeeForm:FormGroup

  qualifications: QualificationsDropdown;
  maritals: MaritalsDropdown;
  bloods: BloodsDropdown;
  // gender: GenderDropdown;
  gender=['M','F'];
  today = new Date();
  minDate: Date;
  maxDate: Date;
  // employeeForm: FormGroup;
  constructor( 
    private fb: FormBuilder,
    private employeeService: EmployeeService,
    private tostr: ToastrService
    ) { 
      const currentYear = new Date().getFullYear();
      const currentMonth = new Date().getMonth()
      const currentday = new Date().getDate()
      this.minDate = new Date(currentYear -118, 0, 1);
      this.maxDate = new Date(currentYear -18, currentMonth, currentday);
    }

  ngOnInit() {
    this.getHighestQualificationDropdown();
    this.getMaritalStatusDropdown();
    this.getBloodGroupDropdown();
    //this.getSexDropdown();
  }

  private getHighestQualificationDropdown() {
    this.employeeService.getHighestQualificationDropdown().subscribe(res => {
      this.qualifications = res;
      }
    )
  }
  private getMaritalStatusDropdown() {
    this.employeeService.getMaritalStatusDropdown().subscribe(res => {
      this.maritals = res;
      }
    )
  }
  private getBloodGroupDropdown() {
    this.employeeService.getBloodGroupDropdown().subscribe(res => {
      this.bloods = res;
      }
    )
  }
  // private getSexDropdown() {
  //   this.employeeService.getSexDropdown().subscribe(res => {
  //     this.gender = res;
  //     }
  //   )
  // }

  

}
