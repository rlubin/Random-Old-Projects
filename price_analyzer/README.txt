purpose is to take price data analyze it for the purposes of identifing stocks that have a high probability of doing something (what is that something, well work that out as we go)

PriceData.py converts the price data into a pandas dataframe with standard columns (datetime, open, high, low, close, volume)

Stats.py runs stats on a PriceData instance

AlphaVantageAPI.py is a wrapper for calling the alpha vantage api that returns price data

API can only be called 5 times/min

if need to get alot of data can save dfs to csv and update them every hour

ex. watchlist of 10 assets @ 5 api calls/min, means I can get the data within 2 minutes (60s between calls at best actually)