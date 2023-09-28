import {Component} from '@angular/core';

import {retry, RetryConfig} from "rxjs/operators";
import {Subject} from "rxjs";
import {WebSocketSubject} from "rxjs/webSocket";
import {MarkerServiceService} from "./service/marker-service.service";
import {Coordinates} from "./coordinates";
import {ErrorService} from "../global-error/service/error.service";

export const WS_ENDPOINT = "ws://localhost:8081/topic/messages";

@Component({
    selector: 'app-websocket-client',
    templateUrl: './websocket-client.component.html',
    styleUrls: ['./websocket-client.component.css'],
})
export class WebsocketClientComponent {

    title = '';
    content = '';

    coordinates: Coordinates = {
        RSSI: 0,
        SNR: 0,
        Lat: 0,
        Lon: 0,
    }

    RSSI: string = "0"
    SNR: string = "0"

    retryConfig: RetryConfig = {
        delay: 3000,
    };

    websocket$: any

    constructor(
        private markerServiceService: MarkerServiceService,
        private errorService: ErrorService
    ) {
        this.websocket$ = this.createNew()
        this.activate()
    }

    createNew() {
        const open = new Subject()
        const close = new Subject()
        const websocket = new WebSocketSubject({
            url: WS_ENDPOINT,
            openObserver: open,
            closeObserver: close
        })
        close.subscribe(() => {
            console.log("connection closed")
            this.errorService.handle("Connection lost")
            this.websocket$ = null
        })
        open.subscribe(() => {
            console.log("connection opened")
            this.errorService.clear()
        })
        return websocket
    }

    activate() {
        this.websocket$.pipe(retry(this.retryConfig)).subscribe(
            {
                next: (message: any) => {
                    console.log("Receive Message from server:" + JSON.stringify(message))
                    JSON.parse(JSON.stringify(message))
                    console.log("JSON.parse(JSON.stringify(message)):" + JSON.parse(JSON.stringify(message)))
                    this.coordinates = JSON.parse(JSON.stringify(message)) as Coordinates
                    this.markerServiceService.markerContainer$.next({lat: this.coordinates.Lat, lng: this.coordinates.Lon})
                },
                error: (e: any) => {
                    console.error("error" + e)
                },
                complete: () => {
                    console.log('complete')
                    this.websocket$ = this.createNew()
                    this.activate()
                }
            },
        )
    }
}
