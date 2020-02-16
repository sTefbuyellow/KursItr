import {Component, Injectable, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {AddPageService} from '../add-page.service';
import {PagePayload} from '../add-page/page-payload';
import {HomeComponent} from '../home/home.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  search: string;

  constructor(private authService: AuthService, private pageService: AddPageService, private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.authService.logout();
  }

  public searchSend(search) {
    this.router.navigateByUrl('/search/' + search);
    this.search = '';
  }
}
