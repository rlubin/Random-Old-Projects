from tkinter import Tk, Label, Button
from .GraphFuturePrice import GraphFuturePrice


class MainMenu:

    def __init__(self, window):
        self.window = window
        window.title("Commodity Analysis App")

        self.label = Label(window, text="Main Menu")
        self.label.pack()

        self.button = Button(
            window, text="Graph Future Price", command=GraphFuturePrice)
        self.button.pack()


root = Tk()
app = MainMenu(root)
root.mainloop()
