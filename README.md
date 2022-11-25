Это тестовое задание для АО «РИВЦ-Пулково».

Для успешного запуска и взаимодействия с приложением нужны:
    1. PostgreSQL 9.6
    2. Java 17
    3. Maven либо Java-совместимая среда разработки
    4. Curl либо Postman

PostgreSQL можно получить несколькими способами: докер либо установить локально.
    Докер: 
        1. Перейти в папку с docker-compose.yml
        2. В ней запустить docker-compose up
        3. Узнать id контейнера с PostgreSQL
        4. Создать в этом контейнере бд "pulkovo" при помощи команды: 
            sudo docker container exec $ContainerId su postgres -c "createdb -O postgres pulkovo"
        5. Подключиться к БД при помощи любого удобного обозревателя БД (docker-compose up также развернёт еще контейнер с pgadmin4 на порте 9010. Пользователь: golovinvladislav@gmail.com, пароль: 1234)
    Локально:
        1. Убедиться что есть рабочая версия PostgreSQL и развёрнута на порт 5432
        2. Убедиться что есть пользователь "postgres" c паролем "postgres"
        3. От имени этого пользователя создать БД:
            su postgres -c "createdb -O postgres pulkovo"
        4. Подключиться к БД при помощи любого удобного обозревателя БД

При первом запуске приложения автоматически создастся таблица divisions и наполнится автогенерируемыми тестовыми данными (DataLoader.Java). Данные будут добавляться при каждом запуске приложения.

Таблица создана как в описании за исключением id. В описании тип данных - int, а я выбрал long(bigserial). Сделал так потому что, во-первых, подумал что это ошибка (указали ведь что таблица большая и динамическая), во-вторых, я не смог взаимодействовать с поиском по id пока его тип был int. Изначально делал с int, писал кастомный метод в DivisionRepository, но ловил ошибку EntityManager и не разобрался что с ней делать.

Методы:
    1. Чтение. На вход принимал строку, парсил, передавал в метод поиска, но даже при совпадении времени с точностью до минуты, не смог получить запись (вернулся пустой список). Подробнее в комментариях внутри метода. Пример используемого curl-запроса: "curl -v http://127.0.0.1:8080/divisions/2022-11-25 18:19"

    2. Добавление. НЕ смог проверить процедуру добавления т.к. Не понял как в JSON правильно передать дату. Понял что есть такие понятия как таймзоны и оффсеты, но не разобрался. Опирался на "https://www.codebyamir.com/blog/add-a-timezone-to-localdatetime-with-zoneddatetime-in-java-8". Пример curl-запроса: "curl -X POST http://localhost:8080/divisions/add -H 'Content-type:application/json' -d '{"name": "RPD", "dtTill": 2022-04-05T00:00Z[Europe/Moscow]}'"

    3. Изменение. Проверить не смог. Проблема такая же как и в пункте 2

    4. Удаление. Работает. Пример curl-запроса: "curl -X DELETE http://127.0.0.1:8080/divisions/delete/17"