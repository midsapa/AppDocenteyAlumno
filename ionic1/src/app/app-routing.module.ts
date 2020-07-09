import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./register/register.module').then( m => m.RegisterPageModule)
  },
  {
    path: 'lessons',
    loadChildren: () => import('./lessons/lessons.module').then( m => m.LessonsPageModule)
  },
  {
    path: 'create-lesson',
    loadChildren: () => import('./modal/create-lesson/create-lesson.module').then( m => m.CreateLessonPageModule)
  },
  {
    path: 'topics',
    loadChildren: () => import('./topics/topics.module').then( m => m.TopicsPageModule)
  },
  {
    path: 'homeworks',
    loadChildren: () => import('./homeworks/homeworks.module').then( m => m.HomeworksPageModule)
  },
  {
    path: 'homework-detail',
    loadChildren: () => import('./modal/homework-detail/homework-detail.module').then( m => m.HomeworkDetailPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
