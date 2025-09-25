import { Component } from '@angular/core';
import { Book } from '../../models/book.model';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css'],
})
export class AddBookComponent {
  book: Book = {
    title: '',
    author: '',
    genre: ''
  };
  submitted = false;

  constructor(private bookService: BookService) {}

  saveBook(): void {
    const data = {
      title: this.book.title,
      author: this.book.author,
      genre: this.book.genre
    };

    this.bookService.create(data).subscribe({
      next: (res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => {
        console.error(e)
        alert(e)
      }
    });
  }

  newBook(): void {
    this.submitted = false;
    this.book = {
      title: '',
      author: '',
      genre: ''
    };
  }
}
