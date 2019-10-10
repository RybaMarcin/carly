import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartsDashboardComponent } from './parts-dashboard.component';

describe('PartsDashboardComponent', () => {
  let component: PartsDashboardComponent;
  let fixture: ComponentFixture<PartsDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartsDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartsDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
