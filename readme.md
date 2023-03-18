# Warehouse Shipment Server
Сервер, используемый для организации хранения информации об отправленных заказах.
## Возможности
- Аутентификация пользователя с использованием JWT.
- Вывод информации обо всех отправленных заказах.
- Сохранение информации о времени сборки и сборщике.
- Сохранение изображений собранного заказа.
- Возможность оставить комментарий о заказе.
- Получение информации о составе заказе по запросу из внутренней системы (1С или какой-нибудь другой сервис).
- Возможность подсветить "физическое" место хранения для быстрого поиска продукта.

## Использованные технологии
- KTOR
- Exposed и MySQL драйвер
- База данных MySQL

## Использование

Для запуска сервера необходимо в конфигурационном файле `applicaiton.conf` указать данные для доступа к БД и путь к папке, где будут сохраняться изображения.

Для получения токена JWT необходимо отправить POST запрос по адресу `<server_url>/user/login`
```json
{
    "username": "<NAME>",
    "password": "<Password>"
}
```
В случае успешного входа будет возвращен JWT токен, иначе сообщение об ошибке.

Для получения инормации обо всех заказах необходимо отправить GET запрос по адресу `<server_url>/orders/all`. Ответом будет список объектов `Order`.

Пользователь может запросить детальную информацию о заказе, включая изображения, отправив GET запрос по адресу `<server_url>/orders/name/order_name`, где order_name - имя заказа. Во время выполнения запроса сервер запрашивает информацию о составе заказа в локальной базе данных. Ответом будет объект `Order`.

Для получения изображения по имени изображения необходимо отправить GET запрос по адресу `<server_url>/orders/image/image_name`, где image_name - имя изоюражения. Ответом будет файл с изображением.

Для создания нового заказа необходимо отправить POST запрос с json объектом `Order`  по адресу `<server_url>/orders/new`

Для загрузки изображения на сервер необходимо отправить POST запрос с изображением в `MultipartData` по адресу `<server_url>/orders/uploadImage`

При сборке заказа пользователь может запросить у сервера указание месторасположения необходимого товара, отправив GET запрос по адресу `<server_url>/orders/product/art`, где art - артикул товара. Это запрос транслируется в локальную систему "Умного склада", где соответствующим образом обрабатывается.

Конфигурация Базы Данных представлена в файле `MySQL configuration.txt`