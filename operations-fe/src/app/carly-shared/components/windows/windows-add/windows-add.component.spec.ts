import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WindowsAddComponent } from './windows-add.component';

describe('WindowsAddComponent', () => {
  let component: WindowsAddComponent;
  let fixture: ComponentFixture<WindowsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WindowsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WindowsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
