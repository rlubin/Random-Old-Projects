/**
 * where the main work happens
 */

const fs = require("fs");
const logger = require("./mods/logger");
const path = require("path");
const filePath = path.resolve(__dirname, "./data/items.json");
const itemsjson = fs.readFileSync(filePath);
const items = JSON.parse(itemsjson);
const moment = require("moment");
const av = require("./mods/alphavantageAPI");
const tbills2json = require("./mods/tbillxml2json");
const tbills2db = require("./mods/tbilljson2sqlite");

logger.write(
  `program launched @ ${moment().format("YYYY/MM/DD HH:mm:ss:SS")}\n---`
);

async function getAllav() {
  for (item of items.items) {
    const thing = item;
    const result = await av.get(thing.name, thing.symbol, thing.type);
  }
  await tbills2json.convert();
  tbills2db.convert();
}

async function work() {
  const thing = await getAllav();
  logger.append(
    `program finished @ ${moment().format("YYYY/MM/DD HH:mm:ss:SS")}\n---`
  );
}

work();
