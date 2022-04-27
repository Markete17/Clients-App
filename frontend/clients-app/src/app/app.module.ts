import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { DirectiveComponent } from './components/directive/directive.component';
import { ClientsComponent } from './components/clients/clients.component';
import { ClientService } from './components/clients/client.service';
import { RouterModule,Routes } from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo:'clients', pathMatch:'full'},
  {path: 'directives', component:DirectiveComponent},
  {path: 'clients', component:ClientsComponent},
]


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectiveComponent,
    ClientsComponent,
  ],
  imports: [
    BrowserModule,RouterModule.forRoot(routes)
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
