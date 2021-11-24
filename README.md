# Desafio Movie2You

<img align="center" alt="Android Studio" src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white" />
<img align="center" alt="Kotlin" src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white" />


## 👨‍💻Tecnologias Utilizadas
* Clean Architecture modularizado
* MVVM (Model-View-ViewModel)
* Navigation Component
* Dagger-Hilt (DI)
* Retrofit2
* Coroutines 
* LiveData

---------------------

## Sobre a API 

* A TheMovieDB é uma api focada em filmes e séries e mantida pela própria comunidade,
 contando com mais de 700,000 filmes e 100,00 séries.

API: https://developers.themoviedb.org/3

---------------------

## Arquitetura do Projeto

A divisão dos módulos:
* app - contém todos os componentes de ui do projeto, juntamente com a iniciaçização do hilt;

* data - possui toda a parte de dados remotos da aplicação, assim como as conversões e lógica entre eles;

* domain - módulo que contém as regras de negócio da aplicação. É puramente Kotlin e como está sendo utilizada a Clean Architecture, possui Casos de Uso;

* presentation - contém todos os viewModels do projeto;

* buildSrc - módulo que contém todas as dependências e versões do projeto, incluindo seus módulos.

---------------------

## Demonstração

<img align="left" height="200" src="https://github.com/BrunoBertolini219/Mobile2YouChallenge/blob/master/readme_assets/Movie2YouApp.gif?raw=true">
<img align="left" height="200" src="https://github.com/BrunoBertolini219/Mobile2YouChallenge/blob/master/readme_assets/movie_list_screen_.jpg?raw=true">
<img align="left" height="200" src="https://github.com/BrunoBertolini219/Mobile2YouChallenge/blob/master/readme_assets/movie_detail_screen.jpg?raw=true">
<img align="bottom" height="200" src="https://github.com/BrunoBertolini219/Mobile2YouChallenge/blob/master/readme_assets/movie_detaik_scree_2.jpg?raw=true">

---------------------

## Melhorias Desejáveis

* Testes (Unitários e Instrumentados)
* Local DB ( Cache dos dados)
* Paginação dos filmes




