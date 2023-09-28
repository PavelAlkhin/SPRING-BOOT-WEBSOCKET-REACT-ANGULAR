import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ErrorService {

    error$: Subject<string> = new Subject<string>()

    handle(message: string) {
        this.error$.next(message)
    }

    clear() {
        this.error$.next('')
    }

}
