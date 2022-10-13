/**
 * logs to output.log
 */

const fs = require("fs");
const path = require("path");
const filePath = path.resolve(__dirname, "../app.log");

function write(message) {
  fs.writeFile(filePath, message + "\n", err => {
    if (err) {
      console.log(`logger.write ${err}`);
    }
  });
}

function append(message) {
  fs.appendFile(filePath, message + "\n", err => {
    if (err) {
      console.log(`logger.append ${err}`);
    }
  });
}

module.exports = {
  write,
  append
};
