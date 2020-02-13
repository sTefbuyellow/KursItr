import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PagePayload} from './add-page/page-payload';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddPageService {

  constructor(private httpClient: HttpClient) { }


  addPage(pagePayload: PagePayload) {
    return this.httpClient.post('http://localhost:8080/api/pages', pagePayload);
  }

  getAllPages(): Observable<Array<PagePayload>> {
    return this.httpClient.get<Array<PagePayload>>('http://localhost:8080/api/pages/all');
  }

  getOnePage(pageId: number): Observable<PagePayload> {
    return this.httpClient.get<PagePayload>('http://localhost:8080/api/pages/get/' + pageId);
  }
}
