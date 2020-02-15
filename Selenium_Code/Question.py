from selenium import webdriver
import unittest
import HtmlTestRunner
import cv2 as cv

class GoogleSearchQuestion(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome(executable_path=r"C:\Users\Sidhant\Desktop\chromedriver_win32/chromedriver.exe")
        cls.driver.implicitly_wait(20)
        cls.driver.maximize_window()
        cls.file = open("Answers/Answers.txt", 'w', encoding='utf-8')
        cls.var=0
    def test_search_text(self):
        self.driver.get("https://www.google.com/")
        self.driver.find_element_by_name("q").send_keys("What is acid? class 12 chemistry "+"aglasem")
        self.driver.find_element_by_name("q").send_keys(u'\ue007')
        text_box = self.driver.find_element_by_class_name("e24Kjd")
        text = text_box.text
        if (len(text)<=5):
            img = text_box.screenshot_as_png
            cv.imshow(img)
            cv.imwrite("Answers/img"+ str(self.var)+".jpg",img)
            self.var = self.var+1
        print(text,file= self.file)
        self.driver.find_element_by_xpath('//*[@id="rso"]/div[1]/div/div[1]/div/div[1]/div/div[2]/div/div/div[1]/a').click()
        paragraphs = self.driver.find_elements_by_xpath('/html/body/div[2]/div[4]/div[1]/div[1]/div/div/div/div[4]/div[1]/div/div[4]/div[2]')
        for i in paragraphs:
            text = i.text
            print(text,file=self.file)
        self.driver.implicitly_wait(20)
    @classmethod
    def tearDownClass(cls):
        cls.driver.close()
        cls.driver.quit()

if __name__=='__main__':
    unittest.main(testRunner= HtmlTestRunner.HTMLTestRunner(output = r'C:\Users\Sidhant\PycharmProjects\untitled3\Selenium Reports'))
