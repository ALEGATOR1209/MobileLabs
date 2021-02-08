![Build](https://github.com/ALEGATOR1209/MobileLabs/workflows/Android%20.apk%20builder/badge.svg)

### НАЦІОНАЛЬНИЙ ТЕХНІЧНИЙ УНІВЕРСИТЕТ УКРАЇНИ<br/>"КИЇВСЬКИЙ ПОЛІТЕХНІЧНИЙ ІНСТИТУТ ІМЕНІ ІГОРЯ СІКОРСЬКОГО"<br/>Факультет інформатики та обчислювальної техніки<br/>Кафедра обчислювальної техніки

## Лабораторна робота №1.2
з дисципліни
### "Розроблення клієнтських додатків для мобільних платформ"

__Виконав__:<br/>
студент групи ІП-84<br/>
ЗК ІП-8410<br/>
Ковалишин Олег

Київ 2021

Без варіанту

### Скріншоти роботи додатку
Автоматична темна тема на Android 10+
![](screenshots/pic1.png)
![](screenshots/pic2.png)

### Лістинг коду
Увесь код можна знайти в репозиторії. Основні файли:

* [Файли розмітки](app/src/main/res), зокрема [фрагмент першого екрану](app/src/main/res/layout/fragment_student_info.xml)
* [Код відображення](app/src/main/java/ua/kpi/comsys/ip8410/croconut), зокрема [фрагмент навігації](app/src/main/java/ua/kpi/comsys/ip8410/croconut/NavigationFragment.kt), [перший екран](app/src/main/java/ua/kpi/comsys/ip8410/croconut/student_info/StudentInfoFragment.kt)

Під час розробки використовуються підходи View Binding, Single Activity App.

#### Робота 1.2
Завдання ЛР виконано в модулі [lab1-2](lab1-2).
Завдання з першої частини можна знайти і запустити у файл [Main.kt](lab1-2/src/main/java/ua/kpi/comsys/ip8410/time/Main.kt).
Завдання з другої частини можна знайти у класі [TimeOK](lab1-2/src/main/java/ua/kpi/comsys/ip8410/time/TimeOK.kt), а приклади використання - у [юніт-тестах](lab1-2/src/test/kotlin/TimeOKTest.kt).

### Висновок
Розроблено додаток відповідно до вимог лабораторної роботи.

Зібраний інсталяційний файл додатку можна знайти в [артефактах збірки](https://github.com/ALEGATOR1209/MobileLabs/actions).