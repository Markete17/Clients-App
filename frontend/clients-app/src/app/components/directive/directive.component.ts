import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directive',
  templateUrl: './directive.component.html'
})
export class DirectiveComponent {

  constructor() {
    this.desactive = false
  }

  courseList: string[] = ['TypeScript','JavaScript','Java','C#','PHP']
  desactive:boolean

  hideShow(){
    this.desactive = (this.desactive) ? false : true
  }
}
