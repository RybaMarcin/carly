import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WheelsAddComponent } from './wheels-add.component';

describe('WheelsAddComponent', () => {
  let component: WheelsAddComponent;
  let fixture: ComponentFixture<WheelsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WheelsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WheelsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
