import { environment } from '../../../../../environments/environment';

export abstract class ServiceBookingApi {
    private static readonly module = 'service'
    private static readonly controller = 'jobcard'
    private static readonly controllerForBooking = 'servicebooking'
    private static readonly controllerForActivity = 'activityProposal'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${ServiceBookingApi.module}/${ServiceBookingApi.controller}`
    static readonly apiControllerForBooking = `${environment.baseUrl}/${environment.api}/${ServiceBookingApi.module}/${ServiceBookingApi.controllerForBooking}`
    static readonly apiControllerForActivity = `${environment.baseUrl}/${environment.api}/${ServiceBookingApi.module}/${ServiceBookingApi.controllerForActivity}`
  
    static readonly getDropDownMechanicName = `${ServiceBookingApi.apiController}/getDropDownMechanicName`
    static readonly dropDownServiceCategory = `${ServiceBookingApi.apiController}/dropDownServiceCategory`
    static readonly dropDownPlaceOfService = `${ServiceBookingApi.apiController}/dropDownPlaceOfService`
    static readonly dropDownSourceOfBooking = `${ServiceBookingApi.apiControllerForBooking}/dropDownSourceOfBooking`
    static readonly getChassisDetailsByChassisNoInJobCard = `${ServiceBookingApi.apiController}/getChassisDetailsByChassisNoInJobCard`
    static readonly getServiceTypeByHour = `${ServiceBookingApi.apiController}/getServiceTypeByHour`
    static readonly saveServiceBooking = `${ServiceBookingApi.apiControllerForBooking}/saveServiceBooking`
    static readonly autoCompleteChassisNoInJobCard = `${ServiceBookingApi.apiController}/autoCompleteChassisNoInJobCard`
    static readonly serviceBookingSearch = `${ServiceBookingApi.apiControllerForBooking}/serviceBookingSearch`
    static readonly autoCompleteBookingNo = `${ServiceBookingApi.apiControllerForBooking}/autoCompleteBookingNo`
    static readonly dropDownModel = `${environment.baseUrl}/${environment.api}/${ServiceBookingApi.controller}/dropDownModel`
    static readonly dropDownServiceType = `${environment.baseUrl}/${environment.api}/${ServiceBookingApi.controller}/dropDownServiceType`
    static readonly dropDownBookingStatus = `${environment.baseUrl}/${environment.api}/bookingstatus/dropDownBookingStatus`
    static readonly autoCompleteEngineNumber = `${ServiceBookingApi.apiControllerForBooking}/autoCompleteEngineNumber`
    static readonly viewServiceBookingById = `${ServiceBookingApi.apiControllerForBooking}/viewServiceBookingById`
    static readonly cancelServiceBooking = `${ServiceBookingApi.apiControllerForBooking}/cancelServiceBooking`
    static readonly getAllActivityType = `${ServiceBookingApi.apiControllerForActivity}/sbjc/getActiveActivityType`
    static readonly getActivityNumberByActivityTypeForJobCard = `${ServiceBookingApi.apiControllerForActivity}/getActivityNumberByActivityTypeForJobCard`
}//static readonly getActivityNumberByActivityTypeForJobCard = `${environment.baseUrl}${urlService.api}/service/activityProposal/getActivityNumberByActivityTypeForJobCard`