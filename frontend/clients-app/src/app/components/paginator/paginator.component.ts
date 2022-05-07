import { Component, OnInit, Input, OnChanges,SimpleChange } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html'
})
export class PaginatorComponent implements OnInit {

  constructor() { }

  @Input() paginator:any;
  pages: number[]

  from: number;
  to: number;


  ngOnInit(): void {
    this.initPaginator();
  }

    //Hay que usar el OnChange porque el onInit se hace solo una vez y no se va actualizando la paginacion

    ngOnChanges(changes: SimpleChange){
      let paginatorUpdated = changes['paginator'];
      //Si se detectan cambios en el paginator
      if(paginatorUpdated.previousValue){
        this.initPaginator()
      }
    }

  private initPaginator():void{
    this.from = Math.min(Math.max(1,this.paginator.number-4),this.paginator.totalPages-5);
    this.to = Math.max(Math.min(this.paginator.totalPages,this.paginator.number+4),6);
    
    if(this.paginator.totalPages>5){
      this.pages = new Array(this.to - this.from +1).fill(0).map((_value,index) => index+this.from)
    } else {
      this.pages = new Array(this.paginator.totalPages).fill(0).map((_value,index) => index+1)
    }
  }

}
