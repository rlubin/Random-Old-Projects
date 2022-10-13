import pandas as pd
from File import File
from docx import Document
import Utilities as ut

class DOCXFile(File):
  def __init__(self, path):
    super().__init__(path)
    self.read()

  def read(self):
    '''
    convert docx file contents to a list of strings
    '''
    f = open(self.path, 'rb')
    document = Document(f)
    contents = []
    for para in document.paragraphs:
      contents.append(para.text.strip())
    self.contents = ut.removeEmptyElements(contents)
    # self.contents = ut.removeNonASCIICharsList(contents)
    f.close()