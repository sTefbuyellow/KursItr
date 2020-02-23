import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserPayload} from '../user/user-payload';
import {UserService} from '../user.service';
import {FormControl, FormGroup} from '@angular/forms';
import {LocalStorageService} from 'ngx-webstorage';
import {AuthService} from '../auth/auth.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  userId: number;
  user: UserPayload;
  editUserForm: FormGroup;

  constructor(private router: ActivatedRoute, private userService: UserService, private rout: Router,
              private localStorageService: LocalStorageService, private authService: AuthService) {
    this.editUserForm = new FormGroup({
      userName: new FormControl(''),
      email: new FormControl(''),
      role: new FormControl('')
    });
    this.user = {
      id: '',
      userName: '',
      email: '',
      role: ''
    };
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.userId = params['id'];
    });
    this.userService.getOneUser(this.userId).subscribe((data: UserPayload) => {
      console.log(data);
      this.user = data;
      this.editUserForm.setValue({userName: this.user.userName, email: this.user.email, role: this.user.role});
    }, (err: any) => {
      console.log('Failure response');
    });
  }

  isAdmin(role: string): boolean {
     if (role === 'ROLE_ADMIN') {
       return true;
     }
     return false;
  }


  save() {
  this.user.role = this.editUserForm.get('role').value;
  this.user.email = this.editUserForm.get('email').value;
  this.user.userName = this.editUserForm.get('userName').value;
  console.log(this.user.role + ' ' + this.localStorageService.retrieve('role'));
  this.userService.refreshUser(this.user, this.userId).subscribe((data: UserPayload) => {
      console.log(data);
      if (this.localStorageService.retrieve('role') === '[ROLE_ADMIN]' &&  this.user.role === 'ROLE_USER') {
        this.authService.logout();
      } else {
        this.rout.navigateByUrl('/user/' + this.userId);
      }
    }, (err: any) => {
      console.log('Failure response');
    });
  }
}
