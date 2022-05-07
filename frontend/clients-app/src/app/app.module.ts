import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { DirectiveComponent } from './components/directive/directive.component';
import { ClientsComponent } from './components/clients/clients.component';
import { ClientService } from './components/clients/client.service';
import { RouterModule,Routes } from '@angular/router';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormComponent } from './components/clients/form.component'
import { FormsModule } from '@angular/forms';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { DetailComponent } from './components/clients/detail/detail.component';
import { LoginComponent } from './components/users/login.component';
import { AuthGuard } from './components/users/guards/auth.guard';
import { RoleGuard } from './components/users/guards/role.guard';
import { TokenInterceptor } from './components/users/interceptors/token.interceptor';
import { AuthInterceptor } from './components/users/interceptors/auth.interceptor';

const routes: Routes = [
  {path: '', redirectTo:'clients', pathMatch:'full'},
  {path: 'directives', component:DirectiveComponent},
  {path: 'clients', component:ClientsComponent},
  {path: 'clients/page/:page', component:ClientsComponent},
  {path: 'clients/form', component:FormComponent,canActivate:[AuthGuard,RoleGuard],data:{role: 'ROLE_ADMIN'}},
  {path: 'clients/form/:id', component:FormComponent,canActivate:[AuthGuard, RoleGuard],data:{role: 'ROLE_ADMIN'}},
  {path: 'login', component:LoginComponent},
]


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectiveComponent,
    ClientsComponent,
    FormComponent,
    PaginatorComponent,
    DetailComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  /*Con LOCALE_ID para formatear dentro de los html */
  providers: [
    ClientService,
    {provide: LOCALE_ID,useValue: 'en-US'},
    {provide: HTTP_INTERCEPTORS, useClass:TokenInterceptor, multi:true},
    {provide: HTTP_INTERCEPTORS, useClass:AuthInterceptor, multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
