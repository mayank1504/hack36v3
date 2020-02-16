from selenium import webdriver
import aglasemfin as ag
import Quora_fin as qu


driver = webdriver.Chrome(executable_path=r"C:\Users\Sidhant\Desktop\chromedriver_win32/chromedriver.exe")
driver.implicitly_wait(20)
driver.maximize_window()
file = open("Answers/Answers.txt", 'w', encoding='utf-8')

quora = qu.GoogleSearchQuestion(driver,file)
aglasem = ag.GoogleSearchQuestion(driver,file)



questions = []
questions.append("What is water cycle?")

for i in questions:
    try:
        var = aglasem.run(i,0,subject= 'biology')
    except:
        var = quora.run(i,0)

driver.close()
driver.quit()
