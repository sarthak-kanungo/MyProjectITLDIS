import { Injectable } from "@angular/core";
import { BehaviorSubject, Subject } from "rxjs";
import { Router } from "@angular/router";
import { NavApiService } from "./nav-api.service";
import { UserFunctionality } from "./core/model/user-functionality.model";
import { ToastrService } from "ngx-toastr";
import { LocalStorageService } from "./root-services/local-storage.service";
import { MatDialog, MatDialogConfig } from "@angular/material";
import { AuthencationPasswordComponent } from "./authencation-password/authencation-password.component";
import { AuthService } from "./auth/auth.service";

@Injectable({
  providedIn: "root",
})
export class NavService {
  public appDrawer: any;
  public currentUrl = new BehaviorSubject<string>(undefined);
  public userFunctionality$ = new BehaviorSubject<UserFunctionality[]>(
    undefined
  );
  constructor(
    private router: Router,
    private navApiService: NavApiService,
    private _toasterService: ToastrService,
    private localStorageService: LocalStorageService,
    private dialog: MatDialog,
    private authService: AuthService
  ) {}

  public closeNav() {
    this.appDrawer.close();
  }

  public openNav() {
    this.appDrawer.open();
  }
  getFunctionalityMappedToUser(loginUserId: number) {
    this.navApiService.getFunctionalityMappedToUser(loginUserId).subscribe(
      (res) => {
        this.userFunctionality$.next(
          res.result.filter((res) => res.functionality != "Dashboard Module")
        );
      },
      (error) => {
        console.log(error);
        this._toasterService.warning(error);
        this.authService.logout();
        // console.error("API error:", error); // Check if error is being triggered
        // const dialogConfig = new MatDialogConfig();
        // dialogConfig.disableClose = true;
        // dialogConfig.id = "modal-component";
        // dialogConfig.width = "500px";
        // const modalDialog = this.dialog.open(
        //   AuthencationPasswordComponent,
        //   dialogConfig
        // );
        // modalDialog.afterClosed().subscribe((result) => {});
      }
    );
  }
}
