import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EngineStepComponent } from './engine-step.component';

describe('EngineStepComponent', () => {
  let component: EngineStepComponent;
  let fixture: ComponentFixture<EngineStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EngineStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EngineStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
