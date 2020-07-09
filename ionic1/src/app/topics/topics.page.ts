import { Component, OnInit } from '@angular/core';
import { ApiService} from './../services/api.service';
import { Storage } from '@ionic/storage';

import { ModalController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.page.html',
  styleUrls: ['./topics.page.scss'],
})
export class TopicsPage implements OnInit {

  lesson_id: string;
  student_id: string;
  student_user: string;
  Topics = new Array();
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
    });
    this.storage.get('session_lesson').then((value) => {
      this.lesson_id = value;
      console.log(this.lesson_id);

      var val = 'call_topics_of_lesson.php?lesson_id='+this.lesson_id;
      this.apiService.getApi(val).subscribe((data)=>{
        console.log(data);
        this.Topics = data['topics'];
      });
    });
  }

  returnLessons(){
    this.router.navigate(['/lessons']);
  }
  seeHomeworks(topic_id: BigInteger){
    this.storage.set('session_topic', topic_id);
    this.router.navigate(['/homeworks']);
  }
}
