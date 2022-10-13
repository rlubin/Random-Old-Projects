const http = require("http")
const url = require("url")
const fs = require("fs")

const server = http.createServer(request_handler)

server.listen(3000)

function request_handler(request, response) {
  let request_url = url.parse(request.url)
  let time = new Date().toString()
  let ip = request.connection.remoteAddress
  console.log("---")
  console.log("REQUEST TIME: " + time)
  console.log("REQUEST IP: " + ip)
  console.log("REQUEST URL: " + request_url.pathname)
  console.log("REQUEST METHOD: " + request.method)

  let receivedData = "";
  request.on("data", function(chunk) {
    receivedData += chunk
  })

  request.on("end", function () {
    let file_path = null;

    if(request_url.pathname === "/") {
      file_path = "client/index.html"
    }
    else if(request_url.pathname == "/favicon.ico") {
      file_path = "client/favicon.ico"
    }
    else if(request_url.pathname === "/function.js") {
      file_path = "client/function.js"
    }
    else if(request_url.pathname === "/style.css") {
      file_path = "client/style.css"
    }
    else if(request_url.pathname === "/jquery-1.11.3.js") {
      file_path = "client/jquery-1.11.3.js"
    }

    if(!(file_path === null)) {
      fs.readFile(file_path, function (error, data) {

        if (error) {
          console.log("ERROR: " + JSON.stringify(error))
          response.writeHead(404)
          response.end(JSON.stringify(error))
          return
        }

        response.writeHead(200, { "Content-Type": get_file_type(file_path) })
        response.end(data)
      })
    }
    
    if (request.method == "POST" && request_url.pathname === "/add_name") {
      let name = JSON.parse(receivedData)
      console.log("ADD NAME")
      console.log("First name: " + name.first)
      console.log("Middle name: " + name.middle)
      console.log("Last name: " + name.last)
      console.log("Ip: " + ip)
      console.log("Time: " + time)
      const sqlite3 = require("sqlite3").verbose()
      const db = new sqlite3.Database("data/baby_names.db")
      db.run("INSERT INTO baby_names(first_name, middle_name, last_name, ip, time) VALUES(?,?,?,?,?)", [name.first, name.middle, name.last, ip, time], function (err) {

        if(err){
          return console.log(err.message)
        }

        console.log("ENTRY ADDED TO DATABASE")
      })
      db.close()
      response.end()
    }

    if (request.method == "GET" && request_url.pathname === "/view_database") {
      console.log("VIEW DATABASE")
      const sqlite3 = require("sqlite3").verbose()
      const db = new sqlite3.Database("data/baby_names.db")
      let returnObj = {}
      let names = []
      db.all("SELECT first_name, middle_name, last_name FROM baby_names", [], function (err, table) {

        if (err) {
          return console.log(err.message)
        }

        console.log("ENTRIES RETREIVED FROM DATABASE")
        for (let i = 0; i < table.length; i++) {

          if (table[i].middle_name === "") {
            names[i] = table[i].first_name + " " + table[i].last_name
          }
          else {
            names[i] = table[i].first_name + " " + table[i].middle_name + " " + table[i].last_name
          }

        }
        returnObj.names = names
        //console.log(returnObj.names)
        response.writeHead(200, { "Content-Type": file_type["json"]})
        response.end(JSON.stringify(returnObj))
      })
      db.close()
    }

    })
}

const file_type = {
  css: "text/css",
  gif: "image/gif",
  htm: "text/html",
  html: "text/html",
  ico: "image/x-icon",
  jpeg: "image/jpeg",
  jpg: "image/jpeg",
  js: "application/javascript",
  json: "application/json",
  png: "image/png",
  svg: "image/svg+xml",
  txt: "text/plain"
}

function get_file_type(file) {
  for(let ext in file_type) {
    if(file.indexOf(ext, file.length - ext.length) !== -1) {
      return file_type[ext]
    }
  }
  return file_type["txt"]
}

console.log("---\n\n")
console.log("Baby Name App Server Running @")
console.log("http://localhost:3000")