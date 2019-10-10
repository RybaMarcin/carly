import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartsAddComponent } from './parts-add.component';

describe('PartsAddComponent', () => {
  let component: PartsAddComponent;
  let fixture: ComponentFixture<PartsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
