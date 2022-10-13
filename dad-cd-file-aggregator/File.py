import os

class File:
  def __init__(self, path):
    self.path = path
    self.name = self.getNameFromPath(path)
    self.contents = []

  def read(self):
    '''
    will be defined by children
    '''
    pass

  def getNameFromPath(self, path):
    '''
    extracts file name from path
    without extension
    '''
    file_name = os.path.basename(path)[:os.path.basename(path).find('.')]
    return file_name