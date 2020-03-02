import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {PagePayload} from './page-payload';
import {AddPageService} from '../add-page.service';
import {error} from 'util';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-add-page',
  templateUrl: './add-page.component.html',
  styleUrls: ['./add-page.component.css']
})
export class AddPageComponent implements OnInit {

  otherName = '';
  addPageForm: FormGroup;
  pagePayload: PagePayload;
  constructor(private addPageService: AddPageService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.otherName = params['name'];
    });
    this.addPageForm = new FormGroup({
      name: new FormControl(''),
      tag: new FormControl(''),
      endingDate: new FormControl(''),
      needed: new FormControl(null),
      bonus: new FormControl('')
    });
    this.pagePayload = {
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
      youTubeVideo: '',
    };
  }

  addPage() {
    this.pagePayload.username = this.otherName;
    this.pagePayload.name = this.addPageForm.get('name').value;
    this.pagePayload.tag = this.addPageForm.get('tag').value;
    this.pagePayload.endingDate = this.addPageForm.get('endingDate').value;
    this.pagePayload.needed = this.addPageForm.get('needed').value;
    this.pagePayload.bonusList.push(this.addPageForm.get('bonus').value);
    this.pagePayload.categories.push('category');

    console.log(this.otherName);
    console.log(this.pagePayload);
    this.addPageService.addPage(this.pagePayload).subscribe(data => {
      this.router.navigateByUrl('/');
    }, error => {
      console.log(this.pagePayload);
    });
  }
}
