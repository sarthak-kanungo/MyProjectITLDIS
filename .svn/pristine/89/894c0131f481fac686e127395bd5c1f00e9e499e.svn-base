declare module "MachineDescripancyClaim" {
    export interface MachineDescripancyClaimDomain {
        claimDate: string,
        discrepancyComplaints: DiscrepancyComplaints[],
        remarks: string
    }

    export interface SaveAndSubmitDescripancyClaimDomain {
        createdBy: number;
        draftMode: boolean;
        remarks: string;
        claimId?: number;
        machineDescripancyClaim: MachineDescripancyClaim[];
    }

    export interface MachineDescripancyClaim {
        complaintId: number;
        machineDescripancyComplaintDetails: MachineDescripancyComplaintDetail[];
    }

    export interface ViewAndEditDescripancyClaimDetailDomain {
        claimId: number;
        claimNumber: string;
        claimStatus: string;
        remarks: string;
        createdBy?: any;
        draftMode: boolean;
        machineDescripancyClaim: DiscrepancyComplaints[]  
        approvalDetails?:any[]
    }

    export interface DiscrepancyComplaints {
        complaintNumber: string;
        complaintStatus: string;
        draftMode: boolean;
        complaintId: number;
        complaintDate: string;
        itemNo: string;
        itemDescription: string;
        chassisNo: string;
        machineDescripancyComplaintDetails: MachineDescripancyComplaintDetail[];
    }

    export interface MachineDescripancyComplaintDetail {
        id: number;
        itemNo: string;
        itemDescription: string;
        quantity: number;
        remarks: string;
        type: string;
        approvedQuantity: number;
        claimQuantity?: number
        machineDescripancyComplaint?:any
    }

    export interface ResponseBySaveDomain {
        claimId: number;
        claimNumber?: any;
        claimStatus: string;
        remarks: string;
        claimDate: string;
        machineDiscrepancyComplaintDetail?: any;
        draftMode: boolean;
    }

}