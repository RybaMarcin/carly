import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-parts',
  templateUrl: './parts.component.html',
  styleUrls: ['./parts.component.scss',
    '../../../carly-shared/styles/table-card.scss',
    '../../../carly-shared/styles/side-nav.scss']
})
export class PartsComponent implements OnInit {

  navLinks = [
    {path: '/parts/engines', label: 'Engine'},
    {path: '/parts/wheels', label: 'Wheels'},
    {path: '/parts/breaks', label: 'Breaks'},
    {path: '/parts/tires', label: 'Tires'},
    {path: '/parts/windows', label: 'Windows'},
    {path: '/parts/painting', label: 'Painting'},
    {path: '/parts/equipment', label: 'Equipment'},
  ];

  constructor() { }

  ngOnInit() {
  }

}
