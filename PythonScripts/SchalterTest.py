import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

GPIO.setup(24, GPIO.IN, pull_up_down = GPIO.PUD_DOWN)

t =1

def button_callback(channel):
    global t
    filepath = "/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt"
    if GPIO.input(24) == 1:
            f = open(filepath , "r")
            i = int(f.read()) + 1
            f.close()
            print i
            g = open(filepath , "w")
            g.write(str(i))
            g.close()
            time.sleep(t)
    else:
        print("7")
        
    print("rdy")

GPIO.add_event_detect(24,GPIO.BOTH,callback=button_callback, bouncetime=300)  

input("Press enter to quit \n\n")

GPIO.cleanup()
