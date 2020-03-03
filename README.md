# TradeApp
JavaFx, Hibernate, Maven, MySQL, JSON

# General Information
Desktop applicationn that enables user to commit trades. Application fetches json objects from public API AlphaVantage. Each json object contains recent data about stock prices. User can buy and sell stocks.  
Application gui displays data about submited orders, completed orders, buy orders, sell orders, chart with latest stock prices.

There are four threads in app. Each of them has infinite loop that runs task every few seconds.
1. connectionThread - pings www.google.com and throws exception if there's no connection.
2. tableDataThread - sets data from database into tables.
3. urlAPIThread - fetches json object from API.
4. tradeTransactionsThread - responsible for trades in database. Compares sell orders with buy orders and commits transactions.

