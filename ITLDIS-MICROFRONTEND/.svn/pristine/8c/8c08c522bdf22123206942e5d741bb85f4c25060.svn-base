import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DealerMasterSearchPagePresenter } from '../dealer-master-search/dealer-master-search.presenter';
import { SearchDealerService } from './search-dealer.service';
import { DealerMasterDropdown } from '../../domain/dealer-master.domain';
@Component({
  selector: 'app-search-dealer',
  templateUrl: './search-dealer.component.html',
  styleUrls: ['./search-dealer.component.scss'],
  providers: [SearchDealerService]
})
export class SearchDealerComponent implements OnInit {

  searchForm: FormGroup;
  dealerName: DealerMasterDropdown;
  dealerCode: DealerMasterDropdown;
  allocatedTerritory: DealerMasterDropdown;
  areaLevel: DealerMasterDropdown;

  constructor(
    private dealerMasterSearchPagePresenter: DealerMasterSearchPagePresenter,
    private searchDealerService: SearchDealerService,
  ) { }

  ngOnInit() {
    this.searchForm = this.dealerMasterSearchPagePresenter.SearchForm;
    this.formValueChanges();
    this.allocatedTerritoryDropdown();
    this.areaLevelDropdown();
  }

  private formValueChanges() {
    this.searchForm.get('dealerName').valueChanges.subscribe(val => {
      if (val) {
        this.dealerNameAuto(val);
      }
    });
    this.searchForm.get('dealerCode').valueChanges.subscribe(val => {
      if (val) {
        this.dealerCodeAuto(val);
      }
    });
  }

  private dealerNameAuto(txt: string) {
    this.searchDealerService.dealerNameAuto(txt).subscribe(res => {
      this.dealerName = res;
    });
  }
  private dealerCodeAuto(txt: string) {
    this.searchDealerService.dealerCodeAuto(txt).subscribe(res => {
      this.dealerCode = res;
    });
  }
  private allocatedTerritoryDropdown() {
    this.searchDealerService.allocatedTerritoryDropdown().subscribe(res => {
      this.allocatedTerritory = res;
    });
  }
  private areaLevelDropdown() {
    this.searchDealerService.areaLevelDropdown().subscribe(res => {
      this.areaLevel = res;
    });

  }

  displayCodeFn(obj: DealerMasterDropdown): string | number | undefined {
    return obj ? obj.value : undefined;
  }
}
