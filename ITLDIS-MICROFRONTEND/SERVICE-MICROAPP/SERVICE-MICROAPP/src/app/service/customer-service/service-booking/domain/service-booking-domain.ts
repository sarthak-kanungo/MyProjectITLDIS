export interface DropDownMechanicName {
  name: string;
  id: number;
}

export interface ServiceCategory {
  id: number;
  category: string;
}

export interface PlaceOfService {
  id: number;
  placeOfService: string;
}

export interface SourceOfBooking {
  sourceOfBooking: string;
  id: number;
}

export interface AutoCompChassisNumber {
  code: string;
  value: string;
  machineInvetoryId: number;
}

export interface ActivityTypeDropDown {
  id?: number
  activityType?: string
}

export interface ActivityNumber {
  id?: number
  value?: string
  code?: string
  partNumber?: string
}

export interface DetailsByChassisNumber {
  previousHour: number;
  totalHour: number;
  customerName: string;
  address: string;
  previousServiceType: string;
  code: string;
  bookingDate: string
  engineNo: string;
  machineInventoryId: number;
  mobileNumber: string;
  previousServiceHour: number;
  registrationNumber?: string;
  model: string;
  customerId: number;
  modelId: number;
  nextDueServiceType: string;
  installationDate: string;
  draftFlag?: boolean
  id?: number
  previousCurrentHour?: string
  currentHour?: string;
  previousServiceDate: string
}

export interface ServiceType {
  id: number
  serviceType: string;
}

export interface MachineModel {
  id: number
  model: string;
}


export interface SaveAndSubmitServiceBooking {
  cancelBookingFlag: boolean;
  machineInventory: MachineVinMaster;
  mechanic: MachineInventory;
  hours: number;
  sourceOfBooking: MachineInventory;
  serviceCategory: MachineInventory;
  placeOfService: MachineInventory;
  customerMaster: MachineInventory;
  serviceMtServiceTypeInfo: MachineInventory;
  appointmentDate: string;
  appointmentTime: string;
  remarks: string;
  draftFlag: boolean;
  reasonForCancellation: string
  id?: number
  serviceMtActivityType: MachineInventory
  serviceActivityProposal: MachineInventory
  currentHour: number;
  totalHour: number;
  previousHour: number;
}

export interface MachineInventory {
  id: number;
}
export interface MachineVinMaster {
  vinId: number;
}

export interface FilterSearchServiceBooking {
  activityNo?: string;
  activityType?: string;
  appointmentFromDate?: string;
  appointmentToDate?: string;
  bookingFromDate?: string;
  bookingNo?: string;
  bookingToDate?: string;
  chassisNo?: string;
  engineNo?: string;
  machineSubModel?: string;
  mechanicName?: string;
  page: number;
  placeOfService?: string;
  serviceCategory?: string;
  serviceType?: string
  size: number;
  sourceOfBooking?: string;
  status?: string;
  totalCount?:number
}

export interface BookingNo {
  id: number;
  code: string;
  value: string;
}

export interface Status {
  id: number;
  status: string;
}

export interface EngineNo {
  engineNo: string;
  machineInvetoryId: number;
}

export interface ViewServiceBooking {
  mobileNumber: string;
  customerName: string;
  bookingNo: string;
  category: string;
  serviceCategoryId: number
  placeOfService: string;
  engineNo: string;
  remarks: string;
  chassisNo: string;
  previousServiceHour: number;
  name: string;
  sourceOfBooking: string;
  bookingDate: string;
  cancelBookingFlag: boolean;
  reasonForCancellation?: any;
  appointmentDate: string;
  appointmentTime: string;
  dateOfInstallation?: any;
  previousServiceType?: any;
  previousServiceDate?: any;
  nextDueServiceType?: any;
  totalHour?: any;
  id: number;
  address: string;
  status: string;
  machineInventoryId: number;
  mechanicId: number;
  customerId: number;
  sourceOfBookingId: number;
  placeOfServiceId: number;
  serviceTypeId: number;
  modelId: number;
  model: string;
  serviceType: string;
  draftFlag: boolean
  jobCardId?: number
  activityType: string
  activityTypeId: number
  activityNumber: string
  activityNoId: number
  currentHour: number
  previousHour: number
}