from PIL import Image
import pytesseract as pt
import cv2 as cv
import tesseract as tt

text = pt.image_to_string(Image.open('Questions/queee.png'))

print(text)

final = []
s=''
for i in text:
  if i[0]=='Q':
    final.append(s)
    s=i
  s = s + i

final_2=[]
for i in final:
  j= i[2:len(i)-2]
  if i!='':
    final_2.append(j)
file = open("Questions/Questions.txt",'w')
print(final_2,file= file)