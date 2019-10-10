import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BreaksEditComponent } from './breaks-edit.component';

describe('BreaksEditComponent', () => {
  let component: BreaksEditComponent;
  let fixture: ComponentFixture<BreaksEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BreaksEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BreaksEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
