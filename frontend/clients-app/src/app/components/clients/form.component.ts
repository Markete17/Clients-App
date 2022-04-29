import { Component, OnInit } from '@angular/core';
import { Client } from './client';
import { ClientService } from './client.service';
import { Router,ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  constructor(private clientService: ClientService,private route:Router,private activatedRoute:ActivatedRoute) { }
  title:string = 'Create Client';
  client:Client=new Client();

  errors:string[];

  ngOnInit(): void {
    this.loadClient()
  }

  loadClient():void{
    this.activatedRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.clientService.getClient(id).subscribe(
          client => this.client = client
        )
      } 
    })
  }

  create(){
    this.clientService.create(this.client)
    .subscribe( {
      next: response => 
      {
        Swal.fire({
          title: 'Do you want to submit?',
          showDenyButton: true,
          showCancelButton: true,
          confirmButtonText: 'Save',
          denyButtonText: `Don't save`,
        }).then((result) => {
          if (result.isConfirmed) {
            Swal.fire('New Client',`${response.message} - ${response.client.firstName}`,'success').then((result) =>{
              if (result.isConfirmed) {
                this.route.navigate(['/clients']);
              }
            })
            
          } else if (result.isDenied) {
            Swal.fire('Changes are not saved!', '', 'info')
          }
        })
      },
      error: (err) => this.errors = err.error.errors as string [],
      complete: () => console.log(this.errors),
      }
        );
  }

  update(){
    this.clientService.update(this.client)
    .subscribe( {
      next: client => {
      Swal.fire({
        title: 'Do you want to submit?',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Update',
        denyButtonText: `Don't update`,
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire('Client updated',`The client: ${client.firstName} has updated successfully`,'success').then((result) =>{
            if (result.isConfirmed) {
              this.route.navigate(['/clients']);
            }
          })
        } else if (result.isDenied) {
          Swal.fire('Changes are not updated', '', 'info')
        }
      })
    },
    error: (err) => this.errors = err.error.errors as string [],
    complete: () => console.log(this.errors),
    }
      );
  }

}
