import { Injectable } from '@angular/core';
import { MarketingActivityAddproductDomain } from 'ActivityProposalModule';

@Injectable()
export class MarketingActivityAddproductContainerService {
  addProduct = new Array<MarketingActivityAddproductDomain>()
  constructor() { }
}
