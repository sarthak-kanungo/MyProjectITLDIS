import { Injectable } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { DateService } from 'src/app/root-service/date.service';
import { AutoDealerDetails, AutoEmployeeCode, CityDetails, DistrictDetails, PinCodeDetails, PostOfficeDetails, StateDetails, SubmitDto, TehsilDetails } from '../../domain/dealer-employee-domain';
import { EmployeeMasterCreatePageStore } from './EmployeeMasterCreatePageStore';



@Injectable()
export class EmployeeMasterCreatePagePresenter {

    readonly employeemasterForm: FormGroup

    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private employeeMasterPageStore: EmployeeMasterCreatePageStore, private dateService: DateService,
    ) {
        this.employeemasterForm = this.employeeMasterPageStore.employeeMasterForm
    }

    get detailsForm() {
        return this.employeemasterForm.get('employeeMasterDetails') as FormGroup
    }

    get employeeForm() {
        return this.employeemasterForm.get('employeeForm') as FormGroup
    }

    get employmentForm() {
        return this.employeemasterForm.get('employmentForm') as FormGroup
    }

    get employeeAddressForm() {
        return this.employeemasterForm.get('employeeAddressForm') as FormGroup
    }

    get employeeFamilyDetailsForm() {
        return this.employeemasterForm.get('employeeFamilyDetailsForm') as FormGroup
    }

    get employeeWorkExperienceForm() {
        return this.employeemasterForm.get('employeeWorkExperienceForm') as FormGroup
    }

    createEmployeeFamilyDetailsRow() {
        return this.employeeMasterPageStore.buildemployeeFamilyDetailsForm()
    }

    createEmployeeWorkExperienceRow() {
        return this.employeeMasterPageStore.buildemployeeWorkExperienceForm()

    }

    addRow() {
        const familyDetails = this.employeeFamilyDetailsForm.controls.dataTable as FormArray
        familyDetails.push(this.createEmployeeFamilyDetailsRow())
    }

    addRows() {
        const workExperience = this.employeeWorkExperienceForm.controls.dataTables as FormArray
        workExperience.push(this.createEmployeeWorkExperienceRow())
    }

    deleteRow() {
        const familyDetails = this.employeeFamilyDetailsForm.controls.dataTable as FormArray
        const nonSelected = familyDetails.controls.filter(details => !details.value.isSelected)

        familyDetails.clear()
        nonSelected.forEach(elt => familyDetails.push(elt))


    }

    deleteRows() {
        const workExperience = this.employeeWorkExperienceForm.controls.dataTables as FormArray
        const noSelected = workExperience.controls.filter(details => !details.value.isSelected)

        workExperience.clear()
        noSelected.forEach(elts => workExperience.push(elts))
    }

    markFormAsTouched() {
        this.employeemasterForm.markAllAsTouched();
    }

    disableRows(){
        
    }

    stateValidation() {
        this.employeeAddressForm.get('state').setErrors({
          selectFromList: 'Please select from list',
        });
    }

    districtValidation() {
        this.employeeAddressForm.get('district').setErrors({
          selectFromList: 'Please select from list',
        });
    }

    tehsilValidation() {
        this.employeeAddressForm.get('tehsil').setErrors({
          selectFromList: 'Please select from list',
        });
      }

    villageValidation() {
        this.employeeAddressForm.get('city').setErrors({
          selectFromList: 'Please select from list',
        });
    }

    pinCodeValidation() {
        this.employeeAddressForm.get('pinCode').setErrors({
          selectFromList: 'Please select from list',
        });
    }
    
    postOfficeValidation() {
        this.employeeAddressForm.get('postOffice').setErrors({
          selectFromList: 'Please select from list',
        });
    }

    disableForView() {
		// this.jobCardFormGroup.disable()
		// this.serviceFormGroup.disable()
		this.detailsForm.disable()
		this.employeeForm.disable()
		this.employmentForm.disable()
		this.employeeAddressForm.disable()
		this.employeeFamilyDetailsForm.disable()
		this.employeeWorkExperienceForm.disable()
	}

    patchDataFromView(viewData: any) {
		console.log('viewData--', viewData)
        let epmCode: AutoEmployeeCode={
            id: viewData.id,
            employeeCode: viewData.employeeCode,
        }
        this.detailsForm.get('employeeCode').patchValue(epmCode)
        // let dealaerDetail: AutoDealerDetails={
        //     id: viewData.dealerId,
        //     code: '',
        //     displayString:'',
        //     name:''
        // }
        // this.detailsForm.get('dealerDetails').patchValue(dealaerDetail)
        this.detailsForm.patchValue({
           // dealerDetails: viewData.dealerId,
           // employeeCode:viewData.employeeCode,
            title: viewData.title,
            firstName: viewData.firstName,
            middleName: viewData.middleName,
            lastName: viewData.lastName,
            status: viewData.activeStatus,
            email: viewData.emailId,
            mobileNo: viewData.mobileNo,
            alternateMobileNo: viewData.alternateMobileNo,
            division: viewData.division,
            department: viewData.departmentId,
            desidnation: viewData.designationId,
            licenceType: viewData.licenceType,
            drivingLicenceNo: viewData.drivingLicenceNo,
            expiryDate: this.stringTodate(viewData.drivingLicenceExpiryDate),
            // reportingEmployeeCode: viewData.reportingEmployeeId,
            // reportingEmployeeName: viewData.reportingEmployeeName
        })
        

        // const convertedDate =  this.dateService.getDateIntoDDMMYYYY(new Date(viewData.anniversaryDate))
        // this.employeeForm.get('anniversaryDate').patchValue(convertedDate)
         console.log('anniversaryDate--',this.stringTodate(viewData.birthDate));
        
        
        this.employeeForm.patchValue({
            birthday: this.stringTodate(viewData.birthDate),
            anniversaryDate: this.stringTodate(viewData.anniversaryDate),
            highestQualification: viewData.highestQualification,
            maritalStatus: viewData.maritalStatus,
            bloodGroup: viewData.bloodGroup,
            sex: viewData.sex,
            emergencyContactName: viewData.emergencyContactName,
            emergencyContactNo: viewData.emergencyContactNo,
        })

        this.employmentForm.patchValue({
            joiningDate: this.stringTodate(viewData.joiningDate),
            preFromDate: this.stringTodate(viewData.preFromDate),
            preToDate: this.stringTodate(viewData.preToDate),
            aadharNo:viewData.aadharNo,
            totalExperience:viewData.totalExperience,
            latestSalary: viewData.latestSalary,
            leavingDate: this.stringTodate(viewData.leavingDate),
            pfNo: viewData.pfNo,
            panNo: viewData.panNo,
            esiNo: viewData.esiNo,
            bankAccountNo: viewData.bankAccountNo,
            bankName: viewData.bankName,
            ifsCode:viewData.ifsCode,
            bankBranch: viewData.bankBranch,
            authorizedDiscount: viewData.authorizedDiscount,
        })

        this.employeeAddressForm.patchValue({
            address1: viewData.address1,
            address2: viewData.address2,
            address3: viewData.address3,
            // pinCode: viewData.pinCode,
            // locality: viewData.locality,
            // tehsil: viewData.tehsil,
            // district: viewData.district,
            // city: viewData.city,
            // state: viewData.state,
            country: viewData.country,
        })
        let state: StateDetails={
            id:'',
            state: viewData.state,
        }
        this.employeeAddressForm.get('state').patchValue(state)
        let district: DistrictDetails={
            id:'',
            district: viewData.district,
        }
        this.employeeAddressForm.get('district').patchValue(district)
        let tehsil: TehsilDetails={
            id:'',
            tehsil: viewData.tehsil,
        }
        this.employeeAddressForm.get('tehsil').patchValue(tehsil)

        let city: CityDetails={
            id:'vv',
            city: viewData.city,
        }
        this.employeeAddressForm.get('city').patchValue(city)

        let pinCode: PinCodeDetails={
            pinID:'',
            pinCode: viewData.pinCode,
        }
        this.employeeAddressForm.get('pinCode').patchValue(pinCode)

        let postOffice: PostOfficeDetails={
            id:'',
            postOffice: viewData.locality,
        }
        this.employeeAddressForm.get('postOffice').patchValue(postOffice)

        this.employeeFamilyDetailsForm.patchValue({
            isSelected: viewData.isSelected,
            name: viewData.name,
            relationship: viewData.relationship,
        })

        this.employeeWorkExperienceForm.patchValue({
            isSelected: viewData.isSelected,
            companyName: viewData.companyName,
            fromDate: viewData.fromDate,
            toDate: viewData.toDate,
            designation: viewData.designation,
            role: viewData.role,
        })
    }

    patchDataForDealerDetails(viewData: any) {
        let dealaerDetail: AutoDealerDetails={
            id: viewData.id,
            code: viewData.dealerCode,
            displayString: viewData.dealerDetails,
            name: '',
            dealerShip: ''
        }
        this.detailsForm.get('dealerDetails').patchValue(dealaerDetail)
    }
    stringTodate(date){
        if (date!=null || date!= undefined) {
        const val = date.split('-')
        return new Date( `${val[2]}-${val[1]}-${val[0]}`);
        }
        else{
            return date
        }
    }


}