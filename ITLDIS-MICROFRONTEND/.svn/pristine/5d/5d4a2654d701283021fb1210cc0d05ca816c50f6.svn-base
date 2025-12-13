import { environment } from 'src/environments/environment';

export abstract class TrainingASApi {
    private static readonly module = 'training';
    private static readonly controller = 'attendance-sheet';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${TrainingASApi.module}/${TrainingASApi.controller}`;

    static readonly getProgramAndNomineeDtl = `${TrainingASApi.apiController}/getProgramAndNomineeDtl`;
    static readonly getAttendanceTrainers = `${TrainingASApi.apiController}/getAttendanceTrainers`;
    static readonly saveNomineAttendanceSheet = `${TrainingASApi.apiController}/saveNomineAttendanceSheet`;

    static readonly updateNomineAttendanceSheet = `${TrainingASApi.apiController}/updateNomineAttendanceSheet`;
    static readonly attendaceSheetSearch = `${TrainingASApi.apiController}/attendaceSheetSearch`;

    static readonly getViewEditData = `${TrainingASApi.apiController}/getViewEditData`;

    static readonly getProgramNoForNominee = `${TrainingASApi.apiController}/getProgramNoForNominee`;
    static readonly downloadTrainingCertificate=`${TrainingASApi.apiController}/generateTrainingCertificate`
}