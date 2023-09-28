import { Injectable } from '@angular/core';
import {Marker} from "../../my-google-map/marker-properties";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MarkerServiceService {


  public markerContainer$ = new Subject<Marker>();

  public changeMarker(marker: Marker){
    this.markerContainer$.next(marker)
  }

  constructor() { }
}
