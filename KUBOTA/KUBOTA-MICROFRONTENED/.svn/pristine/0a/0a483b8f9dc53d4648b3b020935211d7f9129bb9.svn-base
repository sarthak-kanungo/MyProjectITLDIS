import { BrowserModule } from "@angular/platform-browser";
import { NgModule, APP_INITIALIZER } from "@angular/core";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import {
  MatIconModule,
  MatInputModule,
  MatSelectModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatButtonModule,
  MatFormFieldModule,
  MatTableModule,
  MatToolbarModule,
  MatCardModule,
  MatMenuModule,
  MatDialogModule,
  MatSortModule,
  MatProgressSpinnerModule,
  MatPaginatorModule,
  MatCheckboxModule,
  MatRippleModule,
  MatStepperModule,
  MatExpansionModule,
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonToggleModule,
  MatChipsModule,
  MatDividerModule,
  MatGridListModule,
  MatListModule,
  MatProgressBarModule,
  MatRadioModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatTabsModule,
  MatTooltipModule,
  MatTreeModule,
  MAT_CHECKBOX_CLICK_ACTION,
  MAT_RIPPLE_GLOBAL_OPTIONS,
} from "@angular/material";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { NgxsModule } from "@ngxs/store";
import { ToastrModule } from "ngx-toastr";
import {
  NgxUiLoaderModule,
  NgxUiLoaderHttpModule,
  NgxUiLoaderConfig,
} from "ngx-ui-loader";
// import { ConfigAssetLoaderService } from './root-services/config-asset-loader-service';
import { PdfViewerModule } from "ng2-pdf-viewer";
import { NavbarComponent } from "./navbar/navbar.component";
import { AccessDeniedComponent } from "./access-denied/access-denied.component";
import { HelpComponent } from "./help/help.component";
import { MenuListItemComponent } from "./menu-list-item/menu-list-item.component";
import { AuthGuard } from "./auth/auth.guard";
import { AuthService } from "./auth/auth.service";
import { JwtInterceptor } from "./auth/jwt.interceptor";
import { ErrorInterceptor } from "./auth/error.interceptor";
import { BreadcrumbComponent } from "./breadcrumb/breadcrumb.component";
import { SessionExpiredComponent } from "./session-expired/session-expired.component";
import { EmptyComponent } from "./empty/empty.component";
import { IframeComponent } from "./iframe/iframe.component";
import { IFrameLayoutComponent } from "./i-frame-layout/i-frame-layout.component";
import { UserIdleModule } from "angular-user-idle";
import { ChangePasswordComponent } from "./change-password/change-password.component";
import { ChangePasswordService } from "./change-password/change-password.service";
import { ForgotPasswordComponent } from "./forgot-password/forgot-password.component";
import { ForgotPasswordService } from "./forgot-password/forgot-password.service";
import { AuthencationPasswordComponent } from "./authencation-password/authencation-password.component";
import { EncryptDecryptService } from "./auth/encrypt-decrypt";

const ngxUiLoaderConfig: NgxUiLoaderConfig = {
  bgsColor: "GREEN",
  fgsColor: "#00a8a9",
  pbColor: "#00a8a9",
  textColor: "GREEN",
};
@NgModule({
  exports: [
    MatFormFieldModule,
    MatIconModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSortModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatDialogModule,
    MatTableModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MatCheckboxModule,
    MatRippleModule,
    MatPaginatorModule,
    MatStepperModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonToggleModule,
    MatChipsModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatListModule,
    MatProgressBarModule,
    MatRadioModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatTabsModule,
    MatTooltipModule,
    MatTreeModule,
    MatExpansionModule,
  ],
  declarations: [AuthencationPasswordComponent],
})
export class MaterialModule {}

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NavbarComponent,
    ChangePasswordComponent,
    ForgotPasswordComponent,
    MenuListItemComponent,
    BreadcrumbComponent,
    AccessDeniedComponent,
    HelpComponent,
    SessionExpiredComponent,
    IframeComponent,
    EmptyComponent,
    IFrameLayoutComponent,
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: "serverApp" }),
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    MaterialModule,
    MatListModule,
    NgxsModule.forRoot([]),
    ToastrModule.forRoot({
      preventDuplicates: true,
    }),
    UserIdleModule.forRoot({ idle: 1800, timeout: 3, ping: 1 }),
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig), // import NgxUiLoaderModule
    NgxUiLoaderHttpModule.forRoot({
      showForeground: true,
      // exclude: ExcludeArray
    }),
    PdfViewerModule,
  ],
  providers: [
    AuthGuard,
    ChangePasswordService,
    ForgotPasswordService,
    AuthService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    EncryptDecryptService,
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    ChangePasswordComponent,
    ForgotPasswordComponent,
    AuthencationPasswordComponent,
  ],
})
export class AppModule {}
