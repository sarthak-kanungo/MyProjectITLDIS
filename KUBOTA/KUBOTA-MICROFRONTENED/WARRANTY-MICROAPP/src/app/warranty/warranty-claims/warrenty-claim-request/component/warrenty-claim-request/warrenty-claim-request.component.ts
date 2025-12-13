import { Component, OnInit, Input } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { WcrPagePresenter } from "../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter";
import { Operation } from "../../../../../utils/operation-util";

@Component({
  selector: "app-warrenty-claim-request",
  templateUrl: "./warrenty-claim-request.component.html",
  styleUrls: ["./warrenty-claim-request.component.scss"]
})
export class WarrentyClaimRequestComponent implements OnInit {
  @Input() wcrForm: FormGroup;
  operation: string;
  isView = false;
  dealerCode: string;

  constructor(private wcrPagePresenter: WcrPagePresenter) {}

  ngOnInit() {
    this.checkFormOperation();
  }

  private checkFormOperation() {
    this.dealerCode = this.wcrPagePresenter.loginUser.dealerCode;
    this.operation = this.wcrPagePresenter.operation;
    if (this.operation == Operation.VIEW) {
      this.isView = true;
    }
  }
}
