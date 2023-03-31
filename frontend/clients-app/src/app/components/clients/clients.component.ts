import { Component, OnInit } from '@angular/core';
import { Client } from './client';
import { ClientService } from './client.service';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';
import { ModalService } from './detail/modal.service';
import { LoginService } from '../users/login.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {

  constructor(private clientService: ClientService,
    private activatedRoute:ActivatedRoute,
    public modalService:ModalService,
    public loginService: LoginService) { }

  clients: Client[]
  paginator:any;
  url:string='/clients/page'
  selectedClient:Client;

  ngOnInit(): void {
    //On init solo se ejecuta una vez. Por lo tanto para detectar el cambio de pagina
    //se necesita un observable: paramMap
    this.activatedRoute.paramMap.subscribe( params =>{
      //con el +delante transforma string a number
      let page:number = +params.get('page')
      if(!page){
        page = 0
      }
      this.clientService.getClients(page).subscribe(
      response => {
        this.clients = response.content as Client[];
        this.paginator = response;
      }
    )
    })

    this.modalService.notifyUpload.subscribe(
      client => {
        this.clients = this.clients.map(c => {
          if(c.id === client.id){
            c.photo = client.photo;
          }
          return c;
        })
      }
    )
  }
  delete(client:Client):void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: 'Are you sure?',
      text: `Are you sure you want to delete the client ${client.firstName} ${client.lastName} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.clientService.delete(client.id).subscribe(
          response => {
            this.clients = this.clients.filter(c=> c!== client)
            if(response instanceof Client){
              swalWithBootstrapButtons.fire(
                'Deleted!',
                'Your file has been deleted.',
                'success'
              )
            }
          }
          
        )

      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire(
          'Cancelled',
          'Your imaginary file is safe :)',
          'error'
        )
      }
    })
  }
  
  openModal(client:Client){
    this.selectedClient = client;
    this.modalService.openModal();
  }

}
