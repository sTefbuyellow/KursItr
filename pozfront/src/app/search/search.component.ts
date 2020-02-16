import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PagePayload} from '../add-page/page-payload';
import {AddPageService} from '../add-page.service';
import {Observable} from 'rxjs';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  name: string;
  constructor(private router: ActivatedRoute, private pageService: AddPageService) { }

  pages: Observable<Array<PagePayload>>;
  ngOnInit() {
    this.router.params.subscribe(params => {
      this.name = params['name'];
    });
    console.log(this.name);
    this.pages = this.pageService.findPages(this.name);
  }
}
