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
import { MeComponent } from './me/me.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { EditPageComponent } from './edit-page/edit-page.component';
import { EditMeComponent } from './edit-me/edit-me.component';

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
    UsersComponent,
    MeComponent,
    EditUserComponent,
    EditPageComponent,
    EditMeComponent
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
          {path: 'me', component: MeComponent, canActivate: [AuthGuard]},
          {path: 'edit/:id', component: EditUserComponent, canActivate: [AuthGuard]},
          {path: 'edit-page/:id', component: EditPageComponent, canActivate: [AuthGuard]},
          {path: 'edit-me', component: EditMeComponent, canActivate: [AuthGuard]},
          {path: 'home', component: HomeComponent},
          {path: 'users', component: UsersComponent, canActivate: [AuthGuard]},
          {path: 'user/:id', component: UserComponent, canActivate: [AuthGuard]},
          {path: 'search/:name', component: SearchComponent},
          {path: 'add-page/:name', component: AddPageComponent, canActivate: [AuthGuard]},
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
