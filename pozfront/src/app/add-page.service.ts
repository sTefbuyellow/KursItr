import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PagePayload} from './add-page/page-payload';

@Injectable({
  providedIn: 'root'
})
export class AddPageService {

  constructor(private httpClient: HttpClient) { }


  addPage(pagePayload: PagePayload) {
    return this.httpClient.post('http://localhost:8080/api/pages', pagePayload);
  }
}
