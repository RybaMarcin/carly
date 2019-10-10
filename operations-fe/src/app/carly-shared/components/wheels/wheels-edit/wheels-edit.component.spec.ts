import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WheelsEditComponent } from './wheels-edit.component';

describe('WheelsEditComponent', () => {
  let component: WheelsEditComponent;
  let fixture: ComponentFixture<WheelsEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WheelsEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WheelsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
