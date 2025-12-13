export interface SubmitTransporter {
    transporterCode: string;
    transporterName: string;
}

export interface TransporterCode {
    transporterCode: string
}
export interface TransporterName {
    transporterName: string
}


export interface FilterTransporterSearch {
    page: number;
    size: number;
    transporterCode: string;
    transporterName: string;
}