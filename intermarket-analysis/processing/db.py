'''
wrapper class for databases
'''

import sqlite3

class db():

  def getData(self):
    data = sqlite3.connect("../db/marketData.db")
    c = data.cursor()
    result = c.execute("SELECT * FROM data")
    data.close
    return result

  # def setAnal(self):
    # anal = sqlite3.connect("../db/analysis.db")
    # c. = anal.cursor()
    # c.executemany("REPLACE INTO anal(column_list) VALUES(value_list)") # incorrect column names and values
    # anal.commit()
    # anal.close
