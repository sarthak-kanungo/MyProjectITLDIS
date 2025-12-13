import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { shareReplay } from 'rxjs/operators';
import { environment } from '../../environments/environment';

interface Configuration {
  resourceServerA: string;
  resourceServerB: string;
  stage: string;
}

@Injectable({
  providedIn: 'root'
})
export class ConfigAssetLoaderService {

  private readonly CONFIG_URL = 'assets/config/config.json';
  private configuration$: Observable<Configuration>;

  constructor(private http: HttpClient) {
  }

  public loadConfigurations(): any {
    

    if (!this.configuration$) {
      this.configuration$ = this.http.get<Configuration>(this.CONFIG_URL).pipe(
        shareReplay(1)
      );
      this.configuration$.subscribe((res) => {
        environment.baseUrl = res['baseUrl'];
        environment.production = res['production'];
        environment.version = res['version'];
        // environment.ssrPort = res['ssrPort'];
      });
    }
    return this.configuration$;
  }
}
