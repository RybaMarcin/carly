import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'painting',
  templateUrl: './painting.component.html',
  styleUrls: ['./painting.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss',
    '../../../carly-shared/styles/buttons.scss']
})
export class PaintingComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
