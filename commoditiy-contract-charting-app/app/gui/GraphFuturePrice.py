from tkinter import Tk, Label, Button
from logic.GraphFuturePrice import open_and_graph


class GraphFuturePrice:

    def __init__(self):
        self.window = Tk()
        self.window.title("Graph Future Price")

        self.label = Label(
            self.window, text="Select commodity csv from barchart.com")
        self.label.pack()

        self.button = Button(
            self.window, text="open", command=open_and_graph)
        self.button.pack()

        self.window.mainloop()
