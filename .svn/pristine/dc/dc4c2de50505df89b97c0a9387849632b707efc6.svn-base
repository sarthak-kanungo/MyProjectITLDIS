export interface ChassisNo {
  code: string;
  id: number;
  chassisNo: string;
  machineAllotmentId : number
}

export interface ChassisNoForSearch {
  value : string
  id: number;
  code: string; 
}


export interface DataByChassisNo {
  name: string;
  model: string;
  engineNo: string;
  draftFlag? : boolean
  id?: number
}

export interface CheckpointListByModel {
  modelId: number;
  aggregateId: number;
  defaultTick: boolean;
  specification: string;
  checkpointId: number;
  aggregateSequenceNo: number;
  pdcCheckpoint: string;
  fieldType: string;
  aggregate: string;
  checkpointSequenceNo: number;
  observedSpecification?: string;
  remarks?: string;
}

export interface SpecificationDropDown {
  seqNo: number;
  id: number;
  observedSpecification: string;
}

export interface SaveAndSubmitPdc {
  draftFlag: boolean;
  id? : number
  machineInventory: MachineInventory;
  okFlag: boolean;
  remarks: string;
  servicePdcChassisCheckpointInfoSet: ServicePdcChassisCheckpointInfoSet[];
}

export interface ServicePdcChassisCheckpointInfoSet {
  chassisCheckpointId: ChassisCheckpointId;
  observedSpecification: string;
  remarks: string;
  okFlag: boolean;
}

export interface ChassisCheckpointId {
  modelMaster: MachineInventory;
  serviceMtPdcAggregate: MachineInventory;
  serviceMtPdcCheckpoint: MachineInventory;
}

export interface MachineInventory {
  id: number;
}

export interface FilterPdcSearch {
  chassisNo?: string;
  page: number;
  pdcFromDate?: string;
  pdcToDate?: string;
  size: number;
  orgId: number;
}

export interface SearchPdcListTable {
  id: number;
  chassisNo: string;
  pdcNo: string;
  engineNo: string;
  model: string;
  name: string;
  edit : string
}

export interface ViewPdc {
  pdcViewHeaderResponse: PdcViewHeaderResponse;
  pdcCheckpointList: CheckpointListByModel[];
}

export interface PdcViewHeaderResponse {
  model: string;
  chassisNo: string;
  engineNo: string;
  pdcNo: string;
  pdcDate: string;
  pdcId: number;
  name: string;
  remarks: string;
  chassisId?: any;
  okFlag: boolean;
}