import { Component, OnInit } from '@angular/core';
import { Client } from '../client';
import { ClientService } from '../client.service';
import Swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import { Input } from '@angular/core';
import { ModalService } from './modal.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  constructor(private clientService:ClientService,
    public modalService: ModalService) { }

  @Input() client:Client;
  title:string = "Client Detail"
  selectedPhoto: File;
  preview : string|ArrayBuffer;
  progressBar:number = 0;

  ngOnInit(): void {
    /* Con el modal ya no hace falta cargar el cliente
    
    this.activatedroute.paramMap.subscribe(params =>{
      let id = +params.get('id')
      if(id){
        this.clientService.getClient(id).subscribe(c => {
          this.client = c;
        })
      }
    })*/
  }

  selectPhoto(event){

    this.selectedPhoto = event.target.files[0];
    this.progressBar = 0;
    
    const fileReader = new FileReader();
    
    fileReader.onload = e => this.preview = fileReader.result;

    fileReader.readAsDataURL(this.selectedPhoto);

    if(this.selectedPhoto.type.indexOf('image')<0){
      Swal.fire("Error select image","Invalid format",'error')
    }
  }

  upload(){
    if(!this.selectedPhoto){
      Swal.fire("Error upload","No photo founded",'error')
    } else {
      this.clientService.uploadPhoto(this.selectedPhoto,this.client.id)
      .subscribe(event => {
        if(event.type === HttpEventType.UploadProgress){
          this.progressBar = Math.round((event.loaded/event.total)*100);
          console.log(this.progressBar)
        } else if (event.type === HttpEventType.Response){
          let response: any = event.body;
          this.client = response.client as Client;

          //Se emite el cliente con la foto actualizada para que se cambie en el clients components
          this.modalService.notifyUpload.emit(this.client);
          Swal.fire("The photo has been uploaded successfully","Image: "+response.message,'success')
        }
        
      })
      this.closeModal();
      this.progressBar=null;
    }
  }

  closeModal(){
    this.modalService.closeModal();
    this.selectedPhoto = null;
    this.progressBar = null;
    this.preview = null;
  }

}
