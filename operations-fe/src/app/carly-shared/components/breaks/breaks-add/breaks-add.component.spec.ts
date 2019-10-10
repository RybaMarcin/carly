import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BreaksAddComponent } from './breaks-add.component';

describe('BreaksAddComponent', () => {
  let component: BreaksAddComponent;
  let fixture: ComponentFixture<BreaksAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BreaksAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BreaksAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
