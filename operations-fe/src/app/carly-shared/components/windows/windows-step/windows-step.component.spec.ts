import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WindowsStepComponent } from './windows-step.component';

describe('WindowsStepComponent', () => {
  let component: WindowsStepComponent;
  let fixture: ComponentFixture<WindowsStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WindowsStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WindowsStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
