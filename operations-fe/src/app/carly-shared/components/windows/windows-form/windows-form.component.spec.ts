import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WindowsFormComponent } from './windows-form.component';

describe('WindowsFormComponent', () => {
  let component: WindowsFormComponent;
  let fixture: ComponentFixture<WindowsFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WindowsFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WindowsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
