import requests
import json


class FMP:

    def __init__(self):
        # with open("API_KEYS.json", "r") as f:
        #     fmp_key = json.load(f)["keys"]["FMP"]
        # self.key = fmp_key
        self.key = "ab3334a0402b9304852dfa6de9887280"

    def get_daily(self, symbol):
        response = requests.get(
            f"https://financialmodelingprep.com/api/v3/historical-price-full/{symbol}?apikey={self.key}")
        data = response.json()
        # print(data["symbol"])
        # print(data["historical"][0])
        # print(len(data["historical"]))
        return data


# fmp = FMP()
# fmp.get_daily("AAPL")
# fmp.get_daily("CLUSD")
# fmp.get_daily("NGUSD")
# fmp.get_daily("GCUSD")
