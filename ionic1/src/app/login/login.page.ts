import { Component, OnInit } from '@angular/core';
import { PostProviderService } from './../services/post-provider.service';
import { ToastController } from '@ionic/angular';
import { Router } from '@angular/router';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  username: string = "";
  password: string = "";
  constructor(
    private postProviderService: PostProviderService,
    private router: Router,
    private toastController: ToastController,
    private storage: Storage
  ) { }

  ngOnInit() {
  }

  formRegister(){
    this.router.navigate(['/register']);
  }

  async prosesLogin(){

    if(this.username==""){
      const toast = await this.toastController.create({
        message: "Campo usuario vacio",
        duration: 2000
      });
      toast.present();
    }else if(this.password==""){
      const toast = await this.toastController.create({
        message: "Campo contraseña vacio",
        duration: 2000
      });
      toast.present();
    }else{
      let body = {
        username: this.username,
        password: this.password,
        aksi: 'login'
      };
      this.postProviderService.postData(body, 'login-api.php').subscribe(async data=>{
        var alertmsg = data.msg;
        if(data.success){
          this.storage.set('session_storage', data.result);
          this.router.navigate(['/lessons']);
          const toast = await this.toastController.create({
            message: "Bienvenido " + data.result['username'],
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

}
