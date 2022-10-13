/**
 * wrapper class for database
 * db functions
 */

const sqlite3 = require("sqlite3").verbose();
const path = require("path");
const dataDB = path.resolve(__dirname, "../../db/data.db");
const analDB = path.resolve(__dirname, "../../db/data.db");
const createDataDB = path.resolve(__dirname, "../../db/dataCreate.sql");
const fs = require("fs");
const createTable = fs.readFileSync(createDataDB).toString();

function replace(symbol, name, type, datesString, valuesString) {
  const db = new sqlite3.Database(dataDB, err => {
    if (err) {
      console.log(`db.replace ${err}`);
    }
  });
  db.run(
    `REPLACE INTO data VALUES (?,?,?,?,?)`,
    [symbol, name, type, datesString, valuesString],
    err => {
      if (err) {
        console.log(`db.replace ${err}`);
      }
    }
  );
  db.close();
}

function replaceV2(table, [symbol, name, type, datesString, valuesString]) {
  const db = new sqlite3.Database(dataDB, err => {
    if (err) {
      console.log(`db.replace ${err}`);
    }
  });
  db.run(
    `REPLACE INTO data VALUES (?,?,?,?,?)`,
    [symbol, name, type, datesString, valuesString],
    err => {
      if (err) {
        console.log(`db.replace ${err}`);
      }
    }
  );
  db.close();
}

// function clear() {
//   const db = new sqlite3.Database(dataDB, err => {
//     if (err) {
//       console.log(`db.clear() const db error: ${err}`);
//     }
//   });
//   db.run("DROP TABLE IF EXISTS data", err => {
//     if (err) {
//       console.log(`db.clear() run error: ${err}`);
//     }
//   });
//   db.run(createTable, err => {
//     if (err) {
//       console.log(`db.clear() run error: ${err}`);
//     }
//   });
//   db.close();
// }

module.exports = {
  replace
  // clear
};
