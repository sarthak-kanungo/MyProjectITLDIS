export interface RepresentativeData {
    representativeName?: string,
    representativeType?: string,
    chassisNo?: string
    id?: number
    uuid?: string
    chassisId?: number;
}

export interface MachineSeries {
    series: string;
    id: number;
}

export interface AutoServiceStaffName {
    employeeName: string;
    id: number;
}

export interface ReInstallationCheckPoint {
    checkpointId: number;
    checkpointSequenceNo: number;
    aggregateSequenceNo: number;
    aggregateId: number;
    aggregate: string;
    observedSpecification: string;
    riCheckpoint: string;
    fieldType: string;
    defaultTick: boolean;
}

export interface SpecificationDropDown {
    seqNo: number;
    id: number;
    observedSpecification: string;
  }


export interface ReinstallationNumber {
    reinstallationNumber: string;
    id: number;
}

export interface FilterReInstallation {
    fromDate?: string;
    page: number;
    reInstallationNo?: string;
    series: string;
    serviceStaffName?: string;
    size: number;
    toDate?: string;
}

export interface chassisNumberList {
    code: string;
    value: string;
    id: number;
}

export interface DataByChassisNo {
    engineNo: string;
    customerName: string;
    id: number;
}

export interface RepresentativeTypesDropDown {
    id: number;
    representativeTypes: string;
}

export interface SaveAndSubmitReInstallation {
    machineSeries: MachineSeriesId;
    draftFlag: boolean;
    id?: number
    dealerEmployee: MachineSeriesId;
    serviceReinstallationChassisInfo: ServiceReinstallationChassisInfo[];
    serviceRiChassisCheckpointInfo: ServiceRiChassisCheckpointInfo[];
}

export interface ServiceRiChassisCheckpointInfo {
    riChassisCheckpointId: RiChassisCheckpointId;
    specification: string;
    okFlag: boolean;
}

export interface RiChassisCheckpointId {
    serviceMtReinstallationAggregate: MachineSeriesId;
    serviceMtReinstallationCheckpoint: MachineSeriesId;
}

export interface ServiceReinstallationChassisInfo {
    machineInventory: MachineSeriesId;
    representativeCount: number;
    serviceRepresentativeInfo: ServiceRepresentativeInfo[];
}

export interface ServiceRepresentativeInfo {
    representativeName: string;
    representativeType: string;
}

export interface MachineSeriesId {
    id: number;
}

export interface ViewReInstallation {
    riViewHeaderData: RiViewHeaderData;
    reInstallationCheckpointList: ReInstallationCheckPoint[];
    reInstallationMachineDetailsList: ReInstallationMachineDetailsList[];
    reInstallationRepresentativeDetailsList: RepresentativeData[];
}

export interface ReInstallationMachineDetailsList {
    customerName: string;
    chassisId: number;
    id: number;
    representativeCount: number;
    chassisNo: string;
    engineNo: string;
}

export interface RiViewHeaderData {
    series: string;
    serviceStaffName: string;
    reInstallationNumber: string;
    reInstallationDate: string;
    seriesId: number;
    serviceStaffId: number;
    id: number;
}