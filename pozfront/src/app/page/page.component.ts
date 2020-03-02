import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';
import {AddPageService} from '../add-page.service';
import {PagePayload} from '../add-page/page-payload';
import {AuthService} from '../auth/auth.service';
import {MakeDonatePayload} from './make-donate-payload';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent implements OnInit {

  makeDonate: MakeDonatePayload;
  summ: number;
  page: PagePayload;
  pageId: number;

  constructor(private activatedRoute: ActivatedRoute, private pageService: AddPageService, private router: Router,
              private authService: AuthService) {
    this.makeDonate = {
      page: '',
      amount: 0
    };
    this.page = {
      id: '',
      name: '',
      username: '',
      tag: '',
      bonusList: [],
      creationDate: '',
      endingDate: '',
      categories: [],
      money: 0,
      needed: 0,
      images: [],
      youTubeVideo: ''
    };
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.pageId = params['id'];
    });
    this.pageService.getOnePage(this.pageId).subscribe( (data: PagePayload) => {
      console.log(data);
      this.page = data;
      this.makeDonate.page = data.name;
    }, (err: any) => {
      console.log('Failure response');
    });
  }

  deletePage() {
    this.pageService.deletePage(this.pageId).subscribe(data => this.router.navigateByUrl('/'));
  }

  donate() {
    this.makeDonate.amount = this.summ;
    console.log(this.makeDonate);
    this.pageService.makeDonate(this.makeDonate).subscribe(data => this.router.navigateByUrl('/'));
  }
}
