from tkinter.filedialog import askopenfilename
import csv
import pandas as pd
import matplotlib.pyplot as plt
import datetime as dt


def open_and_graph():
    filepath = askopenfilename(
        initialdir="/", title="Select file", filetypes=(("csv files", "*.csv"), ("all files", "*.*")))

    contract = {
        "date": [],
        "price": []
    }

    with open(filepath) as csv_file:
        csv_reader = csv.DictReader(csv_file)
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                line_count += 1
            contract["date"].append(
                row["Contract"][row["Contract"].find("(")+1:row["Contract"].find(")")])
            contract["price"].append(row["Last"])
            line_count += 1

    # removing extra data from barchart csv
    contract["date"].pop()
    contract["price"].pop()

    # convert price data to floats
    # format dates to "mmm yy"
    for i in range(0, len(contract["price"])):
        if i == 0:
            contract["date"][i] = dt.datetime.today().strftime("%b %y")
        contract["date"][i] = contract["date"][i].replace("'", "")
        contract["price"][i] = float(contract["price"][i])
        contract["date"][i] = dt.datetime.strptime(
            contract["date"][i], "%b %y")

    s = pd.Series(contract["price"], index=contract["date"])

    filename = filepath[filepath.rfind("/")+1:len(filepath)+1]

    s.plot(legend=False, title=filename)
    plt.title(filename)
    plt.ylabel("Price")
    plt.xlabel("Date")
    plt.show()
