import {CatalogItem} from "./catalogItem.model";
import {CatalogItemType} from "../../enums/catalogItemType";

export class Book extends CatalogItem {

  constructor(itemType: CatalogItemType, id: number, qtyInStock: number, qtyOnLoan: number, titles: string,
              param: {author: string; format: string; pages: number; publisher: string; yearOfPublication: number; language: string; isbn10: string; isbn13: string }) {
    super(itemType, id, qtyInStock, qtyOnLoan, titles);
    this.author = param.author;
    this.format = param.format;
    this.pages = param.pages;
    this. publisher = param.publisher;
    this.yearOfPublication = param.yearOfPublication;
    this.language = param.language;
    this.isbn10 = param.isbn10;
    this.isbn13 = param.isbn13
  }

  author: string;
  format: string;
  pages: number;
  publisher: string;
  yearOfPublication: number;
  language: string;
  isbn10: string;
  isbn13: string;

  public toString = (): string => {
    return this.itemType;
  }
}
