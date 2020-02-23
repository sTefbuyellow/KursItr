import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {UserPayload} from './user-payload';
import {PagePayload} from '../add-page/page-payload';
import {ActivatedRoute, Router} from '@angular/router';
import {AddPageService} from '../add-page.service';
import {AuthService} from '../auth/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: UserPayload;
  userId: number;

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private router: Router,
              private authService: AuthService) {
    this.user = {
      id: '',
      userName: '',
      email: '',
      role: ''
    };
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.userId = params['id'];
    });
    this.userService.getOneUser(this.userId).subscribe((data: UserPayload) => {
      console.log(data);
      this.user = data;
    }, (err: any) => {
      console.log('Failure response');
    });
  }

  deleteUser() {
    console.log(this.userId);
    this.userService.deleteUser(this.userId).subscribe(data => this.router.navigateByUrl('/users'));
  }

  createAs(name: string) {
   this.router.navigateByUrl('/add-page/' + name);
  }
}
