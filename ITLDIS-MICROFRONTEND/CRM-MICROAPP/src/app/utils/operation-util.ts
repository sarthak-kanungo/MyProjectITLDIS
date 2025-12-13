import { ActivatedRoute } from '@angular/router';
export abstract class OperationsUtil {
    static operation(actRt: ActivatedRoute) {
        return actRt.snapshot.routeConfig.path.split('/')[0]
    }
}

export enum Operation {
    VIEW = 'view', EDIT = 'edit', CREATE = 'create', JOBCARD = 'jobCard'
}