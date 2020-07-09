import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { CreateLessonPage } from './create-lesson.page';

describe('CreateLessonPage', () => {
  let component: CreateLessonPage;
  let fixture: ComponentFixture<CreateLessonPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateLessonPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(CreateLessonPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
