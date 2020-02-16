from selenium import webdriver
import unittest
import HtmlTestRunner

class GoogleSearchQuestion():
    def __init__(self,driver,file):
        self.driver = driver
        self.file = file
    def run(self,question,var1,standard=10,subject='science'):
        self.var1 = var1
        self.driver.get("https://www.google.com/")
        self.driver.find_element_by_name("q").send_keys(question+" "+str(standard) +" "+subject+" "+"aglasem")
        self.driver.find_element_by_name("q").send_keys(u'\ue007')
        text_box = self.driver.find_element_by_class_name("e24Kjd")
        self.text = text_box.text
        print(self.text,file= self.file)
        self.driver.find_element_by_xpath('//*[@id="rso"]/div[1]/div/div[1]/div/div[1]/div/div[2]/div/div/div[1]/a').click()
        #//*[@id="rso"]/div[1]/div/div[1]/div/div[1]/div/div[2]/div/div/div[1]/a/h3
        paragraphs = self.driver.find_elements_by_xpath('/html/body/div[2]/div[4]/div[1]/div[1]/div/div/div/div[4]/div[1]/div/div[4]/div[2]')
        for i in paragraphs:
            self.text = i.text
            print(self.text,file=self.file)
        return var1+1
    def close(self):
        self.driver.close()
        self.driver.quit()