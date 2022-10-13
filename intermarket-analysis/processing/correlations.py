'''
checks for significant correlations between the data in the db
on multiple time frames, reference timeFrame variables
'''

import sqlite3
import pandas as pd
import matplotlib.pyplot as plt
import moment
from db import db

conn = sqlite3.connect("../db/data.db")
# create an add new tables to databases
symbolList = []
valueList = []
dataList = []
dfList = []
dfDict = {}
dfThings = []
correlationDFList = []
# assumes 251 days in average trading year
# 1mth, 3m, 6m, 1y, 3y, 5, 10, 15, 20 in days
timeFrameDisplay = ["1 month", "2 month", "3 month", "6 month", "1 year",
                    "2 year", "3 year", "5 year", "7 year", "10 year", "15 year", "20 year"]
timeFrame = [21, 42, 63, 126, 251, 502, 753, 1255, 1757, 2510, 3765, 5020]
# run on different time frames, example 21 days = 1 month
c = conn.cursor()

for row in c.execute("SELECT * FROM data"):
    dates = row[3].split(",")
    values = row[4].split(",")
    values = list(map(float, values))
    data = {
        "dates": dates,
        "data": values
    }
    # symbolList.append(row[0])
    symbolList.append(row[1])
    valueList.append(values)
    dataList.append(data)


def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        return False


f = open("correlations.log", "w+")

for j in range(0, len(timeFrame)):
    dfList = []
    for i in range(0, len(valueList)):
        if len(valueList[i]) >= timeFrame[j]:
            dfList.append([symbolList[i], pd.Series(
                valueList[i][0:timeFrame[j]])])
            dfDict[symbolList[i]] = valueList[i][0:timeFrame[j]]
    dfGod = pd.DataFrame.from_dict(dfDict)
    # print(dfGod)
    pearsonCorr = dfGod.corr(method="pearson")
    # print(pearsonCorr)
    # f.write(pearsonCorr.to_string())
    val = pearsonCorr.values
    col = pearsonCorr.columns
    significant = []
    for w in range(0, len(val)):
        # print(w)
        for x in range(0, len(val)):
            # print(x)
            # print("index: " + pearsonCorr.index[w])
            # print("col: " + col[x])
            # print("cor: ", val[w][x])
            if pearsonCorr.index[w] != col[x]:
                if val[w][x] > 0.7 or val[w][x] < -0.7:
                    # check if {pearsonCorr.index[w], col[x], val[w][x]} exists in significant
                    if {pearsonCorr.index[w], col[x], val[w][x]} not in significant:
                        significant.append(
                            {pearsonCorr.index[w], col[x], val[w][x]})
    # print("\nOver a ", timeFrameDisplay[j], " period")
    # print("Pearson Correlation\nsignificant relationships:")
    # print(significant)
    # print(len(significant))
    for item in significant:
        # print(item)
        things = []
        for thing in item:
            if is_number(thing) == True:
                # print("TRUE")
                things.insert(0, thing)
                things.insert(1, timeFrameDisplay[j])
            else:
                things.append(thing)
            # print(thing)
            # print(type(thing), thing
            if(type(thing) != str):
                thing = str(thing)
            # f.write(thing + " ")
        # f.write("\n")
        # print(things)
        dfThings.append(things)
        # print(item)
        #
        #
        # do something here to add all of them significant entries into a dataframe
        # correlationDFList.append() # create dataframe from significant list for timeframe
        #
        #
        #
    # f.close()
    dfList = []
    dfDict = {}
    # print(dfThings)

    # print(dfGod)
# print(dfThings)
DFThings = pd.DataFrame(
    dfThings, columns=["correlation", "timeframe", "symbol1", "symbol2"])
print(DFThings)
f.write("" + DFThings.to_string() + "")
f.close()

#
#
#
# error printing the last correlations over and over in log file
#
#
#
