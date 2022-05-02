import { Injectable,EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor() { }

  modal:boolean = false;

  // Para notificar al client component que tiene que actualizar la imagen del cliente
  // Para diferenciar la propiedad del get, se pone guion bajo delante
  private _notifyUpload = new EventEmitter<any>();

  openModal(){
    this.modal = true;
  }

  get notifyUpload(): EventEmitter<any>{
    return this._notifyUpload;
  }

  closeModal(){
    this.modal = false;
  }
}
