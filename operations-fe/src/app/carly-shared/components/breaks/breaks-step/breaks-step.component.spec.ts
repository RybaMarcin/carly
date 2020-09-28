import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BreaksStepComponent } from './breaks-step.component';

describe('BreaksStepComponent', () => {
  let component: BreaksStepComponent;
  let fixture: ComponentFixture<BreaksStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BreaksStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BreaksStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
