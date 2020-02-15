from selenium import webdriver
import unittest
import HtmlTestRunner
import time

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
        self.driver.find_element_by_name("q").send_keys("what is water cycle? "+"quora")
        self.driver.find_element_by_name("q").send_keys(u'\ue007')
        try:
            text_box = self.driver.find_element_by_class_name("e24Kjd")
            self.text = text_box.text
            print(self.text,file= self.file)
        except:
            pass
        self.driver.find_element_by_partial_link_text('Quora').click()
        buttons = self.driver.find_elements_by_class_name('ui_button_label')
        for i in buttons:
            i.click()
        time.sleep(10)

        paragraphs = self.driver.find_elements_by_class_name('ui_qtext_rendered_qtext')
        for i in paragraphs:
            self.text = i.text
            print(self.text,file=self.file)
            self.driver.implicitly_wait(2000)
        self.images =[]
        self.images = self.driver.find_elements_by_tag_name('img')
        for i in self.images :
            try:
                i.screenshot('Answers/img'+str(self.var)+'.png')
                print("@#$img"+str(self.var)+"&*!",file=self.file)
                self.var = self.var+1
            except:
                pass

    @classmethod
    def tearDownClass(cls):
        cls.driver.close()
        cls.driver.quit()

if __name__=='__main__':
    unittest.main(testRunner= HtmlTestRunner.HTMLTestRunner(output = r'C:\Users\Sidhant\PycharmProjects\untitled3\Selenium Reports'))