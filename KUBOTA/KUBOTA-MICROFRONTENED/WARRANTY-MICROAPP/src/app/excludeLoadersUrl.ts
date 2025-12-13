import { PCRExcludeUrl } from "./warranty/warranty-claims/product-concern-report/url-utils/product-concern-report-exclude.urls";
import { LogSheetExcludedUrl } from "./warranty/warranty-claims/log-sheet/url-utils/log-sheet-exclude-urls";
import { RfcExcludedUrl } from "./warranty/warranty-claims/retro-fitment-campaign/url-utils/retro-fitment-campaign-exclude-url";
import { WpdcExcludeUrl } from "./warranty/warranty-claims/warranty-parts-delivery-challan/url-utils/warranty-parts-delivery-challan-exclude-url";
import { WcrExcludeUrl } from './warranty/warranty-claims/warrenty-claim-request/url-utils/warrenty-claim-request-exclude-url';
import { WcrReportExcludeUrl } from './warranty/warranty-claims/wcr-report/url-utils/wcr-report-exclude-url';
import { KaiInspectionSheetExcludedUrl } from './warranty/warranty-claims/kai-inspection-sheet/url-utils/kai-inspection-sheet-exclude-url';
export const ExcludeArray = [
  ...PCRExcludeUrl.PCRExcludeUrls,
  ...LogSheetExcludedUrl.LogSheetExcludeUrls,
  ...RfcExcludedUrl.RfcExcludeUrls,
  ...WpdcExcludeUrl.WpdcExcludeUrls,
  ...WcrExcludeUrl.WcrExcludeUrls,
  ...WcrReportExcludeUrl.WcrReportExcludeUrls,
  ...KaiInspectionSheetExcludedUrl.KaiInspectionSheetExcludeUrls,
];
