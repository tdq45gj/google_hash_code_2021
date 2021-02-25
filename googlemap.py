# -*- coding: utf-8 -*-
"""
Created on Fri Feb 26 01:40:35 2021

@author: flama000711@icloud.com
"""

import os

for FILE in os.listdir('.'):
    
    if FILE.endswith('.py'):
        continue
    
    with open(FILE, 'r') as ifp:
        lines = ifp.readlines()
    
    total_time = int(lines[0].split()[0])
    num_intersection = int(lines[0].split()[1])
    num_street = int(lines[0].split()[2])
    num_car = int(lines[0].split()[3])
    point = int(lines[0].split()[4])
    
    
    #遍历文件的第2到（2+I-1）行，读取street数据
    for i in range(2, 2+num_street):
        print(lines[i])
        
    #遍历文件的第（2+I-1)行到最后一行，读取car数据
    for i in range(2+num_street,len(lines)):
        print(lines[i])