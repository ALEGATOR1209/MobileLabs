![Build](https://github.com/ALEGATOR1209/MobileLabs/workflows/Android%20.apk%20builder/badge.svg)

### НАЦІОНАЛЬНИЙ ТЕХНІЧНИЙ УНІВЕРСИТЕТ УКРАЇНИ<br/>"КИЇВСЬКИЙ ПОЛІТЕХНІЧНИЙ ІНСТИТУТ ІМЕНІ ІГОРЯ СІКОРСЬКОГО"<br/>Факультет інформатики та обчислювальної техніки<br/>Кафедра обчислювальної техніки

## Лабораторна робота №6
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
![](screenshots/lab6pic1.png)
![](screenshots/lab6pic2.png)
![](screenshots/lab6pic3.png)
![](screenshots/lab6pic4.png)
![](screenshots/lab6pic5.png)
![](screenshots/lab6pic6.png)
![](screenshots/lab6.gif)

## Лістинг коду
Увесь код можна знайти в репозиторії.
* Модуль [feature-films](feature-films);
* Модуль [feature-gallery](feature-gallery)

Основні файли:
* Модуль доступу до даних [feature-films/data](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/data);
* [FilmViewModel](feature-films/src/main/java/ua/kpi/comsys/ip8410/feature_films/ui/FilmViewModel.kt);
* Модуль доступу до даних [feature-gallery/data](feature-gallery/src/main/java/ua/kpi/comsys/ip8410/feature_gallery/data);
* [GalleryViewModel](feature-gallery/src/main/java/ua/kpi/comsys/ip8410/feature_gallery/ui/GalleryViewModel.kt).

Під час розробки використовуються підходи View Binding, Single Activity App, MVVM, Kotlin Coroutines.

## Висновок
Розроблено додаток відповідно до вимог лабораторної роботи.

Зібраний інсталяційний файл додатку можна знайти в [артефактах збірки](https://github.com/ALEGATOR1209/MobileLabs/actions).
