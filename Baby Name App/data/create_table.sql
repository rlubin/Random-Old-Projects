CREATE TABLE IF NOT EXISTS baby_names(
  first_name TEXT NOT NULL,
  middle_name TEXT,
  last_name TEXT NOT NULL,
  ip TEXT NOT NULL,
  time TEXT NOT NULL,
  PRIMARY KEY(ip, time)
);