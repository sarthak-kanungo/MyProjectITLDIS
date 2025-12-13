export interface SearchAutocomplete {
	id?: number
	value?: string
	code?: string
	partNumber?: string
	itemNo?: string
	frsCode?: string;
	machineInventoryId?:number;
}
export interface SearchAutocompleteJobCode {
	description: string;
	id: number;
	jobCodeNo: string;
	
	value?: string
}
export interface ChassisNumberJobData {
	model?: string
	customerName?: string
	installationDate?: string
	previousCurrentHour?: string
	seviceBookingId?: number
	bookingNo: string
	registrationNumber?: any
	code?: string
	address?: string
	
	totalWarrantyHour?:number
	warrantyEndDate?:string
	
	mobileNumber?: string
	alternateNumber?:string
	bookingDate?: Date
	customerId?: number
	previousHour?: number
	totalHour?: number
	machineInventoryId?: number
	modelId?: number
	engineNo?: string
	dealerName?: string
	placeOfService?: string
	category?: string
	draftFlag?: boolean | string
	currentHour?: number
	retrofitmentFlag?: boolean
	dateOfFailure?: Date;
	serviceType?: string
	categoryId?: number
	serviceTypeId?: number;
	csbNumber?: string;
	soldDealer?: string;
	result?:boolean
	ServiceBookingRemarks:string
}
export interface PlaceOfServiceDropDownList {
	id: number
	placeOfService: string
}
export interface ServiceCategoryDropDownList {
	id: number
	category: string
}
export interface FinalSaveJobCard {
	serviceJobCard: ServiceJobCard
	fsCouponPage1: File[]
	fsCouponPage2: File[]
	hourMeterPhoto: File[]
	chassisPhoto: File[]
	signedJobcard:File[]
	retroAcknowledgementForm:File[]
}
export interface ServiceJobCard {
	currentHour?: number
	customerName:string
	soldDealer:string
	mobileNumber:string
	address:string
	totalHour?: number
	previousHour?: number
	estimatedAmount?: number
	estimatedCompletionDate?: string
	installationDate?:string
	alternateNumber?: number;
	dateOfFailure?: string;
	finalActionTaken?: string
	suggestionToCustomer?: string
	registrationNumber?: string
	taskDate: string
	taskTime: string
	outDateTime:string
	jobCardOkFlag: boolean
	meterChangeHour: number;
	draftFlag: boolean
	serviceCategory: ServiceCategory
	placeOfService: string
	machineInventory: MachineInventory
	serviceRepresentative?: ServiceRepresentative
	serviceType: ServiceType
	serviceBooking: ServiceBooking
	mechanic?: MechanicName
	customerMaster?: CustomerMaster
	sparePartRequisitionItem?: SparePartRequisitionItem[]
	labourCharge: LabourCharge[];
	outsideJobCharge: OutsideJobCharge[];
	customerConcern?: string
	mechanicObservation?: string
	id?: number
	serviceActivityProposal: ServiceActivityProposal
	closeDelayReason:string
	closeRemark?:string
	jobcardRetroMappings:jobcardRetroMappings[]
	// jobcardRetroMappings:jobcardRetroMappings[]
}
export interface jobcardRetroMappings{
   id:any
   warrantyRetrofitmentCampaign:warrantyRetrofitmentCampaign
   machineInventory:machineInventory
}
export interface warrantyRetrofitmentCampaign{
	id:number
	retrofitmentNo: string,
	campaignName: string,
	status: string
}
export interface machineInventory{
	vinId: number,
	chassisNo: string
}

export interface ServiceCategory {
	category: string
	id: number
}
export interface ServiceActivityProposal {
	id?: number
}
export interface ServiceBooking {
	id: number
}
export interface ServiceType {
	id: number
}

export interface CustomerMaster {
	id: number
}
export interface MechanicConcern {
	mechanicObservation: string
}

export interface CustomerConcern {
	concern: string
}
export interface LabourCharge {
	serviceMtFrsCode: ServiceMtFrsCode;
	amount: number;
}
export interface OutsideJobCharge {
	serviceMtJobcode: ServiceMtJobcode;
	amount: number
}
export interface ServiceMtFrsCode {
	id: number;
}
export interface ServiceMtJobcode {
	id: number;

}
export interface SparePartRequisitionItem {
	sparePartMaster: SparePartMaster
	reqQuantity: number
	uom: number
	itemDescription?: string
	priceUnit: number
	amount: number
	category: string
	approveStatus?: string
	utilizedQuantity?: number
	approveQuantity?: number
	partNumber?: string
	quantity?: number
	mrp?: number
	sparePartId?: number
	isSelected?: boolean
	qtyUpdateFlag?: boolean
}

export interface SparePartMaster {
	sparePartId: number
	id?: string
	itemNo: string
	itemDescription?: string
	partNumber?: string
}

export interface MachineInventory {
    vinId: number
}
export interface ConditionCheck { }

export interface ViewJobCard {
	jobCardNo: string
	jobCardDate: string
	taskDate: string
	//preinvoiceFlag?: boolean
	partIssueFlag?: boolean;
	closeRemark?:string;
	taskTime: string
	outDateTime: string
	draftFlag?: boolean
	status?: string
	installationDate:string 
	soldDealer:string
	totalWarrantyHour?:number
	warrantyEndDate?:string
	customerMaster: CustomerMasterView
	customerName?: string
	alternateNumber?: number
	address?: string;
	pcrApprovedFlag?: boolean;
	mobileNumber?: number
	dateOfFailure: Date;
	serviceType: ServiceType
	totalHour: string
	meterChangeHour:number
	currentHour: number
	mechanic: Mechanic
	registrationNumber: string
	customerConcern: string
	mechanicObservation: string
	previousHour: number
	serviceRepresentative: ServiceRepresentative
	serviceJobcardPhotos: ServiceJobcardPhoto[]
	machineInventory: MachineInventoryView
	serviceActivityProposal: ServiceActivityProposalView;
	sparePartRequisitionItem: SparePartRequisitionItemView[]
	labourCharge: LabourChargeView[];
	outsideJobCharge: OutsideJobChargeView[];
	finalActionTaken: string
	suggestionToCustomer: string
	serviceBooking: ServiceBookingView
	serviceCategory: ServiceCategoryView
	placeOfService: string
	closedDate:string
	closeDelayReason:string
	id: number
	pcrNo?: string
	gwNo?:string        
	pcrRaiseFlag?: boolean
	invoicedFlag?:boolean
	allowVideoUpload?:boolean
	goodwillRaised?:boolean
    goodwillRequired?:boolean
    goodwillApproved?:boolean
	isLatest?:boolean
}
export interface ServiceActivityProposalView {
	activityNumber: string;
	serviceMtActivityType: ServiceMtActivityType;
	id: number;
	category: Category
}
export interface ServiceMtActivityType {
	activityType: string;
	id: number;
}

export interface OutsideJobChargeView {
	id: number;
	serviceMtJobcode: ServiceMtJobcode;
	amount: number;
	approvedAmount?: any;
	inspectionRemark?: any;
	claimable?: any;
	jobcodeId?: number
	pcrAmount?:number;        
	category: Category
}
export interface ServiceMtJobcode {
	id: number;
	jobcode: string;
	description: string;
	activeStatus: string;
}
export interface LabourChargeView {
	id: number;
	serviceMtFrsCode: ServiceMtFrsCodeView;
	amount: number;
	approvedAmount?: any;
	labourChargeId: number
	category: Category;
	pcrAmount?:number;
}

export interface ServiceMtFrsCodeView {
	id: number;
	jobCodeNo: string;
	description: string;
	time: number;
	modelMaster: ModelMasterLabour;
	activeStatus: string;
}
export interface ModelMasterLabour {
	id: number;
	model: string;
	activeStatus: string;
}
export interface ServiceCategoryView {
	category: string
	id: number
}

export interface ServiceType {
	serviceType: string
	id: number
}
export interface ServiceBookingView {
	bookingNo: string
	bookingDate: string
	id: number
}
export interface Mechanic {
	lastName: string
	id: number
	name: string
}
export interface CustomerMasterView {
	mobileNumber: string
	id: number
	customerName: string
	address: string
}
export interface SparePartRequisitionItemView {
	reqQuantity?: number
	itemDescription?: string
	priceUnit?: number
	utilizedQuantity?: number
	partNumber?: string
	category: Category
	amount: number
	sparePartMaster: SparePartMaster
	uom?: any
	approveStatus?: any
	quantity: number
	mrp: number
	sparePartId?: number
	isSelected?: boolean
	approveQuantity: number
	utilisedQuantity?: number
	qtyUpdateFlag?: boolean
	pcrQuantity?:number        
}
export interface Category {
	id: number
	category: string
}

export interface MachineInventoryView {
	chassisNo: string
	engineNo: string
	vinId: number
	registrationNumber?: string
	csbNumber?: string
}

export interface ServiceJobcardPhoto {
	id: number
	fileName: string
	fileType: string
}
export interface PartNumberData {
	itemNo: string
	itemDescription: string
	id: number
	mrp: number
}
export interface MechanicName {
	id: number
	name: string
}
export interface DropDownCategory {
	category: string
	id: number
}
export interface ServiceRepresentative {
	name: string
	id: number
}

export interface checkByChassisNo {
	result: number
	draftFlag: boolean
	jobCardNo: string
}

export interface ServicetypesList {
	serviceType: string
	id?: number
}
export interface ActivityEventTypesList {
	activityType: string
	id: number
}

export interface BookingNoJobData {
	customerName: string
	address: string
	serviceCategory: string
	engineNo: string
	serviceType: string
	bookingNo: string
	model: string
	mobileNo: string
	placeOfService: string
	previousHour: number
	chassisNo: string
	bookingDate: string
	installationDate: string
	machineInventoryId?: number
	currentHour?: number
	totalHour?: number
	categoryId?: number
	customerId?: number
	serviceTypeId?: number
	csbNumber?: string
	soldDealer?: string
	registrationNumber?: string
	previousCurrentHour?: string
	ServiceBookingRemarks:string
}

export interface TotalHoursCalculation {
	totalHour: number
}

export interface FrsNumberData {
	frsCode: string;
	description: string;
	time: number;
	id: number;
    amount:number;
}
export interface JobNumberData {
	description: string;
}

export interface CheckChassisNumber {
	result: number
}

export interface ExcelView {
	value: string;
	viewValue: string;
  }

export interface AutoDealerCodeSearch {
    id: number,
    code: string,
    name: string,
    displayString: string
}

export interface Product {
    product: string
}
export interface SeriesByProduct {
    series: string;
}

export interface ModelBySeries {
    model: string;
}
export interface AutoCompSubModel {
    subModel: string;
}

export interface Variants {
    variant: string;
}
export interface PopulateByItemNo {
    model?: string;
    subModel?: string;
    variant?: string;
    series?: string;
    product?: string;
    itemDescription?: string;
}
export interface AutoCompVariant {
    variant: string;
}