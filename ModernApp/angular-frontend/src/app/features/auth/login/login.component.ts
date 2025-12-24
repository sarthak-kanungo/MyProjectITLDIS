import { Component, OnInit, signal } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../core/services/auth.service';
import { CustomCaptchaComponent } from '../../../shared/components/custom-captcha/custom-captcha.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    CustomCaptchaComponent
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  isLoading = signal(false);
  hidePassword = signal(true);
  captchaKey = signal<string>('6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI'); // Google reCAPTCHA v2 test key
  captchaResponse = signal<string>('');
  captchaLoaded = signal(false);
  captchaWidgetId: number | null = null;
  captchaError = signal<string>('');
  captchaVerified = signal<boolean>(false);
  captchaExpired = signal<boolean>(false);
  useCustomCaptcha = signal<boolean>(true); // Toggle between custom and Google reCAPTCHA
  customCaptchaComponent: CustomCaptchaComponent | null = null;
  private grecaptcha: any;
  private retryCount = 0;
  private readonly MAX_RETRIES = 3;

  // Check if username and password have values (reactive getter)
  get canSubmit(): boolean {
    if (!this.loginForm) return false;
    const username = this.loginForm.get('username')?.value || '';
    const password = this.loginForm.get('password')?.value || '';
    return username.trim().length > 0 && password.trim().length > 0;
  }

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    
    if (this.useCustomCaptcha()) {
      // Use custom captcha - no need to load Google reCAPTCHA
    } else {
      this.loadRecaptchaScript();
    }
    
    // Redirect if already authenticated
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/dashboard']);
    }
  }

  onCustomCaptchaVerified(component: CustomCaptchaComponent): void {
    this.customCaptchaComponent = component;
    if (component.verified()) {
      this.captchaResponse.set('custom-verified');
      this.captchaVerified.set(true);
      this.loginForm.patchValue({ captcha: 'custom-verified' });
      this.loginForm.get('captcha')?.updateValueAndValidity();
    }
  }

  private loadRecaptchaScript(): void {
    // Check if reCAPTCHA is already loaded (from index.html)
    if (typeof window !== 'undefined') {
      let attempts = 0;
      const maxAttempts = 50; // 5 seconds max
      
      const checkRecaptcha = setInterval(() => {
        attempts++;
        if ((window as any).grecaptcha) {
          this.grecaptcha = (window as any).grecaptcha;
          this.captchaLoaded.set(true);
          this.captchaError.set('');
          this.renderCaptcha();
          clearInterval(checkRecaptcha);
        } else if (attempts >= maxAttempts) {
          clearInterval(checkRecaptcha);
          this.captchaError.set('Failed to load reCAPTCHA. Please refresh the page.');
          this.captchaLoaded.set(false);
          console.error('reCAPTCHA failed to load after multiple attempts');
        }
      }, 100);
    } else {
      this.captchaError.set('Browser environment not available');
    }
  }

  private renderCaptcha(): void {
    if (this.grecaptcha && this.captchaLoaded()) {
      setTimeout(() => {
        const container = document.getElementById('recaptcha-container');
        if (container && !this.captchaWidgetId) {
          try {
            // Clear any existing content
            container.innerHTML = '';
            
            this.captchaWidgetId = this.grecaptcha.render(container, {
              'sitekey': this.captchaKey(),
              'theme': 'light',
              'size': 'normal',
              'tabindex': 0,
              'callback': (response: string) => {
                this.onCaptchaResolved(response);
              },
              'expired-callback': () => {
                this.onCaptchaExpired();
              },
              'error-callback': (error: any) => {
                this.onCaptchaError(error);
              }
            });
            
            this.captchaError.set('');
          } catch (error: any) {
            console.error('Error rendering reCAPTCHA:', error);
            this.captchaError.set('Failed to initialize captcha. Please try again.');
            this.retryCaptcha();
          }
        }
      }, 200);
    }
  }

  private retryCaptcha(): void {
    if (this.retryCount < this.MAX_RETRIES) {
      this.retryCount++;
      setTimeout(() => {
        this.captchaWidgetId = null;
        this.renderCaptcha();
      }, 1000 * this.retryCount); // Exponential backoff
    } else {
      this.captchaError.set('Unable to load captcha. Please refresh the page.');
    }
  }

  private initializeForm(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      captcha: ['', Validators.required]
    });
  }

  togglePasswordVisibility(): void {
    this.hidePassword.update(value => !value);
  }

  onCaptchaResolved(response: string): void {
    if (response && response.length > 0) {
      this.captchaResponse.set(response);
      this.loginForm.patchValue({ captcha: response });
      this.loginForm.get('captcha')?.updateValueAndValidity();
      this.captchaVerified.set(true);
      this.captchaExpired.set(false);
      this.captchaError.set('');
      this.retryCount = 0; // Reset retry count on success
    } else {
      this.onCaptchaError('Invalid captcha response');
    }
  }

  onCaptchaExpired(): void {
    this.captchaResponse.set('');
    this.loginForm.patchValue({ captcha: '' });
    this.captchaVerified.set(false);
    this.captchaExpired.set(true);
    this.captchaError.set('Captcha expired. Please verify again.');
    
    if (this.captchaWidgetId !== null && this.grecaptcha) {
      try {
        this.grecaptcha.reset(this.captchaWidgetId);
      } catch (error) {
        console.error('Error resetting captcha:', error);
        // Re-render if reset fails
        this.captchaWidgetId = null;
        setTimeout(() => this.renderCaptcha(), 500);
      }
    }
  }

  onCaptchaError(error: any): void {
    console.error('reCAPTCHA error:', error);
    this.captchaResponse.set('');
    this.loginForm.patchValue({ captcha: '' });
    this.captchaVerified.set(false);
    this.captchaError.set('Captcha verification failed. Please try again.');
    this.retryCaptcha();
  }

  resetCaptcha(): void {
    if (this.captchaWidgetId !== null && this.grecaptcha) {
      try {
        this.grecaptcha.reset(this.captchaWidgetId);
        this.captchaResponse.set('');
        this.loginForm.patchValue({ captcha: '' });
        this.captchaVerified.set(false);
        this.captchaExpired.set(false);
        this.captchaError.set('');
      } catch (error) {
        console.error('Error resetting captcha:', error);
      }
    }
  }

  async onSubmit(): Promise<void> {
    if (!this.canSubmit) {
      this.markFormGroupTouched(this.loginForm);
      return;
    }

    // Check if captcha is completed
    if (this.useCustomCaptcha()) {
      if (!this.customCaptchaComponent?.verified()) {
        this.snackBar.open('Please complete the security challenge.', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['error-snackbar']
        });
        this.loginForm.get('captcha')?.markAsTouched();
        return;
      }
    } else {
      if (!this.captchaResponse()) {
        this.snackBar.open('Please complete the captcha verification.', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['error-snackbar']
        });
        this.loginForm.get('captcha')?.markAsTouched();
        return;
      }
    }

    // Determine captcha value based on type
    let captchaValue = '';
    if (this.useCustomCaptcha()) {
      if (!this.customCaptchaComponent?.verified()) {
        this.snackBar.open('Please complete the security challenge.', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['error-snackbar']
        });
        return;
      }
      captchaValue = 'custom-verified';
    } else {
      if (!this.captchaResponse()) {
        this.snackBar.open('Please complete the captcha verification.', 'Close', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: ['error-snackbar']
        });
        return;
      }
      captchaValue = this.captchaResponse();
    }

    if (this.loginForm.valid && captchaValue) {
      this.isLoading.set(true);
      
      const credentials = {
        username: this.loginForm.value.username,
        password: this.loginForm.value.password,
        captcha: captchaValue
      };

      this.authService.login(credentials).subscribe({
        next: () => {
          this.snackBar.open('Login successful!', 'Close', {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'top'
          });
          this.router.navigate(['/dashboard']);
        },
        error: (error) => {
          this.isLoading.set(false);
          this.snackBar.open(
            error.error?.message || 'Login failed. Please try again.',
            'Close',
            {
              duration: 5000,
              horizontalPosition: 'end',
              verticalPosition: 'top',
              panelClass: ['error-snackbar']
            }
          );
          this.loginForm.patchValue({ captcha: '' });
          this.captchaResponse.set('');
        }
      });
    }
  }

  private markFormGroupTouched(formGroup: FormGroup): void {
    Object.keys(formGroup.controls).forEach(key => {
      const control = formGroup.get(key);
      control?.markAsTouched();
    });
  }

  getErrorMessage(controlName: string): string {
    const control = this.loginForm.get(controlName);
    if (control?.hasError('required')) {
      return `${controlName.charAt(0).toUpperCase() + controlName.slice(1)} is required`;
    }
    if (control?.hasError('minlength')) {
      const minLength = control.errors?.['minlength'].requiredLength;
      return `Minimum length is ${minLength} characters`;
    }
    return '';
  }
}

