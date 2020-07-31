import { Component, OnInit, Input } from '@angular/core';
import { ModalController, ToastController } from '@ionic/angular';
import { ApiService} from './../../services/api.service';
import { Storage } from '@ionic/storage';

import { Camera, CameraOptions, PictureSourceType } from '@ionic-native/Camera/ngx';
import { Base64 } from '@ionic-native/base64/ngx';
import { PostProviderService } from '../../services/post-provider.service';
import { empty } from 'rxjs';


@Component({
  selector: 'app-homework-detail',
  templateUrl: './homework-detail.page.html',
  styleUrls: ['./homework-detail.page.scss'],
})
export class HomeworkDetailPage implements OnInit {

  @Input() homework_id: any;
  sendhomework_id: string;
  student_id: string;
  student_user: string;
  description: string;
  sh_id: string;
  points_v: string;
  HomeworkSearch = new Array();
  Points = new Array();

  imagenes: string = null;
  constructor(
    private modalController: ModalController,
    private apiService: ApiService,
    private storage: Storage,
    public base64:Base64,
    public toastController:ToastController,
    public postProviderService:PostProviderService,
    private camera:Camera,

    ) { }


  ngOnInit() {
    this.storage.get('session_storage').then((value) => {
      this.student_id = value['id'];
      this.student_user = value['username'];

       var val2 = 'points.php?homework_id='+this.homework_id+'&student_id='+this.student_id;
      this.apiService.getApi(val2).subscribe((data)=>{
        console.log(data);
        this.Points = data['queries'];
    
        if(data['queries'] == null){
          this.points_v = 'vacio';
        }else{
          this.points_v = 'si_envio';

          console.log(data['queries']['id_sh']);
          this.sh_id = data['queries']['id_sh'];
       }

        console.log(this.points_v);

      });
      var val = 'call_homeworks_search.php?homework_id='+this.homework_id;
      this.apiService.getApi(val).subscribe((data)=>{
        console.log(data);
        this.HomeworkSearch = data['homework'];
      });
    });

      
     
  }

 
  async close(){
    await this.modalController.dismiss();
  }
  getPicture(){
    let options: CameraOptions = {
      destinationType: this.camera.DestinationType.DATA_URL,
      targetWidth: 1000,
      targetHeight: 1000,
      quality: 100,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      sourceType: this.camera.PictureSourceType.PHOTOLIBRARY //si lo quitas entra a la camara
    }
    this.camera.getPicture( options )
    .then(imageData => {
      //this.imagenes = `data:image/jpeg;base64,${imageData}`;
      this.imagenes = `${imageData}`;

      alert("Imagen capturada");
      
      
    })
    .catch(error =>{
      alert( error );
    });
  }
  subir(){
    console.log(this.student_id);
    
    let body = {
      user_id: this.student_id,
      homework_id: this.homework_id,
      file: this.imagenes,
      aksi: 'subimg'
    };
    this.postProviderService.postData(body, 'subfile.php').subscribe(async data=>{
      var alertmsg = data.msg;
      if(data.success){
        const toast = await this.toastController.create({
          message: "archivo enviado",
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

  ec(){
    alert(this.sh_id);
    let body = {
      description: this.description,
      sendhomework_id: this.sh_id,
      aksi: 'comentarioNota'
    };
    this.postProviderService.postData(body, 'comments.php').subscribe(async data=>{
      var alertmsg = data.msg;
      if(data.success){
        const toast = await this.toastController.create({
          message: "Comentario enviado",
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
