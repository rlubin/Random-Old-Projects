import pandas as pd
import numpy as np

'''
converts csv to dataframe on construct
this class holds all of the price data
'''


class PriceData:
    def __init__(self, file, _type):
        if(_type == "stock"):
            self.data = self.csv_to_df1(file)
        if(_type == "crypto"):
            self.data = self.csv_to_df2(file)
        self.length = len(self.data)
        self.start_date = self.data['Date'][0]
        self.end_date = self.data['Date'][len(self.data)-1]
        self.name = file[file.rfind('/')+1:len(file)-4]

    # converts a csv security data to a dataframe
    def csv_to_df1(self, filename):
        df = pd.read_csv(filename)
        df['Date'] = pd.to_datetime(df['Date'], format="%Y-%m-%d")
        return df

    # converts a csv security data to a dataframe
    def csv_to_df2(self, filename):
        df = pd.read_csv(filename)
        df['Date'] = pd.to_datetime(df['Date'], format="%Y-%m-%d")
        df = df[::-1]
        df['order'] = np.arange(len(df))
        df = df.set_index('order')
        del df.index.name
        return df
