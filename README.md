# Курсовой проект "Акционный дашбоард"

### Что реализовано:

1. Отображение новостей рынка
2. Поиск акций по наиболее подходящим символам на основе запроса. Можно ввести что угодно, от символа, названия ценной бумаги до ISIN и Cusip.
3. Отображение акции
    1. Отображение тикера
    2. Отображение названия компании
   3. Отображение торгующей биржи
   4. Отображение валюты
   5. Отображение текущей стоимости
   6. Отображение процентного изменения
   7. Возможность добавить или удалить акцию из избранного
   8. Отображение японской свечи за 3 прошедших месяца
   9. Отображение базовых показателей акции
4. Отображение списка избранных акций
5. Регистрация и авторизация пользователей

* [Сервис для получения финансовой информации](https://finnhub.io/)

### Как запустить

Для запуска необходимо в файле docker-compose.yml запустить сервис backend (docker-compose up -d backend). Для старта необходим установленный Docker Desktop

