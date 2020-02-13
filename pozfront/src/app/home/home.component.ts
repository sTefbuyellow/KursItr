import { Component, OnInit } from '@angular/core';
import {AddPageService} from '../add-page.service';
import {Observable} from 'rxjs';
import {PagePayload} from '../add-page/page-payload';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private pageService: AddPageService) { }

  pages: Observable<Array<PagePayload>>;
  ngOnInit() {
    this.pages = this.pageService.getAllPages();
  }

}
