import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';
import {AddPageService} from '../add-page.service';
import {PagePayload} from '../add-page/page-payload';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent implements OnInit {

  page: PagePayload;
  pageId: number;

  constructor(private router: ActivatedRoute, private pageService: AddPageService) {
    this.page = {
      id: '',
      name: '',
      username: '',
      tag: '',
    };
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.pageId = params['id'];
    });

    this.pageService.getOnePage(this.pageId).subscribe( (data: PagePayload) => {
      console.log(data);
      this.page = data;
    }, (err: any) => {
      console.log('Failure response');
    });
  }

}
