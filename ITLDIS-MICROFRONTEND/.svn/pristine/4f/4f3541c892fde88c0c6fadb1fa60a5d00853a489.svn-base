import { Injectable } from '@angular/core';
import { DealerMasterPageStore } from './dealer-master-create.store'
import { FormGroup } from '@angular/forms';
@Injectable()
export class DealerMasterPagePresenter {

    private _operation: string;
    
    constructor(
        private dealerMasterPageStore: DealerMasterPageStore,

    ) {}

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    get dealerMaster(): FormGroup {
        return this.dealerMasterPageStore.allForm.get('dealerMaster') as FormGroup;
    }
    get dealerBank(): FormGroup {
        return this.dealerMasterPageStore.allForm.get('dealerBank') as FormGroup;
    }
    get dealerAddress(): FormGroup {
        return this.dealerMasterPageStore.allForm.get('dealerAddress') as FormGroup;
    }
    get dealerType(): FormGroup {
        return this.dealerMasterPageStore.allForm.get('dealerType') as FormGroup;
    }
    get dealerContactDetail(): FormGroup {
        return this.dealerMasterPageStore.allForm.get('dealerContactDetail') as FormGroup;
    }
    get kaiRepresentative(): FormGroup {
        return this.dealerMasterPageStore.allForm.get('kaiRepresentative') as FormGroup;
    }


}