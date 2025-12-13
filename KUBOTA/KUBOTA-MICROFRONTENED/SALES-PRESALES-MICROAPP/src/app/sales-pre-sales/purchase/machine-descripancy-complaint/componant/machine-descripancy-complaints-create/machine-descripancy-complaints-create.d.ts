declare module "MachineDescripancyComplaintModule" {
    export interface ChasisNumberDomain {
        chassisNo: string;
        id: number;
    }
    export interface DetailsByChasisNoDomain {
        transporterName: string;
        transporterVehicleNumber: string;
        chassisNo: string;
        dmsGrnNumber: string;
        machineGrnId: number;
        grnDate?: any;
        engineNo: string;
        kaiInvoiceDate: string;
        lrNo: string;
    }
    export interface SaveMachineDescripancyComplaintDomain {
        complaintId: number;
        complaintNumber?: any;
        complaintStatus: string;
        draftMode: boolean;
        machineDescripancyComplaintDetails: MachineDescripancyComplaintDetail[];
        grnMachineDetails: GrnMachineDetails
        dealerEmployeeMaster: DealerEmployeeMaster
        complaintDate: string;
    }

    export interface MachineDescripancyComplaintDetail {
        id?: number;
        itemNo: string;
        itemDescription: string;
        type: string;
        quantity: number;
        remarks: string;
        approvedQuantity?: number;


    }
    export interface DealerEmployeeMaster {
        id: number;
    }
    export interface GrnMachineDetails {
        id: number;
        chassisNo?: string;
        engineNo?: string;
        machineGrn?: MachineGrn;
    }

    export interface ViewEditMachineDescComplaintDomain {
        grnMachineDetails: GrnMachineDetails;
        complaintId: number;
        complaintNumber?: any;
        machineDescripancyComplaintDetails: MachineDescripancyComplaintDetail[];
        dealerEmployeeMaster: DealerEmployeeMaster;
        complaintDate: string;
        draftMode: boolean;
        complaintStatus: string;

        approvalDetails?:any[];
    }
    export interface MachineGrn {
        dmsGrnNumber: string;
        grnDate?: any;
        transporterVehicleNumber: string;
        transporter: Transporter;
        accPacInvoice: AccPacInvoice;
    }
    interface AccPacInvoice {
        invoiceDate: string;
        lrNo: string;
      }
      
      interface Transporter {
        transporterName: string;
      }

}