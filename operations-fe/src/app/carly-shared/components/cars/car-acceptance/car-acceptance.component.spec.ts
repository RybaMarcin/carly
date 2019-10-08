import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarAcceptanceComponent } from './car-acceptance.component';

describe('CarAcceptanceComponent', () => {
  let component: CarAcceptanceComponent;
  let fixture: ComponentFixture<CarAcceptanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarAcceptanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarAcceptanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
