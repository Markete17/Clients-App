import { Injectable } from '@angular/core';
import { Client } from './client';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map,catchError,Observable,throwError,tap} from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { formatDate,DatePipe,registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es'
import { HttpRequest,HttpEvent } from '@angular/common/http';

@Injectable()
export class ClientService {
  private urlEndPoint:string = 'http://localhost:8080/api/clients'
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  })
  
  constructor(private http:HttpClient,private router:Router) { }

  getClients(page:number): Observable<any>{
    return this.http.get(this.urlEndPoint + '/page/' + page).pipe(
      tap( (response:any) =>{
        (response.content as Client[]).forEach(c => {console.log(c)})
      }),
      map( (response:any) => {
        (response.content as Client[]).map(c =>
        {
          c.firstName = c.firstName.toUpperCase();
          c.lastName = c.lastName.toUpperCase();
          // Para darle formato a la fecha se puede usar formatDate o DatePipe
            // c.createAt = formatDate(c.createAt,'dd/MM/YYY','en-US')
           // c.createAt = new DatePipe('en-US').transform(c.createAt,'dd/MM/yyy')
          // Para dar formato a los días meses etc
           c.createAt = new DatePipe('en-US').transform(c.createAt,'EEEE d, MMMM yyyy')
           //c.createAt = new DatePipe('en-US').transform(c.createAt,'fullDate')
           //Para formatearlo en paises se usa la libreria common/es por ejemplo para España
            // el registerLocaleData se podría meter en el app.module para hacerlo global y crear el LOCALE_ID (ver app module)
            // registerLocaleData(localeES,'es')
            //c.createAt = new DatePipe('es').transform(c.createAt,'EEEE dd, MMMM yyy')
          return c;
        });
        return response
      }), /* Con el tap podemos hacer otra tarea a la vez que el map */
      tap(response => {
        (response.content as Client[]).forEach(
          c => {
            console.log(c)
          }
        )
      }),
    )
  }

  // En vez de con el Pipe se puede poner <Client> despues del http operator
  getClient(id):Observable<Client>{
    return this.http.get<Client>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
      this.router.navigate(['/clients'])
      Swal.fire('Error while editing ',e.error.message,'error');
      const err = new Error();
      console.log(err)
      return throwError(() => err);
    }))
  }

  /*La peticion devuelve {client y message por eso es any} */
  create(client:Client):Observable<any>{
    return this.http.post<any>(this.urlEndPoint,client,{headers:this.httpHeaders}).pipe(
      catchError(e => {
        if(e.status === 400){
          return throwError(() => e)
        }
        this.router.navigate(['/clients'])
        Swal.fire('Error while creating ',e.error.message,'error');
        return throwError(() => e);
      })
    )
  }
  /*Se puede hacer de otra forma para devolver Observable<Client> */
  update(client:Client):Observable<Client>{
    return this.http.put<any>(`${this.urlEndPoint}/${client.id}`,client,{headers:this.httpHeaders}).pipe(
      map((response:any) => response.client as Client),
      catchError(e => {
        if(e.status === 400){
          return throwError(() => e)
        }
        this.router.navigate(['/clients'])
        Swal.fire('Error while editing ',e.error.message,'error');
        const err = new Error();
        return throwError(() => err);
      })
    )
  }
  /*Tambien se puede hacer con pipe */
  delete(id):Observable<Client>{
    return this.http.delete(`${this.urlEndPoint}/${id}`,{headers:this.httpHeaders}).pipe(
      map(response => response as Client)
    ).pipe(
      catchError(e => {
        this.router.navigate(['/clients'])
        Swal.fire('Error while deleting ',e.error.message,'error');
        const err = new Error();
        return throwError(() => err);
      })
    )
  }

  uploadPhoto(file,id):Observable<HttpEvent<{}>>{
    let formData = new FormData();
    formData.append("file",file);
    formData.append("id",id);
    

    // Para la barra de progreso se tiene que hacer con el httpRequest
    const req = new HttpRequest('POST',`${this.urlEndPoint}/upload`,formData,{reportProgress:true});

    return this.http.request(req);
  }
}
