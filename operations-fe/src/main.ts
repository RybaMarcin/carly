import {enableProdMode, Injector} from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

import 'core-js/es6/reflect';
import 'core-js/es7/reflect';

export let appInjector: Injector;

import 'hammerjs';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule).then(
  (componentRef) => {
    appInjector = componentRef.injector;
  })
  .catch(err => console.error(err));
