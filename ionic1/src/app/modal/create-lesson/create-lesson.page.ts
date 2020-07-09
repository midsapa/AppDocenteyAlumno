import { Component, OnInit, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ApiService} from './../../services/api.service';
import { ToastController } from '@ionic/angular';
import { PostProviderService } from './../../services/post-provider.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-lesson',
  templateUrl: './create-lesson.page.html',
  styleUrls: ['./create-lesson.page.scss'],
})
export class CreateLessonPage implements OnInit {

  @Input() student_id_modal: any;
  LessonSearch = new Array();
  lesson_name: string = "";
  constructor(
    public modalController: ModalController,
    public apiService: ApiService,
    public toastController: ToastController,
    public postProviderService: PostProviderService,
    private router: Router

  ) { }

  ngOnInit() {

  }
  
  async close(){
    await this.modalController.dismiss();
  }

  async searchLesson(){
    if(this.lesson_name==""){
      const toast = await this.toastController.create({
        message: "Ingrese el nombre del grupo a buscar!",
        duration: 2000
      });
      toast.present();
    }else{
      var val = 'call_lessons_search.php?lesson_name='+this.lesson_name+'&student_id='+this.student_id_modal;
      this.apiService.getApi(val).subscribe((data)=>{
        console.log(data);
        this.LessonSearch = data['lessons'];
      });
    }
  }

  async registerToLesson(id: BigInteger){

      let body = {
        lesson_id: id,
        user_id: this.student_id_modal,
        aksi: 'registerLesson'
      };
      this.postProviderService.postData(body, 'proses-api.php').subscribe(async data=>{
        var alertmsg = data.msg;
        if(data.success){
          const toast = await this.toastController.create({
            message: "Registro correcto al grupo",
            duration: 2000
          });
          toast.present();
        }else{
          const toast = await this.toastController.create({
            message: alertmsg,
            duration: 2000
          });
          toast.present();
        }
      });
    }
    

}
