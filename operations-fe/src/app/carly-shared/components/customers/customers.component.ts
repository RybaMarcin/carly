import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class CustomersComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
