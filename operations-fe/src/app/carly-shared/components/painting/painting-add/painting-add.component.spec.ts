import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaintingAddComponent } from './painting-add.component';

describe('PaintingAddComponent', () => {
  let component: PaintingAddComponent;
  let fixture: ComponentFixture<PaintingAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaintingAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaintingAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
