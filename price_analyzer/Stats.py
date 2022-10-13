'''
class takes a PriceData object and can run stats on it
assumes data is non-parametric
dox link
https://pandas.pydata.org/docs/
'''

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np


class Stats:

    # price_data is a pandas dataframe
    def __init__(self, price_data):
        self.name = price_data['symbol'][0]
        self.price_data = price_data

    # return standard devation of last per periods of column
    def std(self, per=1, column='close'):
        if(per > len(self.price_data)):
            per = len(self.price_data)
        return self.price_data[column][0:per].std()

    # return variance of last per periods of column
    def var(self, per=1, column='close'):
        if(per > len(self.price_data)):
            per = len(self.price_data)
        return self.price_data[column][0:per].var()

    # return mean of column
    def mean(self, per=1, column='close'):
        if(per > len(self.price_data)):
            per = len(self.price_data)
        return self.price_data[column][0:per].mean()

    # return median of column
    def median(self, per=1, column='close'):
        if(per > len(self.price_data)):
            per = len(self.price_data)
        return self.price_data[column][0:per].median()

    # return mode of column
    def mode(self, per=1, column='close'):
        if(per > len(self.price_data)):
            per = len(self.price_data)
        return self.price_data[column][0:per].mode()

    # display history and density curve of last per periods of column
    # has mean and stds lines
    def hist(self, per=1, column='close'):
        if(per > len(self.price_data)):
            per = len(self.price_data)
        data = self.price_data[column][0:per]
        mean = self.mean(per, column)
        median = self.median(per, column)
        center = mean
        if(abs(median-mean) > 0.1*mean):
            center = median
        std1p = center + self.std(per, column)
        std1m = center - self.std(per, column)
        std2p = center + (2*self.std(per, column))
        std2m = center - (2*self.std(per, column))
        lines = [center, std1p, std1m, std2p, std2m]
        # print(self.name)
        # print('68% range = ' + str(round(std1m, 2)) +
        #       ' to ' + str(round(std1p, 2)))
        # print('95% range = ' + str(round(std2m, 2)) +
        #       ' to ' + str(round(std2p, 2)))
        ax = sns.distplot(data, color='darkblue', hist_kws={
            'edgecolor': 'black'})
        ax.set_title(self.name + ' histogram and density plot from ' +
                     data.keys()[0].strftime('%Y-%m-%d') + ' to ' + data.keys()[per-1].strftime('%Y-%m-%d'))
        ax.set_ylabel('density')
        i = 0
        for line in lines:
            if(i == 0):
                plt.axvline(line, color='r',
                            linestyle='solid', linewidth=1)
            else:
                plt.axvline(line, color='k',
                            linestyle='dotted', linewidth=1)
            i = i + 1
        plt.show()

    # return max of column over per peroids
    def max(self, per=1, column='close'):
        return self.price_data[column][0:per].max()

    # return min of column over per peroids
    def min(self, per=1, column='close'):
        return self.price_data[column][0:per].min()

    # return correlation dataframe of list of PriceData instances
    # create a dataframe out of pricedata list
    # get the correlation of the dataframe
    # display a heatmap of the dataframe
    def corr(self, dataframe_list, per=1, column='close'):
        df = pd.concat(dataframe_list)
        df = df.reset_index()
        df = df[['index', 'close', 'symbol']]
        df_pivot = df.pivot('index', 'symbol', 'close').reset_index()
        corr_df = df_pivot.corr(method='pearson')
        corr_df.head().reset_index()
        del corr_df.index.name
        # take the bottom triangle since it repeats itself
        mask = np.zeros_like(corr_df)
        mask[np.triu_indices_from(mask)] = True
        # generate plot
        sns.heatmap(corr_df, cmap='RdYlGn', vmax=1.0,
                    vmin=-1.0, mask=mask, linewidths=2.5)
        plt.yticks(rotation=0)
        plt.xticks(rotation=90)
        plt.show()
