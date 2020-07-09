import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public getApi(val: string){
    return this.httpClient.get(`http://farmaciafarmax.net/clases/services/${val}`);
  }
}
