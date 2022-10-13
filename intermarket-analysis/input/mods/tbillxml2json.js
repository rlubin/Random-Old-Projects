/**
 * reads xml file and converts to json file
 */

module.exports.convert = function() {
  const xmljs = require("xml-js");
  const fs = require("fs");
  const path = require("path");
  const filePathXML = path.resolve(
    __dirname,
    "./../data/DailyTreasuryYieldCurveRateData.xml"
  );
  const filePathJSON = path.resolve(
    __dirname,
    "./../data/DailyTreasuryYieldCurveRateData.json"
  );

  function xml2json(filePath) {
    fs.readFile(filePath, (err, xml) => {
      const result2 = xmljs.xml2json(xml, { compact: true, spaces: 4 });
      fs.writeFile(filePathJSON, result2, err => {
        if (err) {
          console.log(`tbillxml2json.xml2json ${err}`);
        }
      });
    });
  }

  xml2json(filePathXML);
};
