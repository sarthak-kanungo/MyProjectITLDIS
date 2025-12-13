declare module "MachineDescripancyComplaintModule" {
    export interface ItemNoDomain {
        itemNo: string;
        id: number;
    }
    export interface DetailsByItemNoDomain {
        itemDescription: string;
        itemNo: string;
        id: number;
    }
    export interface TypeDomain {
        complaintType: string;
        id: number;
    }

}