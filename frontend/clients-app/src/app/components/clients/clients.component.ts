import { Component, OnInit } from '@angular/core';
import { Client } from './client';
import { ClientService } from './client.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {

  constructor(private clientService: ClientService) { }

  clients: Client[]

  ngOnInit(): void {
    this.clientService.getClients().subscribe(
      clients => this.clients = clients
    )
  }

}
