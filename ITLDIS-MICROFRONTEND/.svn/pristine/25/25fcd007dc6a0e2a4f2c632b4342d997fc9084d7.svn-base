import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule, MatInputModule, MatSelectModule, MatDatepickerModule, MatNativeDateModule, MatButtonModule, MatFormFieldModule, MatTableModule, MatToolbarModule, MatCardModule, MatMenuModule, MatDialogModule, MatSortModule, MatProgressSpinnerModule, MatPaginatorModule, MatCheckboxModule, MatRippleModule, MatStepperModule, MatExpansionModule, MatAutocompleteModule, MatBadgeModule, MatBottomSheetModule, MatButtonToggleModule, MatChipsModule, MatDividerModule, MatGridListModule, MatListModule, MatProgressBarModule, MatRadioModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatTabsModule, MatTooltipModule, MatTreeModule, MAT_CHECKBOX_CLICK_ACTION, MAT_RIPPLE_GLOBAL_OPTIONS } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgxsModule } from '@ngxs/store';
import { ToastrModule } from 'ngx-toastr';
import { NgxUiLoaderModule, NgxUiLoaderHttpModule, NgxUiLoaderConfig } from 'ngx-ui-loader';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { NavbarComponent } from './navbar/navbar.component';
import { JwtInterceptor } from './auth/jwt.interceptor';
import { ErrorInterceptor } from './auth/error.interceptor';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { EncryptDecryptService } from './auth/encrypt-decrypt';

const ngxUiLoaderConfig: NgxUiLoaderConfig = {
  bgsColor: 'GREEN',
  fgsColor: '#00a8a9',
  pbColor: '#00a8a9',
  textColor: 'GREEN'
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
})
export class MaterialModule { }

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AccessDeniedComponent,
    BreadcrumbComponent
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    MaterialModule,
    NgxsModule.forRoot([]),
    ToastrModule.forRoot(
      {
        preventDuplicates: true,
      }
    ),
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig), // import NgxUiLoaderModule
    NgxUiLoaderHttpModule.forRoot({
      showForeground: true,
      // exclude: ExcludeArray
    }),
    PdfViewerModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    EncryptDecryptService
  ],
  bootstrap: [AppComponent],
  entryComponents:[AccessDeniedComponent]
})
export class AppModule { }

