import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalTransactionComponent } from './personal-transaction.component';

describe('PersonalTransactionComponent', () => {
  let component: PersonalTransactionComponent;
  let fixture: ComponentFixture<PersonalTransactionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalTransactionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
