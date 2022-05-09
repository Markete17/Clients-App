import { Component, OnInit } from '@angular/core';
import { Client } from './client';
import { ClientService } from './client.service';
import { Router,ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { RegionService } from '../regions/region.service';
import { Region } from '../regions/region';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  constructor(private clientService: ClientService,private route:Router,private activatedRoute:ActivatedRoute, private regionService:RegionService) { }
  title:string = 'Create Client';
  public client:Client=new Client();
  regions:Region[];

  errors:string[];

  ngOnInit(): void {
    this.loadClient()
    this.loadRegions();
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

  loadRegions():void{
    this.regionService.getRegions().subscribe(
      regions => this.regions = regions
    );
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
    this.client.invoices = null; //Para solucionar el error
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

  compareRegion(r1:Region,r2:Region):boolean{
    //El primer r1 es la region de la iteracion y el r2 es la region que viene del edit
    if(r1 === undefined && r2 === undefined){
      return true;
    }
    return r1 === null || r2 === null || r1 === undefined || r2 === undefined ? false : r1.id===r2.id;
  }

}
