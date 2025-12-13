import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../auth/auth.service';
import { NavService } from '../nav.service';
import { ChangePasswordService } from './change-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  passwordResetForm:FormGroup;
  newPasswordError:string='';
  oldPasswordError:string='';
  tcount:number = 0;
  constructor(private dialogRef : MatDialogRef<ChangePasswordComponent>,
    private changePasswordService : ChangePasswordService,
    private toastr : ToastrService,
    private navService: NavService,
    private authService: AuthService) { }

  ngOnInit() {
    this.passwordResetForm = this.changePasswordService.createPasswordResetForm();
    
    this.passwordResetForm.controls.newPassword.valueChanges.subscribe(newPassword => {
      if(newPassword){
        const oldPassword = this.passwordResetForm.controls.oldPassword.value;
        this.passwordResetForm.controls.confirmPassword.reset();
        if(oldPassword==undefined || oldPassword==null || oldPassword==''){
          this.passwordResetForm.controls.newPassword.reset();
          this.passwordResetForm.controls.oldPassword.setErrors({oldPasswordError:"Enter Old Password"});
          this.oldPasswordError="Enter Old Password";
        }else{
          if(newPassword===oldPassword){
            this.passwordResetForm.controls.newPassword.setErrors({newPasswordError:"New Password does not match Old Password"});
            this.newPasswordError='New Password does not match Old Password';
          }
        }
      }
    });

    this.passwordResetForm.controls.confirmPassword.valueChanges.subscribe(confirmPassword => {
      const newPassword = this.passwordResetForm.controls.newPassword.value;
      if(confirmPassword){
        this.passwordResetForm.controls.confirmPassword.setErrors({confirmPasswordError:"Password does not match"});
        
        if(newPassword && newPassword === confirmPassword){
          this.passwordResetForm.controls.confirmPassword.setErrors(null);
        }else if(newPassword==undefined || newPassword==null || newPassword==''){
          this.passwordResetForm.controls.confirmPassword.reset();
          this.passwordResetForm.controls.newPassword.setErrors({newPasswordError:"Enter New Password"});
          this.newPasswordError='Enter New Password';
        }
      }
    });

  }

  public close(){
    this.dialogRef.close();
  }
  
  public submit(){
    //this.dialogRef.close({event:'upload',data:this.file}); 
    this.passwordResetForm.markAllAsTouched();
    if(this.passwordResetForm.valid){
      const postbody = {...this.passwordResetForm.getRawValue(),...{count:this.tcount}}
      this.changePasswordService.resetPassword(postbody).subscribe(response => {
        if(response){
          if(response['result']['Msg']=='Done'){
            this.toastr.success("Password updated successfuly");
            this.dialogRef.close();
            this.authService.logout();
            this.navService.closeNav();
          }else{
            if(response['result']['Msg']==='Incorrect Password'){
              this.passwordResetForm.controls.oldPassword.setErrors({oldPasswordError:response['result']['Msg']});
              this.oldPasswordError = response['result']['Msg'];
            }else{
              this.passwordResetForm.controls.newPassword.setErrors({newPasswordError:response['result']['Msg']});
              this.newPasswordError = response['result']['Msg'];
            }
            this.tcount = response['result']['resetCount'];
          }
        }
      })
    } 
  }

  preventWhiteSpace(event){
    if (event.code === 'Space') {
      event.preventDefault();
    }
  }
}
