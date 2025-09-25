import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book.model';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css'],
})
export class BooksListComponent implements OnInit {
  books?: Book[];
  currentBook: Book = {};
  currentIndex = -1;
  title = '';

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.retrieveBooks();
  }

  retrieveBooks(): void {
    this.bookService.getAll().subscribe({
      next: (data) => {
        this.books = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  refreshList(): void {
    this.retrieveBooks();
    this.currentBook = {};
    this.currentIndex = -1;
  }

  setActiveBook(tutorial: Book, index: number): void {
    this.currentBook = tutorial;
    this.currentIndex = index;
  }

  searchTitle(): void {
    this.currentBook = {};
    this.currentIndex = -1;

    this.bookService.findByTitle(this.title).subscribe({
      next: (data) => {
        this.books = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
}
