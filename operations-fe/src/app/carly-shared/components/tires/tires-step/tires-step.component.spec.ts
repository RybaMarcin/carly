import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TiresStepComponent } from './tires-step.component';

describe('TiresStepComponent', () => {
  let component: TiresStepComponent;
  let fixture: ComponentFixture<TiresStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TiresStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TiresStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
