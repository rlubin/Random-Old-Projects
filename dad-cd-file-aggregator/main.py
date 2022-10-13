from RtfFile import RTFFile
from DocxFile import DOCXFile
import os
from pathlib import Path
from DocxBuilder import DocxBuilder
import Utilities as ut

def createFileArray(files, path):
  array = []
  for file in files:
    file_path = os.path.join(path, file)
    file_ext = file[file.index('.')+1:]
    if file_ext == 'docx':
      array.append(DOCXFile(file_path))
    if file_ext == 'rtf':
      array.append(RTFFile(file_path))
  return array

def writeFile(files, name):
  output_file = os.path.join(output_folder, name + '.txt')
  f = open(output_file, 'w')
  for file in files:
    for line in file.contents:
      f.write(line + "\n")
  f.close()

if __name__ == '__main__':
  folder_path = os.path.join(os.path.abspath('.'), 'files')
  artist_dir = os.listdir(os.path.join(folder_path, 'artist'))
  genre_dir = os.listdir(os.path.join(folder_path, 'genre'))
  artist_files = createFileArray(artist_dir, os.path.join(folder_path, 'artist'))
  genre_files = createFileArray(genre_dir, os.path.join(folder_path, 'genre'))

  output_folder = os.path.join(os.path.abspath('.'), 'output')
  Path(output_folder).mkdir(parents=True, exist_ok=True)

  # writeFile(artist_files, 'artist')
  # writeFile(genre_files, 'genre')

  # this file can be sorted easily
  artist_list = artist_files[0].contents + artist_files[1].contents
  artist_list = ut.removeDuplicatesFromList(artist_list)
  sorted_artist_list = sorted(artist_list, key=str.lower)

  # this list can not be sorted easily because they are in categories
  # where categories are started with all capital names
  # JAZZ
  genre_list = genre_files[0].contents + genre_files[1].contents
  genre_list = ut.removeDuplicatesFromList(genre_list)
  # split the list into sublists, each representing a different category
  title_locations = []
  for i in range(len(genre_list)):
    if(genre_list[i].isupper()):
      # print(genre_list[i], i)
      title_locations.append(i)
  # print(title_locations)
  sub_list_title = []
  sub_list = []
  for i in range(len(title_locations)):
    if (i < len(title_locations)-1):
      start_index = title_locations[i]
      end_index = title_locations[i+1]-1
      sub_list_title.append(genre_list[start_index])
      sub_list.append(genre_list[start_index+1:end_index])
      # print(start_index, end_index)
    else:
      start_index = title_locations[i]
      sub_list_title.append(genre_list[start_index])
      sub_list.append(genre_list[start_index+1:len(genre_list)])
      # print(start_index, len(genre_list))
  # print(sub_list_title[-1])
  # print(sub_list[-1])
  for i in range(len(sub_list)):
    sub_list[i] = sorted(sub_list[i], key=str.lower)
  
  sorted_genre_list = []
  for i in range(len(sub_list_title)):
    sorted_genre_list.append(sub_list_title[i])
    for j in range(len(sub_list[i])):
      sorted_genre_list.append(sub_list[i][j])



  DocxBuilder(sorted_artist_list, os.path.join(os.path.abspath('.'), 'output', 'artists.docx'))
  DocxBuilder(sorted_genre_list, os.path.join(os.path.abspath('.'), 'output', 'categories.docx'))

  # matches = 0

  # print(type(artist_files))
  # for i in artist_files[0].contents:
  #   for j in artist_files[1].contents:
  #     if i == j:
  #       print(i)
  #       matches += 1
  # print(matches)

  # DocxBuilder(artist_files, os.path.join(os.path.abspath('.'), 'output', 'artists.docx'))
  # DocxBuilder(genre_files, os.path.join(os.path.abspath('.'), 'output', 'categories.docx'))