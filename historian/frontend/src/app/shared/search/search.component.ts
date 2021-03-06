import { Component, OnInit, Output, EventEmitter, Input, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {

  @Input() placeHolder: string;
  @Output() queryString = new EventEmitter<string>();
  private searchTerms = new Subject<string>();

  constructor() {

  }

  ngOnInit() {
    this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(400),
      // ignore new term if same as previous term
      distinctUntilChanged(),
       // switch to new search observable each time the term changes
       switchMap(
        (term: string) => {
          this.queryString.emit(term);
          return 'nonempty'; // error if empty string or void
        }
      ),
    ).subscribe();
  }

  ngOnDestroy() {
    this.searchTerms.unsubscribe();
  }

  update(term: string): void {
    this.searchTerms.next(term);
  }

  onEnter(term: string): void {
    this.queryString.emit(term);
  }
}
