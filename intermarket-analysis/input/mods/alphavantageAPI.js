/**
 * call alphavantageAPI
 */

const fetch = require("node-fetch");
const logger = require("./logger");
const db = require("./db");
const path = require("path");
const fs = require("fs");
const APIKeysjson = fs.readFileSync(
  path.resolve(__dirname, "../data/APIKeys.json")
);
const APIKeys = JSON.parse(APIKeysjson);

const sleep = ms => {
  return new Promise(resolve => {
    setTimeout(resolve, ms);
  });
};

// due to API key restriction of 5 queries/min and 500/day
const get = (name, symbol, type) => {
  return sleep(12000)
    .then(v => {
      return alphavantageAPI(name, symbol, type);
    })
    .catch(err => console.log(`alphavantage.get ${err}`));
};

function alphavantageAPI(name, symbol, type) {
  fetch(
    `https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=${symbol}&outputsize=full&apikey=${APIKeys.AlphaVantage}`
  )
    .then(res => res.json())
    .then(body => {
      const newObj = {};
      newObj.name = name;
      const keys = Object.keys(body);
      const dates = Object.keys(body[keys[1]]);
      const values = [];
      const data = {};
      newObj.symbol = symbol;
      newObj.type = type;
      newObj.endDate = body["Meta Data"]["3. Last Refreshed"].toString();
      for (let i = 0; i < dates.length; i++) {
        data[dates[i]] = body[keys[1]][dates[i]]["4. close"];
        values[i] = body[keys[1]][dates[i]]["4. close"];
        if (i === dates.length - 1) {
          newObj.startDate = dates[i];
        }
      }
      newObj.data = data;
      console.log(`${newObj.symbol} - ${newObj.name}`);
      const datesString = dates.join(",");
      const valuesString = values.join(",");
      db.replace(
        newObj.symbol,
        newObj.name,
        newObj.type,
        datesString,
        valuesString
      );
      logger.append(
        `${newObj.symbol} - ${newObj.name}\nType - ${
          newObj.type
        }\nStart date - ${newObj.startDate.substring(
          0,
          10
        )} \nEnd date - ${newObj.endDate.substring(0, 10)} \nData points - ${
          Object.keys(newObj.data).length
        } \nLast price - ${newObj.data[newObj.endDate]} \n---`
      );
    })
    .catch(err => {
      console.error(`alphavantageAPI.alphavantageAPI ${err}`);
    });
}

module.exports = { get };
