import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PagePayload} from './add-page/page-payload';
import {Observable} from 'rxjs';
import {UserPayload} from './user/user-payload';
import {MakeDonatePayload} from './page/make-donate-payload';

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

  findPages(name: string): Observable<Array<PagePayload>> {
    return this.httpClient.get<Array<PagePayload>>('http://localhost:8080/api/pages/get/find/' + name);
  }

  refreshPage(pagePayload: PagePayload, id: number): Observable<PagePayload> {
    return this.httpClient.put<PagePayload>('http://localhost:8080/api/pages/refresh/' + id, pagePayload);
  }

  deletePage(id: number) {
    return this.httpClient.delete<PagePayload>('http://localhost:8080/api/pages/delete/' + id);
  }

  makeDonate(makeDonate: MakeDonatePayload) {
    return this.httpClient.put<MakeDonatePayload>('http://localhost:8080/api/pages/donate', makeDonate);
  }
}
