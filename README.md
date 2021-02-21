![Build](https://github.com/ALEGATOR1209/MobileLabs/workflows/Android%20.apk%20builder/badge.svg)

### НАЦІОНАЛЬНИЙ ТЕХНІЧНИЙ УНІВЕРСИТЕТ УКРАЇНИ<br/>"КИЇВСЬКИЙ ПОЛІТЕХНІЧНИЙ ІНСТИТУТ ІМЕНІ ІГОРЯ СІКОРСЬКОГО"<br/>Факультет інформатики та обчислювальної техніки<br/>Кафедра обчислювальної техніки

## Лабораторна робота №7
з дисципліни
### "Розроблення клієнтських додатків для мобільних платформ"

__Виконав__:<br/>
студент групи ІП-84<br/>
ЗК ІП-8410<br/>
Ковалишин Олег

Київ 2021

__Варіант 1: 8410 mod 2 + 1 = 1__

__Варіант 2: 8410 mod 6 + 1 = 5__

## Скріншоти роботи додатку
![](screenshots/lab7.gif)

## Лістинг коду
Увесь код можна знайти в репозиторії.
* Модуль [feature-films](feature-films);
* Модуль [feature-gallery](feature-gallery)

Основні файли:
* Файли організації БД у [feature-films/data/datasource/local](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/data/datasource/local);
* Абстракція доступу до даних [FilmRepository](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/data/repository/FilmRepositoryImpl.kt)
* Модуль доступу до даних [feature-gallery/data/datasource/local](feature-gallery/src/main/java/ua/kpi/comsys/ip8410/feature_gallery/data/datasource/local);
* Абстракція доступу до даних [ImageRepository](feature-gallery/src/main/java/ua/kpi/comsys/ip8410/feature_gallery/data/repository/ImageRepositoryImpl.kt);

Під час розробки використовуються підходи View Binding, Single Activity App, MVVM, Kotlin Coroutines.

Для кешування зображень використовувалася бібліотека [Picasso](https://square.github.io/picasso/), для організації SQLite бази даних - бібліотека [Room](https://developer.android.com/jetpack/androidx/releases/room).

## Висновок
Розроблено додаток відповідно до вимог лабораторної роботи.

Зібраний інсталяційний файл додатку можна знайти в [артефактах збірки](https://github.com/ALEGATOR1209/MobileLabs/actions).
