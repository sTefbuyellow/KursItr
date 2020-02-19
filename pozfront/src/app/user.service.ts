import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PagePayload} from './add-page/page-payload';
import {UserPayload} from './user/user-payload';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getOneUser(userId: number): Observable<UserPayload> {
    return this.httpClient.get<UserPayload>('http://localhost:8080/api/users/user/' + userId);
  }

  getAllUsers(): Observable<Array<UserPayload>> {
    return this.httpClient.get<Array<UserPayload>>('http://localhost:8080/api/users/all');
  }
}
