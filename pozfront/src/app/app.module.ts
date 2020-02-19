import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterSuccessComponent } from './auth/register-success/register-success.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {Ng2Webstorage} from 'ngx-webstorage';
import { HomeComponent } from './home/home.component';
import { AddPageComponent} from './add-page/add-page.component';
import {EditorModule} from '@tinymce/tinymce-angular';
import {HttpClientInterceptor} from './http-client-interceptor';
import { PageComponent } from './page/page.component';
import {AuthGuard} from './auth.guard';
import { SearchComponent } from './search/search.component';
import { UserComponent } from './user/user.component';
import { UsersComponent } from './users/users.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    RegisterSuccessComponent,
    HomeComponent,
    AddPageComponent,
    PageComponent,
    SearchComponent,
    UserComponent,
    UsersComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FormsModule,
        ReactiveFormsModule,
        Ng2Webstorage.forRoot(),
        RouterModule.forRoot([
          {path: 'page/:id', component: PageComponent},
          {path: '', component: HomeComponent},
          {path: 'home', component: HomeComponent},
          {path: 'users', component: UsersComponent},
          {path: 'user/:id', component: UserComponent},
          {path: 'search/:name', component: SearchComponent},
          {path: 'add-page', component: AddPageComponent, canActivate: [AuthGuard]},
          {path: 'register', component: RegisterComponent},
          {path: 'login', component: LoginComponent},
          {path: 'register-success', component: RegisterSuccessComponent}
        ]),
        HttpClientModule,
        EditorModule
    ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
