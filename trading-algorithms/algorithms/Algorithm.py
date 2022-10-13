class Algorithm:
    def __init__(self, name='', description=''):
        self.name = name
        self.description = description
        self.length = 0
        self.i = 0

    def on_tick(self, price_data, current_position):
        pass
