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
  public client:Client=new Client();

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
    .subscribe( client => {
        Swal.fire({
          title: 'Do you want to submit?',
          showDenyButton: true,
          showCancelButton: true,
          confirmButtonText: 'Save',
          denyButtonText: `Don't save`,
        }).then((result) => {
          if (result.isConfirmed) {
            Swal.fire('New Client',`Client ${client.firstName} has created successfully!`,'success').then((result) =>{
              if (result.isConfirmed) {
                this.route.navigate(['/clients']);
              }
            })
            
          } else if (result.isDenied) {
            Swal.fire('Changes are not saved!', '', 'info')
          }
        })
        
        })
  }

  update(){
    this.clientService.update(this.client)
    .subscribe( client => {
      Swal.fire({
        title: 'Do you want to submit?',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'Update',
        denyButtonText: `Don't update`,
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire('Client updated',`Client ${client.firstName} has updated successfully!`,'success').then((result) =>{
            if (result.isConfirmed) {
              this.route.navigate(['/clients']);
            }
          })
        } else if (result.isDenied) {
          Swal.fire('Changes are not updated', '', 'info')
        }
      }).then((result)=>{

      })
    })
  }

}
