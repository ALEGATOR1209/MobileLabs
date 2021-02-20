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

__Варіант 8410 mod 6 + 1 = 5__

## Скріншоти роботи додатку
![](screenshots/lab5pic1.png)
![](screenshots/lab5pic2.png)
![](screenshots/lab5pic3.png)
![](screenshots/lab5pic4.png)
![](screenshots/lab5.gif)

## Лістинг коду
Увесь код можна знайти в репозиторії.
Фрагмент галереї реалізовано у модулі [feature-gallery](feature-gallery)

Основні файли:
* [Файли розмітки](feature-gallery/src/main/res);
* [Фрагмент GalleryFragment](feature-gallery/src/main/java/ua/kpi/comsys/ip8410/feature_gallery/ui/GalleryFragment.kt);
* [Адаптер списку картинок PhotoAdapter](feature-gallery/src/main/java/ua/kpi/comsys/ip8410/feature_gallery/ui/adapter/PhotoAdapter.kt);
* [Менеджер розміщення картинок GalleryLayoutManager](feature-gallery/src/main/java/ua/kpi/comsys/ip8410/feature_gallery/ui/adapter/GalleryLayoutManager.kt);

Під час розробки використовуються підходи View Binding, Single Activity App, MVVM.

## Висновок
Розроблено додаток відповідно до вимог лабораторної роботи.

Зібраний інсталяційний файл додатку можна знайти в [артефактах збірки](https://github.com/ALEGATOR1209/MobileLabs/actions).
