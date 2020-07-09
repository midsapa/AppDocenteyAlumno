import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService} from './../services/api.service';
import { Storage } from '@ionic/storage';
import { ModalController, ToastController } from '@ionic/angular';
import { HomeworkDetailPage } from './../modal/homework-detail/homework-detail.page';

@Component({
  selector: 'app-homeworks',
  templateUrl: './homeworks.page.html',
  styleUrls: ['./homeworks.page.scss'],
})
export class HomeworksPage implements OnInit {

  topic_id: string;
  student_id: string;
  student_user: string;
  Homeworks = new Array();

  
  constructor(
    private apiService: ApiService,
    private storage: Storage,
    public modalController: ModalController,
   
    public router: Router,
   
    
  ) { }

  ngOnInit() {
    this.storage.get('session_storage').then((value) => {
      this.student_id = value['id'];
      this.student_user = value['username'];
    });
    this.storage.get('session_topic').then((value) => {
      this.topic_id = value;
      console.log(this.topic_id);

      var val = 'call_homeworks_of_topic.php?topic_id='+this.topic_id;
      this.apiService.getApi(val).subscribe((data)=>{
        console.log(data);
        this.Homeworks = data['homeworks'];
      });
    });
  }
  returnTopics(){
    this.router.navigate(['/topics']);
  }

  async modalHomeworkDetail(homework_id: BigInteger) {
    const modal = await this.modalController.create({
      component: HomeworkDetailPage,
      componentProps: {
        homework_id: homework_id
      }
    });
    return modal.present();
    
  }
  
}
