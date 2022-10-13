import json
import requests


class API:
    def __init__(self):
        with open('./api/key.json') as json_file:
            data = json.load(json_file)
            key = data['key']
        self.key = key

    def get_history(self, sym1, sym2='CAD', lim=100):
        url = f'https://min-api.cryptocompare.com/data/v2/histominute?fsym={sym1}&tsym={sym2}&limit={lim}'
        f = requests.get(url)
        ipdata = f.json()
        print(ipdata)
        return ipdata['Data']
