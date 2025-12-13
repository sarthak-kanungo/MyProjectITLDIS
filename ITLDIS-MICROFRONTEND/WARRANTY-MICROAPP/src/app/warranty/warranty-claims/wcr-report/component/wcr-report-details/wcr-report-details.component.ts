import { Component, OnInit, Input } from "@angular/core";
import { FormArray } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { WcrDetail } from "../../domain/wcr-report.domain";
import { MatCheckboxChange } from "@angular/material";
import { WcrReportPagePresenter } from "../wcr-report-create-page/wcr-report-create-page.presenter";
import {
  IFrameService,
  IFrameMessageSource
} from "../../../../../root-service/iFrame.service";

@Component({
  selector: "app-wcr-report-details",
  templateUrl: "./wcr-report-details.component.html",
  styleUrls: ["./wcr-report-details.component.scss"]
})
export class WcrReportDetailsComponent implements OnInit {
  @Input() wcrReportDetailForm: FormArray;
  wcrDetailsHeading = [
    "Select",
    "Wcr No.",
    "Wcr Date ",
    "Job Card No.",
    "Pcr No.",
    "Model",
    "Chassis No.",
    "Engine No.",
    "Hours",
    "Type of Claim",
    "No. of Times",
    "Claim Value"
  ];
  private wcrId = new Array<string>();
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private wcrReportPagePresenter: WcrReportPagePresenter,
    private iFrameService: IFrameService
  ) {}

  ngOnInit() {}

  navigateToPath(value: string, path: string) {
    console.log("path", path);
    console.log("value", value);
    if (path == "wcr") {
      this.router.navigate(["../../warrenty-claim-request/view"], {
        relativeTo: this.activatedRoute,
        queryParams: { wcrNo: btoa(value) }
      });
    } else if (path == "pcr") {
      this.router.navigate(["../../product-concern-report/view"], {
        relativeTo: this.activatedRoute,
        queryParams: { pcrNo: btoa(value), name: "pcrNo" }
      });
    } else {
      const url = "service/customerService/job-card/view";
      this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.WARRANTY, {
        url,
        queryParam: { id: btoa(value), hasButton: false }
      });
    }
  }
  isChecked(evt: MatCheckboxChange, list: WcrDetail) {
    if (evt.checked) {
      this.wcrId.push(list.id);
    } else {
      const idx = this.wcrId.indexOf(list.id);
      if (idx > -1) {
        this.wcrId.splice(idx, 1);
      }
    }
    this.wcrReportPagePresenter.wcrId(this.wcrId.toString());
  }
  checkAll(evt: MatCheckboxChange) {
    if (evt.checked) {
      this.wcrReportDetailForm.controls.forEach(elt => {
        elt.get("isSelect").patchValue(true);
        this.wcrId.push(elt.get("id").value);
      });
    } else {
      this.wcrReportDetailForm.controls.forEach(elt => {
        elt.get("isSelect").patchValue(false);
      });
      this.wcrId = [];
    }
    this.wcrReportPagePresenter.wcrId(this.wcrId.toString());
  }
}
