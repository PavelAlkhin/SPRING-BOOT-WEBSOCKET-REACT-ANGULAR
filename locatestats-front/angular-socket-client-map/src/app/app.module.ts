import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WebsocketClientComponent } from './components/websocket-client/websocket-client.component';
import {HttpClientJsonpModule, HttpClientModule} from "@angular/common/http";
import { MyGoogleComponent } from './components/my-google-map/my-google.component';
import {GoogleMapsModule} from "@angular/google-maps";
import {GlobalErrorComponent} from "./components/global-error/global-error.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    WebsocketClientComponent,
    MyGoogleComponent,
    GlobalErrorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    GoogleMapsModule,
    HttpClientJsonpModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
