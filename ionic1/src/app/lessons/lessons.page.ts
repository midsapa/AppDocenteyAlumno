import { Component, OnInit } from '@angular/core';
import { ApiService} from './../services/api.service';
import { Storage } from '@ionic/storage';

import { ModalController } from '@ionic/angular';
import { CreateLessonPage } from './../modal/create-lesson/create-lesson.page';
import { Router } from '@angular/router';

import Swal from 'sweetalert2';


@Component({
  selector: 'app-lessons',
  templateUrl: './lessons.page.html',
  styleUrls: ['./lessons.page.scss'],
})
export class LessonsPage implements OnInit {

  Lessons = new Array();
  student_id: string;
  student_user: string;
  tituloAlerta: string = "";

  constructor(
    private apiService: ApiService,
    private storage: Storage,
    public modalController: ModalController,
    public router: Router
  ) { }

  ngOnInit() {
 
    this.storage.get('session_storage').then((value) => {
      this.student_id = value['id'];
      this.student_user = value['username'];

      var val = 'call_lessons.php?student_id='+this.student_id;
      this.apiService.getApi(val).subscribe((data)=>{
        console.log(data);
        this.Lessons = data['lessons'];
      });
    });
  }

  async presentModal() {
    const modal = await this.modalController.create({
      component: CreateLessonPage,
      componentProps: {
        student_id_modal: this.student_id
      }
    });
    await modal.present();
    modal.onDidDismiss()
    .then(res => Swal.fire('Esta saliendo del buscador de grupos, gracias!', this.tituloAlerta, 'success'));
 
  }

  seeTopics(lesson_id: BigInteger){
    this.storage.set('session_lesson', lesson_id);
    this.router.navigate(['/topics']);
  }

}
