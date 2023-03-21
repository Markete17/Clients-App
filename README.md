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

## SonarQube en SpringBoot

1. Descargar SonarQube ->https://www.sonarsource.com/products/sonarqube/downloads/historical-downloads/
2. Acceder a la carpeta descargada desde la terminal y buscar la carpeta bin/windows
3. Ejecutar StartSonar
4. Se ejecutará en el localhost:9000 user:admin password:admin
5. Incluir el plugin de Sonar en el pom.xml

```bash
            <plugin>
                <groupId>org.sonarsource.scanner.maven</groupId>
                <artifactId>sonar-maven-plugin</artifactId>
                <version>3.4.0.905</version>
            </plugin>
```

6. Con el terminal acceder a la raíz del proyecto SpringBoot y ejecutar los siguientes comandos:

```bash

mvnw clean deploy sonar:sonar

```

```bash

mvnw sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=admin


```

7. Métricas encontradas en el localhost:9000 projects

## SonarQube en Angular

[SonarQube en Angular](https://medium.com/beingcoders/setup-sonarqube-with-angular-project-in-6-minutes-57a87b3ca8c4)


## Jenkins con SpringBoot

1. Instalar Jenkins para Windows
[Jenkins](https://www.jenkins.io/download/)

2. Agregar un nuevo Job de tipo Pipeline

3. En el script Pipeline, añadir el script o agregar en el proyecto un JenkinsFile y agregarlo al Pipeline

```node
node {
    stage 'Clone the project'
    git 'https://github.com/Markete17/Clients-App/'
  
    dir('C:/ProgramData/Jenkins/.jenkins/workspace/Jenkins Pipeline/backend/clients-rest-api') {
        stage("Compilation and Analysis") {
            parallel 'Compilation': {
                bat "mvnw clean install -DskipTests"
            }
        }
        stage("Tests and Deployment") {
            parallel 'Unit tests': {
                stage("Runing unit tests") {
                    try {
                        bat "mvnw test -Punit"
                    } catch(err) {
                        step([$class: 'JUnitResultArchiver', testResults: 
                          '**/target/surefire-reports/TEST-*UnitTest.xml'])
                        throw err
                    }
                }
            }, 'Integration tests': {
                stage("Runing integration tests") {
                    try {
                        bat "mvnw test -Pintegration"
                    } catch(err) {
                        step([$class: 'JUnitResultArchiver', testResults: 
                          '**/target/surefire-reports/TEST-' 
                            + '*IntegrationTest.xml'])
                        throw err
                    }
                }
            }
        }
    }

}
```