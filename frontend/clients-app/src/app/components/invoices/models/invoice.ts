import { Client } from "../../clients/client";
import { InvoiceItem } from "./invoice-item";

export class Invoice {
    id: number;
    description: string;
    observation: string;
    items: InvoiceItem[] = []
    client: Client;
    total: number;
    createAt: string;

    calculateTotalInvoice():number{
        this.items.forEach((item:InvoiceItem) =>{
            this.total = 0;
            this.total = this.total + item.product.price*item.amount;
        })
        return this.total;
    }
}
