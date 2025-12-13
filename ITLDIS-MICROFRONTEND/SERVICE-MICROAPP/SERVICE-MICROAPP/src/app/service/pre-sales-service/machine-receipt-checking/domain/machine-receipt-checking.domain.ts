
export interface FinalMrc {
    serviceMrc: Mrc
    multipartFileList: File[]
}
export interface Mrc {
    id?: number;
    draftFlag: boolean,
    accPacInvoice: AccPacInvoice,
    accPacInvoicePartDetails: AccPacInvoicePartDetails,
    serviceMrcChassisCheckpointInfSet: ServiceMrcChassisCheckpointInfoList[]
    serviceMrcDiscrepancySet: ServiceMrcDiscrepancySet[],
    serviceMrcPhotosList: File[]
}
export interface AccPacInvoice {
    id: number
}
export interface AccPacInvoicePartDetails {
    id: number
}
export interface ServiceMrcChassisCheckpointInfoList {
    remarks: string,
    okFlag: boolean,
    observedSpecification:string,
    aggregateSequenceNo:number,
    checkpointSequenceNo:number,
    serviceMrcChassisCheckpointId: serviceMrcChassisCheckpointId[]
}
export interface serviceMrcChassisCheckpointId {
    serviceMtCheckPoint: ServiceMtMrcCheckPoint,
    //serviceMtMrcAggregate: ServiceMtMrcCheckPoint

}
export interface ServiceMtMrcCheckPoint {
    checkpointId: number
}

export interface ServiceMrcDiscrepancySet {
    sparePartMaster: SparePartMaster[],
    quantity: number,
    remarks: string,
    type: string,
    raiseComplaint: boolean,
    id: number,
    deleteFlag: boolean
}

export interface SparePartMaster {
    id: number
    itemNo: string;
}
export interface KaiInvoiceNumber {
    transports: string,
    invoiceNumber: string,
    lrDate: string,
    lrNumber: string
}
export interface ChassisNumber {
    chassisNumber: string,
    model: string,
    engineNumber: string
}

export interface Tab {
    id: number;
    tabName: string;
    mrcCheckPoint: MrcCheckPoint[];
}
export interface MrcCheckPoint {
    defaultTick: boolean,
    fieldType:string,
    specification:string,
    observedSpecification:string,
    checkpointSequenceNo: number,
    checkpointId:number,
    checkpointDesc: string,
    aggregateSequenceNo: number
    aggregateId: number,
    aggregate:string,
    remarks?: string
}
export interface ItemNumberAuto {
    itemDescription: string,
    displayValue: string,
    itemNo: string,
    id: number
}
export interface MrcNumberAuto {
    value: string,
    code: string,
    id: string
}
export interface SearchMrc {
    mrcNo: string,
    invoiceNo: string,
    mrcFromDate: string,
    mrcToDate: string,
    invoiceFromDate: string,
    invoiceToDate: string,
    page: number;
    size: number;
    orgId: number;
}

export interface MrcViewResult {
    mrcHeaderData: MrcHeaderData;
    mrcCheckpointList: MrcCheckpointList[];
    mrcPhotoList: MrcPhotoList[];
    mrcDiscrepancyList: MrcDiscrepancyList[];
}

export interface MrcDiscrepancyList {
    id?: number
    quantity: number;
    itemNo: string;
    raiseComplaint?: any;
    itemDescription: string;
    remarks: string;
    type: string;
}

export interface MrcPhotoList {
    fileName: string;
}

export interface MrcCheckpointList {
    defaultTick: boolean,
    fieldType:string,
    specification:string,
    checkpointSequenceNo: number,
    checkpointId:number,
    checkpointDesc: string,
    aggregateSequenceNo: number,
    observedSpecification:string,
    aggregateId: number,
    aggregate:string,
    remarks?: string
}
export interface DropDownDataSpecification {
    observedSpecification: string;
    id: number;
}
export interface MrcHeaderData {
    kaiInvoiceId: number;
    kaiInvoiceNumber: string;
    transporter_name: string;
    chassisNumber: string;
    chassisNoId: number;
    mrcNumber: string;
    mrcDate: string;
    lrDate: string;
    machineModel: string;
    lrNumber: string;
    engineNumber: string;
    PendingMRC:number
    CompletedMRC: number;
    isShowFormF:boolean;
    isShowForm22:boolean;
}
export interface CheckChassis {
    draftFlag: boolean
    id: number
    result: boolean
}