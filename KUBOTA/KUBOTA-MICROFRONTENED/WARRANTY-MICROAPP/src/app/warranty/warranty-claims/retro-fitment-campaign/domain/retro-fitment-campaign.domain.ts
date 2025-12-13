
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';
import { WarrantyPcrPhotos } from '../../product-concern-report/domain/product-concern-report.domain';

export enum FileExtension {
  XLSX = "xlsx"
}

export interface AutoCompleteResult {
  code: string;
  value: string;
  id: number;
  partNumber: number
}

export interface PartDetails {
  itemNo: string;
  itemDescription: string;
  mrp: number;
  id: number;
}

export interface Rfc {
  campaignName: string;
  startDate: string;
  endDate: string;
  dataFilePath: string
  warrantyRetrofitmentCampaignItemList: WarrantyRetrofitmentCampaignItem[];
  warrantyRetrofitmentJcLabourChargeInfoList: warrantyRetrofitmentJcLabourChargeInfo[];
  warrantyRetrofitmentCampaignPhoto?: WarrantyPcrPhotos;
}

export interface WarrantyRetrofitmentCampaignItem {
  sparePartMaster: SparePartMaster;
  quantity: number;
}
export interface warrantyRetrofitmentJcLabourChargeInfo {
  serviceMtJobcode: serviceMtJobcode
  hours: number
  claimAmount: number
}

export interface SparePartMaster {
  id: number;
  itemNo: string;
  itemDescription?: string;
  value?: string;
}

export interface MaterialDetails {
  sparePartMaster: string;
  description: string;
  quantity: number;
  isSelected?: boolean;
}

export interface SubmitRfc {
  warrantyRetrofitmentCampaign: Rfc;
  multipartFile: File;
  multipartFileList: UploadableFile[];
}

export interface SearchWarrantyRfc {
  page: number;
  rfcNo:any
  size: number;
  // retrofitmentNumber: string;
  retrofitmentDate: string;
  campaignName: string;
  fromDate: string;
  toDate: string;
}

export interface RfcSearchStatus {
  status: string;
  id: number;
}
export interface LabourDetails {
  serviceMtJobcode: string;
  description: string;
  hours:number;
  claimAmount:number
}
export interface serviceMtJobcode {
  id: number;
  jobcode: string;
  value?: string;
  description?: string
}
export interface SearchAutocompleteJobCode {
	description: string;
	id: number;
	jobcode: string;
	value?: string
}