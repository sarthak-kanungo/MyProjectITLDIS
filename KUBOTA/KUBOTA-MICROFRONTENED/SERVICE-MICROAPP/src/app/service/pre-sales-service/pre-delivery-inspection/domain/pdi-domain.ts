export interface ChasisNumber {
    chassisNo: number

}
export interface Autocomplete {
    id: number;
    value: string
    code?: string
}
export interface ChasisNumberData {
    chassisNo: string;
    dmsGrnNumber: string;
    grnDate: string;
    model: string;
    engineNo: string;
    invoiceNumber: string;
}

export interface ModelNumberData {
    modelId: number;
    defaultTick: string;
    checkpointId: number;
    specification: string;
    aggregateId: number;
    aggregate: string;
    checkpointDesc: string;
    aggregateSequenceNo: number;
    fieldType: string;
    checkpointSequenceNo: number;
    observedSpecification?: string;
    remark?: string;
    id?: number;
    dropDownData?: DropDownDataSpecification[]
}
export interface PdiCheckpointList {
    defaultTick?: boolean;
    checkpointId?: number;
    aggregateId?: number;
    aggregate?: string;
    remark?: any;
    checkpointDesc?: string;
    aggregateSequenceNo?: number;
    fieldType?: string;
    checkpointSequenceNo?: number;
    specification?: string;
    observedSpecification?: string;
}
export interface DropDownDataSpecification {
    observedSpecification: string;
    id: number;
}
export interface MachineModels {
    id: number;
    model: string;
}



export interface SavePdiObject {
    draftFlag: boolean;
    id?: number;
    machineInventory: MachineInventory;
    okFlag: boolean;
    pdiDate: string;
    pdiNo: string;
    remarks: string;
    servicePdiChassisCheckpointInfoSet: ServicePdiChassisCheckpointInfoSet[];
}

export interface ServicePdiChassisCheckpointInfoSet {
    chassisCheckpointId: ChassisCheckpointId;
    observedSpecification: string;
    okFlag: boolean;
    remarks: string;
    checkpointSequenceNo:number;
    aggregateSequenceNo:number;
}

export interface ChassisCheckpointId {
    serviceMtCheckPoint: Checkpoint;
}

export interface MachineInventory {
    id: number;
}
export interface Checkpoint {
    checkpointId: number;
}

export interface SearchAutocomplete {
    id?: number;
    value: string;
    code?: string;
}



export interface Result {
    pdiHeaderData: PdiHeaderData;
    pdiCheckpointList: PdiCheckpointList[];
}



export interface PdiHeaderData {
    dmsGrnNumber?: string;
    engineNumber?: string;
    kaiInvoiceNumber?: string;
    machineModel?: string;
    pdiDate?: string;
    dmsGrnDate?: string;
    pdiNumber?: string;
    chassisNo?: string;
    chassisId?: number;
    okFlag?: string;
    remarks?: string;
}
export interface ViewChasisNumber {
    value?: string;
    id?: number;
    code?: string
}