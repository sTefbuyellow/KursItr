import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {RegisterPayload} from '../register-payload';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {LoginPayload} from '../login-payload';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  registerPayload: RegisterPayload;
  loginPayload: LoginPayload;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.formBuilder.group({
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    });
    this.registerPayload = {
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
    this.loginPayload = {
      username: '',
      password: '',
    };
  }

  ngOnInit() {
  }

  onSubmit() {
    this.registerPayload.username = this.registerForm.get('username').value;
    this.registerPayload.email = this.registerForm.get('email').value;
    this.registerPayload.password = this.registerForm.get('password').value;
    this.registerPayload.confirmPassword = this.registerForm.get('confirmPassword').value;

    this.loginPayload.username = this.registerPayload.username;
    this.loginPayload.password = this.registerPayload.password;

    this.authService.register(this.registerPayload).subscribe(data1 => {
      console.log('register success');
      this.authService.login(this.loginPayload).subscribe(data2 => {
        if (data2) {
          console.log('login success');
          this.router.navigateByUrl('/home');
        } else {
          console.log('login error');
        }
       });
    }, error => {
      console.log('register failed');
    });
  }
}
