export interface AutoChasisNumber {
    id: number
    value?: string
    code: string
    engine_no?:string
    model?:string        
}
export interface DropDownDataSpecification {
    observedSpecification: string;
    id: number;
}
export interface AutoPopulateByChasisNumber {
    engineNo: string
    model: string
}

export interface CheckListByChassisNo {
    checkpointDesc: string;
    defaultTick: boolean;
    aggregate: string;
    aggregateId: number;
    aggregateSequenceNo: number;
    fieldType: string;
    checkpointId: number
    checkpointSequenceNo: number;
    remarks?: string
    okFlag?: boolean;
    observedSpecification:string;
}

export interface SaveAndSubmitPsc {
    draftFlag: boolean;
    id?: number
    machineInventory: MachineInventory;
    servicePscChassisCheckpointInfo: ServicePscChassisCheckpointInfo[];
}

export interface ServicePscChassisCheckpointInfo {
    id?: number;
    remarks: string;
    okFlag: boolean;
    observedSpecification:string;
    aggregateSequenceNo:number;
    checkpointSequenceNo:number;
    chassisCheckpointId: PscChassisCheckpointId;
}

export interface PscChassisCheckpointId {
    serviceMtCheckPoint: CheckPoint;
}

export interface MachineInventory {
    id: number;
}
export interface CheckPoint {
    checkpointId: number;
}
export interface PscNumber {
    id: number
    pscNo: string
}

export interface FilterSearchPsc {
    chassisNo: string;
    fromDate: string;
    page: number;
    pscNo: string;
    size: number;
    toDate: string;
    orgId: number
}

export interface SearchPscListTable {
    id: number;
    chassisNo: string;
    pscNo: string;
    pscDate: string;
    engineNo: string;
    machineModel: string;
    edit: string
}

export interface ViewPsc {
    pscViewHeaderData: PscViewHeaderData;
    pscCheckpointList: CheckListByChassisNo[];
}

export interface PscViewHeaderData {
    chassisNo: string;
    engineNo?: any;
    pscDate: string;
    pscNo?: any;
    model: string;
    id: number;
    chassisId: number
}