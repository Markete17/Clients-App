import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { DirectiveComponent } from './components/directive/directive.component';
import { ClientsComponent } from './components/clients/clients.component';
import { ClientService } from './components/clients/client.service';
import { RouterModule,Routes } from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import { FormComponent } from './components/clients/form.component'
import { FormsModule } from '@angular/forms';

const routes: Routes = [
  {path: '', redirectTo:'clients', pathMatch:'full'},
  {path: 'directives', component:DirectiveComponent},
  {path: 'clients', component:ClientsComponent},
  {path: 'clients/form', component:FormComponent},
  {path: 'clients/form/:id', component:FormComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectiveComponent,
    ClientsComponent,
    FormComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  /*Con LOCALE_ID para formatear dentro de los html */
  providers: [ClientService,{provide: LOCALE_ID,useValue: 'en-US'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
