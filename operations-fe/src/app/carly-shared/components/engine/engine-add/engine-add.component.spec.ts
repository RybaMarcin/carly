import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EngineAddComponent } from './engine-add.component';

describe('EngineAddComponent', () => {
  let component: EngineAddComponent;
  let fixture: ComponentFixture<EngineAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EngineAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EngineAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
