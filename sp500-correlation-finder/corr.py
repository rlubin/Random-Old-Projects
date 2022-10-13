import pandas as pd

# return correlation dataframe of list of PriceData instances
# create a dataframe out of pricedata list
# get the correlation of the dataframe
# display a heatmap of the dataframe


def corr(dataframe_list, per=1, column='close'):
    # print(dataframe_list)
    shortframe = []
    for x in range(0, len(dataframe_list)):
        shortframe.append(dataframe_list[x][0:per])
    df = pd.concat(shortframe)
    df = df.reset_index()
    df = df[['index', 'close', 'symbol']]
    df_pivot = df.pivot('index', 'symbol', 'close').reset_index()
    corr_df = df_pivot.corr(method='pearson')
    corr_df.head().reset_index()
    # del corr_df.index.name
    return corr_df
