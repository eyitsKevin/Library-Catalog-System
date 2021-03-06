import { CatalogItem } from "./catalogItem.model";
import { CatalogItemType } from "../../enums/catalogItemType";

export class searchfilters  {
  book: String;
  magazine: String;
  movie: String;
  music: String;
  title: String;
  search: string;
  author: string;
  format: string;
  publisher: string;
  language: string;
  isbn10: string;
  isbn13: string;
  type: string;
  artist: string;
  label: string;
  asin: string;
  director: string;
  producers: string;
  actors: string;
  subtitles: string;
  dubs: string;
  releaseDate: string;
    constructor(
        param: {
          book: string;
          magazine: string;
          movie: string;
          music: string;
          title: string,
          search: string;
          author: string;
          format: string;
          publisher: string;
          language: string;
          isbn10: string;
          isbn13: string;
          type: string;
          artist: string;
          label: string;
          asin: string;
          director: string;
          producers: string;
          actors: string;
          subtitles: string;
          dubs: string;
          releaseDate: string;
        }
      ) {
        this.book = param.book;
        this.magazine = param.magazine;
        this.movie = param.movie;
        this.music = param.music;
        this.title = param.title;
        this.search = param.search;
        this.author = param.author;
        this.format = param.format;
        this.publisher = param.publisher;
        this.language = param.language;
        this.isbn10 = param.isbn10;
        this.isbn13 = param.isbn13;
        this.type = param.type;
        this.artist = param. artist;
        this.label = param.label;
        this.asin = param.asin;
        this.director = param.director;
        this.producers = param.producers;
        this.actors = param.actors;
        this.subtitles = param.subtitles;
        this.dubs = param.dubs;
        this.releaseDate = param.releaseDate;
      }
}