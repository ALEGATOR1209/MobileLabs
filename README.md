![Build](https://github.com/ALEGATOR1209/MobileLabs/workflows/Android%20.apk%20builder/badge.svg)

### НАЦІОНАЛЬНИЙ ТЕХНІЧНИЙ УНІВЕРСИТЕТ УКРАЇНИ<br/>"КИЇВСЬКИЙ ПОЛІТЕХНІЧНИЙ ІНСТИТУТ ІМЕНІ ІГОРЯ СІКОРСЬКОГО"<br/>Факультет інформатики та обчислювальної техніки<br/>Кафедра обчислювальної техніки

## Лабораторна робота №4
з дисципліни
### "Розроблення клієнтських додатків для мобільних платформ"

__Виконав__:<br/>
студент групи ІП-84<br/>
ЗК ІП-8410<br/>
Ковалишин Олег

Київ 2021

__Варіант 8410 mod 2 + 1 = 1__

## Скріншоти роботи додатку
![](screenshots/lab4pic1.png)
![](screenshots/lab4pic2.png)
![](screenshots/lab4pic3.png)
![](screenshots/lab4pic4.png)
![](screenshots/lab4pic5.png)
![](screenshots/lab4pic6.png)
![](screenshots/lab4pic7.png)
![](screenshots/lab4.gif)

## Лістинг коду
Увесь код можна знайти в репозиторії.
Фрагмент відображення фільмів реалізовано у модулі [feature-films](feature-films)

Основні файли:
* [Файли розмітки](feature-films/src/main/res);
* [Модель Film](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/core/domain/model/Film.kt)
* [Абстракція доступу до даних FilmDataSource](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/core/datasource/FilmDataSource.kt) та її [реалізація](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/data/datasource/local/FilmsAssetsDataSource.kt).
* [Адаптер спискового відображення FilmAdapter](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/ui/recycler/FilmAdapter.kt)
* [Фрагмент відображення фільмів FilmListFragment](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/ui/FilmListFragment.kt)
* [Фрагмент відображення фільму FilmFragment](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/ui/FilmFragment.kt)
* [Фрагмент додавання фільму AddFilmFragment](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/ui/add_film/AddFilmFragment.kt)

Під час розробки використовуються підходи View Binding, Single Activity App, MVVM.

## Висновок
Розроблено додаток відповідно до вимог лабораторної роботи.

Зібраний інсталяційний файл додатку можна знайти в [артефактах збірки](https://github.com/ALEGATOR1209/MobileLabs/actions).