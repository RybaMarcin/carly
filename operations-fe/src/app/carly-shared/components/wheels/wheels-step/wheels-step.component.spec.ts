import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WheelsStepComponent } from './wheels-step.component';

describe('WheelsStepComponent', () => {
  let component: WheelsStepComponent;
  let fixture: ComponentFixture<WheelsStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WheelsStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WheelsStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
