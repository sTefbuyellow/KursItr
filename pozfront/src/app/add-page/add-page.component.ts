import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {PagePayload} from './page-payload';
import {AddPageService} from '../add-page.service';
import {error} from 'util';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-page',
  templateUrl: './add-page.component.html',
  styleUrls: ['./add-page.component.css']
})
export class AddPageComponent implements OnInit {

  addPageForm: FormGroup;
  pagePayload: PagePayload;
  name = new FormControl('');
  tag = new FormControl('');
  constructor(private addPageService: AddPageService, private router: Router) { }

  ngOnInit() {
    this.addPageForm = new FormGroup({
      name: this.name,
      tag: this.tag,
    });
    this.pagePayload = {
      id: '',
      name: '',
      tag: '',
      username: ''
    };
  }

  addPage() {
    this.pagePayload.name = this.addPageForm.get('name').value;
    this.pagePayload.tag = this.addPageForm.get('tag').value;

    this.addPageService.addPage(this.pagePayload).subscribe(data => {
      this.router.navigateByUrl('/');
    }, error => {
      console.log(this.pagePayload);
    } );
  }
}
