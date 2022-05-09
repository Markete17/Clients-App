import { Product } from "./product";

export class InvoiceItem {
    product: Product;
    amount: number = 1;
    import: number;

    public calculateImport():number{
        return this.amount*this.product.price;
    }
}
