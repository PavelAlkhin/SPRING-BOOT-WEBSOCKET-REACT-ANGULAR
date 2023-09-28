import {Component, Input, OnInit} from '@angular/core';
import {Observable, of, share} from "rxjs";
import {catchError, map, tap} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Marker} from "./marker-properties";
import {MarkerServiceService} from "../websocket-client/service/marker-service.service";

const GOOGLE_MAP_KEY = "AIzaSyBAyMH-A99yD5fHQPz7uzqk8glNJYGEqus"

@Component({
    selector: 'app-my-google',
    templateUrl: './my-google.component.html',
    styleUrls: ['./my-google.component.css']
})
export class MyGoogleComponent implements OnInit {

    loaded = false;

    mapOptions: google.maps.MapOptions = {
        center: {lat: 48.8588548, lng: 2.2945},
        zoom: 10,
    };

    marker:Marker = { lat: 48.8588548, lng: 2.2945}

    @Input() options!: google.maps.MapOptions;

    @Input() markerOptions: google.maps.MarkerOptions = {draggable: false};

    apiLoaded$!: Observable<boolean>;

    constructor(private httpClient: HttpClient, public markerServiceService: MarkerServiceService) {

    }

    ngOnInit() {
        console.log("started")
        this.apiLoaded$ = this.load();
        this.markerServiceService.markerContainer$.subscribe((m) => {
            this.marker = m
            console.log("fromsubscribe+lng" + m.lng)
        })
    }

    load(): Observable<boolean> {
        return !this.loaded
            ? this.httpClient
                .jsonp(`https://maps.googleapis.com/maps/api/js?key=${GOOGLE_MAP_KEY}`, 'callback')
                .pipe(
                    tap(() => (this.loaded = true)),
                    share(),
                    map(() => true),
                    catchError((err) => {
                        console.log("error" + err)
                        return of(false);
                    })
                )
            : of(this.loaded);
    }
}
