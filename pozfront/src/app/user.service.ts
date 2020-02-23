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
    return this.httpClient.get<UserPayload>('http://localhost:8080/api/users/admin/user/' + userId);
  }

  getMe(): Observable<UserPayload> {
    return this.httpClient.get<UserPayload>('http://localhost:8080/api/users/me');
  }

  getAllUsers(): Observable<Array<UserPayload>> {
    return this.httpClient.get<Array<UserPayload>>('http://localhost:8080/api/users/admin/all');
  }

  refreshUser(userPayload: UserPayload, id: number): Observable<UserPayload> {
    return this.httpClient.put<UserPayload>('http://localhost:8080/api/users/admin/refresh/' + id, userPayload);
  }

  deleteUser(id: number) {
    return this.httpClient.delete('http://localhost:8080/api/users/admin/delete/' + id);
  }

  createAs(id: number) {
    return this.httpClient.post('http://localhost:8080/api/users/login-as', id);
  }
}
