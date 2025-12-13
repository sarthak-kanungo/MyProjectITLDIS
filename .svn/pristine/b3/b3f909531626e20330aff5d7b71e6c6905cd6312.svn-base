export interface RepresentativeTypes {
    id: number;
    representativeType: string;
}
export interface AutoChassisNumber {
    code: string;
    id: number;
    value: string;
}
export interface AutoCsbNumber {
    code?: string;
    id?: number;
    value?: string;
}

export interface InstallationNumber {
    installationNumber: string;
    id: number;
}

export interface ServiceStaffName {
    employeeName: string;
    id: number;
}

export interface DetailsByChassisNo {
    model: string;
    customerName: string;
    installationType?: string;
    id: number;
    engineNo: string;
    installationId: number
    csbNo:string
}

export interface InstallationCheckList {
    aggregateCheckpointMappingActiveStatus?: string;
    modelId?: number;
    checkpointId?: number;
    aggregateCheckpointMappingId?: number;
    aggregateId?: number;
    diCheckpoint?: string;
    fiCheckpoint?: string;
    checkpointSequenceNo?: number;
    diAggregateId?: number;
    diCheckpointId?: number;
    fiAggregateId?: number;
    fiCheckpointId?: number;
    aggregate?: string;
    specification?: string;
    aggregateActiveStatus?: string;
    checkpointActiveStatus?: string;
    aggregateSequenceNo?: number;
    fieldType?: string;
    defaultTick?: boolean;
    remarks?: string
    observedSpecification?: string
}

export interface DropDownSpecification {
    seqNo: number;
    id: number;
    observedSpecification: string;
}

export interface Installation {
    serviceMachineInstallation : SaveAndSubmitInstallation
    multipartFileList : File[]
}

export interface SaveAndSubmitInstallation {
    csbNumber: string;
    customerRepName: string;
    representativeType: string;
    dealerEmployee: MachineInventory;
    draftFlag: boolean;
    id?: number
    machineInstallationType: MachineInventory;
    machineInventory: MachineInventory;
    serviceDiChassisCheckpointInfo?: ServiceChassisCheckpointInfo[];
    serviceFiChassisCheckpointInfo?: ServiceChassisCheckpointInfo[];
    serviceInstallationPhotosList: File[];
}

export interface ServiceChassisCheckpointInfo {
    diChassisCheckpointInfo?: ChassisCheckpointInfo;
    fiChassisCheckpointInfo?: ChassisCheckpointInfo;
    observedSpecification: string;
    okFlag: boolean;
    remarks: string;
}

export interface ServiceInstallationPhotosList {
    fileName: string;
  }
  

export interface ChassisCheckpointInfo {
    modelMaster: MachineInventory;
    serviceMtDeliveryInstallationAggregate?: MachineInventory;
    serviceMtDeliveryInstallationCheckpoint?: MachineInventory;
    serviceMtFieldInstallationAggregate?: MachineInventory;
    serviceMtFieldInstallationCheckpoint?: MachineInventory;
}

export interface MachineInventory {
    id: number;
}

export interface FilterInstallationSearch {
    dealerCode: string;
    dealerName:string
    dealerId:number
    chassisNo: string
    installationNo: string
    installationType: string
    fromDate: string
    toDate: string
    page: number
    size: number
}

export interface SearchInstallationList {
    id?: any;
    chassisNo: string;
    installationType?: string;
    installationNumber: string;
    installationDate?: string;
    engineNo: string;
    model: string;
    serviceStaffName?: string;
    serviceStaffNameId: number
    representativeType?: string;
    customerRepName?: string;
    customerName: string;
    csbNumber?: any;
    edit?: any;
    chassisId?: number
    installationTypeId?: number
}

export interface ViewInstallation {
    installationViewHeaderData: SearchInstallationList;
    installationCheckpointList?: InstallationCheckList[]
    machineInstallationPhotoList?: ServiceInstallationPhotosList[]
}
