import { Component, OnInit } from '@angular/core';
import {UserPayload} from '../user/user-payload';
import {FormControl, FormGroup} from '@angular/forms';
import {PagePayload} from '../add-page/page-payload';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../user.service';
import {LocalStorageService} from 'ngx-webstorage';
import {AuthService} from '../auth/auth.service';
import {AddPageService} from '../add-page.service';

@Component({
  selector: 'app-edit-page',
  templateUrl: './edit-page.component.html',
  styleUrls: ['./edit-page.component.css']
})
export class EditPageComponent implements OnInit {

  pageId: number;
  page: PagePayload;
  editPageForm: FormGroup;

  constructor(private router: ActivatedRoute, private pageService: AddPageService, private rout: Router,
              private localStorageService: LocalStorageService, private authService: AuthService) {
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
    this.editPageForm = new FormGroup({
      name: new FormControl(''),
      tag: new FormControl(''),
      endingDate: new FormControl(''),
      needed: new FormControl(0),
  });
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.pageId = params['id'];
    });
    this.pageService.getOnePage(this.pageId).subscribe((data: PagePayload) => {
      console.log(data);
      this.page = data;
      this.editPageForm.setValue({name: this.page.name, endingDate: this.page.endingDate, tag: this.page.tag, needed: this.page.needed});
    }, (err: any) => {
      console.log('Failure response');
    });
  }

  save() {
    this.page.name = this.editPageForm.get('name').value;
    this.page.tag = this.editPageForm.get('tag').value;
    this.page.needed = this.editPageForm.get('needed').value;
    this.page.endingDate = this.editPageForm.get('endingDate').value;
    this.pageService.refreshPage(this.page, this.pageId).subscribe((data: PagePayload) => {
        this.rout.navigateByUrl('/page/' + this.pageId);
    }, (err: any) => {
      console.log('Failure response');
    });
  }
}
