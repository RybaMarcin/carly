import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartStepComponent } from './part-step.component';

describe('PartStepComponent', () => {
  let component: PartStepComponent;
  let fixture: ComponentFixture<PartStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
