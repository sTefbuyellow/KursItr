import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../user.service';
import {LocalStorageService} from 'ngx-webstorage';
import {AuthService} from '../auth/auth.service';
import {FormControl, FormGroup} from '@angular/forms';
import {RegisterPayload} from '../auth/register-payload';
import {UserPayload} from '../user/user-payload';

@Component({
  selector: 'app-edit-me',
  templateUrl: './edit-me.component.html',
  styleUrls: ['./edit-me.component.css']
})
export class EditMeComponent implements OnInit {
  me: RegisterPayload;
  editMeForm: FormGroup;

  constructor(private router: ActivatedRoute, private userService: UserService, private rout: Router,
              private authService: AuthService) {
    this.editMeForm = new FormGroup({
      username: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl('')
    });
    this.me = {
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  }
  ngOnInit(): void {
    this.userService.getMyData().subscribe((data: RegisterPayload) => {
      console.log(data);
      this.me = data;
      this.editMeForm.setValue({username: this.me.username, email: this.me.email, password: this.me.password});
    }, (err: any) => {
      console.log('Failure response');
    });
  }
}
