CREATE TABLE IF NOT EXISTS data (
  symbol TEXT NOT NULL, /*string*/
  name TEXT NOT NULL, /*string*/
  type TEXT NOT NULL, /*string value = stock | bond | commodity | currency | economic*/
  dates TEXT, /*in the form of an array YYYY-MM-DD HH:mm:ss:SS*/
  prices TEXT, /*in the form of an array*/
  PRIMARY KEY(symbol, name, type)
);