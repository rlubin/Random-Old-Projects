def removeEmptyElements(_list):
  '''
  removes empty elements from list
  '''
  return list(filter(None, _list))

def removeNonASCIICharsString(string):
  '''
  removes non ASCII characters from a string
  '''
  new_string = string
  for char in string:
    if (ord(char) > 126 or ord(char) < 32):
      new_string = new_string.replace(char, '')
  return new_string

def removeNonASCIICharsList(_list):
  '''
  removes non ASCII characters from string in a list
  '''
  new_list = []
  for i in range(0, len(_list)-1):
    new_list.append(removeNonASCIICharsString(_list[i]))
  return new_list

def removeDuplicatesFromList(_list):
  '''
  remove duplicates from string
  '''
  return list(dict.fromkeys(_list))