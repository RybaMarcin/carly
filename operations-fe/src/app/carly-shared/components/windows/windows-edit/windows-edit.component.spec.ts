import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WindowsEditComponent } from './windows-edit.component';

describe('WindowsEditComponent', () => {
  let component: WindowsEditComponent;
  let fixture: ComponentFixture<WindowsEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WindowsEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WindowsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
