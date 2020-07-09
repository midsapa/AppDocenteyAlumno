import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PostProviderService } from './../services/post-provider.service';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  username: string="";
  fullname: string="";
  email: string="";
  cellphone: string="";
  password: string="";
  confirm_password: string="";
  constructor(
    private router: Router,
    private postProviderService: PostProviderService,
    private toastController: ToastController,
  ) { }

  ngOnInit() {
  }

  formLogin(){
    this.router.navigate(['/login']);
  }

  async prosesRegister(){

    if(this.username==""){
      const toast = await this.toastController.create({
        message: "Campo usuario vacio",
        duration: 2000
      });
      toast.present();
    }else if(this.fullname==""){
      const toast = await this.toastController.create({
        message: "Campo nombre completo vacio",
        duration: 2000
      });
      toast.present();
    }else if(this.email==""){
      const toast = await this.toastController.create({
        message: "Campo correo vacio",
        duration: 2000
      });
      toast.present();
    }else if(this.cellphone==""){
      const toast = await this.toastController.create({
        message: "Campo celular vacio",
        duration: 2000
      });
      toast.present();
    }else if(this.password==""){
      const toast = await this.toastController.create({
        message: "Campo contraseña vacio",
        duration: 2000
      });
      toast.present();
    }else if(this.password != this.confirm_password){
      const toast = await this.toastController.create({
        message: "Las contraseñas no coinciden",
        duration: 2000
      });
      toast.present();
    }else{
      let body = {
        username: this.username,
        fullname: this.fullname,
        email: this.email,
        cellphone: this.cellphone,
        password: this.password,
        aksi: 'register'
      };
      this.postProviderService.postData(body, 'proses-api.php').subscribe(async data=>{
        var alertmsg = data.msg;
        if(data.success){
          this.router.navigate(['/login']);
          const toast = await this.toastController.create({
            message: "Registro correcto",
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
