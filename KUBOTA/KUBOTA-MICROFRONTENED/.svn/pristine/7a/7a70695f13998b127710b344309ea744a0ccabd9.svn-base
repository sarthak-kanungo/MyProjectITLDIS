import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnquiryRoutingModule } from './enquiry-routing.module';
import { EnquirySearchComponent } from './pages/enquiry-search/enquiry-search.component';
import { MaterialModule } from '../../../app.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EnqiryProductInterestedComponent } from './component/enqiry-product-interested/enqiry-product-interested.component';
import { ProspectDetailsComponent } from './component/prospect-details/prospect-details.component';
import { ProspectBackgroundComponent } from './component/prospect-background/prospect-background.component';
import { CurrentMachineryDetailsComponent } from './component/current-machinery-details/current-machinery-details.component';
import { RetailFinanceComponent } from './component/retail-finance/retail-finance.component';
import { EnquiryCreateComponent } from './pages/enquiry-create/enquiry-create.component';
import { EnquiryCreationComponent } from './component/enquiry-creation-component/enquiry-creation-component.component';
import { WebserviceConfigModule } from '../../../webservice-config/webservice-config.module';
import { BlockCopyPasteDirective } from './component/prospect-details/block-copy-paste.directive';
import { EnquiryCommonServiceModule } from '../enquiry-common-service/enquiry-common-service.module';
import { EnquiryCreationContainerComponent } from './component/enquiry-creation-container/enquiry-creation-container.component';
import { CurrentMachineDetailsContainerComponent } from './component/current-machine-details-container/current-machine-details-container.component';
import { EnquiryProductInterestedContainerComponent } from './component/enquiry-product-interested-container/enquiry-product-interested-container.component';
import { ProspectBackgroundContainerComponent } from './component/prospect-background-container/prospect-background-container.component';
import { ProspectDetailsContainerComponent } from './component/prospect-details-container/prospect-details-container.component';
import { RetailFinanceContainerComponent } from './component/retail-finance-container/retail-finance-container.component';
import { EnquiryService } from './enquiry.service';
import { EnquirySearchCriteriaComponent } from './component/enquiry-search-criteria/enquiry-search-criteria.component';
import { EnquirySearchCriteriaContainerComponent } from './component/enquiry-search-criteria-container/enquiry-search-criteria-container.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { EditableTableModule } from 'editable-table';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EnquiryViewMobileComponent } from './component/enquiry-view-mobile/enquiry-view-mobile.component';
import { TransferEnquiryModule } from '../transfer-enquiry/transfer-enquiry.module';
import { EnquiryFollowupModule } from '../enquiry-followup/enquiry-followup.module';
import { SalesPreSalesCommonServiceModule } from '../../sales-pre-sales-common-service/sales-pre-sales-common-service.module';
import { EnquiryEditComponent } from './component/enquiry-edit/enquiry-edit.component';
import { EnquiryCommonService } from '../enquiry-common-service/enquiry-common.service';


@NgModule({
  declarations: [EnquiryCreateComponent, EnquirySearchComponent, EnqiryProductInterestedComponent, ProspectDetailsComponent, ProspectBackgroundComponent, CurrentMachineryDetailsComponent, RetailFinanceComponent,EnquiryCreationComponent, BlockCopyPasteDirective, EnquiryCreationContainerComponent, CurrentMachineDetailsContainerComponent, EnquiryProductInterestedContainerComponent, ProspectBackgroundContainerComponent, ProspectDetailsContainerComponent, RetailFinanceContainerComponent, EnquirySearchCriteriaComponent, EnquirySearchCriteriaContainerComponent, EnquiryViewMobileComponent, EnquiryEditComponent],
  imports: [
    CommonModule,
    EnquiryRoutingModule,
    MaterialModule, 
    FormsModule, 
    ReactiveFormsModule,
    WebserviceConfigModule,
    EnquiryCommonServiceModule,
    DynamicTableModule,
    EditableTableModule,
    NgswSearchTableModule,
    EnquiryFollowupModule,
    TransferEnquiryModule,
    SalesPreSalesCommonServiceModule
  ],
  providers: [EnquiryService, EnquiryCommonService]
})
export class EnquiryModule { }
