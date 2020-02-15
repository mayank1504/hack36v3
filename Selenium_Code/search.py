from selenium import webdriver
import unittest
import HtmlTestRunner
import cv2 as cv

class GoogleSearchaIntegrals(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome(executable_path=r"C:\Users\Sidhant\Desktop\chromedriver_win32/chromedriver.exe")
        cls.driver.implicitly_wait(20)
        cls.driver.maximize_window()
    def test_search_text(self):
        self.driver.get("https://www.wolframalpha.com/")
        self.driver.find_element_by_class_name('_3ci9dP6l').click()
        self.driver.find_element_by_class_name('_3mX7MD-u').send_keys("∫x^2dx")
        self.driver.find_element_by_class_name('_3mX7MD-u').send_keys(u'\ue007')
        self.driver.find_element_by_class_name('_3ci9dP6l').click()
        #self.driver.find_element_by_class_name('_2HkkNXzH _1caL4O8E _3nvo6gir').click()
        self.driver.implicitly_wait(20)
    @classmethod
    def tearDownClass(cls):
        cls.driver.close()
        cls.driver.quit()

if __name__=='__main__':
    unittest.main(testRunner= HtmlTestRunner.HTMLTestRunner(output = r'C:\Users\Sidhant\PycharmProjects\untitled3\Selenium Reports'))
