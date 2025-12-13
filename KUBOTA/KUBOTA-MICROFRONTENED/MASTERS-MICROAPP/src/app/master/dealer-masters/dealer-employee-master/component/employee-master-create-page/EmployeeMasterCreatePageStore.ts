import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { isNull } from 'util';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class EmployeeMasterCreatePageStore {

    private readonly _employeeMasterForm: FormGroup;
    ifscCode = "^[A-Z]{4}0[A-Z0-9]{6}$";
    aadharNumber="^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$'";

    constructor(private formBuilder: FormBuilder) {
        this._employeeMasterForm = this.formBuilder.group({
            employeeMasterDetails: this.buildEmployeeMasterDetailsForm(),
            employeeForm: this.buildEmployeeForm(),
            employmentForm: this.buildEmploymentForm(),
            employeeAddressForm: this.buildEmployeeAddressForm(),
            employeeFamilyDetailsForm:this.formBuilder.group({
                dataTable:this.formBuilder.array([])
            }),
            employeeWorkExperienceForm:this.formBuilder.group({
                dataTables:this.formBuilder.array([])
            })
        });
    }

    get employeeMasterForm() {
        return this._employeeMasterForm;
    }

    private buildEmployeeMasterDetailsForm() {
        return this.formBuilder.group({
            dealerDetails: [null, Validators.compose([Validators.required])],
            employeeCode: [null],
            title: [null, Validators.compose([])],
            firstName:[null, Validators.compose([Validators.required])],
            middleName: [null, Validators.compose([])],
            lastName: [null, Validators.compose([])],
            status: [null, Validators.compose([])],
            email: [null, Validators.compose([Validators.email])],
            mobileNo: [null, Validators.compose([CustomValidators.mobileNumber])],
            alternateMobileNo: [null, Validators.compose([CustomValidators.mobileNumber])],
            division: [null, Validators.compose([Validators.required])],
            department: [null, Validators.compose([Validators.required])],
            desidnation: [null, Validators.compose([Validators.required])],
            licenceType: [null, Validators.compose([])],
            drivingLicenceNo: [null, Validators.compose([])],
            expiryDate: [null],
            reportingEmployeeCode: [null, Validators.compose([])],
            reportingEmployeeName: [{value:null, disabled: true}, Validators.compose([])],
        });
    }

    private buildEmployeeForm() {
        return this.formBuilder.group({
            birthday: [null, Validators.compose([Validators.required])],
            anniversaryDate: [null],

            highestQualification: [null, Validators.compose([Validators.required])],
            maritalStatus: [null, Validators.compose([])],
            bloodGroup: [null, Validators.compose([])],
            sex: [null, Validators.compose([])],
            emergencyContactName: [null, Validators.compose([])],
            emergencyContactNo: [null, Validators.compose([CustomValidators.mobileNumber])],
        })
    }

    private buildEmploymentForm() {
        return this.formBuilder.group({
            joiningDate: [null, Validators.compose([Validators.required])],
            latestSalary: [null, Validators.compose([])],
            preFromDate:[null],
            preToDate:[null],
            totalExperience:[{value:null,disabled:true}],
            leavingDate: [null],
            aadharNo:[{value:null,disabled:false}],
            pfNo: [{value:null,disabled:false}],
            panNo: [{value:null,disabled:false}],
            esiNo: [{value:null,disabled:false}],
            bankAccountNo: [{value:null,disabled:true}],
            bankName: [{value:null,disabled:true}],
            bankBranch: [{value:null,disabled:true}],
            ifsCode:[{value:null,disabled:true}],
            authorizedDiscount:[null,Validators.compose([])],
        })
    }

    private buildEmployeeAddressForm() {
        return this.formBuilder.group({
            address1: [null, Validators.compose([Validators.required])],
            address2: [null, Validators.compose([])],
            address3: [null, Validators.compose([])],
            //pinCode: [null, Validators.compose([CustomValidators.numberOnly])],
            pinCode: [null, Validators.compose([Validators.required])],
            postOffice: [null, Validators.compose([Validators.required])],
            tehsil: [null, Validators.compose([Validators.required])],
            district: [null, Validators.compose([Validators.required])],
            city: [null, Validators.compose([Validators.required])],
            state: [null,Validators.compose([Validators.required])],
            country: [null, Validators.compose([])],
        })
    }

    buildemployeeFamilyDetailsForm(){
        return this.formBuilder.group({
            isSelected:[false],
            name: [null, Validators.compose([])],
            relationship: [null, Validators.compose([])],
        })
    }

    buildemployeeWorkExperienceForm(){
        return this.formBuilder.group({
            isSelected:[false],
            companyName: [null, Validators.compose([])],
            fromDate: [{value:null}],
            toDate: [{value:null}],
            designation: [null, Validators.compose([])],
            role: [null, Validators.compose([])],

        })
    }


}
