import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EngineEditComponent } from './engine-edit.component';

describe('EngineEditComponent', () => {
  let component: EngineEditComponent;
  let fixture: ComponentFixture<EngineEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EngineEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EngineEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
