import pandas as pd
from File import File
from striprtf.striprtf import rtf_to_text
import Utilities as ut

class RTFFile(File):
  def __init__(self, path):
    super().__init__(path)
    self.read()

  def read(self):
    '''
    convert rtf file contents to a list of strings
    '''
    with open(self.path, 'r') as file:
      rtf = file.read()
      contents_string = rtf_to_text(rtf)
      contents_list = contents_string.splitlines()
      contents = []
      for content in contents_list:
        contents.append(content.strip())
      self.contents = ut.removeEmptyElements(contents)
      # self.contents = ut.removeNonASCIICharsList(contents)
      