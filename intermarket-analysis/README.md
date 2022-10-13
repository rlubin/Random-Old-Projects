# Intermarket Analysis Application

Download, store and calculates correlations on samples of markets

## Setup and Run

### /input

```
npm i
node app.js
```

see app.log for output

### /processing

```
python correlations.py
```

see correlations.log for output

### /input/mods

To convert and store the US treasury data store in /input/data

```
node tbillxml2json.js
node tbilljson2sqlite.js
```
