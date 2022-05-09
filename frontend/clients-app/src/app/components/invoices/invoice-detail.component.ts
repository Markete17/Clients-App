import { Component, OnInit } from '@angular/core';
import { Invoice } from './models/invoice';
import { InvoiceService } from './services/invoice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html'
})
export class InvoiceDetailComponent implements OnInit {

  constructor(private invoiceService:InvoiceService, private activatedRoute: ActivatedRoute) { }

  invoice: Invoice;
  title: string = 'Invoice';

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params =>{
      let id = +params.get('id');
      this.invoiceService.getInvoice(id).subscribe(invoice => this.invoice = invoice)
    });

  }

}
