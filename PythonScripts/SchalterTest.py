import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

GPIO.setup(24, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)

i = True
t =1

def button_callback(channel):
    global i
    global t
    if GPIO.input(24) == 1:
        if i:
            f = open("/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt", "w")
            f.write("1")
            f.close()
            i = False
            print(i)
            time.sleep(t)
            
        elif not i:
            f = open("/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt", "w")
            f.write("0")
            f.close()
            i = True
            print (i)
            time.sleep(t)
    else:
        print("7")
        
    print("rdy")

GPIO.add_event_detect(24,GPIO.BOTH,callback=button_callback, bouncetime=300)  

input("Press enter to quit \n\n")

GPIO.cleanup()
