/**
 * reads json file and adds it to sqlite
 */

module.exports.convert = function() {
  const yieldData = require("./../data/DailyTreasuryYieldCurveRateData.json");
  const fs = require("fs");
  const db = require("./db");
  const logger = require("./logger");

  const properties = {
    "d: NEW_DATE": "DATE",
    "d: BC_1MONTH": "1MONTH",
    "d: BC_2MONTH": "2MONTH",
    "d: BC_3MONTH": "3MONTH",
    "d: BC_6MONTH": "6MONTH",
    "d: BC_1YEAR": "1YEAR",
    "d: BC_2YEAR": "2YEAR",
    "d: BC_3YEAR": "3YEAR",
    "d: BC_5YEAR": "5YEAR",
    "d: BC_7YEAR": "7YEAR",
    "d: BC_10YEAR": "10YEAR",
    "d: BC_20YEAR": "20YEAR",
    "d: BC_30YEAR": "30YEAR"
  };

  const dataObjs = [];

  /**
   * create the list of objects that will eventually be inserted to the database
   */
  for (let item in properties) {
    if (item.toLowerCase().includes("date")) {
    } else {
      let offset = 2;
      if (properties[item].substring(0, 2) / 1) {
        offset = 3;
      }
      const newObj = {
        symbol: `${properties[item].substring(0, offset)}TB`,
        name: `${properties[item]} T-Bill`,
        type: "bond",
        dateArray: [],
        valueArray: []
      };
      dataObjs.push(newObj);
    }
  }

  for (let element in Object.keys(yieldData["feed"]["entry"])) {
    const keys = Object.keys(
      yieldData["feed"]["entry"][element]["content"]["m:properties"]
    );
    for (let key of keys) {
      for (let obj of dataObjs) {
        if (key.includes("DATE")) {
          obj["dateArray"].unshift(
            yieldData["feed"]["entry"][element]["content"]["m:properties"][key][
              "_text"
            ].substring(0, 10)
          );
        } else if (
          key.includes(obj["name"].substring(0, 3)) &&
          !key.includes("DISPLAY")
        ) {
          if (
            yieldData["feed"]["entry"][element]["content"]["m:properties"][key][
              "_text"
            ] === undefined
          ) {
            obj["valueArray"].unshift("0");
          } else {
            obj["valueArray"].unshift(
              parseFloat(
                yieldData["feed"]["entry"][element]["content"]["m:properties"][
                  key
                ]["_text"]
              ).toFixed(4)
            );
          }
        }
      }
    }
  }

  /**
   * reverse all date and value arrays in objects, they are backwards
   */
  for (let obj of dataObjs) {
    if (obj["symbol"] === "10YTB") {
      db.replace(
        obj["symbol"],
        obj["name"],
        obj["type"],
        obj["dateArray"].join(),
        obj["valueArray"].join()
      );
      logger.append(
        `${obj["symbol"]} - ${obj["name"]}\nType - ${
          obj["type"]
        }\nStart date - ${obj["dateArray"][0]}\nEnd date - ${
          obj["dateArray"].slice(-1)[0]
        } \nData points - ${obj["valueArray"].length}\nLast ptice - ${
          obj["valueArray"][0]
        }\n---\n`
      );
      console.log(obj["symbol"] + " - " + obj["name"]);
    }
  }
};
