import { Component, OnInit, EventEmitter, Output } from "@angular/core";
import { AuthService } from "../auth/auth.service";
import { LoginFormService } from "../login/component/login-form/login-form.service";
import { StorageLoginUser } from "LoginDto";
import { NavService } from "../nav.service";
import { UserIdleService } from "angular-user-idle";
import { MatDialog, MatDialogConfig, MatDialogRef } from "@angular/material";
import { ChangePasswordComponent } from "../change-password/change-password.component";
import { Router } from "@angular/router";
import {
  IFrameMessageSource,
  IFrameService,
} from "../root-services/iFrame.service";
import { EncryptDecryptService } from "../auth/encrypt-decrypt";
import { AuthencationPasswordComponent } from "../authencation-password/authencation-password.component";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"],
  providers: [EncryptDecryptService],
})
export class NavbarComponent implements OnInit {
  @Output()
  sideClick: EventEmitter<any> = new EventEmitter();
  isLoggedIn$: boolean = false;
  isLoggedOut$: boolean = true;
  modalDialog: MatDialogRef<ChangePasswordComponent>;
  dialogRef: MatDialogRef<AuthencationPasswordComponent>;
  user = {} as StorageLoginUser;
  todayDate = new Date();
  userCode: any;
  emailId: any;
  mobileNo: any;
  constructor(
    private userIdle: UserIdleService,
    private authService: AuthService,
    private loginFormService: LoginFormService,
    private navService: NavService,
    private dialog: MatDialog,
    private router: Router,
    private iframe: IFrameService,
    private encDecService: EncryptDecryptService
  ) {}
  ngOnInit() {
    this.user = this.loginFormService.getLoginUser();
    this.userCode = this.encDecService.decrypt(this.user.username);
    this.emailId = this.encDecService.decrypt(this.user.emailId);
    this.mobileNo = this.encDecService.decrypt(this.user.mobileNo);

    this.authService.loggedIn.subscribe((value) => {
      this.isLoggedIn$ = value as boolean;
    });
    this.isLoggedIn$ = this.authService.checkUserIsLogged();

    this.userIdle
      .onTimerStart()
      .subscribe((count) => console.log("count", count));

    this.userIdle.onTimeout().subscribe(() => {
      console.log("Time is up!");
      this.stopWatching();
    });

    if (this.user) {
      let loginUserId = String(
        this.encDecService.decrypt(this.user.loginUserId)
      );
      this.navService.getFunctionalityMappedToUser(Number(loginUserId));
    }
  }
  stopWatching() {
    this.userIdle.stopWatching();
    this.authService.sessionExpired();
    this.navService.closeNav();
    if (this.modalDialog) {
      this.modalDialog.close();
    }
    if (this.dialogRef) {
      this.dialogRef.close();
    }
  }
  logout() {
    this.logOut();
    this.authService.logout();

    this.navService.closeNav();
    if (this.modalDialog) {
      this.modalDialog.close();
    }
  }

  logOut() {
    // this.loginFormService.logOut().subscribe((res) => {
    //   if (res) {
    //     console.log(res, "res");
    //   }
    // });
  }

  minimizeSidebar() {
    this.sideClick.emit(true);
  }

  changePasswordPopup() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    this.modalDialog = this.dialog.open(ChangePasswordComponent, dialogConfig);
    this.modalDialog.afterClosed().subscribe((result) => {});
  }

  dashboard() {
    if (this.router.url.startsWith("/dashboard")) {
      const url = "dashboard";
      this.iframe.sendRouteChangeRequest(IFrameMessageSource.MAIN, { url });
    } else {
      this.router.navigate(["/dashboard"]);
    }
  }
}
