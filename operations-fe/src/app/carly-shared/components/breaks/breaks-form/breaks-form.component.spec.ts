import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BreaksFormComponent } from './breaks-form.component';

describe('BreaksFormComponent', () => {
  let component: BreaksFormComponent;
  let fixture: ComponentFixture<BreaksFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BreaksFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BreaksFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
