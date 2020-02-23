import { Component, OnInit } from '@angular/core';
import {UserPayload} from '../user/user-payload';
import {UserService} from '../user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.css']
})
export class MeComponent implements OnInit {

  user: UserPayload;
  constructor(private userService: UserService, private router: Router) {
    this.user = {
      id: '',
      userName: '',
      email: '',
      role: ''
    };
  }

  ngOnInit() {
    this.userService.getMe().subscribe((data: UserPayload) => {
      console.log(data);
      this.user = data;
    }, (err: any) => {
      console.log('Failure response');
    });
  }

  createAs(userName: string) {
    this.router.navigateByUrl('/add-page/' + userName);
  }
}
