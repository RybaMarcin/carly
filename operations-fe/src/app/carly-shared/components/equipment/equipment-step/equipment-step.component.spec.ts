import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentStepComponent } from './equipment-step.component';

describe('EquipmentStepComponent', () => {
  let component: EquipmentStepComponent;
  let fixture: ComponentFixture<EquipmentStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EquipmentStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EquipmentStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
