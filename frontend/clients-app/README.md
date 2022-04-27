# ClientsApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 13.3.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


## Observaciones

Antes de continuar vamos a revisar y modificar el archivo tsconfig.json que se encuentra en la raíz del proyecto angular, todas las configuraciones que diga strict lo cambian a false, por ejemplo:

"strict": false,
...
"strictNullChecks": false,
"strictFunctionTypes": false,
"strictBindCallApply": false,
"strictPropertyInitialization": false 
etc... y cualquier otra configuración que contenga la palabra strict al comienzo lo cambian a false

## Instalar un componente mas rapido
- Crea la carpeta header, componente ts, html y css asociados

- cd components
- ng g c clients

## Instalar una clase mas rapido
- ng generate class client

## Instalar un service mas rapido
- ng g service client

## Bootstrap Jquery Popper
- npm install bootstrap@4.0.0-beta.2 jquery popper.js --save

