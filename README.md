# fr_test by @semykinru

Api для формарирования опросов и прохождения опросов пользователем.

Для запуска приложения локально требуется выполнить следующие команды в корневой папке приложения:

mvn clean package

java -jar target/fr_test-0.1.0.jar

Для запуска в Docker выполняем следующие команды в корневой папке приложения:

 sudo docker build -t poll_api_app:1.0 .
 
 sudo docker run -p8080:8080 --network="host" -t -d poll_api_app:1.0

После запуска приложения с документацией можно ознакомится будет по адрессу:

http://localhost:8080/swagger-ui/

Запросит авторизацию водим
admin
password

