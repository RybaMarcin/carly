import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WheelsFormComponent } from './wheels-form.component';

describe('WheelsFormComponent', () => {
  let component: WheelsFormComponent;
  let fixture: ComponentFixture<WheelsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WheelsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WheelsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
