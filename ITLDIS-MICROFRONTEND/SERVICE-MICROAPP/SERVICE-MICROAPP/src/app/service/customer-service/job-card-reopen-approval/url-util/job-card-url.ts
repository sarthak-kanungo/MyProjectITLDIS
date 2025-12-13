import { environment } from '../../../../../environments/environment'
import { urlService } from '../../../../webservice-config/baseurl'

export abstract class JobCardUrl {
	private static readonly module = 'service'
	private static readonly master = 'servicebooking'
	private static readonly controller = 'jobcard'
	static readonly apiControl = `${environment.baseUrl}/${environment.api}/${JobCardUrl.module}/${JobCardUrl.master}`

	static readonly apiController = `${environment.baseUrl}/${environment.api}/${JobCardUrl.module}/${JobCardUrl.controller}`
	static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`

	static readonly getDropDownServiceRepresentative = `${JobCardUrl.apiController}/getDropDownServiceRepresentative`
	static readonly postJobCardTableSearchUrl = `${JobCardUrl.apiController}/searchJobCard`
	static readonly searchJobCardReopenApproval = `${JobCardUrl.apiController}/searchJobCardReopenApproval`
	static readonly chassisNumberUrl = `${JobCardUrl.apiController}/autoCompleteSearchChassisNo`
	static readonly jobCardNumberUrl = `${JobCardUrl.apiController}/searchAutoCompleteJobCode`
	static readonly engineNumberUrl = `${JobCardUrl.apiController}/searchAutoCompleteEngineNo`
	static readonly csbNumberUrl = `${JobCardUrl.apiController}/searchJobCard`
	static readonly bookingNumberUrl = `${JobCardUrl.apiControl}/autoCompleteBookingNo`
	static readonly autoCompleteSearchcsbNumber = `${JobCardUrl.apiControl}/autoCompleteSearchcsbNumber`
	static readonly updateJobcard = `${JobCardUrl.apiController}/updateJobcard`
	static readonly cancelJobcard = `${JobCardUrl.apiController}/cancelJobcard`
	static readonly reopenJobcard = `${JobCardUrl.apiController}/reopenJobcard`
	static readonly approveJobcardReopen = `${JobCardUrl.apiController}/approveJobcardReopen`
	static readonly getJobCardApprovalDetails = `${JobCardUrl.apiController}/getJobCardApprovalDetails`
	static readonly saveJobcard = `${JobCardUrl.apiController}/saveJobcard`
	static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`
	static readonly autoCompleteChassisNoInJobCard = `${JobCardUrl.apiController}/autoCompleteChassisNoInJobCard`
	static readonly getChassisDetailsByChassisNoInJobCard = `${JobCardUrl.apiController}/getChassisDetailsByChassisNoInJobCard`
	static readonly dropDownServiceCategory = `${JobCardUrl.apiController}/dropDownServiceCategory`
	static readonly dropDownPlaceOfService = `${JobCardUrl.apiController}/dropDownPlaceOfService`
	static readonly viewJobCard = `${JobCardUrl.apiController}/getJobCardByJobCardNo`
	static readonly autoCompletePartNumber = `${JobCardUrl.apiController}/autoCompletePartNumber`
	static readonly getPartDetailsByPartNumber = `${JobCardUrl.apiController}/getPartDetailsByPartNumber`
	static readonly getDropDownMechanicName = `${JobCardUrl.apiController}/getDropDownMechanicName`
	static readonly getDropDownCategory = `${JobCardUrl.apiController}/getDropDownCategory`
	static readonly dropDownServiceType = `${JobCardUrl.apiController}/getServiceTypeByHour`

	static readonly dropDownActivityEvent = `${environment.baseUrl}${urlService.api}/service/activityProposal/sbjc/getActiveActivityType`
	static readonly autoCompleteBookingNoInJobCard = `${JobCardUrl.apiControl}/autoCompleteBookingNo`
	static readonly bookingNumberData = `${JobCardUrl.apiController}/getBookingDetailsByBookingNo`
	static readonly getTotalHour = `${JobCardUrl.apiController}/getTotalHour`
	static readonly checkByChassisNo = `${JobCardUrl.apiController}/checkByChassisNo`

	static readonly autoCompletefrsNumber = `${JobCardUrl.apiController}/autoCompleteFrsCode`
	static readonly getDataFromFrsNumberUrl = `${JobCardUrl.apiController}/frsCodeDetailByFrsCode`
	static readonly autoCompletejobNumber = `${JobCardUrl.apiController}/autoCompleteOutsideJobCode`
	// http://192.168.15.133:8383/api/service/jobcard/autoCompleteOutsideJobCode?jobcode=0
	static readonly getDataFromJobNumberUrl = `${JobCardUrl.apiController}/autoCompleteOutsideJobCode`
	static readonly checkCondition = `${JobCardUrl.apiController}/closeJobCard`

	static readonly getActivityNumberByActivityTypeForJobCard = `${environment.baseUrl}${urlService.api}/service/activityProposal/getActivityNumberByActivityTypeForJobCard`

	static readonly invoiceFlagSetURL = `${JobCardUrl.apiController}/jobCardPreInvoice`

	static readonly dropDownLabourChargeCategory = `${JobCardUrl.apiController}/dropDownLabourChargeCategory`
	static readonly dropDownOsLabourChargeCategory = `${JobCardUrl.apiController}/dropDownOsLabourChargeCategory`



}
