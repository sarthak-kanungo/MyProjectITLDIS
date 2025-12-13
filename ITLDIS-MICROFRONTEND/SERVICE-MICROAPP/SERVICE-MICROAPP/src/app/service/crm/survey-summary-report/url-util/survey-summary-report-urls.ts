import { urlService } from "src/app/webservice-config/baseurl"
import { environment } from "src/environments/environment"

export abstract class SurveySummaryReportApi {
    private static readonly module = '/crm/crmmodule'
    private static readonly controller = '/surveysummaryreport'


    static readonly surveyStatus = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getSurveyStatus}`
    static readonly surveyDate = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getSurveyDate}`
    static readonly surveySatisfaction = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getSurveySatisfaction}`
    static readonly surveyName = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getSurveyName}`
    static readonly searchSurveySurmmaryReport = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.searchSurveySummaryReport}`
    static readonly allZone = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getZone}`
    static readonly regionByZone = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getRegions}`
    static readonly areaByRegion = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getRegions}`
    static readonly territoryByArea= `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getRegions}`

    static readonly getChassisNo = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getChassisNo}`

    static readonly getSurveyNo = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getSurveyNo}`
    static readonly getQuestion = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getQuestion}`
    static readonly getQASurveyName = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.getQASurveyName}`

    static readonly downloadSurveyDetailsExcelReport = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.downloadSurveyDetailsExcelReport}`


    static readonly surveySummaryQandAReport = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.surveySummaryQandAReport}`

    static readonly downloadSurveyQandAExcelReport = `${environment.baseUrl}${urlService.api}${SurveySummaryReportApi.module}${SurveySummaryReportApi.controller}${urlService.downloadSurveyQandAExcelReport}`


}