import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {UserPayload} from './user-payload';
import {PagePayload} from '../add-page/page-payload';
import {ActivatedRoute} from '@angular/router';
import {AddPageService} from '../add-page.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: UserPayload;
  userId: number;

  constructor(private userService: UserService, private router: ActivatedRoute) {
    this.user = {
      id: '',
      userName: '',
      email: '',
    };
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.userId = params['id'];
    });
    this.userService.getOneUser(this.userId).subscribe((data: UserPayload) => {
      console.log(data);
      this.user = data;
    }, (err: any) => {
      console.log('Failure response');
    });
  }
}
