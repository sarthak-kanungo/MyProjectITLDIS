

    export interface CreateDeliveryChallan {
        isImplementsFormValid: boolean;
        isCheckListFormValid: boolean;
        isInsuranceFormValid: boolean;
        isProspectFormValid: boolean;
        isCancelFormValid: boolean;
        isDcFormValid: boolean;
        deliveryChallanId: number;
    
        deliverableChecklist: DeliverableChecklistMaster[];
        dcMachineDetailFirst: DcMachineDetails[];
        dcMachineDetail: DcMachineDetails[];
        dcAccessoriesDetails: DcAccessoriesDetails[];
        insuranceCompanyMaster: IdMaster;
        customerMaster: DcCustomerMaster;
        dealerMachineTransfer?: IdMaster;
        machineAllotment?: IdMaster;
        mobileNumber: number;
        enquiry: IdMaster;
    
        allotmentId: number;
        deliveryChallanNumber: string;
        customerMobileNumber: number;
        dcCancellationReason: string;
        deliveryChallanType: string;
        invoiceCustomerName: string;
        dcCancellationType: string;
        policyExpiryDate: string;
        policyStartDate: string;
        gatePassNumber: string;
        otherReason: string;
        createdBy: IdMaster;
        remarks: string;
        brand: string;
        model: string;
        id?: string;
    
        customerName: string;
        customerAddress: string;
        customerCode: string;
        pinCode: string;
        postOffice: string;
        city: string;
        tehsil: string;
        district: string;
        state: string;
        country: string;
    }
    export interface DcMachineDetails {
        machineInventory: IdMaster
        quantity: number;
    }
    export interface DcAccessoriesDetails {
        machineMaster: IdMaster
        quantity: number;
    }
    
    export interface IdMaster {
        id: number
    }
    export interface DcCustomerMaster {
        mobileNumber: number;
        id?: number;
    }
    export interface DeliverableChecklistMaster {
        dcDeliverableChecklistMaster: IdMaster;
        isDelivered: boolean;
    }
    export interface ApprovalHierDetails {
        approvalStatus:string,
        isFinalApprover:string,
        approvedBy:string,
        approvalDate:string,
        approverRemarks:string
    }
