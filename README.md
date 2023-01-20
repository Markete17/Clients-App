# ClientsApp
Java & Angular App

## Frontend

Antes de continuar vamos a revisar y modificar el archivo tsconfig.json que se encuentra en la raíz del proyecto angular, todas las configuraciones que diga strict lo cambian a false, por ejemplo:

"strict": false,
...
"strictNullChecks": false,
"strictFunctionTypes": false,
"strictBindCallApply": false,
"strictPropertyInitialization": false 
etc... y cualquier otra configuración que contenga la palabra strict al comienzo lo cambian a false

### Instalar un componente mas rapido
- Crea la carpeta header, componente ts, html y css asociados

- cd components
- ng g c clients

### Instalar una clase mas rapido
- ng generate class client

### Instalar un service mas rapido
- ng g service client

## Bootstrap Jquery Popper
- npm install bootstrap@4.0.0-beta.2 jquery popper.js --save

## Instalar un form component más rapido
- ng g c clients/form --flat

### SweetAlert - Mandar alert más bonitos
- npm install sweetalert2 --save

## Angular Material
- ng add @angular/material

## Backend

### Validar Form
Para validar es muy importante agregar la siguiente dependencia en el pom.xml del proyecto Spring Boot
	<pre><code>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
	</code></pre>

## Desplegar backend
Dirigirse a la carpeta del proyecto backend donde esté el archivo mvnw.cmd y ejecutar:
 - mvnw.cmd clean package
 - java -jar .\target\clients-rest-api-0.0.1-SNAPSHOT.jar

## Desplegar frontend
- ng build --prod
- en dist npm init
- npm install --save
- crear el archivo server.js

## Descargar stomp
- npm install @stomp/stompjs@5.2.0 --save
- npm i sockjs-client --save
- npm i @types/sockjs-client --save-dev
