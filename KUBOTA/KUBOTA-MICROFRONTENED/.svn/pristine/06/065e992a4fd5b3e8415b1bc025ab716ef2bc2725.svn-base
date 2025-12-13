import { Router } from "@angular/router";
import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { UserIdleService } from "angular-user-idle";
import { AuthService } from "../../../auth/auth.service";
import { StorageLoginUser } from "LoginDto";
import { LoginFormService } from "./login-form.service";
import { NavService } from "../../../nav.service";
import { IFrameService } from "../../../root-services/iFrame.service";
import { MatDialog, MatDialogConfig } from "@angular/material";
import { ForgotPasswordComponent } from "src/app/forgot-password/forgot-password.component";
import { EncryptDecryptService } from "src/app/auth/encrypt-decrypt";

@Component({
  selector: "app-login-form",
  templateUrl: "./login-form.component.html",
  styleUrls: ["./login-form.component.scss"],
  providers: [EncryptDecryptService],
})
export class LoginFormComponent implements OnInit {
  public loginForm: FormGroup;
  public loginUser: StorageLoginUser;
  public formInvalid: boolean = false;
  public invalidCredentials: boolean = false;
  invalidLogin: string = "";
  private isLoggingIn = false;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService,
    private loginService: LoginFormService,
    private navService: NavService,
    private iframeService: IFrameService,
    private userIdle: UserIdleService,
    private dialog: MatDialog,
    private encDec: EncryptDecryptService
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [null, Validators.compose([Validators.required])],
      password: [null, Validators.compose([Validators.required])],
    });
  }
  loginToKubota() {
    if (this.loginForm.valid) {
      const username = this.loginForm.value.username;
      const password = this.loginForm.value.password;
      // Encrypt the username and password
      const encryptedUsername = this.encDec.encryptData(username);
      const encryptedPassword = this.encDec.encryptData(password);

      // Create payload with encrypted data
      const encryptedCredentials = {
        username: encryptedUsername,
        password: encryptedPassword,
      };

      this.loginService.loginKubotaUser(encryptedCredentials).subscribe(
        (userAuth) => {
          if (userAuth) {
            this.loginUser = userAuth["result"] as StorageLoginUser;
            this.loginUser.token = userAuth["token"];
            this.authService.storeLoginUser(this.loginUser);
            let loginUserId = String(
              this.encDec.decrypt(this.loginUser.loginUserId)
            );
            this.navService.getFunctionalityMappedToUser(Number(loginUserId));
            this.userIdle.startWatching();
            this.router.navigate(["/dashboard"]);
            return;
          }
        },
        (error) => {
          console.log("error", error);

          this.invalidCredentials = true;
          this.invalidLogin = error;
          // this.showDialog(error);
        }
      );
    } else {
      this.formInvalid = true;
    }
  }

  sendPasswordInMail() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.id = "modal-component";
    dialogConfig.width = "500px";
    const modalDialog = this.dialog.open(ForgotPasswordComponent, dialogConfig);
    modalDialog.afterClosed().subscribe((result) => {});
  }
}
