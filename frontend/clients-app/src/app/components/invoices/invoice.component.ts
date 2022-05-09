import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../clients/client.service';
import { Invoice } from './models/invoice';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith,mergeMap} from 'rxjs/operators';
import { InvoiceService } from './services/invoice.service';
import { Product } from './models/product';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { InvoiceItem } from './models/invoice-item';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html'
})
export class InvoiceComponent implements OnInit {

  constructor(private clientService:ClientService,
   private activatedRoute:ActivatedRoute,
   private invoiceService:InvoiceService,
   private router:Router) { }

  title:string = 'New Invoice';
  invoice:Invoice = new Invoice();

  autocompleteControl = new FormControl();
  products: string[] = [];
  filteredProducts: Observable<Product[]>;

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let clientId = +params.get('clientId');
      this.clientService.getClient(clientId).subscribe(client => this.invoice.client = client)
      this.filteredProducts = this.autocompleteControl.valueChanges
      .pipe(
        map(value => typeof value === 'string' ? value : value.name),
        mergeMap(value => value ? this._filter(value) : []),
      );
    });
  }

  private _filter(value: string): Observable<Product[]> {
    const filterValue = value.toLowerCase();
    return this.invoiceService.filterProducts(filterValue)
  }

  showName(product?:Product):string | undefined{
    return product? product.name : undefined
  }

  selectProduct(event:MatAutocompleteSelectedEvent){
    let product = event.option.value as Product;
    if(this.existItem(product.id)){
      this.incrementAmount(product.id)
    } else {

    let newItem = new InvoiceItem;
    newItem.product = product;
    this.invoice.items.push(newItem);
    this.autocompleteControl.setValue('');
    event.option.focus();
    event.option.deselect();
    }
    this.autocompleteControl.setValue('');
    event.option.focus();
    event.option.deselect();
  }

  updateAmount(id:number,event:any):void{
    let amount:number = event.target.value as number;
    if(amount==0){
      return this.deleteInvoiceItem(id);
    }
    this.invoice.items = this.invoice.items.map( (item:InvoiceItem) => {
      if(id === item.product.id){
        item.amount = amount;
      }
      return item;
    });
  }

  existItem(id:number):boolean{
    let exist = false;this.invoice.items.forEach((item:InvoiceItem) => {
      if(id === item.product.id){
        exist = true;
      }
    })
    return exist;
  }

  incrementAmount(id:number):void{
    this.invoice.items = this.invoice.items.map( (item:InvoiceItem) => {
      if(id === item.product.id){
        ++item.amount;
      }
      return item;
    });
  }

  deleteInvoiceItem(id:number):void{
    this.invoice.items = this.invoice.items.filter( (item:InvoiceItem) => item.product.id != id)
  }

  create():void{
    this.invoiceService.create(this.invoice).subscribe(invoice => {
      Swal.fire(this.title, `Invoice ${invoice.description} has been created successfully!`,'success')
      this.router.navigate(['/invoices',invoice.id])
    })
  }

  calculateTotal():number{
    let total = 0;
    this.invoice.items.forEach((item:InvoiceItem) =>{
      total += item.amount*item.product.price;
    })
    return total;
  }
}
