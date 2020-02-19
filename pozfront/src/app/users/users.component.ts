import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {Observable} from 'rxjs';
import {PagePayload} from '../add-page/page-payload';
import {UserPayload} from '../user/user-payload';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  constructor(private userService: UserService) {}

  users: Observable<Array<UserPayload>>;
  ngOnInit() {
    this.users = this.userService.getAllUsers();
  }

}
