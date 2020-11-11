


import os
import glob
import pandas as pd

PATH_TO_BASE_PRODUCTS = "./Base Products/Product CSV Files"
PATH_TO_FEATURED_PRODUCTS = "./Featured Products/Product CSV Files"
PATH_TO_BASE_KISS_FILES = "./Base Products/Product kiss Files"
PATH_TO_FEATURED_KISS_FILES = "./Featured Products/Product kiss Files"





def make_kiss_file(data, file_name, path_to_kiss_file):
    file_content = ""
    states = list(data[0].unique())

    
    for state in states:
        transitions = data[data[0] == state]
        for index, row in transitions.iterrows():
            file_content += row[0] + " -- " + row[1] + "/" + row[2] + " -> " + row[3] + "\n"
        file_content += "\n\n"
    f = open(path_to_kiss_file +'/'+ file_name +'.kiss', 'w')
    f.write(file_content)
    f.close()
    

path = PATH_TO_FEATURED_PRODUCTS + "/*.csv"
for fname in glob.glob(path):
    data = pd.read_csv(fname, header=None, index_col=None)
    print(fname.split('/')[-1])
    make_kiss_file(data, fname.split('/')[-1][:-4], PATH_TO_FEATURED_KISS_FILES)


path = PATH_TO_BASE_PRODUCTS + "/*.csv"
for fname in glob.glob(path):
    data = pd.read_csv(fname, header=None, index_col=None)
    print(fname.split('/')[-1])
    make_kiss_file(data, fname.split('/')[-1][:-4], PATH_TO_BASE_KISS_FILES)

    



