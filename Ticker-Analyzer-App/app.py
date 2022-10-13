import requests
import json
import pandas as pd
import matplotlib.pyplot as plt
import datetime as dt

print("Ticker Analyzer App")
print("Type 'exit' to close program")

while True:
    # get ticker symbol from user
    ticker = input("Please enter ticker: ")

    if ticker == "exit":
        break

    # call alphavantage api to get ticker data
    response = requests.get(
        "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + ticker + "&outputsize=full&apikey=5ZC0WWZ6635PJYOT")

    data = response.json()

    # put response into response.log
    f = open("response.log", "w+")
    f.write(str(response.status_code) + "\n")
    f.write(json.dumps(data))
    f.close()

    tickerData = {
        "dates": [],
        "prices": []
    }

    # save the dates and prices
    for date in data["Time Series (Daily)"].keys():
        tickerData["dates"].insert(
            0, dt.datetime.strptime(date, "%Y-%m-%d").date())
        tickerData["prices"].insert(0,
                                    float(data["Time Series (Daily)"][date]["4. close"]))

    s = pd.Series(tickerData["prices"], index=tickerData["dates"])

    # display graph
    # set graph settings
    # plot stock price
    s.plot()
    plt.title(ticker.upper())
    plt.xlabel("Date")
    plt.ylabel("Price")
    plt.xticks(rotation=45)
    plt.show()
