import { Component, OnInit, Input } from '@angular/core';
import { ModalController, ToastController } from '@ionic/angular';
import { ApiService} from './../../services/api.service';
import { Storage } from '@ionic/storage';

import { Camera, CameraOptions, PictureSourceType } from '@ionic-native/Camera/ngx';
import { Base64 } from '@ionic-native/base64/ngx';
import { PostProviderService } from '../../services/post-provider.service';

@Component({
  selector: 'app-homework-detail',
  templateUrl: './homework-detail.page.html',
  styleUrls: ['./homework-detail.page.scss'],
})
export class HomeworkDetailPage implements OnInit {

  @Input() homework_id: any;
  student_id: string;
  student_user: string;
  HomeworkSearch = new Array();

  imagenes: string = null;
  constructor(
    private modalController: ModalController,
    private apiService: ApiService,
    private storage: Storage,
    public toastController:ToastController,
    public postProviderService:PostProviderService,
    private camera:Camera,

    ) { }


  ngOnInit() {
    this.storage.get('session_storage').then((value) => {
      this.student_id = value['id'];
      this.student_user = value['username'];
    });

      var val = 'call_homeworks_search.php?homework_id='+this.homework_id;
      this.apiService.getApi(val).subscribe((data)=>{
        console.log(data);
        this.HomeworkSearch = data['homework'];
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

      alert(this.imagenes);
      
      
    })
    .catch(error =>{
      alert( error );
    });
  }
  subir(){
    alert('mostrar:'+this.imagenes);
    let body = {
      file: this.imagenes,
      aksi: 'subimg'
    };
    this.postProviderService.postData(body, 'subfile.php').subscribe(async data=>{
      var alertmsg = data.msg;
      if(data.success){
        const toast = await this.toastController.create({
          message: "Bienvenido " + data.result,
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
