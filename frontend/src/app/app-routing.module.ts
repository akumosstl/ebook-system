import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooksListComponent } from './components/books-list/books-list.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { AuthGuard } from './guard/auth.guard';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'books', component: BooksListComponent, canActivate: [AuthGuard] },
  { path: 'books/:id', component: BookDetailsComponent, canActivate: [AuthGuard] },
  { path: 'add', component: AddBookComponent,canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppRoutingModule { }
